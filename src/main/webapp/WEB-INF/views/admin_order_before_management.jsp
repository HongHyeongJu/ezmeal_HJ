<%--
  Created by IntelliJ IDEA.
  User: taewan
  Date: 2023/07/23
  Time: 4:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ezmeal_admin | 발주관리</title>
    <link rel="stylesheet" href="/css/screens/admin_order_before_management.css">
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
            <h2>발주확인 관리</h2>
            <%@ include file="admin_due.jsp" %> <!-- 관리자 메뉴 인클루드 -->
            <div class="admin-order__contents">
                <!-- admmin-order__nav 끝 -->
                <ul class="admin-order__nav">
                    <li class="admin-order__nav-li">주문번호별</li>
                </ul>

                <div class="admin-order__content-box">
                    <div class="admin-order__option">
                        <button>엑셀다운로드</button>
                        <button>인쇄</button>
                    </div>
                    <div class="admin-order__check-order">
                        <button>발주확인</button>
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
                                <th><input type="checkbox"/></th>
                                <th>주문일</th>
                                <th>주문번호</th>
                                <th>상품명 - 옵션포함</th>
                                <th>주문자</th>
                                <th>입금자</th>
                                <th>입금은행</th>
                                <th>결제방식</th>
                                <th>메모</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><input type="checkbox"/></td>
                                <td>2023-07-20</td>
                                <td>ORD2023072001</td>
                                <td>아이패드 프로 12.9 - 256GB, 스페이스</td>
                                <td>홍길동</td>
                                <td>홍길동</td>
                                <td>국민은행</td>
                                <td>신용카드</td>
                                <td>특별 배송 요청: 현관문 앞에 놓아주세요.</td>
                            </tr>
                            <tr>
                                <td><input type="checkbox"/></td>
                                <td>2023-07-22</td>
                                <td>ORD2023072202</td>
                                <td>갤럭시 버즈 프로 - 블랙</td>
                                <td>김철수</td>
                                <td>김철수</td>
                                <td>우리은행</td>
                                <td>무통장 입금</td>
                                <td>입금 확인 후 상품 발송 예정</td>
                            </tr>
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
</body>
</html>
