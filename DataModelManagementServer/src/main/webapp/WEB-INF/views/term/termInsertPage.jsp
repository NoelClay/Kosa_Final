<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<!-- =========================================================
* Sneat - Bootstrap 5 HTML Admin Template - Pro | v1.0.0
==============================================================

* Product Page: https://themeselection.com/products/sneat-bootstrap-html-admin-template/
* Created by: ThemeSelection
* License: You must have a valid license purchased in order to legally use the theme for your project.
* Copyright ThemeSelection (https://themeselection.com)

=========================================================
-->
<!-- beautify ignore:start -->
<html
        lang="en"
        class="light-style layout-menu-fixed"
        dir="ltr"
        data-theme="theme-default"
        data-assets-path="../assets/"
        data-template="vertical-menu-template-free"
>
<head>
    <meta charset="utf-8"/>
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"
    />

    <title>DMS : 표준 용어 관리 시스템</title>

    <meta name="description" content=""/>

    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="/img/favicon/favicon.ico"/>

    <!-- Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>
    <link
            href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
            rel="stylesheet"
    />

    <!-- Icons. Uncomment required icon fonts -->
    <link rel="stylesheet" href="/css/boxicons.css"/>

    <!-- Core CSS -->
    <link rel="stylesheet" href="/css/core.css" class="template-customizer-core-css"/>
    <link rel="stylesheet" href="/css/theme-default.css" class="template-customizer-theme-css"/>
    <link rel="stylesheet" href="/css/demo.css"/>

    <!-- Vendors CSS -->
    <link rel="stylesheet" href="/css/perfect-scrollbar.css"/>

    <!-- Page CSS -->

    <!-- Helpers -->
    <script src="/js/helpers.js"></script>

    <!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
    <!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
    <script src="/js/config.js"></script>
</head>
<style>
    #selectWordButton {
        width: 10%; /* 버튼을 100%로 넓힙니다. */
    }
</style>

<body>

