/* 구매 확정
* */
const orderFixedAll = document.querySelectorAll('.order-detail__product_fixed'); // 구매확정 btn 누르기
if (orderFixedAll) orderFixedAll.forEach(orderFixed => {
    orderFixed.addEventListener('click', handleOrderFixed);
})

async function handleOrderFixed(event) {
    const orderDetailPk = event.target.getAttribute('ord_dtl_pk');
    const updateResult = await updateAdminSubmitBtn('/order/detail/fixed', orderDetailPk); // db 내용 update 하기 - 구매 확정으로 변경
    console.log(updateResult);
    if (updateResult === 'fail') {
        console.log('구매 확정에 실패했습니다.');
        alert('구매 확정에 실패했습니다.');
        return;
    }
    event.target.previousElementSibling.textContent = '구매 확정'; // 상태 tag 변경
    event.target.className = 'order-detail__product_review'; // class명 변경
    event.target.textContent = '리뷰 작성하기'; // button 내용 변경
}

/*
리뷰작성
 */
const orderId = window.location.pathname.split('/').pop(); // url의 page 가지고 오기
const orderReviewBtnAll = document.querySelectorAll('.order-detail__product_review'); // 리뷰작성하기 누르기

if (orderReviewBtnAll) orderReviewBtnAll.forEach(orderReviewBtn => {
    orderReviewBtn.addEventListener('click', handleWriteOrderReview);
})

// todo. 나중에 상품쪽에서 사용할 수 있도록

async function handleWriteOrderReview(event) {
    const stringPromise = await fetch(`/modal/review/${orderId}`, {
        method: 'get',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
    })
        .then(response => response.text())
        .then(data => {
            return data;
        });
    console.log(stringPromise);

    // modal 생성될 때 적용되는 code
    const prodReviewModalDiv = document.querySelector('.prod_review_modal'); // 해당 위치 div 찾기
    prodReviewModalDiv.innerHTML = stringPromise; // jsp 형태 string 넣기
    prodReviewModalDiv.classList.toggle("show");
    if (prodReviewModalDiv.classList.contains("show")) {
        body.style.overflow = "hidden"; // body scroll 막는 code
    }
    const orderDetailIdInput = prodReviewModalDiv.querySelector('.prod-review-modal__order-detail-id');
    orderDetailIdInput.value = event.target.getAttribute('ord_dtl_pk'); // 눈에 안보이는 input에 담겨있음

    const prodCdInput = prodReviewModalDiv.querySelector('.prod-review-modal__prod-cd');
    prodCdInput.value = event.target.getAttribute('prod_cd'); // 눈에 안보이는 input에 담겨있음

    BTN_CANCEL = document.querySelector('.prod_review_cancel');
    BTN_CANCEL.addEventListener('click', handleCancel);
    console.log(BTN_CANCEL);
}