<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hhju2
  Date: 2023-07-20
  Time: AM 9:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="loginOutLink" value="${sessionScope.empId==null ? '/admin/login' : '/admin/logout'}"/>
<c:set var="loginOut" value="${sessionScope.empId==null ? '로그인' : '로그아웃'}"/>

<html>
<head>
    <title>ezmeal_admin</title>
    <link rel="stylesheet" href="/css/screens/admin_header.css">
</head>
<body>
<div class="admin_header_div">
    <div class="admin_header_div">
        <div class="header">
            <div class="admin_title">
                <img src="/img/ez_fork.png" class="logo_img">
                <span>&nbsp;ezmeal  관리자  페이지</span>
            </div>
            <div class="admin_name">
                <c:if test="${not empty empId}">
                    <p style="width: 200px">${loginAdminInfo.title} ${loginAdminInfo.emp_acct_id}님 로그인 중</p>
                    <a href="<c:url value='${loginOutLink}'/>"><p id="logout_btn" >/&nbsp; ${loginOut}</p></a>
                </c:if>
                <c:if test="${empty empId}">
                    <a href="${loginOutLink}"><p>${loginOut}</p></a> <!--원활한 test 확인을 위해서 login 안될 경우 login 하도록 상단에 표시-->
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>
