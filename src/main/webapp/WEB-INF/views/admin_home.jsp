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
</head>
<body>
<!-- 관리자 페이지 헤더 -->
<%@ include file="admin_header.jsp" %>  <!-- 관리자 헤더 인클루드 -->

<!-- 헤더 아래부분 전체 div
[ 메뉴 div /  관리자 담당 페이지 div  ] -->
<div class="all_container_div">

    <%@ include file="admin_menu.jsp" %> <!-- 관리자 메뉴 인클루드 -->

    <div class="admin_container_div">
        <!-- admin_container_div 내용.
        각자 맡은 관리자 페이지 내용 이 디브 안에 쓰면 됩니다.  -->
        <h1>admin home 입니다.</h1>
    </div>
    <div class="all">
        <div class="admin_graph">
            <h1>주문</h1>
            <div class="chart_div">
                <h2 class="chart_name">주문량</h2>
                <div id="chart_shape1"></div>
            </div>
        </div>

        <div class="admin_graph">
            <h1>상품</h1>
            <div class="chart_div">
                <h2 class="chart_name">홍형주</h2>
                <div id="chart_shape2"></div>
            </div>
        </div>
        <div class="admin_graph">
            <h1>회원</h1>
            <div class="chart_div">
                <h2 class="chart_name">백현욱</h2>
                <div id="chart_shape3"></div>
            </div>
        </div>
        <div class="admin_graph">
            <h1>게시판</h1>
            <div class="chart_div">
                <h2 class="chart_name">이나경</h2>
                <div id="chart_shape4"></div>
            </div>
        </div>
    </div>

    <!--주문 그래프-->
    <script
            type="text/javascript"
            src="https://www.gstatic.com/charts/loader.js"
    ></script>
    <!--상품 그래프-->
    <script
            type="text/javascript"
            src="https://www.gstatic.com/charts/loader.js"
    ></script>
    <!--회원 그래프-->
    <script
            type="text/javascript"
            src="https://www.gstatic.com/charts/loader.js"
    ></script>
    <!--게시판 그래프-->
    <script
            type="text/javascript"
            src="https://www.gstatic.com/charts/loader.js"
    ></script>
    <!-- js 링크-->
    <script src="adminmain.js"></script>
</div>
</body>
</html>


