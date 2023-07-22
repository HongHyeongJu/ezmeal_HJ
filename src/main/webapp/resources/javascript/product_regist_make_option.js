
// 할인코드 outerHTML 받아오기
const selectElement = document.getElementById('dc_cd');
const selectInnerHTML = selectElement.innerHTML;

// 메인제품 소비자가 받아오기
let mainProductCnsmrPrc = document.querySelector('#cnsmr_prc');

// 옵션 리스트를 추가할 DOM 요소를 가져옵니다. 옵션 div
let optionContainer = document.querySelector('.make_option_ulli');

// '옵션 생성하기' 버튼을 가져옵니다.  + 옵션 생성하기 버튼
let addButton = document.querySelector('.make_opt_btn');

// input 요소 가져오기
const optListSizeInput = document.getElementById('optList_size_count');

// input 요소의 value 가져오기
const optListSizeValue = optListSizeInput.value;

/*옵션 인덱스 시작 번호*/
let optionCounter = optListSizeValue==0 ? 0 : optListSizeValue+1 // 옵션의 고유 번호를 저장하는 카운터


addButton.addEventListener('click', function(event) {
    event.preventDefault();

    let optionNumber = optionCounter++; // 현재 옵션의 고유 번호를 가져오고, 카운터를 증가시킵니다.

    // 백틱을 이용하여 HTML 문자열을 만듭니다.
    let newOption = `
    <ul class="opt_list_ul" data-id="${optionNumber}">
        <li>
            <span>옵션명</span>
            <input type="text" name="options[${optionNumber}].name">
        </li>
        <li>
            <span>옵션수량</span>
            <input type="number" name="options[${optionNumber}].qty" min="0" value="0">
        </li>
        <li>
            <span>할인코드</span>
            <select name="options[${optionNumber}].dc_cd">
               ${selectInnerHTML}
            </select>         
        </li>
        <li>
            <span>소비자가</span>
            <input type="number" name="options[${optionNumber}].cnsmr_prc" min="0" disabled>
        </li>
        <li>
            <span>판매가</span>
            <input type="number" name="options[${optionNumber}].sale_prc" min="0" disabled value="0">
        </li>
        <li>
            <span>할인율</span>
            <input type="number" name="options[${optionNumber}].opt_dc_per" min="0" disabled value="0">
        </li>
        <li class="last_li">
            <span class="span_rmk">비고</span>
            <textarea class="opt_rmk" name="options[${optionNumber}].rmk"></textarea>
            <button class="del_opt_btn">X</button>
        </li>
    </ul>
    `;

    // 만들어진 HTML 문자열을 컨테이너에 추가합니다.
    optionContainer.insertAdjacentHTML('beforeend', newOption);

    // '삭제' 버튼에 이벤트 리스너를 추가합니다.
    document.querySelector(`.opt_list_ul[data-id="${optionNumber}"] .del_opt_btn`).addEventListener('click', function(event) {
        event.preventDefault();
        this.closest('ul').remove();
    });

    // '옵션수량' 필드에 이벤트 리스너를 추가합니다.
    document.querySelector(`.opt_list_ul[data-id="${optionNumber}"] input[name="options[${optionNumber}].qty"]`).addEventListener('change', function(event) {
        let qty = parseInt(this.value);
        let cnsmrPrc = parseFloat(mainProductCnsmrPrc.value);

        if (!isNaN(qty) && !isNaN(cnsmrPrc)) {
            let optionCnsmrPrc = qty * cnsmrPrc;

            // 계산된 값을 해당 옵션의 '소비자가' 필드에 설정합니다.
            document.querySelector(`.opt_list_ul[data-id="${optionNumber}"] input[name="options[${optionNumber}].cnsmr_prc"]`).value = optionCnsmrPrc;
        }
    });


// 할인코드 필드에 이벤트 리스너를 추가합니다.
    document.querySelector(`.opt_list_ul[data-id="${optionNumber}"] select[name="options[${optionNumber}].dc_cd"]`).addEventListener('change', function(event) {
        let selectedOption = this.options[this.selectedIndex];
        let discountType = selectedOption.getAttribute('data-discount-type');
        let discountRate = parseFloat(selectedOption.getAttribute('data-discount-rate')) || 0;
        let discountPrc = parseFloat(selectedOption.getAttribute('data-discount-prc')) || 0;

        // 필요한 값을 가져오기
        let cnsmr_prc = parseFloat(document.querySelector(`.opt_list_ul[data-id="${optionNumber}"] input[name="options[${optionNumber}].cnsmr_prc"]`).value) || 0;

        // 할인된 판매가를 계산합니다.
        let sale_prc = 0;
        if (discountType === 'prc') {
            sale_prc = cnsmr_prc - discountPrc;
        } else if (discountType === 'pt') {
            sale_prc = cnsmr_prc - (cnsmr_prc * (discountRate / 100));
        }

        // 계산된 값을 해당 옵션의 '판매가' 필드에 설정합니다.
        document.querySelector(`.opt_list_ul[data-id="${optionNumber}"] input[name="options[${optionNumber}].sale_prc"]`).value = Math.round(sale_prc);

        // 여기에서 할인율을 계산합니다.
        const optDcPerInput = document.querySelector(`.opt_list_ul[data-id="${optionNumber}"] input[name="options[${optionNumber}].opt_dc_per"]`);

        let opt_Sc_Per = cnsmr_prc !== 0 ? ((cnsmr_prc - sale_prc) / cnsmr_prc) * 100 : 0;

        optDcPerInput.value = Math.floor(opt_Sc_Per);
    });




});

