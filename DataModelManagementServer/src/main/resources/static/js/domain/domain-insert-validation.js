// 중복 확인 버튼 클릭 이벤트 핸들러
document.getElementById("checkDuplicateButton").addEventListener("click", function () {
    checkDuplicate();
});

// Confirm 버튼 클릭 이벤트 핸들러
document.getElementById("confirmButton").addEventListener("click", function () {
    confirmButtonClick();
});


document.addEventListener("DOMContentLoaded", function(){
	DataFromServer();
});

function DataFromServer() {
    sendAjaxRequest(
        "/dms/session/get",
        null,
        function (response) {
			const stdAreaId = document.getElementById('selectStandardArea').value
			getDomainGroup();
        }
    );
}

//도메인 그룹
function getDomainGroup() {
    sendAjaxRequest(
    "/dms/single-domain/group",
    {},
    function (response) {        
        const domainGroupDTO = response.domainGroupDTO;
            // data 배열을 순회하면서 각 요소의 domGrpId가 비어있지 않은 경우, select 요소에 새로운 option 추가
            $("select#domGroupId option").remove();
            $("select#domGroupId").append(`<option value="" disabled selected>도메인 그룹</option>`);
            //$("select#domGroupId").append(`<option value="">선택 안함</option>`);
            for (let i = 0; i < domainGroupDTO.length; i++) {
                // data[i]의 domGrpId가 비어있지 않은지 확인, // 조건을 만족하는 경우에만 아래의 작업을 수행
                if (domainGroupDTO[i].domGrpId !== '') {
					let domGrpId = domainGroupDTO[i].domGrpId;
					let domGrpNm = domainGroupDTO[i].domGrpNm;
					 $("select#domGroupId").append("<option value='" + domGrpId + "'>" + domGrpNm + "</option>");
                
                }
            }
    },

    function () {
        handleAjaxError();
        // Handle AJAX error
    }
);
}

// 도메인 단건 등록에서 표준분류체계 변경시 표준분류체계에 따라 도메인 그룹 변경
//document.getElementById('selectStandardArea').addEventListener('change', () => {
	//getDomainGroup();
//})

//도메인명 자동생성
function updateDomName() {
    $("#confirmButton").attr('disabled', true); // 입력하거나 셀렉트박스 바꿀때마다 비활성화
    let keyDomName = $("#keyDomName").val().toUpperCase() != null ? $("#keyDomName").val().toUpperCase() : '';
    let dataTypeCode = $("#dataTypeCode").val() != null ? $("#dataTypeCode").val() : '';
    let dataLen = $("#dataLen").val() != null ? $("#dataLen").val() : '';
    let dataScale = $("#dataScale").val() != null ? $("#dataScale").val() : '';

    let domNameValue = keyDomName + ' ' + dataTypeCode;
    if (dataLen !== '') {
		domNameValue += dataLen;
	}
    if (dataScale !== '') {
        domNameValue += "," + dataScale;
    }

    $("#domName").val(domNameValue);


}

// 데이터 타입이 'NUMBER' 일 때, 데이터 길이는 소수점 길이보다 커야한다.
//document.getElementById("dataLen").addEventListener('input', checkDataLength);
//document.getElementById("dataScale").addEventListener('input', checkDataLength);
//document.getElementById("dataTypeCode").addEventListener('change', checkDataLength);


function isNumber(value){
	const regex = /^[0-9]+$/;
	return regex.test(value);
}

function checkValidation(){
	const parsedDataLenVal = parseInt(dataLenInput.value);
    const parsedDataScaleVal = parseInt(dataScaleInput.value);
    const parsedDataMinVal = parseInt(dataMinInput.value);
    const parsedDataMaxVal = parseInt(dataMaxInput.value);
 	const dataTypeCodeVal = document.getElementById("dataTypeCode").value;

    if (dataTypeCodeVal === 'N' && parsedDataLenVal !== NaN && parsedDataScaleVal !== NaN){
	    if (parsedDataLenVal <= parsedDataScaleVal) {
	        showAlert('데이터 길이는 데이터 소수점보다 커야합니다.');
	        dataLenInput.value = '';
	        updateDomName();
		} else if (parsedDataScaleVal > 6) {
			showAlert('데이터 소수점은 숫자 6까지만 입력 가능합니다.');
			dataScaleInput.value = '';
			updateDomName();
		}
	}

   	if (parsedDataMinVal !== NaN && parsedDataMaxVal !== NaN){
	    if (parsedDataMaxVal <= parsedDataMinVal) {
	        showAlert('데이터 범위의 최소, 최대 값을 정확히 입력해 주세요.');
	        dataMaxInput.value = '';
	        updateDomName();
		}
	}
}

const dataMinInput = document.getElementById("dataMin");
const dataMaxInput = document.getElementById("dataMax");
const dataLenInput = document.getElementById("dataLen");
const dataScaleInput = document.getElementById("dataScale");
const dataTypeCodeOption = document.getElementById("dataTypeCode");

function handleInput(inputElement) {
    const enteredValue = inputElement.value;
    const isValid = isNumber(enteredValue);

    if (!isValid) {
        showAlert('숫자만 입력이 가능합니다.');
        inputElement.value = '';
        return;
    }
}

dataLenInput.addEventListener("input", function() {
    handleInput(dataLenInput);
    updateDomName();
    checkValidation();
});

dataScaleInput.addEventListener("input", function() {
    handleInput(dataScaleInput);
    updateDomName();
    checkValidation();
});

