let firstFlag = true;

function getStandardAreaId() {
    return document.getElementById("stdAreaId").value.trim();
}

function getDifferenceCombined() {
    return document.getElementById("differenceCombined").value.trim();

}


function getSearchWord() {
    return document.getElementById("searchWord").value.toUpperCase().trim();
}

function isSearchWordEmpty() {
    return getSearchWord() === "";
}

function getStandardAreaName() {
    return document.getElementById("selectStandardArea").value.trim();
}

function getStandardId() {
    return document.getElementById("selectStandardArea").value.trim();
}


function sendAjaxRequest(url, data, successCallback, errorCallback) {
    $.ajax({
        type: "POST",
        url: url,
        data: JSON.stringify(data),
        contentType: "application/json",
        success: successCallback,
        error: errorCallback,
    });
}


function getWordList() {
    const stdAreaId = getStandardAreaName();
    const searchWord = getSearchWord();

    if (isSearchWordEmpty()) {
        showAlert("검색어를 입력하세요.");
    } else {
        sendAjaxRequest(
            "/dms/single-term/searchWordRest",
            {
                stdAreaId: stdAreaId, searchWord: searchWord},
            function (response) {

                updateWordTable(response.wordList);
                updateInputValues(response.wordList); // 여기서 updateInputValues() 호출
                appendRow(response); // appendRow 함수에 응답 데이터 전달
                handleSelectCheckResponse();

            },
            function () {
                handleAjaxError();
            }
        );
    }
}

function handleSelectCheckResponse() {
    showAlert("검색이 성공적으로 완료되었습니다", "info");

}

// dicLogNm과 dicPhyNm을 합산하고 공백을 제외한 값을 각각의 input 요소에 넣는 함수
let lastCheckedRowIndex = -1; // 최근에 체크된 행의 인덱스를 저장하는 변수
function updateInputValues(wordList) {
    let concatenatedDicLogNm = '';
    let concatenatedDicPhyNm = '';

    // wordList에서 dicGbnCd가 'word'인 경우만 처리
    const filteredWordList = wordList.filter(word => word.dicGbnCd === 'word');

    // 'word'인 경우에만 dicLogNm과 dicPhyNm을 연결
    filteredWordList.forEach((word, rowIndex) => {
        concatenatedDicLogNm += word.dicLogNm + ' ';
        concatenatedDicPhyNm += word.dicPhyNm + ' ';

        // 각 체크박스에 이벤트 리스너 추가
        const checkboxes = document.querySelectorAll('#wordTableBody tr:nth-child(' + (rowIndex + 1) + ') input[type="checkbox"]');
        checkboxes.forEach((checkbox) => {
            checkbox.addEventListener('change', function () {
                if (checkbox.checked) {
                    // Checkbox checked: Store the index of the row
                    lastCheckedRowIndex = rowIndex;
                }
                // 입력 값 및 필드 업데이트
                const inputDicLogNm = document.getElementById('dicLogNm');
                const inputDicPhyNm = document.getElementById('dicPhyNm');

                if (!checkbox.checked) {
                    concatenatedDicLogNm = concatenatedDicLogNm.replace(word.dicLogNm + ' ', '');
                    concatenatedDicPhyNm = concatenatedDicPhyNm.replace(word.dicPhyNm + ' ', '');
                } else {
                    concatenatedDicLogNm += word.dicLogNm + ' ';
                    concatenatedDicPhyNm += word.dicPhyNm + ' ';
                }

                inputDicLogNm.value = concatenatedDicLogNm.trim();
                inputDicPhyNm.value = concatenatedDicPhyNm.trim();
            });
        });
    });

    // 초기 입력 필드에 연결된 값 설정
    const inputDicLogNm = document.getElementById('dicLogNm');
    const inputDicPhyNm = document.getElementById('dicPhyNm');
    inputDicLogNm.value = concatenatedDicLogNm.trim();
    // 용어 검색 후, 단어 사이 '_' 로 연결하여 물리용어명 생성하여 화면에 출력
    inputDicPhyNm.value = concatenatedDicPhyNm.trim().split(' ').join('_');
    
    // dicLogNm 에서 속성분류어를 추출
    const termLogNm = document.getElementById('dicLogNm').value;
	document.getElementById('modalAttributeWord').value = termLogNm.split(' ').reverse()[0];
	document.getElementById('keyDomName').value = termLogNm.split(' ').reverse()[0];
}

