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
    <link rel="stylesheet" href="/css/screens/admin_board_notice.css">
</head>
<body>
    <!-- 관리자 페이지 헤더 -->
    <%@ include file="admin_header.jsp" %>  <!-- 관리자 헤더 인클루드 -->

    <!-- 헤더 아래부분 전체 div
    [ 메뉴 div /  관리자 담당 페이지 div  ] -->
    <div class="all_container_div">

        <%@ include file="admin_menu.jsp" %> <!-- 관리자 메뉴 인클루드 -->

        <div class="admin_container_div">
            <div class="all">
                <div class="admin_graph">
                    <table border="1" summary="">
                        <h2>
                            자료실 게시판 용량 현황
                        </h2>
                        <colgroup>
                            <col style="width: auto" />
                            <col style="width: 33%" />
                            <col style="width: 33%" />
                        </colgroup>
                        <thead>
                        <tr>
                            <th scope="col">총 용량</th>
                            <th scope="col">사용중</th>
                            <th scope="col">사용가능</th>
                        </tr>
                        </thead>
                        <tbody class="right">
                        <tr>
                            <td><strong class="underline txtEm">100</strong> GB</td>
                            <td><strong class="underline txtEm">32</strong> GB</td>
                            <td><strong class="underline txtWarn">100</strong> GB</td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class="admin_graph">
                    <div class="chart_div">
                        <h3 class="chart_name">공지사항 게시판</h3>
                        <h4 class="chart_sub_name chart_sub_name_order">2023년 8월 글 숨김 여부</h4>
                        <div id="chart_board1"></div>
                    </div>
                </div>
                <div class="admin_graph">
                    <div class="chart_div">
                        <h3 class="chart_name">1:1 문의 게시판</h3>
                        <h4 class="chart_sub_name chart_sub_name_order">2023년 8월 답변 대기 </h4>
                        <div id="chart_board2"></div>
                    </div>
                </div>

                <div class="admin_graph">
                    <div class="chart_div">
                        <h3 class="chart_name">상품 후기 게시판</h3>
                        <h4 class="chart_sub_name chart_sub_name_order">베스트리뷰 선정 </h4>
                        <div id="chart_board3"></div>
                    </div>
                </div>
            </div>
        </div>
            <!-- admin_container_div 내용.
            각자 맡은 관리자 페이지 내용 이 디브 안에 쓰면 됩니다.  -->
        </div>
            <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

            <script src="/javascript/admin_board_notice.js"></script>
</body>
</html>


