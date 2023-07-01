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
    <li><a href="/login">로그인</a></li>
    <li><a href="/logout">로그아웃</a></li>
    <li><a href="/cart/general">일반 장바구니</a></li>
    <li><a href="/cart/subscript">구독 장바구니</a></li>
    <li><a href="//productcatelist">상품 목록</a></li>
    <li><a href="/address">배송지</a></li>
    <li><a href="/member/signup">회원가입</a></li>
    <li><a href="/mypage/withdrawal">회원탈퇴</a></li>
    <li><a href=""></a></li>
    <li><a href=""></a></li>
</ul>
</body>
</html>
