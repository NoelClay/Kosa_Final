document.getElementById('downloadExcelFormOfTermBtn').addEventListener('click', function() {
	window.location.href = '/dms/downloadExcelFormOfTerm'
});

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
                    "tempDicLogicalName",
                    "dicDescription",
                    "keyDomainName",
                    "domainTypeCode",
                    "domainGroupName",
                    "dataTypeCode",
                    "dataLength",
                    "dataScale",
                    "dicLogicalName",
                    "dicPhysicalName",
                    "domainName",
                    "reason"
                ];

                const row = {};

                dataFields.forEach(field => {
                    row[field] = checkboxes[i].closest("tr").querySelector(`[name='${field}']`).innerText;
                });

                row.reason = [row.reason];

                selectedRows.push(row);
            }
        }

        return selectedRows;
    }

    function logSelectedRows(checkboxName) {
        const selectedRows = extractSelectedRows(checkboxName);
        if (selectedRows.length > 0) {
            sendBulkTermData(selectedRows);
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

function sendBulkTermData(selectedRows) {
    $.ajax({
        type: "POST",
        url: "/dms/bulk-term/download",
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
            downloadLink.download = "term.xlsx";

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
    const insertButton = document.getElementById("insertBulkTermButton");

    insertButton.addEventListener("click", () => {
        const checkboxes = document.getElementsByName("availableRow");
        const selectedRows = [];

        for (let i = 1; i < checkboxes.length; i++) {
            if (checkboxes[i].checked && checkboxes[i].value === 'Y') {
                const insertFields = [
                    "stdAreaId",
                    "tempDicLogicalName",
                    "dicDescription",
                    "keyDomainName",
                    "domainTypeCode",
                    "domainGroupName",
                    "dataTypeCode",
                    "dataLength",
                    "dataScale",
                    "dicLogicalName",
                    "dicPhysicalName",
                    "domainName",
                    "reason"
                ];

                const values = {};

                insertFields.forEach(field => {
                    const stdAreaId = document.getElementById('selectStandardArea').value;
                    let fieldValue = checkboxes[i].closest("tr").querySelector(`[name='${field}']`);
                    fieldValue = fieldValue === null ? fieldValue : fieldValue.innerText.trim();
                    if (field === "stdAreaId") {
                        values[field] = stdAreaId;
                    } else {
                        values[field] = fieldValue;
                    }

                });

                values.reason = [values.reason];

                selectedRows.push(values);
            }
        }

        if (selectedRows.length > 0) {
            insertBulkTermData(selectedRows);
        } else {
            showAlert("등록할 항목을 선택해 주세요.");
        }
    });
});

function insertBulkTermData(selectedRows) {
    showLoadingSpinner();
    $.ajax({
        type: "POST",
        url: "/dms/bulk-term/insert",
        contentType: "application/json",
        data: JSON.stringify(selectedRows),
        success: function (insertedTermDataList) {
            showAlert("용어 등록이 완료되었습니다.", "success");
            $("#exLargeModal .modal-body").empty();

            const headerHtml = `
                <div class="table-responsive text-nowrap">
                    <div class="card-body both-scrollbars-example">
                        <table class="table">
                            <thead class="thead-sticky">
                                <tr class="text-nowrap">
                                    <th>번호</th>
                                    <th>용어명</th>
                                    <th>용어설명</th>
                                    <th>대표도메인명</th>
                                    <th>도메인유형</th>
                                    <th>도메인그룹</th>
                                    <th>논리데이터타입</th>
                                    <th>데이터길이</th>
                                    <th>소수점</th>
                                    <th>논리용어명</th>
                                    <th>물리용어명</th>
                                    <th>도메인명</th>
                                    <th>사유</th>
                                </tr>
                            </thead>
                            <tbody>
            `;

            $("#exLargeModal .modal-body").append(headerHtml);

            insertedTermDataList.forEach(function (insertedTermData, index) {

                const reasonsHtml = insertedTermData.reason.map(function (reason, reasonIndex) {
                    return `${reason}${reasonIndex !== insertedTermData.reason.length - 1 ? ' / ' : ''}`;
                }).join('');

                const rowHtml = `
                    <tr>
                        <th scope="row">${index + 1}</th>
                        <td name="tempDicLogicalName">${insertedTermData.tempDicLogicalName}</td>
                        <td name="dicDescription">${insertedTermData.dicDescription ? insertedTermData.dicDescription : ''}</td>
                        <td name="keyDomainName">${insertedTermData.keyDomainName}</td>
                        <td name="domainTypeCode">${insertedTermData.domainTypeCode}</td>
                        <td name="domainGroupName">${insertedTermData.domainGroupName ? insertedTermData.domainGroupName : ''}</td>
                        <td name="dataTypeCode">${insertedTermData.dataTypeCode}</td>
                        <td name="dataLength">${insertedTermData.dataLength ? insertedTermData.dataLength : ''}</td>
                        <td name="dataScale">${insertedTermData.dataScale ? insertedTermData.dataScale : ''}</td>
                        <td name="dicLogicalName">${insertedTermData.dicLogicalName}</td>
                        <td name="dicPhysicalName">${insertedTermData.dicPhysicalName}</td>
                        <td name="domainName">${insertedTermData.domainName}</td>
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
            hideLoadingSpinner();
        },
        error: function (error) {
            console.error(error);
            hideLoadingSpinner();
        }
    });
}

document.getElementById('confirmBtn').addEventListener('click', function() {
    window.location.href = '/dms/bulk-term';
});

