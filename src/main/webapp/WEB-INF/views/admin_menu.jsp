<%--
  Created by IntelliJ IDEA.
  User: hhju2
  Date: 2023-07-20
  Time: AM 9:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ezmeal_admin</title>
    <link rel="stylesheet" href="/css/screens/admin_menu.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
</head>
<body>

    <div class="menu_container_div">
        <ul id="menu_list">

            <a href="홈페이지 주소">
                <li class="main_li">
                    <i class="fas fa-home"></i>&nbsp;&nbsp;&nbsp;홈
                </li>
            </a>

            <li class="order main_li" id="order"><i class="fas fa-shopping-cart"></i>&nbsp;&nbsp;주문</li>
            <a href="홈페이지 주소">
                <li class="hidden order_li sub_li">주문 대시보드</li>
            </a>
            <a href="홈페이지 주소">
                <li class="hidden order_li sub_li">전체 주문 조회</li>
            </a>
            <a href="홈페이지 주소">
                <li class="hidden order_li sub_li">배송 중비중 관리</li>
            </a>
            <a href="홈페이지 주소">
                <li class="hidden order_li sub_li">배송 대기 관리</li>
            </a>
            <a href="홈페이지 주소">
                <li class="hidden order_li sub_li">배송 중 관리</li>
            </a>
            <a href="홈페이지 주소">
                <li class="hidden order_li sub_li">배송 완료 조회</li>
            </a>
            <a href="홈페이지 주소">
                <li class="hidden order_li sub_li">취소</li>
            </a>
            <a href="홈페이지 주소">
                <li class="hidden order_li sub_li">반품</li>
            </a>
            <a href="홈페이지 주소">
                <li class="hidden order_li sub_li">환불</li>
            </a>


            <li class="prod main_li" id="prod"><i class="fas fa-boxes"></i>&nbsp;&nbsp;상품</li>
            <a href="홈페이지 주소">
                <li class="hidden prod_li sub_li">상품 대시보드</li>
            </a>
            <a href="홈페이지 주소">
                <li class="hidden prod_li sub_li">상품 목록</li>
            </a>
            <a href="홈페이지 주소">
                <li class="hidden prod_li sub_li">상품 등록</li>
            </a>
            <a href="홈페이지 주소">
                <li class="hidden prod_li sub_li">상품 진열</li>
            </a>
            <a href="홈페이지 주소">
                <li class="hidden prod_li sub_li">상품 옵션 관리</li>
            </a>
            <a href="홈페이지 주소">
                <li class="hidden prod_li sub_li">재고 관리</li>
            </a>
            <a href="홈페이지 주소">
                <li class="hidden prod_li sub_li">상품 할인코드 관리</li>
            </a>
            <a href="홈페이지 주소">
                <li class="hidden prod_li sub_li">거래처 관리</li>
            </a>

            <li class="member main_li" id="member"><i class="fas fa-user"></i>&nbsp;&nbsp;&nbsp;고객</li>
            <a href="홈페이지 주소">
                <li class="hidden member_li sub_li">회원정보 조회</li>
            </a>
            <a href="홈페이지 주소">
                <li class="hidden member_li sub_li">회원 등급별 관리</li>
            </a>
            <a href="홈페이지 주소">
                <li class="hidden member_li sub_li">탈퇴 관리</li>
            </a>
            <a href="홈페이지 주소">
                <li class="hidden member_li sub_li">휴면 회원 관리</li>
            </a>
            <a href="홈페이지 주소">
                <li class="hidden member_li sub_li">접속 관리</li>
            </a>
            <a href="홈페이지 주소">
                <li class="hidden member_li sub_li">적립금 관리</li>
            </a>
            <a href="홈페이지 주소">
                <li class="hidden member_li sub_li">쿠폰 관리</li>
            </a>


            <li class="board main_li" id="board"><i class="fas fa-clipboard"></i>&nbsp;&nbsp;&nbsp;&nbsp;게시판</li>
            <a href="홈페이지 주소">
                <li class="hidden board_li sub_li">게시판 대시보드</li>
            </a>
            <a href="홈페이지 주소">
                <li class="hidden board_li sub_li">공지게시판 관리</li>
            </a>
            <a href="홈페이지 주소">
                <li class="hidden board_li sub_li">상품후기게시판 관리</li>
            </a>
            <a href="홈페이지 주소">
                <li class="hidden board_li sub_li">1:1문의게시판 관리</li>
            </a>

            <li class="main_li"><i class="fas fa-chart-bar"></i>&nbsp;&nbsp;&nbsp;통계</li>
            <li class="main_li"><i class="fas fa-comments-dollar"></i>&nbsp;&nbsp;프로모션</li>
            <li class="main_li"><i class="fas fa-calendar-check"></i>&nbsp;&nbsp;&nbsp;운영일정</li>
            <li class="main_li"><i class="fas fa-shipping-fast"></i>&nbsp;&nbsp;EZ배송관리</li>
            <li class="main_li"><i class="fas fa-warehouse"></i>&nbsp;&nbsp;상품창고</li>
            <li class="main_li"><i class="fas fa-database"></i>&nbsp;&nbsp;&nbsp;데이터분석</li>
            <li class="main_li"><i class="fas fa-id-card-alt"></i>&nbsp;&nbsp;관리자정보</li>
            <li class="main_li"><i class="fas fa-journal-whills"></i>&nbsp;&nbsp;&nbsp;쇼핑몰 정책</li>
            <li class="main_li">&nbsp;</li>

        </ul>
    </div>


    <script>

        let menuList = document.getElementById('menu_list');

        menuList.addEventListener('click', function (e) {
            let targetElement = e.target;

            while (targetElement != null && targetElement.nodeName != "LI") {
                targetElement = targetElement.parentElement;
            }

            if (targetElement != null && targetElement.classList.contains('main_li')) {
                let id = targetElement.id;
                let submenuItems = document.getElementsByClassName(id + '_li');

                for (let j = 0; j < submenuItems.length; j++) {
                    if (submenuItems[j].classList.contains('hidden')) {
                        submenuItems[j].classList.remove('hidden');
                    } else {
                        submenuItems[j].classList.add('hidden');
                    }
                }
                e.preventDefault(); //상위 메뉴 클릭시 페이지 전환 방지
            }
        });

    </script>

</body>
</html>
