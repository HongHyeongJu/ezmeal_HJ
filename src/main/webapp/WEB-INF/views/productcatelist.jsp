<%--
  Created by IntelliJ IDEA.
  User: hhju2
  Date: 2023-06-26
  Time: PM 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>카테고리 별 상품 목록</title>
  <link rel="stylesheet" href="/css/screens/productcatelist.css?ver=2"/>
</head>
<body>

<!--헤더 내려오는 공백-->
<div class="empty"></div>
<ul class="지붕묶음" id="section1">

</ul>

<!--전달 받은 상품 리스트 하나한 꺼내기-->
<c:forEach var="prodList" items="${prodList}" varStatus="status">
  <!-- 4개씩 ul 태그로 감싸기 -->
  <c:if test="${status.index % 4 == 0}">
    <ul class="prod_list_ul">
  </c:if>

  <li class="prod_list_li">
    <div class="prod_outer"  data-prod-idx="${status.index}">
      <!--------------------------------------------------------------------------------------------->
      <figure class="prod_top top_figure">
        <a> <!--상품 대표 이미지-->
          <c:set var="productImg" value="${prodImgList[status.index].url}"/>
          <img id="prod_top top_img"
               src="/img/1.png"/>
<%--               src="/img/${empty productImg ? 'ezmeal_logo' : productImg}.png"/>--%>
        </a>
      </figure>
      <!--------------------------------------------------------------------------------------------->
      <div class="prod_bottom">
        <div class="review_score"> <!--상품 관련 평균평점 및 리뷰 (리뷰테이블이 오면 값넣음)-->
          <div class="review_set">
            <span class="star_img"></span> <!--별 이미지(고정)-->
            <c:set var="starAvg" value="${reviewAngMap[prodList.prod_cd].avg}" />
            <span class="score">${empty starAvg ? 0 : starAvg}</span> <!--평균 점수-->
            <c:set var="reviewCnt" value="${reviewCntMap[prodList.prod_cd].count}" />
            <span class="total_num">(${empty reviewCnt ? 0 : reviewCnt})</span> <!--리뷰 수-->
          </div>
        </div>
        <p class="prod_title"> <!--상품 이름-->
          <a class="prod_title prod_name">${prodList.getName()}</a>
        </p>
        <div class="dc_cd_prod_dscpt_wrapper"> <!-- 할인정보, 판매가격, 소비자가격 세트 -->
          <span class="dc_cd">
            <c:set var="cnsmr_prc" value="${prodList.getCnsmr_prc()}" />
            <c:set var="sale_prc" value="${prodList.getSale_prc()}" />
                      <strong>
                          ${(cnsmr_prc-sale_prc)/cnsmr_prc*100} <!--할인 퍼센트-->
                      </strong>%
                    </span>&nbsp;
          <span class="sale_prc"> <!--판매 가격-->
                            <a>${prodList.getSale_prc()}
                            </a>원
                    </span>
          <p class="cnsmr_prc"> <!--소비자 가격-->
            <span>${prodList.getCnsmr_prc()}
            </span>원
          </p>
        </div>
        <!--------------------------------------------------------------------------------------------->
        <!--상품 목록 페이지에서 바로 장바구니에 넣는 버튼-->
        <div class="add_cart">
          <form id="cart_form_${status.index}" class="cart_form">
          <div class="button_wrapper">
              <!--수량 빼는 버튼-->
              <button type="button" id="min_btn" onclick="decreaseQuantity(this)">-</button>
                  <!--선택한 수량. 담기 버튼 누르면 보내줘야 하는 값-->
                  <span id="quantity_${status.index}" class="quantity">1</span>
              <!--수량 추가 버튼-->
              <button type="button" id="pls_btn" onclick="increaseQuantity(this)">+</button>
              &nbsp;&nbsp;
              <!--(히든)장바구니 담기버튼 눌렀을 때 상품 코드도 함께 전송-->
              <input type="hidden" id="prod_cd" value="${prodList.getProd_cd()}">
              <!--제출 버튼-->
              <input type="submit" class="submit_btn" value="담기">
            </div>
          </form>
        </div>
      </div>
      <!--------------------------------------------------------------------------------------------->
    </div>
  </li>

  <!-- 4개마다 ul 태그 닫기 -->
  <c:if test="${status.index % 4 == 3 || status.last}">
    </ul>
  </c:if>
</c:forEach>

<script src="/javascript/productlistaddcart.js"></script>
<script>
  // msg 값을 확인하고 alert 창 띄우기 없는 상품코드 일 때
    var msg = "${msg}";
    if (msg !== "") {
      alert(msg);
    }
</script>
</body>
</html>
