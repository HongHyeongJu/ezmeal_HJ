// 현재 비밀번호가 틀렸을때, 경고창
if (msg !== ""){
    alert(msg);
}

function confirmSubmit() {
    // confirm 대화상자를 띄워 사용자로부터 확인 또는 취소를 받음
    var result = confirm("회원정보를 수정하시겠습니까??");

    if (result) {
        // 확인을 선택한 경우
        console.log("회원정보 요청 진행");
        return true; // 폼 제출 진행
    } else {
        // 취소를 선택한 경우
        return false; // 폼 제출 취소
    }
}

function checkEmailDuplicate() {
    const emailInput = $("#email");
    const email = emailInput.val();
    const regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/; // 이메일 정규식
    let button = $("#emailButton");

    if (loginEmail === email) {
        button.disabled = true;
    }

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

function validateEmail() {
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