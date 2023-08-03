<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.teamProject.ezmeal.dao.NoticeDao" %><%--
  Created by IntelliJ IDEA.
  User: lee nakyeong
  Date: 2023-07-28
  Time: 오전 8:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/screens/notice_list_div.css">

</head>
<body>

<%--<%@ include file="header.jsp" %>--%>

<div class="notice-content-title-box">
    <div class="notice-content-title">
        <h2 class="notice-title">공지사항</h2>
        <span class="notice-title-explain"
        >easymeal의 새로운 소식들과 유용한 정보들을 한곳에서
                확인하세요.</span
        >
    </div>
</div>

<!--게시글 목록-->
<div class="notice-table">
    <table>
        <colgroup>
            <col style="width: 40px" />
            <col style="width: 500px" />
            <col style="width: 60px" />
            <col style="width: 140px" />
        </colgroup>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
        </tr>
        <!-- view로 올때 모델에 담아준 노티스리스트를 반복해서 출력해주는역할-->
        <!--foreach은 for문이라고 생각-->
        <c:forEach var="notice" items="${noticeList}">
            <tr>
                <td class="notice_td">${notice.notice_no}</td>
                <!--공지사항 글번호-->
                <td class="notice_td">
                    <a href="/noticestmt?notice_no=${notice.notice_no}"
                    >[${notice.typ}] ${notice.title}</a
                    >
                </td>
                <!-- 공지사항의 타입, 제목-->
                <td class="notice_td">${notice.writer}</td>
                <!--공지사항 작성자-->
                <td class="notice_td">${notice.wrt_dt_format}</td>
                <!--공지사항 작성일-->
            </tr>
        </c:forEach>
    </table>
</div>
<div class="page-bottom">
    <div class="pagination">


        <script type="text/javascript">
            //<![CDATA[
            function CmPageMove( pg , size ) {
                var frm     = document.frm;
                frm["nowPageNo"].value	= pg;

                if (size != undefined) {
                    frm["pageSize"].value	= size;
                }

                frm.submit();
            }
            //]]>
        </script>
        <div class="pagging-wrap page-area">
            <div class="page-number">
                    <c:if test="${ph.isShowPrev()}">
                        <a class="page" href="<c:url value="/notice?page=${ph.getBeginPage()-1}"/>"><</a>
                    </c:if>
                    <c:forEach var="i" begin="${ph.getBeginPage()}" end="${ph.getEndPage()}">
                        <a class="page ${i==ph.getPage()? "paging-active" : ""}" href="<c:url value="/notice?page=${i}&pageSize=10"/>">${i}</a>
                    </c:forEach>
                    <c:if test="${ph.isShowNext()}">
                        <a class="page" href="<c:url value="/notice?page=${ph.endPage+1}"/>">></a>
                    </c:if>


<%--                <ul class="page_list_num_ul">--%>
<%--                    <li class="page_list_num">&lt;</li>--%>
<%--                    <li class="page_list_num thispage">1</li>--%>
<%--                    <li class="page_list_num">2</li>--%>
<%--                    <li class="page_list_num">3</li>--%>
<%--                    <li class="page_list_num">4</li>--%>
<%--                    <li class="page_list_num">5</li>--%>
<%--                    <li class="page_list_num">&gt;</li>--%>
<%--                </ul>--%>
            </div>

        </div>
    </div>
</div>


</body>
</html>
