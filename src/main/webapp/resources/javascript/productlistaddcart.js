function increaseQuantity() {
    var quantity = document.getElementById("quantity");
    quantity.textContent = parseInt(quantity.textContent) + 1;
}

function decreaseQuantity() {
    var quantity = document.getElementById("quantity");
    var value = parseInt(quantity.textContent);

    if (value > 1) {
        quantity.textContent = value - 1;
    }
}

function submitForm(event) {
    event.preventDefault(); // 기본 제출 동작 막기
    var qty = document.getElementById("quantity").textContent;
    var prod_cd = document.getElementById("product_code").value;

    if (parseInt(quantity) >= 1) {

        $.ajax({
            url: "/addcart", // 서버의 경로 설정
            type: "POST", // 요청 방식 (POST 또는 GET)
            data: {
                prod_cd: prod_cd, // 상품 코드 전달
                qty: qty // 수량 전달
            },
            success: function(response) {
                alert("장바구니 담기 성공!");
            },
            error: function(xhr, status, error) {
                alert("장바구니 담기 실패!");
            }
        });
    }
}