// element

// 1. order-modal radio btn 전역 설정
const clickRow = document.querySelector(".order__modal_table"); // querySelector는 항상 1st만 인식하기 때문에 추가 조치 필요
// 2. order-modal 벗어나기
const closeOrderModal = document.querySelector(".order__modal_ok");
// 3. order__coupon
const orderCouponTitle = document.querySelector(".order__coupon");
const orderCouponPk = document.querySelector(".order__coupon_pk");
console.dir(orderCouponPk);

let COUPON_NAME;
let COUPON_PK;
// function

// 1. handleClickRow
function handleClickRow(event) {
    // 클릭된 행 찾기
    const clickedRow = event.target.closest(".order__modal_table_instance");
    // coupon 명 가져오기
    COUPON_NAME = clickedRow.querySelector(".order__coupon_name").innerHTML;
    // coupon pk 가져오기
    COUPON_PK = clickedRow.querySelector("td[hidden]").innerHTML;
    if (clickedRow) {
        const radio = clickedRow.querySelector('input[type="radio"]');
        radio.checked = true;
    }
}
//2. handleCloseOrderModal
function handleCloseOrderModal(event) {
    if (event.target === closeOrderModal) {
        modal.classList.toggle("show");
        if (!modal.classList.contains("show")) {
            body.style.overflow = "auto"; // body scroll 원상 복구
        }
        if (COUPON_NAME === undefined || COUPON_NAME === "") {
            console.log("not change");
            return;
        }
        // coupon 적용 이름 변경
        orderCouponTitle.innerHTML = COUPON_NAME;
        console.log("pk :" + COUPON_PK);
        orderCouponPk.innerHTML = COUPON_PK;
        COUPON_NAME = "";
        COUPON_PK = "";
    }
}

// event

// 1. radio btn
clickRow.addEventListener("click", handleClickRow);
// 2. modal close
closeOrderModal.addEventListener("click", handleCloseOrderModal);
