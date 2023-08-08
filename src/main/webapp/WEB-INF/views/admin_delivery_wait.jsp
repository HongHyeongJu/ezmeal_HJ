<%--
  Created by IntelliJ IDEA.
  User: taewan
  Date: 2023/07/26
  Time: 10:46 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ezmeal 관리자 | 배송 대기 관리</title>
    <link rel="stylesheet" href="/css/screens/admin_order.css">
</head>
<body>
<!-- 관리자 페이지 헤더 -->
<%@ include file="admin_header.jsp" %>  <!-- 관리자 헤더 인클루드 -->

<!-- 헤더 아래부분 전체 div
[ 메뉴 div /  관리자 담당 페이지 div  ] -->
<div class="all_container_div">

    <%@ include file="admin_menu.jsp" %> <!-- 관리자 메뉴 인클루드 -->

    <div class="admin_container_div">
        <div class="admin_container_div-order">
            <h2>배송 준비중 관리</h2>
            <%@ include file="admin_due.jsp" %> <!-- 관리자 기간 검색 인클루드 -->
            <div class="admin-order__contents">
                <!-- admin-order__nav 끝 -->
                <ul class="admin-order__nav">
                    <li class="admin-order__nav-li">주문번호별</li>
                    <li class="admin-order__nav-li">해외배송별</li>
                    <li class="admin-order__nav-li">상품별</li>
                    <li class="admin-order__nav-li">배송번호별</li>
                    <li class="admin-order__nav-li">추가주문</li>
                    <li class="admin-order__nav-li">배송보류</li>
                    <li class="admin-order__nav-li">묶음배송</li>
                </ul>

                <div class="admin-order__content-box">
                    <div class="admin-order__option">
                        <button>SMS 일괄배송</button>
                        <button>엑셀다운로드</button>
                        <button>인쇄</button>
                    </div>
                    <div class="admin-order__check-order">
                        <button>배송중 처리</button>
                        <button>송장번호 저장</button>
                        <button>묶음배송처리</button>
                        <button>묶음배송일괄처리</button>
                        <button>묶음배송해제</button>
                        <button>배송보류 처리</button>
                    </div>

                    <div class="admin-order__select">
                        <select name="admin-order__select-type">
                            <option value="order">주문일순</option>
                            <option value="order-reverse">주문일역순</option>
                            <option value="payment">결제액순</option>
                            <option value="payment-reverse">결제액역순</option>
                        </select>
                        <select name="admin-order__select-number">
                            <option value="order">10개씩보기</option>
                            <option value="order-reverse">20개씩보기</option>
                            <option value="payment">30개씩보기</option>
                            <option value="payment-reverse">50개씩보기</option>
                        </select>
                    </div>
                    <!-- admin-order__select 끝 -->


                    <div class="admin-order__content-table__div">
                        <table class="admin-order__content-table">
                            <thead>
                            <tr>
                                <th><input type="checkbox" /></th>
                                <th>주문일</th>
                                <th>주문번호</th>
                                <th>주문자</th>
                                <th>묶음선택</th>
                                <!-- 동일 주문번호 내부에서만 묶음선택 가능-->
                                <th><input type="checkbox" name="check" id="check" /></th>
                                <!-- 묶음 선택으로 묶인 것중에서 묶음 배송을 할 것 check -> 묶음 배송 처리 btn 눌러서 update-->
                                <th>운송장정보</th>
                                <!-- td에 select로 자체배송, 우체국 배송 , 넣어주기 | input 만들어서 송정번호저장하기 -->
                                <th>배송비</th>
                                <th>공급사</th>
                                <!-- 운송장정보에 따라서 자체공급, 우체국 이란 값이 나오도록 함 -->
                                <th>상품명/옵션</th>
                                <th>수량</th>
                                <th>상품구매금액</th>
                                <th>총 실결제금액</th>
                                <th>결제수단</th>
                                <th>메모</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%-- TODO 동적 data 들어오는 부분--%>
                            </tbody>
                        </table>
                    </div>
                </div>
                <!-- admin-order__content-box 끝 -->
            </div>
            <!-- admin-order__contents 끝 -->
        </div>

    </div>
    <!-- admin_container_div 끝 -->
</div>
<script src="/javascript/module/admin_due.js"></script>
<script src="/javascript/module/check_box_module.js"></script>
<script src="/javascript/module/admin_order_ajax.js"></script>
<script src="/javascript/module/admin_order.js"></script>
<%--<script src="/javascript/admin_delivery_prepare.js"></script>--%>
</body>
</html>