<%--
  Created by IntelliJ IDEA.
  User: hhju2
  Date: 2023-06-29
  Time: AM 9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>상품 상세 페이지</title>
  <link rel="stylesheet" href="/css/screens/productdetail.css">
  <link href="https://fonts.googleapis.com/css2?family=FontName&display=swap" rel="stylesheet">
</head>
<body>
<div class="empty-space" id="section0"></div>
<!-----------------------------------------  메인 왼쪽 사진  ---------------------------------------------------->
<div class="head_main">
  <div class="head_main_left">

    <img class="main_img" src="/img/${productCateCdImgMap['대표']}.png" />

    <ul class="mini_img_set">
      <a> <img class="mini_img 메인1" src="../../img/${productCateCdImgMap['메인1']}.png"> </a>
      <a> <img class="mini_img 메인2" src="../../img/${productCateCdImgMap['메인2']}.png"> </a>
      <a> <img class="mini_img 메인3" src="../../img/${productCateCdImgMap['메인3']}.png"> </a>
      <a> <img class="mini_img 메인4" src="../../img/${productCateCdImgMap['메인4']}.png"> </a>
    </ul>
    <div class="main_left 아래 여백"></div>
  </div>
  <!--------------------------------------------- 메인 오른쪽 정보 --------------------------------------------------->
  <div class="head_main_right">
    <div class="right_info prod_name">${productDto.getName()}</div>
    <div class="right_info prod_prc">
      <p class="price_set dc_cd">
        <strong>${discountRateMap[productDto.getProd_cd()]}</strong>
        %&nbsp;
      </p>
      &nbsp;
      <p class="price_set prod_dscpt">
        <strong>${productDto.getSale_prc()}</strong>
        원&nbsp;
      </p>
      <p class="price_set cnsmr_prc">
        <span>${productDto.getCnsmr_prc()}</span>
        원
      </p>
    </div>
    <div class="right_keyword mbr_rv">
      <span >&nbsp;</span>
      <span class="star_img"></span>
      <span class="star_img"></span>
      <span class="star_img"></span>
      <span class="star_img"></span>
      <span class="star_img"></span>
      &nbsp;
      <span class="score">${reviewStarAvgMap[productDto.getProd_cd()]}</span>점
      (
      <span class="total_num">${reviewCountMap[productDto.getProd_cd()]}</span>
      )
    </div>
    <!--------------------------------------------- 메인 오른쪽 정보 --------------------------->
    <div class="right_keywords_things">
      <dl class="dl_row">
        <dd>
          <ul class="prod_dscpt">
            <li class="prod_dscpt_one">" ${productDto.getDscpt()} "</li>
          </ul>
        </dd>
      </dl>
      <dl class="dl_row">
        <dt>배송방법</dt>
        <dd>
          <ul class="free_dilive">
            <li>EZ무료배송</li>
          </ul>
        </dd>
      </dl>
      <dl class="dl_row">
        <dt>추가혜택</dt>
        <dd>
          <ul class="plus_benefit">
            <li>증정품 : 불닭소스</li>
          </ul>
        </dd>
      </dl>
      <dl class="dl_row">
        <dt>보관방법</dt>
        <dd>
          <ul class="txt-list configSite">
            <li>${productDto.getSfkp_stus()}</li>
          </ul>
        </dd>
      </dl>

      <!------------------------------------------------  메인 오른쪽 아래  ----------------------------------------------->

      <dl class="dl_row choose_option">
        <strong>옵션선택</strong>
        <input type="option" >
      </dl>
    </div>


    <div class="right_keyword 옵션및가격">돈관련

    </div>
  </div>
</div>

<!-------------------------------------------  상세 시작  ---------------------------------------------->

<div class="middle_1">
  <ul class="detail_navi_set" id="section1">
    <li class="detail_navi"><a href="#section1">상품설명</a></li>
    <li class="detail_navi"><a href="#section2">상세정보</a></li>
    <li class="detail_navi"><a href="#section3">상품후기</a></li>
    <li class="detail_navi"><a href="#section4">상품문의</a></li>
  </ul>
  <img class="detail_img" src="../../img/${productCateCdImgMap['상세']}.png" >
</div>
<!-----------------------------------------  나중에 쇼핑몰 광고넣을 수 있음 ------------------------------------->
<div class="middle_2" >
  <img class="이지밀이미지" src="">
</div>
<!--------------------------------------  제품 상세 정보  ------------------------------------------>
<div class="middle_3 dl-row">
  <dl class="dscpt_bottom ">
    <dt id="section2">보관방법</dt>
    <dd>${productDto.getSfkp_mtd()}</dd>
  </dl>
  <dl class="dscpt_bottom ">
    <dt>레시피</dt>
    <dd>${productDto.getRecipe()}</dd>
  </dl>
  <dl class="dscpt_bottom">
    <dt>활용법</dt>
    <dd>${productDto.getMtd()}</dd>
  </dl>
  <dl class="dscpt_bottom ">
    <dt>원산지</dt>
    <dd>${productDto.getOrplc()}</dd>
  </dl>
  <dl class="dscpt_bottom ">
    <dt>유통기한</dt>
    <dd>${productDto.getDistb_tlmt()}</dd>
  </dl>
  <dl class="dscpt_bottom ">
  </dl>
</div>
<!------------------------------------  상품후기 (상단)  ----------------------------------------------------->
<div class="review_head">
  <dt class="bottm_title">&nbsp;&nbsp;상품 후기</dt>
  <button class="make_review_btn" id="section3"><a href="/index">리뷰쓰기</a></button>
