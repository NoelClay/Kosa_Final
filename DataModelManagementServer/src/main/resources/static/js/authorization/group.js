function getRowClickEvent() {
	const rows = document.querySelectorAll('tr[id^=groupRow]');
    
    rows.forEach(function(row) {
        row.addEventListener('click', function() {
            const groupIdCell = row.querySelector('td[name="groupId"]');
            if (groupIdCell) {
                const groupId = groupIdCell.textContent;
                getDetail(groupId);
            }
        });
    });
}

document.addEventListener('DOMContentLoaded', function() {
	getRowClickEvent();
});

function getDetail(groupId) {
	$.ajax({
	type: "POST",
	url: "/dms/authorization-group/detail",
	contentType: "application/json",
	data: groupId,
	success: function (data) {
		const groupDescription = data.groupDTO.groupDesc;

	    const tableDetailRow = document.getElementById('groupRow_detail_' + `${groupId}`);
	    const detail = document.getElementById('detail_content_' + `${groupId}`);
	    
	    if(tableDetailRow.style.display === 'none'){
			tableDetailRow.style.display = 'table-row';
			detail.style.display = 'table-cell';
			
			const detailRows = document.querySelectorAll('tr[id^=groupRow_detail_]');
			Array.from(detailRows).filter(detailRow => {
				const id = detailRow.id;
				if(!id.endsWith(groupId) && detailRow.style.display === 'table-row'){
					detailRow.style.display = 'none';
				}
			});
			
			const contentRows = document.querySelectorAll('tr[id^=detail_content_]');
			Array.from(contentRows).filter(contentRow => {
				const id = contentRow.id;
				if(!id.endsWith(groupId) && contentRow.style.display === 'table-row'){
					contentRow.style.display = 'none';
				}
			});

			document.getElementById('basic-icon-default-description_' + `${groupId}`).value = groupDescription;
			
			const buttonGroup = document.getElementById('memberTagBtns_' + groupId);

			if (buttonGroup) {
			    if (buttonGroup.innerHTML.trim() === '') {
			        createButtons(data.groupMemberDtoList, groupId);
			    }
			}
		
			showModalForAddingIndividual(groupId);

			getUpdateButton(data.groupDTO);
		} else {
			tableDetailRow.style.display = 'none';
			detail.style.display = 'none';	
	
		}
	    
	    const tbody = document.querySelector('tbody#groupAuthorization');
	    tbody.innerHTML = '';
	    
	    data.groupAuthorizationBySubjAreaIdDtoList.forEach(function(groupAuthorizationBySubjAreaIdDto, index) {
	      const tr = document.createElement('tr');
	      tr.id = "groupAuthRows";
	     
	      tr.innerHTML = `
	        <input type="hidden" id="subjAreaId" value="${groupAuthorizationBySubjAreaIdDto.subjAreaId}"/>
	        <input type="hidden" id="targetGroupId" value="${groupId}"/>
	        <td scope="row">${index + 1}</td>
	        <td name="subjAreaName">${groupAuthorizationBySubjAreaIdDto.subjAreaName}</td>
	        <td name="authTpCd">
	          <div class="col-md" style="margin-bottom: 0rem;">
	            <div class="form-check form-check-inline">
	              <input
	                class="form-check-input"
	                type="radio"
	                name="inlineRadioOptions${index}"
	                id="inlineRadio1_${index}"
	                value="R"
                    onclick="handleRadioClick(this)"
	                ${groupAuthorizationBySubjAreaIdDto.authTpCd === 'R' ? 'checked' : ''}
	              />
	              <label class="form-check-label" for="inlineRadio1_${index}">R</label>
	            </div>
	            <div class="form-check form-check-inline">
	              <input
	                class="form-check-input"
	                type="radio"
	                name="inlineRadioOptions${index}"
	                id="inlineRadio2_${index}"
	                value="W"
	                onclick="handleRadioClick(this)"
	                ${groupAuthorizationBySubjAreaIdDto.authTpCd === 'W' ? 'checked' : ''}
	              />
	              <label class="form-check-label" for="inlineRadio2_${index}">W</label>
	            </div>
	          </div></td>
	      `;
	      tbody.appendChild(tr);
	     });
	     getUpdateAuthBtn(groupId);
	},
	error: function (error) {
	    showAlert("사용자 정보를 조회하는데 실패했습니다.");
	}
    });
}

