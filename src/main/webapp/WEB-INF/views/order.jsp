<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: taewan
  Date: 2023/07/02
  Time: 9:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title></title>
</head>
<link rel="stylesheet" href="/css/style.css" />

<body>
<main class="order">
    <div class="order__name">
        <h1>주문서</h1>
    </div>
    <!-- order__name 끝 -->

    <div class="order__main">
        <div class="order__subjects">
            <div class="order__subjects_list">
                <!--subject : 주문상품 -->
                <h4 class="order_category">
                    <span> 주문상품 </span>
                    <button class="order__items_list__btn">
                        <i class="fas fa-chevron-down" style="color: #0c0c0c"></i>
                    </button>
                </h4>
        <%-- TODO
                <p> 처음 주문 상품 값 ${cartProductDtos.get(0)} <br>
                주문 상품 총 개수 ${cartProductDtos.size()}</p>
        --%>
                <!-- 주문상품 title 끝-->
                <ul class="order__items__ul">
<c:forEach var="item" items="${cartProductDtos}">
                    <!--반복 시작 -->
                    <!--장바구니 식품 반복 시작 -->
                    <li class="order__item_list">
                        <a href="" class="order__item_list__a">
                            <img src="img/goods.png" />
                        </a>
                        <!-- TODO 상품코드 필요 -->
                        <!--상품사진 끝-->
                        <div class="order__item_list_description">
                            <a href="">
                                <p class="cart__item_list_prod_cd">[${item.prod_cd}]</p>
                                <br />
                                <p>${item.name}</p>
                            </a>
                        </div>
                        <!--상품명 끝-->
                        <div class="order__item__count">
                            <div> ${item.qty}개 </div>
                            <!--default value = 1-->
                        </div>
                        <!--상품수량 끝-->

                        <div class="order__item_price">
                  <span aria-label="할인 가격" data-testid="discount-price">
                    ${item.cnsmr_prc}원
                  </span>
                            <span aria-label="판매 가격" data-testid="product-price">
                    ${item.sale_prc}원
                  </span>
                        </div>
                        <!--상품 가격 끝-->
                    </li>
