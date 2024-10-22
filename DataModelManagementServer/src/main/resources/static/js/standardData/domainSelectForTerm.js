function getStandardAreaName() {
    return document.getElementById("selectStandardArea").value.trim();
}

function getStdTermModalDomainName() {
    return document.getElementById("stdModalDomainName").value.toUpperCase().trim();
}

document.getElementById('modalTermDomSearch').addEventListener('click', function () {
    openStdTermSelectDomainModal();
});

function openStdTermSelectDomainModal() {
    // 모달 내용에 dicId를 표시
    $('#stdSelectDomainListModal').modal('show');
    const attributeWord = document.getElementById('stdTermModalLogNm').value.split(' ').reverse()[0];
    getStdTermDomainSearchModal(attributeWord);
}

// 검색창 enter키 입력해도 검색 진행
const inputOfstdModalDomainName = document.getElementById('stdModalDomainName');
inputOfstdModalDomainName.addEventListener("keydown", function(e){
	if(e.key === 'Enter'){
		document.getElementById('modalTermDomainSearchLabel').click();
	}
})

document.getElementById('modalTermDomainSearchLabel').addEventListener('click', function() {
    const attributeWord = document.getElementById('stdTermModalLogNm').value.split(' ').reverse()[0];
    getStdTermDomainSearchModal(attributeWord);
});

// 기존의 getDomainList() 함수 정의
function getStdTermDomainSearchModal(attributeWord) {
    const stdAreaName = getStandardAreaName();
    const domainName = getStdTermModalDomainName();

    sendAjaxRequest(
        "/dms/single-domain/domainSearchRest",
        {
			keyDomName: attributeWord,
            stdAreaName: stdAreaName,
            domainName: domainName,
        },
        function (response) {
            generateStdTermDomainTable(response.domainList);
            handleStdTermDomainGetListSuccess();

        },
        function () {
            handleAjaxError();
        }
    );
}

function handleStdTermDomainGetListSuccess() {
    showAlert("도메인 검색이 완료 되었습니다.", "success");
}

function handleStdTermDomainSelectSuceess() {
    showAlert("도메인 선택이 완료 되었습니다.", "success");
}
function generateStdTermDomainTable(domainList) {
    const domainTableBody = document.getElementById('stdModalTermDomainTableBody');

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
                const stdTermDomIdInput = document.getElementById('stdTermModalDomId');
                stdTermDomIdInput.value = domain.domId;

                const stdTermDomNameInput = document.getElementById('stdTermModalDomName');
                stdTermDomNameInput.value = domain.domName;

                const stdTermDomTypeInput = document.getElementById('stdTermModalDomType');
                stdTermDomTypeInput.value = domain.domTypeCode;
                
                const stdTermDomDataTypeInput = document.getElementById('stdTermModalDomDataType');
                stdTermDomDataTypeInput.value = domain.dataTypeCode;

                // 버튼 클릭 이벤트 호출
                confirmDomainNameButton.click();
                // 모달 숨기기
                const domainSearchModal = document.getElementById('stdSelectDomainListModal'); // 여기에 모달의 ID를 지정해야 합니다.
                const stdSelectDomainListModal = bootstrap.Modal.getInstance(domainSearchModal);
                stdSelectDomainListModal.hide();
                handleStdTermDomainSelectSuceess();

                // domId 값과 함께 getDomId 함수 호출

            });
            domainTableBody.appendChild(domainRow);
        });
    }
}

// 버튼 숨기기


const confirmDomainNameButton = document.getElementById('confirmDomainNameButton');

confirmDomainNameButton.style.display = 'none';




