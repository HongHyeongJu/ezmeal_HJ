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
    <button class="btn">로그인</button>
    <div class="modal hidden">
        <div class="modal__background"></div>
        <!-- <div class="modal__content"> -->
        <button class="close_btn">닫기</button>
        <div class="wrapper">
            <div class="container">
                <div class="sign-up-container">
                    <form>
                        <h1>회원가입하기</h1>
                        <label for="startstart">소셜 및 일반회원으로 가입할 수 있습니다</label>
                        <figure class="ss_start">
                            <img class="ss_start_img" src="./kakao_start.png" />
                        </figure>
                        <figure class="ss_start">
                            <img class="ss_start_img" src="./naver_start.png" />
                        </figure>
                        <button class="start_btn"><a href="">&gt;&gt;&nbsp;일반회원 가입하기&nbsp;&lt;&lt;</a></button>
                    </form>
                </div>
                <div class="sign-in-container">
                    <form>
                        <h1>로그인 하기</h1>
                        <input type="email" placeholder="Email">
                        <input type="password" placeholder="Password">
                        <button class="form_btn">로그인</button>
                        <label for="nextnext"><hr class="horizontal_line">
                            &nbsp;또는&nbsp;<hr class="horizontal_line"></label>
                        <figure class="ss_login">
                            <img class="ss_login_img" src="./kakao_login.png" />
                        </figure>
                        <figure class="ss_login">
                            <img class="ss_login_img" src="./naver_login.png" />
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
                            <img src="./ezmeal_logo.png" width="100px" height="100px" />
                        </figure>
                        <h1>EZ_MEAL</h1>
                        <p>EZ_MEAL이 처음이라면?</p>
                        <button id="signUp" class="overlay_btn">가입하기</button>

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

