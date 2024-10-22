
//도메인명은 수정 안되게
//중복확인 활성화버튼

//대표도메인 수정하기
function getDomTypeForStdDomainModal() {
    return document.getElementById("stdModalkeyDomName").value.trim();
}


//데이터 길이
function getDomTypeForStdTermModal() {///?
    return document.getElementById("stdModaldataLen").value.trim();
}

//데이터 소수점
function getDomTypeForStdTermModal() {///?
    return document.getElementById("stdModalDataScale").value.trim();
}
// 데이터 범위(최소)
function getDomTypeForStdTermModal() {///?
    return document.getElementById("stdModalDataMin").value.trim();
}

// 데이터 범위(최대)
function getDomTypeForStdTermModal() {///?
    return document.getElementById("stdModalDataMax").value.trim();
}

//설명
function getModalDescription() {
    return document.getElementById("stdModaldomDescription").value.trim();
}


// 중복확인
function getDomNameForStdTermModal() {
    return document.getElementById("stdTermModalDomName").value.trim();
}


//수정버튼 클릭시
document.getElementById("updateDomainForStdModal").addEventListener("click", function() {
    updateDomainForStdModal();
});

// 도메인 수정
function updateDomainForStdModal() {
    let data = {};
    data["domId"] = $("#stdModalDomId").val() != null ? $("#stdModalDomId").val() : '';
    data["selectStandardArea"] = $("#selectStandardArea") != null ? $("#selectStandardArea").val() : '';
    data["domGroupId"] = $("#stdModalDomGroupId").val() != null ? $("#stdModalDomGroupId").val() : '';
    data["domName"] = $("#stdModalDomName").val() != null ? $("#stdModalDomName").val() : '';
    data["keyDomName"] = $("#stdModalkeyDomName").val() != null ? $("#stdModalkeyDomName").val() : '';
    data["dataTypeCode"] = $("#stdModalDomdataTypeCode").val() != null ? $("#stdModalDomdataTypeCode option:selected").text() : '';
    data["domTypeCode"] = $("#stdModaldomTypeCode").val() != null ? $("#stdModaldomTypeCode").val(): '';
    data["dataLen"] = $("#stdModaldataLen").val() != null ? $("#stdModaldataLen").val() : '';
    data["dataScale"] = $("#stdModalDataScale").val() != null ? $("#stdModalDataScale").val() : '';
    data["dataMin"] = $("#stdModalDataMin").val() != null ? $("#stdModalDataMin").val() : '';
    data["dataMax"] = $("#stdModalDataMax").val() != null ? $("#stdModalDataMax").val() : '';
    data["domDescription"] = $("#stdModaldomDescription").val() != null ? $("#stdModaldomDescription").val() : '';

    sendAjaxRequest(
        "/dms/single-domain/updateRest",//수정url
        data,
        function (response) {
            handleConfirmSuccess(response.domainDTO);

            //성공시닫힘게 모달창구현
            closeModal();
            handleUpdateDomain();
        },
        function () {
            handleAjaxError();
        }
    );
}
function closeModal() {
    // 모달을 닫는 코드 구현
    $('#deleteDomain').modal('hide'); // 예시: jQuery를 사용한 모달 닫기
}



function handleUpdateDomain() {
    showAlert("도메인 수정을 완료하였습니다.","Success");
}


// 중복 확인 버튼 클릭 이벤트 핸들러
document.getElementById("modalDomainValidation").addEventListener("click", function () {
    checkModalDomainDuplicate();
});