// 그룹 내 사용자 상세 정보 모달 창 생성
function getIndividualInfo(userId){
	const individualInfoOfGroupModal = new bootstrap.Modal(document.getElementById('individualInfoOfGroupModal'));
	individualInfoOfGroupModal.show();
	
	$.ajax({
	    type: "POST",
	    url: "/dms/authorization-individual/detail",
	    contentType: "application/json",
	    data: userId,
	    success: function(individualAuthorizationDetailDTO) {
			const individualDetail = individualAuthorizationDetailDTO.individualUserDTO;
			const groupList = individualAuthorizationDetailDTO.individualUserDTO.groupDTOList;
			
			document.getElementById('userId').value = individualDetail.usrId;
			document.getElementById('userName').value = individualDetail.usrName;
			document.getElementById('useYn').value = individualDetail.useYn;
			
			const tbody = document.querySelector('tbody#groupList');
	    	tbody.innerHTML = '';
	    	
	    	groupList.forEach(function(group, index){
		      const tr = document.createElement('tr');
	          tr.innerHTML = `
	          <td scope="row">${index + 1}</td>
	          <td name="groupId">${group.groupId}</td>
	          <td name="groupName">${group.groupName}</td>
	          `;
  		    tbody.appendChild(tr);				
			})  
	    },
	    error: function(error) {
	        showAlert("사용자 정보를 불러오는 데 실패했습니다.");
	    }
	});
}

// 라디오 버튼 선택 및 선택 해제
let lastSelected = null;
function handleRadioClick(radio) {
  if (lastSelected === radio) {
    radio.checked = false;
    lastSelected = null;
  } else {
    lastSelected = radio;
  }
}

// 그룹 내 속한 사용자 목록 Tag 형태로 생성
function createButtons(groupMemberDtoList, groupId){
	const buttonGroup = document.getElementById('memberTagBtns_' + `${groupId}`);
	
	groupMemberDtoList.forEach(member => {
    const tag = member.usrId + '(' + member.usrName + ')';
    const button = document.createElement("button");
    button.id = "memberTagBtn_" + member.usrId;
    button.type = "button";
    button.className = "btn btn-secondary btn-sm";
    button.style.borderRadius = "10px";
    button.style.flex = "0 1 auto";
	button.textContent = tag;

	buttonGroup.appendChild(button);
	
				
	document.getElementById('memberTagBtn_' + member.usrId).addEventListener('click', () => {
		getIndividualInfo(member.usrId);
	})
    });
}

// 사용자 조회, 추가 및 삭제 모달 생성
function showModalForAddingIndividual(groupId){
	document.getElementById('addIndividualToGroupBtn_' + `${groupId}`).addEventListener('click', () => {
		const individualSearchModal = new bootstrap.Modal(document.getElementById('individualSearchModal'));
		document.getElementById('targetGroupId').value = groupId;
    	individualSearchModal.show();
	})
}

// 검색창 enter키 입력해도 검색 진행
const inputOftargetUserName = document.getElementById('targetUserName');
inputOftargetUserName.addEventListener("keydown", function(e){
	if(e.key === 'Enter'){
		document.getElementById('searchUserName').click();
	}
})

// '사용자명'으로 사용자 검색
document.getElementById('searchUserName').addEventListener('click', () => {
	clickEventOfSearchUserName();
})

// 특정 사용자를 특정 그룹에 추가
function addIndividualToGroup(groupId, userId){
	const searchData = {
		targetUserId : userId,
		targetGroupId : groupId
	};

	$.ajax({
	    type: "POST",
	    url: "/dms/authorization-group/insert-individual",
	    contentType: "application/json",
	    data: JSON.stringify(searchData),
	    success: function(response) {
			if(response === true){
				showAlert("사용자를 그룹에 추가했습니다.", "success");
			    clickEventOfSearchUserName();
			} else{
				showAlert("사용자를 그룹에 추가하는 데 실패했습니다.");
			}
	    },
	    error: function(error) {
	        showAlert("사용자를 그룹에 추가하는 데 실패했습니다.");
	    }
	});
}