</div>
<!--------------------------------------  상품후기 (내용)  --------------------------------------------->
<ul class="rv_container">
  <li class="rv_set">
    <span class="rv_wrt">김자바</span>
    <span class="rv_wrt_dt">2023.06.12.</span>
    <span class="rv_star">★★★★★</span>
    <span class="rv_kword">가성비 굿</span>
    <span class="rv_title">맛있어요</span>
    <span class="rv_stmt">또 먹고 싶어요 추천합니다.또 먹고 싶어요 추천합니다.또 먹고 싶어요 추천합니다.또 먹고 싶어요 추천합니다.또 먹고 싶어요 추천합니다.
                또 먹고 싶어요 추천합니다.또 먹고 싶어요 추천합니다.또 먹고 싶어요 추천합니다.또 먹고 싶어요 추천합니다.또 먹고 싶어요 추천합니다.
                또 먹고 싶어요 추천합니다.또 먹고 싶어요 추천합니다.또 먹고 싶어요 추천합니다.또 먹고 싶어요 추천합니다.또 먹고 싶어요 추천합니다.
            </span>
    <span class="rv_img"><img src="../../img/G005.png" width="100px" height="100px" id="img01"></span>

  </li>

</ul>
<ul class="rv_container">
  <li class="rv_set">
    <span class="rv_wrt">김자바</span>
    <span class="rv_wrt_dt">2023.06.12.</span>
    <span class="rv_star">★★★★★</span>
    <span class="rv_kword">가성비 굿</span>
    <span class="rv_title">맛있어요</span>
    <span class="rv_stmt">또 먹고 싶어요 추천합니다.또 먹고 싶어요 추천합니다.또 먹고 싶어요 추천합니다.또 먹고 싶어요 추천합니다.또 먹고 싶어요 추천합니다.
                또 먹고 싶어요 추천합니다.또 먹고 싶어요 추천합니다.또 먹고 싶어요 추천합니다.또 먹고 싶어요 추천합니다.또 먹고 싶어요 추천합니다.
                또 먹고 싶어요 추천합니다.또 먹고 싶어요 추천합니다.또 먹고 싶어요 추천합니다.또 먹고 싶어요 추천합니다.또 먹고 싶어요 추천합니다.
            </span>
    <span class="rv_img"><img src="../../img/G005.png" width="100px" height="100px" id="img02"></span>

  </li>

</ul>

<!---------------------------------  상품문의  (상단)  ----------------------------------------------------->

<div class="inquiry_head">
  <dt class="bottm_title">&nbsp;&nbsp;상품 문의</dt>
  <button class="make_inquiry_btn" id="section4"><a href="/index">문의하기</a></button>
</div>
<!------------------------------------ 상품문의  (내용)  --------------------------------------------->
<table class="inquiry_container">
  <thead>
  <tr class="header">
    <th class="header_title">제목</th>
    <th class="header_wrt">작성자</th>
    <th class="header_wrt_dt">작성일</th>
    <th class="header_stus">답변상태</th>
  </tr>
  </thead>
  <tbody>
  <tr>
    <td class="inquiry_title accordion">&nbsp;Q. 언제 입고돼요</td>
    <td class="inquiry_wrt">김자*</td>
    <td class="inquiry_wrt_dt">2023.05.02</td>
    <td class="inquiry_stus">답변완료</td>
  </tr>
  <tr>
    <td colspan="4">
      <div class="answer">아 고객님 연락드리겠습니다.</div>
    </td>
  </tr>
  <tr>
    <td class="inquiry_title accordion">&nbsp;Q. 불닭맛 있어요?</td>
    <td class="inquiry_wrt">한자*</td>
    <td class="inquiry_wrt_dt">2023.06.13.</td>
    <td class="inquiry_stus">답변중</td>
  </tr>
  <tr>
    <td colspan="4">
      <div class="answer">아 고객님 연락드리겠습니다.
        아 고객님 연락드리겠습니다.   아 고객님 연락드리겠습니다.
        아 고객님 연락드리겠습니다.   아 고객님 연락드리겠습니다.
        아 고객님 연락드리겠습니다.   아 고객님 연락드리겠습니다.
        아 고객님 연락드리겠습니다.   아 고객님 연락드리겠습니다.
        아 고객님 연락드리겠습니다.   아 고객님 연락드리겠습니다.
        아 고객님 연락드리겠습니다.   아 고객님 연락드리겠습니다.
        아 고객님 연락드리겠습니다.   아 고객님 연락드리겠습니다.
        아 고객님 연락드리겠습니다.   아 고객님 연락드리겠습니다.
        아 고객님 연락드리겠습니다.   아 고객님 연락드리겠습니다.
        아 고객님 연락드리겠습니다.   아 고객님 연락드리겠습니다.
        아 고객님 연락드리겠습니다.   아 고객님 연락드리겠습니다.
        아 고객님 연락드리겠습니다.   아 고객님 연락드리겠습니다.
        아 고객님 연락드리겠습니다.   아 고객님 연락드리겠습니다.
        아 고객님 연락드리겠습니다.   아 고객님 연락드리겠습니다.
        아 고객님 연락드리겠습니다.   아 고객님 연락드리겠습니다.
        아 고객님 연락드리겠습니다.   아 고객님 연락드리겠습니다.


      </div>
    </td>
  </tr>
  </tbody>
</table>
<div class="bottom_empty">
  <dt class="bottm_title">  </dt>
</div>
<!------------------------------------- 아래 빈 공간 ------------------------------------------->

<div class="empty-space">
  <button class="go_up"><a href="#section0">맨 위로 올라가기</a></button>
</div>
<!------------------------------------- 자바스크립트 ------------------------------------------->
<script src="/javascript/productdetail.js"></script>
</body>
</html>
