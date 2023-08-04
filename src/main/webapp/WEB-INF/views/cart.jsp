<%-- Created by IntelliJ IDEA. User: taewan Date: 2023/06/20 Time: 8:42 PM To
change this template use File | Settings | File Templates. --%>
<%@ page
        contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>ezmeal - cart</title>
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<main class="cart">
    <div class="cart__name">
        <h1>장바구니</h1>
    </div>
    <!-- cart__name 끝 -->

    <div class="cart__main">
        <div class="cart__items">
            <div class="cart__items_category_btns cart__items_nav">
                <label class="cart__items_nav__choise">
                    <input type="checkbox" class="cart__items_nav__checkbox"/>
                    <span>전체선택 (<span class="cart__items_selected_num">0</span>/${count}개) | </span>
                </label>
                <button class="cart__items_nav__btn_rm">선택삭제</button>
            </div>
            <!--cart__items_category_btns cart__items_nav 끝-->
            <%--------------------------------------------------------------------------------------------------------------------%>
            <div class="cart__items_list">
                <%--TODO. 동적 html을 보여주는 곳--%>
                <c:if test="${not empty productIce}">
                    <h4 class="cart__items_list-type">
                    <span>
                        <span><i class="fas fa-igloo" style="color: #306ed9"></i></span>
                        냉동 상품
                    </span>
                        <button class="cart__items_list__btn">
                            <i class="fas fa-chevron-down" style="color: #8d9096"></i>
                        </button>
                    </h4>
                    <ul class="cart__items__ul">
                        <c:forEach items="${productIce}" var="item">
                            <li class="cart__item_list  cart__item__soldout_${item.soldout_yn}" cart_prod_seq="${item.cart_prod_seq}" prod_cd="${item.prod_cd}"  opt_seq = ${item.opt_seq}>
                                <input type="checkbox" ${item.soldout_yn eq 'y' ? 'disabled' : 'class="cart__item_nav__checkbox"'}/>
                                <a href="/product/detail?prod_cd=${item.prod_cd}" class="cart__item_list__a">
                                    <img src="/img/${item.prod_cd}.png"/>
                                </a>
                                <div class="cart__item_list_description">
                                    <a href="/product/detail?prod_cd=${item.prod_cd}">
                                        <p class="cart__item_list_prod_cd">
                                            [${item.prod_cd}${item.soldout_yn eq 'y' ? " | 품절" : ''}]</p>
                                        <br/>
                                        <p>${item.name}</p>
                                    </a>
                                </div>
                                <div class="cart__item__btn">
                                    <button type="button" class="count_down__btn">-</button>
                                    <input class="count_num" po_qty="${item.po_qty}" type="text" value="${item.cp_qty}"/>
                                    <button type="button" class="count_up__btn">+</button>
                                </div>
                                <div class="cart__item_price">
                                    <span class="cart__item_sale_prc" part_prc="${item.sale_prc}">${item.sale_prc_format}원</span>
                                    <span class="cart__item_product-price" part_prc="${item.cnsmr_prc}" ${item.cnsmr_prc_format eq item.sale_prc_format ? 'hidden' : ''}>${item.cnsmr_prc_format}원</span>

                                </div>
                                <button class="cart__delete_btn" type="button" data-testid="delete">
                                    x
                                </button>
                            </li>
                        </c:forEach>
                    </ul>
                </c:if>

                <c:if test="${not empty productCold}">
                    <h4 class="cart__items_list-type">
                <span>
                    <span><i class="fas fa-tint" style="color: #306ed9"></i></span>
                    냉장 상품
                </span>
                        <button class="cart__items_list__btn">
                            <i class="fas fa-chevron-down" style="color: #8d9096"></i>
                        </button>
                    </h4>
                    <ul class="cart__items__ul">
                        <c:forEach items="${productCold}" var="item">
                            <li class="cart__item_list  cart__item__soldout_${item.soldout_yn}" cart_prod_seq="${item.cart_prod_seq}" prod_cd="${item.prod_cd}" opt_seq = ${item.opt_seq}>
                                <input type="checkbox" ${item.soldout_yn eq 'y' ? 'disabled' : 'class="cart__item_nav__checkbox"'}/>
                                <a href="/product/detail?prod_cd=${item.prod_cd}" class="cart__item_list__a">
                                    <img src="/img/${item.prod_cd}.png"/>
                                </a>
                                <div class="cart__item_list_description">
                                    <a href="/product/detail?prod_cd=${item.prod_cd}">
                                        <p class="cart__item_list_prod_cd">
                                            [${item.prod_cd}${item.soldout_yn eq 'y' ? " | 품절" : ''}]</p>
                                        <br/>
                                        <p>${item.name}</p>
                                    </a>
                                </div>
                                <div class="cart__item__btn">
                                    <button type="button" class="count_down__btn">-</button>
                                    <input class="count_num" po_qty="${item.po_qty}" type="text" value="${item.cp_qty}"/>
                                    <button type="button" class="count_up__btn">+</button>
                                </div>
                                <div class="cart__item_price">
                                    <span class="cart__item_sale_prc" part_prc="${item.sale_prc}">${item.sale_prc_format}원</span>
                                    <span class="cart__item_product-price" part_prc="${item.cnsmr_prc}" ${item.cnsmr_prc_format eq item.sale_prc_format ? 'hidden' : ''}>${item.cnsmr_prc_format}원</span>
                                </div>
                                <button class="cart__delete_btn" type="button" data-testid="delete">
                                    x
                                </button>
                            </li>
                        </c:forEach>
                    </ul>
                </c:if>

                <c:if test="${not empty productOutside}">
                    <h4 class="cart__items_list-type">
                <span>
                    <span><i class="fas fa-sun" style="color: #ef8025"></i></span>
                    상온 상품
                </span>
                        <button class="cart__items_list__btn">
                            <i class="fas fa-chevron-down" style="color: #8d9096"></i>
                        </button>
                    </h4>
                    <ul class="cart__items__ul">
                        <c:forEach items="${productOutside}" var="item">
                            <li class="cart__item_list  cart__item__soldout_${item.soldout_yn}" cart_prod_seq="${item.cart_prod_seq}" prod_cd="${item.prod_cd}"  opt_seq = ${item.opt_seq}>
                                <input type="checkbox" ${item.soldout_yn eq 'y' ? 'disabled' : 'class="cart__item_nav__checkbox"'}/>
                                <a href="/product/detail?prod_cd=${item.prod_cd}" class="cart__item_list__a">
                                    <img src="/img/${item.prod_cd}.png"/>
                                </a>
                                <div class="cart__item_list_description">
                                    <a href="/product/detail?prod_cd=${item.prod_cd}">
                                        <p class="cart__item_list_prod_cd">
                                            [${item.prod_cd}${item.soldout_yn eq 'y' ? " | 품절" : ''}]</p>
                                        <br/>
                                        <p>${item.name}</p>
                                    </a>
                                </div>

                                <div class="cart__item__btn">
                                    <button type="button" class="count_down__btn">-</button>
                                    <input class="count_num" po_qty="${item.po_qty}" type="text" value="${item.cp_qty}"/>
                                    <button type="button" class="count_up__btn">+</button>
                                </div>
                                <div class="cart__item_price">
                                    <span class="cart__item_sale_prc" part_prc="${item.sale_prc}">${item.sale_prc_format}원</span>
                                    <span class="cart__item_product-price" part_prc="${item.cnsmr_prc}" ${item.cnsmr_prc_format eq item.sale_prc_format ? 'hidden' : ''}>${item.cnsmr_prc_format}원</span>
                                </div>
                                <button class="cart__delete_btn" type="button" data-testid="delete">
                                    x
                                </button>
                            </li>
                        </c:forEach>
                    </ul>
                </c:if>

            </div>

            <!--cart__items_list 끝-->
        </div>
        <!--cart__items 끝-->

        <div class="cart__benu">
            <div class="dlvar">
                <h3 class="dlvar_nm">
                    <i class="fas fa-map-marker-alt"></i>배송지
                </h3>
                <div class="dlvar_detail">
                    <div class="dlvar_destination">
                        <c:choose>
                            <c:when test="${empty defaultAddress}">
                                <a href="/address/add?redirectURL=/cart">배송지 등록 해주세요</a>
                            </c:when>
                            <c:otherwise>
                                <p>${defaultAddress.desti} ${defaultAddress.desti_dtl}</p>
                            </c:otherwise>
                        </c:choose>
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
                    <span class="payment__prod_js">0 원</span>
                </div>
                <div class="payment__prod">
                    <span>상품할인금액</span>
                    <span class="payment__prod-dc_js">0 원</span>
                </div>
                <div class="payment__prod">
                    <span>결제예정금액</span>
                    <span class="payment__prod-expect_js">0 원</span>
                </div>
                <div class="payment__prod">
                    <span>적립예정포인트</span>
                    <span class="payment__prod-expect__point">0 point</span>
                </div>
            </div>
            <!-- payment 끝 -->

            <div class="payment-detail">
                <button class="payment-detail__btn">주문하기</button>
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
<script src="/javascript/cart.js"></script>
</body>
</html>
