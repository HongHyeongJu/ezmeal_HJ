<%--
  Created by IntelliJ IDEA.
  User: taewan
  Date: 2023/06/26
  Time: 5:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ezMeal 배송지</title>
</head>
<body>
<ul class="cart__items__ul">
    <!--반복 시작 -->
    <c:forEach addresses="${addressList}" var="item">
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

</body>
</html>
