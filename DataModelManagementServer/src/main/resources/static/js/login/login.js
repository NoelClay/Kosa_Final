function checkCookieOfId(key) {
    const cookies = document.cookie.split(';');
    for (let i = 0; i < cookies.length; i++) {
        let cookie = cookies[i].trim();
        if (cookie.indexOf(key + '=') === 0) {
            return true;
        }
    }
    return false;
}

function deleteCookies(){
	const cookies = document.cookie.split(";");

    for (let i = 0; i < cookies.length; i++) {
        const cookie = cookies[i];
        const eqPos = cookie.indexOf("=");
        const name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
        document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT;path=/";
    }
}

$(document).ready(function() {
            // 페이지 로딩 시 비동기로 세션 값을 가져옴
            const isMemberKeyExist = checkCookieOfId('memberKey');
            if(!isMemberKeyExist){
				deleteCookies();
				window.location.href = "/dms/";
			}
            $.ajax({
                type: "POST",
                url: "/dms/loginSession",
                success: function(data) {
					const currentPath = window.location.pathname;
					const logoutBtn = document.getElementById('logoutBtn');
					
					if(currentPath !== '/dms/authorization-individual/list' && currentPath !== '/dms/authorization-group/list' && currentPath !== '/dms/member-profile/form'){
						document.getElementById('userId').innerHTML = `${data.id}`;
					}
					
				    const selectElement = document.getElementById("selectStandardArea");
				    if (selectElement && selectElement.length === 1 && !selectElement[0].value) {
						deleteCookies();
				        window.location.href = "/dms/";
					}
					
					if(data.id === null){
						deleteCookies();
						window.location.href = "/dms/";
					}
					
					if(currentPath !== '/dms/authorization-individual/list' && currentPath !== '/dms/authorization-group/list' && currentPath !== '/dms/standardArea/page' && currentPath !== '/dms/standardArea/managementPage' && currentPath !== '/dms/member-profile/form'){
						const subjArea = document.getElementById("selectStandardArea").value.trim();
						if(data.subjAreaId === null || subjArea === null || subjArea === ''){
							$('.toast').toast('show');
						}
					}
					
					if(data.id === 'admin' && currentPath === '/dms/authorization-individual/list'){
						const adminArea = document.getElementById('adminArea');
						adminArea.innerHTML = `
							<li class="menu-header small text-uppercase"><span class="menu-header-text">권한 관리</span></li>
		                    <li class="menu-item avtive open" style="margin-left: 15px">
		                    <a href="javascript:void(0);" class="menu-link menu-toggle">
		                        <i class="menu-icon tf-icons bx bx-detail"></i>
		                        <div data-i18n="Account Settings" style="margin-right: 116px">권한</div>
		                    </a>
		                    <ul class="menu-sub">
		                        <li class="menu-item active">
		                            <a href="/dms/authorization-individual/list" class="menu-link">
		                                <div data-i18n="Account">개인별 권한</div>
		                            </a>
		                        </li>
		                        <li class="menu-item">
		                            <a href="/dms/authorization-group/list" class="menu-link">
		                                <div data-i18n="Notifications">그룹별 권한</div>
		                            </a>
		                        </li>
		                    </ul>
						`;
					}else if(data.id === 'admin' && currentPath === '/dms/authorization-group/list'){
						const adminArea = document.getElementById('adminArea');
						adminArea.innerHTML = `
							<li class="menu-header small text-uppercase"><span class="menu-header-text">권한 관리</span></li>
		                    <li class="menu-item avtive open" style="margin-left: 15px">
		                    <a href="javascript:void(0);" class="menu-link menu-toggle">
		                        <i class="menu-icon tf-icons bx bx-detail"></i>
		                        <div data-i18n="Account Settings" style="margin-right: 116px">권한</div>
		                    </a>
		                    <ul class="menu-sub">
		                        <li class="menu-item">
		                            <a href="/dms/authorization-individual/list" class="menu-link">
		                                <div data-i18n="Account">개인별 권한</div>
		                            </a>
		                        </li>
		                        <li class="menu-item active">
		                            <a href="/dms/authorization-group/list" class="menu-link">
		                                <div data-i18n="Notifications">그룹별 권한</div>
		                            </a>
		                        </li>
		                    </ul>
						`;
					}else if(data.id === 'admin') {
						getAuthorizedAreaForAdmin();
						const adminArea = document.getElementById('adminArea');
						adminArea.innerHTML = `
							<li class="menu-header small text-uppercase"><span class="menu-header-text">권한 관리</span></li>
		                    <li class="menu-item" style="margin-left: 15px">
		                    <a href="javascript:void(0);" class="menu-link menu-toggle">
		                        <i class="menu-icon tf-icons bx bx-detail"></i>
		                        <div data-i18n="Account Settings" style="margin-right: 116px">권한</div>
		                    </a>
		                    <ul class="menu-sub">
		                        <li class="menu-item">
		                            <a href="/dms/authorization-individual/list" class="menu-link">
		                                <div data-i18n="Account">개인별 권한</div>
		                            </a>
		                        </li>
		                        <li class="menu-item">
		                            <a href="/dms/authorization-group/list" class="menu-link">
		                                <div data-i18n="Notifications">그룹별 권한</div>
		                            </a>
		                        </li>
		                    </ul>
						`;
						chageMenuByCurrentPath(currentPath);
						
					} else if(data.authTpCd === 'W') {
						getAuthorizedAreaForWrite();
						chageMenuByCurrentPath(currentPath);

					}
					logoutBtn.innerHTML = `
						<i class="bx bx-power-off me-2" style="padding-left: 10px;"></i>
                        <span class="align-middle" id="logout">Log Out (${data.id})</span>
					`;
                },
                error: function(data) {
					deleteCookies();
                    window.location.href = "/dms/";
                }
            });
        });
        
