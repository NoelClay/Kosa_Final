function getStdTermDicId() {
    return document.getElementById("stdTermModalDicId").value.trim();
}

function getStdTermDomId() {
    return document.getElementById("stdTermModalDomId").value.trim();
}

function getStdTermModalDomId() {
    return document.getElementById("stdTermModalDomId").value.trim();

}

function getStdTermModalDesc() {
    return document.getElementById("stdTermModalDesc").value.trim();

}


const termUpdateButton = document.getElementById('updateTermForStdModal');
// Adding a click event listener to the button
termUpdateButton.addEventListener('click', function () {
    // Call the updateTermForStdTermModal function when the button is clicked
    updateTermForStdTermModal();

});

editTerm = new bootstrap.Modal(document.getElementById('editTerm'));


function updateTermForStdTermModal() {
    // DICiD 초기화
    const dicId = getStdTermDicId();
    const domId = getStdTermModalDomId();
    const dicDesc = getStdTermModalDesc();

    sendAjaxRequest(
        "/dms/single-term/updateTermRest",
        {
            dicId: dicId,
            domId: domId,
            dicDesc: dicDesc,
        },
        function (response) {
            handleUpdateTerm(response.dicId);
            editTerm.hide();
            // Hide the editTerm modal after the update is complete

        },
        function () {
            handleAjaxError();
        }
    );
}


function handleUpdateTerm(dicId) {
	sendAjaxRequest(
        "/dms/single-wordAndTerm/selectRest",
        { dicId: dicId },
        function (response) {
			const termDescriptionElement = document.getElementById(`term-description_${response.receivedWordAndTermDTO.dicId}`);
			termDescriptionElement.value = response.receivedWordAndTermDTO.dicDesc;
        },
        function () {
            handleAjaxError();
        }
    );
    showAlert("용어 수정을 완료하였습니다.", "info");
}

// ------------------ 단어 수정 ------------------------//

function getStdWordModalDicId() {

    return document.getElementById("stdWordModalDicId").value.trim();
}

function getStdModalWordLogNm() {

    return document.getElementById("stdModalWordLogNm").value.trim();
}

function getStdModalWordPhyNm() {

    return document.getElementById("stdModalWordPhyNm").value.trim();
}

function getStdModalWordFllNm() {

    return document.getElementById("stdModalWordFllNm").value.toUpperCase().trim();
}

function getStdModalWordEntClssYn() {
    return document.getElementById("stdModalWordEntClssYn").checked ? 'Y' : 'N';
}

function getStdModalWordAttrClssYn() {
    return document.getElementById("stdModalWordAttrClssYn").checked ? 'Y' : 'N';
}

function getStdModalWordDescription() {
    return document.getElementById("stdModalWordDescription").value.trim();
}

function getStdWordModalSTDYN() {
    return document.getElementById("stdWordModalSTDYN").value.trim();
}

function getStdModalStandardAreaName() {
    return document.getElementById("selectStandardArea").value.trim();
}


const wordUpdateButton = document.getElementById('updateWordForStdModal');
// Adding a click event listener to the button
wordUpdateButton.addEventListener('click', function () {
    const stdYn = getStdWordModalSTDYN();

    if (stdYn === 'N') {
        handleStandardYnWord();
        return; // 표준이 아닌 경우에 함수 종료
    }

    // Call the updateTermForStdTermModal function when the button is clicked
    updateWordForStdWordModal();
});

function handleStandardYnWord() {
    showAlert("비 표준어는 수정할 수 없습니다.");
}

editWord = new bootstrap.Modal(document.getElementById('editWord'));

function updateWordForStdWordModal() {
    // DICiD 초기화

    const modalWordDicId = getStdWordModalDicId();
    const modalWordLogNm = getStdModalWordLogNm();
    const modalWordPhyNm = getStdModalWordPhyNm();
    const modalWordEngFllNm = getStdModalWordFllNm();
    const modalWordDescription = getStdModalWordDescription();

    const modalWordEntClssYn = getStdModalWordEntClssYn();
    const modalWordAttrClssYn = getStdModalWordAttrClssYn();


    const wordDTO = {
        dicId: modalWordDicId,
        dicLogNm: modalWordLogNm,
        dicPhyNm: modalWordPhyNm,
        dicPhyFllNm: modalWordEngFllNm,
        dicDesc: modalWordDescription,
        entClssYn: modalWordEntClssYn,
        attrClssYn: modalWordAttrClssYn,
    };

    sendAjaxRequest(
        "/dms/single-word/updateWordRest",
        wordDTO,
        function (response) {
            handleUpdateWord(response.dicId);
            editWord.hide();

        },
        function () {
            handleAjaxError();
        }
    );
}

document.getElementById('modalTermWordValidation').addEventListener('click', function () {
    checkStdModalWordDuplicate();
});


function checkStdModalWordDuplicate() {
    const stdModalStdAreaId = getStdModalStandardAreaName();
    const stdModalDicLogNm = getStdModalWordPhyNm();
    const stdModalDicPhyNm = getStdModalWordLogNm();


    sendAjaxRequest(
        "/dms/single-word/duplicateRest",
        {
            stdAreaId: stdModalStdAreaId,
            dicLogNm: stdModalDicLogNm,
            dicPhyNm: stdModalDicPhyNm
        },
        function (response) {
            handleStdModalWordDuplicateCheckResponse(response);
        },
        function () {
            handleAjaxError();
        }
    );

}

function handleStdModalWordDuplicateCheckResponse(response) {
    if (response.isDuplicate) {
        showAlert("중복된 논리명 또는 물리명. 다른 이름을 입력하세요.");
    } else {
        showAlert("해당 논리, 물리명은 사용 가능합니다.", "success");
    }
}


function handleUpdateWord(dicId) {
	sendAjaxRequest(
        "/dms/single-wordAndTerm/selectRest",
        { dicId: dicId },
        function (response) {
			const wordFullNameElement = document.getElementById(`word-full-name_${response.receivedWordAndTermDTO.dicId}`);
			wordFullNameElement.value = response.receivedWordAndTermDTO.dicPhyFllNm;
            const wordDescriptionElement = document.getElementById(`word-description_${response.receivedWordAndTermDTO.dicId}`);
            wordDescriptionElement.value = response.receivedWordAndTermDTO.dicDesc;
        },
        function () {
            handleAjaxError();
        }
    );
    showAlert("단어 수정을 완료하였습니다.", "info");
}

let deleteWord = new bootstrap.Modal(document.getElementById('deleteWord'));
let deleteTerm = new bootstrap.Modal(document.getElementById('deleteTerm'));


function deleteSingleWord(dicId) {
    sendAjaxRequest(
        "/dms/single-wordAndTerm/deleteRest",
        {
            dicId: dicId
        },
        function (response) {
            handleDeleteWord();
            deleteWord.hide();

        },
        function () {
            handleAjaxError();
            // Handle AJAX error
        }
    );
}

function deleteSingleTerm(dicId) {
    sendAjaxRequest(
        "/dms/single-wordAndTerm/deleteRest",
        {
            dicId: dicId
        },
        function (response) {
            handleDeleteTerm();
            deleteTerm.hide();
        },
        function () {
            handleAjaxError();
            // Handle AJAX error
        }
    );
}

function handleDeleteWord() {
    showAlert("단어 삭제를 완료하였습니다.", "info");
}

function handleDeleteTerm() {
    showAlert("용어 삭제를 완료하였습니다.", "info");
}
