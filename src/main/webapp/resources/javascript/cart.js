// Document
const products = document.getElementsByClassName("cart__item_list");
const paymentBtn = document.querySelector(".payment-detail__btn");

// JSON_string
let SELECTPRODUCTCODE = ""; // 항상 초기화 필요

// event function
function handleOrder() {
    checkChecked(); // string type 데이터

    console.log(SELECTPRODUCTCODE);
    // 쿠키 참조 blog: https://velog.io/@rudnf003/javascript-%EC%BF%A0%ED%82%A4-%EC%83%9D%EC%84%B1-%EB%B0%8F-%EA%B4%80%EB%A6%AC
    // cookie maxtime을 설정해야 한다.
    document.cookie = "orderProduct=" + SELECTPRODUCTCODE + "; path=/; domain=localhost";

    console.log(typeof SELECTPRODUCTCODE)
    window.location.href = "/order/orderView";

    // 초기화
    SELECTPRODUCTCODE = "";
}

// logic
// 1. cart checked prod_cd 찾기
const checkChecked = function () {
    // 반복문이 돌 때마다 block(scope 생성) 이 생성이 되어서 const 사용이 가능하다.
    for (const product of products) {
        // checked 된 것 확인 - html attribute는 고정이여도 동적으로 변하는 property를 인식해서 checked 신경 쓸 필요 X
        const checkbox = product.querySelector("input[type='checkbox']");
        if (checkbox.checked) {
            // checked 된 document의 상품 코드 받아오기
            const checkedProd = product.querySelector(".cart__item_list_prod_cd");
            const prodText = checkedProd.textContent; // "[상품 code]"
            // console.log('prodText : ' + prodText)

            const prodCode = prodText.substring(
                prodText.indexOf("[") + 1,
                prodText.indexOf("]")
            );
            SELECTPRODUCTCODE += prodCode;
        }
    }
};

// event
paymentBtn.addEventListener("click", handleOrder);
