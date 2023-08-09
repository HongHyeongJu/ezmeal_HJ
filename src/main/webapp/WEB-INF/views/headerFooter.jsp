<%--
  Created by IntelliJ IDEA.
  User: hubaek
  Date: 2023/07/13
  Time: 7:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <link rel="stylesheet" href="/css/headerFooter_dropdown.css" />
</head>
<body>
<!--start : 첫번째 헤더(top)-->
<div class="header_box">
    <div class="header_top">
        <div class="logo-box">
            <a href="#">
                <img src="/img/main/EZMEAL_LOGO.jpg" class="ezmeal_logo" />
            </a>
        </div>

        <div class="search_box">
            <input
                    id="search"
                    placeholder="검색어를 입력해주세요"
                    class="search_inner_box"
            />
            <div class="search-btn">
                <a href="#">
                    <i class="fas fa-search" style="font-size: 28px;"></i>
                </a>
            </div>
        </div>
        <!--end : searchbox -->

        <!--start : icon box-->
        <!--start : 아이콘 누르면 나오는 user 드롭다운메뉴-->
        <div class="icon_box">
            <label class="icon_inner_dropdown" for="user_menu">
                <i class="far fa-user" style=" font-size: 32px;"></i>
            </label>
            <input id="user_menu" type="checkbox">
            <nav id="user_menu_nav">
                <ul class="user_menu_drop">
                    <li class="user_menu_drop_list"><a href="#">마이페이지</a></li>
                    <li class="user_menu_drop_list"><a href="#">로그아웃</a></li>
                </ul>
            </nav>
            <!--end : 아이콘 누르면 나오는 user 드롭다운메뉴-->
            <div class="icon_inner_box">
                <a href="#">
                    <i class="far fa-heart" style=" font-size: 32px;"></i>
                </a>
            </div>
            <div class="icon_inner_box">
                <a href="#">
                    <i class="fas fa-shopping-cart" style=" font-size: 30px;"></i>
                </a>
            </div>
        </div>
    </div>

    <!--end : icon box-->

    <!--end : header-top-->

    <!--start : header-bottom-->
    <div class="header-bottom">
        <ul class="sidebar">
            <input type="checkbox" class="openSidebarMenu" id="openSidebarMenu" />
            <label for="openSidebarMenu" class="sidebarIconToggle">
                <div class="spinner diagonal part-1"></div>
                <!--햄버거 첫째줄-->
                <div class="spinner horizontal"></div>
                <!--햄버거 둘째줄-->
                <div class="spinner diagonal part-2"></div>
                <!--햄버거 셋째줄-->
            </label>

            <!--start : sidebar 내용-->
            <div id="sidebarMenu">
                <ul class="sidebarMenuInner">
                    <!--start : 신선식품-->
                    <li class="sidebarMenuInner-list">
                        <a href="#">
                            <i class="fas fa-apple-alt"></i>
                            신선식품</a>
                        <ul class="sidebarMenuInner-Inner">
                            <li class="sidebarMenuInner-Innerlist">
                                <a href="#">전체</a>
                            </li>
                            <li class="sidebarMenuInner-Innerlist">
                                <a href="#">채소</a>
                            </li>
                            <li class="sidebarMenuInner-Innerlist">
                                <a href="#">과일</a>
                            </li>
                        </ul>
                    </li>

                    <!--start : 정육-->
                    <li class="sidebarMenuInner-list">
                        <a href="#">
                            <i class="fas fa-egg"></i>
                            정육</a>
                        <ul class="sidebarMenuInner-Inner">
                            <li class="sidebarMenuInner-Innerlist">
                                <a href="#">전체</a>
                            </li>
                            <li class="sidebarMenuInner-Innerlist">
                                <a href="#">닭고기</a>
                            </li>
                            <li class="sidebarMenuInner-Innerlist">
                                <a href="#">돼지고기</a>
                            </li>
                            <li class="sidebarMenuInner-Innerlist">
                                <a href="#">소고기</a>
                            </li>
                        </ul>

                        <!--start : 샐러드-->
                    </li>
                    <li class="sidebarMenuInner-list">
                        <a href="#">
                            <i class="fas fa-seedling"></i>샐러드</a>
                        <ul class="sidebarMenuInner-Inner">
                            <li class="sidebarMenuInner-Innerlist">
                                <a href="#">전체</a>
                            </li>
                            <li class="sidebarMenuInner-Innerlist">
                                <a href="#">샐러드</a>
                            </li>
                            <li class="sidebarMenuInner-Innerlist">
                                <a href="#">소스</a>
                            </li>
                        </ul>
                    </li>

                    <!--start : 간편식-->
                    <li class="sidebarMenuInner-list">
                        <a href="#">
                            <i class="fas fa-bread-slice"></i>
                            간편식</a>
                        <ul class="sidebarMenuInner-Inner">
                            <li class="sidebarMenuInner-Innerlist">
                                <a href="#">전체</a>
                            </li>
                            <li class="sidebarMenuInner-Innerlist">
                                <a href="#">볶음밥 | 도시락</a>
                            </li>
                            <li class="sidebarMenuInner-Innerlist">
                                <a href="#">밀키트</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
            <!--end : sidebarMenu-->
        </ul>

        <div class="horizonMenu-list">
            <li><a href="#">신상품</a></li>
            <li><a href="#">베스트</a></li>
            <li><a href="#">알뜰쇼핑</a></li>
            <li><a href="#">특가 | 혜택</a></li>
        </div>

        <div class="empty"></div>
    </div>
    <!-- end : header-bottom -->
    <!-- </div>  -->
    <!-- end : header_box -->




    <!--START : FOOTER -->
    <div class="footer_topbox1">
        <!--푸터 1,2 감싸는 박스( 위로 여유 공간포함)-->
        <div class="footer_topbox2">
            <!--푸터 1,2 감싸는 박스( 딱 맞는 사이즈?)-->
            <div class="footer1">
                <div class="footer1_box">
                    <!--end  : 푸터 중간이 비어있는 betweenspace적용된 박스-->
                    <div class="footer1_left">
                        <!--start :왼쪽 footer-->
                        <h2 class="happycenter">고객행복센터</h2>
                        <strong class="callnum"
                        >1541-1541
                            <span class="week_time">월~금요일 오전 9시 - 오후 6시</span>
                        </strong>
                        <div class="inquery">
                            <div class="inquery_box">
                                <button class="inquery_button">1:1 문의</button>
                                <div class="inquery_content">
                                    365일
                                    <br />
                                    고객센터 운영시간에 순차적으로 답변드리겠습니다.
                                </div>
                            </div>
                            <div class="inquery_box">
                                <button class="inquery_button">대량주문 문의</button>
                                <div class="inquery_content">
                                    월~금요일 | 오전9시 - 오후6시
                                    <br />
                                    점심시간 | 낮 12시 - 오후1시
                                </div>
                            </div>
                        </div>
                        <div class="nomember">
                            비회원 문의 : A-team@namgung.com
                            <br />
                            비회원 대량주문 문의 : A-team12@namgung.com
                        </div>
                    </div>
                    <!--end :왼쪽 footer -->

                    <!--start: 오른쪽 footer-->
                    <div class="footer1_right">
                        <ul class="footer1_list">
                            <!--start :오른쪽 푸터 첫번째 (컬리소개, 투자정보~)-->
                            <li class="footer1_list_1">
                                <a href="#" target="_self" class="introduce">컬리소개</a>
                            </li>
                            <li class="footer1_list_1">
                                <a href="#" target="_blank" class="introduce">투자정보</a>
                            </li>
                            <li class="footer1_list_1">
                                <a href="#" target="_blank" class="introduce">인재채용</a>
                            </li>
                            <li class="footer1_list_1">
                                <a href="#" target="_self" class="introduce">이용약관</a>
                            </li>
                            <li class="footer1_list_1">
                                <a href="#" target="_blank" class="introduce"
                                >개인정보처리방침</a
                                >
                            </li>
                            <li class="footer1_list_1">
                                <a href="#" target="_self" class="introduce">이용안내</a>
                            </li>
                        </ul>

                        <!--end :오른쪽 푸터 첫번째 (컬리소개, 투자정보~)-->

                        <div class="footer1_information">
                            <!--start :오른쪽 푸터 두번째 (정보)-->
                            법인명(상호) : 주식회사 A-TEAM | 사업자 등록번호 : 123-45-67890
                            <br />
                            통신판매업 : 제 2022-서울종로-51822호 | 개인정보보호책임자 :
                            이나경
                            <br />
                            주소 : 서울시 종로구 종로 69, 5층 | 대표이사 : 권홍백이
                            <br />
                            제휴문의 : a-team14@namgung.com
                            <br />
                            채용문의 : a-team15@namgung.com
                            <br />
                            팩스 : 123-4567-8900
                        </div>
                        <!--end :오른쪽 푸터 두번째 (정보)-->

                        <!--start : 오른쪽 푸터 세번째(logo)-->
                        <div class="sns_logo_box">
                            <ul class="sns_logo">
                                <a
                                        href="#"
                                        traget="_blank"
                                        rel="noreferrer"
                                        class="sns_logos"
                                >
                                    <img
                                            src="/img/main/instagram.png"
                                            alt="인스타바로가기"
                                    />
                                </a>
                                <a
                                        href="#"
                                        traget="_blank"
                                        rel="noreferrer"
                                        class="sns_logos"
                                >
                                    <img
                                            src="/img/main/facebook.png"
                                            alt="페이스북바로가기"
                                    />
                                </a>
                                <a
                                        href="#"
                                        traget="_blank"
                                        rel="noreferrer"
                                        class="sns_logos"
                                >
                                    <img
                                            src="/img/main/youtube.png"
                                            alt="유튜브바로가기"
                                    />
                                </a>
                            </ul>
                            <!--end : 오른쪽 푸터 세번째(logo)-->
                        </div>
                        <!-- end : 오른쪽 푸터-->
                    </div>
                    <!--end  : 푸터 중간이 비어있는 betweenspace적용된 박스-->
                </div>
                <!--end  : 푸터 왼, 오 싸고있는 박스 -->
            </div>



            <div class="footer2-wrapper">
                <div class="footer2_box">
                    EZMEAL에서 판매되는 상품 중에는 EZMEAL에 입점한 개별 판매자가
                    마켓플레이스(오픈마켓) 상품이 포함되어 있습니다.
                    <br />마켓플레이스(오픈마켓)의 상품의 경우 컬리는 통신판매중개자로서
                    통신판매의 당사자가 아닙니다. 컬리는 해당 상품의 주문, 품질,
                    교환/환불 등 의무와 책임을 부담하지 않습니다
                    <em class="footer2_1">A-TEAM CORP. ALL RIGHTS RESERVED</em>
                </div>
            </div>
            <!--end :푸터 1,2 감싸는 박스( 딱 맞는 사이즈?)-->
        </div>
    </div>
    <!--end :푸터 1,2 감싸는 박스( 위로 여유 공간포함)-->


    <!--start : scroll back to top-->
    <button id="backtotop-btn"><a href="#top" style="color: white">Top</a></button>

    <!--end : back to top button-->
    <script
            src="https://kit.fontawesome.com/6478f529f2.js"
            crossorigin="anonymous"
    ></script>

</body>
</html>
