<%--
  Created by IntelliJ IDEA.
  User: taewan
  Date: 2023/06/20
  Time: 8:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>cart</title>
</head>
<body>
수행할 spring 작업
<br/>
1. 구독상품 or 일반상품 버튼을 누를 경우 -> cart table에서
<br/> 1) 상품삭제가 된 것을 제외하고
<br/> 2) 유형(구독, 일반)에 맞는 상품 나오도록 수행 -> react or JS로 비동기 작업 수행 <br/><br/>

2. 전체 상품 목록을 보여주는 것 ->
<br/> 1.의 유형의 상품 목록을 측정 * 상품수량과 별개
<br/> -> 상품목록을 선택시, JS로 개수가 오르도록 한다. default는 모두 선택 <br/><br/>

3. product table과 join을 통해서 상품, 상품가격 (소비자가, 판매자가),
<br/>옵션 존재시 상품과 join하지 않고 옵션에 바로 join 들어간다.
<br/> 마지막으로 구독 상품일 경우 배송일자가 들어간다. <br/><br/>

4. 배송지는 회원fk를 이용해서 보여지는 것은 기본배소지 정보,
<br/>배송지 변경을 통해서 배송지의 기본 배송지를 변경하는 logic도 수행한다. <br/><br/>

5. 주문 버튼의 경우 상품이 존재하지 않을 시,
<br/>선택 안할시 상품을 담아주세요 문구로 변경 -> JS <br/><br/><br/>


추가 <br/>
1. 나중에 추가적으로 수행할 것으로 품절 상품의 경우, 회색 처리하기 <br/>

2. 상품이 아무것도 담겨있지 않을 경우,
<br/> 삭제 상품이 3개 이하면 베스트 상품 보여주고,
<br/>삭제 상품 3개 초과시 해당 상품 보여주기

<h1>받아온 data</h1>
<h3>총 상품 개수</h3>
<p>${count} 개</p>

<h3>배송지</h3>
<p>${defaultAddress.ncnm}</p>
<p>${defaultAddress.rcpr} | ${defaultAddress.phone}</p>
<p>${defaultAddress.desti} </p>
<p>${defaultAddress.desti_dtl}</p>


<ul>
    냉장
    <c:forEach var="product" items="${cartProducts}">
        <c:if test="${product.typ eq '냉장'}">

            <li>${product.name} | ${product.cnsmr_prc} | ${product.sale_prc} </li>
        </c:if>
    </c:forEach>
</ul>

<ul>
    냉동
    <c:forEach var="product" items="${cartProducts}">
        <c:if test="${product.typ eq '냉동'}">
            <li>${product.name} | ${product.cnsmr_prc} | ${product.sale_prc} </li>
        </c:if>
    </c:forEach>
</ul>


</body>
</html>
