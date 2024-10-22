
function getDomainInfo(domId) {
    if (!domId) {
        console.error('dicId is not available');
        return;
    }


    sendAjaxRequest(
        "/dms/single-domain/selectRest",
        {
            standardAreaName: document.getElementById('selectStandardArea').value,
            domId: domId
        },
        function (response) {
            insertEachDomainTag(response.receivedDomainDTO);
        },

        function () {
            handleAjaxError();
            // Handle AJAX error
        }
    );
}

function getAbbreviationOfDataType(dataTypeCode) {
	let abbreviation;
	switch(dataTypeCode) {
		case 'CHAR' : abbreviation = 'C';
		break;
		case 'NCHAR' : abbreviation = 'NC';
		break;
		case 'VARCHAR' : abbreviation = 'V';
		break;
		case 'NVARCHAR' : abbreviation = 'NV';
		break;
		case 'BOOLEAN' : abbreviation = 'BOOL';
		break;
		case 'NUMBER' : abbreviation = 'N';
		break;
		case 'TIME' : abbreviation = 'TM';
		break;
		case 'TIMESTAMP' : abbreviation = 'TMS';
		break;
		case 'DATE' : abbreviation = 'DT';
		break;
		case 'DATETIME' : abbreviation = 'DTM';
		break;
		case 'CLOB' : abbreviation = 'CLOB';
		break;
		case 'NCLOB' : abbreviation = 'NCLOB';
		break;
		case 'BLOB' : abbreviation = 'BLOB';
		break;
	}
	return abbreviation;
}

function insertEachDomainTag(receivedDomainDTO) {
    if (receivedDomainDTO) { // Added a check for the existence of receivedDomainDTO
    
		const stdAreaId = document.getElementById('selectStandardArea').value;
        getDomainGroup(stdAreaId, receivedDomainDTO.domGroupId );

        document.getElementById('stdModalDomId').value = receivedDomainDTO.domId || '';
        document.getElementById('stdModalkeyDomName').value = receivedDomainDTO.keyDomName || '';
        document.getElementById('stdModalDomName').value = receivedDomainDTO.domName || '';
        document.getElementById('stdModaldomTypeCode').value = receivedDomainDTO.domTypeCode || '';
        //document.getElementById('stdModalDomGroupId').value = receivedDomainDTO.domGroupId || '';
        document.getElementById('stdModalDomdataTypeCode').value = getAbbreviationOfDataType(receivedDomainDTO.dataTypeCode) || '';
        document.getElementById('stdModaldataLen').value = receivedDomainDTO.dataLen || '';
        document.getElementById('stdModalDataScale').value = receivedDomainDTO.dataScale || '';
        document.getElementById('stdModalDataMin').value = receivedDomainDTO.dataMin || '';
        document.getElementById('stdModalDataMax').value = receivedDomainDTO.dataMax || '';
        document.getElementById('stdModaldomDescription').value = receivedDomainDTO.domDescription || '';
        
        
    }
}

//도메인 그룹
function getDomainGroup(stdAreaId, domGroupId) {
    sendAjaxRequest(
    "/dms/single-domain/group",
    {},
    function (response) {
        
        const domainGroupDTO = response.domainGroupDTO;
            // data 배열을 순회하면서 각 요소의 domGrpId가 비어있지 않은 경우, select 요소에 새로운 option 추가
        $("select#stdModalDomGroupId option").remove();
        $("select#stdModalDomGroupId").append(`<option value="" selected disabled>도메인 그룹</option>`);
        //$("select#stdModalDomGroupId").append(`<option value="">선택 안함</option>`);
        for (let i = 0; i < domainGroupDTO.length; i++) {
            // data[i]의 domGrpId가 비어있지 않은지 확인, // 조건을 만족하는 경우에만 아래의 작업을 수행
            if (domainGroupDTO[i].domGrpId !== '') {
				let domGrpId = domainGroupDTO[i].domGrpId;
				let domGrpNm = domainGroupDTO[i].domGrpNm;
				 $("select#stdModalDomGroupId").append("<option value='" + domGrpId + "'>" + domGrpNm + "</option>");
            }
        }
        document.getElementById('stdModalDomGroupId').value = domGroupId || '';
        
    },

    function () {
        handleAjaxError();
        // Handle AJAX error
    }
);
}
