const clickRow = document.querySelector(".order__modal_table"); // modal 내부 쿠폰 table
const closeOrderModal = document.querySelector(".order__modal_ok"); // modal 확인 버튼
const orderCouponTitle = document.querySelector(".order__coupon"); // 선택된 쿠폰 나오는 버튼
const orderCouponPk = document.querySelector(".order__coupon_pk"); // hidden으로 처리된 쿠폰 번호
const orderPoint = document.querySelector(".order__point"); // 사용할 적립금
const productSummary = document.querySelector(".order__prod_summary");
const orderBtn = document.querySelector(".order__price"); // 최종 주문 button

let COUPON_NAME; // 쿠폰 명
let COUPON_PK; // 쿠폰 pk
let EVENT_LIST = [];

/* Global Function */
// 카톡일 경우
let IMP = window.IMP;
IMP.init("imp67011510");

// 실제 결제 연동 api
const requestPay = function (orderMasterResult) {
    return new Promise((resolve, reject) => {
        const {paymentPk, name, finalPrice, buyerName, phone, email} = orderMasterResult;
        IMP.request_pay(
            {
                pg: "kakaopay",
                merchant_uid: paymentPk,
                name: name,
                amount: finalPrice,
                buyer_email: email,
                buyer_name: buyerName,
                buyer_tel: phone,
            },
            function (rsp) {
                // callback
                if (rsp.success) resolve(rsp);
                else reject(rsp);
            }
        );
    })
        .catch((fail) => {
            return fail;
        });
};

const getPaymentData = function (EVENT_LIST) {
    return fetch("/order/paymentData", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(EVENT_LIST)
    })
        .then(response => {
            if (response.ok) return response.json();
            else throw new Error('Error: ' + response.status);
        })
}

const orderPaymentAddressUpdate = function (data) {
    return fetch("/order", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (response.ok) return response.text();
            else throw new Error('Error: ' + response.status);
        })
}

/* 객체 */
class OrderPaymentData {
    constructor(eventList, prodSummaryName, paymentPk, paymentMethod) {
        this.eventList = eventList;
        this.prodSummaryName = prodSummaryName;
        this.paymentPk = paymentPk;
        this.paymentMethod = paymentMethod;
    }
}

/* Event Function */

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

function handleCloseOrderModal(event) {
    if (event.target === closeOrderModal) {
        modal.classList.toggle("show"); // 모달 적용

        if (!modal.classList.contains("show")) body.style.overflow = "auto"; // body scroll 원상 복구
        if (COUPON_NAME === undefined || COUPON_NAME === "") return;

        orderCouponTitle.innerHTML = COUPON_NAME; // modal 밖 coupon에 적용
        orderCouponPk.innerHTML = COUPON_PK; // modal 밖 hidden에 적용
    }
}


// 결제 버튼
async function order() {
    // todo 수행 - 1. 검증 - 현재 결제 금액으로부터 사용가능한 coupon인지 검증 필요 -> 쿠폰 사용가능 조건 금액, 쿠폰 최대 적용 금액 | 최대 적립금 확인
    //  쿠폰 적립금 검증
    //  적립금 검증 코드에 넣기
    //  int pointCanUse = pointTransactionHistoryDao.pointCanUse(memberId); // 사용가능 적립금
    //  if (event.get(0) < pointCanUse) point += event.get(0);
    //  else point += pointCanUse;
    //  쿠폰 검증 코드에 넣기
    //  현재 결제 금액으로부터 사용가능한 coupon인지 검증 필요
    try {
        EVENT_LIST.push(orderPoint.value);
        EVENT_LIST.push(orderCouponPk.textContent);
        const paymentData = await getPaymentData(EVENT_LIST); // 결제 api에 보낼 정보
        const productSummaryName = productSummary.textContent;
        paymentData.name = productSummaryName; // 결제 api에 보낼 추가 정보

        const paymentResultData = await requestPay(paymentData) // 결제 응답
        console.log(paymentResultData);
        if (!paymentResultData.success) return;
        // todo 결제 api 성공시 모든 관련 data insert
        /* 넘겨야할 data
        * 주문 master : event 배열, 상품명 summary
        * 결제 master : 결제pk, 결제수단
        * */
        const orderPaymentData = new OrderPaymentData(EVENT_LIST, productSummaryName, paymentData.paymentPk, paymentResultData.pg_provider)
        console.log('aaaa ')
        console.log( orderPaymentData);
        const success = await orderPaymentAddressUpdate(orderPaymentData);
        console.log(success);

    } catch (e) {
        console.log(e);
    } finally {
        EVENT_LIST = [];
    }
}

/* Document EVENT */

// 클릭된 쿠폰 정보 변수 저장 및 input check해주기
clickRow.addEventListener("click", handleClickRow);
// 확인 버튼 누르면 쿠폰 정보 화면에 넣은 후 닫기
closeOrderModal.addEventListener("click", handleCloseOrderModal);
orderBtn.addEventListener("click", order);