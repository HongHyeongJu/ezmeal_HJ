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

// 발주 확인 버튼, update 함수  '/admin/order/before-management'
const handleClickCheckPaymentBtn = async function (updateUrl, dynamicUrl, fetchDataList) {
    console.log(fetchDataList);
    await updateAdminSubmitBtn(updateUrl, fetchDataList); // then 내부 return 설정 or catch 내용 반환 받는다.
    // todo -> 주문 내역때 처림 기간을 보여줘서 해당 기간 값을 string으로 변환 후 넘겨주는 것이 정석 / 초기화 btn도 필요할 듯하다.
    const adminDynamicData = await getAdminDynamicData(dynamicUrl, {isTrusted: true});
    console.log("adminDynamicData");
    console.log(adminDynamicData);
    renderHTMLFrom(adminDynamicData);
    // rest api 이기 때문에 전역 변수 초기화를 수동으로 처리
    SELECT_SEQ_LIST = []; // 선택된 발주 버튼
    dynamicNum = 0; // 체크박스 선택 수 확인
    alert('발주성공')
}

// admin delivery 준비 중 logic 수행 btn 함수
const handleClickAdminDeliveryBtn = async function (fetchUrl, reloadUrl, fetchDataList, msg) {
    console.log('-----------------------------------------------------------')
    console.log('-------- admin_order.js handleClickAdminDeliveryBtn 시작 ------------');
    console.log(fetchDataList);
    await updateAdminSubmitBtn(fetchUrl, fetchDataList); // then 내부 return 설정 or catch 내용 반환 받는다.
    // todo -> 주문 내역때 처림 기간을 보여줘서 해당 기간 값을 string으로 변환 후 넘겨주는 것이 정석 / 초기화 btn도 필요할 듯하다.
    const adminDynamicData = await getAdminDynamicData(reloadUrl, {isTrusted: true});
    renderHTMLFrom(adminDynamicData);
    // rest api 이기 때문에 전역 변수 초기화를 수동으로 처리
    selectInit();
    alert(msg);
    console.log('-------- admin_order.js handleClickAdminDeliveryBtn 끝 ------------');
}
