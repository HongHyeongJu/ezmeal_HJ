<%--
  Created by IntelliJ IDEA.
  User: hhju2
  Date: 2023-07-20
  Time: AM 9:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ezmeal_admin</title>
    <link rel="stylesheet" href="/css/screens/admin_notice_write.css">
<%--    <link rel="preconnect" href="https://fonts.googleapis.com">--%>
<%--    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>--%>
    <link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Nanum+Gothic&display=swap" rel="stylesheet">
</head>
<body>
<%@ include file="admin_header.jsp" %>  <!-- 관리자 헤더 인클루드 -->
<div class="all_container_div">

<%@ include file="admin_menu.jsp" %> <!-- 관리자 메뉴 인클루드 -->
    <div class="admin_container_div">
<!--start : 공지사항 글 등록 페이지-->
<div class="notice-board">
    <div class="notice-board-inner">
        <div class="notice-title">공지사항</div> <!--글 등록 페이지 상단 제목-->
    </div>

    <!-- 글 등록 테이블 -->
    <form action="/noticeresistration" method="post"> <!--post 방식으로 데이터 전송, action은 url주소-->
        <table class ="admin-notice-resistration-table">
<%--            <colgroup>--%>
<%--                <col style="width: 150px  !important" />--%>
<%--                <col style="width: 1050px  !important" />--%>
<%--            </colgroup>--%>
            <tr> <!--제목-->
                <th class="admin-notice-resistration-table-right">제목</th>
                <td class="admin-notice-resistration-table-left ">
                    <input type="text" id="admin-notice-input1" name="title" required>
                    <!--name 속성은 보통 데이터베이스의 열(컬럼) 이름과 매칭되도록 설정-->
                </td>
            </tr>
            <tr> <!--status : 공지중요도-->
                <th class="admin-notice-resistration-table-right">공지 중요도</th>
                <td class="admin-notice-resistration-table-left ">
                    <label><input type="radio" name="status" value="긴급" ${product.getSfkp_stus().equals("긴급") ? 'checked' : ''}>긴급</label>
                    <label><input type="radio" name="status" value="중요" ${product.getSfkp_stus().equals("중요") ? 'checked' : ''}>중요</label>
                    <label><input type="radio" name="status" value="일반" ${product.getSfkp_stus().equals("일반") ? 'checked' : ''}>일반</label>

    </td>
    </tr>
    <tr> <!--typ : 공지유형-->
        <th class="admin-notice-resistration-table-right">공지 유형</th>
        <td class="admin-notice-resistration-table-left ">
            <label><input type="radio" name="typ" value="배송" ${product.getSfkp_stus().equals("배송") ? 'checked' : ''}>배송</label>
            <label><input type="radio" name="typ" value="정책" ${product.getSfkp_stus().equals("정책") ? 'checked' : ''}>정책</label>
            <label><input type="radio" name="typ" value="서비스점검" ${product.getSfkp_stus().equals("서비스점검") ? 'checked' : ''}>서비스점검</label>
            <label><input type="radio" name="typ" value="기타" ${product.getSfkp_stus().equals("기타") ? 'checked' : ''}>기타</label>
        </td>
    </tr>
    <tr> <!--stmt : 공지 게시글 본문-->
        <th class="admin-notice-resistration-table-right">본문</th>
        <td class="admin-notice-resistration-table-left ">
            <textarea class="notice_stmt" name="stmt"></textarea>
        </td>
    </tr>
<%--    <tr> <!-- 비밀번호-->--%>
<%--        <th class="admin-notice-resistration-table-right">비밀번호</th>--%>
<%--        <td class="admin-notice-resistration-table-left ">--%>
<%--            <input type="text" id="admin-notice-input2" name="input2" required>--%>
<%--        </td>--%>
<%--    </tr>--%>  <!--내 db에는 비밀번호가 없어 -->

    <tr> <!-- 공지 글 보이게 할지 안할지 여부-->
        <th class="admin-notice-resistration-table-right">글 게시여부</th>
        <td class="admin-notice-resistration-table-left ">
            <span class="notice-label"> <!--글 숨김여부-->
              <input id="hide_y/n" name="hide_yn"  value="true" type="checkbox"> <!--체크하면 게시글이 보이고 안하면 안보임-->
               <label for="hide_y/n"></label>
             </span>
            <!--hide[]의 []은 체크박스의 값을 배열형태로 서버로 전송하고자 할때 사용, B는 체크박스가 선택되었을때 서버로 전송되는값-->
        </td>
    </tr>
    </table>

    <!--start :맨 아래에있는 목록, 등록  btn -->
    <div class="admin-notice_btn_box">
        <button
                class="admin-notice_btn_list"
                type="submit"
                width="100"
                height="42"
                radius="0"
        >
            <span class="admin-notice_btn_list">목록</span>
        </button>

        <button
                class="admin-notice_btn_sub"
                type="submit"
                width="100"
                height="42"
                radius="0"
        >
            <span class="admin-notice_btn_submit">등록</span>
        </button>
    </div>
    </form>
</div>
    </div>
</div>
</body>
</html>


