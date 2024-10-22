$(function () {
    $('[data-toggle="tooltip"]').tooltip();
});

document.getElementById("word").addEventListener("change", function () {
    const attributeCheckbox = document.getElementById("attribute");
    const entityCheckbox = document.getElementById("entity");

    if (this.checked) {
        attributeCheckbox.removeAttribute("disabled");
        entityCheckbox.removeAttribute("disabled");
    } else {
        attributeCheckbox.setAttribute("disabled", "true");
        entityCheckbox.setAttribute("disabled", "true");
        attributeCheckbox.checked = false;
        entityCheckbox.checked = false;
    }
});

document.getElementById("domain").addEventListener("change", function () {
    const domainTypeList = document.getElementById("domainTypeList");
    const dataTypeList = document.getElementById("dataTypeList");

    if (this.checked) {
        domainTypeList.removeAttribute("disabled");
        dataTypeList.removeAttribute("disabled");
    } else {
        domainTypeList.setAttribute("disabled", "true");
        dataTypeList.setAttribute("disabled", "true");
        domainTypeList.value = "None";
        dataTypeList.value = "None";
    }
});
const wordCheckbox = document.getElementById("word");
const attributeCheckbox = document.getElementById("attribute");
const entityCheckbox = document.getElementById("entity");
const domainCheckbox = document.getElementById("domain");

const domainTypeSelect = document.getElementById("domainTypeList");
const dataTypeSelect = document.getElementById("dataTypeList");

const termCheckbox = document.getElementById("term");


// 프로젝트 선택 여부 확인
function hasNotSubjArea(subjArea) {
    return subjArea === null || subjArea === undefined || subjArea.trim() === '';
}

// 검색창 enter키 입력해도 검색 진행
const inputOfConditionSearch = document.getElementById('conditionSearch');
inputOfConditionSearch.addEventListener("keydown", function(e){
	if(e.key === 'Enter'){
		document.getElementById("searchLabel").click();
	}
})


document.getElementById("searchLabel").addEventListener("click", function () {
	// 프로젝트 선택하지 않고 '검색' 할 경우 안내창 출력하고 '검색' 불가능
	const subjArea = document.getElementById("selectStandardArea").value.trim();
	if(hasNotSubjArea(subjArea)){
    	showAlert('프로젝트를 선택해야 검색이 가능합니다.');
    	return;
	}
	
    const tableBody = document.querySelector('.table tbody');
    tableBody.innerHTML = '';

    const searchWord = document.getElementById("conditionSearch");
    const conditionState = {
        standardAreaName: selectElement.options[selectElement.selectedIndex].text,
        searchWord: searchWord.value.toUpperCase(),
        word: wordCheckbox.checked,
        attribute: wordCheckbox.checked ? attributeCheckbox.checked : false,
        entity: wordCheckbox.checked ? entityCheckbox.checked : false,
        domain: domainCheckbox.checked,
        domainType: domainCheckbox.checked ? domainTypeSelect.value : "None",
        dataType: domainCheckbox.checked ? dataTypeSelect.value : "Node",
        term: termCheckbox.checked,
    };
    sendCheckedStateToServer(conditionState);
});

function handleAjaxError() {
    showAlert("서버 오류가 발생했습니다. 다시 시도해 주세요.");
}

function confirmDelete(division, dicId) {
    if (division === 'word') {
        openWordDeleteModal(dicId);
    } else if (division === 'domain') {
        openDomainDeleteModal(dicId);
    } else if (division === 'term') {
        openTermDeleteModal(dicId);
    } else {
        console.error('알 수 없는 division:', division);
    }
}

function confirmEdit(division, dicId) {
    if (division === 'word') {
        openWordEditModal(dicId);
    } else if (division === 'domain') {
        openDomainEditModal(dicId);
    } else if (division === 'term') {
        openTermEditModal(dicId);
    } else {
        console.error('알 수 없는 division:', division);
    }
}


function openWordEditModal(dicId) {
    // 모달 내용에 dicId를 표시
    $('#editWord').modal('show');
    getWordInfo(dicId);


}


