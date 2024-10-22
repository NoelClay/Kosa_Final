function getWordInfo(dicId) {
    console.log("getWordAndTermInfo 실행됨");

    // Ensure dicId is properly defined before making the AJAX request
    if (!dicId) {
        console.error('dicId is not available');
        return;
    }

    sendAjaxRequest(
        "/dms/single-wordAndTerm/selectRest",
        { dicId: dicId },
        function (response) {
            insertEachWordTag(response.receivedWordAndTermDTO); // Fixed typo: 'respons' to 'response'
            insertStandardYnTag(response.standardYn);
        },
        function () {
            handleAjaxError();
            // Handle AJAX error
        }
    );
}



function getTermInfo(dicId) {

    // Ensure dicId is properly defined before making the AJAX request
    if (!dicId) {
        console.error('dicId is not available');
        return;
    }

    sendAjaxRequest(
        "/dms/single-wordAndTerm/selectTermRest",
        { dicId: dicId },
        function (response) {
            insertEachTermTag(response.receivedTermDTO)
        },
        function () {
            handleAjaxError();
            // Handle AJAX error
        }
    );
}

function insertStandardYnTag(receivedTermDTO) {



}

function insertEachTermTag(receivedTermDTO) {
    if(receivedTermDTO) {


        const replaceSpacesWithPlus = (value) => value.replace(/ /g, '+');
        document.getElementById('stdTermModalDomId').value = receivedTermDTO.domId || '';
        document.getElementById('stdTermModalDicId').value = receivedTermDTO.dicId || '';
        console.log("stdTermModalDicId 출력 실험=" + receivedTermDTO.dicId);
        document.getElementById('stdTermModalLogNm').value = receivedTermDTO.dicLogNm || '';
        document.getElementById('stdTermModalPhyNm').value = receivedTermDTO.dicPhyNm || '';
        document.getElementById('stdTermModalDomType').value = receivedTermDTO.domTypeCode || '';
        document.getElementById('stdTermModalDomName').value = receivedTermDTO.domName || '';
        document.getElementById('stdTermModalDomDataType').value = receivedTermDTO.dataTypeCode || '';
        document.getElementById('stdTermModalDesc').value = receivedTermDTO.dicDesc || '';

        // 공백을 '+' 기호로 치환
        document.getElementById('stdTermModalLogNmInfo').value = replaceSpacesWithPlus(receivedTermDTO.dicLogNm || '');
        document.getElementById('stdTermModalPhyNmInfo').value = replaceSpacesWithPlus(receivedTermDTO.dicPhyNm || '');

    }
}



function insertEachWordTag(receivedWordAndTermDTO) {
    if (receivedWordAndTermDTO) { // Added a check for the existence of receivedWordAndTermDTO
        document.getElementById('stdWordModalDicId').value = receivedWordAndTermDTO.dicId || '';
        document.getElementById('stdModalWordLogNm').value = receivedWordAndTermDTO.dicLogNm || '';
        document.getElementById('stdModalWordPhyNm').value = receivedWordAndTermDTO.dicPhyNm || '';
        document.getElementById('stdModalWordFllNm').value = receivedWordAndTermDTO.dicPhyFllNm || '';
        document.getElementById('stdModalWordDescription').value = receivedWordAndTermDTO.dicDesc || '';
        document.getElementById('stdWordModalSTDYN').value = receivedWordAndTermDTO.standardYn || '';
        console.log("stdWordModalSTDYN 출력 실험=" + receivedWordAndTermDTO.standardYn);


        // 체크박스의 경우
        document.getElementById('stdModalWordEntClssYn').checked = receivedWordAndTermDTO.entClssYn === 'Y';
        document.getElementById('stdModalWordAttrClssYn').checked = receivedWordAndTermDTO.attrClssYn === 'Y';

    }
}



