/* DOCUMENT 변수명 */
const dynamicTable = document.querySelector('.admin-order__content-table > tbody');
const selectAllBtn = document.querySelector('.admin-order__content-table thead input[type="checkbox"]'); // check box 전체 선택
let selectBtns;

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
    selectBtns.forEach((selectBtn) => { selectBtn.addEventListener("click", selectOrderCheckBox);}); // 상품 선택 이벤트
}

/* 사용 함수 */

// 처음 html loading 후, 바로 수행되는 함수
function getOrderPaymentData(periodString) {
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

/* EVENT 함수 */
document.addEventListener('DOMContentLoaded', getOrderPaymentData); // html 문서 load 된 후 실행되는 js 함수

selectAllBtn.addEventListener("click", selectAllOrderCheckBox); // 전체 상품 선택 이벤트