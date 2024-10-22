function getStandardAreaName() {
    return document.getElementById("selectStandardArea").value.trim();
}

function getModalDomainName() {
    return document.getElementById("modalDomainName").value.toUpperCase().trim();
}

let termDomainSearchModal

const domainSearchButton = document.getElementById('domainSearchButton');
domainSearchButton.addEventListener('click', function () {
    termDomainSearchModal = new bootstrap.Modal(document.getElementById('termDomainSearchModal'));
    termDomainSearchModal.show();
    
    // 도메인 조회 버튼 클릭 시, 속성분류어와 대표도메인명이 같은 도메인을 즉시 조회
    const attributeWord = document.getElementById('modalAttributeWord').value
    getDomainList(attributeWord);
});

// 검색창 enter키 입력해도 검색 진행
const modalDomainSearchLabel = document.getElementById('modalDomainSearch');
document.getElementById("modalDomainName").addEventListener("keydown", function (e) {
    if (e.key === 'Enter') {
        modalDomainSearchLabel.click();
    }
});

modalDomainSearchLabel.addEventListener('click', function () {
	const attributeWord = document.getElementById('modalAttributeWord').value
    getDomainList(attributeWord);
});


// 기존의 getDomainList() 함수 정의
function getDomainList(attributeWord) {
    const stdAreaName = getStandardAreaName();
    const domainName = getModalDomainName();

    sendAjaxRequest(
        "/dms/single-domain/domainSearchRest",
        {
			keyDomName: attributeWord,
            stdAreaName: stdAreaName,
            domainName: domainName,
        },
        function (response) {
			if(response.domainList.length === 0) {
				showAlert('조회되는 도메인명이 없습니다. 도메인을 추가해 주세요.');
				return;
			}
            generateDomainTable(response.domainList);

        },
        function () {
            handleAjaxError();
        }
    );
}
let selectedDomainId; // 클릭한 도메인의 ID를 저장하는 변수
function generateDomainTable(domainList) {
    const domainTableBody = document.getElementById('modalDomainTableBody');

    // 기존 테이블의 내용을 지우기
    domainTableBody.innerHTML = '';

    if (Array.isArray(domainList)) {
        domainList.forEach(function (domain, index) {
            const domainRow = document.createElement('tr');
            domainRow.innerHTML = `
                <td>${index + 1}</td>
                <td>${domain.keyDomName}</td>
                <td>${domain.domName}</td>
                <td>${domain.domTypeCode}</td>
                <td>${domain.dataTypeCode}</td>
                <td>${domain.dataLen ? domain.dataLen : ''}</td>
                <td>${domain.dataScale ? domain.dataScale : ''}</td>
                <td>${domain.domGroupName ? domain.domGroupName : ''}</td>
                <!-- Add any additional data items here -->
            `;
            domainRow.addEventListener('click', function() {
                // 클릭한 행의 domain.keyDomName 값을 가져와 termDomName input에 설정
                const termDomNameInput = document.getElementById('termDomName');
                termDomNameInput.value = domain.domName;
                selectedDomainId = domain.domId; // Add this line


                // 버튼 클릭 이벤트 호출
                confirmDomainNameButton.click();

                // 모달 숨기기
                const domainSearchModal = document.getElementById('termDomainSearchModal'); // 여기에 모달의 ID를 지정해야 합니다.
                const domSearchModal = bootstrap.Modal.getInstance(domainSearchModal);
                domSearchModal.hide();

                // domId 값과 함께 getDomId 함수 호출
                selectedDomainId = getDomId(domain.domId);

            });
            domainTableBody.appendChild(domainRow);
        });
    }
}

const confirmDomainNameButton = document.getElementById('confirmDomainNameButton');
confirmDomainNameButton.addEventListener('click', function() {
    const termDomNameInput = document.getElementById('termDomName');
    const selectedValue = termDomNameInput.value;
    // 이 값(selectedValue)을 원하는 대상에게 전달하거나 활용할 수 있습니다.
});



