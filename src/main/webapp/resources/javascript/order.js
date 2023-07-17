const orderProdSummary = document.querySelector(".order__prod_summary"); // 주문 상품 요약 요소
const orderProdItemList = document.querySelectorAll(".order__item_list"); // 주문 상품 전체 리스트 요소
const orderProdOpenClose = document.querySelector(".order__items_list__btn > i"); // 주문상품 토글 요소

const clickRow = document.querySelector(".order__modal_table"); // modal 내부 쿠폰 table
const selectOrderModal = document.querySelector(".order__modal_ok"); // modal 쿠폰선택
const cancelOrderModal = document.querySelector(".order__modal_cancel"); // modal 쿠폰선택 취소
const orderCouponTitle = document.querySelector(".order__coupon"); // 선택된 쿠폰 나오는 버튼
const orderCouponPk = document.querySelector(".order__coupon_pk"); // hidden으로 처리된 쿠폰 번호

const orderPoint = document.querySelector(".order__point"); // 사용할 적립금
const orderAllPointBtn = document.querySelector(".order__point_alluse");// 전체선택 쿠폰

const productSummary = document.querySelector(".order__prod_summary"); // 상품명 요약

const deliveryPk = document.querySelector('.delivery_address_id').getAttribute('delivery_address_id'); // 선택된 배송지 pk
const deliveryPlace = document.querySelectorAll('.order_info_delivery_place > label'); // 받으실 장소
const deliveryPlaceDetail = document.querySelectorAll('.order_info_delivery_place_detail > label'); // 받으실 장소 상세
const deliveryPlaceDetailInput = document.querySelector('.order_info_option input'); // 받으실 장소 상세 - 작성란

const orderInfoLabel = document.querySelectorAll(".order_info_template__radiobox input[name='come_method']"); // 공동출입구, 기타, 자유출입구
const orderInfoOption = document.querySelector(".order_info_option"); // 공동출입구
const orderInfoOptionSpan = document.querySelector(".order_info_option > span"); // span
const oderInfoOptionInput = document.querySelector(".order_info_option input"); // 공동출입구 placdholder

const deliveryMsg = document.querySelectorAll('.order_info_delivery_msgYN > label'); // 배송 메시지 수신여부

const orderBtn = document.querySelector(".order__price"); // 최종 주문 button

let COUPON_NAME; // 쿠폰 명
let COUPON_DC; // 쿠폰 할인가
let COUPON_PK; // 쿠폰 pk
let EVENT_LIST = []; // [ 사용할 적립금, 사용할 쿠폰 pk ]

let DELIVERY_PLACE = ''; // 받으실 장소
let DELIVERY_PLACE_DETAIL = ''; // 받으실 장소 detail
let DELIVERY_PLACE_DETAIL_INPUT = ''; // 작성 text
let DELIVERY_MSG = ''; // 배송완료 메시지

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

