


// 옵션 리스트를 추가할 DOM 요소를 가져옵니다. 옵션 div
let optionContainer = document.querySelector('.make_option_ulli');

// '옵션 생성하기' 버튼을 가져옵니다.  + 옵션 생성하기 버튼
let addButton = document.querySelector('.make_opt_btn');

// '옵션 생성하기' 버튼을 클릭했을 때의 이벤트 리스너를 추가합니다.
addButton.addEventListener('click', function() {

    //기본 제출기능 막기
    event.preventDefault();

    // 새로운 옵션 리스트 ul
    let newOption = document.createElement('ul');
    newOption.className = 'opt_list_ul';

    /* [1] 옵션명  */
    let nameLi = document.createElement('li');
    let nameSpan = document.createElement('span');
    nameSpan.textContent = '옵션명';
    let nameInput = document.createElement('input');
    nameInput.type = 'text';
    nameInput.name = `options[${optionContainer.children.length}].name`;
    nameLi.appendChild(nameSpan);
    nameLi.appendChild(nameInput);
    newOption.appendChild(nameLi);

    /* [2] 옵션수량  */
    let qtyLi = document.createElement('li');
    let qtySpan = document.createElement('span');
    qtySpan.textContent = '옵션수량';
    let qtyInput = document.createElement('input');
    qtyInput.type = 'number';
    qtyInput.name = `options[${optionContainer.children.length}].qty`;
    qtyInput.min = '0';
    qtyLi.appendChild(qtySpan);
    qtyLi.appendChild(qtyInput);
    newOption.appendChild(qtyLi);

    /* [3] 할인코드  */
    let dcCdLi = document.createElement('li');
    let dcCdSpan = document.createElement('span');
    dcCdSpan.textContent = '할인코드';
    let dcCdSelect = document.createElement('select');
    dcCdSelect.name = `options[${optionContainer.children.length}].dc_cd`;

    // JSP의 select 태그에서 옵션들을 가져옵니다.
    let select = document.querySelector('#dc_cd');
    let options = select.options;

    for(let i=0; i<options.length; i++) {
        let option = options[i].cloneNode(true); // cloneNode를 사용하여 각 옵션을 복사합니다.
        dcCdSelect.appendChild(option);
    }
    dcCdLi.appendChild(dcCdSpan);
    dcCdLi.appendChild(dcCdSelect);
    newOption.appendChild(dcCdLi);

    /* [4] 소비자가  */
    let cnsmrPrcLi = document.createElement('li');
    let cnsmrPrcSpan = document.createElement('span');
    cnsmrPrcSpan.textContent = '소비자가';
    let cnsmrPrcInput = document.createElement('input');
    cnsmrPrcInput.type = 'number';
    cnsmrPrcInput.name = `options[${optionContainer.children.length}].cnsmr_prc`;
    cnsmrPrcInput.value = '';
    cnsmrPrcInput.min = '0';
    cnsmrPrcLi.appendChild(cnsmrPrcSpan);
    cnsmrPrcLi.appendChild(cnsmrPrcInput);
    newOption.appendChild(cnsmrPrcLi);

    /* [5] 판매가  */
    let salePrcLi = document.createElement('li');
    let salePrcSpan = document.createElement('span');
    salePrcSpan.textContent = '판매가';
    let salePrcInput = document.createElement('input');
    salePrcInput.type = 'number';
    salePrcInput.name = `options[${optionContainer.children.length}].sale_prc`;
    salePrcInput.value = '';
    salePrcInput.min = '0';
    salePrcLi.appendChild(salePrcSpan);
    salePrcLi.appendChild(salePrcInput);
    newOption.appendChild(salePrcLi);

    /* [6] 할인율  */
    let optDcPerLi = document.createElement('li');
    let optDcPerSpan = document.createElement('span');
    optDcPerSpan.textContent = '할인율';
    let optDcPerInput = document.createElement('input');
    optDcPerInput.type = 'number';
    optDcPerInput.name = 'opt_dc_per';
    optDcPerInput.value = '';
    optDcPerInput.min = '0';
    optDcPerLi.appendChild(optDcPerSpan);
    optDcPerLi.appendChild(optDcPerInput);
    newOption.appendChild(optDcPerLi);

    /* [7] 비고, x버튼  */
    let rmkLi = document.createElement('li');
    rmkLi.className = 'last_li';
    let rmkSpan = document.createElement('span');
    rmkSpan.className = 'span_rmk';
    rmkSpan.textContent = '비고';
    let rmkTextarea = document.createElement('textarea');
    rmkTextarea.className = 'opt_rmk';
    rmkTextarea.name = `options[${optionContainer.children.length}].rmk`;
    let delButton = document.createElement('button');
    delButton.className = 'del_opt_btn';
    delButton.textContent = 'X';

    delButton.addEventListener('click', function() {
        // '삭제' 버튼을 누르면 해당 옵션 리스트 항목을 삭제합니다.
        this.parentNode.parentNode.removeChild(this.parentNode);
    });

    rmkLi.appendChild(rmkSpan);
    rmkLi.appendChild(rmkTextarea);
    rmkLi.appendChild(delButton);
    newOption.appendChild(rmkLi);

    // 만들어진 옵션 리스트 항목을 컨테이너에 추가합니다.
    optionContainer.appendChild(newOption);
});


/*-------- 가격 계산 --------*/
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

    salePrcInput.value = salePrc;
    optDcPerInput.value = (1 - salePrc / cnsmrPrc) * 100; // 할인율 = (1 - 판매가 / 소비자가) * 100
});