function updateWordTable(wordList, differenceWord) {
    const tableBody = document.getElementById('wordTableBody');

    // 기존 테이블의 내용을 지우기
    tableBody.innerHTML = '';

    if (Array.isArray(wordList)) {
        wordList.forEach(function (word, index) {
            if (word.dicGbnCd === 'word') {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${index + 1}</td>
                    <td><input type="checkbox" checked id="termCheckBox"/></td>
                    <td>${word.dicLogNm}</td>
                    <td>${word.dicPhyNm}</td>
                    <td><input type="checkbox" ${word.entClssYn === 'Y' ? 'checked' : ''} disabled/></td>
                    <td><input type="checkbox" ${word.attrClssYn === 'Y' ? 'checked' : ''} disabled/></td>
                    <td id="standardYn_${word.dicId}">${word.standardYn}</td>
                    <td>${word.dicDesc}</td>
                    <td style="display: none;">${word.dicId}</td>
                    <!-- 추가할 데이터 항목들을 여기에 계속해서 추가하세요 -->
                `;
                tableBody.appendChild(row);

                if (firstFlag === true) {
                    if (index === wordList.length - 1 && word.dicGbnCd === 'word') {
                        lastCheckedRowIndex = index; // Update lastCheckedRowIndex with the index of the last row
                        firstFlag = false;
                    } else if (firstFlag === false) {
                        lastCheckedRowIndex = index - 1;
                    }
                }
            }
            updateTerm(wordList);

        });
    } else {
        console.error("Word list is not defined or is not an array.");
        // Handle the case where wordList is not an array or is undefined
        // For example, show an error message or handle it accordingly
    }
}

function updateTerm(wordList) {
    let termName = null; // 용어 이름을 담을 변수

    if (Array.isArray(wordList)) {
        wordList.forEach(function (word) {
            if (word.dicGbnCd === 'term') {
                termName = word.dicLogNm; // 용어 이름 할당
                return; // term이 하나만 조회되면 루프를 종료합니다.
            }
        });
    }

    const equalWordSelectedInput = document.getElementById("equalWordSelected");
    equalWordSelectedInput.value = termName !== null ? termName : '존재하지 않는 용어입니다.'; // 용어 이름을 input 요소에 할당
}

let modalTermWord;

function appendRow(response) {
    const tableBody = document.getElementById('wordTableBody');
    
    if (response.differenceCombined.length > 0) {
		for(i = 0; i < response.differenceCombined.length; i++) {
			const row = document.createElement('tr');

	        const cell1 = document.createElement('td');
	        cell1.textContent = '※'; // Add content or leave empty based on the table structure
	        row.appendChild(cell1);
	
	        const cell2 = document.createElement('td');
	        const checkbox = document.createElement('input');
	        checkbox.type = 'checkbox'; // Creating a checkbox
	        // Set checkbox attributes or classes if needed
	        cell2.appendChild(checkbox);
	        row.appendChild(cell2);
	
	        // Cell 3 content
	        const cell3 = document.createElement('td');
	        cell3.textContent = response.differenceCombined[i].trim(); // Set content based on response
	        row.appendChild(cell3);
	
	        // Empty cells 4, 5, 6 with colspan 3
	        const emptyCell = document.createElement('td');
	        emptyCell.textContent = '존재하지 않는 단어입니다.';
	        emptyCell.setAttribute('colspan', '3'); // 3칸을 합쳐서 표시
	        row.appendChild(emptyCell);
	
	        // Cell 7 - Button
	        const cellForButton = document.createElement('td');
	        cellForButton.style.textAlign = 'left'; // Align button to the left within the cell
	
	        const button = document.createElement('button');
	        button.textContent = '등록';
	        button.classList.add('btn', 'btn-primary');
	
	        // Add event listener to the button to show the modal
	
	        button.addEventListener('click', function () {
	            // Show the modal when the button is clicked
	            modalTermWord = new bootstrap.Modal(document.getElementById('modalTermWord'));
	            modalTermWord.show();
	
	            // Set the value of modalLogNm input to response.differenceCombined[i].trim()
	            const modalLogNmInput = document.getElementById('modalLogNm');
	            modalLogNmInput.value = response.differenceCombined.trim();
	        });
	
	        cellForButton.appendChild(button);
	        row.appendChild(cellForButton);
	
	
	        // Cell 8 - Empty cell
	        const cell8 = document.createElement('td');
	        cell8.setAttribute('colspan', '1'); // Set colspan to 1 for the empty cell
	        row.appendChild(cell8);
	
	        // Add the row to the table body
	        tableBody.appendChild(row);

		}
	}
}


function handleAjaxError() {
    showAlert("서버 오류가 발생했습니다. 다시 시도해 주세요.");
}

// 프로젝트 선택 여부 확인
function hasNotSubjArea(subjArea) {
    return subjArea === null || subjArea === undefined || subjArea.trim() === '';
}

// 용어명 검색창 enter키 입력해도 검색 진행
const inputOfsearchWord = document.getElementById('searchWord');
inputOfsearchWord.addEventListener("keydown", function(e){
	if(e.key === 'Enter'){
		document.getElementById("selectWordButton").click();
	}
})

document.getElementById("selectWordButton").addEventListener("click", function () {
	// 프로젝트를 선택하지 않고 '검색' 할 경우 안내창 출력하고 '검색' 불가능
	const subjArea = document.getElementById("selectStandardArea").value.trim();
	if(hasNotSubjArea(subjArea)){
    	showAlert('프로젝트를 선택해야 검색이 가능합니다.');
    	return;
	}
    getWordList();
});


// ---------------- 인서트 --------------------------
document.getElementById("insertButton").addEventListener("click", function () {
    insertWordAndTerm(); // insertWordAndTerm() 함수 호출
});

function getDicId() {
    return document.getElementById("dicId").value.trim();
}

function getDicLogNm() {
    return document.getElementById("dicLogNm").value.trim();
}

//dicGbnCd

function getDicPhyNm() {
    return document.getElementById("dicPhyNm").value.trim();
}

function getDicDesc() {
    return document.getElementById("dicDesc").value.trim();
}


// 클릭 이벤트 핸들러 안에서 selectedDomainId 값 설정
// 예: selectedDomainId = domain.domId;

function getDomId() {
    return selectedDomainId;
}
function getTermDomName() {
    return document.getElementById("termDomName").value.trim();
}

function isTermDomNameEmpty() {
    return getTermDomName() === "";
}
function isDomIdEmpty() {
    return getDomId() === "";
}

// 용어 구분 선택 여부 validation
function isNotAnyRadioButtonChecked() {
    const entityRadio = document.getElementById('entity');
    const attributeRadio = document.getElementById('attribute');

    if (!entityRadio.checked && !attributeRadio.checked) {
        return true;
    } 
}

// 단어가 비표준 단어인지 확인
function checkNoneStandard() {
    const elements = document.querySelectorAll('[id^="standardYn_"]');
    return Array.from(elements).some(element => element.textContent.trim() === 'N');
}

let wordList = []; // 객체 대신 배열로 초기화


function getLastWordAttrClssValue() {
    const table = document.getElementById('wordTableBody');
    if (lastCheckedRowIndex !== -1) {
        const attrClssValue = table.rows[lastCheckedRowIndex].cells[5].querySelector('input[type="checkbox"]').checked;
        return attrClssValue;
    }
    return false;
}

function getLastWordEntClssValue() {
    const table = document.getElementById('wordTableBody');
    if (lastCheckedRowIndex !== -1) {
        const entClssValue = table.rows[lastCheckedRowIndex].cells[4].querySelector('input[type="checkbox"]').checked;
        return entClssValue;
    }
    return false;
}

function getCheckedDicIds() {
    const table = document.getElementById('wordTableBody');
    const splitWordsForTerm = getDicLogNm();
    const dicLogNmForTerm = splitWordsForTerm.split(" ").map(word => word.trim());

    const checkedDicIds = [];

    for (let i = 0; i < dicLogNmForTerm.length; i++) {
        const wordToFind = dicLogNmForTerm[i];

        for (let j = 0; j < table.rows.length; j++) {
            const tablesDicLogNm = table.rows[j].cells[2].innerHTML.trim();

            if (tablesDicLogNm === wordToFind) {
                const dicIdCell = table.rows[j].cells[8];
                const dicId = dicIdCell.innerHTML.trim();
                checkedDicIds.push({id: dicId, orderNo: i + 1});
                break;
            }
        }
    }

    return checkedDicIds;
}


function insertWordAndTerm() {

    const stdAreaId = getStandardAreaName();
    const dicLogNm = getDicLogNm();
    const dicPhyNm = getDicPhyNm();
    const dicDesc = getDicDesc();
    const domId = getDomId();
    
    if (isNotAnyRadioButtonChecked()){
		showAlert("용어 구분을 선택하세요.");
		return;
	}

    if (isTermDomNameEmpty()) {
        showAlert("도메인을 선택하세요.");
    } else {
        const isNoneStandard = checkNoneStandard();

        if (isNoneStandard) {
			showAlert("표준여부가 'N'인 단어는 용어로 등록할 수 없습니다.");
			return;
		}
        checkSelectedRadio(stdAreaId, dicLogNm, dicPhyNm, dicDesc, domId);
    }
}

function checkSelectedRadio(stdAreaId, dicLogNm, dicPhyNm, dicDesc, domId) {
    const termDistinction = document.getElementById('termDistinction');
    const radios = termDistinction.querySelectorAll('input[type="radio"]');
    let selectedValue = '';

    radios.forEach(radio => {
        if (radio.checked) {
            selectedValue = radio.id;
        }
    });

    if (selectedValue === 'entity') {
        const attrClssValue = getLastWordEntClssValue();
        
        if (!attrClssValue) {
            showAlert("마지막 단어는 엔티티 분류어가 선택되어야 합니다.");
        } else {
            const checkedDicIds = getCheckedDicIds();

            const termDTOList = checkedDicIds.map((checkedItem, index) => {
                return {
                    dicId: checkedItem.id,
                    orderNo: index + 1
                };
            });

            const termWordDTO = {
                wordDTO: {
                    stdAreaId: stdAreaId,
                    dicLogNm: dicLogNm,
                    dicPhyNm: dicPhyNm,
                    dicDesc: dicDesc,
                    entClssYn: 'Y',
                    domId: domId,

                },
                termDTOList: termDTOList,
            };
            executeInsert(termWordDTO);
        }        
    } else if (selectedValue === 'attribute') {
        const attrClssValue = getLastWordAttrClssValue();
        
        if (!attrClssValue) {
            showAlert("마지막 단어는 속성 분류어가 선택되어야 합니다.");
        } else {
            const checkedDicIds = getCheckedDicIds();

            const termDTOList = checkedDicIds.map((checkedItem, index) => {
                return {
                    dicId: checkedItem.id,
                    orderNo: index + 1
                };
            });

            const termWordDTO = {
                wordDTO: {
                    stdAreaId: stdAreaId,
                    dicLogNm: dicLogNm,
                    dicPhyNm: dicPhyNm,
                    dicDesc: dicDesc,
                    attrClssYn: 'Y',
                    domId: domId,

                },
                termDTOList: termDTOList,
            };
            executeInsert(termWordDTO);
        }
    }
}

function executeInsert(termWordDTO){
    sendAjaxRequest(
    "/dms/single-term/insertRest",
    termWordDTO,
    function (response) {
        handleInsertCheckResponse();
    },
    function () {
        handleAjaxError();
    }
	);
}

function handleInsertCheckResponse() {
    clearTermInputFields();
    showAlert("용어 등록이 성공적으로 완료되었습니다.", "info");

}


function disableInsertButton() {
    document.getElementById("insertButton").setAttribute("disabled", "true");
}

function enableInsertButton() {
    document.getElementById("insertButton").removeAttribute("disabled");
}

function handleTermDuplicateCheckResponse(response) {
    if (response.isDuplicate) {
        showAlert("중복된 용어입니다. 다른 용어를 입력하세요.");
        disableInsertButton();
    } else {
        showAlert("사용 가능한 용어입니다", "success");
        enableConfirmButton();
    }
}


function clearTermInputFields() {
    document.getElementById("selectWordButton").value = "";
    document.getElementById("equalWordSelected").value = "";
    document.getElementById("dicLogNm").value = "";
    document.getElementById("dicPhyNm").value = "";
    document.getElementById("dicDesc").value = "";
    document.getElementById("termDomName").value = "";

    // 체크박스 선택 해제
    const checkboxes = document.querySelectorAll('#wordTableBody input[type="checkbox"]');
    checkboxes.forEach((checkbox) => {
        checkbox.checked = false;
    });

    // wordTableBody 초기화
    const tableBody = document.getElementById('wordTableBody');
    tableBody.innerHTML = '';
}

