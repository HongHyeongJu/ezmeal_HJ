<%--
  Created by IntelliJ IDEA.
  User: hhju2
  Date: 2023-07-20
  Time: AM 9:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ezmeal 관리자 | 상품 대시보드</title>
    <link rel="stylesheet" href="/css/screens/admin_prod_home.css">

</head>
<body>
    <!-- 관리자 페이지 헤더 -->
    <%@ include file="admin_header.jsp" %>  <!-- 관리자 헤더 인클루드 -->

    <!-- 헤더 아래부분 전체 div
    [ 메뉴 div /  관리자 담당 페이지 div  ] -->
    <div class="all_container_div">

        <%@ include file="admin_menu.jsp" %> <!-- 관리자 메뉴 인클루드 -->


    <div class="admin_container_div">
        <div class="go_up_div">
        <div class="all_top_title">
            <h1>상품 대시보드</h1>
        </div>
        <div class="all">

            <div class="admin_graph">
                <h1>상품 품목</h1>
                <div class="chart_div">
                    <h3 class="chart_name">카테고리별 상품 판매 품목</h3>
                    <h4 class="chart_sub_name chart_sub_name_order">집계일 : 2023-08-10</h4>
                    <div id="chart_shape1"></div>
                </div>
            </div>

            <div class="admin_graph">
                <h1>상품 판매량</h1>
                <div class="chart_div">
                    <h3 class="chart_name">카테고리별 상품 판매량</h3>
                    <h4 class="chart_sub_name chart_sub_name_order">2023년 7월 상품판매 현황</h4>
                    <div id="chart_shape2"></div>
                </div>
            </div>

            <div class="admin_graph">
                <h1>판매 인기상품</h1>
                <div class="chart_div chart_div_order">
                    <h3 class="chart_name chart_name_2">최근 3주간 인기상품</h3>
                    <h4 class="chart_sub_name chart_sub_name_order">별점평균 4.5이상, 상품판매량 순</h4>
                    <div id="chart_shape3"></div>
                </div>
            </div>


            <div class="admin_graph">
                <h1>상품 재고</h1>
                <div class="chart_div">
                    <h3 class=" chart_name">재고량 주의 상품목록</h3>
                    <h4 class="chart_sub_name chart_sub_name_order">재고 주의시 수량 확인바랍니다.</h4>
                    <div id="chart_shape4"></div>
                </div>
            </div>

            <div class="admin_graph">
                <h1>이벤트 상품</h1>
                <div class="chart_div">
                    <h3 class="chart_name">이벤트 상품 판매 추이</h3>
                    <h4 class="chart_sub_name chart_sub_name_order">* 여름 한정 판매 밀키트 A,B세트</h4>
                    <div id="chart_shape5"></div>
                </div>
            </div>

            <div class="admin_graph">
                <h1>입고 예정</h1>
                <div class="chart_div">
                    <h3 class="chart_name">9월 신상품</h3>
                    <h4 class="chart_sub_name chart_sub_name_order">입고 예정일 확인</h4>
                    <div id="chart_shape6"></div>
                </div>
            </div>
        </div>
        </div>
    </div>


    <!--공통 그래프-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

    <script src="/javascript/admin_prod_home_dashboard.js"></script>


</body>
</html>
