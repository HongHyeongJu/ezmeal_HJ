const signUpBtn = document.getElementById("signUp");
const signInBtn = document.getElementById("signIn");
const container = document.querySelector(".container");


signUpBtn.addEventListener("click", () => {  /*로그인/회원가입 패널을 전환하는 효과를 구현*/
  container.classList.add("right-panel-active");
});
signInBtn.addEventListener("click", () => {
  container.classList.remove("right-panel-active");
});