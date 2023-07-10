<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page session="false" %>--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
</br>
</br>
</br>
<form action="/login?redirectURL=${redirectURL}" method="post">
    <label for="id">id</label>
    <input type="text" name="loginId" id="id" value="${cookie.id.value}">
    </br>

    <label for="pw">pwd</label>
    <input type="password" name="loginPw" id="pw"/>
    </br>

    <input type="submit" value="login"/>

    <c:if test="${not empty loginFailMsg}">
        <p id="${loginFailMsg}">${loginFailMsg}</p>
    </c:if>
    <%--  check 되면 dom property : checked=true  --%>
    <%--    id 기억<input type="checkbox" name="remember" id="remember" ${cookie.id != null ? "checked" : ''}/>--%>
    id 기억<input type="checkbox" name="remember" id="remember" value="on" ${empty cookie.id.value ? "":"checked"}/>
</form>
<script>
    <%--const msg = document.getElementById("${loginFailMsg}");--%>
    <%--if (msg !== "") {--%>
    <%--    alert(msg);--%>
    <%--}--%>
</script>
</body>
</html>
