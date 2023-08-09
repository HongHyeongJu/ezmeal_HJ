// const body = document.querySelector("body");
const modalCpn = document.querySelector(".modal");
const btnOpenPopupCpn = document.querySelector(".order__coupon.btn-open-popup");

function handleShowModal() {
    modalCpn.classList.toggle("show");

    if (modalCpn.classList.contains("show")) {
        body.style.overflow = "hidden"; // body scroll 막는 code
    }
}

function handleRemoveModal(event) {
    if (event.target === modalCpn) {
        modalCpn.classList.toggle("show");

        if (!modalCpn.classList.contains("show")) {
            body.style.overflow = "auto"; // body scroll 원상 복구
        }
    }
}
// show가 없는 경우 event
btnOpenPopupCpn.addEventListener("click", handleShowModal);

// show 있는 경우 event - show가 있어서 현재 body가 .modal로 뒤덮힌 상황
modalCpn.addEventListener("click", handleRemoveModal);

// 만약 x btn 넣고 싶은 경우 -  document에서 x btn 받아오고 hanleRemoveModal 반복하면 된다.