// 특정 그룹에서 특정 사용자 삭제
function deleteIndividualFromGroup(groupId, userId){
	const searchData = {
		targetUserId : userId,
		targetGroupId : groupId
	};

	$.ajax({
	    type: "POST",
	    url: "/dms/authorization-group/delete-individual",
	    contentType: "application/json",
	    data: JSON.stringify(searchData),
	    success: function(response) {
			if(response === true){
				showAlert("사용자를 그룹에서 삭제했습니다.", "success");
			    clickEventOfSearchUserName();
			} else{
				showAlert("사용자를 그룹에서 삭제하는 데 실패했습니다.");
			}
	    },
	    error: function(error) {
	        showAlert("사용자를 그룹에서 삭제하는 데 실패했습니다.");
	    }
	});
}

// '사용자명'으로 사용자 검색
function clickEventOfSearchUserName(){
	if (document.getElementById('userNameSearchResultTbody').innerHTML) {
    document.getElementById('userNameSearchResultTbody').innerHTML = '';
	} 

	const targetUserName = document.getElementById('targetUserName').value;
	const targetGroupId = document.getElementById('targetGroupId').value;
	const searchData = {
		targetUserName : targetUserName,
		targetGroupId : targetGroupId
	};
	
	$.ajax({
	    type: "POST",
	    url: "/dms/authorization-group/search-userName",
	    contentType: "application/json",
	    data: JSON.stringify(searchData),
	    success: function(dataList) {
			const userNameSearchResult = document.getElementById('userNameSearchResultTbody');
			dataList.forEach(function(data, index){
				const iconName = data.groupId === null ? 'bi-plus' : 'bi-dash';
				const iconAction = data.groupId === null ? `addIndividualToGroup('${targetGroupId}', '${data.usrId}')` : `deleteIndividualFromGroup('${targetGroupId}', '${data.usrId}')`;
				
				const row = document.createElement('tr');
				row.innerHTML = `
					<td>${index + 1}</td>
					<td>${data.groupName ? data.groupName : ''}</td>
	                <td style="text-align: center;"><i class="bi ${iconName}" style="cursor: pointer; font-size: 20px;" onclick="${iconAction}"></i></label></td>
	                <td>${data.usrId}</td>
	                <td>${data.usrName}</td>
	                <td>${data.usrEmail ? data.usrEmail : ''}</td>
	                <td>${data.useYn}</td>
				`
				userNameSearchResult.appendChild(row);
			})
	    },
	    error: function(error) {
	        showAlert("사용자 정보를 조회하는 데 실패했습니다.");
	    }
	});
}

// 그룹 정보 수정 모달 생성
function getUpdateButton(groupDTO){
	document.getElementById('updateGroupBtn_' + `${groupDTO.groupId}`).addEventListener('click', () => {
	const groupName = groupDTO.groupName;
	//const groupDescription = groupDTO.groupDesc;
	const updateGroupInfoModal = new bootstrap.Modal(document.getElementById('updateGroupInfoModal'));
    updateGroupInfoModal.show();

    document.getElementById('currentGroupName').value = groupName;
    // document.getElementById('newGroupDescription').value = groupDescription;  
	});
	getUpdateUserInfoBtn(groupDTO.groupId);
}

function getUpdateUserInfoBtn(targetGroupId){
	document.getElementById('updateGroupInfoBtn').addEventListener('click', () => {
	const updateGroupName = document.getElementById('newGroupName').value;
    const updateGroupDescription = document.getElementById('newGroupDescription').value;
    validateUpdateInput(targetGroupId, updateGroupName, updateGroupDescription);
});
}

