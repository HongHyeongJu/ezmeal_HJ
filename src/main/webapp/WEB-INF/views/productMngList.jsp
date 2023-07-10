<%--
  Created by IntelliJ IDEA.
  User: hhju2
  Date: 2023-07-02
  Time: AM 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 상품 CRUD</title>
    <link rel="stylesheet" href="/css/screens/productMngList.css"/>
</head>
<body>
<div class="all_div">
  <!------------------------ 디브 시작 ------------------------->
  <div class="half_div" id="left_div">
    <br>
    <div class="long-div">
      <h3>상품 관리</h3>
      <p>관리자용 상품 관리 페이지 입니다.</p>
      &nbsp; &nbsp;
      <button id="prod_regist">새상품 등록</button>
    </div>
    <div class="read_only">
      <table>
        <tr>
          <td>전체<input type="text" name="prod_cd" value="30" readonly>건</td>
          <td>&#124;</td>
          <td>판매중 &nbsp;일반<input type="text" name="prod_cd" value="20" readonly>건</td>
          <td>구독<input type="text" name="prod_cd" value="10" readonly>건</td>
          <td>판매안함 &nbsp;일반<input type="text" name="prod_cd" value="13" readonly>건</td>
          <td>구독<input type="text" name="prod_cd" value="10" readonly>건</td>
          <td>&#124;</td>
          <!-- <td>&nbsp;</td> -->
          <td>진열중 &nbsp;일반<input type="text" name="prod_cd" value="18" readonly>건</td>
          <td>진열안함 &nbsp;일반<input type="text" name="prod_cd" value="18" readonly>건</td>
        </tr>
      </table>
    </div>
    <br>
    <div class="yn_table"> <!--search_table-->
      <table>
        <tr class="tr_title">
          <td colspan="1">검색</td>
          <td colspan="1">
            <select id name="option">
              <option value="name" disabled selected>상품명</option>
              <option value="cust_cd">상품명</option>
              <option value="cust_cd">거래처</option>
            </select>
          </td>
          <td colspan="4">
            <input type="text" name="search_keyword" value="" >
          </td>
        </tr>
        <tr>
          <td>상품구분</td>
          <td colspan="5">
            <input type="radio" name="sub_yn" value="n">일반 상품&nbsp;&nbsp;
            <input type="radio" name="sub_yn" value="y">구독 상품
          </td>
        </tr>
        <tr>
          <td>일반상품분류</td>
          <td colspan="5">
            <input type="radio" name="cate_cd" value="">전체
            <input type="radio" name="cate_cd" value="01">닭가슴살
            <input type="radio" name="cate_cd" value="02">도시락.볶음밥
            <input type="radio" name="cate_cd" value="03">샐러드.소스
            <input type="radio" name="cate_cd" value="04">신선식품
            <input type="radio" name="cate_cd" value="05">즉석간편식
          </td>
        </tr>
        <tr>
          <td>상품날짜</td>
          <td colspan="5">
            <input type="radio" name="fst_reg_dt" value="n">등록일
            <input type="radio" name="up_dtm" value="n">최종수정일
            &nbsp;&nbsp;&#124;&#124;&nbsp;&nbsp;
            <input type="button" name="search_date" value="오늘">
            <input type="button" name="search_date" value="1주일">
            <input type="button" name="search_date" value="1달">
            <input type="button" name="search_date" value="전체">
            &nbsp;&nbsp;&#124;&nbsp;&nbsp;검색기간
            <input type="date" name="vld_start_dt" value="">
            ~
            <input type="date" name="vld_end_dt" value="">
          </td>
        </tr>
        <tr>
          <td>판매상태</td>
          <td colspan="3">
            <input type="radio" name="sale_yn" value="n">판매 O
            &nbsp;&nbsp;
            <input type="radio" name="sale_yn" value="n">판매 X
          </td>
          <td>진열상태</td>
          <td colspan="3">
            <input type="radio" name="dp_yn" value="y">진열 O
            &nbsp;&nbsp;
            <input type="radio" name="dp_yn" value="n">진열 X
          </td>
        </tr>
      </table>
    </div>
    <div class="search_btn_set">
      <button id="search_submit_btn">검색</button>
      <input type="reset" id="reset_btn" value="초기화">
    </div>
    <!--------------------------------------------------------------------->
    <!--------------------------------------------------------------------->
    <br>
    <div class="long-div">
      <h3>상품 목록</h3>
    </div>
    <!-------------------------------------------------------------------->
    <div class="list_btn_set">
      <button id="sale_y">판매 O</button>
      <button id="sale_n">판매 X</button>
      <button id="dp_y">진열 O</button>
      <button id="dp_n">진열 X</button>
      <button id="copy_btn">복사</button>
      <button id="del_btn">삭제</button>
    </div>
    <br>
    <!-------------------------------------------------------------------->
    <div class="product_list_div">
      <table class="product_table">
        <tr>
          <td>선택</td>
          <td>NO</td>
          <td>상품구분</td>
          <td>상품코드</td>
          <td colspan="3">상품명</td>
          <td>판매가</td>
          <td>소비자가</td>
          <td>비고</td>
        </tr>

        <!-- 상품 1 반복 시작 -->
        <c:forEach items="${productCateCdList}" var="productDto">
          <tr>
            <td><input type="checkbox" id="${productDto.getProd_cd()}"></td>
            <td class="list_seq">1</td>
            <td>${productDto.getSub_yn() == "y" ? "구독" : "일반"}</td>
            <td>${productDto.getProd_cd()}</td>
            <td><img src="./food.png" width="40px" height="40px"/></td>
            <td>${productDto.getName()}</td>
            <td>${productDto.getMng_prod_nm()}</td>
            <td>${productDto.getSale_prc()}</td>
            <td>${productDto.getCnsmr_prc()}</td>
            <td>${productDto.getRmk()}</td>
          </tr>
        </c:forEach>
        <!-- 상품 1 반복 끝 -->

      </table>
    </div>
    <br><br><br>
    <!-------------------------------------------------------------------->
  </div>
  <!-------------------------------------------------------------------->
</div><!-------------- 전체 큰 끝 ------------------------->
  <script>

  </script>

</body>
</html>
