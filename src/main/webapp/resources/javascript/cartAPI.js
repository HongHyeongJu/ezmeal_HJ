const deleteBtns = document.querySelectorAll(".cart__delete_btn"); // 삭제 표시 버튼
const deleteAllBtn = document.querySelector(".cart__items_nav__btn_rm"); // 선택상품 삭제
const quantityDivs = document.querySelectorAll('.cart__item__btn');

let debounce = null;
// updateCartProductQuantity
// 디바운스 이용

const validation = function (validationList, quantity){
    return fetch("/cart/validation", {
        method: 'POST',
        headers: {
            // content-type 요청 보내기 | accept 응답 받기
            'Content-Type': 'application/json',
        },
        // 요청 보내는 경우, 형식을 지켜줘야함
        body: JSON.stringify(validationList)
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
                alert("잘못된 상품 등록번호입니다.")
                return;
            }
            // 2nd fetch 작업에 사용할 data json으로 변경 - qty와 cartProdSeq가 필요
            validationList.push(quantity);
            console.log("validationList12122112 :" +validationList)
            console.log(`cartProdSeqList121121212 = ${validationList}`)
            return validationList;
        })
}

// fetch 비동기함수를 따로 분리했기 때문에 현재 function 내부에서는 해당 함수가 완료 되기 전까지 기다려야해서 async , await를 사용한다.
async function updateCartProductQuantity(event) {
    /* 중요
       event.target: 실제 event가 발생한 요소를 반환
       event.target.value : input, select, textarea 같은 form 요소일 경우 value를 가지고 온다.
       event.target.textContent : form이 아닌 요소들 내부의 text 경우 value를 가지고 온다.
     */

    // event 발생 요소
    const quantityDivCnt = event.target.textContent;
    // 해당 요소의 부모를 받아온 후 내부 다른 요소를 가지고 온다.
    const parentElement = event.target.parentNode.parentNode; // li dom
    const cartProdSeq = parentElement.getAttribute('cart_prod_seq');
    const cartProdSeqList = [parseInt(cartProdSeq)];// 문자열로 된 숫자를 정수로 변환하여 배열에 추가
    // update할 int
    const quantityDivNum = event.target.parentNode.querySelector('.count_num');
    let QUANTITY = parseInt(quantityDivNum.textContent);

    if (quantityDivCnt === '-' && QUANTITY > 1)
        quantityDivNum.innerHTML = --QUANTITY + '';
    else if (quantityDivCnt === '+' && QUANTITY < 100)
        quantityDivNum.innerHTML = ++QUANTITY + '';

    // const validationResult = await validation(cartProdSeqList, QUANTITY);
    // console.log("validation" + validationResult);


    fetch("/cart/validation", {
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
                return;
            } else if (data === "wrong product") {
                alert("잘못된 상품 등록번호입니다.")
                return;
            }
            // 2nd fetch 작업에 사용할 data json으로 변경 - qty와 cartProdSeq가 필요
            cartProdSeqList.push(QUANTITY);
            console.log(`cartProdSeqList = ${cartProdSeqList}`)
            return cartProdSeqList;
        })
        .then(data => {
            return fetch('/cart/update', {
                method: 'PATCH',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            });
        })
        .then(response => {
            // 두 번째 요청의 응답 처리
            if (response.ok) {
                // 응답이 성공적인 경우
                return response.text();
            } else {
                throw new Error('두 번째 요청이 실패하였습니다.');
            }
        })
        .then(data => {
            if (data === "fail"){
                console.log('수량이 오르지 않았습니다.')
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}
function handleDebounce(event){
    clearTimeout(debounce);
    debounce = setTimeout(()=>{
        updateCartProductQuantity(event)
    }, 500);
}

// delete REST API
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

// event
deleteBtns.forEach(deleteBtn => {
    deleteBtn.addEventListener("click", deleteCartProduct);
});

deleteAllBtn.addEventListener("click", deleteCartProductAll);

quantityDivs.forEach(quantityDiv => {
    quantityDiv.addEventListener("click", handleDebounce);
})