function validateUpdateInput(targetGroupId, updateGroupName, updateGroupDescription){
	
	if((updateGroupName === null || updateGroupName === '') && (updateGroupDescription === null || updateGroupDescription === '')){
		showAlert('수정할 그룹명 또는 설명을 반드시 입력해 주세요.');
		return;
	}
	
	if (updateGroupName) {
	    if (document.getElementById('newGroupNameValidationRow').style.display === 'none') {
	      showAlert('입력한 그룹명의 중복 여부를 확인해 주세요.');
	      return;
	    }
    
	    if (document.getElementById('newGroupNameValidationMessage').textContent === '중복된 그룹명입니다. 다른 그룹명을 입력해 주세요.') {
	      showAlert('중복된 그룹명입니다. 다른 그룹명을 입력해 주세요.');
	      return;
	    }
	}
  	updateGroup(targetGroupId, updateGroupName, updateGroupDescription);
}

function updateGroup(targetGroupId, updateGroupName, updateGroupDescription){
	const groupDTO = {
		groupId: targetGroupId,
		groupName: updateGroupName,
        groupDesc: updateGroupDescription
	};
	
	$.ajax({
	    type: "POST",
	    url: "/dms/authorization-group/update-group-info",
	    contentType: "application/json",
	    data: JSON.stringify(groupDTO),
	    success: function(response) {
			if(response === true){
				showAlert('그룹 정보가 수정되었습니다.', 'success');
				setTimeout(function() {
			        window.location.reload();
			    }, 3000);
			} else {
				showAlert("그룹 정보를 수정하는데 실패했습니다.");
			}
	    },
	    error: function(error) {
	        showAlert("그룹 정보를 수정하는데 실패했습니다.");
	    }
	});
}

// 특정 그룹의 전체 프로젝트별 권한 수정
function getUpdateAuthBtn(targetGroupId){
	
	const updateAuthBtn = document.getElementById('updateAuthBtn');
	updateAuthBtn.onclick = null;
	updateAuthBtn.onclick = () => {
	showLoadingSpinner();

	const values = [];
	
	const groupAuthRows = document.querySelectorAll('[id="groupAuthRows"]');
	
	groupAuthRows.forEach(row => {
	    const subjAreaId = row.querySelector('[id^="subjAreaId"]').value;
	    const groupId = document.getElementById('targetGroupId').value;
	    const radios = row.querySelectorAll('[name^="inlineRadioOptions"]');
	    
	    radios.forEach(radio => {
	        if (radio.checked) {
	            values.push({
	                subjAreaId: subjAreaId,
	                groupId: groupId,
	                authTpCd: radio.value
	            });
	        }
	    });	    
	});
	
	const updatedValues = {
		groupId: targetGroupId,
		groupAuthorizationBySubjAreaIdDTOList: values
	};

	$.ajax({
	    type: "POST",
	    url: "/dms/authorization-group/update-auth",
	    contentType: "application/json",
	    data: JSON.stringify(updatedValues),
	    success: function(data) {
			hideLoadingSpinner();
			const groupId = data;
	        if (groupId !== 'updateFailure') {
				getDetail(groupId);
			} else {
				showAlert("프로젝트별 그룹 권한을 수정하는데 실패했습니다.");
			}
	    },
	    error: function(error) {
	        showAlert("프로젝트별 그룹 권한을 수정하는데 실패했습니다.");
	    }
	});
	}

}

// 그룹ID 중복 검사
document.getElementById('groupIdValidation').addEventListener('click', () => {
	const groupId = document.getElementById('groupId').value;
	$.ajax({
	    type: "POST",
	    url: "/dms/authorization-group/validation-groupId",
	    contentType: "application/json",
	    data: groupId,
	    success: function(response) {
	        if (response) {
				document.getElementById('groupIdValidationRow').style.display = 'block';
				document.getElementById('groupIdValidationRow').style.color = 'lightpink';
				document.getElementById('groupIdValidationMessage').textContent = '중복된 그룹ID입니다. 다른 그룹ID를 입력해 주세요.';
			} else {
				document.getElementById('groupIdValidationRow').style.display = 'block';
				document.getElementById('groupIdValidationRow').style.color = 'lightblue';
				document.getElementById('groupIdValidationMessage').textContent = '해당 그룹ID는 사용가능합니다.';
			}
	    },
	    error: function(error) {
	        showAlert("중복 검사에 실패하였습니다.");
	    }
	});
})

