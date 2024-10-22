document.getElementById('downloadExcelFormOfWordBtn').addEventListener('click', function() {
	window.location.href = '/dms/downloadExcelFormOfWord'
});

const selectElementInBulkWord = document.getElementById("selectStandardArea");

document.getElementById("inputGroupFileAddon04").addEventListener("click", function(event) {
    const stdAreaName = document.getElementById("selectStandardArea").value;
    const form = document.getElementById("fileUploadForm");
    const input = document.createElement("input");
    input.type = "hidden";
    input.name = "stdAreaName";
    input.value = stdAreaName;
    form.appendChild(input);

    form.submit();
});

function selectAvailableAll(selectAvailableAll) {
    const avaCheckboxes = document.getElementsByName('availableRow');
    avaCheckboxes.forEach((checkbox) => {
        checkbox.checked = selectAvailableAll.checked;
    });
}

function selectUnavailableAll(selectUnavailableAll) {
    const unAvaCheckboxes = document.getElementsByName('unavailableRow');
    unAvaCheckboxes.forEach((checkbox) => {
        checkbox.checked = selectUnavailableAll.checked;
    });
}

document.addEventListener("DOMContentLoaded", () => {
    const saveAvaRowToExcelButton = document.getElementById('saveAvaRowToExcel');
    const saveUnAvaRowToExcelButton = document.getElementById('saveUnAvaRowToExcel');

    function extractSelectedRows(checkboxName) {
        const checkboxes = document.getElementsByName(checkboxName);
        const selectedRows = [];

        for (let i = 1; i < checkboxes.length; i++) {
            if (checkboxes[i].checked) {
                const dataFields = [
                    "dicLogicalName",
                    "dicPhysicalName",
                    "dicPhysicalFullName",
                    "entitySuffix",
                    "attributeSuffix",
                    "synonym",
                    "dicDescription",
                    "reason"
                ];

                const selectedData = {};

                dataFields.forEach(field => {
                    const fieldValue = checkboxes[i].closest("tr").querySelector(`[name='${field}']`).innerText;
                    selectedData[field] = field === 'synonym' || field === 'reason' ? [fieldValue] : fieldValue;
                });

                selectedRows.push(selectedData);
            }
        }

        return selectedRows;
    }

    function logSelectedRows(checkboxName) {
        const selectedRows = extractSelectedRows(checkboxName);
        if (selectedRows.length > 0) {
            sendBulkWordData(selectedRows);
        } else {
            showAlert("출력할 항목을 선택해 주세요.");
        }
    }

    saveAvaRowToExcelButton.addEventListener("click", () => {
        logSelectedRows("availableRow");
    });

    saveUnAvaRowToExcelButton.addEventListener("click", () => {
        logSelectedRows("unavailableRow");
    });
});

