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
    <title>ezmeal_admin</title>
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
        <div class="all_top_title">
            <h1>상품 대시보드</h1>
        </div>
        <div class="all">

            <div class="admin_graph">
                <h1>상품현황</h1>
                <div class="chart_div">
                    <h3 class="chart_name">카테고리별 상품 판매 품목</h3>
                    <h4 class="chart_sub_name chart_sub_name_order">집계일 : 2023-08-10</h4>
                    <div id="chart_shape1"></div>
                </div>
            </div>

            <div class="admin_graph">
                <h1>상품</h1>
                <div class="chart_div">
                    <h3 class="chart_name">카테고리별 상품 판매량</h3>
                    <h4 class="chart_sub_name chart_sub_name_order">2023년 7월 상품판매 현황</h4>
                    <div id="chart_shape2"></div>
                </div>
            </div>

            <div class="admin_graph">
                <h1>판매 인기상품</h1>
                <div class="chart_div chart_div_order">
                    <h3 class="chart_name">최근 3주간 인기상품</h3>
                    <h4 class="chart_sub_name chart_sub_name_order">최근 3주간 인기상품</h4>
                    <div id="chart_shape3"></div>
                </div>
            </div>


            <div class="admin_graph">
                <h1>재고 현황</h1>
                <div class="chart_div">
                    <h3 class="chart_name">재고량 주의 상품목록</h3>
                    <h4 class="chart_sub_name chart_sub_name_order">2023년 7월 상품판매 현황</h4>
                    <div id="chart_shape4"></div>
                </div>
            </div>

            <div class="admin_graph">
                <h1>이벤트 상품</h1>
                <div class="chart_div">
                    <h3 class="chart_name">이벤트 상품 판매 추이</h3>
                    <h4 class="chart_sub_name chart_sub_name_order">해당상품: 여름 한정 판매 밀키트 5종</h4>
                    <div id="chart_shape5"></div>
                </div>
            </div>

            <div class="admin_graph">
                <h1>입고 예정 상품</h1>
                <div class="chart_div">
                    <h2 class="chart_name">[여름 한정 판매 밀키트]</h2>
                    <div id="chart_shape6"></div>
                </div>
            </div>

            <div class="admin_graph">
                <h1>막대 테스트 [2]</h1>
                <div class="chart_div">
                    <div id="chart_shape7"></div>
                </div>
            </div>
            <div class="admin_graph">
                <h1>막대 테스트 [3]</h1>
                <div class="chart_div">
                    <div id="chart_shape8"></div>
                </div>
            </div>
            <div class="admin_graph">
                <h1>막대 테스트 [4]</h1>
                <div class="chart_div">
                    <div id="chart_shape9"></div>
                </div>
            </div>

        </div>


    </div>


    <!--공통 그래프-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

    <script src="/javascript/admin_prod_home_dashboard.js"></script>


</body>
</html>
