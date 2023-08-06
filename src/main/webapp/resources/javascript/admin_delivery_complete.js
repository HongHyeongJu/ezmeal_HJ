/* DOCUMENT 변수명 */
const periodBtnAll = document.querySelectorAll(".admin__period_btn"); // due btn
const dynamicTable = document.querySelector('.admin-order__content-table > tbody'); // 동적 data 들어가는 table

const bndlAllBtn = document.querySelector('.dlvar-id__all-checkBox'); // order-id를 list에 담는 전체 선택 check-box
let bndlBtns; // dlvar_id를 list에 담는 전체 선택 check-box

const deliveryCompleteFixed = document.querySelector('.admin-order__check-order button');   // 구매확정 처리 btn


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
        const prod_cd = info.prod_cd ?? '';                 // 주문상품 pk               - 개별
        const qty = info.qty ?? '';                         // 주문상품 수량              - 개별

        const count = info.count ?? '';                     // 상품 중복수량
        const stus = info.stus ?? '';                       // 상품 상태                 - 개별

        // 1. 개별값 존재하는 colum 배열로 빼기 - prod_name, qty, tot_prc, setl_expct_prc, 비고

        function convertStringToArray(str) {
            return str.split(',').map(item => item.trim());
        } // 배열변경 내부 함수

        // prod_name, qty, tot_prc, setl_expct_prc 변수들을 배열로 변환
        const up_dtm_format_arr = convertStringToArray(up_dtm_format);
        const dlvar_id_arr = convertStringToArray(dlvar_id);
        const prod_name_arr = convertStringToArray(prod_name);
        const prod_cd_arr = convertStringToArray(prod_cd);
        const qty_arr = convertStringToArray(qty);
        const stus_arr = convertStringToArray(stus);

        // 2. 벡틱 내부에 for문 돌리기
        if (count > 1) {
            for (let i = 1; i < count; i++) {
                const up_dtm_format = up_dtm_format_arr[i];
                const dlvar_id = dlvar_id_arr[i];
                const prod_name = prod_name_arr[i];
                const prod_cd = prod_cd_arr[i];
                const currentQty = qty_arr[i];
                const stus = stus_arr[i];

                // 생성된 템플릿 문자열 추가
                forHTML += `
                            <tr>
                              <!-- 추가적인 필요한 코드 -->
                              <td>${stus === 'a3' ? '<span>구매확정</span>' : `<input type="checkbox" class="dlvar-id__checkBox" dlvar_id="${dlvar_id}" />`}</td> <!-- checkbox -->
                              <td>${up_dtm_format} / ${dlvar_id}</td> <!-- 배송일 / 배송번호-->
                              <td>${prod_name}</td> <!-- 상품명 -->
                              <td>${prod_cd}</td> <!-- 상품코드 -->
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
                            <td> ${stus_arr[0] === 'a3' ? '<span>구매확정</span>' : `<input type="checkbox" class="dlvar-id__checkBox" dlvar_id="${dlvar_id_arr[0]}" />`}</td> <!-- 묶음선택할 check box-->
                            <td> ${up_dtm_format_arr[0]} / ${dlvar_id_arr[0]}</td> <!-- 배송일 / 배송번호 -->                            
                            <td rowspan="${count}"> ${invc_id} </td> <!-- 운송장번호 -->
                            <td rowspan="${count}">${vend}</td>      <!-- 공급사 -->
                            <td>${prod_name_arr[0]}</td>             <!-- 주문 개별 상품명 -->
                            <td>${prod_cd_arr[0]}</td>                  <!--주문 수량-->
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


/* EVENT 함수 */

// 처음 html loading 후, 바로 수행되는 함수, html 문서 load 된 후 실행되는 js 함수
document.addEventListener('DOMContentLoaded',
    (event) => firstRenderData('/admin/delivery/complete', event)
);

// due btn 누를 경우 , dynamic 수행
periodBtnAll.forEach((periodBtn) => {
    periodBtn.addEventListener('click',
        (event) => handlePeriodAndRender(event, '/admin/delivery/ship')
    );
})

// 전체 선택 버튼 누를 경우
bndlAllBtn.addEventListener("click", () => selectAllBNDL('dlvar_id'));

// 구매확정 처리
deliveryCompleteFixed.addEventListener('click',
    () => handleClickAdminDeliveryBtn('/admin/delivery/complete/fixed', '/admin/delivery/complete', DLVAR_SEQ_LIST, '구매확정 완료')
);