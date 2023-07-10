

/*작동 안함!!*/

/*상품별 장바구니 버튼의 동작  (감소) */
function decreaseQuantity(button) {
    let quantityElement = button.nextElementSibling;
    let quantity = parseInt(quantityElement.textContent);
    if (quantity > 1) {
        quantityElement.textContent = quantity - 1;
    }
}

/*상품별 장바구니 버튼의 동작  (증가) */
function increaseQuantity(button) {
    let quantityElement = button.previousElementSibling;
    let quantity = parseInt(quantityElement.textContent);
    quantityElement.textContent = quantity + 1;
}


document.querySelectorAll('.cart_form').forEach((form) => {
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        let quantityElement  = this.querySelector('.quantity');
        let prodCdElement = this.querySelector('.prod_cd');

        let qty = parseInt(quantityElement.textContent);
        let prod_cd = prodCdElement.value;

        if (qty >= 1) {
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
    });
});







/* 나중에? 비동기로 처리할 때 상품 정렬에 따라 새로운 객체리스트 받아오는 코드 */
/*
$(document).ready(function() {
    $('.sort-link').click(function(e) {
        e.preventDefault(); // 기본 링크 클릭 동작을 방지합니다.

        var sortKeyword = $(this).data('sort'); // data-sort 값(정렬 키워드)을 가져옵니다.

        $.ajax({
            url: '/product/catelist', // 서버에 요청을 보낼 URL입니다.
            method: 'GET', // HTTP 메서드입니다.
            data: {
                'sortKeyword': sortkeyword,
                'cate_cd': cate_cd
            }, // 서버에 보낼 데이터입니다.
            success: function(data) {
                // 서버로부터 받은 응답을 처리하는 코드를 여기에 작성합니다.
                // 예를 들어, 서버로부터 받은 정렬된 상품 리스트를 페이지에 표시하는 코드를 작성할 수 있습니다.
            },
            error: function(error) {
                // 요청이 실패했을 때 실행할 코드를 여기에 작성합니다.
            }
        });
    });
});
*/