
/* 상품 수량 감소 */
function decreaseQuantity(btn) {
    let qtyElement = btn.nextElementSibling;
    let qty = parseInt(qtyElement.textContent);
    if (qty > 1) {
        qtyElement.textContent = qty - 1;
    }
    updatePrice();
}

/* 상품 수량 증가 */
function increaseQuantity(btn) {
    let qtyElement = btn.previousElementSibling;
    let qty = parseInt(qtyElement.textContent);
    qtyElement.textContent = qty + 1;
    updatePrice();
}


/* 상품 수량에 따른 가격 업데이트 */
function updatePrice() {
    /*수량*/
    let qtyElement = document.getElementById("qty");
    let qty = parseInt(qtyElement.textContent);
    /*단일상품 가격*/
    let priceElement = document.getElementById("prod_sale_prc");
    let salePrc = parseInt(priceElement.value);
    /*계산된 값 보여줄 태그 - 선택버튼 옆*/
    let amountElement = document.getElementById("amount_by_qty_x_price");
    /*계산된 값 보여줄 태그 - 주문하기 위(노 옵션시 만)*/
    let lastamountElement = document.getElementById("order_amount");
    /*옵션 있는지 여부*/
    let is_opt = document.getElementById("is_opt");
    let isOpt = parseInt(is_opt.dataset.value) > 0; /*div는 그냥 value는 없다 조심!*/
    /*계산 가격*/
    console.log("isOpt: "+isOpt);
    let amount = salePrc * qty;
    console.log("amount: "+amount);

    if(isOpt){
        /*옵션상품 가격*/
        let optSelect = document.getElementById("opt_select");  /*선택된 옵션 Element*/
        let optSalePrc = parseInt(optSelect.value.split('_')[1]); /*옵션 가격*/
        let optAmount = optSalePrc*qty;
        amountElement.textContent = optAmount.toLocaleString('ko-KR');
    } else {
        amountElement.textContent = amount.toLocaleString('ko-KR');
        lastamountElement.textContent = amount.toLocaleString('ko-KR');
        console.log("lastamountElement.textContent: "+lastamountElement.textContent);
    }

}



/* 옵션 선택 후 '선택' 버튼 클릭 시, 선택한 옵션 및 수량에 대한 리스트 생성 */
document.getElementById('calculation_btn').addEventListener('click', function() {
    /* 옵션 셀렉트 Element로부터 값을 얻어온다.
    * 얻어올 것: full_name, qty, 계산값, opt_seq */
    let optSelect = document.getElementById('opt_select');
    let selectedOptionFullName = optSelect.options[optSelect.selectedIndex].text;
    let selectedOptvalue = optSelect.options[optSelect.selectedIndex].value;
    let selectedOptSeq = parseInt(selectedOptvalue.split('_')[0]);
    let qty = parseInt(document.getElementById('qty').textContent);
    let selectedOptPrc = parseInt(selectedOptvalue.split('_')[1]);

    /*만들것: ul(id opt_seq 이용해서 고유하게)
    * li: full_name + * + qty
    * li: 계산값
    * input: hidden으로 id, value opt_seq
    * button: id opt_seq로 고유하게*/

    let ul = document.createElement('ul');
    ul.className = "make_ul"
    ul.dataset.value = "make_ul_"+ qty + "_" + selectedOptPrc + "_" + selectedOptSeq;

    /*만들것: <li class="choice_opt">어쩌구저쩌구 불닭볶음밥 20개 세트 * 2 </li>*/
    let liChoice = document.createElement('li');
    liChoice.className = "choice_opt";
    liChoice.textContent = selectedOptionFullName + "  *  " + qty + "개";
    liChoice.value = qty; /*장바구니에 넘길 수량*/

    /*만들것: <li class="price_opt">40,000원 </li>*/
    let liPrice = document.createElement('li');
    liPrice.className = "price_opt";
    liPrice.textContent = (selectedOptPrc * qty).toLocaleString('ko-KR') + "원";

    /*만들것: <input type="hidden" id="hidden_opt_seq" value="옵션시퀀스"/>*/
    let inputHidden = document.createElement('input');
    inputHidden.className = "hidden_opt_seq";
    inputHidden.type = 'hidden';
    inputHidden.id = "hidden_opt_seq_" + selectedOptSeq;
    inputHidden.value = selectedOptSeq; /*장바구니에 넘길 opt_seq*/

    /*만들것: <button type="button" class="del_btn" id="del_btn_옵션시퀀스">X</button>*/
    let delButton = document.createElement('button');
    delButton.className = 'del_btn';
    delButton.id = "del_btn_" + selectedOptSeq;
    delButton.textContent = 'X';

    ul.appendChild(liChoice);
    ul.appendChild(liPrice);
    ul.appendChild(inputHidden);
    ul.appendChild(delButton);

    /*del_btn_(+opt_seq로) 누르면 해당 ul outerHtml 삭제삭제*/
    delButton.addEventListener('click', function() {
        this.parentNode.outerHTML = '';
        updateLastPrice()
    });
    document.querySelector('.make_li').appendChild(ul);

    updatePrice();
    updateLastPrice();  // 최종 금액 업데이트
});


/*className이 make_ul 인 것을 모두 찾아온다.
*최종가격 태그 변수를 받아온다.
* 반복문으로 id를 꺼내서 2번째과 3번째를 곱한 가격을 누적하여 보여준다.
* */


/* 옵션에 따른 최종 가격 업데이트 */
function updateLastPrice() {
    let ulTags = document.getElementsByClassName('make_ul'); // 가격을 나타내는 모든 요소 선택
    let totalAmount = 0;

    for (let ul of ulTags) {
        let qty = parseInt(ul.dataset.value.split('_')[2]);
        let prc = parseInt(ul.dataset.value.split('_')[3]);
        totalAmount += (qty*prc);
    }

    /*태그 받아오기*/
    let lastamountElement = document.getElementById("order_amount_opt");

    /* 최종 금액 업데이트 */
    lastamountElement.textContent = totalAmount.toLocaleString('ko-KR');
}