// 그룹명 중복 검사
document.getElementById('groupNameValidation').addEventListener('click', () => {
	const groupName = document.getElementById('groupName').value;
	$.ajax({
	    type: "POST",
	    url: "/dms/authorization-group/validation-groupName",
	    contentType: "application/json",
	    data: groupName,
	    success: function(response) {
	        if (response) {
				document.getElementById('groupNameValidationRow').style.display = 'block';
				document.getElementById('groupNameValidationRow').style.color = 'lightpink';
				document.getElementById('groupNameValidationMessage').textContent = '중복된 그룹명입니다. 다른 그룹명을 입력해 주세요.';
			} else {
				document.getElementById('groupNameValidationRow').style.display = 'block';
				document.getElementById('groupNameValidationRow').style.color = 'lightblue';
				document.getElementById('groupNameValidationMessage').textContent = '해당 그룹명은 사용가능합니다.';
			}
	    },
	    error: function(error) {
	        showAlert("중복 검사에 실패하였습니다.");
	    }
	});
})

// 그룹 정보 수정시, 그룹명 중복 검사
document.getElementById('newGroupNameValidation').addEventListener('click', () => {
	const newGroupName = document.getElementById('newGroupName').value;
	$.ajax({
	    type: "POST",
	    url: "/dms/authorization-group/validation-groupName",
	    contentType: "application/json",
	    data: newGroupName,
	    success: function(response) {
	        if (response) {
				document.getElementById('newGroupNameValidationRow').style.display = 'block';
				document.getElementById('newGroupNameValidationRow').style.color = 'lightpink';
				document.getElementById('newGroupNameValidationMessage').textContent = '중복된 그룹명입니다. 다른 그룹명을 입력해 주세요.';
			} else {
				document.getElementById('newGroupNameValidationRow').style.display = 'block';
				document.getElementById('newGroupNameValidationRow').style.color = 'lightblue';
				document.getElementById('newGroupNameValidationMessage').textContent = '해당 그룹명은 사용가능합니다.';
			}
	    },
	    error: function(error) {
	        showAlert("중복 검사에 실패하였습니다.");
	    }
	});
})

// 신규 그룹 등록 모달 생성
const openModalBtn = document.getElementById('openModalBtnForInsertNewGroup');
openModalBtn.addEventListener('click', () => {
	const insertNewGroupModal = new bootstrap.Modal(document.getElementById('insertNewGroupModal'));
	getAllSubjArea();	
    insertNewGroupModal.show();   
})

// 그룹 신규 등록 전, 그룹ID, 그룹명 필수 입력 여부 확인 및 중복 여부 확인 
function validateInsertInput(groupId, groupName, groupDescription){
	if(groupId === null || groupId === '' || groupName === null || groupName === ''){
		showAlert('그룹ID 와 그룹명은 반드시 입력해 주세요.');
        return;
	}
	
	if(groupId !== null && groupId !== ''){
		if(document.getElementById('groupIdValidationRow').style.display === 'none'){
			showAlert('그룹ID 중복 여부를 확인 해 주세요.');
	      	return;
		}
	}
	
	if(groupName !== null && groupName !== ''){
		if(document.getElementById('groupNameValidationRow').style.display === 'none'){
			showAlert('그룹명 중복 여부를 확인 해 주세요.');
	      	return;
		}
	}
	
	if(document.getElementById('groupIdValidationRow').style.display === 'block'){
		if(document.getElementById('groupIdValidationMessage').textContent === '중복된 그룹ID입니다. 다른 그룹ID를 입력해 주세요.'){
			showAlert('중복된 그룹ID입니다. 다른 그룹ID를 입력해 주세요.');
	      	return;
		}
	}
	
	if(document.getElementById('groupNameValidationRow').style.display === 'block'){
		if(document.getElementById('groupNameValidationMessage').textContent === '중복된 그룹명입니다. 다른 그룹명을 입력해 주세요.'){
			showAlert('중복된 그룹명입니다. 다른 그룹명을 입력해 주세요.');
	      	return;
		}
	}

  	insertGroup(groupId, groupName, groupDescription);
}

