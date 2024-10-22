function getSelectedCheckboxes() {
    const checkboxes = document.querySelectorAll('.form-check-input');
    const selectedCheckboxes = Array.from(checkboxes).filter(checkbox => checkbox.checked);
    const selectedValues = selectedCheckboxes.map(checkbox => checkbox.id);
    return selectedValues;
}

// 프로젝트 선택 여부 확인
function hasNotSubjArea(subjArea) {
    return subjArea === null || subjArea === undefined || subjArea.trim() === '';
}

document.getElementById("searchCollectionButton").addEventListener("click", function(event) {
	// 프로젝트를 선택하지 않고 '조회' 할 경우 안내창 출력하고 '조회' 불가능
	const stdAreaName = document.getElementById("selectStandardArea").value;
	if(hasNotSubjArea(stdAreaName)){
    	showAlert('프로젝트를 선택해야 조회가 가능합니다.');
    	return;
	}
    showLoadingSpinner();
    
    const selectedCheckboxes = getSelectedCheckboxes();

    const standardDataSearchDTO = {
        standardAreaName: stdAreaName,
        word: selectedCheckboxes.includes("word"),
        domain: selectedCheckboxes.includes("domain"),
        term: selectedCheckboxes.includes("term")
    };

	console.log("term값???" + standardDataSearchDTO.term);
    $.ajax({
        url: "/dms/collectionBook/display",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(standardDataSearchDTO),
        success: function(standardDataDTOList) {
            console.log("탔나?")
            console.log(standardDataDTOList)
            const tableBody = document.querySelector('.table tbody');
            tableBody.innerHTML = '';
            let rowNumber = 1;
            standardDataDTOList.forEach(dataDTO => {
                const newRow = document.createElement('tr');
                let divisionText;
                switch (dataDTO.division) {
                    case 'word':
                        divisionText = '단어';
                        break;
                    case 'term':
                        divisionText = '용어';
                        break;
                    case 'domain':
                        divisionText = '도메인';
                        break;
                    default:
                        divisionText = '';
                }
                newRow.innerHTML = `
                        <td>${rowNumber++}</td>
                        <td><input class="form-check-input" type="checkbox" name="availableRow" value="Y" checked="checked"></td>
                        <td name="standardClassification">${dataDTO.standardClassification || ""}</td>
                        <td name="division">${divisionText || ""}</td>
                        <td name="logicalName">${dataDTO.logicalName || ""}</td>
                        <td name="physicalName">${dataDTO.physicalName || ""}</td>
                        <td name="domainGroup">${dataDTO.domainGroup || ""}</td>
                        <td name="domainType">${dataDTO.domainType || ""}</td>
                        <td name="domainName">${dataDTO.domainName || ""}</td>
                        <td name="logicalDataType">${dataDTO.logicalDataType || ""}</td>
                        <td name="length">${dataDTO.length || ""}</td>
                        <td name="isStandard">${dataDTO.isStandard || ""}</td>
                        <td name="attributeClassifier">${dataDTO.attributeClassifier || ""}</td>
                        <td name="entityClassifier">${dataDTO.entityClassifier || ""}</td>
                    `;

                tableBody.appendChild(newRow);
            })
            hideLoadingSpinner();
        },
        error: function(error) {
            hideLoadingSpinner();
            console.error(error);
        }
    });
});

function selectAvailableAll(selectAvailableAll) {
    const avaCheckboxes = document.getElementsByName('availableRow');
    avaCheckboxes.forEach((checkbox) => {
        checkbox.checked = selectAvailableAll.checked;
    });
}

document.addEventListener("DOMContentLoaded", () => {
    const saveAvaRowToExcelButton = document.getElementById('saveCollectionToExcel');

    function extractSelectedRows(checkboxName) {
        const checkboxes = document.getElementsByName(checkboxName);
        const selectedRows = [];

        for (let i = 1; i < checkboxes.length; i++) {
            if (checkboxes[i].checked) {
                const dataFields = [
                    "standardClassification",
                    "division",
                    "logicalName",
                    "physicalName",
                    "domainGroup",
                    "domainType",
                    "domainName",
                    "logicalDataType",
                    "length",
                    "isStandard",
                    "attributeClassifier",
                    "entityClassifier"
                ];

                const row = {};

                dataFields.forEach(field => {
                    let fieldValue = checkboxes[i].closest("tr").querySelector(`[name='${field}']`);
                    if (fieldValue !== null) {
                        row[field] = fieldValue.innerText
                    }
                });

                selectedRows.push(row);
            }
        }

        return selectedRows;
    }

    function logSelectedRows(checkboxName) {
        const selectedRows = extractSelectedRows(checkboxName);
        if (selectedRows.length > 0) {
            sendCollectionBookData(selectedRows);
        } else {
            showAlert("출력할 항목을 선택해 주세요.");
        }
    }

    saveAvaRowToExcelButton.addEventListener("click", () => {
        logSelectedRows("availableRow");
    });

});

function sendCollectionBookData(selectedRows) {
    $.ajax({
        type: "POST",
        url: "/dms/collectionBook/download",
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
            downloadLink.download = "collectionBook.xlsx";

            document.body.appendChild(downloadLink);
            downloadLink.click();
            document.body.removeChild(downloadLink);
        },
        error: function (error) {
            console.error(error);
        }
    });
}

