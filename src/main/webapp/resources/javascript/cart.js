const selectAllBtn = document.querySelector(".cart__items_nav__checkbox"); // 전체 선택
const selectBtns = document.querySelectorAll(".cart__item_nav__checkbox"); // 선택
let CART_PROD_SEQ_LIST = [];
let dynamicNum = 0;

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

// 모두 선택
function selectAllProduct() {
    const check = selectAllBtn.checked; // true, false(default)

    selectBtns.forEach(selectBtn => {
        console.log(selectAllBtn.checked);
        selectBtn.checked = check; // 우변의 checked 여부에 따라서 좌변의 checked 여부 변경
        const cartProdSeq = selectBtn.closest("li").getAttribute('cart_prod_seq');
        const LongCartProdSeq = parseInt(cartProdSeq);

        check ? CART_PROD_SEQ_LIST.push(LongCartProdSeq) : (CART_PROD_SEQ_LIST = []);
        // 중복 요소 제거
        CART_PROD_SEQ_LIST = CART_PROD_SEQ_LIST.filter((value, index) => CART_PROD_SEQ_LIST.indexOf(value) === index);
    })

    console.log("all : " + CART_PROD_SEQ_LIST);
}

function selectProduct(event) {
    const targetBtn = event.target;
    const cartProdSeq = targetBtn.parentNode.getAttribute('cart_prod_seq');
    const LongCartProdSeq = parseInt(cartProdSeq);
    console.log("cartProdSeq" + LongCartProdSeq);
    if (targetBtn.checked) {
        CART_PROD_SEQ_LIST.push(LongCartProdSeq);
        dynamicNum++;
    } else {
        // filter : 해당 조건이 true인 경우 값을 남긴다
        CART_PROD_SEQ_LIST = CART_PROD_SEQ_LIST.filter((item) => item !== LongCartProdSeq);
        dynamicNum--;
    }
    console.log("dynamicNum:" + dynamicNum)
    selectAllBtn.checked = (selectBtns.length === dynamicNum);
    console.log("prod : " + CART_PROD_SEQ_LIST)
}

// event
selectBtns.forEach(selectBtn => {
    selectBtn.addEventListener("click", selectProduct);
})

selectAllBtn.addEventListener("click", selectAllProduct);