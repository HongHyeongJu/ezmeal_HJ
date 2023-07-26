<%@ page import="com.teamProject.ezmeal.domain.MemberDto" %>
<%@ page import="com.teamProject.ezmeal.service.MemberService" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ page session="false" %>--%>
<%--<c:set var="loginId"--%>
<%--       value="${pageContext.request.getSession(false)==null ? '' : pageContext.request.session.getAttribute('id')}"/>--%>

<c:set var="loginOutLink" value="${sessionScope.memberId==null ? '/login' : '/logout'}"/>
<c:set var="loginOut" value="${sessionScope.memberId==null ? '로그인' : '로그아웃'}"/>
<%--<%--%>
<%--    HttpSession session1 = request.getSession();--%>
<%--    Long memberId = (Long) session1.getAttribute("memberId");--%>
<%--    MemberService memberService = (MemberService) request.getAttribute("memberService");--%>
<%--    if (memberId != null && memberService != null) {--%>
<%--        MemberDto loginMbrInfo = memberService.mbrInfo(memberId);--%>
<%--        request.setAttribute("loginMbrInfo", loginMbrInfo);--%>
<%--    }--%>

<%--%>--%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>ezMeal-test</title>
</head>
<body>

<jsp:include page="header.jsp"/>
<div style="padding-top: 100px">

  <li><a href="/login">로그인 - test용 Id: 12, pw: 12 </a></li>

  <p> wan </p>
  <li><a href="/cart">장바구니</a></li>
  <li><a href="/address">배송지</a></li>
  <li><a href="/order">주문서</a></li>
  <li><a href="/orderPayment">주문 내역</a></li>
  <li><a href="/admin/order/before-management">관리자 - 발주</a></li>
  <li><a href="/admin/delivery/prepare">관리자 - 배송준비중</a></li>




  <br/>
  <br/>

  <p> hhj </p>
  <ul>
    <li><a href="/product/catelist?cate_cd=02">상품 목록</a></li>
    <li><a href="/product/detail?cate_cd=05&prod_cd=3">상품 상세(옵션X)</a></li>
    <li><a href="/product/detail?cate_cd=05&prod_cd=16">상품 상세(옵션O)</a></li>







  </ul>
  <br/>
  <br/>

  <p> baek </p>
  <ul>
    <c:if test="${not empty loginMbrInfo}">
      <li><a href="/mypage/modify">회원명 : ${loginMbrInfo.name}</a></li>
    </c:if>
    <li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
    <li><a href="/member/signup">회원가입</a></li>
    <li><a href="/mypage/modify">마이페이지</a></li>








  </ul>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
