/* DOCUMENT 변수명 */
const dynamicTable = document.querySelector('.admin-order__content-table > tbody');
const selectAllBtn = document.querySelector('.admin-order__content-table thead input[type="checkbox"]'); // check box 전체 선택
let selectBtns;
const checkPayment = document.querySelector('.admin-order__check-order > button'); // 발주 확인 btn   # 개별
const periodBtnAll = document.querySelectorAll(".admin__period_btn"); // 조회 기간 버튼
// check_box_module의 변수로 SELECT_SEQ_LIST, dynamicNum 가 존재

/* Rendering 함수 */ // # 개별
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
async function firstRenderData (event) {
    const adminDynamicData = await getAdminDynamicData('dynamic-before-management', event);
    renderHTMLFrom(adminDynamicData);
}

// 기간 btn 누를 경우 , dynamic 수행
async function handlePeriodAndRender(event){ // admin_due.js에 이미 등록이 되어있는 함수이다.
    const periodDateString = handlePeriod(event); // admin_due의 함수 호출
    const adminDynamicData = await getAdminDynamicData('/admin/order/dynamic-before-management', periodDateString);
    renderHTMLFrom(adminDynamicData);
}

// checkbox 선택 후, 해당 ord_id를 list에 담음
// 전체 선택 btn
function selectAllOrderCheckBox() {
    selectAllProduct("tr", "ord_id");
}
// 개별 선택 btn
function selectOrderCheckBox(event) {
    selectProduct(event, "tr", "ord_id");
}

// 발주 확인 버튼, update 함수
async function handleClickCheckPaymentBtn() {
    console.log(SELECT_SEQ_LIST);
    await updateAdminSubmitBtn('/admin/order/before-management', SELECT_SEQ_LIST); // then 내부 return 설정 or catch 내용 반환 받는다.
    // todo -> 주문 내역때 처림 기간을 보여줘서 해당 기간 값을 string으로 변환 후 넘겨주는 것이 정석 / 초기화 btn도 필요할 듯하다.
    const adminDynamicData = await getAdminDynamicData('/admin/order/dynamic-before-management',{isTrusted:true});
    renderHTMLFrom(adminDynamicData);
}


/* EVENT 함수 */
document.addEventListener('DOMContentLoaded', firstRenderData); // html 문서 load 된 후 실행되는 js 함수
selectAllBtn.addEventListener("click", selectAllOrderCheckBox); // 전체 상품 선택 이벤트
checkPayment.addEventListener("click", handleClickCheckPaymentBtn); // 발주 확인 이벤트
periodBtnAll.forEach((periodBtn) => {
    periodBtn.addEventListener('click', handlePeriodAndRender); // 해당 함수가 존재하는 js 파일이 먼저 로딩되야한다.
})
