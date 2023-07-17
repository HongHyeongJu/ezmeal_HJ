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
        /* CSS 스타일을 여기에 작성합니다. */
        body {
            font-family: Arial, sans-serif;
        }

        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }

        h1 {
            margin-bottom: 30px;
        }

        .fa-cart-arrow-down {
            font-size: 80px;
            margin-bottom: 45px;
        }

        img {
            width: 180px;
            height: 205px;
            margin-bottom: 45px;
        }

        p {
            margin-bottom: 20px;
        }

        p:nth-child(5) {
            margin-bottom: 40px;
        }

        a {
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

        a:hover {
            background-color: #00c728;
            color: #fff;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>주문이 완료되었습니다!</h1>
<%--    <i class="fas fa-cart-arrow-down" style="color: #00c728"></i>--%>
    <img src="/img/penguin.png" alt="penguin" />
    <p>주문해 주셔서 감사합니다.</p>
    <p>주문 번호: ${orderId}</p>
    <p>주문 내역 및 상세 정보는 아래의 링크를 통해 확인할 수 있습니다.</p>
    <a href="/">홈으로 돌아가기</a>
    <a href="/">주문 내역 확인하기</a>
    <a href="/">주문 상세 확인하기</a>
</div>
</body>
<%--<script--%>
<%--        src="https://kit.fontawesome.com/6478f529f2.js"--%>
<%--        crossorigin="anonymous"--%>
<%--></script>--%>
</html>
