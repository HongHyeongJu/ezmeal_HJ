<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: hhju2
  Date: 2023-06-29
  Time: AM 9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>ezmeal</title>
  <link rel="stylesheet" href="/css/screens/wishList.css">
</head>
<body>
<div class="empty-space" id="section0"></div>

<!-- 헤더 -->
<jsp:include page="header.jsp"/>
<jsp:include page="mypageHeader.jsp"/>

<!-----------------------------------------  마이페이지  ---------------------------------------------------->


<div class="main-section">
    <jsp:include page="mypageLeft.jsp"/>
    <div class="wishList_contain_div">
      <div class="myPage_title">
        <p>찜한상품</p>
      </div>
      <div class="wishList_prod">
            <div class="prod_img_header ih" ></div>
            <div class="prod_name_header nh" ></div>
            <div class="prod_delBtn_header dh" ></div>
        <c:if test="${prodCdList.size() != null }">
          <c:forEach var="wish_prod_cd" items="${prodCdList}">
            <div class="prod_img ih" id="prod_img">
              <img class="prod_img_tag" src="../../img/${prodImgMap[wish_prod_cd].url}">
            </div>
            <div class="prod_name nh" id="prod_name">
              <span class="prod_name_tag">
                <span>${prodNameMap[wish_prod_cd]}</span>
              </span>
            </div>
            <div class="prod_delBtn dh" id="prod_delBtn">
              <button class="prod_delBtn_tag">X</button>
            </div>
          </c:forEach>
        </c:if>
      </div>
    </div>

</div>


<script>
  src="https://kit.fontawesome.com/6478f529f2.js"
  crossOrigin="awesome"
</script>
</body>
</html>