function getAuthorizedAreaForAdmin(){
	document.getElementById('authorizedArea').innerHTML = `
		<li class="menu-header small text-uppercase"><span class="menu-header-text">데이터 표준 등록</span></li>
		<li class="menu-item" id="wordMenuItem" style="margin-left: 15px">
	        <a href="javascript:void(0);" class="menu-link menu-toggle">
	            <i class="menu-icon tf-icons bx bx-detail"></i>
	            <div data-i18n="Account Settings" style="margin-right: 80px">표준 단어</div>
	        </a>
	        <ul class="menu-sub">
	            <li class="menu-item" id="singleWordMenuItem">
	                <a href="/dms/single-word/form" class="menu-link">
	                    <div data-i18n="Account">등록</div>
	                </a>
	            </li>
	            <li class="menu-item" id="bulkWordMenuItem">
	                <a href="/dms/bulk-word" class="menu-link">
	                    <div data-i18n="Notifications">일괄등록</div>
	                </a>
	            </li>
	        </ul>
		</li>
	
	    <li class="menu-item" id="domainMenuItem" style="margin-left: 15px">
	        <a href="javascript:void(0);" class="menu-link menu-toggle">
	            <i class="menu-icon tf-icons bx bx-detail"></i>
	            <div data-i18n="Account Settings">도메인</div>
	        </a>
	        <ul class="menu-sub" id="singleDomainMenuItem">
	            <li class="menu-item">
	                <a href="/dms/single-domain/form" class="menu-link">
	                    <div data-i18n="Account">등록</div>
	                </a>
	            </li>
	            <li class="menu-item" id="bulkDomainMenuItem">
	                <a href="/dms/bulk-domain" class="menu-link">
	                    <div data-i18n="Notifications">일괄등록</div>
	                </a>
	            </li>
	        </ul>
	    </li>
	
	    <li class="menu-item" id="termMenuItem" style="margin-left: 15px">
	        <a href="javascript:void(0);" class="menu-link menu-toggle">
	            <i class="menu-icon tf-icons bx bx-detail"></i>
	            <div data-i18n="Account Settings">용어</div>
	        </a>
	        <ul class="menu-sub">
	            <li class="menu-item" id="singleTermMenuItem">
	                <a href="/dms/term" class="menu-link">
	                    <div data-i18n="Account">등록</div>
	                </a>
	            </li>
	            <li class="menu-item" id="bulkTermMenuItem">
	                <a href="/dms/bulk-term" class="menu-link">
	                    <div data-i18n="Notifications">일괄등록</div>
	                </a>
	            </li>
	        </ul>
	    </li>
	
	    <li class="menu-item" id="subjAreaMenuItem" style="margin-left: 15px">
	        <a href="javascript:void(0);" class="menu-link menu-toggle">
	            <i class="menu-icon tf-icons bx bx-detail"></i>
	            <div data-i18n="Account Settings">프로젝트 관리</div>
	        </a>
	        <ul class="menu-sub">
	            <li class="menu-item" id="subjAreaInsertMenuItem">
	                <a href="/dms/standardArea/page" class="menu-link">
	                    <div data-i18n="Account">등록</div>
	                </a>
	            </li>
	            <li class="menu-item" id="subjAreaManagementMenuItem">
	                <a href="/dms/standardArea/managementPage" class="menu-link">
	                    <div data-i18n="Notifications">관리</div>
	                </a>
	            </li>
	        </ul>
	    </li>`
}

