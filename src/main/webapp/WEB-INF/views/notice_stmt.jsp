<%@ page import="com.teamProject.ezmeal.dao.NoticeDao" %><%--
  Created by IntelliJ IDEA.
  User: lee nakyeong
  Date: 2023-07-28
  Time: 오전 8:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
   <link rel="stylesheet" href="/css/screens/notice_stmt.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Nanum+Gothic&display=swap" rel="stylesheet">
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="notice-board">
    <div class="notice-board-inner">
        <div class="notice-title">공지사항</div>
        <div class="notice-title-explain">
            ezmeal의 새로운 소식들과 유용한 정보들을 한곳에서 확인하세요.
        </div>
    </div>

    <div class="notice-table">
        <table>
            <tr>
                <td class="notice-board-title">제목</td>
                <td class="notice-board-explain">[${notice.typ}] ${notice.title}</td>
            </tr>
            <tr>
                <td class="notice-board-title">작성자</td>
                <td class="notice-board-explain">${notice.writer}</td>
            </tr>
            <tr>
                <td class="notice-board-title">작성일</td>
                <td class="notice-board-explain">${notice.wrt_dt_format}</td>
            </tr>
            <tr>
                <td class="notice-board-title"></td>
                <td class="notice-board-explain">
                    <img src="/img/notice1.jpg" class="notice_img">
                </td>
            </tr>
        </table>
    </div>

    <div class="notice-board-listbtn">
        <a href="/notice"
           class="board-listbtn"
           type="button"
           width="150"
           height="42"
           radius="0">
        <span class="board-listbtn-title">목록</span>
        </a>
    </div>
</div>
</div>
</div>
</body>
</html>
