<%--
  Created by IntelliJ IDEA.
  User: hubaek
  Date: 2023/07/24
  Time: 3:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ezmeal home</title>
    <link rel="stylesheet" href="/css/main.css">

    <style>
        .footer {
            margin-top: 20px; /* 원하는 크기로 조절 */
        }
    </style>
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="slide_container">
<!--start : img slide-->
    <div class="image-slideshow">
        <div class="image fade">
            <img
                    src="/img/main/slideimg_1.jpg"
                    style="width: 100%; height: auto"
                    alt="7월신메뉴"
            />
        </div>
        <div class="image fade">
            <img
                    src="/img/main/slideimg_2.jpg"
                    style="width: 100%; height: auto"
                    alt="구독신청"
            />
        </div>
        <div class="image fade">
            <img
                    src="/img/main/slideimg_3.jpg"
                    style="width: 100%; height: auto"
                    alt="콘텐츠"
            />
        </div>
    </div>
</div>
<script src="/javascript/main1.js"></script>
<!-- 메인 추천 상품 -->
<div class="index_display_product">
    <jsp:include page="index_display_product.jsp"/>
</div>
<div class="index_footer">
    <jsp:include page="footer.jsp"/>
</div>


<!--start : scroll back to top-->
<button id="backtotop-btn">
    <a href="#top" style="color: white">Top</a>
</button>

<!--end : back to top button-->
<script src="https://kit.fontawesome.com/3dd102f0de.js" crossorigin="anonymous"></script>
</body>
</html>