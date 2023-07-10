<%--
  Created by IntelliJ IDEA.
  User: hubaek
  Date: 2023/07/02
  Time: 2:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<c:set var="loginId"
       value="${pageContext.request.getSession(false)==null ? '' : pageContext.request.session.getAttribute('id')}"/>
<c:set var="loginOutLink" value="${loginId=='' ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${loginId=='' ? 'Login' : 'ID='+=loginId}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>ezMeal-test</title>
</head>
<body>
<ul>
    <li><a href="/mypage/modify">회원정보수정</a></li>
    <li><a href="/mypage/withdrawal">회원탈퇴</a></li>
    <li><a href="/">홈으로</a></li>

</ul>
</body>
</html>
