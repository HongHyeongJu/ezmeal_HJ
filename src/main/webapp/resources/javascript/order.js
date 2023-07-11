const clickRow = document.querySelector(".order__modal_table"); // modal 내부 쿠폰 table
const closeOrderModal = document.querySelector(".order__modal_ok"); // modal 확인 버튼
const orderCouponTitle = document.querySelector(".order__coupon"); // 선택된 쿠폰 나오는 버튼
const orderCouponPk = document.querySelector(".order__coupon_pk"); // hidden으로 처리된 쿠폰 번호

let COUPON_NAME; // 쿠폰 명
let COUPON_PK; // 쿠폰 pk
// function

function handleClickRow(event) {
    // 클릭된 행 찾기
    const clickedRow = event.target.closest(".order__modal_table_instance");
    COUPON_NAME = clickedRow.querySelector(".order__coupon_name").innerHTML; // coupon 명 변수에 저장
    COUPON_PK = clickedRow.querySelector("td[hidden]").innerHTML; // coupon pk 변수에 저장
    // table row 누르면 radio btn click 되게 하기
    if (clickedRow) {
        const radio = clickedRow.querySelector('input[type="radio"]');
        radio.checked = true;
    }
}
//2. handleCloseOrderModal
function handleCloseOrderModal(event) {
    if (event.target === closeOrderModal) {
        modal.classList.toggle("show"); // 모달 적용

        if (!modal.classList.contains("show")) body.style.overflow = "auto"; // body scroll 원상 복구
        if (COUPON_NAME === undefined || COUPON_NAME === "") return;

        orderCouponTitle.innerHTML = COUPON_NAME; // modal 밖 coupon에 적용
        orderCouponPk.innerHTML = COUPON_PK; // modal 밖 hidden에 적용
    }
}

/* Document EVENT */

// 클릭된 쿠폰 정보 변수 저장 및 input check해주기
clickRow.addEventListener("click", handleClickRow);
// 확인 버튼 누르면 쿠폰 정보 화면에 넣은 후 닫기
closeOrderModal.addEventListener("click", handleCloseOrderModal);
