document.addEventListener('DOMContentLoaded', function() {
	getUserDetail();
});

function getUserDetail(){
	$.ajax({
		type: "POST",
        url: "/dms/loginSession",
        success: function(data){
			getUserDetailInfo(data.id);
		},
		error: function(data){
			showAlert("사용자 정보를 불러오는데 실패했습니다.");
		}
	});
}

function getUserDetailInfo(userId){
	$.ajax({
		type: "POST",
        url: "/dms/authorization-individual/user-detail",
        contentType: "application/json",
		data: userId,
        success: function(data){
			document.getElementById('basic-default-userId').value = data.usrId;
			document.getElementById('basic-default-userName').value = data.usrName;
			document.getElementById('basic-default-email').value = data.usrEmail;
		},
		error: function(error){
			showAlert("사용자 정보를 불러오는데 실패했습니다.");
		}
	});
}

document.getElementById('updatePasswordBtn').addEventListener('click', () => {
	const updatePasswordModal = new bootstrap.Modal(document.getElementById('updatePasswordModal'));
    updatePasswordModal.show();
})

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
	const targetId = document.getElementById('basic-default-userId').value;
	const currentPassword = document.getElementById('currentPassword').value;
    const updatePassword = document.getElementById('updatePassword').value;
    const validatePassword = document.getElementById('validatePassword').value;
    validateUpdateInput(targetId, currentPassword, updatePassword, validatePassword);
})

function validateUpdateInput(targetId, currentPassword, updatePassword, validatePassword){
	if(currentPassword === ''){
		showAlert('현재 비밀번호를 입력해 주세요.');
		return;
	}
	
	if(updatePassword === '' || validatePassword === ''){
		showAlert('새 비밀번호를 입력해 주세요.');
		return;
	}
	
	if(updatePassword || validatePassword) {
	    if(document.getElementById('passwordValidationRow').style.display === 'none') {
	      showAlert('새 비밀번호와 확인 비밀번호가 같은지 확인하려면 확인 버튼을 눌러주세요.');
	      return;
	    }
    
	    if(document.getElementById('passwordValidationMessage').textContent === '비밀번호가 일치하지 않습니다. 다시 입력해 주세요.') {
	      if(updatePassword === validatePassword){
			  showAlert('새 비밀번호와 확인 비밀번호가 같은지 확인하려면 확인 버튼을 눌러주세요.');
			  return;
		  }
	      showAlert('새 비밀번호와 확인 비밀번호가 일치하지 않습니다. 다시 확인해 주세요.');
	      return;
	    }
	}
  	updateUserPassword(targetId, currentPassword, updatePassword);
}

function updateUserPassword(targetId, currentPassword, updatePassword){
	
	updateUserPasswordDTO = {
			usrId: targetId,
			usrCurrentPassword: currentPassword,
            usrUpdatePassword: updatePassword
	};
	
	$.ajax({
	    type: "POST",
	    url: "/dms/authorization-individual/update-user-password",
	    contentType: "application/json",
	    data: JSON.stringify(updateUserPasswordDTO),
	    success: function(response) {
			if(response === 'updateSuccess'){
				showAlert('비밀번호가 수정되었습니다.', 'success');
				setTimeout(function() {
			        window.location.reload();
			    }, 1000);
			}else if(response === 'incorrect_password'){
				showAlert('입력하신 현재 비밀번호가 틀립니다. 정확한 비밀번호를 입력해 주세요.');
			}else {
				showAlert("사용자 정보를 수정하는데 실패했습니다.");
			}
	    },
	    error: function(error) {
	        showAlert("사용자 정보를 수정하는데 실패했습니다.");
	    }
	});
}

// '비밀번호 수정' 모달창 닫힐 때 , 모달 내용 초기화
function resetUpdatePasswordModal(){
	document.getElementById('currentPassword').value = '';
	document.getElementById('updatePassword').value = '';
	document.getElementById('validatePassword').value = '';
}

