<%--
  Created by IntelliJ IDEA.
  User: hubaek
  Date: 2023/06/30
  Time: 12:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EZMeal-회원탈퇴</title>
    <link rel="stylesheet" href="/css/screens/withdrawal.css">
</head>
<body>
<section id="contents" class="container">
    <div class="title">
        회원탈퇴안내
    </div>
    <div class="content-section">
        <div class="content">
            <div class="content-side">
                <label class="side">회원탈퇴안내</label>
            </div>
            <div class="content-main">
                <div class="msg1 e114s4r93">고객님께서 회원 탈퇴를 원하신다니 저희 쇼핑몰의 서비스가 많이 부족하고 미흡했나 봅니다. 불편하셨던 점이나 불만사항을 알려주시면 적극 반영해서 고객님의 불편함을 해결해 드리도록 노력하겠습니다.
                    <strong class="notice">아울러 회원 탈퇴시의 아래 사항을 숙지하시기 바랍니다.</strong>
                    <ul class="notice-list-title">
                        <li class="notice-list">1. 회원 탈퇴 시 고객님의 정보는 상품 반품 및 A/S를 위해 전자상거래 등에서의 소비자 보호에 관한 법률에 의거한 고객정보 보호정책에따라 관리 됩니다.</li>
                        <li class="notice-list">2. 탈퇴 시 고객님께서 보유하셨던 적립금은 모두 삭제 됩니다.</li>
                        <li class="notice-list">3. 회원 탈퇴 후 3개월간 재가입이 불가능합니다.</li>
                        <li class="notice-list">4. 회원 탈퇴 시 컬리패스 해지는 별도로 고객행복센터(1644-1107)를 통해서 가능합니다. 직접 해지를 요청하지 않으면 해지 처리가 되지 않습니다.</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="button-section">
        <button class="button1" type="button" height="44" onclick="history.back();">
<%--        <button class="button1" type="button" height="44" onclick="sendGetRequest()">--%>
            <span class="button-msg">취소</span>
        </button>
        <button class="button2" type="submit" height="44" onclick="sendPostRequest()">
            <span class="button-msg">탈퇴</span>
        </button>
    </div>
</section>
<script>
    // function sendGetRequest() {
    //     // AJAX 요청을 생성하여 해당 URI로 POST 요청을 보냄
    //     var xhr = new XMLHttpRequest();
    //     xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    //     xhr.onreadystatechange = function() {
    //         if (xhr.readyState === 4 && xhr.status === 200) {
    //             // 응답을 받았을 때의 동작 처리
    //             console.log(xhr.responseText);
    //             window.location.href = "/";
    //         }
    //     };
    //     xhr.open("GET", "/mypage/withdrawal/cancle", true);
    //     xhr.send("data=value");
    //
    // }
    // function sendGetRequest(url) {
    //     window.location.href = url;
    // }

    function sendPostRequest() {
        // AJAX 요청을 생성하여 해당 URI로 POST 요청을 보냄
        var xhr = new XMLHttpRequest();
        var result = confirm("회원탈퇴를 하시겠습니까?");
        if (result){
            xhr.open("POST", "/mypage/withdrawal", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.send("data=value");
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    // 응답을 받았을 때의 동작 처리
                    alert("회원탈퇴가 완료되었습니다")
                    window.location.href = "/";
                    console.log(xhr.responseText);
                }
            }
        }
    }
</script>
</body>
</html>
