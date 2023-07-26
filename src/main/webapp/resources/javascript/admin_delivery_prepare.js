/* DOCUMENT 변수명 */
const periodBtnAll = document.querySelectorAll(".admin__period_btn"); // due btn

const dynamicTable = document.querySelector('.admin-order__content-table > tbody');
const selectAllBtn = document.querySelector('.admin-order__content-table thead input[type="checkbox"]'); // check box 전체 선택
let selectBtns;
const checkPayment = document.querySelector('.admin-order__check-order > button'); // 발주 확인 btn   # 개별
// check_box_module의 변수로 SELECT_SEQ_LIST, dynamicNum 가 존재

/* Rendering 함수 */
const renderHTMLFrom = function (adminBeforeManageInfoList) {
    let HTML_STRING = ''; // 외부 html
    let forHTML = ''; // 내부 html
    adminBeforeManageInfoList.forEach((info) => {
        const in_dtm_format = info.in_dtm_format ?? ''; // 주문일
        const ord_id = info.ord_id ?? ''; // 주문번호
        const name = info.name ?? '';  // 주문자명
        const vend = info.vend ?? '';  // 주문자명
        const invc_id = info.invc_id ?? ''; // 송장번호
        const dexp = info.dexp ?? ''; // 배송비
        const pay_mtd = info.pay_mtd ?? ''; // 결제방식
        const prod_name = info.prod_name ?? ''; // 주문상품 명
        const qty = info.qty ?? ''; // 주문상품 수량
        const tot_prc = info.tot_prc ?? ''; // 상품 할인 제외 상품 가격 * 수량
        const setl_expct_prc = info.setl_expct_prc ?? ''; // 상품 할인 포함 가격 * 수량
        const count = info.count ?? ''; // 상품 중복수량
        const dlvar_id = info.dlvar_id ?? ''; // 배송지 pk
        const bndl_yn = info.bndl_yn ?? ''; // 묶음 선택

        // 1. 개별값 존재하는 colum 배열로 빼기 - prod_name, qty, tot_prc, setl_expct_prc, 비고
        function convertStringToArray(str) {
            return str.split(',').map(item => item.trim());
        }

        // prod_name, qty, tot_prc, setl_expct_prc 변수들을 배열로 변환
        const prod_name_arr = convertStringToArray(prod_name);
        const qty_arr = convertStringToArray(qty);
        const tot_prc_arr = convertStringToArray(tot_prc);
        const setl_expct_prc_arr = convertStringToArray(setl_expct_prc);
        const dlvar_id_arr = convertStringToArray(dlvar_id);
        const bndl_yn_arr = convertStringToArray(bndl_yn);

        // 2. 벡틱 내부에 for문 돌리기
        if (count > 1) {
            for (let i = 1; i < count; i++) {
                const prod_name = prod_name_arr[i];
                const currentQty = qty_arr[i];
                const currentTotPrc = tot_prc_arr[i];
                const currentSetlExpctPrc = setl_expct_prc_arr[i];
                const dlvar_id = dlvar_id_arr[i];
                const bndl_yn = bndl_yn_arr[i];

                // 생성된 템플릿 문자열 추가
                forHTML += `
                            <tr>
                              <!-- 추가적인 필요한 코드 -->
                              <td><input type="checkbox" dlvar_id="${dlvar_id}" ${bndl_yn === 'y' ? 'checked' : ''}/></td>
                              <td>${prod_name}</td>
                              <td>${currentQty}</td>
                              <td>${currentTotPrc}</td>
                              <td>${currentSetlExpctPrc}</td>
                              <td>비고</td>
                              <!-- 추가적인 필요한 코드 -->
                            </tr>
                          `;
            }
        }

        HTML_STRING += `<tr ord_id="${ord_id}"> <!-- -->
                            <td rowspan="${count}"><input type="checkbox"/></td> <!-- -->
                            <td rowspan="${count}">${in_dtm_format}</td> <!-- -->
                            <td rowspan="${count}">${ord_id}</td> <!-- -->
                            <td rowspan="${count}">${name}</td> <!--주문자-->
                            <!-- 묶음선택 btn : 묶음 선택 된 상품에 한해서 배송 : 배송 보류 처리가 가능
                            TODO. 묶음선택 check 부분 & 배송 보류의 경우 - deliveryMaster 와 deliveryHistory insert 작업이 필요할 듯하다. - 3차 개발때 진행
                            -->
                            <td rowspan="${count}"><button>묶음선택</button></td>
                            <td><input type="checkbox" class="${dlvar_id_arr[0]}" ${bndl_yn_arr[0] === 'y' ? 'checked' : ''}/></td> <!-- 묶음선택할 check box-->
                            <td rowspan="${count}">  <!--운송장번호-->
                                <select name="admin-order__select-type">
                                    <option value="ezmeal">자체배송</option>
                                    <option value="post-office">우체국배송</option>
                                    <option value="cj">CJ대한통운</option>
                                </select>
                                <input type="text" name="invc" value=${invc_id}>
                            </td>
                            <td rowspan="${count}"><input type="text" name="delivery_fee" value=${dexp}></td> <!--배송비-->
                            <td rowspan="${count}">${vend}</td>  <!--공급사-->
                            <td>${prod_name_arr[0]}</td>  <!--주문 개별 상품-->
                            <td>${qty_arr[0]}</td>  <!--주문 수량-->
                            <td>${tot_prc_arr[0]}</td>  <!--상품 할인제외 금액-->
                            <td>${setl_expct_prc_arr[0]}</td>  <!--상품 할인 포함 금액-->
                            <td rowspan="${count}">${pay_mtd}</td> <!--결제수단-->
                            <td>비고</td>
                        </tr>
                        ${forHTML}
                        `;
        forHTML = ''; // 내부 html 누적 초기화
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

// due btn 누를 경우 , dynamic 수행
periodBtnAll.forEach((periodBtn) => {
    periodBtn.addEventListener('click',
        (event) => handlePeriodAndRender(event, '/admin/delivery')
    );
})
// // 전체 선택 버튼 누를 경우
// selectAllBtn.addEventListener("click",
//     (event) => selectAllProduct('tr','ord_id')
// );
// checkPayment.addEventListener("click",
//     () => handleClickCheckPaymentBtn('/admin/order/before-management', SELECT_SEQ_LIST)
// ); // 발주 확인 이벤트
