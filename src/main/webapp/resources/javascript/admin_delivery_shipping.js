/* DOCUMENT 변수명 */
const periodBtnAll = document.querySelectorAll(".admin__period_btn"); // due btn
const dynamicTable = document.querySelector('.admin-order__content-table > tbody'); // 동적 data 들어가는 table

const bndlAllBtn = document.querySelector('.dlvar-id__all-checkBox'); // order-id를 list에 담는 전체 선택 check-box
let bndlBtns; // dlvar_id를 list에 담는 전체 선택 check-box

// SELECT_SEQ_LIST : 주문번호 list
// DLVAR_SEQ_LIST  : 배송번호 list

const checkDeliveryShipping = document.querySelector('.admin-order__check-order button:nth-child(1)'); // 배송중 처리 btn
const addInvoiceNum = document.querySelector('.admin-order__check-order button:nth-child(2)');  // 송장번호 저장 btn
// todo 나머지 btn은 3차 개발 때


/* Rendering 함수 */
const renderHTMLFrom = function (adminBeforeManageInfoList) {
    let HTML_STRING = ''; // 외부 html
    let forHTML = ''; // 내부 html
    adminBeforeManageInfoList.forEach((info) => {
        const in_dtm_format = info.in_dtm_format ?? '';     // 주문일
        const ord_id = info.ord_id ?? '';                   // 주문번호
        const name = info.name ?? '';                       // 주문자명
        const up_dtm_format = info.up_dtm_format ?? '';     // 배송일 dm.up_dtm_format   - 개별
        const dlvar_id = info.dlvar_id ?? '';               // 배송지 pk == 주문상세번호     - 개별
        const invc_id = info.invc_id ?? '';                 // 송장번호
        const vend = info.vend ?? '';                       // 공급회사
        const prod_name = info.prod_name ?? '';             // 주문상품 명                - 개별
        const qty = info.qty ?? '';                         // 주문상품 수량               - 개별
        const count = info.count ?? '';                     // 상품 중복수량

        // 1. 개별값 존재하는 colum 배열로 빼기 - prod_name, qty, tot_prc, setl_expct_prc, 비고
        function convertStringToArray(str) {
            return str.split(',').map(item => item.trim());
        }

        // prod_name, qty, tot_prc, setl_expct_prc 변수들을 배열로 변환
        const up_dtm_format_arr = convertStringToArray(up_dtm_format);
        const dlvar_id_arr = convertStringToArray(dlvar_id);
        const prod_name_arr = convertStringToArray(prod_name);
        const qty_arr = convertStringToArray(qty);

        // 2. 벡틱 내부에 for문 돌리기
        if (count > 1) {
            for (let i = 1; i < count; i++) {
                const up_dtm_format = up_dtm_format_arr[i];
                const dlvar_id = dlvar_id_arr[i];
                const prod_name = prod_name_arr[i];
                const currentQty = qty_arr[i];

                // 생성된 템플릿 문자열 추가
                forHTML += `
                            <tr>
                              <!-- 추가적인 필요한 코드 -->
                              <td><input type="checkbox" class="dlvar-id__checkBox" dlvar_id="${dlvar_id}" /></td> <!-- checkbox -->
                              <td>${up_dtm_format} / ${dlvar_id}</td> <!-- 배송일 / 배송번호-->
                              <td>${prod_name}</td> <!-- 상품명 -->
                              <td>${currentQty}</td> <!-- 수량 -->
                              <td>비고</td>
                            </tr>
                          `;
            }
        }

        HTML_STRING += `<tr ord_id="${ord_id}"> <!-- -->
                            <td rowspan="${count}">${in_dtm_format} / ${ord_id}</td> <!-- 주문일 / 주문번호 -->
                            <td rowspan="${count}">${name}</td> <!--주문자-->
                            <!-- 묶음선택 btn : 묶음 선택 된 상품에 한해서 배송 : 배송 보류 처리가 가능
                            TODO. 묶음선택 check 부분 & 배송 보류의 경우 - deliveryMaster 와 deliveryHistory insert 작업이 필요할 듯하다. - 3차 개발때 진행
                            -->
                            <td><input type="checkbox" class="dlvar-id__checkBox" dlvar_id="${dlvar_id_arr[0]}" /></td> <!-- 묶음선택할 check box-->
                            <td> ${up_dtm_format_arr[0]} / ${dlvar_id_arr[0]}</td> <!-- 배송일 / 배송번호 -->                            
                            <td rowspan="${count}"> ${invc_id} </td> <!-- 운송장번호 -->
                            <td rowspan="${count}">${vend}</td>      <!-- 공급사 -->
                            <td>${prod_name_arr[0]}</td>             <!-- 주문 개별 상품명 -->
                            <td>${qty_arr[0]}</td>                   <!--주문 수량-->
                            <td>비고</td>
                        </tr>
                        ${forHTML}
                        `;
        forHTML = ''; // 내부 html 누적 초기화
    });

    dynamicTable.innerHTML = HTML_STRING;

    // 동적 생성 요소에 관한 /* DOCUMENT 변수명 */ 및 /* EVENT 함수 */ TODO 따로 함수로 빼는 것이 조금 더 코드를 보기가 깔끔할 듯 하다.
    bndlBtns = document.querySelectorAll('.dlvar-id__checkBox'); // check box 선택

    bndlBtns.forEach((bndlBtn) => {
        bndlBtn.addEventListener("click",
            event => selectBNDL(event, 'dlvar_id') // todo - checkbox.js method명 변경 필요
        );
    }); // 상품 선택 이벤트
    dlvarInit();
}

