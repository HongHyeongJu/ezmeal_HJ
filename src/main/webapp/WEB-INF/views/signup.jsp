<%--
  Created by IntelliJ IDEA.
  User: hubaek
  Date: 2023/06/28
  Time: 10:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.net.URLDecoder"%>
<html>
<head>
  <title>회원가입</title>
  <link rel="stylesheet" href="/css/screens/signup.css">
</head>
<body>

<form action="<c:url value="/member/signup/success"/>" method="post" onsubmit="return formCheck(this)">
  <div class="title">회원가입</div>
  <div id="msg" class="msg"> ${URLDecoder.decode(param.msg, "utf-8")} </div>
  <label for="">아이디 <span class="star">*</span></label>
  <input class="input-field" type="text" name="lgin_id" value="${memberDto.lgin_id}" placeholder="8~12자리의 영대소문자와 숫자 조합" autofocus>
  <div id="idmsg" class="idmsg"> </div>
  <label for="">비밀번호</label>
  <input class="input-field" type="password" id="password" name="lgin_pw" value="${memberDto.lgin_pw}" placeholder="8~12자리의 영대소문자와 숫자 조합">
  <div id="pwdmsg" class="pwdmsg"> </div>
  <label for="">이름</label>
  <input class="input-field" type="text" name="name" value="${memberDto.name}" placeholder="이름을 입력해 주세요">
  <label for="">휴대전화</label>
  <input class="input-field" type="tel" name="phone" value="${memberDto.phone}" placeholder="010-0000-0000">
  <label for="">이메일</label>
  <input class="input-field" type="text" name="email" value="${memberDto.email}" placeholder="예: ezmeal@meal.com">
  <label for="">생년월일</label>
  <input class="input-field" type="text" name="birth" value="${memberDto.birth}" placeholder="2020/12/31">
  <div class="gender">
    <label for="">성별</label>
    <label><input type="radio" name="sex" id="m" value="m" ${memberDto.sex == 'm' ? 'checked' : ''}/>남자</label>
    <label><input type="radio" name="sex" id="f" value="f" ${memberDto.sex == 'f' ? 'checked' : ''}/>여자</label>
  </div>
  <input type="image" src="/img/register.png" id="button" alt="회원가입">
  <%--        <button>회원 가입</button>--%>
</form>

<script src="/javascript/signup.js"></script>
</body>
</html>