function checkModalDomainDuplicate() {
    const domName = document.getElementById('stdModalDomName').value;
    const domGroupId = document.getElementById('stdModalDomGroupId').value;
    const selectStandardArea = document.getElementById('selectStandardArea').value;

    sendAjaxRequest(
        "/dms/single-domain/duplicateDomainRest",
        {
            domName: domName,
            domGroupId: domGroupId,
            selectStandardArea: selectStandardArea
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
        showAlert("중복 된 도메인명입니다.");
        disableConfirmUpdateButton();
    } else {
        showAlert("해당 도메인명은 사용 가능합니다..", "success");
        enableConfirmUpdateButton();
    }
}

//도메인명 자동생성
function updateDomName() {
    $("#updateDomainForStdModal").attr('disabled', true); // 입력하거나 셀렉트박스 바꿀때마다 수정버튼 비활성화
    let keyDomName = $("#stdModalkeyDomName").val() != null ? $("#stdModalkeyDomName").val() : '';
    let dataTypeCode = $("#stdModalDomdataTypeCode").val() != null ? $("#stdModalDomdataTypeCode").val() : '';
    let dataLen = $("#stdModaldataLen").val() != null ? $("#stdModaldataLen").val() : '';
    let dataScale = $("#stdModalDataScale").val() != null ? $("#stdModalDataScale").val() : '';
    let domNameValue = keyDomName + ' ' + dataTypeCode;
    
    if (dataLen !== '') {
		domNameValue += dataLen;
	}
    if (dataScale !== '') {
        domNameValue += "," + dataScale;
    }
    $("#stdModalDomName").val(domNameValue);
}
$("#stdModalDataScale").off().on('input', function (e) { // 키보드의 i/o 입출력
    // keyup = 키보드의 키가 눌렷다 올라올때마다 // keydown은 키보드가 눌릴때마다
    let value = e.target.value;
    if (value < 1) $(this).val('');
    else if (value > 8) $(this).val(8);
    updateDomName();
});
$("#stdModaldataLen").off().on('input', function (e) {
    if($("#stdModalDomdataTypeCode").val() === 'NUM') {
        let v = e.target.value;
        if (v <= $("#stdModalDataScale").val()) {
            showAlert('데이터 길이는 데이터 소수점보다 커야합니다.');
            $(this).val('');
        }
    }
    updateDomName();
});

$("#stdModalkeyDomName").on('input', updateDomName); //셋keyDomName, #dataLen, #dataScale중 하나라도 입력을 받으면 updateDomName함수가 계속 실행(inputbox에 입력할 떄)
$("#stdModalDomdataTypeCode").change(updateDomName);//dataTypeCode를 입력할 때마다  updateDomName 함수가 실행됨(option selectbox 선택시)

// 도메인명 가져오기
function getDomName() {
    return document.getElementById("domName").value.trim();
}
function getKeyDomName() {
    return document.getElementById("keyDomName").value.trim();
}
function getDataType() {
    return document.getElementById("dataTypeCode").value.trim();
}
function getDomType() {
    return document.getElementById("domTypeCode").value.trim();
}

// 도메인명이 비어 있는지 확인
function isKeyDomNameEmpty() {
    return getKeyDomName() === "";
}
function isDataTypeEmpty() {
    return getDataType() === "";
}
function isDomTypeEmpty() {
    return getDomType() === "";
}

// AJAX 요청을 보내는 공통 함수
function sendAjaxRequest(url, data, successCallback, errorCallback) {
    $.ajax({
        type: "POST",//post면 data이고, 컨트롤러에서 @RequestBody로 받는다, 안되면 contentType: "application/json" 추가한다 // get이면 data대신에 params이고, @RequestParam("keyName")으로 받는다.
        url: url,
        data: JSON.stringify(data),
        //params: {'domName' : domName} ==> @RequestParam("domName")
        contentType: "application/json",  // Content-Type을 application/json으로 설정
        success: successCallback,
        error: errorCallback,
    });
}

// 중복 확인 응답 처리 함수
function handleDuplicateCheckResponse(response) {
    if (response) {
        showAlert("중복된 도메인명입니다. 다른 이름을 입력하세요.");
        disableConfirmUpdateButton();
    } else {
        showAlert("도메인명 사용이 가능합니다.", "success");
        enableConfirmUpdateButton();
    }
}

// Confirm 성공 처리 함수
function handleConfirmSuccess(domainDTO) {
	const domainKeyDomainNameElement = document.getElementById(`domain-keydomain-name_${domainDTO.domId}`);
	domainKeyDomainNameElement.value = domainDTO.keyDomName;
	const domainLengthElement = document.getElementById(`domain-length_${domainDTO.domId}`);
	domainLengthElement.value = domainDTO.dataLen + "." + domainDTO.dataScale;
	const domainRangeElement = document.getElementById(`domain-datamin-datamax_${domainDTO.domId}`);
	domainRangeElement.value = (domainDTO.dataMin != null ? domainDTO.dataMin : '') + '~' + (domainDTO.dataMax != null ? domainDTO.dataMax : '');
	const domainDescElement = document.getElementById(`domain-description_${domainDTO.domId}`);
	domainDescElement.value = domainDTO.domDescription;
    $('.btn-close').click();

}

// AJAX 오류 처리 함수
function handleAjaxError() {
    showAlert("서버 오류가 발생했습니다. 다시 시도해 주세요.");
    disableConfirmUpdateButton();
}

// 수정 버튼 비활성화 함수
function disableConfirmUpdateButton() {
    document.getElementById("updateDomainForStdModal").setAttribute("disabled", "true");
}

// Confirm 버튼 활성화 함수
function enableConfirmUpdateButton() {
    document.getElementById("updateDomainForStdModal").removeAttribute("disabled");
}

// 입력 필드 초기화 함수
function clearInputFields() {
    document.getElementById("stdModalDomName").value = "";
    disableConfirmUpdateButton();
}

// 도메인명 입력 이벤트 핸들러
document.getElementById("stdModalkeyDomName").addEventListener("input", function () {
    enableConfirmUpdateButton();
});

