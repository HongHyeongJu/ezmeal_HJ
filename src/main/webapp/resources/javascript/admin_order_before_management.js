/* DOCUMENT 변수명 */
const dynamicTable = document.querySelector('.admin-order__content-table > tbody');

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

        HTML_STRING += `<tr>
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
}

/* 사용 함수 */
// 처음 html loading 후, 바로 수행되는 함수
function getOrderPaymentData(periodString) {
    fetch('/admin/order/dynamic-before-management', {
        method: 'POST',
        headers: {
            'Content-Type' : 'application/json',
        },
        body: JSON.stringify(periodString)
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            renderHTMLFrom(data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}


/* EVENT 함수 */
document.addEventListener('DOMContentLoaded', getOrderPaymentData); // html 문서 load 된 후 실행되는 js 함수
