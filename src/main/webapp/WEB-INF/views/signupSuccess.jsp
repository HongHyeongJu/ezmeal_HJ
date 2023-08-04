<%--
  Created by IntelliJ IDEA.
  User: hubaek
  Date: 2023/06/28
  Time: 3:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>EZmeal-회원가입</title>
  <link rel="stylesheet" href="/css/screens/signupSuccess.css">
</head>
<body>

<jsp:include page="header.jsp"/>

  <section id="contents" class="container">

    <div class="signupSuccess-container">

      <p class="signupSuccess">회원가입이 완료되었습니다.</p>

      <div class="signupSuccess-area">
        <div class="area">
          <div class="areamenu">아이디</div>
          <div class="areavalue">${loginMbrInfo.lgin_id}</div>
        </div>

        <div class="area">
          <div class="areamenu">이름</div>
          <div class="areavalue">${loginMbrInfo.name}</div>
        </div>

        <div class="area">
          <div class="areamenu">이메일</div>
          <div class="areavalue">${loginMbrInfo.email}</div>
        </div>

        <div class="button-area">
          <button class="button-1" type="button" height="46" radius="3" onclick="window.location.href = '/';" >
            <span class="css-ymwvow e4nu7ef1">메인페이지</span>
          </button>
          <button class="button" type="button" height="46" radius="3" onclick="window.location.href = '/orderPayment';" >
            <span class="css-ymwvow e4nu7ef1">마이페이지</span>
          </button>
          <button class="button" type="button" height="46" radius="3" onclick="window.location.href = '/product/headerlist?headertyp=best';" >
            <span class="css-ymwvow e4nu7ef1">베스트상품보기</span>
          </button>
        </div>

      </div>

    </div>

  </section>

<script>
  let msg = "${msg}"
  if (msg=="Signup_OK") alert("성공적으로 회원가입이 되었습니다.");
</script>
</body>
</html>