function handleStandardYn() {
    showAlert("비 표준어는 수정할 수 없습니다");
}

function openTermEditModal(dicId) {
    // 모달 내용에 dicId를 표시
    $('#editTerm').modal('show');
    getTermInfo(dicId);
}

function openDomainEditModal(dicId) {
    // 모달 내용에 domId 표시
    $('#editDomain').modal('show');
    getDomainInfo(dicId);
}


function openWordDeleteModal(dicId) {
    // 모달 내용에 dicId를 표시
    $('#deleteWord').modal('show');
    $('#deleteWord .modal-body').text('정말 삭제하시겠습니까?');

    // 확인 버튼 클릭 시 deleteSingleWordAndTerm 함수 호출
    $('#deleteStdWordModalButton').off('click').on('click', function () {
        deleteSingleWord(dicId);


    });
}

function openTermDeleteModal(dicId) {


    // 모달 내용에 dicId를 표시
    $('#deleteTerm').modal('show');
    $('#deleteTerm .modal-body').text('정말 삭제하시겠습니까?');
    $('#deleteStdTermModalButton').off('click').on('click', function () {
        deleteSingleTerm(dicId);
    });
}


function openDomainDeleteModal(dicId) {
    // 모달 내용에 dicId를 표시
    $('#deleteDomain').modal('show');
    $('#deleteDomain .modal-body').text("정말 삭제하시겠습니까?");

    $('#deleteDomainForStdModal').off('click').on('click', function() {
        deleteSingleDomain(dicId);
    });
}


function findLabelsByLogicalName(logicalName) {
    // logicalName을 기준으로 라벨을 찾는 함수
    const labelsContainers = document.querySelectorAll('.table tbody .word-fields');
    for (const labelsContainer of labelsContainers) {
        const labelsAbove = labelsContainer.querySelectorAll('.word-label');
        const logicalNameAbove = labelsAbove[0].textContent.split(':')[1].trim();
        if (logicalNameAbove === logicalName) {
            return labelsAbove;
        }
    }
    return [];
}

const tableBody = document.querySelector('.table tbody');

function sendCheckedStateToServer(checkedState) {
    showLoadingSpinner()
    
    	sendAjaxRequest(
        "/dms/loginSession",
        null,
        function(data){			
			inserSearchTableRow(data.id, data.authTpCd, checkedState);
		},
		function(){
			handleAjaxError();
		})

    
}

