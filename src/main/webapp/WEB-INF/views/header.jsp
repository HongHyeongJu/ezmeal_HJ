<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hubaek
  Date: 2023/07/13
  Time: 8:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="loginOutLink" value="${sessionScope.memberId==null ? '/login' : '/logout'}"/>
<c:set var="loginOut" value="${sessionScope.memberId==null ? '로그인' : '로그아웃'}"/>
<html>
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />

  <link rel="stylesheet" href="/css/header.css" />
</head>
<body>
<!--start : 첫번째 헤더(top)-->
<div class="header_box">
  <div class="header_top">
    <div class="logo-box">
      <a href="/">
        <img src="/img/main/ezmeal_logo.png" class="ezmeal_logo" />
        <img src="/img/main/ezmeal.png" class="ezmeal_letter">
      </a>
    </div>

    <div class="search_box">
      <input
              id="search"
              placeholder="검색어를 입력해주세요"
              class="search_inner_box"
      />
      <div class="search-btn">
        <a href="#">
          <svg focusable="false" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" ><path d="M15.5 14h-.79l-.28-.27A6.471 6.471 0 0 0 16 9.5 6.5 6.5 0 1 0 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"></path></svg>
        </a>
      </div>
    </div>
    <!--end : searchbox -->

    <!--start : icon box-->
    <div class="icon_box">
      <div class="icon_inner_dropdown" for="user_menu">
          <a href="<c:url value='${loginOutLink}'/>">
            <svg xmlns="http://www.w3.org/2000/svg" width="31" height="31" viewBox="0 0 30 30">
              <g data-name="그룹 4823">
                <path data-name="패스 26" d="M52.156 32.352s.48-9.291 12.787-8.954 12.308 8.954 12.308 8.954" transform="translate(-49.156 -6.414)"
                        style="
                                stroke-linecap: round;
                                stroke: #00c728;
                                stroke-width: 3px;
                                fill: ${not empty memberId ? '#00c728' : 'none'};"/>
                <g data-name="타원 5" transform="translate(9.349 2)" style="stroke: #00c728; stroke-width: 3px; fill: none">
                  <circle cx="6.349" cy="6.349" r="6.349" style="stroke: none"/>
                  <circle cx="6.349" cy="6.349" r="5.349" style="fill: none"/>
                </g>
              </g>
            </svg>
          </a>
      </div>

      <c:if test="${not empty memberId}">
        <div class="icon_inner_box">
          <a href="/orderPayment">
            <?xml version="1.0" ?>
            <svg xmlns="http://www.w3.org/2000/svg" height="2em" viewBox="0 0 512 512">
              <path d="M458.4 64.3C400.6 15.7 311.3 23 256 79.3 200.7 23 111.4 15.6 53.6 64.3-21.6 127.6-10.6 230.8 43 285.5l175.4 178.7c10 10.2 23.4 15.9 37.6 15.9 14.3 0 27.6-5.6 37.6-15.8L469 285.6c53.5-54.7 64.7-157.9-10.6-221.3zm-23.6 187.5L259.4 430.5c-2.4 2.4-4.4 2.4-6.8 0L77.2 251.8c-36.5-37.2-43.9-107.6 7.3-150.7 38.9-32.7 98.9-27.8 136.5 10.5l35 35.7 35-35.7c37.8-38.5 97.8-43.2 136.5-10.6 51.1 43.1 43.5 113.9 7.3 150.8z"
                    style="fill: #00c728"/>
            </svg>
          </a>
        </div>
      </c:if>

      <div class="icon_inner_box">
        <a href="/cart">
          <svg xmlns="http://www.w3.org/2000/svg" width="35" height="35" viewBox="0 0 30 30">
            <g data-name="그룹 4845" transform="translate(-1635.719 -78.854)">
              <rect data-name="사각형 4912" width="3.244" height="3.244" rx="1.622" transform="translate(1645.071 104.9)" style="fill: #00c728"/>
              <rect data-name="사각형 4913" width="3.244" height="3.244" rx="1.622" transform="translate(1652.737 104.9)" style="fill: #00c728"/>
              <path data-name="패스 944" d="M947.719 2245.563h6.515v16h14.291l3.194-11.378h-17.485" transform="translate(689.081 -2161.756)"
                    style="
                    fill: none;
                    stroke: #00c728;
                    stroke-linecap:round;
                    stroke-linejoin: round;
                    stroke-width: 2.5px;"/>
            </g>
          </svg>
        </a>
      </div>
    </div>
    <!--end : icon box-->
  </div>
</div>
<!--end : header-top-->

