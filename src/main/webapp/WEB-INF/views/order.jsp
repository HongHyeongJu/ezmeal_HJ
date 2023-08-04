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
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title></title>
</head>
<link rel="stylesheet" href="/css/style.css"/>

<body>
<jsp:include page="header.jsp"/>
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
                <!-- 주문상품 title 끝-->
                <ul class="order__items__ul">
                    <%--TODO 이거 절대로 li 다음줄로 넘기면 안됨. 개행문자 적용이 되어서 kakao pay시 문제를 발생시킨다.--%>
                    <li class="order__prod_summary"
                        product_cnt="${cartProductList.size()}">${cartProductList.get(0).name} <c:if
                            test="${cartProductList.size() -1 != 0}">외 ${cartProductList.size() -1}건</c:if></li>

                    <c:forEach var="item" items="${cartProductList}">
                        <!--반복 시작 -->
                        <!--장바구니 식품 반복 시작 -->
                        <li class="order__item_list order_li_hidden" cart_prod_seq="${item.cart_prod_seq}">
                            <img src="/img/${item.prod_cd}.png"/>
                            <!--상품사진 끝-->
                            <div class="order__item_list_description">
                                    <p class="cart__item_list_prod_cd">[${item.prod_cd}]</p>
                                    <p> ${item.name}</p>
                            </div>
                            <!--상품명 끝-->
                            <div class="order__item__count">
                                <div> ${item.cp_qty}개</div>
                                <!--default value = 1-->
                            </div>
                            <!--상품수량 끝-->

                            <div class="order__item_price">
                                <span> ${item.sale_prc_format}원 </span>
                                <span class="cart__item_product-price" ${item.cnsmr_prc_format eq item.sale_prc_format ? 'hidden' : ''}>${item.cnsmr_prc_format} 원</span>
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
                        <div>${memberInfo.name}</div>
                    </div>
                    <div class="order_info_template__title">
                        <span>휴대폰</span>
                        <div>${memberInfo.phone}</div>
                    </div>
                    <div class="order_info_template__title">
                        <span>이메일</span>
                        <div>${memberInfo.email}</div>
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
                    <div class="order_info_template__title order_info_delivery">
                        <span>배송지</span>
                        <div class="delivery_address_id" delivery_address_id="${selectedAddress.addr_id}">
                            <div> <span>수령지 별명 </span> <span>${selectedAddress.rcpr}</span></div>
                            <div> <span>수령지 기본 주소</span> <span>${selectedAddress.desti}</span></div>
                            <div> <span>수령지 상세 주소</span>  <span>${selectedAddress.desti_dtl}</span></div>
                            <div> <span>수령인</span>  <span>${selectedAddress.rcpr}</span></div>
                            <div> <span>수령인 연락처</span>  <span>${selectedAddress.phone}</span></div>
                        </div>
                    </div>
                    <div class="order_info_template__title">
                        <span>배송 요청사항</span>
                        <!-- 배송 요청사항 - 선택란 -->
                        <div class="order_info_template__radiobox">
                            <div class="order_info_delivery_place">
                                <span>받으실 장소*</span>
                                <label>
                                    <input type="radio" name="default_address" value="문 앞"/>
                                    <span>문 앞</span>
                                </label>
                                <label>
                                    <input type="radio" name="default_address" value="경비실"/>
                                    <span>경비실</span>
                                </label>
                                <label>
                                    <input type="radio" name="default_address" value="택배함"/>
                                    <span>택배함</span>
                                </label>
                            </div>

                            <div class="order_info_delivery_place_detail">
                                <span>공동현관 출입방법* </span>
                                <label>
                                    <input type="radio" name="come_method" value="공동현관"/>
                                    <span>공동현관</span>
                                </label>
                                <label>
                                    <input type="radio" name="come_method" value="자유 출입 가능"/>
                                    <span>자유 출입 가능</span>
                                </label>
                                <label>
                                    <input type="radio" name="come_method" value="기타"/>
                                    <span>기타</span>
                                </label>
                            </div>
                            <!-- TODO 숨김 JS 수행 필요 -->
                            <div class="order_info_option" style="display: none">
                                <span></span>
                                <label>
                                    <input type="text" name="come_method" placeholder=""/>
                                </label>
                            </div>

                            <div class="order_info_delivery_msgYN">
                                <span>배송 완료 메시지 전송 *</span>
                                <label>
                                    <input type="radio" name="delivery_msg" value="y"/>
                                    <span>예</span>
                                </label>
                                <label>
                                    <input type="radio" name="delivery_msg" value="n"/>
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
                                <button class="order__btn order__coupon btn-open-popup">
                                    사용가능 쿠폰 N장 / 전체 쿠폰 ${couponList.size()}장
                                </button>
                                <div class="order__coupon_pk" hidden>0</div>
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
                            <div class="order_info_template__title order_info_template__title_point1">
                                <span>적립금 적용</span>
                                <input class="order__btn order__point" placeholder="사용가능 적립금 ${pointMap.userPoint}"/>
                                <button class="order__btn order__point_alluse"> 모두사용</button>
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
                            <div class="order_info_template__title pay_methods">
                                <span>결제수단 선택</span>
                                <button class="pay_method order__btn_creditCard" name="creditCard">신용카드</button>
                                <button class="pay_method order__btn_kakao" name="kakaopay">카카오 페이</button>
                                <button class="pay_method order__btn_toss" name="tosspay">Toss</button>
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
                                    <br />
                                    <span>개인정보 수집, 이용 및 처리 동의</span>
                                    <button class="order__agreement__btn">보기</button>
                                </div>
                                <div>
                                    <span>결제대행 서비스 약관 동의</span>
                                    <button class="order__agreement__btn">보기</button>
                                </div>
                                <div>
                                    <span>전자지급 결제대행 서비스 이용약관 동의</span>
                                    <button class="order__agreement__btn">보기</button>
                                </div>
                                <div>
                                    <br />
                                    <span>위 내용을 확인 하였으며 결제에 동의합니다.</span>
                                </div>
                            </div>
                        </div>
                        <!--order_info_template 개인정보 수집 제공 끝 -->
                        <!-- subject : 개인정보 수집 제공 끝 -->
                        <div class="order__price_div">
                            <button class="order__btn order__price">${priceMap.orderPrice} 원 결제하기</button>
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
                                <span class="order_benu__order_price change_payment_price">${priceMap.orderPrice}원</span>
                            </div>

                            <div class="order_benu__title">
                                <span> - 상품금액</span>
                                <span class="order_benu__number">${priceMap.productPrice}원</span>
                            </div>
                            <div class="order_benu__title">
                                <span> - 할인금액</span>
                                <span class="order_benu__number ">-${priceMap.productsDiscount}원</span>
                            </div>

                            <div class="order_benu__title">
                                <span>쿠폰할인</span>
                                <span class="order_benu__coupon change_payment_price">0 원</span>
                            </div>
                            <div class="order_benu__title">
                                <span>적립금사용</span>
                                <span class="order_benu__point change_payment_price">0 point</span>
                            </div>
                            <div class="order_benu__title">
                                <div>
                                    <span>최종결제 금액</span>
                                    <span class="order_benu__total">${priceMap.orderPrice}원</span>
                                </div>
                                <div>
                                    <span>적립 예정 포인트</span>
                                    <span> ${pointMap.pointRate} point</span>
                                </div>
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

    <!-- 쿠폰용 modal -->
    <div class="modal">
        <!-- modal background-->
        <div class="modal_body">
            <!-- modal table 작성 -->
            <table class="order__modal_table">
                <tr class="order__modal_table_row">
                    <th>선택</th>
                    <th>쿠폰명</th>
                    <th>할인 - (정률할인은 최대금액 존재)</th>
                    <th>사용 시작기간 ~ 사용 마감일</th>
                    <th>사용기준</th>
                </tr>
                <c:forEach var="item" items="${couponList}">
                    <tr class="order__modal_table_instance">
                        <td hidden>${item.mbr_coupn_id}</td>
                        <td><input type="radio" name="coupon"/></td>
                        <td class="order__coupon_name">${item.name}</td>
                        <c:if test="${item.val <= 100}">
                            <td class="order__coupon_dc">${item.val}% 할인 - (최대 ${item.max_prc}원)</td>
                        </c:if>
                        <c:if test="${item.val > 100}">
                            <td class="order__coupon_dc">${item.val}원 할인</td>
                        </c:if>
                        <td class="order__coupon_date">${item.vld_start_dt} ~ ${item.vld_end_dt}</td>
                        <td class="order__coupon_rule">${item.use_base_prc} 이상 구매시 사용 가능</td>
                    </tr>
                </c:forEach>
            </table>
            <!-- Modal  table 끝 -->
            <div class="order__modal_btn">
                <button class="order__modal_ok">확인</button>
                <button class="order__modal_cancel">선택 취소</button>
            </div>
        </div>
        <!-- modal main contents-->
    </div>
    <!-- 쿠폰용 modal -->

</main>
<!--order 끝-->
<script src="https://kit.fontawesome.com/6478f529f2.js" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script> <!-- 결제 api -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script> <!-- 결제 api -->
<script src="/javascript/modal.js"></script>
<script src="/javascript/order.js"></script>
</body>
</html>