function getRowClickEvent() {
	const rows = document.querySelectorAll('tr[id^=individualUserRow]');
    
    rows.forEach(function(row) {
        row.addEventListener('click', function() {
            const userIdCell = row.querySelector('td[name="usrId"]');
            if (userIdCell) {
                const userId = userIdCell.textContent;
                getDetail(userId);
            }
        });
    });
}

document.addEventListener('DOMContentLoaded', function() {
	getRowClickEvent();
});

function getDetail(userId) {
	$.ajax({
	type: "POST",
	url: "/dms/authorization-individual/detail",
	contentType: "application/json",
	data: userId,
	success: function (individualAuthorizationDetailDTO) {
		const userEmail = individualAuthorizationDetailDTO.individualUserDTO.usrEmail;
	    const userPassword = individualAuthorizationDetailDTO.individualUserDTO.usrPassword;
	    const useYn = individualAuthorizationDetailDTO.individualUserDTO.useYn;

	    const tableDetailRow = document.getElementById('individualUserRow_detail_' + `${userId}`);
	    const detail = document.getElementById('detail_content_' + `${userId}`);
	    
	    if(tableDetailRow.style.display === 'none'){
			tableDetailRow.style.display = 'table-row';
			detail.style.display = 'table-cell';
			
			const detailRows = document.querySelectorAll('tr[id^=individualUserRow_detail_]');
			Array.from(detailRows).filter(detailRow => {
				const id = detailRow.id;
				if(!id.endsWith(userId) && detailRow.style.display === 'table-row'){
					detailRow.style.display = 'none';
				}
			});
			
			const contentRows = document.querySelectorAll('tr[id^=detail_content_]');
			Array.from(contentRows).filter(contentRow => {
				const id = contentRow.id;
				if(!id.endsWith(userId) && contentRow.style.display === 'table-row'){
					contentRow.style.display = 'none';
				}
			});

			document.getElementById('basic-icon-default-email_' + `${userId}`).value = userEmail;
			// document.getElementById('basic-icon-default-password_' + `${userId}`).value = userPassword;
			// document.getElementById('basic-icon-default-useYn_' + `${userId}`).value = useYn;
		
			getUpdateButton(individualAuthorizationDetailDTO.individualUserDTO);
		} else {
			tableDetailRow.style.display = 'none';
			detail.style.display = 'none';			
		}
	    
	    const tbody = document.querySelector('tbody#IndividualAuthorization');
	    tbody.innerHTML = '';
	    
	    individualAuthorizationDetailDTO.individualAuthorizationByStdjAreaIdDTOList.forEach(function(individualAuthorizationByStdjAreaIdDTO, index) {
	      const tr = document.createElement('tr');
	      tr.id = "individualAuthRows";
	     
	      tr.innerHTML = `
	        <input type="hidden" id="subjAreaId" value="${individualAuthorizationByStdjAreaIdDTO.subjAreaId}"/>
	        <input type="hidden" id="targetUserId" value="${userId}"/>
	        <td scope="row">${index + 1}</td>
	        <td name="subjAreaName">${individualAuthorizationByStdjAreaIdDTO.subjAreaName}</td>
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
	                ${individualAuthorizationByStdjAreaIdDTO.authTpCd === 'R' ? 'checked' : ''}
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
	                ${individualAuthorizationByStdjAreaIdDTO.authTpCd === 'W' ? 'checked' : ''}
	              />
	              <label class="form-check-label" for="inlineRadio2_${index}">W</label>
	            </div>
	          </div></td>
	      `;
	      tbody.appendChild(tr);
	     });
	     getUpdateAuthBtn(userId);
	},
	error: function (error) {
	    showAlert("사용자 정보를 조회하는데 실패했습니다.");
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

function getUpdateButton(individualUserDTO){
	document.getElementById('updateUserBtn_' + `${individualUserDTO.usrId}`).addEventListener('click', () => {
	const userId = individualUserDTO.usrId;
	const userEmail = individualUserDTO.usrEmail;
	const useYn = individualUserDTO.useYn;
	const userPassword = individualUserDTO.usrPassword;
	const updateIndividualInfoModal = new bootstrap.Modal(document.getElementById('updateIndividualInfoModal'));
    updateIndividualInfoModal.show();
    
    // 현재 비밀번호 show or hide
	document.getElementById('toggle-password-icon-currentPassword').addEventListener('click', function () {
	  const passwordInput = document.getElementById('currentPassword');
	  const eyeIcon = document.getElementById('eyeIcon-currentPassword');
	
	  showOrHidePassword([passwordInput], eyeIcon);
	});
    
    // 새 비밀번호 show or hide
	document.getElementById('toggle-password-icon-update').addEventListener('click', function () {
	  const passwordInput = document.getElementById('updatePassword');
	  const validatePasswordInput = document.getElementById('validatePassword');
	  const eyeIcon = document.getElementById('eyeIcon-update');
	
	  showOrHidePassword([passwordInput, validatePasswordInput], eyeIcon);
	});

    document.getElementById('targetIdForUpdate').value = userId;
    document.getElementById('currentEmail').value = userEmail;
    document.getElementById('currentPassword').value = userPassword;
    document.getElementById('updateUseYn').value = useYn;    
	});
}

document.getElementById('validatePasswordBtn').addEventListener('click', () => {
    const updatePassword = document.getElementById('updatePassword').value;
    const validatePassword = document.getElementById('validatePassword').value;
    if(updatePassword === validatePassword){
		document.getElementById('passwordValidationRow').style.display = 'block';
		document.getElementById('passwordValidationRow').style.color = 'lightblue';
		document.getElementById('passwordValidationMessage').textContent = '비밀번호가 일치합니다.';
	} else{
		document.getElementById('passwordValidationRow').style.display = 'block';
		document.getElementById('passwordValidationRow').style.color = 'lightpink';
		document.getElementById('passwordValidationMessage').textContent = '비밀번호가 일치하지 않습니다. 다시 입력해 주세요.';
	}
})

document.getElementById('updateUserInfoBtn').addEventListener('click', () => {
	const targetId = document.getElementById('targetIdForUpdate').value;
	const updateEmail = document.getElementById('updateEmail').value;
    const updatePassword = document.getElementById('updatePassword').value;
    const validatePassword = document.getElementById('validatePassword').value;
    const updateUseYn = document.getElementById('updateUseYn').value;
    validateUpdateInput(targetId, updateEmail, updatePassword, validatePassword, updateUseYn);
})

function validateUpdateInput(targetId, updateEmail, updatePassword, validatePassword, updateUseYn){
	if (updatePassword || validatePassword) {
	    if (document.getElementById('passwordValidationRow').style.display === 'none') {
	      showAlert('새 비밀번호와 확인 비밀번호가 같은지 확인하려면 확인 버튼을 눌러주세요.');
	      return;
	    }
    
	    if (document.getElementById('passwordValidationMessage').textContent === '비밀번호가 일치하지 않습니다. 다시 입력해 주세요.') {
	      if(updatePassword === validatePassword){
			  showAlert('새 비밀번호와 확인 비밀번호가 같은지 확인하려면 확인 버튼을 눌러주세요.');
			  return;
		  }
	      showAlert('새 비밀번호와 확인 비밀번호가 일치하지 않습니다. 다시 확인해 주세요.');
	      return;
	    }
	}
  	updateIndividual(targetId, updateEmail, updatePassword, updateUseYn);
}

function updateIndividual(targetId, updateEmail, updatePassword, updateUseYn){
	
	individualUserDTO = {
			usrId: targetId,
			usrEmail: updateEmail,
            usrPassword: updatePassword,
            useYn: updateUseYn
	};
	
	$.ajax({
	    type: "POST",
	    url: "/dms/authorization-individual/update-user-info",
	    contentType: "application/json",
	    data: JSON.stringify(individualUserDTO),
	    success: function(response) {
			if(response.isUpdateState === 'updateSuccess'){
				showAlert('사용자 정보가 수정되었습니다.', 'success');
				setTimeout(function() {
			        window.location.reload();
			    }, 1000);
			} else {
				showAlert("사용자 정보를 수정하는데 실패했습니다.");
			}
	    },
	    error: function(error) {
	        showAlert("사용자 정보를 수정하는데 실패했습니다.");
	    }
	});
}

function showOrHidePassword(passwordFields, eyeIcon) {
  const allPasswordsVisible = passwordFields.every(
    (field) => field.type === 'text'
  );

  passwordFields.forEach((passwordField) => {
    passwordField.type = allPasswordsVisible ? 'password' : 'text';
  });

  // 아이콘 토글
  if (allPasswordsVisible) {
    eyeIcon.classList.replace('bi-eye', 'bi-eye-slash');
  } else {
    eyeIcon.classList.replace('bi-eye-slash', 'bi-eye');
  }

}

function getUpdateAuthBtn(targetUserId){
	const updateAuthBtn = document.getElementById('updateAuthBtn');
	updateAuthBtn.onclick = null;
	updateAuthBtn.onclick = () => {
		showLoadingSpinner()
		const values = [];
		
		const individualAuthRows = document.querySelectorAll('[id="individualAuthRows"]');
		individualAuthRows.forEach(row => {
		    const subjAreaId = row.querySelector('[id^="subjAreaId"]').value;
		    const usrId = document.getElementById('targetUserId').value;
		    const radios = row.querySelectorAll('[name^="inlineRadioOptions"]');
		    
		    radios.forEach(radio => {
		        if (radio.checked) {
		            values.push({
		                subjAreaId: subjAreaId,
		                usrId: usrId,
		                authTpCd: radio.value
		            });
		        }
		    });
		});
		
		const updatedValues = {
			usrId: targetUserId,
			individualAuthorizationByStdjAreaIdDTOList: values
		};
	
		$.ajax({
		    type: "POST",
		    url: "/dms/authorization-individual/update",
		    contentType: "application/json",
		    data: JSON.stringify(updatedValues),
		    success: function(data) {
				hideLoadingSpinner();
				const usrId = data;
		        if (usrId !== 'updateFailure') {
					getDetail(usrId);
				} else {
					showAlert("프로젝트별 사용자 권한을 수정하는데 실패했습니다.");
				}
		    },
		    error: function(error) {
		        showAlert("프로젝트별 사용자 권한을 수정하는데 실패했습니다.");
		    }
		});
	}
}

const insertNewIndividualBtn = document.getElementById('insertNewIndividualBtn');
insertNewIndividualBtn.addEventListener('click', () => {
	const insertNewIndividualModal = new bootstrap.Modal(document.getElementById('insertNewIndividualModal'));
	getAllSubjArea();
	
	// 비밀번호 show or hide
	document.getElementById('toggle-password-icon-userPassword').addEventListener('click', function () {
	  const passwordInput = document.getElementById('userPassword');
	  const eyeIcon = document.getElementById('eyeIcon-userPassword');
	
	  showOrHidePassword([passwordInput], eyeIcon);
	});
	
    insertNewIndividualModal.show();
    
})

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
    const tableBody = document.getElementById("newIndividualAuthorization");
    
    if (tableBody.querySelector(`input[name="subjAreaId"][value="${subjAreaId}"]`)) {
    return;
    }
    
    const newRow = document.createElement("tr");
    newRow.id = "newIndividualAuthRows";
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

const insertNewSubjAreaAuthBtn = document.getElementById('insertNewSubjAreaAuthBtn');
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
});


// 검색창 enter키 입력해도 검색 진행
const inputOfUserSearch = document.getElementById('searchForUser');
inputOfUserSearch.addEventListener("keydown", function(e){
	if(e.key === 'Enter'){
		document.getElementById('searchForUserBtn').click();
	}
})

document.getElementById('searchForUserBtn').addEventListener('click', () => {
	showLoadingSpinner()
	const searchCondition = document.getElementById('searchCondition').value;
	const searchValue = document.getElementById('searchForUser').value;
	
	if(searchCondition === '' || searchCondition === null){
		hideLoadingSpinner();
		showAlert('검색 조건을 선택해 주세요.');
		return;
	}
	
	$.ajax({
    type: "POST",
    url: "/dms/authorization-individual/search-user",
    contentType: "application/json",
    data: JSON.stringify({
		searchCondition: searchCondition,
		searchValue: searchValue
	}),
    success: function(response) {
		hideLoadingSpinner();
		showAlert("검색이 완료되었습니다.", "success");
		const searchedResults = response;
		
		const tbody = document.querySelector('tbody#IndividualAuthorization');
	    tbody.innerHTML = '';
	    
		document.querySelector('tbody#allIndividualUserTbody').innerHTML = '';

		searchedResults.forEach(function(searchedResult, index) {
		    const tr = document.createElement('tr');
		    tr.id = 'individualUserRow_' + searchedResult.usrId;
		    tr.innerHTML = `
		        <td scope="row">${index + 1}</td>
		        <td name="usrId">${searchedResult.usrId}</td>
		        <td name="usrName">${searchedResult.usrName}</td>
		        <td name="usrYn">${searchedResult.useYn}</td>
		    `;
		    document.querySelector('tbody#allIndividualUserTbody').appendChild(tr);
		    
		    const detailTr = document.createElement('tr');
		    detailTr.id = 'individualUserRow_detail_' + searchedResult.usrId;
		    detailTr.style.display = 'none';
		    detailTr.innerHTML = `
		    	<td colspan="4" id="detail_content_${searchedResult.usrId}" style="display:none;">
	                <div class="card-header d-flex align-items-center justify-content-between">
	                    <div class="col">
	                      <h5 class="mb-0">사용자 정보</h5>
	                    </div>
	                    <div class="col-auto">
	        			  <button class="btn btn-secondary btn-lg" type="button" id="updateUserBtn_${searchedResult.usrId}">수정</button>
	   					</div>
	                </div>
	                <div class="card-body">
	                  <form>
	                  <div class="row mb-3">
	                      <label class="col-sm-2 col-form-label" for="basic-icon-default-email">이메일</label>
	                      <div class="col-sm-10">
	                        <div class="input-group input-group-merge">
	                          <input
	                            type="text"
	                            id="basic-icon-default-email_${searchedResult.usrId}"
	                            class="form-control"
	                            placeholder="이메일"
	                            aria-label="john.doe"
	                            aria-describedby="basic-icon-default-email2"
	                            readonly
	                          />
	                        </div>
	                      </div>
	                    </div>
	                  </form>
	                </div>
	    		  </td>
		    `
		    document.querySelector('tbody#allIndividualUserTbody').appendChild(detailTr);
		});
		getRowClickEvent();
    },
    error: function(error) {
        showAlert("검색에 실패했습니다.");
    }
	});
})

document.getElementById('userIdValidation').addEventListener('click', () => {
	const userId = document.getElementById('userId').value;
	$.ajax({
	    type: "POST",
	    url: "/dms/authorization-individual/validation-userId",
	    contentType: "application/json",
	    data: userId,
	    success: function(response) {
	        if (response) {
				showAlert("중복된 아이디 입니다.");
			} else {
				showAlert("해당 아이디는 사용가능합니다.", "success");
			}
	    },
	    error: function(error) {
	        showAlert("중복 검사에 실패하였습니다.");
	    }
	});
})                       
