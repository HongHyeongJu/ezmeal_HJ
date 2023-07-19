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
    <title>주문 상세</title>
    <link rel="stylesheet" href="/css/orderDetail.css"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<jsp:include page="mypageHeader.jsp"/>

<div class="main-section">
    <jsp:include page="mypageLeft.jsp"/>
    <div class="main-section-right__order-detail">
        <!-- 주문 번호 header 시작 -->
        <div class="order-detail__header order-detail__products-header">
            <h4 class="order-detail__products-header__title">
                주문번호 2307120460177
            </h4>
        </div>
        <!-- order-detail__header 끝 -->

        <!-- 주문 내역 detail 시작 -->
        <div class="order-detail__main order-detail__products-main">
            <div class="order-detail__products-main__products">
                <img src="img/goods.png" class="order-detail__products-img"/>
                <div class="order-detail__products-main__products-definition">
                    <a href="" class="order-detail__products-header__detail-link">
                        [KF365] 국산 블루베리 200g
                    </a>
                    <div class="가격 수량">
                        <span>10,355원</span>
                        <span>10,900원</span>
                        <span>1개</span>
                    </div>
                </div>
            </div>
            <!-- order-detail__main 끝 -->

            <div class="order-detail__products-main__status">
                <span class="order-detail__products__status-span">구매확정</span>
                <div class="order-detail__products__status-function">
                    리뷰작성하기
                </div>
            </div>
            <!-- order-detail__products-main__status 끝 -->
        </div>
        <!-- order-detail__products-main 끝 -->

        <!-- 배송조회 header 시작 -->
        <div class="order-detail__header order-detail__products-header">
            <h4 class="order-detail__products-header__title">배송조회</h4>
        </div>
        <!-- 배송조회 header 끝 -->
        <div class="order-detail__main order-detail__delivery-main">
            <span>배송중 단계부터 배송상태 확인이 가능합니다.</span>
        </div>
        <!-- order-detail__main 끝 -->

        <!-- 결제정보 header 시작 -->
        <div class="order-detail__header order-detail__payment-header">
            <h4 class="order-detail__products-header__title">결제정보</h4>
        </div>
        <div class="order-detail__main order-detail__payment-main">
            <ul>
                <li><span>상품금액</span> <span>0원</span></li>
                <li><span>상품할인금액</span> <span>0원</span></li>
                <li><span>배송비</span> <span>0원</span></li>
                <li><span>카드즉시할인</span> <span>0원</span></li>
                <li><span>결제금액</span> <span>0원</span></li>
                <li><span>환불완료금액</span> <span>0원</span></li>
                <li><span>결제방법</span> <span>0원</span></li>
            </ul>
        </div>
        <!-- 결제정보 header 끝 -->

        <!-- 주문정보 header 시작 -->
        <div class="order-detail__header order-detail__order-info-header">
            <h4 class="order-detail__order-info-header__title">주문정보</h4>
        </div>
        <div class="order-detail__main order-detail__order-info-main">
            <ul>
                <li><span>주문번호</span><span>2307120460177</span></li>
                <li><span>보내는 분</span><span>권태완</span></li>
                <li><span>결제일시</span><span>2023-05-30 20:47:24</span></li>
            </ul>
        </div>
        <!-- 주문정보 header 끝 -->

        <!-- 배송정보 header 시작 -->
        <div class="order-detail__header order-detail__address-header">
            <h4 class="order-detail__address-header__title">배송정보</h4>
        </div>
        <div class="order-detail__main order-detail__address-main">
            <ul>
                <li><span>받는분</span><span>권태완</span></li>
                <li><span>핸드폰</span><span>010-9701-****</span></li>
                <li>
                    <span>주소</span>
                    <span>서울특별시 종로구 종로 69 서울 ymca 5층 518호</span>
                </li>
                <li><span>받으실 장소</span><span>문앞</span></li>
                <li><span>공동현관 출입방법</span><span>자유 출입 가능</span></li>
                <li><span>포장 방법 </span><span>종이 포장재</span></li>
            </ul>
        </div>
        <!-- 배송정보 header 끝 -->
    </div>
    <!-- main-section-right__order-detail 끝 -->
</div>
</body>
</html>
