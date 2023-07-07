const selectAllBtn = document.querySelector(".cart__items_nav__checkbox"); // 전체 선택
const selectBtns = document.querySelectorAll(".cart__item_nav__checkbox"); // 선택

const itemPriceSpan = document.querySelectorAll(".cart__item_price span"); // 가격 div 내부 실제가격
const bannerPrice = document.querySelectorAll(".payment__prod span:last-child") // 배너 가격

const deleteBtns = document.querySelectorAll(".cart__delete_btn"); // 삭제 표시 버튼
const deleteAllBtn = document.querySelector(".cart__items_nav__btn_rm"); // 선택상품 삭제
const quantityDivs = document.querySelectorAll('.cart__item__btn'); // 수량 관련 div

let CART_PROD_SEQ_LIST = []; // 선택된 장바구니 상품 pk
let dynamicNum = 0; // 체크박스 선택 수 확인
let debounceInitTime = null; // 서버 부하 방지

/* 재사용 함수 */
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

/* EVENT 함수 */

// 상품 수량 함수
function handleUpdateCartProductQuantity(event) {
    /* 중요
       event.target: 실제 event가 발생한 요소를 반환
       event.target.value : input, select, textarea 같은 form 요소일 경우 value를 가지고 온다.
       event.target.textContent : form이 아닌 요소들 내부의 text 경우 value를 가지고 온다.
     */

    const minusPlusBtn = event.target.textContent; // -  || + string

    const cartProductInputQuantity = event.target.parentNode.querySelector('.count_num'); // 상품수량 tag
    let QUANTITY = parseInt(cartProductInputQuantity.value); // 상품 수량

    if (minusPlusBtn === '-' && QUANTITY > 1)
        cartProductInputQuantity.value = --QUANTITY;
    else if (minusPlusBtn === '+' && QUANTITY < 100)
        cartProductInputQuantity.value = ++QUANTITY;

}

async function updateCartProductQuantity(event) {
    const cartProductList = event.target.parentNode.parentNode; // 장바구니 상품 리스트 <li></li>
    const cartProductSequence = cartProductList.getAttribute('cart_prod_seq'); // 장바구니 상품 리스트 pk
    const cartProdSeq_quantity_List = [parseInt(cartProductSequence)];// 문자열로 된 숫자를 정수로 변환하여 배열에 추가

    const cartProductInputQuantity = event.target.parentNode.querySelector('.count_num'); // 상품수량 tag
    let QUANTITY = parseInt(cartProductInputQuantity.value); // 상품 수량

    // 1. 비동기 검증 수행
    await validation(cartProdSeq_quantity_List); // [장바구니 상품 pk]
    cartProdSeq_quantity_List.push(QUANTITY); // [장바구니 상품 pk, 수량]
    // 2. update 수행
    await updateProductQuantity(cartProdSeq_quantity_List); // [장바구니 상품 pk, 수량]

}

async function debounce(event) {
    clearTimeout(debounceInitTime);
    debounce = setTimeout(() => {
        updateCartProductQuantity(event)
    }, 500);
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
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

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
                element.remove();
            });

        })
        .catch(error => {
            console.error('Error:', error);
        });
}


// // event function
// function handleOrder() {
//     checkChecked(); // string type 데이터
//
//     // 쿠키 참조 blog: https://velog.io/@rudnf003/javascript-%EC%BF%A0%ED%82%A4-%EC%83%9D%EC%84%B1-%EB%B0%8F-%EA%B4%80%EB%A6%AC
//     document.cookie = "orderProduct=" + SELECTPRODUCTCODE + "; path=/; domain=localhost";
//     window.location.href = "/order/general";
//     // 초기화
//     SELECTPRODUCTCODE = "";
// }
// //
// // // logic -> TODO cart_seq 변경되어서 logic 수정 필요
// // // 1. cart checked prod_cd 찾기
// const checkChecked = function () {
//     // 반복문이 돌 때마다 block(scope 생성) 이 생성이 되어서 const 사용이 가능하다.
//     for (const product of products) {
//         // checked 된 것 확인 - html attribute는 고정이여도 동적으로 변하는 property를 인식해서 checked 신경 쓸 필요 X
//         const checkbox = product.querySelector("input[type='checkbox']");
//
//         if (checkbox.checked) {
//             // checked 된 document의 상품 코드 받아오기
//             const checkedProd = product.querySelector(".cart__item_list_prod_cd");
//             const prodText = checkedProd.textContent; // "[상품 code]"
//             const prodCode = prodText.substring(
//                 prodText.indexOf("[") + 1,
//                 prodText.indexOf("]")
//             );
//         }
//     }
// }


