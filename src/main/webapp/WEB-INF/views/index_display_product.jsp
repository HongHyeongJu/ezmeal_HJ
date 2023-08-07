<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hubaek
  Date: 2023/07/13
  Time: 8:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title></title>
  <link rel="stylesheet" href="/css/screens/index_display_product.css" />
</head>
<body>


<!------------------- 직장인을 위한 [empl]--------------------->
<div class="recoprod_wrapper" id="empl">
  <div class="singletyp_recoprod">
    <ul class="recoprod_ul">
      <span class="singletyp_title">
        <i class="fas fa-suitcase"></i>&nbsp;직장인을 위한 추천!&nbsp;&nbsp;베스트 후기 상품만 모았어요-
      </span>
    </ul>
    <ul class="recoprod_ul">
      <li class="recoprod_img_li">
        <img src="/img/main/empl.png" class="typ_img">
      </li>

      <c:forEach var="empl" items="${emplList}" varStatus="status">
      <a href="/product/detail?prod_cd=${empl.prod_cd}">
      <li class="health_prod_li">
        <div class="prod_outer">
          <c:set var="emplImg" value="${prodImgMap[empl.prod_cd]}" />
          <figure>
            <img src="/img/${emplImg.url}">
          </figure>
          <div class="prod_text">
            <span class="pord_name">${empl.name}</span>
          </div>
          <div class="prod_number">
            <span class="dc_pt">${empl.dc_rate}%</span>
            <span class="sale_prc">${empl.sale_prc}원</span>
            <del class="cnsmr_prc">${empl.cnsmr_prc}원</del>
          </div>
        </div>
      </li>
      </a>
      </c:forEach>
      <!-- prod_li 5번 반복 출력 -->

    </ul>
  </div>
</div>


<!------------------- 헬스인을 위한  [health] --------------------->
<div class="recoprod_wrapper" id="health">
  <div class="singletyp_recoprod">
    <ul class="recoprod_ul">
      <span class="singletyp_title">
        <i class="fas fa-dumbbell"></i>&nbsp;헬스인을 위한 추천!&nbsp;&nbsp;식단 정해드립니다-
      </span>
    </ul>
    <ul class="recoprod_ul">
      <li class="recoprod_img_li">
        <img src="/img/main/health.png" class="typ_img">
      </li>

      <c:forEach var="health" items="${healthList}" varStatus="status">
      <a href="/product/detail?cate_cd=05&prod_cd=${health.prod_cd}">
        <li class="health_prod_li">
          <div class="prod_outer">
            <c:set var="healthImg" value="${prodImgMap[health.prod_cd]}" />
            <figure>
              <img src="/img/${healthImg.url}">
            </figure>
            <div class="prod_text">
              <span class="pord_name">${health.name}</span>
            </div>
            <div class="prod_number">
              <span class="dc_pt">${health.dc_rate}%</span>
              <span class="sale_prc">${health.sale_prc}원</span>
              <del class="cnsmr_prc">${health.cnsmr_prc}원</del>
            </div>
          </div>
        </li>
      </a>
      </c:forEach>
      <!-- prod_li 5번 반복 출력 -->

    </ul>
  </div>
</div>



<!------------------- 먹잘알을 위한 [eat]--------------------->
<div class="recoprod_wrapper" id="eat">
  <div class="singletyp_recoprod">
    <ul class="recoprod_ul">
      <span class="singletyp_title">
        <i class="far fa-kiss-wink-heart"></i>&nbsp;먹잘알을 위한 추천!&nbsp;&nbsp;신제품을 맛보세요-
      </span>
    </ul>
    <ul class="recoprod_ul">
      <li class="recoprod_img_li">
        <img src="/img/main/eat.png" class="typ_img">
      </li>

      <c:forEach var="eat" items="${eatList}" varStatus="status">
      <a href="/product/detail?cate_cd=05&prod_cd=${eat.prod_cd}">
        <li class="health_prod_li">
          <div class="prod_outer">
            <c:set var="eatImg" value="${prodImgMap[eat.prod_cd]}" />
            <figure>
              <img src="/img/${eatImg.url}">
            </figure>
            <div class="prod_text">
              <span class="pord_name">${eat.name}</span>
            </div>
            <div class="prod_number">
              <span class="dc_pt">${eat.dc_rate}%</span>
              <span class="sale_prc">${eat.sale_prc}원</span>
              <del class="cnsmr_prc">${eat.cnsmr_prc}원</del>
            </div>
          </div>
        </li>
      </a>
      </c:forEach>
      <!-- prod_li 5번 반복 출력 -->

    </ul>
  </div>
</div>



<!------------------- 자취러를 위한 [home]--------------------->
<div class="recoprod_wrapper" id="home">
  <div class="singletyp_recoprod">
    <ul class="recoprod_ul">
      <span class="singletyp_title">
        <i class="fas fa-house-user"></i>&nbsp;자취러를 위한 추천!&nbsp;&nbsp;많이 살 수록 큰 할인-
      </span>
    </ul>
    <ul class="recoprod_ul">
      <li class="recoprod_img_li">
        <img src="/img/main/home.png" class="typ_img">
      </li>

      <c:forEach var="home" items="${homeList}" varStatus="status">
      <a href="/product/detail?cate_cd=05&prod_cd=${home.prod_cd}">
        <li class="health_prod_li">
          <div class="prod_outer">
            <c:set var="homeImg" value="${prodImgMap[home.prod_cd]}" />
            <figure>
              <img src="/img/${homeImg.url}">
            </figure>
            <div class="prod_text">
              <span class="pord_name">${home.name}</span>
            </div>
            <c:set var="optIndexZero" value="${prodOptMap[home.prod_cd].get(0)}" />
            <div class="prod_number">
              <span class="dc_pt">${optIndexZero.dc_rate}%</span>
              <span class="sale_prc">${optIndexZero.sale_prc}원</span>
              <del class="cnsmr_prc">${optIndexZero.cnsmr_prc}원</del>
            </div>
          </div>
        </li>
      </a>
      </c:forEach>
      <!-- prod_li 5번 반복 출력 -->

    </ul>
  </div>
</div>


<script src="https://kit.fontawesome.com/3dd102f0de.js" crossorigin="anonymous"></script>
</body>
</html>
