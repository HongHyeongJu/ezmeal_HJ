<%--
  Created by IntelliJ IDEA.
  User: taewan
  Date: 2023/07/17
  Time: 11:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>주문 완료</title>
    <style>
        @import url(https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@1.0/nanumsquare.css);
        body {
            font-family: 'NanumSquare', sans-serif;
        }

        .order-complete__container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            margin-top: 150px;
        }

        .order-complete__title {
            margin-bottom: 30px;
        }


        .order-complete__img {
            width: 250px;
            height: 205px;
            margin-bottom: 45px;
        }

        .order-complete__p {
            margin-bottom: 20px;
        }

        .order-complete__p:nth-child(5) {
            margin-bottom: 40px;
        }

        .order-complete__a {
            width: 350px;
            margin-bottom: 10px;
            padding: 14px;
            border: #00c728 1px solid;
            text-decoration: none;
            text-align: center;
            color: #00c728;
            transition: background-color 0.5s ease, color 0.3s ease;
            font-size: large;
        }

        .order-complete__a:hover {
            background-color: #00c728;
            color: #fff;
            font-weight: bold;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="order-complete__container">
    <h1 class="order-complete__title">주문이 완료되었습니다!</h1>
    <img src="/img/main/order_logo.png" alt="penguin" class="order-complete__img"/>
    <p class="order-complete__p order-complete__contents">주문해 주셔서 감사합니다.</p>
    <p class="order-complete__p order-complete__order-number">주문 번호: ${orderId}</p>
    <p class="order-complete__p">주문 내역 및 상세 정보는 아래의 링크를 통해 확인할 수 있습니다.</p>
    <a class="order-complete__a" href="/">홈으로 돌아가기</a>
    <a class="order-complete__a" href="/orderPayment">주문 내역 확인하기</a>
    <a class="order-complete__a" href="/orderDetail">주문 상세 확인하기</a>
</div>
</body>
</html>