/*-------- 옵션에서 소비자가, 판매가로 할인율 계산 --------*/
// optionContainer에 이벤트 리스너를 추가합니다.
optionContainer.addEventListener('change', function(event) {
    // '소비자가'나 '판매가' input 필드에서 이벤트가 발생했는지 확인합니다.
    if (event.target.name.endsWith('.cnsmr_prc') || event.target.name.endsWith('.sale_prc')) {
        // 옵션 번호를 가져옵니다.
        const optionNumber = event.target.name.match(/options\[(\d+)\]/)[1];

        // '소비자가', '판매가', '할인율' input 필드를 가져옵니다.
        const cnsmrPrcInput = document.querySelector(`.opt_list_ul[data-id="${optionNumber}"] input[name="options[${optionNumber}].cnsmr_prc"]`);
        const salePrcInput = document.querySelector(`.opt_list_ul[data-id="${optionNumber}"] input[name="options[${optionNumber}].sale_prc"]`);
        const optDcPerInput = document.querySelector(`.opt_list_ul[data-id="${optionNumber}"] input[name="options[${optionNumber}].opt_dc_per"]`);

        // 할인율을 계산합니다.
        const opt_Cnsmr_Prc = parseFloat(cnsmrPrcInput.value);
        const opt_Sale_Prc = parseFloat(salePrcInput.value);

        let opt_Sc_Per = opt_Cnsmr_Prc !== 0 ? ((opt_Cnsmr_Prc - opt_Sale_Prc) / opt_Cnsmr_Prc) * 100 : 0;

        optDcPerInput.value = Math.floor(opt_Sc_Per);

    }
});









/*-------- 메인 가격 자동 계산 --------*/

const qtyInput = document.querySelector('#qtyInput');
const dcCdSelect = document.querySelector('#dcCdSelect');
const cnsmrPrcInput = document.querySelector('#cnsmrPrcInput');
const salePrcInput = document.querySelector('#salePrcInput');
const optDcPerInput = document.querySelector('#optDcPerInput');

const productCnsmrPrcInput = document.querySelector('#cnsmr_prc');



qtyInput.addEventListener('change', function() {
    let cnsmrPrc = document.querySelector('#cnsmr_prc').value;
    cnsmrPrcInput.value = this.value * cnsmrPrc; // 소비자가 = 옵션수량 * 상품의 소비자가
});


dcCdSelect.addEventListener('change', function() {
    let selectedOption = this.options[this.selectedIndex];
    let discountType = selectedOption.getAttribute('data-discount-type');
    let discountRate = selectedOption.getAttribute('data-discount-rate');
    let discountPrc = selectedOption.getAttribute('data-discount-prc');

    let cnsmrPrc = cnsmrPrcInput.value;
    let salePrc = cnsmrPrc;

    if (discountType === 'rate') {
        salePrc = cnsmrPrc - cnsmrPrc * (discountRate / 100);
    } else if (discountType === 'prc') {
        salePrc = cnsmrPrc - discountPrc;
    }

    salePrcInput.value = Math.round(salePrc);
    optDcPerInput.value = Math.round((1 - salePrc / cnsmrPrc) * 100);

});
