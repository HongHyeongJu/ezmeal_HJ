<%--
  Created by IntelliJ IDEA.
  User: hubaek
  Date: 2023/07/27
  Time: 5:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <link rel="stylesheet" href="/css/screens/admin_member_info.css">

</head>
<body>
<%@ include file="admin_header.jsp" %>

<div class="all_container_div">

  <%@ include file="admin_menu.jsp" %>

    <div class="admin_container_div">

      <div id="content">
        <div class="headingArea">
          <div class="title">
            <h1>회원정보 조회</h1>
          </div>
        </div>

        <div class="section" id="QA1">
          <div class="optionArea">
            <div class="option">
              <table border="1" summary>
                <colgroup>
                  <col style="width: 145px;">
                  <col style="width: auto;">
                  <col style="width: 145px;">
                  <col style="width: auto;">
                </colgroup>
                <tbody>
                <tr>
                  <th scope="row">
                    개인정보
                  </th>
                  <td colspan="3">
                    <select id="searchOption" class="select" name="search_type">
                      <option value="id">아이디</option>
                      <option value="name">이름</option>
                      <option value="email">이메일</option>
                      <option value="phone">휴대폰번호</option>
                    </select>
                    <input type="text" name="searchValue" class="fText" style="width: 130px;">
                  </td>
                </tr>
                <tr>
                  <th scope="row">
                    회원등급
                  </th>
                  <td>
                    <select name="group_no" class="select">
                      <option value="" selected="selected">전체</option>
                      <option value="1">신규사과</option>
                      <option value="2">깨끗한사과</option>
                      <option value="3">맛있는사과</option>
                    </select>
                  </td>
                  <th scope="row">
                    회원유형
                  </th>
                  <td>
                    <label class="gLabel eSelected">
                      <input type="radio" name="member_auth" value="0" checked class="fChk">전체
                    </label>
                    <label class="gLabel">
                      <input type="radio" name="member_auth" value="1" class="fChk">특별회원
                    </label>
                    <label class="gLabel">
                      <input type="radio" name="member_auth" value="2" class="fChk">불량회원
                    </label>
                  </td>
                </tr>
                <tr>
                  <th scope="row">
                    성별
                  </th>
                  <td colspan="3">
                    <label class="gLabel eSelected">
                      <input type="radio" name="gender" value="1" checked class="fChk">전체
                    </label>
                    <label class="gLabel">
                      <input type="radio" name="gender" value="2" class="fChk">남
                    </label>
                    <label class="gLabel">
                      <input type="radio" name="gender" value="3" class="fChk">여
                    </label>
                  </td>
                </tr>
                </tbody>
              </table>
            </div>

            <div class="button-section center">
              <button type="button" id="button" class="button"  >조회</button>
            </div>

          </div>
        </div>

        <div class="section" id="QA2">
          <div class="title">
            <h2>회원목록</h2>
          </div>

          <div class="state">
            <div class="left">
              <p class="total">
<%--                [총 회원수--%>
<%--                <strong>{n}</strong>--%>
<%--                명] 검색결과--%>
<%--                <strong>{n}</strong>--%>
<%--                건--%>
              </p>
            </div>
            <div class="right">
              <select id="rows" name="rows" class="select">
<%--                <option value="all" selected>전체보기</option>--%>
                <option value="10">10개씩보기</option>
                <option value="20">20개씩보기</option>
                <option value="30">30개씩보기</option>
                <option value="40">40개씩보기</option>
                <option value="50" selected>50개씩보기</option>
              </select>
            </div>
          </div>

          <div class="mBoard scroll cellNarrow">
            <table border="1" summary class="eChkColor">
              <colgroup>
                <!-- <col class="chk"> -->
                <col class="date">
                <col style="width:70px">
                <col style="width:100px;">
                <col style="width:100px;">
                <col style="width:120px">
                <col style="width:60px">
                <col style="width:60px; ">
              </colgroup>

              <thead>
              <tr>
                <!-- <th scope="col">
                    <input type="checkbox" class="allClick">
                </th> -->
                <th scope="col">
                  등록일
                </th>
                <th scope="col">
                  이름
                </th>
                <th scope="col">
                  아이디
                </th>
                <th scope="col">
                  등급
                </th>
                <th scope="col">
                  휴대폰
                </th>
                <th scope="col">
                  성별
                </th>
                <th scope="col">
                  생년월일
                </th>
              </tr>
              </thead>

              <tbody id="memberTableBody" class="center">

              </tbody>

            </table>
          </div>
        </div>
      </div>

    </div>
</div>
<script src="/javascript/admin_member_info.js">


</script>

</body>
</html>
