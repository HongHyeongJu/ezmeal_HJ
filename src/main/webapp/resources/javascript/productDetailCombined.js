
window.onload = function() {
    /*페이지 로드 시 */
    makeReviewAvgStarImg();   /*리뷰 별점 이미지 출력*/
    someFunction();
    imgFunction();   /*이미지 교체 기능 넣기*/
    updatePrice();   /*기본으로 가격 업데이트*/
    changeMainImage(document.getElementById("main_img"))

    /*선택한 옵션으로 바로 가격 바뀌도록*/
    let optSelect = document.getElementById("opt_select");

    optSelect.addEventListener('change', function() {
        updatePrice();
    });


    /*------------   호버    [찜하기] [장바구니] [주문하기]   호버   --------*/
    let buttons = document.querySelectorAll('.wishlist_btn, .addcart_btn, .order_btn');
    buttons.forEach(addHover);

}


/*---------- 찜하기 기능 ------*/

document.querySelectorAll('.wishlist_btn').forEach((button) => {
    button.addEventListener('click', function() {

        /* 상품코드 가져오기 */
        let prodCdElement = document.getElementById("prod_cd_is");
        let prod_cd = prodCdElement.dataset.value;

        $.ajax({
            url: "/wishlist/add",
            type: "POST",
            data: JSON.stringify({ prod_cd: prod_cd }),
            contentType: "application/json",
            dataType: "json", // 서버 응답이 JSON 형식임을 명시적으로 지정
            success: function (response) {
                alert(response); // 서버에서 보낸 메시지를 출력
            },
            error: function (xhr, status, error) {
                alert(xhr.responseText); // 서버에서 보낸 에러 메시지를 출력
            }
        });
    });
});

/*---------- 장바구니 넣기 기능 ------*/

document.querySelectorAll('.addcart_btn').forEach((button) => {
    button.addEventListener('click',   function() {

        /* 상품코드 가져오기 */
        let prodCdElement = document.getElementById("prod_cd_is");
        let prod_cd = prodCdElement.dataset.value;
        console.log("prod_cd 가져오기:"+prod_cd);

        /* 보관방법 가져오기 */
        let typElement = document.getElementById("typ_is");
        let typ = typElement.getAttribute('value');
        console.log("typ 가져오기:"+typ);

        /* 수량 가져오기 */
        let qtyElement = document.getElementById("qty");
        let qty = parseInt(qtyElement.textContent);
        console.log("qty 가져오기:"+qty);

        /* 옵션 상품 판별 */
        let is_opt = document.getElementById("is_opt");
        let isOpt = parseInt(is_opt.dataset.value) > 0; /*div는 그냥 value는 없다 조심!*/
        console.log("isOpt 은?:"+isOpt);

        if(!isOpt){
            /* 일반 상품일 때 */
            $.ajax({
                url: "/cart/add",
                type: "POST",
                data: JSON.stringify({
                    prod_cd: prod_cd,
                    typ: typ,
                    qty: qty
                }),
                contentType: "application/json",
                success: function(response) {
                    alert("일반상품 장바구니 담기 성공!");
                },
                error: function(xhr, status, error) {
                    alert("일반상품 장바구니 담기 실패!");
                }
            });

        } else {
            /* 옵션 상품일 때 */
            /* 옵션 ul값 다 찾아오기 */
            let ulTags = document.getElementsByClassName('make_ul');

            let cartProductDto = [];  // 배열 선언 추가

            for (let ul of ulTags) {
                let optSeq = parseInt(ul.dataset.value.split('_')[4]);
                let optQty = parseInt(ul.dataset.value.split('_')[2]);
                console.log("optSeq:"+optSeq);
                console.log("optQty:"+optQty);

                // 각 옵션 상품을 객체로 만들어 배열에 넣기
                cartProductDto.push({
                    prod_cd: prod_cd,
                    opt_seq: optSeq,
                    typ: typ,
                    qty: optQty
                });
            }

            // 배열을 서버로 보내기
            $.ajax({
                url: "/cart/adds",
                type: "POST",
                data: JSON.stringify(cartProductDto),
                contentType: "application/json",
                success: function(response) {
                    alert("옵션상품 장바구니 담기 성공!");
                },
                error: function(xhr, status, error) {
                    alert("옵션상품 장바구니 담기 실패!");
                }
            });

        }

    });
});


