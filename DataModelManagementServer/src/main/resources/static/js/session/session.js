document.addEventListener('DOMContentLoaded', function () {
    fetchDataFromServer();
});
const selectElement = document.getElementById("selectStandardArea");

function fetchDataFromServer() {
    sendAjaxRequest(
        "/dms/session/get",
        null,
        function (response) {
            selectOptionBasedOnData(response);
        }
    )
}

function handleSelectChange() {
    const selectedStandardArea = selectElement.options[selectElement.selectedIndex].text
    sendAjaxRequest(
        "/dms/session/set",
        selectedStandardArea,
        function (response) {
            if(response) {
				getLoginSession();
				getDomainGroup();
			}
        }
    )
}

function selectOptionBasedOnData(selectedOption) {
    for (var i = 0; i < selectElement.options.length; i++) {
        if (selectElement.options[i].value === selectedOption) {
            selectElement.selectedIndex = i;
            break;
        }
    }
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

function getLoginSession() {
    const isMemberKeyExist = checkCookieOfId('memberKey');
    if(!isMemberKeyExist){
		deleteCookies();
		window.location.href = "/dms/";
	}
	sendAjaxRequest(
        "/dms/loginSession",
        null,
        function (data) {
			if (selectElement && selectElement.length === 1 && !selectElement[0].value) {
				deleteCookies();
	        	window.location.href = "/dms/";
			}
			
			if(data.id === null){
				deleteCookies();
				window.location.href = "/dms/";
			}
			
			if(data.id === 'admin'){
				const currentPath = window.location.pathname;
				alertForChangedSubjArea(currentPath);
				getAuthorizedAreaForAdmin();
				chageMenuByCurrentPath(currentPath);
			} else if(data.authTpCd === 'W'){
				const currentPath = window.location.pathname;
				alertForChangedSubjArea(currentPath);
				getAuthorizedAreaForWrite();
				chageMenuByCurrentPath(currentPath);
			} else if(data.authTpCd === 'R'){
				const currentPath = window.location.pathname;
				alertForChangedSubjArea(currentPath);
				document.getElementById('authorizedArea').innerHTML = '';
				getAlertByCurrentPath(currentPath);
			}
			logoutBtn.innerHTML = `
						<i class="bx bx-power-off me-2" style="padding-left: 10px;"></i>
                        <span class="align-middle" id="logout">Log Out (${data.id})</span>
					`;
			
        }
    )
}

function getAlertByCurrentPath(currentPath){
	switch (currentPath) {
    case '/dms/single-word/form':
    case '/dms/bulk-word':
	case '/dms/bulk-word/display':
    case '/dms/single-domain/form':
	case '/dms/bulk-domain':
	case '/dms/bulk-domain/display':
	case '/dms/term':
	case '/dms/bulk-term':
	case '/dms/bulk-term/display':
	case '/dms/standardArea/page':
	case '/dms/standardArea/managementPage':
        alert('선택하신 프로젝트에 대한 등록 권한이 없습니다.');
        window.location.href = "/dms/standardData/page";
        break;
    default:
        break;
	}
}

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

function alertForChangedSubjArea(currentPath){
	if(currentPath === '/dms/login' || currentPath === '/dms/standardData/page'){
		const tableBody = document.querySelector('.table tbody');
		if(tableBody && tableBody.children.length > 0){
			alert('프로젝트가 변동되었습니다. 다시 검색해 주세요.');
			window.location.href = "/dms/standardData/page";
		}
	} else if(currentPath === '/dms/collectionBook/page'){
		const tableBody = document.querySelector('.table tbody');
		if(tableBody && tableBody.children.length > 0){
			alert('프로젝트가 변동되었습니다. 다시 조회해 주세요.');
			window.location.href = "/dms/collectionBook/page";
		}
	} else if(currentPath === '/dms/synonym/page'){
		const tableBody = document.querySelector('.table tbody');
		if(tableBody && tableBody.children.length > 0){
			alert('프로젝트가 변동되었습니다. 다시 조회해 주세요.');
			window.location.href = "/dms/synonym/page";
		}
	}
}