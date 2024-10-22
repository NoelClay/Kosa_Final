function getStandardAreaName() {
    return document.getElementById("selectStandardArea").value.trim();
}

function getModalLogNm() {
    return document.getElementById("modalLogNm").value.toUpperCase().trim();
}

function isModalLogNmEmpty() {
    return getModalLogNm() === "";
}

function getModalPhyNm() {
    return document.getElementById("modalPhyNm").value.toUpperCase().trim();
}

function isModalPhyNmEmpty() {
    return getModalPhyNm() === "";
}

function getModalFllNm() {
    return document.getElementById("modalFllNm").value.toUpperCase().trim();
}

function getModalDescription() {
    return document.getElementById("modalDescription").value.trim();
}

function getModalEntClssYn() {
    return document.getElementById("modalEntClssYn").checked ? 'Y' : 'N';
}

function getModalAttrClssYn() {
    return document.getElementById("modalAttrClssYn").checked ? 'Y' : 'N';
}

document.addEventListener('DOMContentLoaded', function () {
    const modalTermWordValidation = document.getElementById('modalTermWordValidation');

    if (modalTermWordValidation) {
        modalTermWordValidation.addEventListener('click', function () {
            checkModalTermWordDuplicate();
        });
    }
});

document.addEventListener('DOMContentLoaded', function () {
    const ModalTermWordInsert = document.getElementById('ModalTermWordInsertButton');

    if (ModalTermWordInsert) {
        ModalTermWordInsert.addEventListener('click', function () {
            modalTermWordInsert();
        });
    }
});
let wordDTO = {}; // 빈 객체로 초기화
function checkModalTermWordDuplicate() {
    const stdAreaId = getStandardAreaName();
    const dicLogNm = getModalLogNm();
    const dicPhyNm = getModalPhyNm();


    sendAjaxRequest(
        "/dms/single-word/duplicateRest",
        {
            stdAreaId: stdAreaId,
            dicLogNm: dicLogNm,
            dicPhyNm: dicPhyNm
        },
        function (response) {
            handleDuplicateWordResponse(response);
        },
        function () {
            handleAjaxError();
        }
    );
}

function handleDuplicateWordResponse(response) {
    if (response.isDuplicate) {
        showAlert("중복된 논리명 또는 물리명. 다른 이름을 입력하세요.");
        disableModalTermInsertButton();
    } else {
        showAlert("해당 논리, 물리명은 사용 가능합니다.", "success");
        enableModalTermInsertButton();
    }
}




function modalTermWordInsert() {
    const stdAreaId = getStandardAreaName();
    const logicalName = getModalLogNm();
    const physicalName = getModalPhyNm();
    const phyFullName = getModalFllNm();
    const dicDescription = getModalDescription();
    const entityClssYn = getModalEntClssYn();
    const attributeClssYn = getModalAttrClssYn();

    if (isModalLogNmEmpty() && isModalPhyNmEmpty()) {
        showAlert("논리명 또는 물리명을 입력하세요.");
    } else {
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
            wordDTO,
            function (response) {
                handleConfirmTermModalWordSuccess(response);
                modalTermWord.hide();
            },

            function () {
                handleAjaxError();
            }
        );
    }
}




function handleConfirmTermModalWordSuccess() {
        showAlert("단어 등록이 완료되었습니다.", "success");
}

function handleAjaxError() {
    showAlert("서버 오류가 발생했습니다. 다시 시도해 주세요.");
}

// Confirm 버튼 비활성화 함수
function disableModalTermInsertButton() {
    document.getElementById("ModalTermWordInsertButton").setAttribute("disabled", "true");
}

// Confirm 버튼 활성화 함수
function enableModalTermInsertButton() {
    document.getElementById("ModalTermWordInsertButton").removeAttribute("disabled");
}
