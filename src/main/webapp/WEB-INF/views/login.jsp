<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
</br>
</br>
</br>
<form action="/login?redirectURL=${redirectURL}" method="post">
    id <input type="text" name="tryMbrId" id="id" value="${cookie.id != null ? cookie.id.value : ''}"> pwd
    <input type="password" name="tryMbrPw" id="pw"/>
    <input type="submit" value="login"/>
    <%--  check 되면 dom property : checked=true  --%>
    id 기억<input type="checkbox" name="remember" id="remember" ${cookie.id != null ? "checked" : ''}/>
</form>
</body>
</html>
