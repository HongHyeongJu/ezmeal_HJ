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
    <title>ezmeal | 주문 상세</title>
    <link rel="stylesheet" href="/css/orderDetail.css"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<jsp:include page="mypageHeader.jsp"/>

<div class="main-section">
    <jsp:include page="mypageLeft.jsp"/>
    <div class="main-section-right__order-detail">
        <!-- 타이틀 -->
        <div class="order-detail__title">
            <h2>주문 상세</h2>
        </div>
        <!-- 타이틀 끝 -->
        <!-- 주문 번호 header 시작 -->
        <div class="order-detail__header order-detail__products-header">
            <h4 class="order-detail__products-header__title">
                주문번호 ${orderDetailProductList.get(0).ord_id}
            </h4>
        </div>
        <!-- order-detail__header 끝 -->
        <div>
            <c:forEach var="orderDetailProduct" items="${orderDetailProductList}">
                <!-- 주문 내역 detail 시작 -->
                <div class="order-detail__main order-detail__products-main">
                    <div class="order-detail__products-main__products">
                        <img src="/img/${orderDetailProduct.url}" class="order-detail__products-img"/>
                        <div class="order-detail__products-main__products-definition">
                            <a href="/product/detail?prod_cd=${orderDetailProduct.prod_cd}" class="order-detail__products-header__detail-link">
                                [${orderDetailProduct.prod_cd}] ${orderDetailProduct.name}
                            </a>
                            <div class="가격 수량">
                                <span class="order-detail_seler_prc">${orderDetailProduct.setl_expct_prc_format}원</span>
                                <span class="order-detail_cnsmr_prc"  ${orderDetailProduct.setl_expct_prc_format eq orderDetailProduct.tot_prc_format ? 'hidden' : ''}>${orderDetailProduct.tot_prc_format}원</span>
                                <span>| ${orderDetailProduct.qty}개</span>
                            </div>
                        </div>
                    </div>
                    <!-- order-detail__main 끝 -->

                    <div class="order-detail__products-main__status">
                        <span class="order-detail__products__status-span">${orderDetailProduct.stus}</span>
                        <c:if test="${orderDetailProduct.ord_prod_stus_cd eq 'h6'}">
                            <button class="order-detail__product_fixed" ord_dtl_pk='${orderDetailProduct.ord_prod_dtl_id}'>구매확정하기</button>
                        </c:if>
                        <%-- 처음 들어갈 경우는 jsp에서 load, 구매확정 btn 누를 경우 rest API로 변경 --%>
                        <c:if test="${orderDetailProduct.ord_prod_stus_cd eq 'a3' and orderDetailProduct.review_yn eq 'n'}">
                            <button class="order-detail__product_review btn-open-popup" ord_dtl_pk='${orderDetailProduct.ord_prod_dtl_id}' prod_cd = '${orderDetailProduct.prod_cd}'>리뷰 작성하기</button>
                        </c:if>
                        <c:if test="${orderDetailProduct.ord_prod_stus_cd eq 'a3' and orderDetailProduct.review_yn eq 'y'}">
                            <button class="order-detail__product_review_finish" >리뷰 작성완료</button>
                        </c:if>
                    </div>
                    <!-- order-detail__products-main__status 끝 -->
                </div>
                <!-- order-detail__products-main 끝 -->
            </c:forEach>
        </div>
        <!-- 배송조회 header 시작 -->
        <div class="order-detail__header order-detail__products-header">
            <h4 class="order-detail__products-header__title">배송조회</h4>
        </div>
        <!-- 배송조회 header 끝 -->
        <div class="order-detail__main order-detail__delivery-main">
            <c:if test="${result != 1}">
                <span>배송중 단계부터 배송상태 확인이 가능합니다.</span>
            </c:if>
            <c:if test="${result eq 1}">
                <span> 총 ${deliveryDataCount.get('totalDeliveryCnt')} 건 중 정상 배송 ${deliveryDataCount.get('normalDeliveryCnt')} 건
                <c:if test="${deliveryDataCount.get('waitDeliveryCnt') != 0 }">
                    , 배송 보류 ${deliveryDataCount.get('waitDeliveryCnt')} 건
                </c:if>
                </span>
                <br/>
                <c:forEach var="deliveryHistory" items="${deliveryHistoryList}">
                    <div class="order-detail__dlvar-hist">
                        <p>${deliveryHistory.get("chg_dtm")}</p>
                        <p> ${deliveryHistory.get("chg_rsn")}</p>
                    </div>
                </c:forEach>
            </c:if>

        </div>
        <!-- order-detail__main 끝 -->

        <!-- 결제정보 header 시작 -->
        <div class="order-detail__header order-detail__payment-header">
            <h4 class="order-detail__products-header__title">결제정보</h4>
        </div>
        <div class="order-detail__main order-detail__payment-main">
            <ul>
                <li><span>상품금액</span> <span>${outsideOrderDetailInfo.ord_tot_prc} 원</span></li>
                <li><span>상품할인금액</span> <span>${outsideOrderDetailInfo.tot_dc_prc} 원</span></li>
                <c:if test="${not empty outsideOrderDetailInfo.dexp}">
                    <li><span>배송비</span> <span>${outsideOrderDetailInfo.ord_tot_prc} 원</span></li>
                </c:if>
                <li><span>카드즉시할인</span> <span> 0 원 </span></li>
                <%-- 나중에 카드할인 column 넣어서 만들기 --%>
                <li><span>사용적립금</span> <span> ${outsideOrderDetailInfo.use_pnt} point </span></li>
                <li><span>쿠폰사용금액</span> <span> ${outsideOrderDetailInfo.coupn_use_prc} 원 </span></li>
                <li><span>결제방법</span> <span>${outsideOrderDetailInfo.pay_mtd}</span></li>
                <li><span>결제금액</span> <span>${outsideOrderDetailInfo.pay_prc} 원</span></li>
            </ul>
        </div>
        <!-- 결제정보 header 끝 -->

        <!-- 주문정보 header 시작 -->
        <div class="order-detail__header order-detail__order-info-header">
            <h4 class="order-detail__order-info-header__title">주문정보</h4>
        </div>
        <div class="order-detail__main order-detail__order-info-main">
            <ul>
                <li><span>주문번호</span><span>${orderDetailProductList.get(0).ord_id}</span></li>
                <li><span>보내는 분</span><span>${outsideOrderDetailInfo.name}</span></li>
                <li><span>결제일시</span><span>${outsideOrderDetailInfo.pay_dtm}</span></li>
            </ul>
        </div>
        <!-- 주문정보 header 끝 -->

        <!-- 배송정보 header 시작 -->
        <div class="order-detail__header order-detail__address-header">
            <h4 class="order-detail__address-header__title">배송정보</h4>
        </div>
        <div class="order-detail__main order-detail__address-main">
            <ul>
                <li><span>받는분</span><span>${outsideOrderDetailInfo.rcpr}</span></li>
                <li><span>핸드폰</span><span>${outsideOrderDetailInfo.rcpr_phone}</span></li>
                <li>
                    <span>주소</span>
                    <span>${outsideOrderDetailInfo.sum_desti}</span>
                </li>
                <li><span>받으실 장소</span><span>${outsideOrderDetailInfo.req_mtr}</span></li>
                <li><span>공동현관 출입방법</span><span>${outsideOrderDetailInfo.in_mtd}</span></li>
                <li><span>박스 크기 </span><span>${outsideOrderDetailInfo.box_size} size</span></li>
            </ul>
        </div>
        <!-- 배송정보 header 끝 -->
    </div>
    <!-- main-section-right__order-detail 끝 -->
</div>
<div class="prod_review_modal"></div>
<script src="/javascript/module/admin_order_ajax.js"></script>
<script src="/javascript/orderDetail.js"></script>
<script src="/javascript/prod_review_modal.js"></script>
</body>
</html>
