<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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

    <style>
        .thead-sticky {
            position: sticky;
            top: 0;
            background-color: #ffffff;
            z-index: 1;
        }

        #alertContainer {
            z-index: 2000;
        }
    </style>
</head>

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
                >-->
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
                
                <!-- Dashboard -->
                <li class="menu-header small text-uppercase">
                    <span class="menu-header-text">대시보드</span>
                </li>
                <li class="menu-item">
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
                    <!-- Dropdown -->
                    <!-- STD_AREA_PICKER -->
                    <div class="input-group">
                        <label class="input-group-text" for="selectStandardArea">프로젝트명</label>
                        <select id="selectStandardArea" class="form-select color-dropdown" onchange="handleSelectChange(this)">
                            <option value="" selected disabled hidden></option>
                            <c:forEach var="std" items="${sessionScope.stdList}">
                                <option value="${std.stdAreaNm}">${std.stdAreaNm}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <!-- STD_AREA_PICKER -->
                    
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
                    <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-bold"> 단어 / </span> 일괄등록 </h4>
                    <!-- Title -->

                    <!-- 여기에 내용 넣기 -->

                    <div class="row">

                        <!-- 파일 업로드 -->
                        <div class="col-12 mb-3">
                            <div class="card">
                                <h5 class="card-header">파일위치</h5>
                                <div class="card-body demo-vertical-spacing demo-only-element">
                                    <form id="fileUploadForm" method="post" action="/dms/bulk-word/display"
                                          enctype="multipart/form-data">
                                        <div class="input-group">
                                            <input
                                                    type="file"
                                                    class="form-control"
                                                    id="inputGroupFile04"
                                                    name="file"
                                                    aria-describedby="inputGroupFileAddon04"
                                                    aria-label="Upload"
                                            />
                                            <button class="btn btn-outline-primary" type="submit"
                                                    id="inputGroupFileAddon04"
                                                    style="color: #fff; background-color: #696cff; border-color: #696cff;">
                                                    Upload
                                            </button>
                                        </div>
                                    </form>
                                    <div class="col-sm-5 mb-3">
                                        <button type="button" class="btn btn-secondary btn-lg btn-md" id="downloadExcelFormOfWordBtn"
                                        >서식다운로드
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- 파일 업로드 -->

                        <c:if test="${not empty wordExcelDataDTOList}">
                            <!-- Vertical & Horizontal Scrollbars -->
                            <div class="col-12 mb-3">
                                <div class="card overflow-hidden" style="height: 500px">
                                    <h5 class="card-header">단어 목록(등록 가능)</h5>

                <!-- 엑셀 데이터 화면 출력 -->
                <div class="table-responsive text-nowrap">
                  <div class="card-body both-scrollbars-example">
                    <table class="table">
                      <thead class="thead-sticky">
                        <tr class="text-nowrap">
                          <th>행번호</th>
                          <th><input class="form-check-input" type="checkbox" name="availableRow" value="selectAvailableAll" onclick="selectAvailableAll(this)"/></th>
                          <th>사유</th>
                          <th>논리명*</th>
                          <th>물리명*</th>
                          <th>영문풀네임*</th>
                          <th>엔티티 분류어</th>
                          <th>속성 분류어</th>
                          <th>동의어</th>
                          <th>설명</th>
                        </tr>
                      </thead>
                      <tbody>
                      <c:forEach items="${wordExcelDataDTOList}" var="wordExcelDataDTO" varStatus="status">
                        <c:if test="${fn:contains(wordExcelDataDTO.reason, '!Y!')}">
                          <tr>
                            <th scope="row">${status.index + 2}</th>
                            <td><input class="form-check-input" type="checkbox" name="availableRow" value="Y" checked="checked"></td>
                            <td name="reason">Y</td>
                            <td name="dicLogicalName">${wordExcelDataDTO.dicLogicalName}</td>
                            <td name="dicPhysicalName">${wordExcelDataDTO.dicPhysicalName}</td>
                            <td name="dicPhysicalFullName">${wordExcelDataDTO.dicPhysicalFullName}</td>
                            <td name="entitySuffix">${wordExcelDataDTO.entitySuffix}</td>
                            <td name="attributeSuffix">${wordExcelDataDTO.attributeSuffix}</td>
                            <td name="synonym">
                              <c:forEach items="${wordExcelDataDTO.synonym}" var="synonym" varStatus="status">
                                ${synonym}${not status.last  ? ' , ' : ''}
                              </c:forEach>
                            </td>
                            <td name="dicDescription">${wordExcelDataDTO.dicDescription}</td>
                          </tr>
                        </c:if>
                      </c:forEach>
                      </tbody>
                    </table>
                  </div>
                </div>
                <!-- 엑셀 데이터 화면 출력 -->

                                    <!-- 버튼 -->
                                    <div class="card-body">
                                        <div class="d-grid gap-2 col-lg-6 mx-auto text-center">
                                            <div class="demo-inline-spacing">
                                                <button class="btn btn-primary btn-lg" type="button"
                                                        id="insertBulkWordButton">등록
                                                </button>
                                                <button class="btn btn-secondary btn-lg" type="button"
                                                        id="saveAvaRowToExcel">출력
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- 버튼 -->
                                </div>
                            </div>
                            <!-- Vertical & Horizontal Scrollbars -->

                            <!-- Vertical & Horizontal Scrollbars -->
                            <div class="col-12">
                                <div class="card overflow-hidden" style="height: 500px">
                                    <h5 class="card-header">단어 목록(등록 불가능)</h5>

                <!-- 엑셀 데이터 화면 출력 -->
                <div class="table-responsive text-nowrap">
                  <div class="card-body both-scrollbars-example">
                    <table class="table">
                      <thead class="thead-sticky">
                      <tr class="text-nowrap">
                        <th>행번호</th>
                        <th><input class="form-check-input" type="checkbox" name="unavailableRow" value="selectUnavailableAll" onclick="selectUnavailableAll(this)"/></th>
                        <th>사유</th>
                        <th>논리명*</th>
                        <th>물리명*</th>
                        <th>영문풀네임*</th>
                        <th>엔티티 분류어</th>
                        <th>속성 분류어</th>
                        <th>동의어</th>
                        <th>설명</th>
                      </tr>
                      </thead>
                      <tbody>
                      <c:forEach items="${wordExcelDataDTOList}" var="wordExcelDataDTO" varStatus="status">
                        <c:if test="${not fn:contains(wordExcelDataDTO.reason, '!Y!')}">
                          <tr>
                            <th scope="row">${status.index + 2}</th>
                            <td><input class="form-check-input" type="checkbox" name="unavailableRow"></td>
                            <td name="reason">
                              <c:forEach items="${wordExcelDataDTO.reason}" var="reason" varStatus="status">
                                ${reason}${not status.last  ? ' / ' : ''}
                              </c:forEach>
                            </td>
                            <td name="dicLogicalName">${wordExcelDataDTO.dicLogicalName}</td>
                            <td name="dicPhysicalName">${wordExcelDataDTO.dicPhysicalName}</td>
                            <td name="dicPhysicalFullName">${wordExcelDataDTO.dicPhysicalFullName}</td>
                            <td name="entitySuffix">${wordExcelDataDTO.entitySuffix}</td>
                            <td name="attributeSuffix">${wordExcelDataDTO.attributeSuffix}</td>
                            <td name="synonym">
                              <c:forEach items="${wordExcelDataDTO.synonym}" var="synonym" varStatus="status">
                                ${synonym}${not status.last  ? ' , ' : ''}
                              </c:forEach>
                            </td>
                            <td name="dicDescription">${wordExcelDataDTO.dicDescription}</td>
                          </tr>
                        </c:if>
                      </c:forEach>
                      </tbody>
                    </table>
                  </div>
                </div>
                <!-- 엑셀 데이터 화면 출력 -->

                                    <!-- 버튼 -->
                                    <div class="card-body">
                                        <div class="d-grid gap-2 col-lg-6 mx-auto text-center">
                                            <div class="demo-inline-spacing">
                                                <button class="btn btn-secondary btn-lg" type="button"
                                                        id="saveUnAvaRowToExcel">출력
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- 버튼 -->
                                </div>
                            </div>
                            <!-- Vertical & Horizontal Scrollbars -->
                        </c:if>
                    </div>


                    <!-- Modal 1-->
                    <c:if test="${not fn:contains(wordExcelDataDTO.reason, '엑셀 내 단어논리명 중복')}">
                        <div
                                class="modal fade"
                                id="modalToggle"
                                aria-labelledby="modalToggleLabel"
                                tabindex="-1"
                                style="display: none"
                                aria-hidden="true"
                        >
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="modalToggleLabel">Modal 1</h5>
                                        <button
                                                type="button"
                                                class="btn-close"
                                                data-bs-dismiss="modal"
                                                aria-label="Close"
                                        ></button>
                                    </div>
                                    <div class="modal-body">
                                        <!-- Responsive Table -->
                                        <div class="card">
                                            <h5 class="card-header">Responsive Table</h5>
                                            <div class="table-responsive text-nowrap">
                                                <table class="table">
                                                    <thead>
                                                    <tr class="text-nowrap">
                                                        <th>#</th>
                                                        <th>Table heading</th>
                                                        <th>Table heading</th>
                                                        <th>Table heading</th>
                                                        <th>Table heading</th>
                                                        <th>Table heading</th>
                                                        <th>Table heading</th>
                                                        <th>Table heading</th>
                                                        <th>Table heading</th>
                                                        <th>Table heading</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <tr>
                                                        <th scope="row">1</th>
                                                        <td>Table cell</td>
                                                        <td>Table cell</td>
                                                        <td>Table cell</td>
                                                        <td>Table cell</td>
                                                        <td>Table cell</td>
                                                        <td>Table cell</td>
                                                        <td>Table cell</td>
                                                        <td>Table cell</td>
                                                        <td>Table cell</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">2</th>
                                                        <td>Table cell</td>
                                                        <td>Table cell</td>
                                                        <td>Table cell</td>
                                                        <td>Table cell</td>
                                                        <td>Table cell</td>
                                                        <td>Table cell</td>
                                                        <td>Table cell</td>
                                                        <td>Table cell</td>
                                                        <td>Table cell</td>
                                                    </tr>
                                                    <tr>
                                                        <th scope="row">3</th>
                                                        <td>Table cell</td>
                                                        <td>Table cell</td>
                                                        <td>Table cell</td>
                                                        <td>Table cell</td>
                                                        <td>Table cell</td>
                                                        <td>Table cell</td>
                                                        <td>Table cell</td>
                                                        <td>Table cell</td>
                                                        <td>Table cell</td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                        <!--/ Responsive Table -->
                                    </div>
                                    <div class="modal-footer">
                                        <button
                                                class="btn btn-primary"
                                                data-bs-target="#modalToggle2"
                                                data-bs-toggle="modal"
                                                data-bs-dismiss="modal"
                                        >
                                            Open second modal
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>


                    <!-- Insert Modal -->
                    <div class="modal fade" id="exLargeModal" data-bs-backdrop="static" data-bs-keyboard="false"
                         tabindex="-1" aria-hidden="true">
                        <div class="modal-dialog modal-xl modal-dialog-scrollable" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title fw-bold" id="exampleModalLabel4"><span
                                            class="text-muted fw-bold">단어 /</span> 등록 완료 목록</h5>
                                </div>
                                <div class="modal-body"></div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary" id="confirmBtn">확인</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Insert Modal -->

                    <!-- 여기에 내용 넣기 -->

                </div>
                <!-- Content -->
                <div id="alertContainer"
                     style="position: fixed; bottom: 10px; left: 50%; transform: translateX(-50%);"></div>
                <div class="spinner-border spinner-border-lg text-primary" role="status" id="loading-spinner"
                     style="position: fixed; top: 10px; left: 50%; z-index: 9999; display: none">
                    <span class="visually-hidden">Loading...</span>
                </div>
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
<script src="/js/spinner.js"></script>
<script src="/js/showAlert.js"></script>
<script src="/js/sendAjaxRequest.js"></script>
<script src="/js/bulk/word.js"></script>
<script src="/js/session/session.js"></script>

<!-- Place this tag in your head or just before your close body tag. -->
<script async defer src="https://buttons.github.io/buttons.js"></script>
</body>
</html>