<%--
  Created by IntelliJ IDEA.
  User: taewan
  Date: 2023/07/26
  Time: 10:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> ezmeal | admin_home</title>
    <link rel="stylesheet" href="/css/screens/admin_home.css">
</head>
<body>
<!-- 관리자 페이지 헤더 -->
<%@ include file="admin_header.jsp" %>  <!-- 관리자 헤더 인클루드 -->

<!-- 헤더 아래부분 전체 div
[ 메뉴 div /  관리자 담당 페이지 div  ] -->
<div class="all_container_div">

    <%@ include file="admin_menu.jsp" %> <!-- 관리자 메뉴 인클루드 -->

    <div class="admin_container_div">
        <div class="all">
            <div class="admin_graph">
                <a href="/admin/order/dashboard" class="chart_link">
                    <h1>주문</h1>
                </a>
                <div class="chart_div chart_div_order">
                    <h3 class="chart_name chart_name_order">주문,교환,반품 추이</h3>
                    <h4 class="chart_sub_name chart_sub_name_order">2023년 주문 통계</h4>
                    <div id="chart_shape1"></div>
                </div>
            </div>

            <div class="admin_graph">
                <a href="/admin/prod/home" class="chart_link">
                    <h1>상품</h1>
                </a>
                <div class="chart_div">
                    <h3 class="chart_name">카테고리별 상품 판매량</h3>
                    <h4 class="chart_sub_name chart_sub_name_order">2023년 7월 상품판매 현황</h4>
                    <div id="chart_shape2"></div>
                </div>
            </div>
            <div class="admin_graph">
                <a href="/admin/member" class="chart_link">
                    <h1>회원</h1>
                </a>
                <div class="chart_div">
                    <h3 class="chart_name">회원가입, 접속자 수</h3>
                    <h4 class="chart_sub_name chart_sub_name_order">2023년 통계</h4>
                    <div id="chart_shape3"></div>
                </div>
            </div>
            <div class="admin_graph">
                <a href="/admin/notice/dashboard" class="chart_link">
                    <h1>게시판</h1>
                </a>
                <div class="chart_div">
                    <h3 class="chart_name">게시판관련 제목</h3>
                    <h4 class="chart_sub_name chart_sub_name_order">부제</h4>
                    <div id="chart_shape4"></div>
                </div>
            </div>
        </div>
    </div>


    <!--공통 그래프-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

    <script src="/javascript/admin_home_member.js"></script>
    <script src="/javascript/admin_home_order.js"></script>
    <script src="/javascript/admin_home_board.js"></script>
    <script src="/javascript/admin_home_product.js"></script>
</div>
</body>
</html>


