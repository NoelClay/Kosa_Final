document.getElementById('downloadExcelFormOfDomainBtn').addEventListener('click', function() {
	window.location.href = '/dms/downloadExcelFormOfDomain'
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
                    "stdAreaId",
                    "keyDomainName",
                    "domainName",
                    "domainTypeCode",
                    "domainGroupName",
                    "domainGroupId",
                    "dataTypeCode",
                    "dataLength",
                    "dataScale",
                    "dataMin",
                    "dataMax",
                    "domainDescription",
                    "reason"
                ];

                const row = {
                    stdAreaId: checkboxes[i].closest("tr").querySelector("input[name='stdAreaId']").value.trim(),
                };

                dataFields.forEach(field => {
                    row[field] = checkboxes[i].closest("tr").querySelector(`[name='${field}']`).innerText.trim();
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
            sendBulkDomainData(selectedRows);
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

function sendBulkDomainData(selectedRows) {
    $.ajax({
        type: "POST",
        url: "/dms/bulk-domain/download",
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
            downloadLink.download = "domain.xlsx";

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
    const insertButton = document.getElementById("insertBulkDomainButton");

    insertButton.addEventListener("click", () => {
        const checkboxes = document.getElementsByName("availableRow");
        const selectedRows = [];

        for (let i = 1; i < checkboxes.length; i++) {
            if (checkboxes[i].checked && checkboxes[i].value === 'Y') {
                const insertFields = [
                    "stdAreaId",
                    "keyDomainName",
                    "domainName",
                    "domainDescription",
                    "domainTypeCode",
                    "domainGroupName",
                    "domainGroupId",
                    "dataTypeCode",
                    "dataLength",
                    "dataScale",
                    "dataMin",
                    "dataMax",
                    "reason"
                ];

                const values = {};

                insertFields.forEach(field => {
                    const stdAreaId = document.getElementById('selectStandardArea').value.trim();
                    let fieldValue = checkboxes[i].closest("tr").querySelector(`[name='${field}']`);
                    if (field === 'domainGroupId' && fieldValue !== null) {
                        values[field] = fieldValue.value.trim();
                    } else if (field === 'stdAreaId') {
                        values[field] = stdAreaId;
                    } else {
                        fieldValue = fieldValue === null ? fieldValue : fieldValue.innerText.trim();
                        values[field] = fieldValue;
                    }
                });

                values.reason = [values.reason];

                selectedRows.push(values);
            }
        }

        if (selectedRows.length > 0) {
            insertBulkDomainData(selectedRows);
        } else {
            showAlert("등록할 항목을 선택해 주세요.");
        }
    });
});

function insertBulkDomainData(selectedRows) {
    showLoadingSpinner();
    $.ajax({
        type: "POST",
        url: "/dms/bulk-domain/insert",
        contentType: "application/json",
        data: JSON.stringify(selectedRows),
        success: function (insertedDomainDataList) {
            $("#exLargeModal .modal-body").empty();

            const headerHtml = `
                <div class="table-responsive text-nowrap">
                    <div class="card-body both-scrollbars-example">
                        <table class="table">
                            <thead class="thead-sticky">
                                <tr class="text-nowrap">
                                    <th>번호</th>
                                    <th>대표도메인명</th>
                                    <th>도메인명</th>
                                    <th>도메인유형</th>
                                    <th>도메인그룹</th>
                                    <th>논리데이터타입</th>
                                    <th>데이터길이</th>
                                    <th>소수점</th>
                                    <th>최소값</th>
                                    <th>최대값</th>
                                    <th>설명</th>
                                    <th>사유</th>
                                </tr>
                            </thead>
                            <tbody>
            `;

            $("#exLargeModal .modal-body").append(headerHtml);

            insertedDomainDataList.forEach(function (insertedDomainData, index) {

                const reasonsHtml = insertedDomainData.reason.map(function (reason, reasonIndex) {
                    return `${reason}${reasonIndex !== insertedDomainData.reason.length - 1 ? ' / ' : ''}`;
                }).join('');

                const rowHtml = `
                    <tr>
                        <th scope="row">${index + 1}</th>
                        <input type="hidden" name="stdAreaId" value="${insertedDomainData.stdAreaId}">
                        <td name="keyDomainName">${insertedDomainData.keyDomainName}</td>
                        <td name="domainName">${insertedDomainData.domainName}</td>
                        <td name="domainTypeCode">${insertedDomainData.domainTypeCode}</td>
                        <td name="domainGroupName">${insertedDomainData.domainGroupName ? insertedDomainData.domainGroupName : ''}</td>
                        <td name="dataTypeCode">${insertedDomainData.dataTypeCode}</td>
                        <td name="dataLength">${insertedDomainData.dataLength ? insertedDomainData.dataLength : ''}</td>
                        <td name="dataScale">${insertedDomainData.dataScale ? insertedDomainData.dataScale : ''}</td>
                        <td name="dataMin">${insertedDomainData.dataMin ? insertedDomainData.dataMin : ''}</td>
                        <td name="dataMax">${insertedDomainData.dataMax ? insertedDomainData.dataMax : ''}</td>
                        <td name="domainDescription">${insertedDomainData.domainDescription ? insertedDomainData.domainDescription : ''}</td>
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
            showAlert("도메인 등록이 완료되었습니다.", "success");
            hideLoadingSpinner();
        },
        error: function (error) {
            showAlert("도메인 등록이 실패했습니다.");
            hideLoadingSpinner();
        }
    });
}

document.getElementById('confirmBtn').addEventListener('click', function() {
    window.location.href = '/dms/bulk-domain';
});


