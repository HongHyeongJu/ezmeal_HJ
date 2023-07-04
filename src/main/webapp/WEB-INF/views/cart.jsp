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
                    <span>전체선택 (0/${count}개) |</span>
                </label>
                <button class="cart__items_nav__btn_rm">선택삭제</button>
            </div>
            <!--cart__items_category_btns cart__items_nav 끝-->

            <div class="cart__items_list">
                <%--forEach     : 반복 작업을 수행
                    choose-when : 내부 when은 true일 경우 1번만 실행, forEach는 반복작업을 수행하는 역할
                    -> choose내부 when이 모든 true인 경우,  choose는 1번만 수행되지만 forEach로 인해서 그다음 choose-when 순차 수행
                --%>
                <c:forEach var="category" items="${ProductsMap.keySet()}">
                    <c:set var="categoryIcon" value=""/>
                    <c:set var="categoryColor" value=""/>
                    <c:set var="categoryLabel" value=""/>

                    <c:choose>
                        <c:when test="${category eq 'cold'}">
                            <c:set var="categoryIcon" value="fa-tint"/>
                            <c:set var="categoryColor" value="#306ed9"/>
                            <c:set var="categoryLabel" value="냉장 상품"/>
                        </c:when>
                        <c:when test="${category eq 'ice'}">
                            <c:set var="categoryIcon" value="fa-igloo"/>
                            <c:set var="categoryColor" value="#306ed9"/>
                            <c:set var="categoryLabel" value="냉동 상품"/>
                        </c:when>
                        <c:when test="${category eq 'outside'}">
                            <c:set var="categoryIcon" value="fa-sun"/>
                            <c:set var="categoryColor" value="#ef8025"/>
                            <c:set var="categoryLabel" value="상온 상품"/>
                        </c:when>
                    </c:choose>

                    <c:if test="${not empty ProductsMap.get(category)}">
                        <h4 class="cart__items_list-type">
                <span>
                    <span><i class="fas ${categoryIcon}" style="color: ${categoryColor}"></i></span>
                    ${categoryLabel}
                </span>
                            <button class="cart__items_list__btn">
                                <i class="fas fa-chevron-down" style="color: #8d9096"></i>
                            </button>
                        </h4>

                        <ul class="cart__items__ul">
                            <c:forEach items="${ProductsMap.get(category)}" var="item">
                                <li class="cart__item_list ${item.soldout_yn eq 'y' ? 'cart__item__soldout' : ''}">
                                    <input type="checkbox"
                                           cart_prod_seq="${item.cart_prod_seq}"  ${item.soldout_yn eq 'y' ? 'disabled' : ''}/>
                                    <a href="/productlist/${item.prod_cd}" class="cart__item_list__a">
                                        <img src="/img/${item.prod_cd}.png"/>
                                    </a>

                                    <div class="cart__item_list_description">
                                        <a href="/productlist/${item.prod_cd}">
                                            <p class="cart__item_list_prod_cd">[${item.prod_cd}${item.soldout_yn eq 'y' ? " | 품절" : ''}]</p>
                                            <br/>
                                            <p>${item.name}</p>
                                        </a>
                                    </div>

                                    <div class="cart__item__btn">
                                        <button type="button" aria-label="수량내리기" disabled>-</button>
                                        <div>1</div>
                                        <button type="button" aria-label="수량올리기">+</button>
                                    </div>

                                    <div class="cart__item_price">
                                        <span aria-label="할인 가격" data-testid="discount-price">${item.sale_prc}</span>
                                        <c:if test="${item.cnsmr_prc ne item.sale_prc}">
                                            <span aria-label="판매 가격" data-testid="product-price"
                                                  class="${item.cnsmr_prc eq item.sale_prc ? "cart__item_product-price-only" : "cart__item_product-price"}">
                                                    ${item.cnsmr_prc}
                                            </span>
                                        </c:if>
                                    </div>

                                    <button class="cart__delete_btn" type="button" data-testid="delete">
                                        <span>x</span>
                                    </button>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                </c:forEach>
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
                                <a href="/login">로그인해주세요</a>
                            </c:when>
                            <c:otherwise>
                                <p>${defaultAddress.desti}</p>
                                <p>${defaultAddress.desti_dtl}</p>
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
<script>
    const deleteBtns = document.querySelectorAll(".cart__delete_btn");

    function deleteCartProduct(e) {
        const parentDiv = e.target.parentNode; // delete btn의 부모 요소 들고 오기
        const deleteProdCd = parentDiv.querySelector(".cart__item_list_prod_cd").textContent.trim().replace(/\[|\]/g, '');
        // console.log(deleteProdCd);
        fetch("/cart/general", {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'

            },
            body: JSON.stringify(deleteProdCd)
        })
            .then(response => {
                if (response.ok) {
                    console.log(response.json());
                } else {
                    throw new Error('Error: ' + response.status);
                }
            })
            .then(updatedPage => {
                console.log(updatedPage);
                // 서버에서 응답이 성공적으로 도착한 경우, JSP로 업데이트된 페이지를 보여줄 수 있습니다.
                // 업데이트된 페이지를 화면에 표시하는 로직을 구현하세요.
            })
            .catch(error => {
                // 서버에서 응답이 실패한 경우에 대한 처리를 여기에 작성하세요.
                console.error('Error:', error);
            });
    }

    for (let deleteBtn of deleteBtns)
        deleteBtn.addEventListener("click", deleteCartProduct);
</script>
</body>
</html>
