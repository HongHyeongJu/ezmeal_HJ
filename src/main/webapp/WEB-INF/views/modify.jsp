<%--
  Created by IntelliJ IDEA.
  User: hubaek
  Date: 2023/07/02
  Time: 2:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/screens/modify.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<jsp:include page="mypageHeader.jsp"/>

<div class="main-section">

    <jsp:include page="mypageLeft.jsp"/>

    <%--  개인정보수정 시작  --%>
    <div class="main-container">
        <div class="title-section">
            <div class="title">
                <h2 class="title-value">개인 정보 수정</h2>
            </div>
        </div>
        <!-- 개인정보수정 타이틀 끝 -->

        <div class="form-section">
            <form action="/mypage/modify" method="post" onsubmit="return confirmSubmit()">
                <div class="form-row">
                    <div class="label">
                        <label for="lgin_id" class="label-value">아이디</label>
                    </div>
                    <div class="value">
                        <input class="input-field" type="text" id="lgin_id" name="lgin_id" value="<c:out value="${loginMbrInfo.lgin_id}"/>" readonly />
                    </div>
                    <div class="button-section"></div>
                </div>

                <div class="form-row">
                    <div class="label">
                        <label for="origin_pw" class="label-value">현재 비밀번호</label>
                    </div>
                    <div class="value">
                        <input class="input-field" type="password" id="lgin_pw" name="origin_pw" placeholder="비밀번호를 입력해 주세요" autofocus >
                    </div>
                    <div class="button-section"></div>
                </div>

                <div class="form-row">
                    <div class="label">
                        <label for="lgin_pw" class="label-value">새 비밀번호</label>
                    </div>
                    <div class="value">
                        <input class="input-field" type="password" id="new_pw" name="lgin_pw"  placeholder="새 비밀번호를 입력해 주세요">
                    </div>
                    <div class="button-section"></div>
                </div>

                <div class="form-row">
                    <div class="label">
                        <label for="name" class="label-value">이름</label>
                    </div>
                    <div class="value">
                        <input class="input-field" type="text" id="name" name="name" value="<c:out value="${loginMbrInfo.name}"/>"  placeholder="이름을 입력해 주세요">
                    </div>
                    <div class="button-section"></div>
                </div>

                <div class="form-row">
                    <div class="label">
                        <label for="email" class="label-value">이메일</label>
                    </div>
                    <div class="value">
                        <input class="input-field" type="text" id="email" name="email" value="<c:out value="${loginMbrInfo.email}"/>" placeholder="이메일을 입력해 주세요">
                    </div>
                    <div class="button-section">
                        <button class="buttons" type="button">
                            <span class="button-value">중복확인</span>
                            <!-- 기존 로그인된 이메일이면 disable, 수정하면 중복확인버튼 on -->
                        </button>
                    </div>
                </div>

                <div class="form-row">
                    <div class="label">
                        <label for="phone" class="label-value">휴대폰</label>
                    </div>
                    <div class="value">
                        <input class="input-field" type="text" id="phone" name="phone" value="<c:out value="${loginMbrInfo.phone}"/>" placeholder="휴대폰 번호를 입력해 주세요">
                    </div>
                    <div class="button-section">
                        <button class="buttons" type="button">
                            <span class="button-value">다른번호 인증</span>
                            <!-- 기본값 readonly 다른번호인증 클릭하면 휴대폰 번호 입력란 초기화 - 인증받기 버튼으로 변경   -->
                        </button>
                    </div>
                </div>

                <div class="form-row">
                    <div class="label">
                        <label class="gender" class="label-value">성별</label>
                    </div>
                    <div class="value">
                        <div class="gender-section">
                            <label class="gender-label" for="gender-man">
                                <input data-testid="radio-MALE" id="gender-man" name="gender" type="radio" class="gender-input" value="m">
                                <%--                                <span class="gender-span"><div class="span-mini"></div></span>--%>
                                <span aria-labelledby="gender-man" class="gender-value">남자</span>
                            </label>

                            <label class="gender-label" for="gender-woman">
                                <input data-testid="radio-FEMALE" id="gender-woman" name="gender" type="radio" class="gender-input" value="f">
                                <%--                                <span class="gender-span"><div class="span-mini"></div></span>--%>
                                <span aria-labelledby="gender-woman" class="gender-value">여자</span>
                            </label>
                            <label class="gender-label" for="gender-none"></label>
                        </div>
                    </div>
                    <div class="button-section"></div>
                </div>

                <div class="form-row">
                    <div class="label">
                        <label class="birth" class="label-value">생년월일</label>
                    </div>
                    <div class="value" height="40">
                        <input class="input-field" height="40" type="text" id="birth" name="birth" value="<c:out value="${loginMbrInfo.birth}"/>" placeholder="생년월일(YYYY/MM/DD)을 입력해 주세요 ">
                    </div>
                    <div class="button-section"></div>
                </div>

                <div class="button-container">
                    <button class="buttons" type="button" onclick="window.location.href = '/mypage/withdrawal';">
                        <span class="button-value">탈퇴하기</span>
                    </button>
                    <button class="button2" type="submit">
                        <span class="button-value">회원정보수정</span>
                    </button>
                </div>
            </form>
        </div>
        <!-- form태그 끝 -->
    </div>
    <%--개인정보수정 끝--%>
</div>

</body>
<script>
    function confirmSubmit() {
        // confirm 대화상자를 띄워 사용자로부터 확인 또는 취소를 받음
        var result = confirm("회원정보를 수정하시겠습니까??");

        if (result) {
            // 확인을 선택한 경우
            alert("수정되었습니다.");
            return true; // 폼 제출 진행
        } else {
            // 취소를 선택한 경우
            return false; // 폼 제출 취소
        }
    }
</script>
</html>
