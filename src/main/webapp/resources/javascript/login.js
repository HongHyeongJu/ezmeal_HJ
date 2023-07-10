
/*로그인관련 script*/
const signUpBtn = document.getElementById("signUp");
const signInBtn = document.getElementById("signIn");
const container = document.querySelector(".container");

signUpBtn.addEventListener("click", () => {  /*로그인/회원가입 패널을 전환하는 효과를 구현*/
container.classList.add("right-panel-active");
});
signInBtn.addEventListener("click", () => {
container.classList.remove("right-panel-active");
});

/*모달관련 script*/
const openButton = document.querySelector("button");
const modal = document.querySelector(".modal");
const closeButton = modal.querySelector("button");
const modalBackground = modal.querySelector(".modal__background");

function displayModal(){    /*모달을 화면에 보이거나 숨기는 역할*/
  modal.classList.toggle("hidden");  
}

openButton.addEventListener("click", displayModal); /*누르면 모달 나타남 */
closeButton.addEventListener("click", displayModal)/*누르면 모달 사라짐 */
modalBackground.addEventListener("click", displayModal); /*모달 외부를 클릭하면 모달이 닫힘 */
