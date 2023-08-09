const signUpBtn = document.getElementById("signUp"); // 회원가입 버튼 - 화면이동
const signInBtn = document.getElementById("signIn"); // 로그인 버튼 - 화면이동
const container = document.querySelector(".container");


signUpBtn.addEventListener("click", () => {  /*로그인/회원가입 패널을 전환하는 효과를 구현*/
  container.classList.add("right-panel-active");
});
signInBtn.addEventListener("click", () => {
  container.classList.remove("right-panel-active");
});


/*모달관련 script*/

// 모달 관련 변수
const body = document.querySelector("body"); // modal로 적용될 body
const modal = document.querySelector(".wrapper");  // 로그인 모달 전체
const btnOpenPopup = document.querySelector(".login_svg"); // modal이 열리는 btn


function handleShowModal() {
  modal.classList.toggle("show");

  if (modal.classList.contains("show")) {
    body.style.overflow = "hidden"; // body scroll 막는 code
  }
}

function handleRemoveModal(event) {
  if (event.target === modal) {
    modal.classList.toggle("show");

    if (!modal.classList.contains("show")) {
      body.style.overflow = "auto"; // body scroll 원상 복구
    }
  }
}
// show가 없는 경우 event
if(btnOpenPopup != null)
btnOpenPopup.addEventListener("click", handleShowModal);

// show 있는 경우 event - show가 있어서 현재 body가 .modal로 뒤덮힌 상황
modal.addEventListener("click", handleRemoveModal);