dataMinInput.addEventListener("input", function() {
    handleInput(dataMinInput);
    updateDomName();
    checkValidation();
});

dataMaxInput.addEventListener("input", function() {
    handleInput(dataMaxInput);
    updateDomName();
    checkValidation();
});

dataTypeCodeOption.addEventListener("change", function() {
    updateDomName();
    checkValidation();
});


$("#keyDomName").on('input', updateDomName); //셋keyDomName, #dataLen, #dataScale중 하나라도 입력을 받으면 updateDomName함수가 계속 실행(inputbox에 입력할 떄)
$("#dataTypeCode").change(updateDomName);//dataTypeCode를 입력할 때마다  updateDomName 함수가 실행됨(option selectbox 선택시)

// 도메인명 가져오기
function getDomName() {
    return document.getElementById("domName").value.trim();
}
function getKeyDomName() {
    return document.getElementById("keyDomName").value.trim().toUpperCase();
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
        contentType: "application/json",  // Content-Type을 application/json으로 설정
        success: successCallback,
        error: errorCallback,
    });
}

// 프로젝트 선택 여부 확인
function hasNotSubjArea(subjArea) {
    return subjArea === null || subjArea === undefined || subjArea.trim() === '';
}

function isNotEmptyRequiredFiled() {
	const subjArea = document.getElementById("selectStandardArea").value.trim();
	if(hasNotSubjArea(subjArea)){
    	showAlert('프로젝트를 선택해야 중복확인이 가능합니다.');
    	return;
	}
	
	if (isDataTypeEmpty()) {
        showAlert("논리 데이터 타입을 입력하세요.");
        disableConfirmButton();
        return false;
    } else if (isKeyDomNameEmpty()) {
        showAlert("대표 도메인명을 입력하세요.");
        disableConfirmButton();
        return false;
    } else if (isDomTypeEmpty()) {
        showAlert("도메인 유형을 입력하세요.");
        disableConfirmButton();
        return false;
    }
    return true;
}

// 중복 확인 수행 함수
function checkDuplicate() {
    const domName = getDomName();
    const domGroupId = document.getElementById('domGroupId').value;
    const selectStandardArea = document.getElementById('selectStandardArea').value;
    
    if (isNotEmptyRequiredFiled()) {
		sendAjaxRequest(
	    "/dms/single-domain/duplicateDomainRest",
	    {
	        domName: domName,
	        domGroupId: domGroupId,
	        selectStandardArea: selectStandardArea
	
	    },
	    function (response) {
	        handleDuplicateDomainCheckResponse(response);
	    },
	    function () {
	        handleAjaxError();
	    }
		);
	}
    
}

// 중복 확인 응답 처리 함수
function handleDuplicateDomainCheckResponse(response) {
    if (response.isDuplicate) {
        showAlert("중복된 도메인명입니다. 다른 이름을 입력하세요.");
        disableConfirmButton();
    } else {
        showAlert("도메인명 사용이 가능합니다.", "success");
        enableConfirmButton();
    }
}

// 도메인 등록 버튼
function confirmButtonClick() {
    const keyDomName = document.getElementById('keyDomName').value.toUpperCase();
    const selectStandardArea = document.getElementById('selectStandardArea')
    const domName = document.getElementById('domName').value;
    const domDescription = document.getElementById('domDescription').value;
    const domTypeCode = document.getElementById('domTypeCode').value;
    const domGroupId = document.getElementById('domGroupId').value;
    let dataTypeCode = '';
    $.each( $('#dataTypeCode').find('option'), function(e1, e2) {
        if(e2.value=== $('#dataTypeCode').val()) {
            dataTypeCode = e2.text;
        }
    });
    const dataLen = document.getElementById('dataLen').value;
    const dataScale = document.getElementById('dataScale').value;
    const dataMin = document.getElementById('dataMin').value;
    const dataMax = document.getElementById('dataMax').value;

    sendAjaxRequest(
        "/dms/single-domain/insertRest",
        {

            selectStandardArea: selectStandardArea.options[selectStandardArea.selectedIndex].text,
            keyDomName: keyDomName,
            domName: domName,
            domDescription: domDescription,
            domTypeCode: domTypeCode,
            domGroupId: domGroupId,
            dataTypeCode: dataTypeCode,
            dataLen: dataLen,
            dataScale: dataScale,
            dataMin: dataMin,
            dataMax: dataMax,
        },
        function (response) {
            handleDomainConfirmSuccess(response);
        },
        function () {
            handleAjaxError();
        }
    );
}

// Confirm 성공 처리 함수
function handleDomainConfirmSuccess(response) {
    if (response.isDomainInserted) {
        clearInputFields();
        disableConfirmButton();
        showAlert("도메인 등록이 완료되었습니다.", "success");

    } else {
        disableConfirmButton();
        showAlert("서버 오류가 발생했습니다. 다시 시도해 주세요.");
    }
}

// AJAX 오류 처리 함수
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
    document.getElementById("keyDomName").value = "";
    document.getElementById("domName").value = "";
    document.getElementById("domTypeCode").selectedIndex = 0;
    document.getElementById("domGroupId").value = "";
    document.getElementById("dataTypeCode").selectedIndex = 0;
    document.getElementById("dataLen").value = "";
    document.getElementById("dataScale").value = "";
    document.getElementById("dataMin").value = "";
    document.getElementById("dataMax").value = "";
    document.getElementById("domDescription").value = "";
    disableConfirmButton();
}

// 도메인명 입력 이벤트 핸들러
document.getElementById("keyDomName").addEventListener("input", function () {
    disableConfirmButton();
});