<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.teamProject.ezmeal.dao.NoticeDao" %><%--
  Created by IntelliJ IDEA.
  User: lee nakyeong
  Date: 2023-07-28
  Time: 오전 8:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/screens/notice_side.css">

</head>
<body>

<jsp:include page="header.jsp"/>

<div class="notice-content-box">
    <div class="notice-content">
        <!--start : 사이드 메뉴-->
        <div class="notice-left">
            <div class="notice-sidemenu-title"><a href="#">고객센터</a></div>

            <ul class="notice-sidemenu-list">
                <li>
                    <a href="#" class="notice-sidemenu-list-title"
                    >공지사항
                        <svg
                                xmlns="http://www.w3.org/2000/svg"
                                width="19"
                                height="19"
                                viewBox="0 0 24 24"
                        >
                            <defs>
                                <path
                                        id="gfk9q0rhta"
                                        d="M1.657 1.657L9.657 1.657 9.657 9.657"
                                ></path>
                            </defs>
                            <g fill="none" fill-rule="evenodd">
                                <g>
                                    <g>
                                        <g>
                                            <g
                                                    transform="translate(-339 -398) translate(0 386) translate(339 12) translate(4.69 6.343)"
                                            >
                                                <use
                                                        stroke="#00c728"
                                                        stroke-linecap="round"
                                                        stroke-width="1.8"
                                                        stroke-linejoin="round"
                                                        transform="rotate(45 5.657 5.657)"
                                                        xlink:href="#gfk9q0rhta"
                                                ></use>
                                            </g>
                                        </g>
                                    </g>
                                </g>
                            </g>
                        </svg>
                    </a>
                </li>
                <li>
                    <a href="#" class="notice-sidemenu-list-title"
                    >자주묻는 FAQ
                        <svg
                                xmlns="http://www.w3.org/2000/svg"
                                width="19"
                                height="19"
                                viewBox="0 0 24 24"
                        >
                            <defs>
                                <path
                                        id="gfk9q0rhta"
                                        d="M1.657 1.657L9.657 1.657 9.657 9.657"
                                ></path>
                            </defs>
                            <g fill="none" fill-rule="evenodd">
                                <g>
                                    <g>
                                        <g>
                                            <g
                                                    transform="translate(-339 -398) translate(0 386) translate(339 12) translate(4.69 6.343)"
                                            >
                                                <use
                                                        stroke="#00c728"
                                                        stroke-linecap="round"
                                                        stroke-width="1.8"
                                                        stroke-linejoin="round"
                                                        transform="rotate(45 5.657 5.657)"
                                                        xlink:href="#gfk9q0rhta"
                                                ></use>
                                            </g>
                                        </g>
                                    </g>
                                </g>
                            </g>
                        </svg>
                    </a>
                </li>
                <li>
                    <a href="#" class="notice-sidemenu-list-title"
                    >1:1 문의하기
                        <svg
                                xmlns="http://www.w3.org/2000/svg"
                                width="19"
                                height="19"
                                viewBox="0 0 24 24"
                        >
                            <defs>
                                <path
                                        id="gfk9q0rhta"
                                        d="M1.657 1.657L9.657 1.657 9.657 9.657"
                                ></path>
                            </defs>
                            <g fill="none" fill-rule="evenodd">
                                <g>
                                    <g>
                                        <g>
                                            <g
                                                    transform="translate(-339 -398) translate(0 386) translate(339 12) translate(4.69 6.343)"
                                            >
                                                <use
                                                        stroke="#00c728"
                                                        stroke-linecap="round"
                                                        stroke-width="1.8"
                                                        stroke-linejoin="round"
                                                        transform="rotate(45 5.657 5.657)"
                                                        xlink:href="#gfk9q0rhta"
                                                ></use>
                                            </g>
                                        </g>
                                    </g>
                                </g>
                            </g>
                        </svg>
                    </a>
                </li>
            </ul>

            <!--end : side-menu -->

            <div class="side-guide-box">
                <dl>
                    <dt>고객센터</dt>
                    <dd class="phone-num">012-3456-7890</dd>
                    <dd>평일 <span class="text-num-md">09:00 ~ 18:00</span></dd>
                    <dd>주말, 공휴일 휴무</dd>
                </dl>
            </div>
            <!--end: side-guide-box -->
        </div>
        <!--end : side-menu-box -->

        <!--end : side-menu+side-guide-box(notice-left)-->

        <!--start : notice list(notice-right)-->
        <!--게시글 목록에 있는 제목?-->
        <div class="notice-right">
            <jsp:include page="notice_list_div.jsp"/>
            <!--end : notice list (notice-right)-->
        </div>
    </div>
</div>
</body>
</html>
