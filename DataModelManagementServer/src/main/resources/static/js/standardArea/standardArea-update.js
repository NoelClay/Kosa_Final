let row;
let updateModal;

function handleEditButtonClick(button) {
    // 부모 <tr>을 찾음
    row = button.closest('tr');

    // 두 번째 <td>의 텍스트(분류체계명) 가져오기
    const category = row.querySelector('td:nth-child(1)').textContent;

    // 세 번째 <td>의 텍스트(설명) 가져오기
    const description = row.querySelector('td:nth-child(2)').textContent;

    //모달 속 내용 채우기
    document.getElementById('modalCenterTitle').textContent = '수정하기';
    document.getElementById('modalCategory').value = category;
    document.getElementById('modalDescription').value = description;
    // 모달 띄우기 (가운데 정렬)
    updateModal = new bootstrap.Modal(document.getElementById('modalCenter'));
    updateModal.show();
}

function getStandardAreaName() {
    return document.getElementById("modalCategory").value.trim();
}

function getStandardAreaDescription() {
    return document.getElementById("modalDescription").value.trim();
}

function isStandardAreaNameEmpty() {
    return getStandardAreaName() === "";
}

function disableConfirmButton() {
    document.getElementById("confirmButton").setAttribute("disabled", "true");
}

// Confirm 버튼 활성화 함수
function enableConfirmButton() {
    document.getElementById("confirmButton").removeAttribute("disabled");
}

function handleAjaxError() {
    showAlert("서버 오류가 발생했습니다. 다시 시도해 주세요.");
    disableConfirmButton();
}

document.getElementById("modalCategory").addEventListener("input", function () {
    disableConfirmButton();
});

function handleDuplicateCheckResponse(response) {
    if (response.isDuplicate) {
        showAlert("중복된 프로젝트명입니다. 다른 이름을 입력하세요.");
        disableConfirmButton();
    } else {
        showAlert("프로젝트명 사용 가능합니다.", "success");
        enableConfirmButton();
    }
}
function containsSpecialCharacters() {
    const standardAreaName = getStandardAreaName();
    const specialCharacters = /[!@#$%^&*(),.?":{}|<>]/; // 필요에 따라 특수 문자를 추가 또는 수정하세요.

    return specialCharacters.test(standardAreaName);
}
function clearInputFields() {
    getStandardAreaName().value = "";
    getStandardAreaDescription().value = "";
    disableConfirmButton();
}

document.getElementById("validationCheckLabel").addEventListener("click", function () {
    showLoadingSpinner();
    const standardAreaName = getStandardAreaName();
    if (isStandardAreaNameEmpty()) {
        showAlert("프로젝트명을 입력하세요.");
        disableConfirmButton();
        hideLoadingSpinner();
    } else if (containsSpecialCharacters()) {
        showAlert("프로젝트명에는 특수 문자를 포함할 수 없습니다.");
        disableConfirmButton();
        hideLoadingSpinner();

        return false;
    } else {
        sendAjaxRequest(
            "/dms/standardArea/checkDuplicate",
            {standardAreaName: standardAreaName},
            function (response) {
                handleDuplicateCheckResponse(response);
                hideLoadingSpinner();
            },
            function () {
                handleAjaxError();
                hideLoadingSpinner();
            }
        );
    }
    hideLoadingSpinner();
})
document.getElementById("confirmButton").addEventListener("click", function () {
    confirmButtonClick();
});

function confirmButtonClick() {
    showLoadingSpinner();

    const originStandardAreaName = row.querySelector('td:nth-child(1)').textContent;

    const standardAreaName = getStandardAreaName();
    const standardAreaDescription = getStandardAreaDescription();

    sendAjaxRequest(
        "/dms/standardArea/update",
        {
            stdAreaNm: standardAreaName,
            stdAreaDesc: standardAreaDescription,
            message: originStandardAreaName,
        },
        function (response) {
            handleConfirmSuccess(response);
            hideLoadingSpinner();
        },
        function () {
            handleAjaxError();
            hideLoadingSpinner();
        }
    );
}

function handleConfirmSuccess(response) {
    showLoadingSpinner();
    if (response.isDuplicate) {
        // 이미 렌더링된 select 엘리먼트를 찾아옴
        const selectElement = document.getElementById("selectStandardArea");

        const changedName = row.querySelector('td:nth-child(1)');//원본
        const changedDesc = row.querySelector('td:nth-child(2)');

        // 기존의 옵션을 찾음
        const existingOption = selectElement.querySelector(`option[value="${changedName.textContent}"]`);

        if (existingOption) {
            // 기존의 옵션이 존재하면 업데이트
            existingOption.value = getStandardAreaName();
            existingOption.text = getStandardAreaName();
        }

        changedName.textContent = getStandardAreaName();
        changedDesc.textContent = getStandardAreaDescription();

        clearInputFields();
        disableConfirmButton();
        showAlert("프로젝트 수정이 완료되었습니다.", "success");
        updateModal.hide();
        hideLoadingSpinner();
    } else {
        disableConfirmButton();
        showAlert("서버 오류가 발생했습니다. 다시 시도해 주세요.");
        hideLoadingSpinner();
    }
    hideLoadingSpinner();
}