//  TODO insert, update fetch 하나로 통합하기
const orderPaymentAddressInsert = function (data) {
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

const updateDataAfterPayment = function (data) {
    return fetch("/order", {
        method: 'PATCH',
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

/* 객체
* 객체 변경시, java 객체도 변경 필요
*  */
class OrderPaymentAddressData {
    constructor(eventList, prodSummaryName, paymentPk, paymentMethod, deliveryPk, deliveryPlace, deliveryDetail, deliveryInput, deliveryMsg) {
        this.eventList = eventList;
        this.prodSummaryName = prodSummaryName;
        this.paymentPk = paymentPk;
        this.paymentMethod = paymentMethod;
        this.deliveryPk = deliveryPk;
        this.deliveryPlace = deliveryPlace;
        this.deliveryDetail = deliveryDetail;
        this.deliveryInput = deliveryInput; // TODO deliveryDetail과 deliveryInput 비교해서 필수값 넣어주기
        this.deliveryMsg = deliveryMsg;
    }
}

/* Event Function */

function handleClickRow(event) {
    // 클릭된 행 찾기
    const clickedRow = event.target.closest(".order__modal_table_instance");
    COUPON_NAME = clickedRow.querySelector(".order__coupon_name").innerHTML; // coupon 명 변수에 저장
    COUPON_DC = clickedRow.querySelector(".order__coupon_dc").innerHTML; // coupon 할인 가를 변수에 저장
    COUPON_PK = clickedRow.querySelector("td[hidden]").innerHTML; // coupon pk 변수에 저장
    // table row 누르면 radio btn click 되게 하기
    if (clickedRow) {
        const radio = clickedRow.querySelector('input[type="radio"]');
        radio.checked = true;
    }
}

// 쿠폰 적용 및 실제 결제창에 값 적용
function handleSelectOrderModal(event) {
    if (event.target === selectOrderModal) {
        modal.classList.toggle("show"); // 모달 적용

        if (!modal.classList.contains("show")) body.style.overflow = "auto"; // body scroll 원상 복구
        if (COUPON_NAME === undefined || COUPON_NAME === "") return;

        orderCouponTitle.innerHTML = COUPON_NAME + " " + COUPON_DC; // modal 밖 coupon에 적용
        orderCouponPk.innerHTML = COUPON_PK; // modal 밖 hidden에 적용

        const dcList = extractNumbersFromString(COUPON_DC);
        const useCoupon = document.querySelector(".order_benu__coupon");

        if (dcList.length === 1) {
            useCoupon.textContent = "-" + dcList[0] + "원";
        } else {
            const orderPrice = document.querySelector(".order_benu__order_price").textContent;
            const beforeMaxRateDC = regexWonToNum(orderPrice) / 100 * dcList[0];
            const afterMaxRateDC = beforeMaxRateDC > dcList[1] ? dcList[1] : beforeMaxRateDC;
            console.log(afterMaxRateDC);
            useCoupon.textContent = "-" + afterMaxRateDC + "원";
        }

        changePaymentPrice(); // 최종결제 금액 변경
    }
}

// 쿠폰 할인가 정규화. list 이용
function extractNumbersFromString(string) {
    const regex = /\d+/g;
    const matches = string.match(regex);
    return matches.map(match => parseInt(match)); // list로 만들어준다.
}

function handleSelectDeliveryPlace(event) {
    DELIVERY_PLACE = event.target.value;
}

function handleSelectDeliveryPlaceDetail(event) {
    DELIVERY_PLACE_DETAIL = event.target.value;
}

function handleSelectDeliveryPlaceDetailInput() {
    DELIVERY_PLACE_DETAIL_INPUT = deliveryPlaceDetailInput.value;
}

function handleSelectDeliveryMsg(event) {
    DELIVERY_MSG = event.target.value;
}

// open toggle 누르면 해당 li hidden 없애기
// TODO - 현재는 selectAll을 이용해서 toggle적용을 하였지만 html에서 해당 부분을 div로 감싸서 작동하도록 수행해도 된다.
//      - 현 상황에서 빠르게 프로젝트를 끝내야하므로 일단은 이 방법으로 진행하고 나중에 div로 css 부분까지 해결하고 변경하도록 한다.
function handleOpenCloseProduct(event) {
    const icon = event.target;
    icon.classList.toggle("fa-chevron-up");
    icon.classList.toggle("fa-chevron-down");
    orderProdSummary.classList.toggle("order_li_hidden");
    orderProdItemList.forEach((orderProdItem) => {
        orderProdItem.classList.toggle("order_li_hidden");
    })
}

// 공동현관, 기타 누를시 동적 html 생성하기
function handleShowInfoInput(event) {
    const value = event.target.value; // 공동현관, 기타
    if (value === "자유 출입 가능")
        return (orderInfoOption.style.display = "none");
    orderInfoOption.style.display = "";
    if (value === "공동현관") {
        orderInfoOptionSpan.textContent = "👉 공동현관 비밀번호";
        oderInfoOptionInput.placeholder = "공동현관 비밀번호";
    }
    if (value === "기타") {
        orderInfoOptionSpan.textContent = "👉 기타 작성란";
        oderInfoOptionInput.placeholder = "기타 작성란";
    }
}


// point 사용 검증 : 음수까지 가능
const regexWonToNum = function (numberWithWon) {
    const regexPoint = /(-?\d+)/; // 숫자 정규식
    const maxPointList = numberWithWon.match(regexPoint);
    return parseInt(maxPointList[0]);
};

// point 초과시 검증
function handleValidatePoint() {
    const inputPoint = parseInt(orderPoint.value);
    const placeholder = orderPoint.placeholder;
    const maxPoint = regexWonToNum(placeholder);
    orderPoint.value = inputPoint > maxPoint ? maxPoint : inputPoint;
    const usePoint = document.querySelector(".order_benu__point");
    usePoint.textContent = "-" + orderPoint.value + " point";
    changePaymentPrice();
}

// 모든 적립금 사용
function handleUseAllPoint() {
    orderPoint.value = regexWonToNum(orderPoint.placeholder);
    const usePoint = document.querySelector(".order_benu__point");
    usePoint.textContent = "-" + orderPoint.value + " point";
    changePaymentPrice();
}

// 쿠폰, 적립금 사용시 결제가 변동되는 함수
const changePaymentPrice = function () {
    const paymentList = document.querySelectorAll('.change_payment_price');
    let totalPaymentPrice = 0;
    paymentList.forEach((payment) => {
        totalPaymentPrice += regexWonToNum(payment.textContent);
    })
    document.querySelector(".order__price").textContent = totalPaymentPrice + " 원 결제하기";
    document.querySelector(".order_benu__total").textContent = totalPaymentPrice + " 원";
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
    //  배송지 필수정보 다 넣었는지 확인 필요
    try {
        EVENT_LIST.push(orderPoint.value === '' ? 0 : orderPoint.value); // 값이 없어서 list에서 출력시, null이지만 실제로는''로 적용된다.
        EVENT_LIST.push(orderCouponPk.textContent);
        console.log('get data for using paymentAPI');
        console.log(EVENT_LIST);
        const paymentData = await getPaymentData(EVENT_LIST); // 결제 api에 보낼 정보
        console.log(paymentData);
        const productSummaryName = productSummary.textContent;
        // const encodingName = encodeURIComponent(productSummaryName);
        paymentData.name = productSummaryName; // 결제 api에 보낼 추가 정보

        console.log('start paymentAPI');
        const paymentResultData = await requestPay(paymentData); // 결제 응답
        console.log(paymentResultData);
        console.log('finish paymentAPI');

        if (!paymentResultData.success) return;

        console.log('start orderPaymentAddressData');
        // 객체 생성
        const orderPaymentAddressData = new OrderPaymentAddressData(
            EVENT_LIST, productSummaryName, paymentData.paymentPk, paymentResultData.pg_provider,
            deliveryPk, DELIVERY_PLACE, DELIVERY_PLACE_DETAIL, DELIVERY_PLACE_DETAIL_INPUT, DELIVERY_MSG)
        console.log(orderPaymentAddressData);
        console.log('finish orderPaymentAddressData');

        console.log('start update DB : order, payment, address');
        const insert = await orderPaymentAddressInsert(orderPaymentAddressData);
        console.log(insert);
        console.log('finish update DB : order, payment, address');
        console.log('finish orderRESTAPI : this for update data');
        // todo. 재고날리기, 쿠폰 사용한거 날리기, 적립금 줄이기, 장바구니 상품 n으로 변경
        console.log('start update inventory')
        // [장바구니 상품코드 list, eventcodelist]
        // const cartProdSeqList = Array.from(orderProdCartSeq).map(cartProdSeq => cartProdSeq.getAttribute("cart_prod_seq"));
        const update = await updateDataAfterPayment(EVENT_LIST);
        console.log(update);

    } catch (e) {
        console.log(e);
    } finally {
        EVENT_LIST = [];
    }
}

/* Document EVENT */

// 클릭된 쿠폰 정보 변수 저장 및 input check 해주기
clickRow.addEventListener("click", handleClickRow);
// 확인 버튼 누르면 쿠폰 정보 화면에 넣은 후 닫기
selectOrderModal.addEventListener("click", handleSelectOrderModal);
orderBtn.addEventListener("click", order);
//  point 사용 검증
orderPoint.addEventListener("change", handleValidatePoint);
orderAllPointBtn.addEventListener("click", handleUseAllPoint);

// 배송관련정보 받아오기
deliveryPlace.forEach(label => {
    label.addEventListener('click', handleSelectDeliveryPlace)
});
deliveryPlaceDetail.forEach(label => {
    label.addEventListener('click', handleSelectDeliveryPlaceDetail)
})
deliveryPlaceDetailInput.addEventListener('change', handleSelectDeliveryPlaceDetailInput)

deliveryMsg.forEach(label => {
    label.addEventListener('click', handleSelectDeliveryMsg)
});

// open toggle 누르면 해당 li hidden 없애기
orderProdOpenClose.addEventListener("click", handleOpenCloseProduct);
// 공동현관, 기타 누를시 동적 html 생성하기
orderInfoLabel.forEach((label) => {
    label.addEventListener("click", handleShowInfoInput);
});