/* function */
//
// // 송장번호 저장 function
// async function handleClickInvoiceBtn() {
//     console.log("--------------- handleClickInvoiceBtn 시작 --------------"); // js에서 값 넘겨받는거는 확인 완료
//     const invoiceDeliveryFeeInfoList = []; // 객체들을 담을 배열
//
//     console.log(invoiceDeliveryFeeInfoList);
//     console.log('select_seq_list = ' + SELECT_SEQ_LIST);
//     let validationNum = 0;
//     console.log('validationNum : ' + validationNum);
//
//     // 1. 먼저 select_seql_list의 내용중에서 select, 송장번호, 배송비를 가지고 온다. -> list로 담는다.
//     // 2. fetch로 값을 보낸다. - 공유 fetch 사용 [[select list] [{객체}{객체}{객체}]]
//     SELECT_SEQ_LIST.forEach(selectSeq => {
//             const dlvarInfo = document.querySelector(`tr[ord_id="${selectSeq}"] td:nth-child(7)`);
//             console.log('dlvarInfo' + dlvarInfo);
//             const dlvarFee = document.querySelector(`tr[ord_id="${selectSeq}"] td:nth-child(8) input`).value;
//             console.log('dlvarFee' + dlvarFee);
//             const dlvarVend = dlvarInfo.querySelector('select').value;
//             console.log('dlvarVend' + dlvarVend);
//             const invoiceNum = dlvarInfo.querySelector('input').value;
//             console.log('dlvarVend' + dlvarVend);
//             console.log('invoiceNum' + invoiceNum);
//             if (dlvarVend === '' || invoiceNum === '' || dlvarFee === '') validationNum++; // 임시 검증. 빈 값일 경우 숫자 넣어줌
//             // 객체 생성 및 배열에 추가
//             const invoiceDeliveryFeeInfo = new InvoiceDeliveryFeeInfo(selectSeq, dlvarVend, invoiceNum, dlvarFee);
//             invoiceDeliveryFeeInfoList.push(invoiceDeliveryFeeInfo);
//             console.log('invoiceDeliveryFeeInfoList = ' + invoiceDeliveryFeeInfoList);
//         }
//     );
//     console.log('validationNum' + validationNum); // validationNum의 값 확인
//     console.log(invoiceDeliveryFeeInfoList); // [InvoiceDeliveryFeeInfo, InvoiceDeliveryFeeInfo] {selectSeq: 202307142397, dlvarVend: 'ezmeal', invoiceNum: '', dlvarFee: undefined}
//     if (validationNum > 0) {
//         alert("송장번호 및 배송비를 작성 해야합니다.")
//         return;
//     }
//     console.log("--------------- handleClickInvoiceBtn 끝 --------------");
//
//     // todo . fetch 수행
//     console.log("--------------- handleClickInvoiceBtn fetch 시작 --------------");
//     // const promise = await updateAdminSubmitBtn('/admin/delivery/invoice', invoiceDeliveryFeeInfoList);
//     // console.log(promise); // 정상동작 확인
//     // TODO fix: hadnleClickAdminDeliveryBtn을 할 경우에만 중복 문제가 발생 - 동일내용으로 중복됨
//     await handleClickAdminDeliveryBtn('/admin/delivery/invoice', '/admin/delivery', invoiceDeliveryFeeInfoList, '송장번호 등록 성공');
//     console.log("--------------- handleClickInvoiceBtn fetch 끝 --------------");
//
// }
//
// // 묶음 선택 table 내부 btn function
// async function handleBundleUpdate(event) {
//     // {ord_id : value, dlvar_id : value};
//     console.log('-------------------------------');
//     console.log('--------------handleBundleUpdate 시작-----------------');
//     const attribute = event.target.getAttribute('ord_id');
//     const bundleInfoData = new BundleInfoData([attribute], DLVAR_SEQ_LIST);
//     const updateBundleResult = await updateAdminSubmitBtn('/admin/delivery/bundle', bundleInfoData);
//     if (updateBundleResult === 'success') alert('묶음배송 등록 완료');
//     else alert('묶음배송 등록 실패');
//     // check box 수동 초기화
//     console.log('--------------handleBundleUpdate 끝-----------------');
// }
//
// async function handleClickDeliveryShipping() {
//     console.log('-------------------------------');
//     console.log('--------------handleClickDeliveryShipping 시작-----------------');
//     await handleClickAdminDeliveryBtn('/admin/delivery/shipping', '/admin/delivery', SELECT_SEQ_LIST, '배송중 등록 완료');
//     console.log('--------------handleClickDeliveryShipping 끝-----------------');
//
// }

