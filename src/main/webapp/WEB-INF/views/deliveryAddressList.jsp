<%--
  Created by IntelliJ IDEA.
  User: taewan
  Date: 2023/06/26
  Time: 4:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>배송지 관리</title>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<body>
<main class="address">
    <div class="address__name">
        <h1>배송지 관리</h1>
    </div>
    <!-- address__name 끝 -->

    <div class="address__main">
        <c:forEach var="address" items="${addressList}">
            <c:if test="${address.basic_yn == 'y'}">
                <%--c:set: 해당 조건에 맞는 변수 생성 --%>
                <c:set var="defaultAddress" value="${address}"/>
            </c:if>
        </c:forEach>
        <%--        반복 위치 address__list--%>
        <div class="address__list">
            <div class="address__list_part">
                <div class="address_title">
                    <div class="address_title_name">${defaultAddress.ncnm} | ${defaultAddress.rcpr}</div>
                    <div class="address_title_default">기본배송지</div>
                </div>
                <!-- address_title 끝-->

                <div class="address_detail">
                    <div class="address_detail_info">
                        <div>${defaultAddress.desti}</div>
                        <div>${defaultAddress.desti_dtl}</div>
                        <div>${defaultAddress.phone}</div>
                    </div>

                    <div class="address_detail_btn">
                        <button type="submit" class="address_detail_btn__edit">
                            수정
                        </button>
                    </div>
                </div>
                <!-- address_detail 끝 -->
            </div>
            <!-- address__list_part 끝 -->
        </div>
        <!-- address__list 끝 -->

        <%--        기본 배송지 아닌 것들 반복 --%>
        <c:forEach var="address" items="${addressList}">
            <c:if test="${address != defaultAddress}">
                <div class="address__list">
                    <div class="address__list_part">
                        <div class="address_title">
                            <div class="address_title_name">${address.ncnm} | ${address.rcpr}</div>
                        </div>
                        <!-- address_title 끝-->

                        <div class="address_detail">
                            <div class="address_detail_info">
                                <div>${address.desti}</div>
                                <div>${address.desti_dtl}</div>
                                <div>${address.phone}</div>
                            </div>

                            <div class="address_detail_btn">
                                <button type="submit" class="address_detail_btn__edit">
                                    수정
                                </button>
                                <button type="submit" class="address_detail_btn__delete">삭제</button>
                            </div>
                        </div>
                        <!-- address_detail 끝 -->
                    </div>
                    <!-- address__list_part 끝 -->
                </div>
                <!-- address__list 끝 -->
            </c:if>
        </c:forEach>
        <button class="address__add">배송지 추가</button>
        <!-- address__add 끝 -->
    </div>
    <!-- address__main 끝 -->
</main>
<!-- main address 끝 -->
</body>
</html>