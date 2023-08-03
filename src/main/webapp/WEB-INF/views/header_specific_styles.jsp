<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hubaek
  Date: 2023/07/13
  Time: 8:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="loginOutLink" value="${sessionScope.memberId==null ? '/login' : '/logout'}"/>
<c:set var="loginOut" value="${sessionScope.memberId==null ? '로그인' : '로그아웃'}"/>
<html>
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Title</title>

  <style>

    /* 특정 페이지에서만 적용할 헤더 스타일 */

    #header-bottom {
      caret-color: transparent; /* 커서 깜빡임 없애기 */
      display: flex;
      width: 100%;
      background-color: rgb(255 255 255) !important; /* 이곳에 !important 위치시킴 */
      position: sticky;
      top: 0;
      height: 50px;
      text-align: center;
      z-index: 10;
      box-shadow: 0px 3px 10px rgba(0, 0, 0, 0.1) !important; /* 이곳에 !important 위치시킴 */
    }

    #horizonMenu-list a {
      text-decoration: none;
      color: white !important; /* 이곳에 !important 위치시킴 */
      justify-content: space-between;
      cursor: pointer;
      position: relative;
      color: black !important; /* 이곳에 !important 위치시킴 */
    }


    .spinner {
      /*햄버거 아이콘 감싸는 박스*/
      transition: all 0.3s;
      box-sizing: border-box;
      position: absolute;
      height: 3px;
      width: 100%;
      background-color: black !important;
    }

    .sidebar  li{
      border: 1px solid #f2f2f2;
    }

  </style>

</head>
<body>
</body>
</html>
