<%--
  Created by IntelliJ IDEA.
  User: taewan
  Date: 2023/06/20
  Time: 8:42 PM
  To change this template use File | Settings | File Templates.
  / 절대경로 : resource로 넘어가게 되어있다.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>ezmeal - cart</title>
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>

<main class="cart">
    <div class="cart__name">
        <h1>장바구니</h1>
    </div>
    <!-- cart__name 끝 -->

    <div class="cart__main">
        <div class="cart__items">
            <div class="cart__items_category">
                <div class="cart__items_category_btns cart__items_category_type">
                    <!-- TODO
                      button에서 type을 이용한 db 변경하기 -->
                    <form action="/cart/subscript" method="GET">
                        <button type="submit" class="cart__items_category-btn">
                            구독상품
                        </button>
                    </form>
                    |
                    <form action="/cart/general" method="GET">
                        <button type="submit" class="cart__items_category-btn">
                            일반상품
                        </button>
                    </form>
                </div>
                <!-- cart__items_category_btns cart__items_category_type 끝 -->

                <div class="cart__items_category_btns cart__items_nav">
                    <label class="cart__items_nav__choise">
                        <input type="checkbox" class="cart__items_nav__checkbox"/>
                        <span>전체선택 (0/${count}개) |</span>
                    </label>
                    <button class="cart__items_nav__btn_rm">선택삭제</button>
                </div>
                <!--cart__items_category_btns cart__items_nav 끝-->
            </div>
            <!--cart__items_category 끝-->

            <div class="cart__items_list">
                <!-- 구독 ---------------------------------------------- -->
                <c:if test="${not empty cartSubProducts}">
                    <h4 class="cart__items_list-type">
              <span>
                <span><i class="fas fa-seedling" style="color: #2ebd46;"></i></span>
                구독 상품
              </span>
                        <button class="cart__items_list__btn">
                            <i class="fas fa-chevron-down" style="color: #8d9096"></i>
                        </button>
                    </h4>

                    <!--cart__items_list-type 구독 끝-->
                    <ul class="cart__items__ul">
                        <!--반복 시작 -->
                        <c:forEach items="${cartSubProducts}" var="item">
                            <!--구독 식품 반복 시작 -->
                            <li class="cart__item_list">
                                <input type="checkbox"/>
                                <a href="/productlist/${item.prod_cd}" class="cart__item_list__a">
                                    <img src="img/goods.png"/>
                                </a>
                                <!--상품사진 끝-->
                                <div class="cart__item_list_description">
                                    <a href="/productlist/${item.prod_cd}">
                                        <p>${item.name}</p>
                                    </a>
                                </div>
                                <!--상품명 끝-->
                                <div class="cart__item__btn">
                                    <button type="button" aria-label="수량내리기" disabled>
                                        -
                                    </button>
                                    <!--수량 1일 경우, disabled-->
                                    <div>1</div>
                                    <!--default value = 1-->
                                    <button type="button" aria-label="수량올리기">+</button>
                                    <!--db로부터 max 수량 받아오기 - ajax-->
                                </div>
                                <!--상품수량 끝-->

                                <div class="cart__item_price">
                  <span aria-label="할인 가격" data-testid="discount-price">
                          ${item.sale_prc}

                  </span>
                                    <span aria-label="판매 가격" data-testid="product-price">
                                            ${item.cnsmr_prc}

                                    </span>
                                </div>
                                <!--상품 가격 끝-->

                                <button type="button" data-testid="delete">
                                    <span>x</span>
                                </button>
                                <!-- 삭제 버튼 끝 -->
                            </li>
                        </c:forEach>
                        <!--반복 끝 -->
                    </ul>
                    <!-- 구독 ul 끝 -->
                </c:if>
            </div>
        </div>
        <!--cart__items 끝-->

        <div class="cart__benu">
            <div class="dlvar">
                <h3 class="dlvar_nm">
                    <i class="fas fa-map-marker-alt"></i>배송지
                </h3>
                <div class="dlvar_detail">
                    <div class="dlvar_destination">
                        <p>${defaultAddress.desti} </p>
                        <p>${defaultAddress.desti_dtl}</p>
                        <p>TODO | login 안했을 시, 로그인 해주세요!</p>
                    </div>
                    <button class="dlvar_chg" type="button">
                        <span>배송지 변경</span>
                    </button>
                </div>
            </div>
            <!--dlvar 끝-->

            <div class="payment">
                <div class="payment__prod">
                    <span>상품금액</span>
                    <span class="payment__prod_js">12000원</span>
                </div>
                <div class="payment__prod">
                    <span>상품할인금액</span>
                    <span class="payment__prod-dc_js">2000원</span>
                </div>
                <div class="payment__prod">
                    <span>결제예정금액</span>
                    <span class="payment__prod-expect_js">10000원</span>
                </div>
                <div class="payment__prod">
                    <span>적립예정포인트</span>
                    <span class="payment__prod-expect__point">10 point</span>
                </div>
            </div>
            <!-- payment 끝 -->

            <div class="payment-detail">
                <button class="payment-detail__btn">
                    주문하기 | 상품 없을 시, 상품을 담아주세요 (JS)
                </button>
                <ul class="payment-detail__content">
                    <li class="payment-detail__content-li">
                        쿠폰/적립금은 주문서에서 사용 가능합니다.
                    </li>
                    <li class="payment-detail__content-li">
                        [주문완료] 상태일 경우에만 주문 취소 가능합니다.
                    </li>
                    <li class="payment-detail__content-li">
                        [마이페이지 > 주문내역 상세페이지] 에서 직접 취소하실 수
                        있습니다.
                    </li>
                    <li class="payment-detail__content-li">
                        쿠폰, 적립금 사용 금액을 제외한 실 결제 금액 기준으로 최종
                        산정됩니다.
                    </li>
                    <li class="payment-detail__content-li">
                        상품별로 적립금 지급 기준이 다를 수 있습니다. (상품
                        상세페이지에서 확인 가능합니다)
                    </li>
                </ul>
            </div>
            <!-- payment-detail 끝 -->
        </div>
        <!--cart__benu 끝-->
    </div>
    <!-- cart__main 끝 -->
</main>
<!--cart 끝-->
<script
        src="https://kit.fontawesome.com/6478f529f2.js"
        crossorigin="anonymous"
></script>


</body>
</html>

