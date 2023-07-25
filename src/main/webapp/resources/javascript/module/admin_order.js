/* 사용 함수 */

// 처음 html loading 후, 바로 수행되는 함수
const firstRenderData = async function (url, event) {
    const adminDynamicData = await getAdminDynamicData(url, event);
    console.log('firstRenderData : ' + adminDynamicData);
    renderHTMLFrom(adminDynamicData);
}

// 기간 btn 누를 경우 , dynamic 수행
const handlePeriodAndRender = async function (event, url) { // admin_due.js에 이미 등록이 되어있는 함수이다.
    const periodDateString = handlePeriod(event); // admin_due의 함수 호출
    const adminDynamicData = await getAdminDynamicData(url, periodDateString);
    renderHTMLFrom(adminDynamicData);
}
//
// // checkbox 선택 후, 해당 ord_id를 list에 담음
// // 전체 선택 btn
// const selectAllOrderCheckBox = function (tag, property) {
//     selectAllProduct("tr", "ord_id");
// }
// // 개별 선택 btn
// const selectOrderCheckBox = function (event, tag, property) {
//     selectProduct(event, tag, property);
// }
//
// 발주 확인 버튼, update 함수  '/admin/order/before-management'
const handleClickCheckPaymentBtn = async function (url, SELECT_SEQ_LIST) {
    console.log(SELECT_SEQ_LIST);
    await updateAdminSubmitBtn(url, SELECT_SEQ_LIST); // then 내부 return 설정 or catch 내용 반환 받는다.
    // todo -> 주문 내역때 처림 기간을 보여줘서 해당 기간 값을 string으로 변환 후 넘겨주는 것이 정석 / 초기화 btn도 필요할 듯하다.
    const adminDynamicData = await getAdminDynamicData(url, {isTrusted: true});
    renderHTMLFrom(adminDynamicData);
    alert('발주성공')
}