// 신규 그룹 등록 + 프로젝트별 신규 권한 생성
function insertGroup(groupId, groupName, groupDescription){
	showLoadingSpinner();
	const groupDetail = {
	    groupId: groupId,
	    groupName: groupName,
	    groupDesc: groupDescription
	};
	
	const groupAuth = [];
	const newGroupAuthRows = document.querySelectorAll('[id="newGroupAuthRows"]');
	newGroupAuthRows.forEach(row => {
	    const subjAreaId = row.querySelector('[id^="subjAreaId"]').value;
	    const subjAreaName = row.querySelector('[id^="subjAreaName"]').textContent;
	    const radios = row.querySelectorAll('[name^="inlineRadioOptions"]');
	    
	    radios.forEach(radio => {
	        if (radio.checked) {
	            groupAuth.push({
	                subjAreaId: subjAreaId,
	                subjAreaName: subjAreaName,
	                groupId: groupId,
	                authTpCd: radio.value
	            });
	        }
	    });
	});
	
	groupAuthorizationDetailDTO = {
		groupDTO: groupDetail,
		groupAuthorizationBySubjAreaIdDtoList: groupAuth
	};

	$.ajax({
	    type: "POST",
	    url: "/dms/authorization-group/insert-group",
	    contentType: "application/json",
	    data: JSON.stringify(groupAuthorizationDetailDTO),
	    success: function(groupId) {
			hideLoadingSpinner();
	        if (groupId !== 'insertFailure') {
				getDetail(groupId);
				showAlert("신규 등록이 완료되었습니다.", "success");
				setTimeout(() => window.location.reload(), 2000);
			} else {
				showAlert("신규 등록에 실패했습니다.");
			}
	    },
	    error: function(error) {
	        showAlert("신규 등록에 실패했습니다.");
	    }
	});
}

// 신규 등록 버튼 클릭 시, 이벤트
document.getElementById('insertNewGroupBtn').addEventListener('click', () => {
	const groupId = document.getElementById('groupId').value;
	const groupName = document.getElementById('groupName').value;
	const groupDescription = document.getElementById('groupDescription').value;
	
	validateInsertInput(groupId, groupName, groupDescription);
});

// 모든 프로젝트 조회
function getAllSubjArea() {
	$.ajax({
    type: "get",
    url: "/dms/authorization-individual/all-subjarea",
    success: function(response) {
        const selectElement = document.getElementById("subjAreaIdList");
        
        const defaultOption = document.createElement('option');
        defaultOption.value = "";
        defaultOption.textContent = "프로젝트명";
        defaultOption.disabled = true;
        defaultOption.selected = true;
        selectElement.appendChild(defaultOption);

        response.forEach(optionData => {
            const option = document.createElement('option');
            option.value = optionData.subjAreaId;
            option.textContent = optionData.subjAreaName;
            selectElement.appendChild(option);
        });
        
        selectElement.addEventListener('change', function() {
            const selectedSubjAreaId = this.value;
            const selectedSubjAreaName = this.options[this.selectedIndex].textContent;
            if (selectedSubjAreaId) {
                addRow(selectedSubjAreaId, selectedSubjAreaName);
            }
        });
    },
    error: function(error) {
        showAlert("프로젝트별 사용자 권한을 수정하는데 실패했습니다.");
    }
	});
}