function expectedSalePrice(check) { // 다중 선택시 사용하는 가격 측정 function
    let PRODUCT_PRICE = 0;
    let SALE_PRICE = 0;
    let EXPECTED_PRICE = 0;
    let EXPECTED_POINT = 0;

    if (check) {
        CART_PROD_SEQ_LIST.forEach(seq => {
            const selector = `li[cart_prod_seq="${seq}"]`;
            const element = document.querySelector(selector); // 개별상품목록
            const cartItemPriceSpans = element.querySelectorAll(".cart__item_price > span"); // 상품 가격 [0]: 판매가 [1]: 소비자가
            const salePrice = parseInt(cartItemPriceSpans[0].textContent);
            const productPrice = parseInt(cartItemPriceSpans[1].textContent);
            PRODUCT_PRICE += productPrice;
            SALE_PRICE += productPrice - salePrice;
            EXPECTED_PRICE += salePrice;
            EXPECTED_POINT += productPrice / 10;
        })
    }
    bannerPrice[0].innerText = PRODUCT_PRICE;
    bannerPrice[1].innerText = SALE_PRICE;
    bannerPrice[2].innerText = EXPECTED_PRICE;
    bannerPrice[3].innerText = EXPECTED_POINT;
}

// 상품 선택 함수
function selectAllProduct() {
    const check = selectAllBtn.checked; // true, false(default)

    selectBtns.forEach(selectBtn => {
        selectBtn.checked = check; // 우변의 checked 여부에 따라서 좌변의 checked 여부 변경
        const cartProdSeq = selectBtn.closest("li").getAttribute('cart_prod_seq');
        const LongCartProdSeq = parseInt(cartProdSeq);

        check ? CART_PROD_SEQ_LIST.push(LongCartProdSeq) : (CART_PROD_SEQ_LIST = []);
        // 중복 요소 제거
        CART_PROD_SEQ_LIST = CART_PROD_SEQ_LIST.filter((value, index) => CART_PROD_SEQ_LIST.indexOf(value) === index);
    })
    dynamicNum = check ? selectBtns.length : 0;
    expectedSalePrice(check);
    console.log("all : " + CART_PROD_SEQ_LIST);

}

function selectProduct(event) {
    const targetBtn = event.target; // click한 btn 요소
    const cartProdSeq = targetBtn.parentNode.getAttribute('cart_prod_seq'); // 해당 상품 리스트 tag
    const LongCartProdSeq = parseInt(cartProdSeq); // 장바구니 상품 pk
    if (targetBtn.checked) {
        CART_PROD_SEQ_LIST.push(LongCartProdSeq); // 리스트에 담음
        dynamicNum++; // 동적 숫자 - 전체상품 개수 확인용
    } else {
        // filter : 해당 조건이 true인 경우 값을 남긴다
        CART_PROD_SEQ_LIST = CART_PROD_SEQ_LIST.filter((item) => item !== LongCartProdSeq); // unchecked 경우 제거
        dynamicNum--; // 동적 숫자
    }
    selectAllBtn.checked = (selectBtns.length === dynamicNum);
    console.log("prod : " + CART_PROD_SEQ_LIST)
}


/* Document EVENT */
selectBtns.forEach(selectBtn => {
    selectBtn.addEventListener("click", selectProduct);
})
selectAllBtn.addEventListener("click", selectAllProduct);

deleteBtns.forEach(deleteBtn => {
    deleteBtn.addEventListener("click", deleteCartProduct);
});

deleteAllBtn.addEventListener("click", deleteCartProductAll);

quantityDivs.forEach(quantityDiv => {
    quantityDiv.addEventListener("click", handleUpdateCartProductQuantity);
    quantityDiv.addEventListener("click", debounce);
    quantityDiv.addEventListener("change", updateCartProductQuantity);
})