function getAuthorizedAreaForWrite(){
	document.getElementById('authorizedArea').innerHTML = `
		<li class="menu-header small text-uppercase"><span class="menu-header-text">데이터 표준 등록</span></li>
		<li class="menu-item" id="wordMenuItem" style="margin-left: 15px">
	        <a href="javascript:void(0);" class="menu-link menu-toggle">
	            <i class="menu-icon tf-icons bx bx-detail"></i>
	            <div data-i18n="Account Settings" style="margin-right: 80px">표준 단어</div>
	        </a>
	        <ul class="menu-sub">
	            <li class="menu-item" id="singleWordMenuItem">
	                <a href="/dms/single-word/form" class="menu-link">
	                    <div data-i18n="Account">등록</div>
	                </a>
	            </li>
	            <li class="menu-item" id="bulkWordMenuItem">
	                <a href="/dms/bulk-word" class="menu-link">
	                    <div data-i18n="Notifications">일괄등록</div>
	                </a>
	            </li>
	        </ul>
		</li>
	
	    <li class="menu-item" id="domainMenuItem" style="margin-left: 15px">
	        <a href="javascript:void(0);" class="menu-link menu-toggle">
	            <i class="menu-icon tf-icons bx bx-detail"></i>
	            <div data-i18n="Account Settings">도메인</div>
	        </a>
	        <ul class="menu-sub">
	            <li class="menu-item" id="singleDomainMenuItem">
	                <a href="/dms/single-domain/form" class="menu-link">
	                    <div data-i18n="Account">등록</div>
	                </a>
	            </li>
	            <li class="menu-item" id="bulkDomainMenuItem">
	                <a href="/dms/bulk-domain" class="menu-link">
	                    <div data-i18n="Notifications">일괄등록</div>
	                </a>
	            </li>
	        </ul>
	    </li>
	
	    <li class="menu-item" id="termMenuItem" style="margin-left: 15px">
	        <a href="javascript:void(0);" class="menu-link menu-toggle">
	            <i class="menu-icon tf-icons bx bx-detail"></i>
	            <div data-i18n="Account Settings">용어</div>
	        </a>
	        <ul class="menu-sub">
	            <li class="menu-item" id="singleTermMenuItem">
	                <a href="/dms/term" class="menu-link">
	                    <div data-i18n="Account">등록</div>
	                </a>
	            </li>
	            <li class="menu-item" id="bulkTermMenuItem">
	                <a href="/dms/bulk-term" class="menu-link">
	                    <div data-i18n="Notifications">일괄등록</div>
	                </a>
	            </li>
	        </ul>
	    </li>
	`
}

function addActiveAndOpenInWordMenuItem(){
	const wordMenuItem = document.getElementById('wordMenuItem');
    wordMenuItem.classList.add('active', 'open');
}

function addActiveInSingleWordMenuItem(){
	const singleWordMenuItem = document.getElementById('singleWordMenuItem');
    singleWordMenuItem.classList.add('active');
}
        
function addActiveInBulkWordMenuItem(){
	const bulkWordMenuItem = document.getElementById('bulkWordMenuItem');
    bulkWordMenuItem.classList.add('active');
}

function addActiveAndOpenInDomainMenuItem(){
	const domainMenuItem = document.getElementById('domainMenuItem');
    domainMenuItem.classList.add('active', 'open');
}

