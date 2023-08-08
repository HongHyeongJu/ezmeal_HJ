<%--
  Created by IntelliJ IDEA.
  User: taewan
  Date: 2023/06/26
  Time: 5:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ezmeal | 배송지</title>
</head>
<body>
<h1>배송지 ${previousAddress != null ? "수정" : "추가"}</h1>

<form action="${previousAddress != null ? "modify" : "add"}" method="post">

    <label for="address_name">배송지명</label>
    <input type="text" id="address_name" name="ncnm" value="${previousAddress !=null ? previousAddress.ncnm : ""}"/>

    <label for="address_recipient">수령인</label>
    <input type="text" id="address_recipient" name="rcpr"
           value="${previousAddress !=null ? previousAddress.rcpr : ""}"/>

    <label for="address_phone">휴대전화</label>
    <input type="text" id="address_phone" name="phone" value="${previousAddress !=null ? previousAddress.phone : ""}"/>

    <label for="address">주소</label>
    <input type="text" id="address" name="desti" value="${previousAddress !=null ? previousAddress.desti : ""}"/>

    <label for="address_detail">상세 주소</label>
    <input type="text" id="address_detail" name="desti_dtl"
           value="${previousAddress !=null ? previousAddress.desti_dtl : ""}"/>

    <label class="address_basic">기본 배송지로 저장하기</label>
    <input type="checkbox" class="address_basic" name="basic_yn" ${previousAddress.basic_yn !="n" ? "checked" : ""} />
    <input type="hidden" name="addr_id" value=${previousAddress.addr_id}>
    <input type="submit" value="배송지 추가"/>
</form>

</body>
</html>
