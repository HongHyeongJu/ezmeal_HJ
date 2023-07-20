<%--
  Created by IntelliJ IDEA.
  User: hubaek
  Date: 2023/07/13
  Time: 8:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Title</title>
  <link rel="stylesheet" href="/css/headerFooter_dropdown.css" />
</head>
<body>
<!--start : 첫번째 헤더(top)-->
<div class="header_box">
  <div class="header_top">
    <div class="logo-box">
      <a href="/">
        <img src="/img/main/EZMEAL_LOGO.jpg" class="ezmeal_logo" />
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
          <i class="fas fa-search" style="font-size: 28px;"></i>
        </a>
      </div>
    </div>
    <!--end : searchbox -->

    <!--start : icon box-->
    <!--start : 아이콘 누르면 나오는 user 드롭다운메뉴-->
    <div class="icon_box">
      <label class="icon_inner_dropdown" for="user_menu">
        <i class="far fa-user" style=" font-size: 32px;"></i>
      </label>
      <input id="user_menu" type="checkbox">
      <nav id="user_menu_nav">
        <ul class="user_menu_drop">
          <li class="user_menu_drop_list"><a href="#">마이페이지</a></li>
          <li class="user_menu_drop_list"><a href="#">로그아웃</a></li>
        </ul>
      </nav>
      <!--end : 아이콘 누르면 나오는 user 드롭다운메뉴-->
      <div class="icon_inner_box">
        <a href="#">
          <i class="far fa-heart" style=" font-size: 32px;"></i>
        </a>
      </div>
      <div class="icon_inner_box">
        <a href="#">
          <i class="fas fa-shopping-cart" style=" font-size: 30px;"></i>
        </a>
      </div>
    </div>
  </div>

  <!--end : icon box-->

  <!--end : header-top-->

  <!--start : header-bottom-->
  <div class="header-bottom">
    <ul class="sidebar">
      <input type="checkbox" class="openSidebarMenu" id="openSidebarMenu" />
      <label for="openSidebarMenu" class="sidebarIconToggle">
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
          <!--start : 신선식품-->
          <li class="sidebarMenuInner-list">
            <a href="#">
              <i class="fas fa-apple-alt"></i>
              신선식품</a>
            <ul class="sidebarMenuInner-Inner">
              <li class="sidebarMenuInner-Innerlist">
                <a href="#">전체</a>
              </li>
              <li class="sidebarMenuInner-Innerlist">
                <a href="#">채소</a>
              </li>
              <li class="sidebarMenuInner-Innerlist">
                <a href="#">과일</a>
              </li>
            </ul>
          </li>

          <!--start : 정육-->
          <li class="sidebarMenuInner-list">
            <a href="#">
              <i class="fas fa-egg"></i>
              정육</a>
            <ul class="sidebarMenuInner-Inner">
              <li class="sidebarMenuInner-Innerlist">
                <a href="#">전체</a>
              </li>
              <li class="sidebarMenuInner-Innerlist">
                <a href="#">닭고기</a>
              </li>
              <li class="sidebarMenuInner-Innerlist">
                <a href="#">돼지고기</a>
              </li>
              <li class="sidebarMenuInner-Innerlist">
                <a href="#">소고기</a>
              </li>
            </ul>

            <!--start : 샐러드-->
          </li>
          <li class="sidebarMenuInner-list">
            <a href="#">
              <i class="fas fa-seedling"></i>샐러드</a>
            <ul class="sidebarMenuInner-Inner">
              <li class="sidebarMenuInner-Innerlist">
                <a href="#">전체</a>
              </li>
              <li class="sidebarMenuInner-Innerlist">
                <a href="#">샐러드</a>
              </li>
              <li class="sidebarMenuInner-Innerlist">
                <a href="#">소스</a>
              </li>
            </ul>
          </li>

          <!--start : 간편식-->
          <li class="sidebarMenuInner-list">
            <a href="#">
              <i class="fas fa-bread-slice"></i>
              간편식</a>
            <ul class="sidebarMenuInner-Inner">
              <li class="sidebarMenuInner-Innerlist">
                <a href="#">전체</a>
              </li>
              <li class="sidebarMenuInner-Innerlist">
                <a href="#">볶음밥 | 도시락</a>
              </li>
              <li class="sidebarMenuInner-Innerlist">
                <a href="#">밀키트</a>
              </li>
            </ul>
          </li>
        </ul>
      </div>
      <!--end : sidebarMenu-->
    </ul>

    <div class="horizonMenu-list">
      <li><a href="#">신상품</a></li>
      <li><a href="#">베스트</a></li>
      <li><a href="#">알뜰쇼핑</a></li>
      <li><a href="#">특가 | 혜택</a></li>
    </div>

    <div class="empty"></div>
  </div>
  <!-- end : header-bottom -->
</div>
  <!-- end : header_box -->

<!--start : scroll back to top-->
<button id="backtotop-btn"><a href="#top" style="color: white">Top</a></button>

<!--end : back to top button-->
<script
        src="https://kit.fontawesome.com/6478f529f2.js"
        crossorigin="anonymous"
></script>
</body>
</html>
