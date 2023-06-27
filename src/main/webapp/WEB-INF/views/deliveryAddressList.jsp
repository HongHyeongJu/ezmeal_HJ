<%--
  Created by IntelliJ IDEA.
  User: taewan
  Date: 2023/06/26
  Time: 4:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul class="cart__items__ul">
    <!--반복 시작 -->
    <c:forEach var="address" items="${addressList}">
        <c:if test="${address.basic_yn == 'y'}">
            <c:set var="defaultAddress" value="${address}" />
        </c:if>
    </c:forEach>

    <li>${defaultAddress.ncnm} | ${defaultAddress.rcpr} | 기본배송지</li>
    <li>${defaultAddress.desti}</li>
    <li>${defaultAddress.desti_dtl}</li>
    <li>${defaultAddress.phone}</li>

    <c:forEach var="address" items="${addressList}">
        <c:if test="${address != defaultAddress}">
            <li>${address.ncnm} | ${address.rcpr}</li>
            <li>${address.desti}</li>
            <li>${address.desti_dtl}</li>
            <li>${address.phone}</li>
        </c:if>
    </c:forEach>
    <!--반복 끝 -->
</ul>
</body>
</html>
