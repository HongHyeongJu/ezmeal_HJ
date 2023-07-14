// 회원가입이 정상적으로 이루어지지 않았을 때 경고문

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