<!--start : header-bottom-->
<div class="header-bottom"  id="header-bottom">
  <ul class="sidebar">
    <input type="checkbox" class="openSidebarMenu" id="openSidebarMenu" />
    <label for="openSidebarMenu" class="sidebarIconToggle" id="sidebarIconToggle">
      <div class="spinner diagonal part-1"></div>
      <!--햄버거 첫째줄-->
      <div class="spinner horizontal"></div>
      <!--햄버거 둘째줄-->
      <div class="spinner diagonal part-2"></div>
      <!--햄버거 셋째줄-->
    </label>

    <!--start : sidebar 내용-->
    <div id="sidebarMenu">
      <ul class="sidebarMenuInner">
        <!--start : 정육-->
        <li class="sidebarMenuInner-list">
          <i class="fa-solid fa-drumstick-bite"></i>
          <a href="/product/catelist?cate_cd=01">&nbsp;닭가슴살</a>
          <ul class="sidebarMenuInner-Inner">
            <li class="sidebarMenuInner-Innerlist"><a href="/product/catelist?cate_cd=010">전체</a></li>
            <li class="sidebarMenuInner-Innerlist"><a href="/product/catelist?cate_cd=0101">닭가슴살</a></li>
            <li class="sidebarMenuInner-Innerlist"><a href="/product/catelist?cate_cd=0102">스테이크</a></li>
            <li class="sidebarMenuInner-Innerlist"><a href="/product/catelist?cate_cd=0103">소시지</a></li>
          </ul>
        </li>


        <!--도시락, 볶음밥-->
        <li class="sidebarMenuInner-list">
          <a href="/product/catelist?cate_cd=02">
            <i class="fa-solid fa-bowl-rice"></i>&nbsp;도시락 | 볶음밥</a>
          <ul class="sidebarMenuInner-Inner">
            <li class="sidebarMenuInner-Innerlist"><a href="/product/catelist?cate_cd=020">전체</a></li>
            <li class="sidebarMenuInner-Innerlist"><a href="/product/catelist?cate_cd=0201">도시락</a></li>
            <li class="sidebarMenuInner-Innerlist"><a href="/product/catelist?cate_cd=0202">볶음밥</a></li>
          </ul>
        </li>

        <!--start : 샐러드-->

        <li class="sidebarMenuInner-list">
          <a href="/product/catelist?cate_cd=03"><i class="fas fa-seedling"></i>&nbsp;샐러드</a>
          <ul class="sidebarMenuInner-Inner">
            <li class="sidebarMenuInner-Innerlist"><a href="/product/catelist?cate_cd=030">전체</a></li>
            <li class="sidebarMenuInner-Innerlist"><a href="/product/catelist?cate_cd=0301">샐러드</a></li>
            <li class="sidebarMenuInner-Innerlist"><a href="/product/catelist?cate_cd=0302">소스</a></li>
          </ul>
        </li>

        <!--start : 신선식품-->
        <li class="sidebarMenuInner-list">
          <a href="/product/catelist?cate_cd=04"><i class="fas fa-apple-alt"></i>&nbsp;신선식품</a>
          <ul class="sidebarMenuInner-Inner">
            <li class="sidebarMenuInner-Innerlist"><a href="/product/catelist?cate_cd=040">전체</a></li>
            <li class="sidebarMenuInner-Innerlist"><a href="/product/catelist?cate_cd=0401">채소 | 과일</a></li>
            <li class="sidebarMenuInner-Innerlist"><a href="/product/catelist?cate_cd=0402">달걀</a></li>
          </ul>
        </li>


        <!--start : 즉석 간편식-->
        <li class="sidebarMenuInner-list">
          <a href="/product/catelist?cate_cd=05"><i class="fas fa-bread-slice"></i>&nbsp;즉석 간편식</a>
          <ul class="sidebarMenuInner-Inner">
            <li class="sidebarMenuInner-Innerlist"><a href="/product/catelist?cate_cd=050">전체</a></li>
            <li class="sidebarMenuInner-Innerlist"><a href="/product/catelist?cate_cd=0501">분식 </a></li>
            <li class="sidebarMenuInner-Innerlist"><a href="/product/catelist?cate_cd=0502">반찬 | 밀키트</a></li>
            <li class="sidebarMenuInner-Innerlist"><a href="/product/catelist?cate_cd=0503">면 </a></li>
            <li class="sidebarMenuInner-Innerlist"><a href="/product/catelist?cate_cd=0504">소스 </a></li>
          </ul>
        </li>
      </ul>
    </div>
  </ul>

    <div class="horizonMenu-list" id="horizonMenu-list">
      <li><a href="/product/headerlist?headertyp=new">신상품</a></li>
      <li><a href="/product/headerlist?headertyp=best">베스트</a></li>
      <li><a href="/ezDelivery"><img src="/img/main/ez_deli_logo_line.png" class="ezdeliveryimg"></a></li>
      <li><a href="/product/headerlist?headertyp=bigdc">특가 | 혜택</a></li>
      <li><a href="/notice">고객센터</a></li>
    </div>


</div>



<!--start : scroll back to top-->
<button id="backtotop-btn"><a href="#top" style="color: white">Top</a></button>

<!--end : back to top button-->
<script src="https://kit.fontawesome.com/3dd102f0de.js" crossorigin="anonymous"></script>
</body>
</html>
