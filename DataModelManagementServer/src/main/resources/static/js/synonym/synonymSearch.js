// 라벨 클릭 이벤트 리스너 추가
document.getElementById("searchLabel").addEventListener("click", function () {
	// 프로젝트 선택하지 않고 '검색' 할 경우 안내창 출력하고 '검색' 불가능
	const subjArea = document.getElementById("selectStandardArea").value.trim();
	if(hasNotSubjArea(subjArea)){
    	showAlert('프로젝트를 선택해야 검색이 가능합니다.');
    	return;
	}
    searchSynonymList();
});

// 프로젝트 선택 여부 확인
function hasNotSubjArea(subjArea) {
    return subjArea === null || subjArea === undefined || subjArea.trim() === '';
}

document.getElementById("synonymSearch").addEventListener("keypress", function (e) {
    if (e.key === 'Enter') { // 엔터키 눌렀을 때
    	// 프로젝트 선택하지 않고 '검색' 할 경우 안내창 출력하고 '검색' 불가능
		const subjArea = document.getElementById("selectStandardArea").value.trim();
		if(hasNotSubjArea(subjArea)){
	    	showAlert('프로젝트를 선택해야 검색이 가능합니다.');
	    	return;
		}
        searchSynonymList();
    }
});

function searchSynonymList() {
    // 검색 기능을 이곳에 구현
    const searchWord = document.getElementById("synonymSearch").value.toUpperCase();
    const stdAreaId = document.getElementById("selectStandardArea");
    const conditionState = {
        dicLogNm: searchWord //무조건 object,string타입
        ,stdAreaId: stdAreaId.value
    };
    sendCheckedStateToServer(conditionState);
}

function handleAjaxError() {
    showAlert("서버 오류가 발생했습니다. 다시 시도해 주세요.");
}

function toggleCollapse() {
    const tableRows = document.querySelectorAll('.table tbody tr');

    tableRows.forEach(function(row) {
        row.addEventListener('click', function(event) {
            const dicId = row.getAttribute('data-bs-target');
            if(dicId) {
                const childTrTags = document.querySelectorAll(`tbody tr[data-bs-parent="${dicId}"]`);
                childTrTags.forEach(function(childRow) {
                    if(childRow.classList.contains('show')) {
                        childRow.classList.remove('show');
                    } else {
                        childRow.classList.add('show');
                    }
                });
            }
        });
    });
}

function sendCheckedStateToServer(conditionState) {
    showLoadingSpinner();
    sendAjaxRequest(
        "/dms/synonym/searchList",
        conditionState,
        function (response) {
			const stdAreaName = document.getElementById("selectStandardArea").value;
            if (response && Array.isArray(response)) {
                response.forEach(function (dataDTO) {
                    const tableBody = document.querySelector('.table tbody');
                    tableBody.innerHTML = '';
                    let rowNumber = 1;
                    response.forEach(function (dataDTO) {
                        const newRow = document.createElement('tr');
                        const arrowTag = document.createElement('td');
                        if(dataDTO.standardYn === '0') {
                            arrowTag.innerHTML = `+`;
                            arrowTag.setAttribute('font-size', '20px');
                            newRow.setAttribute('aria-expanded', 'false');
                            newRow.setAttribute('data-bs-toggle', 'collapse');
                            newRow.setAttribute('data-bs-target', `r${dataDTO.dicId}`);
                        }else {
                            arrowTag.innerHTML = `  ↳`;
                            arrowTag.setAttribute('font-size', '15px');
                            newRow.classList.add('collapse', 'accordion-collapse');
                            newRow.setAttribute('data-bs-parent', `r${dataDTO.altDicId}`);
                            newRow.style.backgroundColor = '#F5F5F5';
                        }
                        newRow.innerHTML = `
                             <td>${rowNumber++}</td>
                             <td>${stdAreaName}</td>
                             <td>${dataDTO.dicLogNm || ""}</td>
                             <td>${dataDTO.dicPhyNm || ""}</td>
                             <td>${dataDTO.standardYn === '0' ? 'Y' : 'N'}</td>
                             <td>${dataDTO.entClssYn || ""}</td>
                             <td>${dataDTO.attrClssYn || ""}</td>
                             <td>${dataDTO.dicDesc || ""}</td>`;
                        newRow.insertBefore(arrowTag, newRow.firstChild); // newRow 맨앞에 arrow tag 추가
                        tableBody.appendChild(newRow);
                    });
                    toggleCollapse();
                });
            }
            showAlert("검색이 완료되었습니다.", "info");
            hideLoadingSpinner();
        },
        function () {
            handleAjaxError();
            document.getElementById("synonymSearch").value = "";
            hideLoadingSpinner();
        }
    );
}

