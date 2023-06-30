<%--
  Created by IntelliJ IDEA.
  User: taewan
  Date: 2023/06/29
  Time: 3:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>주문/결제창</title>
</head>

<body>
<h3> 주문 상품 </h3>
<p>처음 주문 상품 값 ${cartProductDtos.get(0)} <br>
    주문 상품 총 개수 ${cartProductDtos.size()}</p>

<c:forEach var="item" items="${cartProductDtos}">
    <p> 상품 이미지 <br>
        | 상품코드 ${item.prod_cd} <br>
        | 상품명 :  ${item.name} <br>
        | 구매 수량 : ${item.qty} <br>
        | 소비자가 : ${item.cnsmr_prc} 원 <br>
        | 판매자가 : ${item.sale_prc} 원
    </p>
</c:forEach>

<h3> 주문자 정보 </h3>
<p>
    보내는 분 : ${mbrInfo.name} <br>
    휴대폰 : ${mbrInfo.phone} <br>
    이메일 : ${mbrInfo.email }<br>
</p>


<h3> 배송정보 </h3>

<h3> 배송지 </h3>
<p>${defaultAddress.basic_yn} | ${defaultAddress.desti} | ${defaultAddress.desti_dtl}</p>

<h3> 배송 요청사항 </h3>
<P>공동현관출입방법, 배송요청사항, 배송 메시지: 냉동상품이 도착했습니다. 냉동실에 빠른 보관 부탁드립니다.</P>

<h3> 쿠폰 </h3>
<P>내가 가진 쿠폰 보여주기 -> 쿠폰 사용 유무 및 해당 조건에 사용가능한 쿠폰인지 확인 필요, 총 쿠폰 개수, 사용가능 쿠폰 개수</P>
<h3>적립금사용</h3>
<P>적립금 금액 :</P>
<p>최종결제금액 : <br>
| 적립예정금액 : </p>
ps. 결제 창에서 json으로 데이터를 받은 후 주문 master, 수행
<h3> 개인정보 수집/제공 </h3>
<p> 개인 정보 수집, 이용 및 처리 동의 </p>
<p> 전자지급 결제대행 서비스 이용약관 동의 </p>
<p> 위 내용을 확인 하였으며 결제에 동의합니다. </p>

<h3> 결제 금액</h3>
<p> 주문금액 : ${priceMap.get("orderPrice")} </p>
<p> 상품 금액 : ${priceMap.get("productPrice")}</p>
<p> 상품 할인 금액 : ${priceMap.get("productsDiscount")}</p>

<p>적립금 사용 - 이게 좀 빡세네</p>
<p> 최종결제 금액 </p>
</body>
</html>
