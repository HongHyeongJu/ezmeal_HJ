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

    <link rel="stylesheet" href="/css/screens/notice_side.css">

</head>
<body>

<%--<%@ include file="header.jsp" %>--%>

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
            <div class="notice-content-title-box">
                <div class="notice-content-title">
                    <h2 class="notice-title">공지사항</h2>
                    <span class="notice-title-explain"
                    >easymeal의 새로운 소식들과 유용한 정보들을 한곳에서
                확인하세요.</span
                    >
                </div>
            </div>

            <!--게시글 목록-->
            <div class="notice-table">
                <table>
                    <colgroup>
                        <col style="width: 60px" />
                        <col style="width: 530px" />
                        <col style="width: 80px" />
                        <col style="width: 100px" />
                    </colgroup>
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>작성일</th>
                    </tr>
                    <!-- view로 올때 모델에 담아준 노티스리스트를 반복해서 출력해주는역할-->
                    <!--foreach은 for문이라고 생각-->
                    <c:forEach var="notice" items="${noticeList}">
                        <tr>
                            <td class="notice_td">${notice.notice_no}</td>
                            <!--공지사항 글번호-->
                            <td class="notice_td">
                                <a href="/ch4/noticestmt?notice_no=${notice.notice_no}"
                                >[${notice.typ}] ${notice.title}</a
                                >
                            </td>
                            <!-- 공지사항의 타입, 제목-->
                            <td class="notice_td">${notice.writer}</td>
                            <!--공지사항 작성자-->
                            <td class="notice_td">${notice.wrt_dt_format}</td>
                            <!--공지사항 작성일-->
                        </tr>
                    </c:forEach>
                </table>
            </div>


            <!--페이징 버튼 pre, next-->
            <div class="paging-box">
                <div class="paging">
                    <button disabled="" type="button" class="paging-button">
                        <div class="css-7qb0sc e1ilyb3p0">
                            <svg xmlns="http://www.w3.org/2000/svg" width="44" height="44" viewBox="0 0 44 44">
                                <g fill="none" fill-rule="evenodd">
                                    <g>
                                        <g transform="translate(-266.000000, -1622.000000) translate(266.000000, 1622.000000)">
                                            <rect width="43" height="43" x=".5" y=".5" fill="#FFF" stroke="#DDD" stroke-opacity=".5" rx="3"/>
                                            <g>
                                                <path d="M0 0H24V44H0z" transform="translate(10.000000, 0.000000)"/>
                                                <path fill="#EEE" d="M15.146 13.646c.196-.195.512-.195.708 0 .173.174.192.443.057.638l-.057.07L8.207 22l7.647 7.646c.173.174.192.443.057.638l-.057.07c-.174.173-.443.192-.638.057l-.07-.057-8-8c-.173-.174-.192-.443-.057-.638l.057-.07 8-8z" transform="translate(10.000000, 0.000000)"/>
                                            </g>
                                        </g>
                                    </g>
                                </g>
                            </svg>
                        </div>
                    </button>
                    <div>
                        <c:if test="${ph.showPrev}">
                            <a href="/notice?page=${ph.beginPage - 1}&pageSize=${ph.pageSize}">&lt;</a>
                        </c:if>
                        <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                            <a href="/notice?page=${i}&pageSize=${ph.pageSize}">${i}</a>
                        </c:forEach>
                        <c:if test="${ph.showNext}">
                            <a href="/notice?page=${ph.endPage + 1}&pageSize=${ph.pageSize}">&gt;</a>
                        </c:if>
                    </div>
                    <button type="button" class="paging-button">
                        <div class="css-7qb0sc e1ilyb3p0">
                            <svg xmlns="http://www.w3.org/2000/svg" width="44" height="44" viewBox="0 0 44 44">
                                <g fill="none" fill-rule="evenodd">
                                    <g>
                                        <g>
                                            <g transform="translate(-504.000000, -1622.000000) translate(448.000000, 1622.000000) translate(56.000000, 0.000000)">
                                                <rect width="43" height="43" x=".5" y=".5" fill="#FFF" stroke="#DDD" stroke-opacity=".5" rx="3"/>
                                                <g>
                                                    <path d="M0 0H24V44H0z" transform="translate(10.000000, 0.000000)"/>
                                                    <path fill="#333" d="M15.146 13.646c.196-.195.512-.195.708 0 .173.174.192.443.057.638l-.057.07L8.207 22l7.647 7.646c.173.174.192.443.057.638l-.057.07c-.174.173-.443.192-.638.057l-.07-.057-8-8c-.173-.174-.192-.443-.057-.638l.057-.07 8-8z" transform="translate(10.000000, 0.000000) translate(11.500000, 22.000000) scale(-1, 1) translate(-11.500000, -22.000000)"/>
                                                </g>
                                            </g>
                                        </g>
                                    </g>
                                </g>
                            </svg>
                        </div>
                    </button>
                </div>
            </div>
            <!--end : notice list (notice-right)-->
        </div>
    </div>
</div>
</body>
</html>
