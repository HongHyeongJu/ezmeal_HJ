<%--
  Created by IntelliJ IDEA.
  User: taewan
  Date: 2023/08/08
  Time: 2:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> ezmeal | admin_order-dashboard</title>
    <link rel="stylesheet" href="/css/screens/admin_order_dashboard.css">
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
                <h1>주간 주문</h1>
                <div class="chart_div ">
                    <h3 class="chart_name ">주간 주문량 및 주문 접수 현황</h3>
                    <h4 class="chart_sub_name ">2023년 08월 2주차</h4>
                    <div id="chart_shape1"></div>
                </div>
            </div>

            <div class="admin_graph">
                <h1>주간 배송</h1>
                <div class="chart_div">
                    <h3 class="chart_name">주간 배송요구 현황 및 배송중 현황</h3>
                    <h4 class="chart_sub_name ">2023년 08월 2주차</h4>
                    <div id="chart_shape2"></div>
                </div>
            </div>
            <div class="admin_graph">
                <h1>주간 취소</h1>
                <div class="chart_div">
                    <h3 class="chart_name">주간 취소 현황 및 주간 취소 접수 현황</h3>
                    <h4 class="chart_sub_name ">2023년 08월 2주차</h4>
                    <div id="chart_shape3"></div>
                </div>
            </div>

            <div class="admin_graph">
                <h1>주간 반품</h1>
                <div class="chart_div">
                    <h3 class="chart_name">주간 반품 현황 및 주간 반품 접수 현황</h3>
                    <h4 class="chart_sub_name ">2023년 08월 2주차</h4>
                    <div id="chart_shape4"></div>
                </div>
            </div>

        </div>
    </div>


    <!--공통 그래프-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script src="/javascript/admin_order_dashboard.js"></script>
</div>
</body>
</html>


