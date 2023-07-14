<%--
  Created by IntelliJ IDEA.
  User: hubaek
  Date: 2023/07/13
  Time: 12:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/css/screens/login.css" />
</head>
<body>
<jsp:include page="header.jsp" />
<c:if test="${not empty loginFailMsg}">
    <script>
        const loginFailMsg = "${loginFailMsg}";
        alert(loginFailMsg)
    </script>
</c:if>
<div class="wrapper">
    <div class="container">
        <div class="sign-up-container">
            <form action="/member/signup" method="get">
                <h1>회원가입하기</h1>
                <label for="startstart">소셜 및 일반회원으로 가입할 수 있습니다</label>
                <figure class="ss_start" style="margin-bottom: 0px;">
                    <img class="ss_start_img" src="/img/kakao_login.png" width="350px" height="50px" />
                </figure>
                <figure class="ss_start">
                    <img class="ss_start_img" src="/img/naver_login.png" width="350px" height="50px" />
                </figure>
                <button class="start_btn"><a href="/member/signup">&gt;&gt;&nbsp;일반회원 가입하기&nbsp;&lt;&lt;</a></button>
            </form>
        </div>

        <div class="sign-in-container">
            <form action="/login?redirectURL=${redirectURL}" method="post">
                <h1 style="padding-top: 10px;" >로그인 하기</h1>
                <input type="text" id="id" class="login" name="loginId" value="${cookie.id.value}" placeholder="아이디를 입력해 주세요">
                <input type="password" id="pw" class="login" name="loginPw" placeholder="비밀번호를 입력해 주세요">
                <button type="submit" class="form_btn" value="login">로그인</button>
                <div class="login-bottom">
                    <div class="remember-id">
                        <input type="checkbox" name="remember" id="remember" value="on" ${empty cookie.id.value ? "":"checked"}/>
                        아이디기억
                    </div>
                    <div class="find">
                        <a rel="/member/find/id" class="find-a">아이디찾기</a>
                        <span class="find-span"></span>
                        <a rel="/member/find/password" class="find-a">비밀번호찾기</a>
                    </div>
                </div>
                <!-- <label for="nextnext"><hr class="horizontal_line">
                    &nbsp;또는&nbsp;<hr class="horizontal_line"></label> -->
                <figure class="ss_login" style="margin-bottom: 0px;">
                    <img class="ss_login_img" src="/img/kakao_login.png" width="350px" height="50px" />
                </figure>
                <figure class="ss_login">
                    <img class="ss_login_img" src="/img/naver_login.png" width="350px" height="50px" />
                </figure>

            </form>
        </div>
        <div class="overlay-container">
            <div class="overlay-left">
                <h1>기존고객이라면?</h1>
                <p>로그인 하러가기</p>
                <button id="signIn" class="overlay_btn">로그인</button>
            </div>
            <div class="overlay-right">
                <figure class="ezmeal_log">
                    <img src="/img/ezmeal_logo2.png" width="150px" height="150px" />
                </figure>
                <!-- <h1>EZ_MEAL</h1> -->
                <p>EZ_MEAL이 처음이라면?</p>
                <button id="signUp" class="overlay_btn">회원가입</button>

            </div>
        </div>
    </div>
</div>
<script  src="/javascript/login.js"></script>
<%--<jsp:include page="footer.jsp" />--%>
</body>
</html>

