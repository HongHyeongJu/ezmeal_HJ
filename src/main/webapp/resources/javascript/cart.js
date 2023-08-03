/* DOM 가져오기 */

const selectAllBtn = document.querySelector(".cart__items_nav__checkbox"); // 전체 선택
const selectBtns = document.querySelectorAll(".cart__item_nav__checkbox"); // 선택

// todo. 일단은 수량 추가되는 부분만 js로 수행, 상품 삭제 같은 부분은 3차 때 동적 html로 변경시 동적으로 변경되도록 수행할 것임.
const countSelectNum = document.querySelector('.cart__items_selected_num') // 좌상단 선택된 상품수량 개수

const bannerPrice = document.querySelectorAll(".payment__prod span:last-child") // 배너 가격

const deleteBtns = document.querySelectorAll(".cart__delete_btn"); // 삭제 표시 버튼
const deleteAllBtn = document.querySelector(".cart__items_nav__btn_rm"); // 선택상품 삭제
const quantityDivs = document.querySelectorAll('.cart__item__btn'); // 수량 관련 div

const orderBtn = document.querySelector('.payment-detail__btn'); // 상품 주문 버튼

let CART_PROD_SEQ_LIST = []; // 선택된 장바구니 상품 pk
let dynamicNum = 0; // 체크박스 선택 수 확인
let debounceInitTime = null; // 서버 부하 방지

/* REST API 함수 */

// 재고 수량 파악 함수
const inventoryValidation = function () {
    return fetch("/cart/inventoryValidation", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        // 요청 보내는 경우, 형식을 지켜줘야함
        body: JSON.stringify(CART_PROD_SEQ_LIST)
    })
        .then(response => {
            if (response.ok) return response.json();
            else throw new Error('Error: ' + response.status);
        })
        .then(data => {
            console.log(data);  // [] | [Array(2), Array(2), Array(2)] -> 0:(2) [4, 2] 1:(2) [10, 2] 2:(2) [11, 2]
            const length = data.length;
            console.log(length);
            if (length === 0) {
                return;
            }
            if (length !== 0) {
                alert("재고부족 상품이 있습니다.\n옵션을 가진 동일 상품이 여러개 존재시, 개별재고가 아닌 전체 재고를 고지합니다.")
                data.forEach(arr => {
                    const seq = arr[0];
                    const currInv = arr[1];
                    const selector = `li[prod_cd="${seq}"]`;
                    const cartLiElement = document.querySelectorAll(selector);
                    cartLiElement.forEach(cartLi => {
                        const quantityElement = cartLi.querySelector('.cart__item__btn'); // 상품 수량
                        quantityElement.style.border = "2px solid red";
                    })
                    // TODO JS 해당 수량 div 쪽 (quantityElement)에 currInv 최대 수량을 고지해줘야함
                });
                throw new Error('재고부족 상품이 있습니다.');
            }
        })
}

// 주문서 이동 함수
const moveOrderPage = function () {
    fetch("/cart/select", {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(CART_PROD_SEQ_LIST)
    })
        .then(response => {
            if (response.ok) return response.text();
            else throw new Error('Error: ' + response.status);
        })
        .then(data => {
            console.log(data)
        })
        .catch(error => {
            console.error('Error:', error);
        });
    window.location.href = "/order";
}

// 품절상품 검증기
const soldOutValidation = function () {
    return fetch("/cart/soldOutValidation", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(CART_PROD_SEQ_LIST)
    })
        .then(response => {
            if (response.ok) return response.text(); // array로 반환
            else throw new Error('Error: ' + response.status);
        })
        .then(data => {
            if (data !== 'success') {
                alert("품절상품이 있습니다.")
                location.reload();
                throw new Error('품절상품이 있습니다.');
            }
        });
}