function inserSearchTableRow(id, authTpCd, checkedState) {
	sendAjaxRequest(
        "/dms/standardData/conditionSearch",
        checkedState,
        function (response) {
            if (response && Array.isArray(response)) {
                tableBody.innerHTML = '';
                const maxItems = 100;  // 처음 100개만 렌더링
                let rowNumber = 1;
                let i = 0;
                for (i = 0; i < Math.min(response.length, maxItems); i++) {
                    const dataDTO = response[i];
                    const newRow = document.createElement('tr');
                    newRow.setAttribute('data-bs-toggle', 'collapse');
                    newRow.setAttribute('data-bs-target', `#r${dataDTO.dicId}`);
                    newRow.setAttribute('name', 'row');
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
                        <td data-toggle="tooltip" title="클릭해주세요!">${rowNumber++}</td>
                        <td data-toggle="tooltip" title="클릭해주세요!">${dataDTO.standardClassification || ""} </td>
                        <td data-toggle="tooltip" title="클릭해주세요!"> ${divisionText || ""} </td>
                        <td data-toggle="tooltip" title="클릭해주세요!"> ${dataDTO.logicalName || ""} </td>
                        <td data-toggle="tooltip" title="클릭해주세요!"> ${dataDTO.physicalName || ""} </td>
                        <td data-toggle="tooltip" title="클릭해주세요!"> ${dataDTO.domainGroup || ""} </td>
                        <td data-toggle="tooltip" title="클릭해주세요!"> ${dataDTO.domainType || ""} </td>
                        <td data-toggle="tooltip" title="클릭해주세요!"> ${dataDTO.domainName || ""} </td>
                        <td data-toggle="tooltip" title="클릭해주세요!"> ${dataDTO.logicalDataType || ""} </td>
                        <td data-toggle="tooltip" title="클릭해주세요!"> ${dataDTO.length || ""} </td>
                        <td data-toggle="tooltip" title="클릭해주세요!"> ${dataDTO.isStandard || ""} </td>
                        <td data-toggle="tooltip" title="클릭해주세요!"> ${dataDTO.attributeClassifier || ""} </td>
                        <td data-toggle="tooltip" title="클릭해주세요!"> ${dataDTO.entityClassifier || ""} </td>
                    `;

                    tableBody.appendChild(newRow);
                    // 이 시점에 ajax요청이 한 번 더 되어야함.
                    //만약 dataDTO.division이 "word"이고, isStandard가 "N"과 같다면
                    //sandajaxrequest를 한 번 더 함.
                    //url은 /dms/standardData/checkSynonym으로
                    //ajax요청시 요청할 때 담길 본문은 현재번째 dataDTO
                    //dataDTO와 완전히 똑같은 응답이 올 예정인데. 
                    // 응답이온 dataDTO를 요청할 때 사용했던 dataDTO와 바꿈 ( 갱신 하라는 소리 )

                    const collapsedRow = document.createElement('tr');
                    collapsedRow.classList.add('collapse', 'accordion-collapse');
                    collapsedRow.setAttribute('id', `r${dataDTO.dicId}`);
                    collapsedRow.setAttribute('data-bs-parent', '.table');
                    collapsedRow.innerHTML = `
                        <td colspan="13" class="position-relative">
                         ${dataDTO.division === 'word' ?
                        `<div class="word-fields">    
                <h5 class="mb-4">[단어 상세정보]</h5>

                <div class="row">
	                <div class="mb-3 col-md-6" style="display: none;">
	                  <label class="word-label">논리 단어명</label>
	                  <input class="form-control" id="word-logical-name_${dataDTO.dicId}" type="text" value="${dataDTO.logicalName}" autofocus readonly/>
	                </div>
	                <div class="mb-3 col-md-6" style="display: none;">
	                  <label class="word-label">물리 단어명</label>
	                  <input class="form-control" id="word-physical-name_${dataDTO.dicId}" type="text" value="${dataDTO.physicalName}" autofocus readonly/>
	                </div>
	                <div class="mb-3 col-md-6">
	                  <label class="word-label">영문 풀 네임</label>
	                  <input class="form-control" id="word-full-name_${dataDTO.dicId}" type="text" value="${dataDTO.physicalFullName}" autofocus readonly/>
	                </div>
	                <div class="mb-3 col-md-6">
	                  <label class="word-label">동의어</label>
  	                  <input class="form-control" id="word-synonym_${dataDTO.dicId}" type="text" value="${dataDTO.synonym || ""}" autofocus readonly/>
	                </div>
	                <div class="mb-3 col-md-6" style="display: none;">
	                  <label class="word-label">속성 분류어</label>
	                  <input class="form-control" id="word-attribute-classifier_${dataDTO.dicId}" type="text" value="${dataDTO.attributeClassifier}" autofocus readonly/>
	                </div>
	                <div class="mb-3 col-md-6" style="display: none;">
	                  <label class="word-label">엔터티 분류어</label>
	                  <input class="form-control" id="word-entity-classifier_${dataDTO.dicId}" type="text" value="${dataDTO.entityClassifier}" autofocus readonly/>
	                </div>
	                <div class="mb-3 col-md-6" style="display: none;">
	                  <label class="word-label">표준 여부</label>
	                  <input class="form-control" id="word-standard-yn_${dataDTO.dicId}" type="text" value="${dataDTO.isStandard}" autofocus readonly/>
	                </div>
	                <div class="mb-3">
	                  <label class="word-label">연관 용어</label>
	                  <input class="form-control" id="word-related-term_${dataDTO.dicId}" type="text" value="${dataDTO.relatedTerm}" autofocus readonly/>
	                </div>
	                <div class="mb-3">
	                  <label class="word-label">설명</label>
	                  <input class="form-control" id="word-description_${dataDTO.dicId}" type="text" value="${dataDTO.description}" autofocus readonly/>
	                </div>
                </div>` :
                        (dataDTO.division === 'term' ?
                            `<div class="term-fields">
                <h5 class="mb-4">[용어 상세정보]</h5>
                
                <div class="row">
	                <div class="mb-3 col-md-6" style="display: none;">
	                  <label class="term-label">논리 용어명</label>
	                  <input class="form-control" id="term-logical-name_${dataDTO.dicId}" type="text" value="${dataDTO.logicalName}" autofocus readonly/>
	                </div>
	                <div class="mb-3 col-md-6" style="display: none;">
	                  <label class="term-label">물리 용어명</label>
	                  <input class="form-control" id="term-physical-name_${dataDTO.dicId}" type="text" value="${dataDTO.physicalName}" autofocus readonly/>
	                </div>
	                <div class="mb-3 col-md-6" style="display: none;">
	                  <label class="term-label">도메인명</label>
	                  <input class="form-control" id="term-domain-name_${dataDTO.dicId}" type="text" value="${dataDTO.domainName}" autofocus readonly/>
	                </div>
	                <div class="mb-3 col-md-6" style="display: none;">
	                  <label class="term-label">논리데이터 타입</label>
	                  <input class="form-control" id="term-domain-datatype_${dataDTO.dicId}" type="text" value="${dataDTO.logicalDataType}" autofocus readonly/>
	                </div>
	            	<div class="mb-3">
	                  <label class="term-label">설명</label>
	                  <input class="form-control" id="term-description_${dataDTO.dicId}" type="text" value="${dataDTO.description || ""}" autofocus readonly/>
	                </div>
                </div>` :
                            (dataDTO.division === 'domain' ?
                                `<div class="domain-fields">
                <h5 class="mb-4">[도메인 상세정보]</h5>
                
                <div class="row">
	                <div class="mb-3 col-md-6">
	                  <label class="domain-label">대표 도메인명</label>
	                  <input class="form-control" id="domain-keydomain-name_${dataDTO.dicId}" type="text" value="${dataDTO.keyDomain}" autofocus readonly/>
	                </div>
	                <div class="mb-3 col-md-6" style="display: none;">
	                  <label class="domain-label">도메인명</label>
	                  <input class="form-control" id="domain-domain-name_${dataDTO.dicId}" type="text" value="${dataDTO.domainName}" autofocus readonly/>
	                </div>
	                <div class="mb-3 col-md-6" style="display: none;">
	                  <label class="domain-label">논리데이터 타입</label>
	                  <input class="form-control" id="domain-datatype_${dataDTO.dicId}" type="text" value="${dataDTO.logicalDataType}" autofocus readonly/>
	                </div>
                	<div class="mb-3 col-md-6" style="display: none;">
	                  <label class="domain-label">도메인 그룹</label>
	                  <input class="form-control" id="domain-domain-group_${dataDTO.dicId}" type="text" value="${dataDTO.domainGroup || ""}" autofocus readonly/>
	                </div>
                	<div class="mb-3 col-md-6" style="display: none;">
	                  <label class="domain-label">도메인 유형</label>
	                  <input class="form-control" id="domain-domaintype_${dataDTO.dicId}" type="text" value="${dataDTO.domainType}" autofocus readonly/>
	                </div>
	                <div class="mb-3 col-md-6">
	                  <label class="domain-label">길이</label>
	                  <input class="form-control" id="domain-length_${dataDTO.dicId}" type="text" value="${dataDTO.length || ""} . ${dataDTO.scale || ""}" autofocus readonly/>
	                </div>
	                <div class="mb-3 col-md-6">
	                  <label class="domain-label">데이터 범위</label>
	                  <input class="form-control" id="domain-datamin-datamax_${dataDTO.dicId}" type="text" value="${dataDTO.dataMin || ""} ~ ${dataDTO.dataMax || ""}" autofocus readonly/>
	                </div>
	                <div class="mb-3 col-md-6">
	                  <label class="domain-label">연관 용어</label>
	                  <input class="form-control" id="domain-related-term_${dataDTO.dicId}" type="text" value="${dataDTO.relatedTerm}" autofocus readonly/>
	                </div>
	                <div class="mb-3">
	                  <label class="domain-label">설명</label>
	                  <input class="form-control" id="domain-description_${dataDTO.dicId}" type="text" value="${dataDTO.description || ""}" autofocus readonly/>
	                </div>
                </div>` :
                                ''))}
                        <div class="d-flex justify-content-start" id="updateAndDeleteButtons_${dataDTO.dicId}">
                        </div>
                        </td>
                    `;

                    tableBody.appendChild(collapsedRow);
                    getUpdateAndDeleteButtons(id, authTpCd, dataDTO);

                }
                document.getElementById('saveCollectionToExcelArea').innerHTML = `
                <div class="d-grid gap-2 col-lg-6 mx-auto text-center">
                    <div class="demo-inline-spacing">
                        <button class="btn btn-secondary btn-lg" type="button"
                                id="saveCollectionToExcel">출력
                        </button>
                    </div>
                </div>
                `
                

				
                if (i === 100) {
                    showAlert("최대 100행까지 조회됩니다, 조건을 추가해주세요");
                } else {
                    showAlert("검색이 완료되었습니다.", "info");
                }
            }
            document.getElementById("conditionSearch").value = "";
            hideLoadingSpinner();
        },
        function () {
            handleAjaxError();
            document.getElementById("conditionSearch").value = "";
        }
    );
}

function getUpdateAndDeleteButtons(id, authTpCd, dataDTO){

	const updateAndDeleteButtons = document.getElementById(`updateAndDeleteButtons_${dataDTO.dicId}`);
	if(id === 'admin' || authTpCd === 'W'){
		updateAndDeleteButtons.innerHTML = `
		<button class="btn btn-primary" data-dicId="${dataDTO.dicId}" data-division="${dataDTO.division}" onclick="confirmEdit('${dataDTO.division}', '${dataDTO.dicId}')">수정</button>
	    <button class="btn btn-secondary ms-2" data-dicId="${dataDTO.dicId}" data-division="${dataDTO.division}" onclick="confirmDelete('${dataDTO.division}', '${dataDTO.dicId}')">삭제</button>
		`
	} else {
		updateAndDeleteButtons.innerHTML = '';
	}
}


function extractSelectedRows() {
    const tableRows = document.getElementsByName("row");
    const selectedRows = [];

    tableRows.forEach(row => {
        const columns = row.querySelectorAll("td");

        const rowData = {
            standardClassification : columns[1] !== null ? columns[1].innerText : "",
            division : columns[2] !== null ? columns[2].innerText : "",
            logicalName : columns[3] !== null ? columns[3].innerText : "",
            physicalName : columns[4] !== null ? columns[4].innerText : "",
            domainGroup : columns[5] !== null ? columns[5].innerText : "",
            domainType : columns[6] !== null ? columns[6].innerText : "",
            domainName : columns[7] !== null ? columns[7].innerText : "",
            logicalDataType : columns[8] !== null ? columns[8].innerText : "",
            length : columns[9] !== null ? columns[9].innerText : "",
            isStandard : columns[10] !== null ? columns[10].innerText : "",
            attributeClassifier : columns[11] !== null ? columns[11].innerText : "",
            entityClassifier : columns[12] !== null ? columns[12].innerText : ""
        };

        selectedRows.push(rowData);
    })

    return selectedRows;
}

function logSelectedRows() {
    const selectedRows = extractSelectedRows();
    if (selectedRows.length > 0) {
        sendCollectionData(selectedRows);
    } else {
        showAlert("출력할 항목을 선택해 주세요.");
    }
}

const excelButton = document.getElementById('saveCollectionToExcel');
excelButton.addEventListener("click", () => {
    logSelectedRows();
});

function sendCollectionData(selectedRows) {
    $.ajax({
        type: "POST",
        url: "/dms/collection/download",
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
            downloadLink.download = "collection.xlsx";

            document.body.appendChild(downloadLink);
            downloadLink.click();
            document.body.removeChild(downloadLink);
        },
        error: function (error) {
            console.error(error);
        }
    });
}
