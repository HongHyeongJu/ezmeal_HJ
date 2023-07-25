/* DOCUMENT 변수명 */
const dynamicTable = document.querySelector('.admin-order__content-table > tbody');
const selectAllBtn = document.querySelector('.admin-order__content-table thead input[type="checkbox"]'); // check box 전체 선택
let selectBtns;
const checkPayment = document.querySelector('.admin-order__check-order > button'); // 발주 확인 btn   # 개별
const periodBtnAll = document.querySelectorAll(".admin__period_btn"); // 조회 기간 버튼
// check_box_module의 변수로 SELECT_SEQ_LIST, dynamicNum 가 존재

/* Rendering 함수 */
const renderHTMLFrom = function (adminBeforeManageInfoList) {
    let HTML_STRING = '';
    adminBeforeManageInfoList.forEach((info) => {
        // js에서 undefined가 나올 경우 default 값 주기
        const in_dtm_format = info.in_dtm_format ?? '';
        const ord_id = info.ord_id ?? '';
        const pay_mtd = info.pay_mtd ?? '';
        const name = info.name ?? '';
        const qty = info.qty ?? '';
        const tot_prc = info.tot_prc ?? '';
        const count = info.count ?? '';
        const setl_expct_prc = info.setl_expct_prc ?? '';

        HTML_STRING += `<tr ord_id="${ord_id}">
                            <td><input type="checkbox"/></td>
                            <td>${in_dtm_format}</td>
                            <td>${ord_id}</td>
                            <td>${qty}</td>
                            <td>${name}</td>
                            <td>${pay_mtd}</td>
                            <td>${tot_prc}</td>
                            <td>${count}</td>
                            <td>${setl_expct_prc}</td>
                        </tr>`;
    });

    dynamicTable.innerHTML = HTML_STRING;

    // 동적 생성 요소에 관한 /* DOCUMENT 변수명 */ 및 /* EVENT 함수 */ TODO 따로 함수로 빼는 것이 조금 더 코드를 보기가 깔끔할 듯 하다.
    selectBtns = document.querySelectorAll('.admin-order__content-table tbody input[type="checkbox"]'); // check box 선택
    selectBtns.forEach((selectBtn) => {
        selectBtn.addEventListener("click",
            event => selectProduct(event, 'tr', 'ord_id')
        );
    }); // 상품 선택 이벤트
}

/* EVENT 함수 */

// 처음 html loading 후, 바로 수행되는 함수, html 문서 load 된 후 실행되는 js 함수
document.addEventListener('DOMContentLoaded',
    (event) => firstRenderData('/admin/delivery', event)
);
// // 기간 btn 누를 경우 , dynamic 수행
// periodBtnAll.forEach((periodBtn) => {
//     periodBtn.addEventListener('click',
//         (event) => handlePeriodAndRender(event, '/admin/order/dynamic-before-management')
//     );
// })
// // 전체 선택 버튼 누를 경우
// selectAllBtn.addEventListener("click",
//     (event) => selectAllProduct('tr','ord_id')
// );
// checkPayment.addEventListener("click",
//     () => handleClickCheckPaymentBtn('/admin/order/before-management', SELECT_SEQ_LIST)
// ); // 발주 확인 이벤트
