<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.teamProject.ezmeal.domain.CartProductDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%--
  Created by IntelliJ IDEA.
  User: taewan
  Date: 2023/07/04
  Time: 11:36 PM
  To change this template use File | Settings | File Templates.
--%>

<%
    Map<String, List<CartProductDto>> productsMap = (Map<String, List<CartProductDto>>) request.getAttribute("productsMap");
%>

<div class="cart__items_list">
    <%--forEach     : 반복 작업을 수행
        choose-when : 내부 when은 true일 경우 1번만 실행, forEach는 반복작업을 수행하는 역할
        -> choose내부 when이 모든 true인 경우,  choose는 1번만 수행되지만 forEach로 인해서 그다음 choose-when 순차 수행
    --%>
    <c:forEach var="category" items="${productsMap.keySet()}">
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

        <c:if test="${not empty productsMap.get(category)}">
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
                <c:forEach items="${productsMap.get(category)}" var="item">
                    <li class="cart__item_list ${item.soldout_yn eq 'y' ? 'cart__item__soldout' : ''}">
                        <input type="checkbox"
                               cart_prod_seq="${item.cart_prod_seq}"  ${item.soldout_yn eq 'y' ? 'disabled' : ''}/>
                        <a href="/productlist/${item.prod_cd}" class="cart__item_list__a">
                            <img src="/img/${item.prod_cd}.png"/>
                        </a>

                        <div class="cart__item_list_description">
                            <a href="/productlist/${item.prod_cd}">
                                <p class="cart__item_list_prod_cd">
                                    [${item.prod_cd}${item.soldout_yn eq 'y' ? " | 품절" : ''}]</p>
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