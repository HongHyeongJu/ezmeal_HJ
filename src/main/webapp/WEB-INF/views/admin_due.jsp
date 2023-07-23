<%--
  Created by IntelliJ IDEA.
  User: taewan
  Date: 2023/07/23
  Time: 4:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" href="/css/components/admin_due.css">
</head>
<div class="admin-order__due-table-div">
    <!-- include에 들어갈 due table -->
    <table class="admin-order__due-table">
        <tr>
            <th>기간</th>
            <td>
                <button class="admin__period_btn today">오늘</button>
                <button class="admin__period_btn yesterday">어제</button>
                <button class="admin__period_btn third-day">3일</button>
                <button class="admin__period_btn one-week">7일</button>
                <button class="admin__period_btn half-month">15일</button>
                <button class="admin__period_btn one-month">30일</button>
                <button class="admin__period_btn three-month">3개월</button>
                <button class="admin__period_btn six-month">6개월</button>
                <input type="date"/>
                <span>~</span>
                <input type="date"/>
                <button class="admin__period_btn personal-day">
                    기간설정 확인
                </button>
            </td>
        </tr>
        <tr>
            <th>검색어</th>
            <td>
                <span>주문번호</span>
                <input type="text"/>
                <button class="admin__period_btn">확인</button>
            </td>
        </tr>
    </table>
    <!-- include due table 끝 -->
</div>
<!-- admin-order__due-table 끝 -->