let rowIndex = 1; 
function addRow(subjAreaId, subjAreaName) {
    const tableBody = document.getElementById("newGroupAuthorization");
    
    if (tableBody.querySelector(`input[name="subjAreaId"][value="${subjAreaId}"]`)) {
    return;
    }
    
    const newRow = document.createElement("tr");
    newRow.id = "newGroupAuthRows";
    newRow.innerHTML = `
    	<input type="hidden" id="subjAreaId${rowIndex}" name="subjAreaId" value="${subjAreaId}">
    	<td>${rowIndex}</td>
        <td id="subjAreaName${rowIndex}" value="${subjAreaName}">${subjAreaName}</td>
        	<td name="authTpCd">
	          <div class="col-md" style="margin-bottom: 0rem;">
	            <div class="form-check form-check-inline">
	              <input
	                class="form-check-input"
	                type="radio"
	                name="inlineRadioOptions${rowIndex}"
	                id="inlineRadio1_${rowIndex}"
	                value="R"
	              />
	              <label class="form-check-label" for="inlineRadio1_${rowIndex}">R</label>
	            </div>
	            <div class="form-check form-check-inline">
	              <input
	                class="form-check-input"
	                type="radio"
	                name="inlineRadioOptions${rowIndex}"
	                id="inlineRadio2_${rowIndex}"
	                value="W"
	              />
	              <label class="form-check-label" for="inlineRadio2_${rowIndex}">W</label>
	            </div>
	          </div>
            </td>       
    `;

    const previousRow = tableBody.querySelector(`tr[data-subj-area-id="${subjAreaId - 1}"]`);
    if (previousRow) {
        tableBody.insertBefore(newRow, previousRow.nextSibling);
    } else {
        tableBody.appendChild(newRow);
    }
    rowIndex++;
}

function refreshPage() {
    location.reload();
}

/*const insertNewSubjAreaAuthBtn = document.getElementById('insertNewSubjAreaAuthBtn');
insertNewSubjAreaAuthBtn.addEventListener('click', () => {
	showLoadingSpinner()
	const userName = document.getElementById('userName').value;
	const userId = document.getElementById('userId').value;
	const userPassword = document.getElementById('userPassword').value;
	const useYn = document.getElementById('useYn').value;

	const individualDetail = {
	    usrName: userName,
	    usrId: userId,
	    usrPassword: userPassword,
	    useYn: useYn
	};
		
	const individualAuth = [];
	const newIndividualAuthRows = document.querySelectorAll('[id="newIndividualAuthRows"]');
	newIndividualAuthRows.forEach(row => {
	    const subjAreaId = row.querySelector('[id^="subjAreaId"]').value;
	    const subjAreaName = row.querySelector('[id^="subjAreaName"]').textContent;
	    const radios = row.querySelectorAll('[name^="inlineRadioOptions"]');
	    
	    radios.forEach(radio => {
	        if (radio.checked) {
	            individualAuth.push({
	                subjAreaId: subjAreaId,
	                subjAreaName: subjAreaName,
	                usrId: userId,
	                authTpCd: radio.value
	            });
	        }
	    });
	});
	
	individualAuthorizationDetailDTO = {
		individualUserDTO: individualDetail,
		individualAuthorizationByStdjAreaIdDTOList: individualAuth
	};
	$.ajax({
	    type: "POST",
	    url: "/dms/authorization-individual/insert",
	    contentType: "application/json",
	    data: JSON.stringify(individualAuthorizationDetailDTO),
	    success: function(response) {
			hideLoadingSpinner();
			const usrId = response.usrId;
	        if (usrId !== null) {
				getDetail(usrId);
				showAlert("신규 등록이 완료되었습니다.", "success");
				setTimeout(() => window.location.reload(), 2000);
			} else {
				showAlert("신규 등록에 실패했습니다.");
			}
	    },
	    error: function(error) {
	        showAlert("신규 등록에 실패했습니다.");
	    }
	});
});

function closeModal() {
	$('#insertNewIndividualModal').modal('hide');
}

// 검색 조건이 '미사용자'일 경우, 검색어 입력창에 'N' 자동 생성
document.getElementById('searchCondition').addEventListener('change', function () {
  const searchCondition = this.value;
  const searchInput = document.getElementById('searchForUser');

  if (searchCondition === 'INACTIVE') {
    searchInput.value = 'N';
  } else {
    searchInput.value = '';
  }
});*/


// 검색창 enter키 입력해도 검색 진행
const inputOfUserSearch = document.getElementById('searchForGroup');
inputOfUserSearch.addEventListener("keydown", function(e){
	if(e.key === 'Enter'){
		document.getElementById('searchForGroupBtn').click();
	}
})