function addHover(button) {
    button.addEventListener('mouseenter', function() {
        button.classList.add('hover');
    });

    button.addEventListener('mouseleave', function() {
        button.classList.remove('hover');
    });
}

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
function someFunction() {
    let calculationBtn = document.getElementById('calculation_btn');
    if (calculationBtn !== null) {
        calculationBtn.addEventListener('click', function() {
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

            updateLastPrice();
        });
    }

    // 나머지 코드...
}




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

/*-----------병합----------------*/
/*-----------병합----------------*/


/*-----------메인 이미지--------------*/

function imgFunction() {
    /*미니이미지 다가져오기*/
    let mini_imgs = document.getElementsByClassName('mini_img');
    /*반복문으로 함수 집어넣기*/
    for (let img of mini_imgs) {
        img.addEventListener('click', function() {
            changeMainImage(this);
        });
    }

}

/*상품 이미지 수정 함수*/
function changeMainImage(img) {
    /* 현재 메인 이미지 소스 가져오기 */
    let mainImage = document.querySelector('.main_img');
    let mainSrc = mainImage.src;

    /* 클릭한 이미지의 소스 저장 */
    let newSrc = img.src;

    /* 둘이 바꾸기 */
    mainImage.src = newSrc;
    img.src = mainSrc;
}


/*------------[찜하기] [장바구니] [주문하기...는 나중에]--------*/




/*-----------병합----------------*/
/*-----------병합----------------*/



/*------ 상품 이름 아래에 별점 출력하기 ---------*/

/* class stars를 받아온다.
* 정수만큼 클래스에  star_1_img를 추가하고 hidden속성을 false로 바꾸기 document.getElementById('star1').hidden = false; */

/* 0.5가 있는 경우 1개만 star_1_img를 추가해주기.  hidden속성을 false로 바꾸기 */
function makeReviewAvgStarImg () {
    /*별점 태그 가져오기*/
    let divTag = document.getElementsByClassName('stars_set')[0];

    /*별점 평균 가져오기*/
    let reviewAvg = document.getElementById("reviewAvg");
    let avg = parseFloat(reviewAvg.dataset.avg);

    /*별점 평균이 0보다 큰 경우에만 태그 생성*/
    if (avg > 0) {
        /*별점평균만큼 태그 집어넣기*/
        let halfStar = (avg % 1 !== 0) ? true : false;

        /*정수만큼 출력*/
        for(let j = 1; j <= avg; j++){
            let starImg = document.createElement('span');
            starImg.className = 'star_1_img';
            divTag.appendChild(starImg);
        }
        /*소수점이 있을 때*/
        if(halfStar){
            let halfStarImg = document.createElement('span');
            halfStarImg.className = 'star_0_5_img';
            divTag.appendChild(halfStarImg);
        }
    } else { // 별점 평균이 0일 때
        let zeroStarImg = document.createElement('span');
        zeroStarImg.className = 'star_0_img';
        divTag.appendChild(zeroStarImg);
    }
}



window.addEventListener('DOMContentLoaded', (event) => {

    /*-----------------------------------------------  상품 문의 내용보기 스크립트  --------------------------*/
    const accordions = document.querySelectorAll('.accordion');

    accordions.forEach((accordion) => {
        accordion.addEventListener('click', () => {
            const answer = accordion.parentElement.nextElementSibling.querySelector('.answer');
            if (answer.style.maxHeight) {
                answer.style.maxHeight = null;
                answer.style.marginTop = '0';
                answer.style.paddingBottom = '0';
            } else {
                answer.style.maxHeight = answer.scrollHeight + 'px';
                answer.style.marginTop = '20px';
                answer.style.paddingBottom = '20px';
            }
        });
    });

    /*-------------------------------------  상품 후기 이미지 확대 스크립트  ---------------------------------*/

    const imgWrappers = document.querySelectorAll('.rv_img');
    const rvStmts = document.querySelectorAll('.rv_stmt');
    let originalMarginTop = '';

    imgWrappers.forEach((imgWrapper, index) => {
        const rvStmt = imgWrapper.previousElementSibling; // 이전 형제 요소인 .rv_stmt 선택

        imgWrapper.addEventListener('click', () => {
            imgWrapper.classList.toggle('active');

            if (imgWrapper.classList.contains('active')) {
                originalMarginTop = rvStmt.style.marginTop; // 원래의 margin-top 값 저장
                rvStmt.style.width = '100%'; // 이미지 확대시 rv_stmt의 width 값을 조정
            } else {
                rvStmt.style.width = '80%'; // 원래의 width 값으로 복원
            }
        });
    });

});