function addActiveInSingleDomainMenuItem(){
	const singleDomainMenuItem = document.getElementById('singleDomainMenuItem');
    singleDomainMenuItem.classList.add('active');
}
        
function addActiveInBulkDomainMenuItem(){
	const bulkDomainMenuItem = document.getElementById('bulkDomainMenuItem');
    bulkDomainMenuItem.classList.add('active');
}

function addActiveAndOpenInTermMenuItem(){
	const termMenuItem = document.getElementById('termMenuItem');
    termMenuItem.classList.add('active', 'open');
}

function addActiveInSingleTermMenuItem(){
	const singleTermMenuItem = document.getElementById('singleTermMenuItem');
    singleTermMenuItem.classList.add('active');
}
        
function addActiveInBulkTermMenuItem(){
	const bulkTermMenuItem = document.getElementById('bulkTermMenuItem');
    bulkTermMenuItem.classList.add('active');
}

function addActiveAndOpenInTermMenuItem(){
	const termMenuItem = document.getElementById('termMenuItem');
    termMenuItem.classList.add('active', 'open');
}

function addActiveInSingleTermMenuItem(){
	const singleTermMenuItem = document.getElementById('singleTermMenuItem');
    singleTermMenuItem.classList.add('active');
}
        
function addActiveInBulkTermMenuItem(){
	const bulkTermMenuItem = document.getElementById('bulkTermMenuItem');
    bulkTermMenuItem.classList.add('active');
}

function addActiveAndOpenInSubjAreaMenuItem(){
	const subjAreaMenuItem = document.getElementById('subjAreaMenuItem');
    subjAreaMenuItem.classList.add('active', 'open');
}

function addActiveInSubjAreaInsertMenuItem(){
	const subjAreaInsertMenuItem = document.getElementById('subjAreaInsertMenuItem');
    subjAreaInsertMenuItem.classList.add('active');
}
        
function addActiveInSubjAreaManagementMenuItem(){
	const subjAreaManagementMenuItem = document.getElementById('subjAreaManagementMenuItem');
    subjAreaManagementMenuItem.classList.add('active');
}

function chageMenuByCurrentPath(currentPath){
	switch (currentPath) {
    case '/dms/single-word/form':
		addActiveAndOpenInWordMenuItem();
		addActiveInSingleWordMenuItem();
		break;
    case '/dms/bulk-word':
	case '/dms/bulk-word/display':
		addActiveAndOpenInWordMenuItem();
		addActiveInBulkWordMenuItem();
		break;
    case '/dms/single-domain/form':
		addActiveAndOpenInDomainMenuItem();
		addActiveInSingleDomainMenuItem();
		break;
	case '/dms/bulk-domain':
	case '/dms/bulk-domain/display':
		addActiveAndOpenInDomainMenuItem();
		addActiveInBulkDomainMenuItem();
		break;
	case '/dms/term':
		addActiveAndOpenInTermMenuItem();
		addActiveInSingleTermMenuItem();
		break;
	case '/dms/bulk-term':
	case '/dms/bulk-term/display':
		addActiveAndOpenInTermMenuItem();
		addActiveInBulkTermMenuItem();
		break;
	case '/dms/standardArea/page':
		addActiveAndOpenInSubjAreaMenuItem();
		addActiveInSubjAreaInsertMenuItem();
		break;
	case '/dms/standardArea/managementPage':
		addActiveAndOpenInSubjAreaMenuItem();
		addActiveInSubjAreaManagementMenuItem()
        break;
    default:
        break;
	}
}
    
// 로그아웃 버튼 클릭 시, 이벤트
document.getElementById('logoutBtn').addEventListener('click', () => {
    logout();
});
document.getElementById('logoutOfDropdownBtn').addEventListener('click', () => {
    logout();
});

function logout(){
	$.ajax({
        type: "GET",
        url: "/dms/logout",
        success: function(data) {
			if(data.result === 'success'){
				deleteCookies();
	            window.location.href = "/dms/";
			}else{
				window.location.href = "/dms/";
			}
        },
        error: function() {
			window.location.href = "/dms/";
        }
    });
}