// db 검증기
const validation = function (cartProdSeqList) {
    return fetch("/cart/validation", {
        method: 'POST',
        headers: {
            // content-type 요청 보내기 | accept 응답 받기
            'Content-Type': 'application/json',
        },
        // 요청 보내는 경우, 형식을 지켜줘야함
        body: JSON.stringify(cartProdSeqList)
    })
        .then(response => {
            if (response.ok) return response.text();
            else throw new Error('Error: ' + response.status);
        })
        .then(data => {
            if (data === "no_memberId") {
                alert("no_member. go to login");
                window.location.href = "/login";
                throw new Error('Validation Error: no_memberId');
            } else if (data === "wrong product") {
                alert("잘못된 상품 등록번호입니다.")
                throw new Error('Validation Error: no_memberId');
            }
        })
        .catch(error => {
            console.error('Validation Error:', error);
            throw new Error(error);
        });
}
// 상품 수량 db 업데이트
const updateProductQuantity = function (cartProdSeq_quantity_List) {
    return fetch('/cart/update', {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(cartProdSeq_quantity_List)
    })
        .then(response => {
            // 두 번째 요청의 응답 처리
            if (response.ok) {
                // 응답이 성공적인 경우
                return response.text();
            } else {
                throw new Error('fail response');
            }
        })
        .then(data => {
            if (data === "fail") {
                console.log('fail update quantity')
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}
// 결제 예상 가격 _ 그룹일 경우
const groupExpectSalePrice = function () { // 다중 선택시 사용하는 가격 측정 function
    let PRODUCT_PRICE = 0;
    let SALE_PRICE = 0;
    let EXPECTED_PRICE = 0;
    let EXPECTED_POINT = 0;

    CART_PROD_SEQ_LIST.forEach(seq => {
        const selector = `li[cart_prod_seq="${seq}"]`;
        const element = document.querySelector(selector); // 개별상품목록
        if (!element) return; // null인경우 예외 방지 -> !element = !null = true cf) 이렇게 안할거면 동적 html 수행 필요
        const cartItemPriceSpans = element.querySelectorAll(".cart__item_price > span"); // 상품 가격 [0]: 판매가 [1]: 소비자가
        console.log(cartItemPriceSpans);

        const quantity = parseInt(element.querySelector(".count_num").value); // 상품 수량

        const salePrice = parseInt(cartItemPriceSpans[0].textContent.replace(/,/g, '').replace("원", '')); // 1,000원 -> 1000원

        const productPrice = parseInt(cartItemPriceSpans[1].textContent.replace(/,/g, '').replace("point", ''));
        PRODUCT_PRICE += quantity * productPrice;
        SALE_PRICE += quantity * (productPrice - salePrice);
        EXPECTED_PRICE += quantity * salePrice;
        EXPECTED_POINT += quantity * (Math.floor(salePrice / 100));
    })

    bannerPrice[0].innerText = PRODUCT_PRICE.toLocaleString('ko-KR') + " 원";
    bannerPrice[1].innerText = SALE_PRICE.toLocaleString('ko-KR') + " 원";
    bannerPrice[2].innerText = EXPECTED_PRICE.toLocaleString('ko-KR') + " 원";
    bannerPrice[3].innerText = EXPECTED_POINT + " point";
}

// debounce _ db update
async function debounceUpdateQuantity(event) {
    if (debounceInitTime) {
        clearTimeout(debounceInitTime);
    }
    debounceInitTime = setTimeout(() => {
        console.log('working');
        updateCartProductQuantity(event)
    }, 500);
}

/* EVENT 함수 */

// 상품 수량 함수
function handleUpdateCartProductQuantity(event) {
    /* 중요
       event.target: 실제 event가 발생한 요소를 반환
       event.target.value : input, select, textarea 같은 form 요소일 경우 value를 가지고 온다.
       event.target.textContent : form이 아닌 요소들 내부의 text 경우 value를 가지고 온다.
     */
    console.log('----------handleUpdateCartProductQuantity------------')
    console.log('event');
    console.log(event.target);
    const minusPlusBtn = event.target.textContent; // -  || + string

    /* closest() : 해당 요소의 부모인 경우만 적용 */
    const cartProductInputQuantity = event.target.parentNode.querySelector('.count_num'); // 상품수량 tag
    const cartProductList = event.target.closest('li'); // 해당 event의 부모 li tag
    const cartProductPriceSpanAll = cartProductList.querySelectorAll('.cart__item_price > span'); // 내부 가격 span tag

    console.log('-----------cartProductPriceSpanAll---------------');
    console.log(cartProductPriceSpanAll);

    let QUANTITY = parseInt(cartProductInputQuantity.value); // 상품 수량

    if (minusPlusBtn === '-' && QUANTITY > 1)
        cartProductInputQuantity.value = --QUANTITY;
    else if (minusPlusBtn === '+' && QUANTITY < 100)
        cartProductInputQuantity.value = ++QUANTITY;

    console.log('----------start : handleUpdateCartProductQuantity - cartProductPriceSpanAll ------------')
    cartProductPriceSpanAll.forEach((cartProductPriceSpan) => {
        const partPriceInt = parseInt(cartProductPriceSpan.getAttribute('part_prc'));
        // console.log(partPriceInt);
        // console.log(QUANTITY);
        const priceCalculate = partPriceInt * QUANTITY; // 원화 포맷팅 후 계산
        const priceFormat = priceCalculate.toLocaleString('ko-KR') + '원'; // 계산 다한 가격 원화로 변경
        // console.log(priceFormat);
        cartProductPriceSpan.innerHTML = priceFormat;
    })
    console.log('----------finish : handleUpdateCartProductQuantity - cartProductPriceSpanAll ------------')

}

// 상품 수량 업데이트 함수
async function updateCartProductQuantity(event) {
    const cartProductList = event.target.parentNode.parentNode; // 장바구니 상품 리스트 <li></li>
    const cartProductPriceSpanAll = cartProductList.querySelectorAll('.cart__item_price > span'); // 내부 가격 span tag
    const cartProductSequence = cartProductList.getAttribute('cart_prod_seq'); // 장바구니 상품 리스트 pk
    const cartProdSeq_quantity_List = [parseInt(cartProductSequence)];// 문자열로 된 숫자를 정수로 변환하여 배열에 추가

    const cartProductInputQuantity = event.target.parentNode.querySelector('.count_num'); // 상품수량 tag
    let QUANTITY = parseInt(cartProductInputQuantity.value); // 상품 수량

    console.log('----------start : updateCartProductQuantity - cartProductPriceSpanAll ------------')
    cartProductPriceSpanAll.forEach((cartProductPriceSpan) => {
        const partPriceInt = parseInt(cartProductPriceSpan.getAttribute('part_prc'));
        console.log(partPriceInt);
        console.log(QUANTITY);
        const priceCalculate = partPriceInt * QUANTITY; // 원화 포맷팅 후 계산
        const priceFormat = priceCalculate.toLocaleString('ko-KR') + '원'; // 계산 다한 가격 원화로 변경
        console.log(priceFormat);
        cartProductPriceSpan.innerHTML = priceFormat;
    })
    console.log('----------finish : updateCartProductQuantity - cartProductPriceSpanAll ------------')

    // 1. 비동기 검증 수행
    await validation(cartProdSeq_quantity_List); // [장바구니 상품 pk]
    cartProdSeq_quantity_List.push(QUANTITY); // [장바구니 상품 pk, 수량]
    // 2. update 수행
    await updateProductQuantity(cartProdSeq_quantity_List); // [장바구니 상품 pk, 수량]

    // debounce 후 결제찬 상품 가격 올리기
    groupExpectSalePrice();

}


// 상품 삭제 함수  TODO 코드 줄이기
function deleteCartProduct(event) {
    // delete btn의 부모 요소
    const parentElement = event.target.parentNode;
    // 부모요소에서 input 내부 property의 값을 가지고 오기
    const cartProdSeq = parentElement.getAttribute('cart_prod_seq');
    const cartProdSeqList = [parseInt(cartProdSeq)];// 문자열로 된 숫자를 정수로 변환하여 배열에 추가

    // rest API 수행 , server로 값 보내기
    fetch("/cart/delete", {
        method: 'PATCH',
        headers: {
            // content-type 요청 보내기 | accept 응답 받기
            'Content-Type': 'application/json',
            'accept': 'text/html'
        },
        // 요청 보내는 경우, 형식을 지켜줘야함
        body: JSON.stringify(cartProdSeqList)
    })
        .then(response => {
            if (response.ok) return response.text();
            else throw new Error('Error: ' + response.status);
        })
        .then(data => {
            if (data === "no_memberId") {
                alert("no_member. go to login");
                window.location.href = "/login";
                return;
            } else if (data === "wrong product") {
                alert("don't do that")
                return;
            }
            parentElement.remove();
            CART_PROD_SEQ_LIST = CART_PROD_SEQ_LIST.filter(value => value !== parseInt(cartProdSeq)); // todo async
            console.log(CART_PROD_SEQ_LIST);

            groupExpectSalePrice();
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

// 선택상품 삭제 함수
function deleteCartProductAll() {
    // rest API 수행 , server로 값 보내기
    fetch("/cart/delete", {
        method: 'PATCH',
        headers: {
            // content-type 요청 보내기 | accept 응답 받기
            'Content-Type': 'application/json',
            'accept': 'text/html'
        },
        // 요청 보내는 경우, 형식을 지켜줘야함
        body: JSON.stringify(CART_PROD_SEQ_LIST)
    })
        .then(response => {
            if (response.ok) return response.text();
            else throw new Error('Error: ' + response.status);
        })
        .then(data => {
            if (data === "no_memberId") {
                alert("no_member. go to login");
                window.location.href = "/login";
                return;
            } else if (data === "wrong product") {
                alert("don't do that")
                return;
            }
            // 전체삭제 방법
            CART_PROD_SEQ_LIST.forEach(seq => {
                const selector = `li[cart_prod_seq="${seq}"]`;
                const element = document.querySelector(selector);
                if (element) {
                    // dom에선 삭제 되지만 nodeList에선 제거가 안된다. querySelectsAll 시, null 발생
                    element.remove();
                }
            });
            selectAllBtn.checked = false;
            selectAllProduct();
        })
        .catch(error => {
            console.error('Error:', error);
        });


}

// 주문하기 이벤트
async function handleOrderBtn() {
    try {
        await validation(CART_PROD_SEQ_LIST); // 상품존재유무 파악
        await soldOutValidation(); // 품절 파악
        await inventoryValidation(); // 재고 파악
        await moveOrderPage(); // 주문서 이동

    } catch (e) {
        console.log('runTime Exception : ' + e);
    }
}


// 전체 상품 선택 함수
function selectAllProduct() {
    const check = selectAllBtn.checked; // true, false(default)

    selectBtns.forEach(selectBtn => {
        if (selectBtn === null) {
            return;
        }
        selectBtn.checked = check; // 우변의 checked 여부에 따라서 좌변의 checked 여부 변경
        const cartProdSeq = parseInt(selectBtn.closest("li").getAttribute('cart_prod_seq')); // 장바구니 상품 pk

        check ? CART_PROD_SEQ_LIST.push(cartProdSeq) : (CART_PROD_SEQ_LIST = []);
        // 중복 요소 제거
        CART_PROD_SEQ_LIST = CART_PROD_SEQ_LIST.filter((value, index) => CART_PROD_SEQ_LIST.indexOf(value) === index);
    })
    dynamicNum = check ? selectBtns.length : 0;

    groupExpectSalePrice();
    countSelectNum.innerText = CART_PROD_SEQ_LIST.length;
    console.log("all : " + CART_PROD_SEQ_LIST);

}

// 상품 선택 함수
function selectProduct(event) {
    const targetBtn = event.target; // click한 btn 요소
    const cartProdSeq = parseInt(targetBtn.parentNode.getAttribute('cart_prod_seq')); // 장바구니 상품 pk
    if (targetBtn.checked) {
        CART_PROD_SEQ_LIST.push(cartProdSeq); // 리스트에 담음
        dynamicNum++; // 동적 숫자 - 전체상품 개수 확인용
    } else {
        // filter : 해당 조건이 true인 경우 값을 남긴다
        CART_PROD_SEQ_LIST = CART_PROD_SEQ_LIST.filter((item) => item !== cartProdSeq); // unchecked 경우 제거
        dynamicNum--; // 동적 숫자
    }
    selectAllBtn.checked = (selectBtns.length === dynamicNum);
    groupExpectSalePrice();
    countSelectNum.innerText = CART_PROD_SEQ_LIST.length;
    console.log("prod : " + CART_PROD_SEQ_LIST)
}


/* Document EVENT */

// 상품 선택 이벤트
selectBtns.forEach(selectBtn => {
    selectBtn.addEventListener("click", selectProduct);
})
// 전체 상품 선택 이벤트
selectAllBtn.addEventListener("click", selectAllProduct);

// 상품 삭제 이벤트
deleteBtns.forEach(deleteBtn => {
    deleteBtn.addEventListener("click", deleteCartProduct);
});
// 선택상품 삭제 이벤트
deleteAllBtn.addEventListener("click", deleteCartProductAll);

// 상품 수량 이벤트
quantityDivs.forEach(quantityDiv => {
    quantityDiv.addEventListener("click", handleUpdateCartProductQuantity);
    quantityDiv.addEventListener("click", debounceUpdateQuantity);
    quantityDiv.addEventListener("change", updateCartProductQuantity);
})

// 상품 주문 이벤트
orderBtn.addEventListener("click", handleOrderBtn);
/* TODO 꼭 하고 만다... 동적 html */