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
  <div id="msg" class="msg">
    <c:if test="${not empty param.msg}">
      <i class="fa fa-exclamation-circle"> ${URLDecoder.decode(param.msg)}</i>
    </c:if>
  </div>
  <label for="">아이디 <span class="star">*</span></label>
  <input class="input-field" type="text" id="lgin_id" name="lgin_id" value="${memberDto.lgin_id}" placeholder="4~12자리의 영문 혹은 영문과 숫자 조합" autofocus>
  <button type="button" onclick="checkIdDuplicate()">중복확인</button>
  <div id="id-result-msg"></div>
  <div id="idmsg" class="idmsg"> </div>
  <label for="">비밀번호</label>
  <input class="input-field" type="password" id="password" name="lgin_pw" value="${memberDto.lgin_pw}" placeholder="최소 4자 이상 입력">
  <span id="idCheckMessage"></span> <!-- 중복 체크 결과 메시지를 표시할 영역 -->
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
  <input type="image" src="/img/signup.png" id="button" alt="회원가입">
  <%--        <button>회원 가입</button>--%>
</form>

<%--<script src="/javascript/signup.js"></script>--%>

<script>

  var msg = "${msg}";
  if (msg !== "") {
    alert(msg);
  }

  function checkIdDuplicate() {
    var idInput = document.getElementById("lgin_id");
    var idResultMsg = document.getElementById("id-result-msg");

    var id = idInput.value;

    // AJAX 요청을 생성하여 중복 체크 요청을 서버에 보냄
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/member/checkIdDuplicate", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function() {
      if (xhr.readyState === 4 && xhr.status === 200) {
        var response = JSON.parse(xhr.responseText);
        var isDuplicate = response.isDuplicate;

        if (isDuplicate) {
          idResultMsg.innerHTML = "이미 사용 중인 ID입니다.";
          idInput.classList.add("invalid");
        } else {
          idResultMsg.innerHTML = "사용 가능한 ID입니다.";
          idInput.classList.remove("invalid");
        }
      }
    };

    var data = JSON.stringify({ "id": id });
    xhr.send(data);
  }


  <%--function formCheck(frm) {--%>
  <%--  let msg ='';--%>

  <%--  if(frm.id.value.length<3) {--%>
  <%--    setMessage('id의 길이는 3이상이어야 합니다.', frm.id);--%>
  <%--    return false;--%>
  <%--  }--%>

  <%--  if(frm.pwd.value.length<3) {--%>
  <%--    setMessage('pwd의 길이는 3이상이어야 합니다.', frm.pwd);--%>
  <%--    return false;--%>
  <%--  }--%>

  <%--  return true;--%>
  <%--}--%>

  <%--function setMessage(msg, element){--%>
  <%--  document.getElementById("msg").innerHTML = `<i class="fa fa-exclamation-circle"> ${'${msg}'}</i>`;--%>

  <%--  if(element) {--%>
  <%--    element.select();--%>
  <%--  }--%>
  <%--}--%>


  <%--function formCheck(frm) {--%>
  <%--  let msg = `${msg}`;--%>
  <%--  if (msg==Signup_ERR) alert("회원가입에 실패했습니다. 다시 시도해 주세요.");--%>
  <%--  var idmsg ='';--%>
  <%--  var pwmsg ='';--%>
  <%--  var namemsg ='';--%>

  <%--  if(frm.lgin_id.value.length<3) {--%>
  <%--    setidMessage('id의 길이는 3이상이어야 합니다.', frm.lgin_id);--%>
  <%--    return false;--%>
  <%--  }--%>
  <%--  if(frm.lgin_pw.value.length<3) {--%>
  <%--    setpwdMessage('pw의 길이는 3이상이어야 합니다.', frm.lgin_pw);--%>
  <%--    return false;--%>
  <%--  }--%>
  <%--  return true;--%>
  <%--}--%>

  <%--function setidMessage(idmsg, element){--%>
  <%--  document.getElementById("idmsg").innerHTML = `<i class="fa fa-exclamation-circle"> ${idmsg}</i>`;--%>

  <%--  if(element) {--%>
  <%--    element.select();--%>
  <%--  }--%>
  <%--}--%>
  <%--function setpwdMessage(pwmsg, element){--%>
  <%--  document.getElementById("pwdmsg").innerHTML = `<i class="fa fa-exclamation-circle"> ${pwmsg}</i>`;--%>

  <%--  if(element) {--%>
  <%--    element.select();--%>
  <%--  }--%>
  <%--}--%>
</script>
</body>
</html>
