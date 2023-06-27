<%--
  Created by IntelliJ IDEA.
  User: dlwld
  Date: 2023-06-26
  Time: 오후 12:59
  To change this template use File | Settings | File Templates.
--%>
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
<div>
    <!--
    1. 모달 버튼, 히든, 모달의배경, 내용 만들기
    2. 모달의 내용, 배경에 들어갈 자리에 로그인 html 합쳐보기
    -->
    <button class="btn">Login</button>
    <div class="modal hidden">
        <div class="modal__background"></div>
        <!-- <div class="modal__content"> -->
        <button>Close</button>
        <div class="wrapper">
            <div class="container">
                <div class="sign-up-container">
                    <form>
                        <h1>Create Account</h1>
                        <input type="text" placeholder="Name">
                        <input type="email" placeholder="Email">
                        <input type="password" placeholder="Password">
                        <button class="form_btn">Sign Up</button>
                    </form>
                </div>
                <div class="sign-in-container">
                    <form>
                        <h1>Sign In</h1>
                        <input type="email" placeholder="Email">
                        <input type="password" placeholder="Password">
                        <button class="form_btn">Sign In</button>
                    </form>
                </div>
                <div class="overlay-container">
                    <div class="overlay-left">
                        <h1>기존고객이라면?</h1>
                        <p>여기를 눌러주세욥</p>
                        <button id="signIn" class="overlay_btn">Sign In</button>
                    </div>
                    <div class="overlay-right">
                        <h1>A1</h1>
                        <p>A1이 처음이라면? </p>
                        <button id="signUp" class="overlay_btn">Sign Up</button>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<script  src="/javascript/login.js"></script>
</body>
</html>