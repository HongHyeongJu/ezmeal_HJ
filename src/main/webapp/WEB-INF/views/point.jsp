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
    <title>ezmeal | 포인트</title>
    <link rel="stylesheet" href="/css/screens/point.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<jsp:include page="mypageHeader.jsp"/>

<div class="main-section">

    <jsp:include page="mypageLeft.jsp"/>

    <div class="css-171zbec">
        <div class="css-j0lifa">
            <div class="css-fhxb3m">
                <h2 class="css-1268zpe">
                    적립금
                </h2>
                <span class="css-a3vgo2">
                보유하고 계신 적립금의 내역을 한 눈에 확인 하실 수 있습니다.
            </span>
            </div>
        </div>
        <section class="css-1brkpx3">
            <dl class="item">
                <dt class="label">현재 적립금</dt>
                <dd class="price">
                    0
                    <span class="unit">원
                </span>
                </dd>
            </dl>
            <dl class="item">
                <dt class="label">소멸예정 적립금</dt>
                <dd class="price">
                    0
                    <span class="unit">원
                </span>
                </dd>
            </dl>
        </section>

        <section class="section">

            <div class="mBoard eczmil30">
                <table>
                    <colgroup>
                        <col style="width:130px">
                        <col style="width:400px;">
                        <col style="width:120px">
                        <col style="width:120px">
                    </colgroup>
                    <thead>
                    <tr>
                        <th scope="col">날짜</th>
                        <th scope="col">내용</th>
                        <th scope="col">유효기간</th>
                        <th scope="col">금액</th>
                    </tr>
                    </thead>

                    <tbody class="center">
                    <c:forEach var="point" items="${pointList}">
                        <tr>
                            <td>${point.formattedTrjsDtm}</td>
                            <td class="left">${point.name}</td>
                            <td>${point.vld_end_dt}</td>
                            <td>${point.trjs_pnt}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </section>

    </div>
</div>
</body>
</html>