function sendBulkWordData(selectedRows) {
    $.ajax({
        type: "POST",
        url: "/dms/bulk-word/download",
        contentType: "application/json",
        data: JSON.stringify(selectedRows),
        responseType: 'arraybuffer',
        success: function (response) {
            let byteCharacters = atob(response);
            let byteNumbers = new Array(byteCharacters.length);
            for (let i = 0; i < byteCharacters.length; i++) {
                byteNumbers[i] = byteCharacters.charCodeAt(i);
            }
            let byteArray = new Uint8Array(byteNumbers);
            let blob = new Blob([byteArray], { type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" });

            let downloadLink = document.createElement("a");
            downloadLink.href = URL.createObjectURL(blob);
            downloadLink.download = "word.xlsx";

            document.body.appendChild(downloadLink);
            downloadLink.click();
            document.body.removeChild(downloadLink);
        },
        error: function (error) {
            console.error(error);
        }
    });
}

document.addEventListener("DOMContentLoaded", () => {
    //fetchDataFromServer();

    const insertButton = document.getElementById("insertBulkWordButton");

    insertButton.addEventListener("click", () => {
        const checkboxes = document.getElementsByName("availableRow");
        const selectedRows = [];

        for (let i = 1; i < checkboxes.length; i++) {
            if (checkboxes[i].checked && checkboxes[i].value === 'Y') {
                const insertFields = [
                    "stdAreaId",
                    "dicLogicalName",
                    "dicPhysicalName",
                    "dicPhysicalFullName",
                    "entitySuffix",
                    "attributeSuffix",
                    "synonym",
                    "dicDescription",
                    "reason"
                ];

                const selectedData = {};

                insertFields.forEach(field => {
                    const stdAreaId = selectElementInBulkWord.value;
                    let fieldValue = checkboxes[i].closest("tr").querySelector(`[name='${field}']`);
                    fieldValue = fieldValue === null ? fieldValue : fieldValue.innerText.trim();
                    if (field === "stdAreaId") {
                        selectedData[field] = stdAreaId;
                    } else {
                        selectedData[field] = field === 'synonym' || field === 'reason' ? [fieldValue] : fieldValue;
                    }
                });

                selectedRows.push(selectedData);
            }
        }

        if (selectedRows.length > 0) {
            insertBulkWordData(selectedRows);
        } else {
            showAlert("등록할 항목을 선택해 주세요.");
        }
    });
});

function insertBulkWordData(selectedRows) {
    showLoadingSpinner();
    $.ajax({
        type: "POST",
        url: "/dms/bulk-word/insert",
        contentType: "application/json",
        data: JSON.stringify(selectedRows),
        success: function (insertedWordDataList) {
            $("#exLargeModal .modal-body").empty();

            const headerHtml = `
                <div class="table-responsive text-nowrap">
                    <div class="card-body both-scrollbars-example">
                        <table class="table">
                            <thead class="thead-sticky">
                                <tr class="text-nowrap">
                                    <th>번호</th>
                                    <th>논리명</th>
                                    <th>물리명</th>
                                    <th>영문풀네임</th>
                                    <th>엔티티 분류어</th>
                                    <th>속성 분류어</th>
                                    <th>동의어</th>
                                    <th>설명</th>
                                    <th>사유</th>
                                </tr>
                            </thead>
                            <tbody>
            `;

            $("#exLargeModal .modal-body").append(headerHtml);

            insertedWordDataList.forEach(function (insertedWordData, index) {

                const synonymHtml = insertedWordData.synonym
                    ? insertedWordData.synonym
                        .map((synonym, synonymIndex) => `${synonym}${synonymIndex !== insertedWordData.synonym.length - 1 ? ' , ' : ''}`)
                        .join('')
                    : '';

                const reasonsHtml = insertedWordData.reason.map(function (reason, reasonIndex) {
                    return `${reason}${reasonIndex !== insertedWordData.reason.length - 1 ? ' / ' : ''}`;
                }).join('');

                const rowHtml = `
                    <tr>
                        <th scope="row">${index + 1}</th>
                        <input type="hidden" name="stdAreaId" value="${insertedWordData.stdAreaId}">
                        <td name="dicLogicalName">${insertedWordData.dicLogicalName}</td>
                        <td name="dicPhysicalName">${insertedWordData.dicPhysicalName}</td>
                        <td name="dicPhysicalFullName">${insertedWordData.dicPhysicalFullName}</td>
                        <td name="entitySuffix">${insertedWordData.entitySuffix}</td>
                        <td name="attributeSuffix">${insertedWordData.attributeSuffix}</td>
                        <td name="synonym">${synonymHtml}</td>
                        <td name="dicDescription">${insertedWordData.dicDescription}</td>
                        <td name="reason">${reasonsHtml}</td>
                    </tr>
                `;

                $("#exLargeModal .modal-body table tbody").append(rowHtml);
            });

            const footerHtml = `
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            `;

            $("#exLargeModal .modal-body").append(footerHtml);

            const myModal = new bootstrap.Modal(document.getElementById("exLargeModal"));
            myModal.show();
            showAlert("단어 등록이 완료되었습니다.", "success");
            hideLoadingSpinner();

        },
        error: function (error) {
            console.error(error);
            hideLoadingSpinner();
        }
    });
}

document.getElementById('confirmBtn').addEventListener('click', function() {
    window.location.href = '/dms/bulk-word';
});

