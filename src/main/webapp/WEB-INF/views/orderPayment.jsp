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
    <title>주문 내역</title>
    <link rel="stylesheet" href="/css/orderPayment.css"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<jsp:include page="mypageHeader.jsp"/>

<div class="main-section">
    <jsp:include page="mypageLeft.jsp"/>
    <div class="main-section-right__order-history">
        <!-- 주문 내역 및 조회 기간 -->
        <div class="order-history__period">
            <div class="order-history__period_title">
                <h2>주문 내역</h2>
                <span>조회기간 - 2023.04.26~2023.05.26 (js)</span>
            </div>
            <div class="order-history__period_detail">
                <span class="order-history__period_detail__title">조회 설정 </span>
                <button class="order-history__period_btn one-month">1개월</button>
                <button class="order-history__period_btn third-month">3개월</button>
                <button class="order-history__period_btn six-month">6개월</button>
                <button class="order-history__period_btn one-year">1년</button>
                <button class="order-history__period_btn personal-period">
                    기간설정
                </button>
                <button class="order-history__period_btn reset-period">초기화</button>
            </div>
        </div>
        <!-- 주문 내역 및 조회 기간 끝-->

        <!-- 반복 시작 : 실제  -->
        <div class="order-history__AJAX-load">
            <!-- 주문 내역 header 시작 -->
            <div class="order-history__products-header">
                <h4 class="order-history__products-header__title">
                    2023.03.23 (20:34)
                </h4>
                <a
                        href="/order/detail/{path-variable}"
                        class="order-history__products-header__detail-link"
                >
                    주문내역 상세보기 >
                </a>
            </div>
            <!-- 주문 내역 header 시작 -->

            <!-- 주문 내역 detail 시작 -->
            <div class="order-history__products-main">
                <div class="order-history__products-main__products">
                    <img src="img/goods.png" class="order-history__products-img"/>
                    <div class=order-history__products-definition">
                        <dl class="order-history__products-definition__list">
                            <dt class="order-history__products-definition__title">상품명</dt>
                            <dd class="order-history__products-definition__detail">
                                [KF365] 국산 블루베리 200g
                            </dd>
                        </dl>
                        <dl class="order-history__products-definition__list">
                            <dt class="order-history__products-definition__title">
                                주문번호
                            </dt>
                            <dd
                                    class="order-history__products-definition__detail order-history"
                            >
                                2307120460177
                            </dd>
                        </dl>
                        <dl class="order-history__products-definition__list">
                            <dt class="order-history__products-definition__title">
                                결제방법
                            </dt>
                            <dd class="order-history__products-definition__detail">
                                카카오페이
                            </dd>
                        </dl>
                        <dl class="order-history__products-definition__list">
                            <dt class="order-history__products-definition__title">
                                결제금액
                            </dt>
                            <dd class="order-history__products-definition__detail">0원</dd>
                        </dl>
                    </div>
                    <!-- order-history__products-definition 끝 -->
                </div>
                <!-- order-history__products-main__products 끝 -->
                <div class="order-history__products-main__status">
                    <span class="order-history__products__status-span">결제완료</span>
                    <div class="order-history__products__status-function">
                        전체취소(기능div)
                    </div>
                </div>
                <!-- order-history__products-main__status 끝 -->
            </div>
            <!-- order-history__products-main 끝 -->
        </div>
        <!-- order-history__AJAX-load -->
    </div>
    <!-- main-section-right__order-history 끝 -->
</div>
</body>
</html>