<!-- Layout wrapper -->
<div class="layout-wrapper layout-content-navbar">
    <div class="layout-container">

        <!-- Menu -->
        <aside id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme">
            <div class="app-brand demo">
                <a href="/dms/standardData/page" class="app-brand-link">
              <span class="app-brand-logo demo">
                <!--<svg
                        width="25"
                        viewBox="0 0 25 42"
                        version="1.1"
                        xmlns="http://www.w3.org/2000/svg"
                        xmlns:xlink="http://www.w3.org/1999/xlink"
                >  -->
                  <defs>
                    <path
                            d="M13.7918663,0.358365126 L3.39788168,7.44174259 C0.566865006,9.69408886 -0.379795268,12.4788597 0.557900856,15.7960551 C0.68998853,16.2305145 1.09562888,17.7872135 3.12357076,19.2293357 C3.8146334,19.7207684 5.32369333,20.3834223 7.65075054,21.2172976 L7.59773219,21.2525164 L2.63468769,24.5493413 C0.445452254,26.3002124 0.0884951797,28.5083815 1.56381646,31.1738486 C2.83770406,32.8170431 5.20850219,33.2640127 7.09180128,32.5391577 C8.347334,32.0559211 11.4559176,30.0011079 16.4175519,26.3747182 C18.0338572,24.4997857 18.6973423,22.4544883 18.4080071,20.2388261 C17.963753,17.5346866 16.1776345,15.5799961 13.0496516,14.3747546 L10.9194936,13.4715819 L18.6192054,7.984237 L13.7918663,0.358365126 Z"
                            id="path-1"
                    ></path>
                    <path
                            d="M5.47320593,6.00457225 C4.05321814,8.216144 4.36334763,10.0722806 6.40359441,11.5729822 C8.61520715,12.571656 10.0999176,13.2171421 10.8577257,13.5094407 L15.5088241,14.433041 L18.6192054,7.984237 C15.5364148,3.11535317 13.9273018,0.573395879 13.7918663,0.358365126 C13.5790555,0.511491653 10.8061687,2.3935607 5.47320593,6.00457225 Z"
                            id="path-3"
                    ></path>
                    <path
                            d="M7.50063644,21.2294429 L12.3234468,23.3159332 C14.1688022,24.7579751 14.397098,26.4880487 13.008334,28.506154 C11.6195701,30.5242593 10.3099883,31.790241 9.07958868,32.3040991 C5.78142938,33.4346997 4.13234973,34 4.13234973,34 C4.13234973,34 2.75489982,33.0538207 2.37032616e-14,31.1614621 C-0.55822714,27.8186216 -0.55822714,26.0572515 -4.05231404e-15,25.8773518 C0.83734071,25.6075023 2.77988457,22.8248993 3.3049379,22.52991 C3.65497346,22.3332504 5.05353963,21.8997614 7.50063644,21.2294429 Z"
                            id="path-4"
                    ></path>
                    <path
                            d="M20.6,7.13333333 L25.6,13.8 C26.2627417,14.6836556 26.0836556,15.9372583 25.2,16.6 C24.8538077,16.8596443 24.4327404,17 24,17 L14,17 C12.8954305,17 12,16.1045695 12,15 C12,14.5672596 12.1403557,14.1461923 12.4,13.8 L17.4,7.13333333 C18.0627417,6.24967773 19.3163444,6.07059163 20.2,6.73333333 C20.3516113,6.84704183 20.4862915,6.981722 20.6,7.13333333 Z"
                            id="path-5"
                    ></path>
                  </defs>
                  <g id="g-app-brand" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                    <g id="Brand-Logo" transform="translate(-27.000000, -15.000000)">
                      <g id="Icon" transform="translate(27.000000, 15.000000)">
                        <g id="Mask" transform="translate(0.000000, 8.000000)">
                          <mask id="mask-2" fill="white">
                            <use xlink:href="#path-1"></use>
                          </mask>
                          <use fill="#696cff" xlink:href="#path-1"></use>
                          <g id="Path-3" mask="url(#mask-2)">
                            <use fill="#696cff" xlink:href="#path-3"></use>
                            <use fill-opacity="0.2" fill="#FFFFFF" xlink:href="#path-3"></use>
                          </g>
                          <g id="Path-4" mask="url(#mask-2)">
                            <use fill="#696cff" xlink:href="#path-4"></use>
                            <use fill-opacity="0.2" fill="#FFFFFF" xlink:href="#path-4"></use>
                          </g>
                        </g>
                        <g
                                id="Triangle"
                                transform="translate(19.000000, 11.000000) rotate(-300.000000) translate(-19.000000, -11.000000) "
                        >
                          <use fill="#696cff" xlink:href="#path-5"></use>
                          <use fill-opacity="0.2" fill="#FFFFFF" xlink:href="#path-5"></use>
                        </g>
                      </g>
                    </g>
                  </g>
                </svg>
              </span>
                    <span class="app-brand-text demo menu-text fw-bolder ms-2"><img src="/img/tysimg.png" width="38.5" style="margin-bottom: 0.5rem;">Meta</span>
                </a>

                <a href="javascript:void(0);" class="layout-menu-toggle menu-link text-large ms-auto d-block d-xl-none">
                    <i class="bx bx-chevron-left bx-sm align-middle"></i>
                </a>
            </div>
            

            <div class="menu-inner-shadow"></div>
            <ul class="menu-inner py-1">

                <!-- Layouts -->

                <li class="menu-header small text-uppercase">
                    <span class="menu-header-text">데이터 표준 관리</span>
                </li>
                <li class="menu-item">
                    <a href="/dms/standardData/page" class="menu-link">
                        <i class="menu-icon tf-icons bx bx-home-circle"></i>
                        <div data-i18n="Analytics">표준 데이터 조회</div>
                    </a>
                </li>
                
                <li class="menu-item">
                    <a href="/dms/collectionBook/page" class="menu-link">
                        <i class="menu-icon tf-icons bx bx-detail"></i>
                        <div data-i18n="Analytics">표준 데이터 출력</div>
                    </a>
                </li>

                <li class="menu-item">
                    <a href="/dms/synonym/page" class="menu-link">
                        <i class="menu-icon tf-icons bx bx-search"></i>
                        <div data-i18n="Search">동의어 조회</div>
                    </a>
                </li>


                <!-- Components -->
                
                <!-- 데이터 표준 등록 -->
                <div id="authorizedArea">
                </div>
                <!-- 데이터 표준 등록 -->
                
                <li class="menu-header small text-uppercase">
                    <span class="menu-header-text">대시보드</span>
                </li>
                <li class="menu-item ">
                    <a href="/dms/dashboard" class="menu-link">
                        <i class="menu-icon tf-icons bx bxs-pie-chart-alt"></i>
                        <div data-i18n="Chart">통계</div>
                    </a>
                </li>
                <!-- Admin -->
            	<div id="adminArea">
                </div>
                <!-- Admin -->
                
                <!-- User -->
                <a class="dropdown-item" href="auth-login-basic.html" id="logoutBtn" style="margin-top: 30px;">
                </a>
            	<!--/ User -->
            </ul>
        </aside>
        <!-- / Menu -->

        <!-- Layout container -->
        <div class="layout-page">

            <!-- Navbar -->

            <nav
                    class="layout-navbar container-xxl navbar navbar-expand-xl navbar-detached align-items-center bg-navbar-theme"
                    id="layout-navbar"
            >
                <div class="layout-menu-toggle navbar-nav align-items-xl-center me-3 me-xl-0 d-xl-none">
                    <a class="nav-item nav-link px-0 me-xl-4" href="javascript:void(0)">
                        <i class="bx bx-menu bx-sm"></i>
                    </a>
                </div>

                <div class="navbar-nav-right d-flex align-items-center" id="navbar-collapse">
                    <!-- STD_AREA_PICKER-->
                    <div class="input-group">
                        <label class="input-group-text" for="selectStandardArea">프로젝트명</label>
                        <select id="selectStandardArea" class="form-select color-dropdown" onchange="handleSelectChange(this)">
                            <option value="" selected disabled hidden></option>
                            <c:forEach var="std" items="${sessionScope.stdList}">
                                <option value="${std.stdAreaNm}">${std.stdAreaNm}</option>
                            </c:forEach>
                        </select>
                    </div>
                    
                   <!-- User -->     
	               <ul class="navbar-nav flex-row align-items-center ms-auto">
	                <!-- Place this tag where you want the button to render. -->
	                <li class="nav-item lh-1 me-3">
	                </li>
	                <li class="nav-item navbar-dropdown dropdown-user dropdown">
	                  <a class="nav-link dropdown-toggle hide-arrow" href="javascript:void(0);" data-bs-toggle="dropdown">
	                    <div>
	                      <img src="/img/icons/person-circle.png" alt class="w-px-40 h-auto rounded-circle" />
	                    </div>
	                  </a>
	                  <ul class="dropdown-menu dropdown-menu-end" style="text-align: center;">
	                    <li>
	                      <a class="dropdown-item" href="#">
	                        <div class="d-flex" style="text-align: center;">
	                          <div class="flex-grow-1">
	                            <span class="fw-semibold d-block" id="userId"></span>
	                          </div>
	                        </div>
	                      </a>
	                    </li>
	                    <li>
	                      <div class="dropdown-divider"></div>
	                    </li>
	                    <li>
	                      <a class="dropdown-item" href="/dms/member-profile/form">
	                        <span class="align-middle">Profile</span>
	                      </a>
	                    </li>
	                    <li>
	                      <a class="dropdown-item" href="" id="logoutOfDropdownBtn">
	                        <span class="align-middle">Log Out</span>
	                      </a>
	                    </li>
	                  </ul>
	                </li>
	              </ul>
	              <!--/ User -->
                </div>
            </nav>
            <!-- / Navbar -->

            <!-- Content wrapper -->
            <div class="content-wrapper">
            
                <!-- Toast with Placements -->
                <div class="bs-toast toast toast-placement-ex m-2 bg-primary top-0 start-50 translate-middle-x" role="alert" aria-live="assertive" aria-atomic="true" data-delay="1000">
				  <div role="alert" aria-live="assertive" aria-atomic="true">
				  	<div class="toast-header">
		              <i class="bx bx-bell me-2"></i>
		              <div class="me-auto fw-semibold">NOTICE</div>
		              <small></small>
		              <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
		            </div>
		            <div class="toast-body">선택된 프로젝트가 없습니다. 프로젝트를 선택해 주세요.</div>
	              </div>
			    </div>
         		<!-- Toast with Placements -->
         		
                <!-- Content -->
                <div class="container-xxl flex-grow-1 container-p-y">

                    <!-- Title -->
                    <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-bold"> 용어 / </span> 등록 </h4>
                    <!-- Title -->

                    <!-- 여기에 내용 넣기 -->

                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <h5 class="card-header">용어 검색</h5>

                                <!-- 검 색 -->
                                <div class="card-body">
                                    <div class="row align-items-center">
                                        <div class="col">
                                            <div class="input-group">
                                                <input id="searchWord" class="form-control color-dropdown"
                                              		   style="text-transform: uppercase"
                                                       placeholder="용어명">
                                                <button type="button" class="btn btn-primary" id="selectWordButton">검색
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <!-- 검 색 -->


                                <!-- 검색어 정보 출력 -->
                                <h5 class="card-header">단어 목록</h5>
                                <div class="table-responsive text-nowrap" id="wordTable">
                                    <table class="table">
                                        <thead>
                                        <tr class="text-nowrap">
                                            <th>번호</th>
                                            <th>선택</th>
                                            <th>논리용어명</th>
                                            <th>물리용어명</th>
                                            <th>엔티티분류어</th>
                                            <th>속성분류어</th>
                                            <th>표준여부</th>
                                            <th>설명</th>

                                        </tr>
                                        </thead>
                                        <tbody id="wordTableBody">
                                        <!-- 여기에 스크립트 파일에서 생성된 tbody가 들어가야 해요 -->
                                        </tbody>
                                    </table>
                                </div>


                                <!-- 검색어 정보 출력 -->

                            </div>
                        </div>
                    </div>

                    <!-- 용어 정보 -->
                    <div class="card">
                        <h5 class="card-header">용어정보</h5>
                        <div class="card-body">

                            <div class="col-sm-10 mb-3 form-floating">
                                <input type="text" class="form-control" id="equalWordSelected"
                                       placeholder="입력..." readonly aria-describedby="floatingInputHelp">
                                <label for="equalWordSelected">&nbsp;&nbsp;&nbsp;기존 용어</label>
                            </div>

                            <div class="col-sm-10 mb-3 form-floating">
                                <input type="text" class="form-control" id="dicLogNm"
                                       placeholder="입력..." readonly aria-describedby="floatingInputHelp">
                                <label for="dicLogNm">&nbsp;&nbsp;&nbsp;논리용어명</label>
                            </div>

                            <div class="col-sm-10 mb-3 form-floating">
                                <input type="text" class="form-control" id="dicPhyNm"
                                       placeholder="입력..." readonly aria-describedby="floatingInputHelp">
                                <label for="dicPhyNm">&nbsp;&nbsp;&nbsp;물리용어명</label>
                            </div>


                            <br>
                            
                            <div class="row mb-3">
                                <div class="col" id="termDistinction">
                                    <h5>용어구분</h5>
						            <div class="form-check form-check-inline">
						              <input
						                class="form-check-input"
						                type="radio"
						                name="termDistinction"
						                id="entity"
						                value="Y"
						              />
						              <label class="form-check-label" for="entity">엔티티</label>
						            </div>
						            <div class="form-check form-check-inline">
						              <input
						                class="form-check-input"
						                type="radio"
						                name="termDistinction"
						                id="attribute"
						                value="Y"
						              />
						              <label class="form-check-label" for="attribute">속성</label>
						            </div>                                    
                                </div>
                            </div>                            

                            <div class="row mb-3">
                                <div class="col">
                                    <h5>용어설명</h5>
                                    <input type="text" class="form-control" id="dicDesc" placeholder="용어 설명">
                                </div>
                            </div>
                            <div class="row align-items-center">
                                <div class="col">
                                <h5>도메인</h5>
                                    <div class="input-group">
                                        <input type="text" class="form-control" id="termDomName"
                                               placeholder="도메인" aria-describedby="floatingInputHelp">
                                        <label for="termDomName"></label>
                                        <button class="btn btn-primary" id="domainSearchButton">조회</button>&nbsp;
                                        <button class="btn btn-primary" id="domainInsertButton">추가</button>
                                    </div>
                                </div>
                            </div>


                            <div class="card-body demo-vertical-spacing demo-only-element"
                                 style="padding-left: 0rem">

                                <button type="button" class="btn btn-primary"
                                        id="insertButton">등록
                                </button>
                            </div>
                        </div>
                    </div>
                    <!-- 단어 추가 모달 -->
                    <div class="modal fade" id="modalTermWord" tabindex="-1" style="display: none;" aria-hidden="true"
                         data-bs-target="static">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="modalCenterTitle">단어 등록</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col mb-3">
                                            <div class="input-group">
                                                <label for="modalLogNm" class="input-group-text">논리명
                                                    &nbsp;&nbsp;&nbsp; </label>
                                                <input type="text" id="modalLogNm" class="form-control"
                                                       placeholder="입력...">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col mb-3">
                                            <div class="input-group">
                                                <label for="modalPhyNm" class="input-group-text">물리명
                                                    &nbsp;&nbsp;&nbsp; </label>
                                                <input type="text" id="modalPhyNm" class="form-control"
                                                       placeholder="입력...">
                                                <label style="cursor: pointer;" class="input-group-text"
                                                       for="modalPhyNm" id="modalTermWordValidation">중복확인</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col mb-3">
                                            <div class="input-group">
                                                <label for="modalFllNm" class="input-group-text">영문 풀네임</label>
                                                <input type="text" id="modalFllNm" class="form-control"
                                                       placeholder="입력...">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col mb-3">
                                            <div class="input-group">
                                                <label for="modalDescription" class="input-group-text">설 명</label>
                                                <input type="text" id="modalDescription" class="form-control"
                                                       placeholder="입력...">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col mb-3">
                                            <div class="form-check" style="display: flex;">
                                                <input class="form-check-input" type="checkbox" value=""
                                                       id="modalEntClssYn"
                                                       unChecked/>
                                                <label class="form-check-label" for="modalEntClssYn"> 엔티티 </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row g-2">
                                        <div class="col mb-0">
                                            <div class="form-check" style="display: flex;">
                                                <input class="form-check-input" type="checkbox" value=""
                                                       id="modalAttrClssYn"
                                                       unChecked/>
                                                <label class="form-check-label" for="modalAttrClssYn"> 속성 </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary" id="ModalTermWordInsertButton">
                                            확인
                                        </button>
                                        <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
                                            닫기
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- 도메인 조회 모달 -->
                    <div class="modal fade" id="termDomainSearchModal" tabindex="-1" style="display: none;"
                         aria-hidden="true"
                         data-bs-target="static">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="modalDomainTitle">도메인 조회</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col mb-3">
                                            <div class="input-group">
                                            	<input type="text" id="modalAttributeWord" class="form-control" hidden>
                                                <label for="modalDomainName" class="input-group-text">도메인 이름
                                                    &nbsp;&nbsp;&nbsp; </label>
                                                <input type="text" id="modalDomainName" class="form-control"
                                                       placeholder="입력..."
                                                       style="text-transform: uppercase">
                                                <label style="cursor: pointer;" class="input-group-text"
                                                       for="modalDomainName" id="modalDomainSearch">조회</label>
                                            </div>
                                            <div class="table-responsive text-nowrap" id="termDomainTable">
                                                <table class="table">
                                                    <thead>
                                                    <tr class="text-nowrap">
                                                        <th>번호</th>
                                                        <th>대표도메인명</th>
                                                        <th>도메인명</th>
                                                        <th>도메인유형</th>
                                                        <th>논리데이터타입</th>
                                                        <th>데이터길이</th>
                                                        <th>소수점</th>
                                                        <th>도메인그룹</th>

                                                    </tr>
                                                    </thead>
                                                    <tbody id="modalDomainTableBody">
                                                    <!-- 여기에 스크립트 파일에서 생성된 tbody가 들어가야 해요 -->
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary" id="confirmDomainNameButton">
                                            확인
                                        </button>
                                        <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
                                            닫기
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- 도메인 등록 모달 -->
                    <div class="modal fade" id="termDomainInsertModal" tabindex="-1" style="display: none;"
                         aria-hidden="true"
                         data-bs-target="static">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="modalDomainInsertTitle">도메인 등록</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col mb-3">
                                            <form id="domainForm">

                                                <div class="col-sm-16 mb-3 form-floating">
                                                    <input type="text" class="form-control" id="keyDomName" name="keyDomName" readonly>
                                                    <label for="keyDomName">대표 도메인</label>
                                                </div>


                                                <div class="col-sm-16 mb-3 form-floating">
                                                    <input type="text" class="form-control" id="domName" name="domName"
                                                           placeholder="입력..." aria-describedby="domNameHelp" readonly>
                                                    <label for="domName">도메인명</label>
                                                </div>

                                                <div class="col-sm-5 mb-3">
                                                    <button type="button" class="btn btn-primary" id="checkDuplicateButton">중복 확인
                                                    </button>
                                                </div>


                                                <div class="col-sm-16 mb-3">
                                                    <label class="form-label"></label>
                                                    <select id="domTypeCode" class="form-select color-dropdown">
                                                        <option value="bg-primary" selected disabled>도메인 유형</option>
                                                        <option value="번호">번호</option>
                                                        <option value="일반">일반</option>
                                                        <option value="코드">코드</option>
                                                    </select>

                                                    <label class="form-label"></label>
                                                    <select class="form-select color-dropdown" id="domGroupId" name="domGroupId">
                                                    </select>

                                                    <label class="form-label"></label>
                                                    <select class="form-select color-dropdown" id="dataTypeCode"
                                                            name="dataTypeCode">
                                                        <option value="bg-primary" selected disabled>논리 데이터 타입</option>
                                                        <option value="C">CHAR</option>
                                                        <option value="NC">NCHAR</option>
                                                        <option value="V">VARCHAR</option>
                                                        <option value="NV">NVARCHAR</option>
                                                        <option value="BOOL">BOOLEAN</option>
                                                        <option value="N">NUMBER</option>
                                                        <option value="TM">TIME</option>
                                                        <option value="TMS">TIMESTAMP</option>
                                                        <option value="DT">DATE</option>
                                                        <option value="DTM">DATETIME</option>
                                                        <option value="CLOB">CLOB</option>
                                                        <option value="NCLOB">NCLOB</option>
                                                        <option value="BLOB">BLOB</option>
                                                    </select>



                                                </div>


                                                <!----도메인 등록 --->
                                                <!-- Content wrapper -->
                                                <div class="content-wrapper">

                                                </div>




                                                <div class="row">
                                                    <div class="col-sm-5 mb-4 form-floating">
                                                        <input type="number" class="form-control" id="dataLen" name="dataLen"
                                                               placeholder="입력..." aria-describedby="dataLenhelp">
                                                        <label for="dataLen">&nbsp;&nbsp;&nbsp;&nbsp;데이터 길이</label>
                                                    </div>
                                                    <div class="col-sm-1"
                                                         style="line-height: 6; text-align: center; font-weight: bold;">.
                                                    </div>
                                                    <div class="col-sm-6 mb-3 form-floating">
                                                        <input type="number" class="form-control" id="dataScale" name="dataScale"
                                                               placeholder="입력..." aria-describedby="dataScalehelp">
                                                        <label for="dataScale">&nbsp;&nbsp;&nbsp;&nbsp;데이터 소수점</label>
                                                    </div>
                                                </div>


                                                <div class="row">
                                                    <div class="col-sm-5 mb-4 form-floating">
                                                        <input type="number" class="form-control" id="dataMin" name="dataMin"
                                                               placeholder="입력..." aria-describedby="dataMinhelp">
                                                        <label for="dataLen">&nbsp;&nbsp;&nbsp;&nbsp;데이터 범위(최소)</label>
                                                    </div>
                                                    <div class="col-sm-1"
                                                         style="line-height: 6; text-align: center; font-weight: bold;">~
                                                    </div>
                                                    <div class="col-sm-6 mb-3 form-floating">
                                                        <input type="number" class="form-control" id="dataMax" name="dataMax"
                                                               placeholder="입력..." aria-describedby="dataMaxhelp">
                                                        <label for="dataScale">&nbsp;&nbsp;&nbsp;&nbsp;데이터 범위(최대)</label>
                                                    </div>
                                                </div>




                                                <div class="mb-3 form-floating" style=" margin-top: 20px;">
                                                    <input type="text" class="form-control" id="domDescription"
                                                           placeholder="입력...">
                                                    <label for="domDescription">설명</label>
                                                </div>

                                                <div class="modal-footer" style="padding: 0px">
	                                                <button type="button" class="btn btn-primary" id="confirmButton" disabled>등록</button>                                                        
	                                                <button type="reset" class="btn btn-outline-secondary">취소</button>
	                                                <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">닫기</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                </div>
                <!-- 여기에 내용 넣기 -->


                <!-- Content -->
                <!-- Footer -->

                <!-- / Footer -->
                <div id="alertContainer"
                     style="position: fixed; bottom: 10px; left: 50%; transform: translateX(-50%); z-index: 9999;"></div>


                <div class="content-backdrop fade"></div>

                <div class="content-backdrop fade"></div>
            </div>
            <!-- Content wrapper -->
        </div>
        <!-- / Layout page -->
    </div>

    <!-- Overlay -->
    <div class="layout-overlay layout-menu-toggle"></div>
</div>
<!-- / Layout wrapper -->

<!-- Core JS -->
<!-- build:js assets/vendor/js/core.js -->
<script src="/js/jquery.js"></script>
<script src="/js/popper.js"></script>
<script src="/js/bootstrap.js"></script>
<script src="/js/perfect-scrollbar.js"></script>

<script src="/js/menu.js"></script>
<!-- endbuild -->

<!-- Vendors JS -->

<!-- Main JS -->
<script src="/js/main.js"></script>

<!-- Page JS -->
<script src="/js/login/login.js"></script>
<script src="/js/showAlert.js" defer></script>
<script src="/js/term/term-select-validation.js" defer></script>
<script src="/js/term/term-modalWord-insert.js" defer></script>
<script src="/js/term/term-modalDomain-select.js" defer></script>
<script src="/js/term/term-modalDomain-insert.js" defer></script>
<script src="/js/standardArea/standardArea-insert.js"></script>
<script src="/js/session/session.js"></script>
<script src="/js/global/inputTagValidation.js"></script>



<!-- Place this tag in your head or just before your close body tag. -->
<script async defer src="https://buttons.github.io/buttons.js"></script>
</body>
</html>

