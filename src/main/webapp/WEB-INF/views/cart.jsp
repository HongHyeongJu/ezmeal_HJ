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

            <jsp:include page="cartProductModule.jsp"  />

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

    // delete REST API
    function deleteCartProduct(e) {
        // delete btn의 부모 요소 들고 오기
        const parentDiv = e.target.parentNode;

        // 부모요소에서 input 내부 property의 값을 가지고 오기
        const cartProdSeq = parentDiv.querySelector("input[cart_prod_seq]").getAttribute('cart_prod_seq');

        // rest API 수행 , server로 값 보내기
        fetch("/cart/delete", {
            method: 'PATCH',
            headers: {
                // content-type 요청 보내기 | accept 응답 받기
                'Content-Type': 'application/json',
                'accept': 'text/html'
            },
            // 요청 보내는 경우, 형식을 지켜줘야함
            body: JSON.stringify(cartProdSeq)
        })
            .then(response => {
                // 응답을 받는 경우, 형식을 지켜줘야한다.
                if (response.ok) {
                    return console.log(response)
                }
                throw new Error('Error: ' + response.status);
            })
            .then(data => {
                console.log(data);
            })
            .catch(error => {
                // 서버에서 응답이 실패한 경우에 대한 처리를 여기에 작성하세요.
                console.error('Error:', error);
            });
    }

    // 모든 삭제 btn에 관련된 요소 listen하기
    for (let deleteBtn of deleteBtns)
        deleteBtn.addEventListener("click", deleteCartProduct);
</script>
</body>
</html>
