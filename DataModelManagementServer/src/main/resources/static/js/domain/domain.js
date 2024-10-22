function selectAll(selectAll) {
    const checkboxes = document.getElementsByName('domainRow');

    checkboxes.forEach((checkbox) => {
        checkbox.checked = selectAll.checked;
    })
}

let selectedRows = [];
document.addEventListener("DOMContentLoaded", () => {
    const saveButton = document.getElementById("saveToExcel");

    saveButton.addEventListener("click", () => {
        const checkboxes = document.getElementsByName("domainRow");
        const selectedRows = [];

        for (let i = 1; i < checkboxes.length; i++) {
            if (checkboxes[i].checked) {
                const dataFields = [
                    "keyDomainName",
                    "domainName",
                    "domainDescription",
                    "domainTypeCode",
                    "domainGroupId",
                    "dataTypeCode",
                    "dataLength",
                    "dataScale",
                    "dataMin",
                    "dataMax",
                    "reason"
                ];

                const row = {
                    domainId: "test",
                    stdAreaId: "test",
                };

                dataFields.forEach(field => {
                    row[field] = document.getElementsByName(field)[i - 1].innerText;
                });

                row.reason = [row.reason];

                selectedRows.push(row);
            }
        }

        if (selectedRows.length > 0) {
            sendBulkDomainData(selectedRows);
        } else {
            console.log("No rows selected."); // 여기에 알림창 넣어야 할 듯?
        }
    });
});

function sendBulkDomainData(selectedRows) {
    $.ajax({
        type: "POST",
        url: "/dms/bulk-domain/download",
        contentType: "application/json",
        data: JSON.stringify(selectedRows),
        responseType: 'arraybuffer', // 이 부분을 추가
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
            downloadLink.download = "excelFileName.xlsx";

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
    const insertButton = document.getElementById("insertBulkDomain");

    insertButton.addEventListener("click", () => {
        const checkboxes = document.getElementsByName("domainRow");
        const selectedRows = [];

        for (let i = 1; i < checkboxes.length; i++) {
            if (checkboxes[i].checked && checkboxes[i].value === 'Y') {
                const insertFields = [
                    "domainId",
                    "stdAreaId",
                    "keyDomainName",
                    "domainName",
                    "domainDescription",
                    "domainTypeCode",
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
                    const elements = document.getElementsByName(field);
                    if (elements.length > 0) {
                        values[field] = (field === "domainId" || field === "stdAreaId") ? elements[i - 1].value : elements[i - 1].innerText;
                    }
                });

                values.reason = [values.reason];

                selectedRows.push(values);
            }
        }

        if (selectedRows.length > 0) {
            insertBulkDomainData(selectedRows);
        } else {
            console.log("No rows selected."); // 여기에 알림창 넣어야 할 듯?
        }
    });
});

function insertBulkDomainData(selectedRows) {
    $.ajax({
        type: "POST",
        url: "/dms/bulk-domain/insert",
        contentType: "application/json",
        data: JSON.stringify(selectedRows),
        success: function (response) {
        },
        error: function (error) {
            console.error(error);
        }
    });
}