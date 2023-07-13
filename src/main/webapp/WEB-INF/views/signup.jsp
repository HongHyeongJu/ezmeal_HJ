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

<section class="section">
  <%--회원가입란 중에 비어있는 칸에 해당하는 메시지를 띄워줌--%>
  <c:if test="${not empty errorMsg}">
    <script>
      var errorMessage = "${errorMsg}";
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

<%--<script src="/javascript/signup.js"></script>--%>

<script>
  // 회원가입이 정상적으로 이루어지지 않았을 때 경고문
  var msg = "${msg}";
  if (msg !== "") {
    alert(msg);
  }

  // 아이디 중복체크
  function checkIdDuplicate() {
    const idInput = $("#lgin_id"); // id입력란의 lgin_id 'id'을 받아온다
    // var idResultMsg = $("#id-result-msg"); // id-result-msg의 'id'를 반환

    const id = idInput.val(); // id입력란의 값을 저장

    // 정규식(ID조건)에 부합하는지 체크
    const regex = /^(?=.*[a-zA-Z])[a-zA-Z0-9]{4,16}$/;
    if (!regex.test(id)) {
      alert("4자 이상 16자 이하의 영문 혹은 영문과 숫자 조합");
      return; // 조건에 맞지 않으면 함수 실행 중단
    }

    // AJAX 요청을 생성하여 중복 체크 요청을 서버에 보냄
    $.ajax({
      type: "POST",
      url: "/member/checkIdDuplicate",
      contentType: "application/json",
      data: JSON.stringify({ "id": id }),
      success: function(response) {
        var isDuplicate = response.isDuplicate;

        if (isDuplicate) {
          alert("이미 사용 중인 ID입니다.");
          idInput.removeClass("invalid");
        } else {
          // idResultMsg.html("사용 가능한 ID입니다.");
          alert("사용 가능한 ID입니다.");
          idInput.addClass("invalid");
        }
      }
    });
  }

  function checkEmailDuplicate() {
    const emailInput = $("#email");
    const email = emailInput.val();
    const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/; // 이메일 정규식
    let button = $("#emailButton");

    // 중복확인전에 이메일 형식으로 썼는지 검증하고, 조건에 안맞으면 경고창
    if (email === "") {
      alert("이메일을 입력해 주세요.");
      return;
    } else if (!regex.test(email)) {
      alert("이메일 형식으로 입력해 주세요.");
      return;
    }

    $.ajax({
      type: "POST",
      url: "/member/checkEmailDuplicate",
      contentType: "application/json",
      data: JSON.stringify({"email" : email}),
      success : function (response) {
        const emailCheck = response.emailCheck;
        if (emailCheck) {
          alert("사용 불가능한 이메일 입니다.")
          emailInput.removeClass("invalid");
        } else {
          alert("사용 가능한 이메일 입니다.")
          // button.prop("disabled", true);
          emailInput.addClass("invalid")
        }
      }
    });
  }

  // 회원가입을 요청했을때 ID 중복체크를 했는지 확인하는 함수
  function validateForm() {
    let idInput = $("#lgin_id");
    let emailInput = $("#email");

    // ID 중복확인을 하지 않았을 경우, email 중복확인을 하지 않았을 경우
    // if (idResultMsg.html() !== "사용 가능한 ID입니다.") {
    if (!(idInput.hasClass("invalid"))) {
      alert("아이디 중복확인을 해야 합니다.");
      return false; // 회원가입 요청을 중단
    } else if (!(emailInput.hasClass("invalid"))) {
      alert("이메일 중복확인을 해야 합니다.");
      return false;
    }
  }

  // 아이디 입력란 실시간 체크해서, 경고문 출력
  function validateIdInput() {
    const idInput = $("#lgin_id");
    const idMsg = $("#id-msg");

    const id = idInput.val();
    // var regex = /^[a-zA-Z0-9]{4,16}$/; // 단순 4자리이상
    // var regex = /^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{4,16}$/; // 알파벳과 숫자 모두 포함되어야함
    const idRegex = /^(?=.*[a-zA-Z])[a-zA-Z0-9]{4,16}$/;  // 정규식 알파벳 또는 알파벳+숫자

    if (!idRegex.test(id)) {
      idMsg.text("4자 이상 16자 이하의 영문 혹은 영문과 숫자 조합");
    } else {
      idMsg.text("");
    }
  }

  // 비밀번호 입력란 실시간체크, 조건에 맞지 않으면 경고문 출력
  function validatePwInput() {
    const pwInput = $("#lgin_pw");
    const pwMsg = $("#pw-msg");

    const pw = pwInput.val();
    const pwRegex1 = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{10,16}$/; //알파벳+숫자+특수문자
    const pwRegex2 = /^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{10,16}$/; // 알파벳+숫자
    const pwRegex3 = /^(?=.*[a-zA-Z])(?=.*[!@#$%^&*])[a-zA-Z!@#$%^&*]{10,16}$/;  // 알파벳+특수문자
    const pwRegex4 = /^(?=.*[0-9])(?=.*[!@#$%^&*])[0-9!@#$%^&*]{10,16}$/; // 숫자+특수문자

    if (!(pwRegex1.test(pw) || pwRegex2.test(pw) || pwRegex3.test(pw) || pwRegex4.test(pw))) {
      pwMsg.text("10자 이상 영문/숫자/특수문자, 2개이상 조합");
    } else {
      pwMsg.text("");
    }
  }

  // 비밀번호확인 입력란 실시간 체크
  function validateConfirmPwInput() {
    const pwInput = $("#lgin_pw");
    const confirmPwInput = $("#confirmPw");
    const confirmMsg = $("#confirmPw-msg"); // 실시간 전달 메시지

    const pw = pwInput.val();
    const confirmPw = confirmPwInput.val();
    if (pw !== confirmPw) {
      confirmMsg.text("동일한 비밀번호를 입력");
    } else {
      confirmMsg.text("");
    }
  }

  // 이름입력란이 빈칸이면 경고문 출력
  function validateNameInput() {
    const nameInput = $("#name");
    const nameMsg = $("#name-msg");

    const name = nameInput.val();

    if (name === "") {
      nameMsg.text("이름을 입력해 주세요.");
    } else {
      nameMsg.text("");
    }
  }

  // 휴대폰입력란이 빈칸이면 msg, 숫자만 적을 수 있게 조건
  function validatePhoneInput(event) {
    const phoneInput = $("#phone");
    const phoneMsg = $("#phone-msg");
    const phone = phoneInput.val();

    const eventInput = event.target;
    let eventValue = eventInput.value;
    eventValue = eventValue.replace(/\D/g, '');
    eventInput.value = eventValue;

    if (phone === "") {
      phoneMsg.text("휴대폰 번호를 입력해 주세요.");
    } else {
      phoneMsg.text("");
    }
  }

  // 이메일입력란이 빈칸, 이메일형식이 아니면 경고문 출력
  function validateEmailInput() {
    const emailInput = $("#email");
    const emailMsg = $("#email-msg");

    const email = emailInput.val();
    const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    if (email === "") {
      emailMsg.text("이메일을 입력해 주세요.");
    } else if (!regex.test(email)) {
      emailMsg.text("이메일 형식으로 입력해 주세요.");
    } else {
      emailMsg.text("");
    }
  }

  // 생일입력란이 빈칸, YYYYMMDD형식 안적으면 경고문 출력
  function validateBirthInput() {
    const birthInput = $("#birth");
    const birthMsg = $("#birth-msg");

    const birth = birthInput.val();
    let birth2 = birthInput.val().replace(/\D/g, "");

    const regex = /^\d{4}\/\d{2}\/\d{2}$/;

    if (birth === "") {
      birthMsg.text("생년월일을 입력해 주세요.");
    } else if (!regex.test(birth)) {
      birthMsg.text("유효한 생년월일 형식으로 입력해 주세요.(YYYY/MM/DD)");
    } else {
      birthMsg.text("");
    }
    // 생년월일 숫자 입력시 자동으로 YYYY/MM/DD 구분해주는 '/'을 넣어주는 식
    if (birth2.length > 4) {
      birth2 = birth2.replace(/(\d{4})(\d{2})(\d{0,2})/, "$1/$2/$3");
    } else if (birth2.length > 2) {
      birth2 = birth2.replace(/(\d{4})(\d{0,2})/, "$1/$2");
    }
    birthInput.val(birth2);
  }

  // 전체 동의 체크했을때, 모든 이용약관 체크되는 기능
  $(document).ready(function() {
    $("#TermsAgreeAll").change(function() {
      if ($(this).is(":checked")) {
        $(".required-input").prop("checked", true);
      } else {
        $(".required-input").prop("checked", false);
      }
    });
  });

</script>
</body>
</html>
