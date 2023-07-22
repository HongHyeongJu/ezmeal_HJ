/* DOCUMENT 변수명 */
const dynamicDiv = document.querySelector(".order-history__AJAX-load"); //jsp 파일에 미리준비한 div
const periodBtnAll = document.querySelectorAll(".order-history__period_btn"); // 조회 기간 버튼
const initPeriod = document.querySelector(".order-history__period__start_end"); // 초기 조회 시작 기간
const resetPeriod = document.querySelector(".reset-period"); // 조회 초기화 버튼

/* static 변수 */
const initPeriodList = initPeriod.textContent.split(" ~ "); // 시작 날짜와 종료 날짜 배열로 변경 - 초기 설정값 "고정"
const personalPeriodList = [initPeriodList[1]]; // 기간 설정 때 사용하는 날짜 list

/* Rendering 함수 */
const renderHTMLFrom = function (allOrderPaymentList) {
    let HTML_STRING = '';
    allOrderPaymentList.forEach((allOrderPayment) => {
        HTML_STRING += `<div class="order-history__products-header">
                <h4 class="order-history__products-header__title">
                    ${allOrderPayment.date_time}
                </h4>
                <a
                        href="/order/detail/${allOrderPayment.ord_id}"
                        class="order-history__products-header__detail-link"
                >
                    주문내역 상세보기 >
                </a>
            </div>
            <!-- 주문 내역 header 시작 -->

            <!-- 주문 내역 detail 시작 -->
            <div class="order-history__products-main">
                <div class="order-history__products-main__products">
                    <div class="order-history__products-definition">
                        <dl class="order-history__products-definition__list">
                            <dt class="order-history__products-definition__title">상품명</dt>
                            <dd class="order-history__products-definition__detail">${allOrderPayment.prod_smry}</dd>
                        </dl>
                        <dl class="order-history__products-definition__list">
                            <dt class="order-history__products-definition__title">주문번호</dt>
                            <dd class="order-history__products-definition__detail order-history"> ${allOrderPayment.ord_id} </dd>
                        </dl>
                        <dl class="order-history__products-definition__list">
                            <dt class="order-history__products-definition__title">결제방법</dt>
                            <dd class="order-history__products-definition__detail">${allOrderPayment.pay_mtd}</dd>
                        </dl>
                        <dl class="order-history__products-definition__list">
                            <dt class="order-history__products-definition__title">결제금액</dt>
                            <dd class="order-history__products-definition__detail">${allOrderPayment.pay_prc_format}원</dd>
                        </dl>
                    </div>
                    <!-- order-history__products-definition 끝 -->
                </div>
                <!-- order-history__products-main__products 끝 -->
                <div class="order-history__products-main__status">
                    <span class="order-history__products__status-span">${allOrderPayment.stus}</span>
                    <div class="order-history__products__status-function">전체취소(기능div)</div>
                </div>
                <!-- order-history__products-main__status 끝 -->
            </div>`;
    });

    dynamicDiv.innerHTML = HTML_STRING;
}

/* 사용 함수 */
// 처음 html loading 후, 바로 수행되는 함수
function getOrderPaymentData() {
    fetch('/orderPayment/initData', {
        method: 'GET',
    })
        .then(response => response.json())
        .then(data => {
            renderHTMLFrom(data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}


// 조회 버튼 관련 기능
async function handleOrderHistoryPeriod(event) {
    // 1. btn 색깔 변경
    // 2. btn 조회 기간 변경
    const periodCondition = convertStringToNumber(event.target.textContent); // 1월 -> 1
    const changePeriodDate = changePeriod(initPeriodList[1], periodCondition); // 변경된 날짜 string
    initPeriod.innerHTML = `${changePeriodDate} ~ ${initPeriodList[1]}`; // innerHtml 형식에 맞게 생성

    // 3. ajax로 조회 변경 -> 값 전달 할 시, 최근 날로부터 ~ 3개월  [최근날, 변경날]
    const changePeriodList = [initPeriodList[1]]; // 기간 설정 때 사용하는 날짜 list
    changePeriodList.unshift(changePeriodDate);
    const dynamicData = await fetchDynamicData(changePeriodList); // renderHTMLFrom() 사용, await으로 비동기 끝내기
    renderHTMLFrom(dynamicData); // innerHTML은 기존 내용을 제거 후, 새로 작성
}

function handleResetPeriod() {
    initPeriod.innerHTML = `${initPeriodList[0]} ~ ${initPeriodList[1]}`; // innerHtml 초기값으로 다시 형성
    getOrderPaymentData();
}

/* FETCH 함수 */
function fetchDynamicData(periodList) {
    return fetch('/orderPayment/dynamicData', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(periodList)
    })
        .then(response => {
            if (response.ok) return response.json();
            else throw new Error('Error: ' + response.status);
        })
        .catch(error => {
            console.error('Error fetching dymanic data: ', error);
        })
}

/* METHOD 추출 */

// 날짜 추출 함수
function changePeriod(dateStr, period) {
    const endDate = new Date(dateStr); // 시작 날짜를 Date 객체로 변환
    endDate.setMonth(endDate.getMonth() - period); // period달 빼기
    // 날짜 string으로 변환
    return `${endDate.getFullYear()}-${String(endDate.getMonth() + 1).padStart(
        2,
        "0"
    )}-${String(endDate.getDate()).padStart(2, "0")}`;
}

// string의 첫단어
function convertStringToNumber(str) {
    const numberOnly = str.replace(/\D/g, "");
    const number = parseInt(numberOnly);
    return number;
}

/* EVENT 함수 */
document.addEventListener('DOMContentLoaded', getOrderPaymentData); // html 문서 load 된 후 실행되는 js 함수
periodBtnAll.forEach((periodBtn) => {
    periodBtn.addEventListener("click", handleOrderHistoryPeriod);
});

resetPeriod.addEventListener("click", handleResetPeriod);