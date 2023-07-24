/* DOCUMENT 변수명 */
const dynamicTable = document.querySelector('.admin-order__content-table > tbody');
const selectAllBtn = document.querySelector('.admin-order__content-table thead input[type="checkbox"]'); // check box 전체 선택
let selectBtns;
const checkPayment = document.querySelector('.admin-order__check-order > button'); // 발주 확인 btn

/* Rendering 함수 */
const renderHTMLFrom = function (adminBeforeManageInfoList) {
    let HTML_STRING = '';
    adminBeforeManageInfoList.forEach((info) => {
        // js에서 undefined가 나올 경우 default 값 주기
        const in_dtm = info.in_dtm ?? '';
        const ord_id = info.ord_id ?? '';
        const prod_smry = info.prod_smry ?? '';
        const name = info.name ?? '';
        const pay_mtd = info.pay_mtd ?? '';
        const bank = info.bank ?? '';
        const card_num = info.card_num ?? '';
        const rmk = info.rmk ?? '';

        HTML_STRING += `<tr ord_id="${ord_id}">
                            <td><input type="checkbox"/></td>
                            <td>${in_dtm}</td>
                            <td>${ord_id}</td>
                            <td>${prod_smry}</td>
                            <td>${name}</td>
                            <td>${pay_mtd}</td>
                            <td>${bank}</td>
                            <td>${card_num}</td>
                            <td>${rmk}</td>
                        </tr>`;
    });

    dynamicTable.innerHTML = HTML_STRING;

    // 동적 생성 요소에 관한 /* DOCUMENT 변수명 */ 및 /* EVENT 함수 */ TODO 따로 함수로 빼는 것이 조금 더 코드를 보기가 깔끔할 듯 하다.
    selectBtns = document.querySelectorAll('.admin-order__content-table tbody input[type="checkbox"]'); // check box 선택
    selectBtns.forEach((selectBtn) => {
        selectBtn.addEventListener("click", selectOrderCheckBox);
    }); // 상품 선택 이벤트
}

/* 사용 함수 */

// 처음 html loading 후, 바로 수행되는 함수
function getOrderPaymentData(periodString) {
    console.log(periodString);
    fetch('/admin/order/dynamic-before-management', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(periodString)
    })
        .then(response => response.json())
        .then(data => {
            renderHTMLFrom(data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

// checkbox 선택 후, 해당 ord_id를 list에 담음
function selectAllOrderCheckBox() {
    selectAllProduct("tr", "ord_id");
}

function selectOrderCheckBox(event) {
    selectProduct(event, "tr", "ord_id");
}

async function handleClickCheckPaymentBtn() {
    console.log(SELECT_SEQ_LIST);
    await updateCheckPayment(SELECT_SEQ_LIST); // then 내부 return 설정 or catch 내용 반환 받는다.
    getOrderPaymentData({isTrusted:true}); // todo -> 주문 내역때 처림 기간을 보여줘서 해당 기간 값을 string으로 변환후 넘겨주는 것이 정석 / 초기화 btn도 필요할 듯하다.
}

// fetch 함수
const updateCheckPayment = function (orderIdList) {
    return fetch('/admin/order/before-management', {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(orderIdList) // update list
    })
        .then(response => response.text()) // text의 경우: text(), list,map,object 경우 .json()
        .then(data => {
            console.log(data);
            return data;
            // 가지고 온 data로 reload 수행
        })
        .catch(error => {
            console.error('Error:', error); // 따로 return으로 반환할 필요 없음
        })
}

/* EVENT 함수 */
document.addEventListener('DOMContentLoaded', getOrderPaymentData); // html 문서 load 된 후 실행되는 js 함수

selectAllBtn.addEventListener("click", selectAllOrderCheckBox); // 전체 상품 선택 이벤트

checkPayment.addEventListener("click", handleClickCheckPaymentBtn); // 발주 확인 이벤트