</c:forEach>
                    <!--반복 끝 -->
                </ul>
                <!-- order__items__ul 주문상품 끝 -->
                <!--subject : 주문상품 끝-->

                <!-- subject : 주문자 정보 -->
                <h4 class="order_category">
                    <span> 주문자 정보 </span>
                </h4>
                <!-- 주문자 정보 title 끝-->
                <div class="order_info_template">
                    <div class="order_info_template__title">
                        <span>보내는 분</span>
                        <div>${mbrInfo.name}</div>
                    </div>
                    <div class="order_info_template__title">
                        <span>휴대폰</span>
                        <div>${mbrInfo.phone}</div>
                    </div>
                    <div class="order_info_template__title">
                        <span>이메일</span>
                        <div>${mbrInfo.email}</div>
                    </div>
                </div>
                <!--order_info_template 주문자 정보 끝 -->
                <!-- subject : 주문자 정보 끝 -->

                <!-- subject : 배송정보 -->
                <h4 class="order_category">
                    <span> 배송정보 </span>
                </h4>
                <!-- 배송정보 title 끝-->
                <div class="order_info_template">
                    <div class="order_info_template__title">
                        <span>배송지</span>
                        <div>${defaultAddress.basic_yn} | ${defaultAddress.desti} | ${defaultAddress.desti_dtl}</div>
                    </div>
                    <div class="order_info_template__title">
                        <span>배송 요청사항</span>
                        <!-- 배송 요청사항 - 선택란 -->
                        <div class="order_info_template__radiobox">
                            <div>
                                <span>기본 배송지 (필수) </span>
                                <label>
                                    <input type="radio" name="contact" value="mail" />
                                    <span>문 앞</span>
                                </label>
                                <label>
                                    <input type="radio" name="contact" value="mail" />
                                    <span>경비실</span>
                                </label>
                                <label>
                                    <input type="radio" name="contact" value="mail" />
                                    <span>택배함</span>
                                </label>
                            </div>

                            <div>
                                <span>공동현관 출입방법 (필수)</span>
                                <label>
                                    <input type="radio" name="contact" value="mail" />
                                    <span>공동현관 비밀번호</span>
                                </label>
                                <label>
                                    <input type="radio" name="contact" value="mail" />
                                    <span>자유 출입 가능</span>
                                </label>
                            </div>
                            <!-- TODO 숨김 JS 수행 필요 -->
                            <div class="door_password">
                                <span> 👉 공동현관 비밀번호</span>
                                <label>
                                    <input
                                            type="text"
                                            name="contact"
                                            placeholder="공동현관 비밀번호"
                                    />
                                </label>
                            </div>

                            <div>
                                <span>배송 완료 메시지 전송 (필수)</span>
                                <label>
                                    <input type="radio" name="contact" value="mail" />
                                    <span>예</span>
                                </label>
                                <label>
                                    <input type="radio" name="contact" value="mail" />
                                    <span>아니오</span>
                                </label>
                            </div>
                        </div>
                        <!-- 배송 요청사항 - 선택란 끝 -->
                    </div>
                </div>
                <!--order_info_template 배송정보 끝 -->
                <!-- subject : 배송정보 끝-->

                <div></div>

                <!-- subjects with benu -->
                <div class="order__subjects_list_and_benu">
                    <!-- subject_small -->
                    <div class="order__subjects_small">
                        <!-- subject : 쿠폰 -->
                        <h4 class="order_category_small">
                            <span> 쿠폰 </span>
                        </h4>
                        <!--쿠폰 title 끝-->
                        <div class="order_info_template_small">
                            <div class="order_info_template__title">
                                <span>쿠폰적용</span>
                        <%-- TODO coupon db 받아오기 --%>
                                <button class="order__btn order__coupon">
                                    사용가능 쿠폰 1 db장 / 전체 쿠폰 n db장
                                </button>
                                <!-- TODO 모달 넣기 -->
                            </div>
                        </div>
                        <!--order_info_template 쿠폰 끝 -->
                        <!-- subject : 쿠폰 끝 -->

                        <!-- subject : 적립금 -->
                        <h4 class="order_category_small">
                            <span> 적립금 </span>
                        </h4>
                        <!--적립금 title 끝-->
                        <div class="order_info_template_small">
                            <div
                                    class="order_info_template__title order_info_template__title_point1"
                            >
                                <span>적립금 적용</span>
                                <button class="order__btn order__point">0</button>
                                <button class="order__btn order__point_alluse">
                                    모두사용
                                </button>
                            </div>
                            <div
                                    class="order_info_template__title order_info_template__title_point2"
                            >
                                <span></span> <!-- 들여쓰기 용도 -->
                                사용가능 적립금 ${pointMap.get("usePoint")}원
                            </div>
                        </div>
                        <!--order_info_template 적립금 끝 -->
                        <!-- subject : 적립금 끝 -->

                        <!-- subject : 결제수단 -->
                        <h4 class="order_category_small">
                            <span> 결제수단 </span>
                        </h4>
                        <!--결제수단 title 끝-->
                        <div class="order_info_template_small">
                            <div class="order_info_template__title">
                                <span>결제수단 선택</span>
                                <button
                                        class="order__btn order_btn_method order__btn_creditCard"
                                >
                                    신용카드
                                </button>
                                <button
                                        class="order__btn order_btn_method order__btn_kakao"
                                >
                                    kakao
                                </button>
                                <button class="order__btn order_btn_method order__btn_toss">
                                    Toss
                                </button>
                            </div>
                        </div>
                        <!--order_info_template 결제수단 끝 -->
                        <!-- subject : 결제수단 끝 -->

                        <!-- subject : 개인정보 수집 제공 -->
                        <h4 class="order_category_small">
                            <span> 개인정보 수집 제공 </span>
                        </h4>
                        <!--개인정보 수집 제공 title 끝-->
                        <div class="order_info_template_small">
                            <div class="order__agreement">
                                <div>
                                    <span>개인정보 수집, 이용 및 처리 동의</span>
                                    <button>보기</button>
                                    <br />
                                    <span>개인정보 수집, 이용 및 처리 동의</span>
                                    <button>보기</button>
                                </div>
                            </div>
                        </div>
                        <!--order_info_template 개인정보 수집 제공 끝 -->
                        <!-- subject : 개인정보 수집 제공 끝 -->
                        <div class="order__price_div">
                            <button class="order__btn order__price">JS(쿠폰, 적립금 때문)원 결제하기</button>
                        </div>
                    </div>
                    <!-- subject_small 끝-->

                    <!-- benu -->
                    <div class="order__benu_position">
                        <h3 class="order__benu_category">
                            <i class="fas fa-wallet"></i>결제금액
                        </h3>

                        <div class="order_benu">
                            <div class="order_benu__title">
                                <span>주문금액</span>
                                <span class="order_benu__number">${priceMap.get("orderPrice")}원</span>
                            </div>

                            <div class="order_benu__title">
                                <span> - 상품금액</span>
                                <span class="order_benu__number">${priceMap.get("productPrice")}원</span>
                            </div>
                            <div class="order_benu__title">
                                <span> - 할인금액</span>
                                <span class="order_benu__number">-${priceMap.get("productsDiscount")}원</span>
                            </div>

                            <div class="order_benu__title">
                                <span>쿠폰할인</span>
                                <span class="order_benu__number">JS원</span>
                            </div>
                            <div class="order_benu__title">
                                <span>적립금사용</span>
                                <span class="order_benu__number">JS</span>
                            </div>
                            <div class="order_benu__title">
                                <span>최종결제 금액</span>
                                <span class="order_benu__number">JS원</span>
                                <p>적립 예정 금액 : JS - back에서 다시 계산 필요</p>
                            </div>
                        </div>
                        <!-- order_benu 끝 -->
                    </div>

                    <!-- benu 끝 -->
                </div>
                <!-- subjects with benu 끝 -->
            </div>
            <!--order__subjects_list 끝-->
        </div>
        <!--order__subjects 끝-->
    </div>
    <!-- order__main 끝 -->
</main>
<!--order 끝-->
<script
        src="https://kit.fontawesome.com/6478f529f2.js"
        crossorigin="anonymous"
></script>
</body>
</html>