const body = document.querySelector('body');
const modal = document.querySelector(".prod_review_modal"); // modal html이 들어갈 공간
let BTN_CANCEL; // 버튼 취소

const handleRemoveModal = function (event) {
    console.log("haslfdjasldfkm")
    console.log(event.target);
    if (event.target === modal) {
        modal.classList.toggle("show");

        if (!modal.classList.contains("show")) { // class에 show가 없는 경우
            body.style.overflow = "auto"; // body scroll 원상 복구
        }
    }
}
// show 있는 경우 event - show가 있어서 현재 body가 .modal로 뒤덮힌 상황
modal.addEventListener("click", handleRemoveModal);

// 만약 x btn 넣고 싶은 경우 -  document에서 x btn 받아오고 hanleRemoveModal 반복하면 된다.
const handleCancel = function (event) {
    console.log(event.target.textContent);
    if (event.target.textContent === '취소') {
        modal.classList.toggle("show");

        if (!modal.classList.contains("show")) { // class에 show가 없는 경우
            body.style.overflow = "auto"; // body scroll 원상 복구
        }
    }
}
