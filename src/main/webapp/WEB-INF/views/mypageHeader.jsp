<%--
  Created by IntelliJ IDEA.
  User: hubaek
  Date: 2023/07/16
  Time: 8:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/screens/mypageHeader.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>


<div class="head">
    <div class="user-info-box">
        <div class="inner">
            <div class="column user-info">
                            <span class="img">
<%--                                <i class="ico-cou-level01"></i>--%>
                                <img src="${sessionScope.loginMbrInfo.grd_img}" width="70px" alt="등급이미지">
                            </span>
                <div class="txt">
                    <p class="p-name">
                        <strong class="name">${sessionScope.loginMbrInfo.name}님</strong>
                    </p>
                    <ul class="list grade">
                        <li>
                            <i class="ico-bl-crown"></i>
                            ${sessionScope.loginMbrInfo.mbr_grd_name}
                        </li>
                        <li id="groupPointRateText">
                            1%적립
                        </li>
                    </ul>
                    <p class="level" id="levelUp">300,000원 더 구매 시, 청사과로 등급 상승!</p>
                </div>
                <a href="/mypage/memberBenefitsInfo" class="benefit button"> <span>등급 혜택</span> </a>
            </div>
            <!-- user info 끝 -->
            <dl class="column order">
                <dt>주문/배송</dt>
                <dd>
                    <a href="/mypage/orderlist">
                        <Strong class="num" id="orderConuntText">0</Strong>
                        건
                    </a>
                </dd>
            </dl>
            <!-- order -->
            <dl class="column coupon">
                <dt>쿠폰</dt>
                <dd>
                    <a href="/mypage/myCoupon">
                        <strong class="num" id="couponCountText">0</strong>
                        개
                    </a>
                </dd>
            </dl>
            <!-- coupon -->
            <dl class="column point">
                <dt>포인트</dt>
                <dd class="text-primary">
                    <a href="/mypage/myPoint">
                        <strong class="num" id="pointText">0</strong>
                        p
                    </a>
                </dd>
            </dl>
        </div>
    </div>
</div>
<!-- head 끝 -->
<script>
    // 현재 포인트 잔액을 마이페이지 프로필에 띄워주기위한 코드
    $(document).ready(function (){
        $.ajax({
            type : "GET",
            url:"/point",
            success : function (response) {
                $("#pointText").text(response.point);
            }
        });
    });
</script>


</body>
</html>
