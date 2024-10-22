document.getElementById("checkDuplicateWordButton").addEventListener("click", function () {
    checkDuplicateWord();
});
document.getElementById("confirmButton").addEventListener("click", function () {
    confirmButtonClick();
});

function getStandardAreaName() {
    return document.getElementById("selectStandardArea").value.trim();
}


function getDicLogNm() {
    return document.getElementById("dicLogNm").value.trim().toUpperCase();
}

function getDicPhyNm() {
    return document.getElementById("dicPhyNm").value.trim().toUpperCase();
}

function getDicPhyFllNm() {
    return document.getElementById("dicPhyFllNm").value.trim().toUpperCase();
}

function getDicDesc() {
    return document.getElementById("dicDesc").value.trim();
}

function getEntClssYn() {
    return document.getElementById("entClssYn").checked ? 'Y' : 'N';
}

function getAttrClssYn() {
    return document.getElementById("attrClssYn").checked ? 'Y' : 'N';
}


function isDicLogNameEmpty() {
    return getDicLogNm() === "";
}

function isPhyNameEmpty() {
    return getDicPhyNm() === "";
}

function sendAjaxRequest(url, data, successCallback, errorCallback) {
    $.ajax({
        type: "POST",
        url: url,
        data: JSON.stringify(data),  // 데이터를 JSON 문자열로 변환
        contentType: "application/json",  // Content-Type을 application/json으로 설정
        success: successCallback,
        error: errorCallback,
    });
}


let wordDTO = {}; // 빈 객체로 초기화

function checkDuplicateWord() {
    const stdAreaId = getStandardAreaName();
    const dicLogNm = getDicLogNm();
    const dicPhyNm = getDicPhyNm();

    if (isDicLogNameEmpty() || isPhyNameEmpty()) {
        showAlert("논리명 또는 물리명을 입력하세요.");
        disableConfirmButton();
    } else {


        sendAjaxRequest(
            "/dms/single-word/duplicateRest",
            {
                stdAreaId: stdAreaId,
                dicLogNm: dicLogNm,
                dicPhyNm: dicPhyNm
            },
            function (response) {
                handleDuplicateWordCheckResponse(response);
            },
            function () {
                handleAjaxError();
            }
        );
    }
}


// 중복 확인 응답 처리 함수
function handleDuplicateWordCheckResponse(response) {
    if (response.isDuplicate) {
        showAlert("중복된 논리명 또는 물리명. 다른 이름을 입력하세요.");
        disableConfirmButton();
    } else {
        showAlert("해당 논리, 물리명은 사용 가능합니다.", "success");
        enableConfirmButton();
    }
}

function confirmButtonClick() {
    const stdAreaId = getStandardAreaName();
    const logicalName = getDicLogNm();
    const physicalName = getDicPhyNm();
    const phyFullName = getDicPhyFllNm();
    const dicDescription = getDicDesc();
    const entityClssYn = getEntClssYn();
    const attributeClssYn = getAttrClssYn();

    if (isDicLogNameEmpty() && isPhyNameEmpty()) {
        showAlert("논리명 또는 물리명을 입력하세요.");
    } else {

        // WordDTO 생성
        const wordDTO = {
            stdAreaId: stdAreaId,
            dicLogNm: logicalName,
            dicPhyNm: physicalName,
            dicPhyFllNm: phyFullName,
            dicDesc: dicDescription,
            entClssYn: entityClssYn,
            attrClssYn: attributeClssYn
        };

        sendAjaxRequest(
            "/dms/single-word/insertRest",
            wordDTO, // WordDTO 전송
            function (response) {
                handleWordConfirmSuccess(response);
            },
            function () {
                handleAjaxError();
            }
        );
    }

}


function handleWordConfirmSuccess(response) {
    if (response.isWordInserted) {
        clearInputFields();
        disableConfirmButton();
        showAlert("단어 등록이 완료되었습니다.", "success");
    } else {
        disableConfirmButton();
        showAlert("서버 오류가 발생했습니다. 다시 시도해 주세요.");
    }
}

function handleAjaxError() {
    disableConfirmButton();
    showAlert("서버 오류가 발생했습니다. 다시 시도해 주세요.");

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
    document.getElementById("dicLogNm").value = "";
    document.getElementById("dicPhyNm").value = "";
    document.getElementById("dicPhyFllNm").value = "";
    document.getElementById("dicDesc").value = "";
    document.getElementById("entClssYn").checked = false; // checked 속성 변경
    document.getElementById("attrClssYn").checked = false; // checked 속성 변경
    disableConfirmButton();
}

document.getElementById("dicLogNm").addEventListener("input", function () {
    disableConfirmButton();
});

document.getElementById("dicPhyNm").addEventListener("input", function () {
    disableConfirmButton();
});






