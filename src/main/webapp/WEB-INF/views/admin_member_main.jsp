<%--
  Created by IntelliJ IDEA.
  User: hubaek
  Date: 2023/08/07
  Time: 2:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/screens/admin_member_main.css">
</head>
<body>
<%@ include file="admin_header.jsp" %>

<div class="all_container_div">

    <%@ include file="admin_menu.jsp" %>

    <div class="admin_container_div">

        <div id="content">
            <div class="headingArea">
                <div class="mTitle">
                    <h1>회원관리</h1>
                </div>
            </div>

            <div class="dashMain">
                <div class="mDashPannel">
                    <div class="header">
                        <div class="title">
                            <h2>회원현황</h2>
                        </div>
                        <a href="/admin/member/info" class="more">조회</a>
                    </div>
                    <div class="content">
                        <table border="1" summary>
                            <colgroup>
                                <col style="width: 25%;">
                                <col style="width: 25%;">
                                <col style="width: 25%;">
                                <col style="width: 25%;">
                            </colgroup>

                            <thead>
                            <tr>
                                <th scope="col">
                                    신규회원
                                </th>
                                <th scope="col">
                                    방문회원
                                </th>
                                <th scope="col">
                                    탈퇴회원
                                </th>
                                <th scope="col">
                                    TOTAL
                                </th>
                            </tr>
                            </thead>

                            <tbody class="right">
                            <tr>
                                <td>
                                    <strong class="underline txtEm">
                                        1
                                    </strong>
                                    명
                                </td>
                                <td>
                                    <strong class="underline txtEm">
                                        1
                                    </strong>
                                    명
                                </td>
                                <td>
                                    <strong class="underline txtEm">
                                        0
                                    </strong>
                                    명
                                </td>
                                <td>
                                    <strong class="underline txtEm">
                                        41
                                    </strong>
                                    명
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="mTitle">
                    <h2>최근 가입 회원</h2>
                </div>

                <div class="mBoard">
                    <table border="1" summary>
                        <colgroup>
                            <col style="width: 184px;">
                            <col style="width: 154px;">
                            <col style="width: 154px;">
                            <col style="width: auto;">
                            <col style="width: 154px;">
                        </colgroup>
                        <thead>
                        <tr>
                            <th scope="col">가입일시</th>
                            <th scope="col">아이디</th>
                            <th scope="col">이름</th>
                            <th scope="col">메모</th>
                            <th scope="col">등급</th>
                        </tr>
                        </thead>
                        <tbody class="center">
                        <tr>
                            <td>2023-08-08</td>
                            <td>ezmeal123</td>
                            <td>이**</td>
                            <td>-</td>
                            <td>신규사과</td>
                        </tr>
                        <tr>
                            <td>2023-07-30</td>
                            <td>park123</td>
                            <td>박**</td>
                            <td>-</td>
                            <td>신규사과</td>
                        </tr>
                        <tr>
                            <td>2023-07-23</td>
                            <td>zjfl3122</td>
                            <td>백**</td>
                            <td>-</td>
                            <td>신규사과</td>
                        </tr>
                        </tbody>
                    </table>
                </div>

            </div>

        </div>



    </div>
</div>

</body>
</html>
