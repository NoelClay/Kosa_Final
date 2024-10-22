// 차트 데이터
var data = {
    labels: ['도메인', '용어', '단어'],
    datasets: [{
        data: [0, 0, 0],
        backgroundColor: ['rgb(102, 110, 232)', 'rgb(40, 208, 148)', 'rgb(253, 172, 52)'],
    }],
};

// 차트 옵션
var options = {
    cutoutPercentage: 20, // 도넛 차트의 가운데 홈 부분 크기 설정
};

// 도넛 차트를 그릴 캔버스 가져오기
var ctx = document.getElementById('doughnutChart').getContext('2d');

// 도넛 차트 생성
var doughnutChart = new Chart(ctx, {
    type: 'doughnut',
    data: data,
    options: options,
});

// 바 차트의 데이터
var barChartData = {
    labels: ['Label 1', 'Label 2', 'Label 3'],
    datasets: [{
        label: '등록횟수',
        backgroundColor: 'rgb(102, 110, 232)',
        borderColor: 'rgb(102, 110, 232)',
        borderWidth: 1,
        data: [0, 0, 0], // 데이터 값
    }],
};

// 바 차트의 옵션
var barChartOptions = {
    scales: {
        y: {
            beginAtZero: true,
        },
    },
};

// 바 차트를 그릴 캔버스 가져오기
var barChartCtx = document.getElementById('barChart').getContext('2d');

// 바 차트 생성
var myBarChart = new Chart(barChartCtx, {
    type: 'bar',
    data: barChartData,
    options: barChartOptions,
});

// 차트 및 범례 업데이트 함수
function updateCharts(chartData) {
    // 도넛 차트 데이터 업데이트
    doughnutChart.data.labels = chartData
        .filter(item => ['word', 'domain', 'term'].includes(item.element))
        .map(item => {
            // 각 element에 대해 한글 레이블로 매핑
            switch (item.element) {
                case 'word':
                    return '단어';
                case 'domain':
                    return '도메인';
                case 'term':
                    return '용어';
                default:
                    return item.element;  // 기본적으로는 그대로 반환
            }
        });
    doughnutChart.data.datasets[0].data = chartData
        .filter(item => ['word', 'domain', 'term'].includes(item.element))
        .map(item => parseInt(item.count));


    // 바 차트 데이터 업데이트
    myBarChart.data.labels = chartData
        .filter(item => !['word', 'domain', 'term'].includes(item.element))
        .map(item => item.element);
    myBarChart.data.datasets[0].data = chartData
        .filter(item => !['word', 'domain', 'term'].includes(item.element))
        .map(item => parseInt(item.count));

    // 차트 및 범례 업데이트
    doughnutChart.update();
    myBarChart.update();
    updateLegend(chartData);
}

// 범례 업데이트 함수
function updateLegend(chartData) {
    const legendList = document.querySelector('.doughnut-legend');
    legendList.innerHTML = '';

    // 전체 개수 합산
    const totalCount = chartData
        .filter(item => ['word', 'domain', 'term'].includes(item.element))
        .reduce((acc, item) => acc + parseInt(item.count), 0);

    // 단어들의 한글 표현 매핑
    const koreanLabels = {
        'word': '단어',
        'domain': '도메인',
        'term': '용어'
    };

    chartData
        .filter(item => ['word', 'domain', 'term'].includes(item.element))
        .forEach((item, index) => {
            const listItem = document.createElement('li');
            listItem.className = `ct-series-${index} d-flex flex-column`;

            // 개수가 0일 경우를 방지하기 위해 조건 추가
            const percentage = totalCount === 0 ? 0 : ((parseInt(item.count) / totalCount) * 100).toFixed(2);

            listItem.innerHTML = `
                <h5 class="mb-0">${koreanLabels[item.element]}</h5>
                <span class="badge badge-dot my-2 cursor-pointer rounded-pill" 
                      style="background-color: ${item.color};width:35px; height:6px;"></span>
                <div class="text-muted">${item.count} 개 (${percentage}%)</div>
            `;
            legendList.appendChild(listItem);
        });
}


// Ajax 요청 에러 처리 함수
function handleAjaxError() {
    showAlert("서버 오류가 발생했습니다. 다시 시도해 주세요.");
}

// Select List 값 변경 이벤트 처리 함수
function handleSelectChange() {
    const selectElement = document.getElementById("selectStandardArea");
    const standardAreaName = selectElement.options[selectElement.selectedIndex].text;
    sendAjaxRequest(
        "/dms/dashboard/chart",
        standardAreaName,
        function (response) {
            showLoadingSpinner();
            // 차트 및 범례 업데이트
            updateCharts(response);
            hideLoadingSpinner();
        }
    );
    sendAjaxRequest(
        "/dms/session/set",
        standardAreaName,
        function (response) {
			getLoginSession();
        }
    )
    hideLoadingSpinner();
}

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

document.addEventListener('DOMContentLoaded', function () {
    fetchDataFromServer();
});

const selectElement = document.getElementById("selectStandardArea");

function fetchDataFromServer() {
    showLoadingSpinner();
    sendAjaxRequest(
        "/dms/session/get",
        null,
        function (response) {
			console.log('시작');
            selectOptionBasedOnData(response);

            // fetchDataFromServer가 완료된 후에 다음 단계를 실행합니다.
            const standardAreaName = document.getElementById("selectStandardArea").value;
            
            if(standardAreaName === ''){
				hideLoadingSpinner();
				return;
			}
            sendAjaxRequest(
                "/dms/dashboard/chart",
                standardAreaName,
                function (chartResponse) {
                    // 차트 및 범례 업데이트
                    updateCharts(chartResponse);
                    hideLoadingSpinner();
                }
            );
        }
    );
}

function selectOptionBasedOnData(selectedOption) {
    for (var i = 0; i < selectElement.options.length; i++) {
        if (selectElement.options[i].value === selectedOption) {
            selectElement.selectedIndex = i;
            break;
        }
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
				getAuthorizedAreaForAdmin();
			} else if(data.authTpCd === 'W'){
				getAuthorizedAreaForWrite();
			} else if(data.authTpCd === 'R'){
				document.getElementById('authorizedArea').innerHTML = '';
			}
			logoutBtn.innerHTML = `
						<i class="bx bx-power-off me-2" style="padding-left: 10px;"></i>
                        <span class="align-middle" id="logout">Log Out (${data.id})</span>
					`;
			
        }
    )
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