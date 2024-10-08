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

    <title>DMS :  용어 관리 시스템</title>

    <meta name="description" content=""/>

    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="/img/favicon/favicon.ico"/>
    
    <!-- 부트스트랩 아이콘 -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
    

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
                    <span class="app-brand-text demo menu-text fw-bolder ms-2"><img src="/img/tysimg.png" width="38.5" style="margin-bottom: 0.5rem;">Meta</span></a>

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
                <li class="menu-header small text-uppercase"><span class="menu-header-text">데이터  등록</span></li>

                <li class="menu-item">
                    <a href="javascript:void(0);" class="menu-link menu-toggle">
                        <i class="menu-icon tf-icons bx bx-detail"></i>
                        <div data-i18n="Account Settings"> 단어</div>
                    </a>
                    <ul class="menu-sub">
                        <li class="menu-item">
                            <a href="/dms/single-word/form" class="menu-link">
                                <div data-i18n="Account">등록</div>
                            </a>
                        </li>
                        <li class="menu-item">
                            <a href="/dms/bulk-word" class="menu-link">
                                <div data-i18n="Notifications">일괄등록</div>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="menu-item">
                    <a href="javascript:void(0);" class="menu-link menu-toggle">
                        <i class="menu-icon tf-icons bx bx-detail"></i>
                        <div data-i18n="Account Settings">도메인</div>
                    </a>
                    <ul class="menu-sub">
                        <li class="menu-item">
                            <a href="/dms/single-domain/form" class="menu-link">
                                <div data-i18n="Account">등록</div>
                            </a>
                        </li>
                        <li class="menu-item">
                            <a href="/dms/bulk-domain" class="menu-link">
                                <div data-i18n="Notifications">일괄등록</div>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="menu-item">
                    <a href="javascript:void(0);" class="menu-link menu-toggle">
                        <i class="menu-icon tf-icons bx bx-detail"></i>
                        <div data-i18n="Account Settings">용어</div>
                    </a>
                    <ul class="menu-sub">
                        <li class="menu-item">
                            <a href="/dms/term" class="menu-link">
                                <div data-i18n="Account">등록</div>
                            </a>
                        </li>
                        <li class="menu-item">
                            <a href="/dms/bulk-term" class="menu-link">
                                <div data-i18n="Notifications">일괄등록</div>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="menu-item">
                    <a href="javascript:void(0);" class="menu-link menu-toggle">
                        <i class="menu-icon tf-icons bx bx-detail"></i>
                        <div data-i18n="Account Settings"> 분류 체계</div>
                    </a>
                    <ul class="menu-sub">
                        <li class="menu-item">
                            <a href="/dms/standardArea/page" class="menu-link">
                                <div data-i18n="Account">등록</div>
                            </a>
                        </li>
                        <li class="menu-item">
                            <a href="/dms/standardArea/managementPage" class="menu-link">
                                <div data-i18n="Notifications">관리</div>
                            </a>
                        </li>
                        <!-- <li class="menu-item">
                            <a href="/dms/collectionBook/page" class="menu-link">
                                <div data-i18n="Notifications">출력</div>
                            </a>
                        </li>-->
                    </ul>
                </li>

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
                        <!--<label class="input-group-text" for="selectStandardArea"> 분류 체계</label>
                        <select id="selectStandardArea" class="form-select color-dropdown" onchange="handleSelectChange(this)">
                            <option value="" selected disabled hidden></option>
                            <c:forEach var="std" items="${sessionScope.stdList}">
                                <option value="${std.stdAreaNm}">${std.stdAreaNm}</option>
                            </c:forEach>
                        </select>-->
                    </div>
                    <!-- STD_AREA_PICKER -->
                </div>
            </nav>
            <!-- / Navbar -->

            <!-- Content wrapper -->
            <div class="content-wrapper">
                <!-- Content -->
                <div class="container-xxl flex-grow-1 container-p-y">

                    <!-- Title -->
                    <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-bold"> 권한 / </span> 그룹별 권한 </h4>
                    <!-- Title -->

                    <!-- 여기에 내용 넣기 -->
                    
                    <div class="row">
	                <!-- Basic Layout -->
	                <div class="col-xxl">
                 	  <!-- Bootstrap Table with Header - Dark -->
		              <div class="card">
		              <div class="row align-items-center" style="padding-right: 24px;">
		    				<div class="col">
		        				<h5 class="card-header mb-0">그룹 목록</h5>
		    				</div>
		    				<div class="col-auto">
		        				<button class="btn btn-secondary btn-lg" type="button" id="openModalBtnForInsertNewGroup">신규 등록</button>
		   					</div>
						</div>
						<div class="row">
			                <div class="col-md mt-3">
		                         <div class="input-group">
		                          <select id="searchCondition" class="form-select color-dropdown" style="margin-left: 157px; margin-right: -21px; border-radius: 0.375rem;">
		                              <option value="" selected disabled>검색 조건</option>
		                              <option value="ID">그룹ID</option>
		                              <option value="NAME">그룹명</option>
		                          </select><label class="form-label"></label>
		                         </div>
		                     </div>
		                     <div class="col-md mt-3 me-4">
		                         <div class="input-group">
		                             <input class="form-control" type="search" placeholder="검색하기.."
		                                    aria-label="Search" id="searchForGroup">
		                             <label style="cursor: pointer;" class="input-group-text" id="searchForGroupBtn"
		                                    for="searchForGroupBtn">검색</label>
		                         </div>
		                     </div>
		                 </div>
		                <div class="table-responsive text-nowrap">
			              <div class="card-body both-scrollbars-example">
			              <table class="table table-bordered table-hover">
		                      <thead>
		                        <tr>
		                          <th>NO</th>
		                          <th>그룹ID</th>
		                          <th>그룹명</th>
		                        </tr>
		                      </thead>
		                      <tbody id="allGroupTbody">
		                        <c:forEach items="${groupDTOList}" var="groupDTO" varStatus="status">
		                          <tr id="groupRow_${groupDTO.groupId}">
		                            <td scope="row">${status.index + 1}</td>
		                            <td name="groupId">${groupDTO.groupId}</td>
		                            <td name="groupName">${groupDTO.groupName}</td>
		                          </tr>
		                          <tr id="groupRow_detail_${groupDTO.groupId}" style="display:none;">
		                          	<td colspan="3" id="detail_content_${groupDTO.groupId}" style="display:none;">
					                    <div class="card-header d-flex align-items-center justify-content-between">
						                    <div class="col">
						                      <h5 class="mb-0">그룹 정보</h5>
					                        </div>
						                    <div class="col-auto">
   						        			  <button class="btn btn-secondary btn-lg" type="button" id="updateGroupBtn_${groupDTO.groupId}">수정</button>
						        			  <button class="btn btn-secondary btn-lg" type="button" id="addIndividualToGroupBtn_${groupDTO.groupId}">추가/삭제</button>
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
					                                id="basic-icon-default-description_${groupDTO.groupId}"
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
												  <div class="btn-group" id="memberTagBtns_${groupDTO.groupId}" role="group" style="gap: 10px; flex-wrap: wrap;">
												  </div>
					                            </div>
					                          </div>
					                        </div>
					                      </form>
					                    </div>
			                		  </td>
		                          </tr>
		                        </c:forEach>
		                      </tbody>
		                    </table>
			              </div>
		                </div>
		              </div>
              		  <!--/ Bootstrap Table with Header Dark -->
                	  </div>
		              <div class="col-xxl">
			              <div class="card">
			                <div class="row align-items-center" style="padding-right: 24px;">
			    				<div class="col">
			        				<h5 class="card-header mb-0">그룹별 권한</h5>
			    				</div>
			    				<div class="col-auto">
			        				<button class="btn btn-secondary btn-lg" type="button" id="updateAuthBtn">수정</button>
			   					</div>
							</div>
				              <div class="card-body both-scrollbars-example">
				              <table class="table table-hover">
			                      <thead>
			                        <tr>
			                          <th>NO</th>
			                          <th>프로젝트명</th>
			                          <th>권한</th>
			                        </tr>
			                      </thead>
			                      <tbody id="groupAuthorization">
			                      </tbody>
			                    </table>
				              </div>
			              </div>
		              </div>
		              </div>
		
		              <!-- 여기에 내용 넣기 -->

                </div>
                <!-- Content -->
                
                <!-- 신규 등록 모달 -->
                    <div class="modal fade" id="insertNewGroupModal" tabindex="-1" style="display: none;" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="insertNewGroupTitle">신규 등록</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" onclick="refreshPage()"></button>
                                </div>
                                <div class="modal-body">
                                	<div class="row">
                                        <div class="col mb-3">
                                            <div class="input-group">
                                                <label for="groupId" class="input-group-text" style="width: 100px;">그룹ID
                                                    &nbsp;&nbsp;&nbsp; </label>
                                                <input type="text" id="groupId" class="form-control"
                                                       placeholder="그룹ID">
                                                <label style="cursor: pointer;" class="input-group-text"
                                                       for="groupIdValidation" id="groupIdValidation">중복확인</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row" id="groupIdValidationRow" style="display: none;">
                                        <div class="col mb-3">
                                            <div class="input-group" id="groupIdValidationMessage">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col mb-3">
                                            <div class="input-group">
                                                <label for="groupName" class="input-group-text" style="width: 100px;">그룹명
                                                    &nbsp;&nbsp;&nbsp; </label>
                                                <input type="text" id="groupName" class="form-control"
                                                       placeholder="그룹명">
                                                <label style="cursor: pointer;" class="input-group-text"
                                                       for="groupNameValidation" id="groupNameValidation">중복확인</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row" id="groupNameValidationRow" style="display: none;">
                                        <div class="col mb-3">
                                            <div class="input-group" id="groupNameValidationMessage">
                                            </div>
                                        </div>
                                    </div>
                                    <!--<div class="row">
                                        <div class="col mb-3">
                                            <div class="input-group">
                                                <label for="groupName" class="input-group-text" style="width: 100px;">그룹명
                                                    &nbsp;&nbsp;&nbsp; </label>
                                                <input type="text" id="groupName" class="form-control"
                                                       placeholder="그룹명">
                                            </div>
                                        </div>
                                    </div>-->
                                    <div class="row">
                                        <div class="col mb-3">
                                            <div class="input-group">
                                                <label for="groupDescription" class="input-group-text" style="width: 100px;">설명
                                                    &nbsp;&nbsp;&nbsp; </label>
                                                <input type="text" id="groupDescription" class="form-control"
                                                       placeholder="설명">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                    	<div class="col mb-3">
                                            <select class="form-select color-dropdown" id="subjAreaIdList" name="subjAreaIdList">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="row">
                                    	<h5 class="card-header mb-0">그룹별 권한</h5>
							              <div class="card-body both-scrollbars-example">
							              <table class="table table-hover">
						                      <thead>
						                        <tr>
						                          <th>NO</th>
						                          <th>프로젝트명</th>
						                          <th>권한</th>
						                        </tr>
						                      </thead>
						                      <tbody id="newGroupAuthorization">
					                      	  </tbody>
					                      </table>
							              </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary" id="insertNewGroupBtn">등록</button>
                                    <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal" onclick="refreshPage()">취소</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 신규 등록 모달 -->
                    
                    <!-- 사용자 조회, 추가 및 삭제 모달 -->
                    <div class="modal fade" id="individualSearchModal" tabindex="-1" style="display: none;"
                         aria-hidden="true"
                         data-bs-backdrop="static">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="individualSearchTitle">사용자 추가/삭제</h5>
                                    <button type="button" class="btn-close" id="closeIndividualSearchModalIcon" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col mb-3">
                                            <div class="input-group">
                                                <label for="targetUserName" class="input-group-text">사용자명</label>
                                                <input type="text" id="targetGroupId" hidden>
                                                <input type="text" id="targetUserName" class="form-control" placeholder="사용자명">
                                                <label style="cursor: pointer;" class="input-group-text" for="searchUserName" id="searchUserName">조회</label>
                                            </div>
                                            <div class="table-responsive text-nowrap" id="userListTable">
                                                <table class="table">
                                                    <thead>
                                                    <tr class="text-nowrap">
                                                        <th>번호</th>
                                                        <th>그룹명</th>
                                                        <th>추가/삭제</th>
                                                        <th>사용자ID</th>
                                                        <th>사용자명</th>
                                                        <th>이메일</th>
                                                        <th>사용유무</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody id="userNameSearchResultTbody">
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer" style="padding-right: 0;">
                                        <button type="button" class="btn btn-outline-secondary" id="closeIndividualSearchModal" data-bs-dismiss="modal">
                                            닫기
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 사용자 조회, 추가 및 삭제 모달 -->
                    
                    <!-- 특정 그룹의 사용자 정보 -->
                    <div class="modal fade" id="individualInfoOfGroupModal" tabindex="-1" style="display: none;" aria-hidden="true" data-bs-backdrop="static">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="individualInfo">사용자 정보</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                	<div class="row">
                                        <div class="col mb-3">
                                            <div class="input-group">
                                                <label for="userId" class="input-group-text" style="width: 140px;">사용자ID</label>
                                                <input type="text" id="userId" class="form-control" readonly>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col mb-3">
                                            <div class="input-group">
                                                <label for="userName" class="input-group-text" style="width: 140px;">사용자명</label>
                                                <input type="text" id="userName" class="form-control" readonly>
                                            </div>
                                        </div>
                                    </div>
			                        <div class="row">
                                        <div class="col mb-3">
                                            <div class="input-group">
                                                <label for="useYn" class="input-group-text" style="width: 140px;">사용유무</label>
                                                <input type="text" id="useYn" class="form-control" readonly>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                    	<h5 class="card-header mb-0">그룹 목록</h5>
							              <div class="card-body both-scrollbars-example">
							              <table class="table table-hover">
						                      <thead>
						                        <tr>
						                          <th>NO</th>
						                          <th>그룹ID</th>
						                          <th>그룹명</th>
						                        </tr>
						                      </thead>
						                      <tbody id="groupList">
					                      	  </tbody>
					                      </table>
							              </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">닫기</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 특정 그룹의 사용자 정보 -->
                    
                    <!-- 그룹 정보 수정 모달 -->
                    <div class="modal fade" id="updateGroupInfoModal" tabindex="-1" style="display: none;" aria-hidden="true" data-bs-backdrop="static">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="updateGroupInfoTitle">그룹 정보 수정</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" onclick="resetupdateGroupInfoModal()"></button>
                                </div>
                                <div class="modal-body">
                                	<div class="row">
                                        <div class="col mb-3">
                                            <div class="input-group">
                                                <label for="currentGroupName" class="input-group-text" style="width: 110px;">현재 그룹명</label>
                                                <input type="text" id="currentGroupName" class="form-control" readonly>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col mb-3">
                                            <div class="input-group">
                                                <label for="newGroupName" class="input-group-text" style="width: 110px;">새 그룹명</label>
                                                <input type="text" id="newGroupName" class="form-control" placeholder="새 그룹명">
                                                <label style="cursor: pointer;" class="input-group-text"
                                                       for="newGroupNameValidation" id="newGroupNameValidation">중복확인</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row" id="newGroupNameValidationRow" style="display: none;">
                                        <div class="col mb-3">
                                            <div class="input-group" id="newGroupNameValidationMessage">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col mb-3">
                                            <div class="input-group">
                                                <label for="newGroupDescription" class="input-group-text" style="width: 110px;">설명</label>
                                                <input type="text" id="newGroupDescription" class="form-control"
                                                       placeholder="설명">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary" id="updateGroupInfoBtn">수정</button>
                                    <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal" onclick="resetupdateGroupInfoModal()">취소</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 그룹 정보 수정 모달 -->

                <div id="alertContainer"
                     style="position: fixed; bottom: 10px; left: 50%; transform: translateX(-50%);"></div>
                <div class="content-backdrop fade"></div>
                <div class="spinner-border spinner-border-lg text-primary" role="status" id="loading-spinner"
                     style="position: fixed; top: 10px; left: 50%; z-index: 9999; display: none">
                    <span class="visually-hidden">Loading...</span>
                </div>
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
<script src="/js/authorization/group.js"></script>
<script src="/js/session/session.js"></script>

<!-- Place this tag in your head or just before your close body tag. -->
<script async defer src="https://buttons.github.io/buttons.js"></script>
</body>
</html>
