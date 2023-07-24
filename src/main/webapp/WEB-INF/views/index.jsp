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
    <title>Title</title>
    <link rel="stylesheet" href="/css/main.css">
</head>
<body>
<jsp:include page="header.jsp"/>

<!--start : img slide-->
<div class="image-slideshow">
    <div class="image fade">
        <img
                src="/img/main/slideimg_1.jpg"
                style="width: 100%; height: 500px"
                alt="7월신메뉴"
        />
    </div>
    <div class="image fade">
        <img
                src="/img/main/slideimg_2.jpg"
                style="width: 100%; height: 500px"
                alt="구독신청"
        />
    </div>
    <div class="image fade">
        <img
                src="/img/main/slideimg_3.jpg"
                style="width: 100%; height: 500px"
                alt="콘텐츠"
        />
    </div>
</div>
<script src="/javascript/main1.js"></script>

<!--end : img slide-->


<jsp:include page="footer.jsp"/>

<!--start : scroll back to top-->
<button id="backtotop-btn">
    <a href="#top" style="color: white">Top</a>
</button>

<!--end : back to top button-->
<script src="https://kit.fontawesome.com/3dd102f0de.js" crossorigin="anonymous"></script>
</body>
</html>
