<%--
  Created by IntelliJ IDEA.
  User: hubaek
  Date: 2023/06/28
  Time: 10:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.net.URLDecoder"%>
<html>
<head>
  <title>회원가입</title>
  <link rel="stylesheet" href="/css/screens/signup.css">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<jsp:include page="header.jsp"/>
<section class="section">
  <c:if test="${not empty errorMsg}">
    <script>
      // 회원가입 란 중에 비어있는 칸 경고창
      const errorMessage = "${errorMsg}";
      alert(errorMessage);
    </script>
  </c:if>

  <div class="title">회원가입</div>
  <!-- 회원가입 타이틀 끝 -->

  <form action="<c:url value="/member/signup"/>" method="post" onsubmit="return validateForm()">
    <div id="msg" class="msg"></div>
    <div class="content-section">
      <div class="top">
        <span class="star">*</span>필수입력사항
      </div>
      <div class="content-main">
        <div class="form-row">
          <div class="label">
            <label for="lgin_id" class="label-value">아이디
              <span class="star">*</span>
            </label>
          </div>
          <div class="value">
            <div class="input">
              <input class="input-field" type="text" id="lgin_id" name="lgin_id" oninput="validateIdInput()" value="${memberDto.lgin_id}" placeholder="4~12자리의 영문 혹은 영문과 숫자 조합" autofocus>
            </div>
            <div class="msg"> <p id="id-msg" class="valid-msg"></p> </div>
          </div>
          <div class="button-section">
            <button class="button" type="button" onclick="checkIdDuplicate()">
              <span class="button-value">중복확인</span>
            </button>
          </div>
        </div>

        <div class="form-row">
          <div class="label">
            <label for="lgin_pw" class="label-value">비밀번호
              <span class="star">*</span>
            </label>
          </div>
          <div class="value">
            <div class="input">
              <input class="input-field" type="password" id="lgin_pw" name="lgin_pw" oninput="validatePwInput()" value="${memberDto.lgin_pw}" maxlength="16" placeholder="비밀번호를 입력해 주세요.">
            </div>
            <div class="msg"> <p id="pw-msg" class="valid-msg"></p> </div>
          </div>
          <div class="button-section"></div>
        </div>

        <div class="form-row">
          <div class="label">
            <label for="confirmPw" class="label-value">비밀번호확인
              <span class="star">*</span>
            </label>
          </div>
          <div class="value">
            <div class="input">
              <input class="input-field" type="password" id="confirmPw" name="confirmPw" oninput="validateConfirmPwInput()"  maxlength="16" placeholder="비밀번호를 한번 더 입력해 주세요">
            </div>
            <div class="msg"> <p id="confirmPw-msg" class="valid-msg"></p> </div>
          </div>
          <div class="button-section"></div>
        </div>

        <div class="form-row">
          <div class="label">
            <label for="name" class="label-value">이름
              <span class="star">*</span>
            </label>
          </div>
          <div class="value">
            <div class="input">
              <input class="input-field" type="text" id="name" name="name" value="${memberDto.name}" oninput="validateNameInput()" placeholder="이름을 입력해 주세요.">
            </div>
            <div class="msg"> <p id="name-msg" class="valid-msg"></p> </div>
          </div>
          <div class="button-section"></div>
        </div>

        <div class="form-row">
          <div class="label">
            <label for="email" class="label-value">이메일
              <span class="star">*</span>
            </label>
          </div>
          <div class="value">
            <div class="input">
              <input class="input-field" type="text" id="email" name="email" value="${memberDto.email}" oninput="validateEmailInput()" placeholder="예: ezmeal@meal.com">
            </div>
            <div class="msg"> <p id="email-msg" class="valid-msg"></p> </div>
          </div>
          <div class="button-section">
            <button class="button" id="emailButton" type="button" onclick="checkEmailDuplicate()">
              <span class="button-value">중복확인</span>
              <!-- 기존 로그인된 이메일이면 disable, 수정하면 중복확인버튼 on -->
            </button>
          </div>
        </div>

        <div class="form-row">
          <div class="label">
            <label for="phone" class="label-value">휴대폰
              <span class="star">*</span>
            </label>
          </div>
          <div class="value">
            <div class="input">
              <input class="input-field" type="tel" id="phone" name="phone" value="${memberDto.phone}" oninput="validatePhoneInput(event)" maxlength="11" placeholder="숫자만 입력해 주세요.">
            </div>
            <div class="msg"> <p id="phone-msg" class="valid-msg"></p> </div>
          </div>
          <div class="button-section">
            <button class="button" type="button">
              <span class="button-value">인증번호 받기</span>
            </button>
          </div>
        </div>

        <div class="form-row">
          <div class="label">
            <label class="gender" class="label-value">성별
              <span class="star">*</span>
            </label>
          </div>
          <div class="value">
            <div class="gender-section">
              <label class="gender-label" for="gender-man">
                <input data-testid="radio-MALE" id="m" name="gender" type="radio" class="gender-input" value="m" ${memberDto.gender == 'm' ? 'checked' : ''}/>
                <!-- <span class="gender-span">
                    <div class="span-mini"></div>
                </span> -->
                <span aria-labelledby="gender-man" class="gender-value">남자</span>
              </label>

              <label class="gender-label" for="gender-woman">
                <input data-testid="radio-FEMALE" id="f" name="gender" type="radio" class="gender-input" value="f" ${memberDto.gender == 'f' ? 'checked' : ''}/>
                <!-- <span class="gender-span">
                    <div class="span-mini"></div>
                </span> -->
                <span aria-labelledby="gender-woman" class="gender-value">여자</span>
              </label>
              <label class="gender-label" for="gender-none"></label>
            </div>
          </div>
          <div class="button-section"></div>
        </div>

        <div class="form-row">
          <div class="label">
            <label class="birth" class="label-value">생년월일
              <span class="star">*</span>
            </label>
          </div>
          <div class="value" height="40">
            <input class="input-field" height="40" type="text" id="birth" name="birth" value="${memberDto.birth}" oninput="validateBirthInput()" placeholder="숫자만 입력해 주세요(YYYY/MM/DD)">
            <div class="msg"> <p id="birth-msg" class="valid-msg"></p> </div>
          </div>
          <div class="button-section"></div>
        </div>
      </div>
      <!-- content-main의 끝 -->

      <div class="bottom-lane"></div>

      <div class="bottom">
        <div class="bottom-section">
          <div class="bottom-side">
            <label class="bottom-side-label">이용약관동의
              <span class="star">*</span>
            </label>
          </div>
          <!-- bottom-side의 끝 -->

          <div class="bottom-main">

            <div class="bottom-row">
              <label class="agreeAll-label">
                <input id="TermsAgreeAll" class="required-input" type="checkbox" >
                <!-- <div class="requiredTerms">
                    <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M23.5 12C23.5 18.3513 18.3513 23.5 12 23.5C5.64873 23.5 0.5 18.3513 0.5 12C0.5 5.64873 5.64873 0.5 12 0.5C18.3513 0.5 23.5 5.64873 23.5 12Z" stroke="#ddd" fill="#fff"></path><path d="M7 12.6667L10.3846 16L18 8.5" stroke="#ddd" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path></svg>
                </div> -->
                <span>전체 동의합니다.</span>
              </label>
              <p class="css-nygcgj e1sjmfnv6">선택항목에 동의하지 않은 경우도 회원가입 및 일반적인 서비스를 이용할 수 있습니다.</p>
            </div>

            <div class="bottom-row">
              <div class="required">
                <label class="required-label">
                  <input id="RequiredTermsCondition" type="checkbox" class="required-input">
                  <!-- <div class="requiredTerms">
                      <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M23.5 12C23.5 18.3513 18.3513 23.5 12 23.5C5.64873 23.5 0.5 18.3513 0.5 12C0.5 5.64873 5.64873 0.5 12 0.5C18.3513 0.5 23.5 5.64873 23.5 12Z" stroke="#ddd" fill="#fff"></path><path d="M7 12.6667L10.3846 16L18 8.5" stroke="#ddd" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path></svg>
                  </div> -->
                  <span>이용약관 동의</span>
                </label>
                <span class="bottom-span">(필수)</span>
              </div>
              <!-- <a class="css-7chi73 e1sjmfnv3">약관보기</a> -->
            </div>

            <div class="bottom-row">
              <div class="required">
                <label class="required-label">
                  <input type="checkbox" class="required-input">
                  <!-- <div class="requiredTerms">
                      <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M23.5 12C23.5 18.3513 18.3513 23.5 12 23.5C5.64873 23.5 0.5 18.3513 0.5 12C0.5 5.64873 5.64873 0.5 12 0.5C18.3513 0.5 23.5 5.64873 23.5 12Z" stroke="#ddd" fill="#fff"></path><path d="M7 12.6667L10.3846 16L18 8.5" stroke="#ddd" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path></svg>
                  </div> -->
                  <span>개인정보 수집∙이용 동의</span>
                </label>
                <span class="bottom-span">(필수)</span>
              </div>
              <!-- <a class="css-7chi73 e1sjmfnv3">약관보기</a> -->
            </div>

            <div class="bottom-row">
              <div class="required">
                <label class="required-label">
                  <input type="checkbox" class="required-input">
                  <!-- <div class="requiredTerms">
                      <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M23.5 12C23.5 18.3513 18.3513 23.5 12 23.5C5.64873 23.5 0.5 18.3513 0.5 12C0.5 5.64873 5.64873 0.5 12 0.5C18.3513 0.5 23.5 5.64873 23.5 12Z" stroke="#ddd" fill="#fff"></path><path d="M7 12.6667L10.3846 16L18 8.5" stroke="#ddd" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path></svg>
                  </div> -->
                  <span>마케팅 및 광고 수신 동의</span>
                </label>
                <span class="bottom-span">(선택)</span>
              </div>
              <!-- <a class="css-7chi73 e1sjmfnv3">약관보기</a> -->
            </div>

            <div class="bottom-row">
              <div class="required">
                <label class="required-label">
                  <input type="checkbox" class="required-input">
                  <!-- <div class="requiredTerms">
                      <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M23.5 12C23.5 18.3513 18.3513 23.5 12 23.5C5.64873 23.5 0.5 18.3513 0.5 12C0.5 5.64873 5.64873 0.5 12 0.5C18.3513 0.5 23.5 5.64873 23.5 12Z" stroke="#ddd" fill="#fff"></path><path d="M7 12.6667L10.3846 16L18 8.5" stroke="#ddd" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path></svg>
                  </div> -->
                  <span>이벤트 및 할인 혜택 안내 동의</span>
                </label>
                <span class="bottom-span">(선택)</span>
              </div>
              <!-- <a class="css-7chi73 e1sjmfnv3">약관보기</a> -->
            </div>

            <div class="bottom-row">
              <div class="required">
                <label class="required-label">
                  <input type="checkbox" class="required-input">
                  <!-- <div class="requiredTerms">
                      <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M23.5 12C23.5 18.3513 18.3513 23.5 12 23.5C5.64873 23.5 0.5 18.3513 0.5 12C0.5 5.64873 5.64873 0.5 12 0.5C18.3513 0.5 23.5 5.64873 23.5 12Z" stroke="#ddd" fill="#fff"></path><path d="M7 12.6667L10.3846 16L18 8.5" stroke="#ddd" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path></svg>
                  </div> -->
                  <span>본인은 만 14세 이상입니다.</span>
                </label>
                <span class="bottom-span">(필수)</span>
              </div>
              <!-- <a class="css-7chi73 e1sjmfnv3">약관보기</a> -->
            </div>

          </div>
          <!-- bottom-main의 끝 -->
        </div>
        <!-- bottom-section의 끝 -->
      </div>
      <!-- bottom의 끝 -->
      <div class="signup-button-section">
        <button class="signup-button" type="submit" width="240" height="56" radius="3">
          <span class="signup-value">가입하기</span>
        </button>
      </div>

    </div>
    <!-- content-section의 끝 -->
  </form>
</section>

<script>
  const msg = "${msg}";
</script>
<script src="/javascript/signup.js"></script>
<%--<jsp:include page="footer.jsp"/>--%>
</body>
</html>