// 그룹 검색
document.getElementById('searchForGroupBtn').addEventListener('click', () => {
	showLoadingSpinner()
	const searchCondition = document.getElementById('searchCondition').value;
	const searchValue = document.getElementById('searchForGroup').value;
	
	if(searchCondition === '' || searchCondition === null){
		hideLoadingSpinner();
		showAlert('검색 조건을 선택해 주세요.');
		return;
	}
	
	$.ajax({
    type: "POST",
    url: "/dms/authorization-group/search-group",
    contentType: "application/json",
    data: JSON.stringify({
		searchCondition: searchCondition,
		searchValue: searchValue
	}),
    success: function(response) {
		hideLoadingSpinner();
		showAlert("검색이 완료되었습니다.", "success");
		const searchedResults = response;
		
		const tbody = document.querySelector('tbody#groupAuthorization');
	    tbody.innerHTML = '';
	    
		document.querySelector('tbody#allGroupTbody').innerHTML = '';

		searchedResults.forEach(function(searchedResult, index) {
		    const tr = document.createElement('tr');
		    tr.id = 'groupRow_' + searchedResult.groupId;
		    tr.innerHTML = `
		        <td scope="row">${index + 1}</td>
		        <td name="groupId">${searchedResult.groupId}</td>
		        <td name="groupName">${searchedResult.groupName}</td>
		    `;
		    document.querySelector('tbody#allGroupTbody').appendChild(tr);
		    
		    const detailTr = document.createElement('tr');
		    detailTr.id = 'groupRow_detail_' + searchedResult.groupId;
		    detailTr.style.display = 'none';
		    detailTr.innerHTML = `
		    	<td colspan="3" id="detail_content_${searchedResult.groupId}" style="display:none;">
	                <div class="card-header d-flex align-items-center justify-content-between">
	                    <div class="col">
	                      <h5 class="mb-0">그룹 정보</h5>
	                    </div>
	                    <div class="col-auto">
	                      <button class="btn btn-secondary btn-lg" type="button" id="updateGroupBtn_${searchedResult.groupId}">수정</button>
	        			  <button class="btn btn-secondary btn-lg" type="button" id="addIndividualToGroupBtn_${searchedResult.groupId}">추가/삭제</button>
	   					</div>
	                </div>
	                <div class="card-body">
	                  <form>
	                  <div class="row mb-3">
	                      <label class="col-sm-2 col-form-label" for="basic-icon-default-description">설명</label>
	                      <div class="col-sm-10">
	                        <div class="input-group input-group-merge">
	                          <input
	                            type="text"
	                            id="basic-icon-default-description_${searchedResult.groupId}"
	                            class="form-control"
	                            readonly
	                          />
	                        </div>
	                      </div>
	                    </div>
	                    <div class="row mb-3">
	                      <label class="col-sm-2 col-form-label" for="basic-icon-default-individual">사용자</label>
	                      <div class="col-sm-10">
	                        <div class="input-group input-group-merge">
							  <div class="btn-group" id="memberTagBtns_${searchedResult.groupId}" role="group" style="gap: 10px; flex-wrap: wrap;">
							  </div>
                            </div>
                          </div>
                        </div>
                      </form>
                    </div>
	    		  </td>
		    `
		    document.querySelector('tbody#allGroupTbody').appendChild(detailTr);
		});
		getRowClickEvent();
    },
    error: function(error) {
        showAlert("검색에 실패했습니다.");
    }
	});
})

// '그룹 정보 수정' 모달창 닫힐 때 , 모달 내용 초기화
function resetupdateGroupInfoModal(){
	document.getElementById('newGroupName').value = '';
	document.getElementById('newGroupDescription').value = '';
}

// '사용자 추가' 모달 아이콘 'X' 클릭할 때, 모달 내용 초기화
document.getElementById('closeIndividualSearchModalIcon').addEventListener('click',() => {
	resetIndividualSearchModal();
})

// '사용자 추가' 모달 '닫기' 클릭할 때, 모달 내용 초기화
document.getElementById('closeIndividualSearchModal').addEventListener('click',() => {
	resetIndividualSearchModal();
})

// '사용자 추가' 모달창 닫힐 때 , 모달 내용 초기화
function resetIndividualSearchModal(){
	document.getElementById('targetGroupId').value = '';
	document.getElementById('targetUserName').value = '';
	document.getElementById('userNameSearchResultTbody').innerHTML = '';
}
                       
