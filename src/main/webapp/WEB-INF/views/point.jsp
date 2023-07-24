<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: hubaek
  Date: 2023/07/19
  Time: 1:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<jsp:include page="mypageHeader.jsp"/>

<div class="main-section">

    <jsp:include page="mypageLeft.jsp"/>
    <p>현재 적립금 : ${point}</p>
    <h1>적립금 내역</h1>
    <br>
    <table>
        <tr>
            <th>날짜</th>
            <th>내용</th>
            <th>유효기간</th>
            <th>금액</th>
        </tr>
        <c:forEach var="point" items="${pointList}">
            <tr>
                <td>${point.formattedTrjsDtm}</td>
                <td>${point.dscpt}</td>
                <td>${point.vld_end_dt}</td>
                <td>${point.trjs_pnt}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
