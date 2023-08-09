<%--
  Created by IntelliJ IDEA.
  User: hubaek
  Date: 2023/07/14
  Time: 4:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ezmeal | 아이디찾기</title>
    <link rel="stylesheet" href="/css/screens/findId.css">
</head>
<body>
<div class="section">
    <div class="title">
        아이디 찾기
    </div>
    <div class="main-section" >
        <div class="button-section">
            <button type="button" class="button" >이메일 인증</button>
        </div>
        <form class="form" action="/member/find/id" method="post">
            <div class="section">
                <div class="container">
                    <label for="name" class="label">이름</label>
                    <div class="input-section">
                        <input id="name" class="input" name="name" placeholder="이름을 입력해 주세요" type="text">
                    </div>
                </div>
                <p class="msg"></p>
            </div>
            <div class="section">
                <div class="container">
                    <label for="email" class="label">이메일</label>
                    <div class="input-section">
                        <input id="email" class="input" name="email" placeholder="이메일을 입력해 주세요" type="email">
                    </div>
                </div>
                <p class="msg"></p>
            </div>
            <div class="bottom-button">
                <button class="submit-button" type="submit">
                    <span class="button-span">확인</span>
                </button>
            </div>
        </form>
    </div>
</div>

<script>

</script>
</body>
</html>