/* EVENT 함수 */

// 처음 html loading 후, 바로 수행되는 함수, html 문서 load 된 후 실행되는 js 함수
document.addEventListener('DOMContentLoaded',
    (event) => firstRenderData('/admin/delivery/ship', event)
);

// due btn 누를 경우 , dynamic 수행
periodBtnAll.forEach((periodBtn) => {
    periodBtn.addEventListener('click',
        (event) => handlePeriodAndRender(event, '/admin/delivery/ship')
    );
})

// 전체 선택 버튼 누를 경우
bndlAllBtn.addEventListener("click",
    (event) => selectAllBNDL('dlvar_id')
);

// bndlAllBtn.addEventListener('click',
//     () => selectAllBNDL('dlvar_id')
// );
//
// addInvoiceNum.addEventListener("click", handleClickInvoiceBtn); // 송장번호 등록 btn
// checkDeliveryShipping.addEventListener('click', handleClickDeliveryShipping); // 배송중 등록 btn
//
// /* 객체 */
//
// // 송장번호 등록에 사용하는 객체
// class InvoiceDeliveryFeeInfo {
//     constructor(ordId, dlvarVend, invoiceNum, dlvarExpense) {
//         this.ordId = ordId;
//         this.dlvarVend = dlvarVend;
//         this.invoiceNum = invoiceNum;
//         this.dlvarExpense = dlvarExpense;
//     }
// }
//
// // 묶음 update에 사용하는 객체
// class BundleInfoData {
//     constructor(ord_id, dlvar_id) {
//         this.ord_id = ord_id;
//         this.dlvar_id = dlvar_id;
//     }
// }
