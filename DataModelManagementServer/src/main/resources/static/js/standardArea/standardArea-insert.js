// 중복 확인 버튼 클릭 이벤트 핸들러
document.getElementById("validationCheckLabel").addEventListener("click", function () {
    checkDuplicate();
});

// Confirm 버튼 클릭 이벤트 핸들러
document.getElementById("confirmButton").addEventListener("click", function () {
    confirmButtonClick();
});

// 표준 분류 체계명 입력값 가져오기
function getStandardAreaName() {
    return document.getElementById("stdAreaNm").value.trim();
}

// 표준 분류 체계명이 비어 있는지 확인
function isStandardAreaNameEmpty() {
    return getStandardAreaName() === "";
}

// 표준 분류 체계명에 특수 문자가 포함되어 있는지 확인
function containsSpecialCharacters() {
    const standardAreaName = getStandardAreaName();
    const specialCharacters = /[!@#$%^&*(),.?":{}|<>]/; // 필요에 따라 특수 문자를 추가 또는 수정하세요.

    return specialCharacters.test(standardAreaName);
}

// 중복 확인 수행 함수
function checkDuplicate() {
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
}

// 중복 확인 응답 처리 함수
function handleDuplicateCheckResponse(response) {
    if (response.isDuplicate) {
        showAlert("중복된 프로젝트명입니다. 다른 이름을 입력하세요.");
        disableConfirmButton();
    } else {
        showAlert("프로젝트명 사용 가능합니다.", "success");
        enableConfirmButton();
    }
}

// Confirm 버튼 클릭 처리 함수
function confirmButtonClick() {
    showLoadingSpinner();
    const standardAreaName = getStandardAreaName();
    const standardAreaDescription = document.getElementById("stdAreaDesc").value;

    sendAjaxRequest(
        "/dms/standardArea/insert",
        {
            stdAreaNm: standardAreaName,
            stdAreaDesc: standardAreaDescription,
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

// Confirm 성공 처리 함수
function handleConfirmSuccess(response) {
    showLoadingSpinner();
    if (response.isDuplicate) {
        // 이미 렌더링된 select 엘리먼트를 찾아옴
        const selectElement = document.getElementById("selectStandardArea");

        // 새로운 옵션 생성
        const newOption = document.createElement("option");
        newOption.value = getStandardAreaName();
        newOption.text = getStandardAreaName();

        // 생성된 옵션을 select 엘리먼트에 추가
        selectElement.add(newOption);
        clearInputFields();
        disableConfirmButton();
        showAlert("프로젝트 등록이 완료되었습니다.", "success");
    } else {
        disableConfirmButton();
        showAlert("서버 오류가 발생했습니다. 다시 시도해 주세요.");
    }
    hideLoadingSpinner();
}

// AJAX 오류 처리 함수
function handleAjaxError() {
    showAlert("서버 오류가 발생했습니다. 다시 시도해 주세요.");
    disableConfirmButton();
}

// Confirm 버튼 비활성화 함수
function disableConfirmButton() {
    document.getElementById("confirmButton").setAttribute("disabled", "true");
}

// Confirm 버튼 활성화 함수
function enableConfirmButton() {
    document.getElementById("confirmButton").removeAttribute("disabled");
}

// 입력 필드 초기화 함수
function clearInputFields() {
    document.getElementById("stdAreaNm").value = "";
    document.getElementById("stdAreaDesc").value = "";
    disableConfirmButton();
}

// 표준 분류 체계명 입력 이벤트 핸들러
document.getElementById("stdAreaNm").addEventListener("input", function () {
    disableConfirmButton();
});

