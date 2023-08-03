<%--
  Created by IntelliJ IDEA.
  User: hubaek
  Date: 2023/07/19
  Time: 8:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/screens/adminLogin.css">
</head>
<body>
<%@ include file="admin_header.jsp" %>

<div class="all_container_div">

    <%@ include file="admin_menu.jsp" %>

    <div class="admin_container_div">
        <div class="section">
            <div class="title">관리자 로그인</div>
            <div class="form-section">
                <form action="/admin/login" method="post">
                    <div class="form-contents">
                        <div class="input-section">
                            <div class="input-contents">
                                <input data-testid="input-box" class="input-box" name="id" placeholder="아이디를 입력해주세요" type="text" value="">
                            </div>
                        </div>

                        <div class="input-section">
                            <div class="input-contents">
                                <input data-testid="input-box" class="input-box" name="pw" placeholder="비밀번호를 입력해주세요" type="password" >
                            </div>
                        </div>
                    </div>

                    <div class="a-section">
                        <input type="checkbox" class="remember" name="remember" value="on" >아이디기억
                    </div>

                    <div class="button-section">
                        <button class="button" type="submit" height="54" radius="3">
                            <span class="button-span">로그인</span>
                        </button>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
