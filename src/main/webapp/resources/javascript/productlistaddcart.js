
/* 상품 수량 감소 */
function decreaseQuantity(btn) {
    let qtyElement = btn.nextElementSibling;
    let qty = parseInt(qtyElement.textContent);
    if (qty > 1) {
        qtyElement.textContent = qty - 1;
    }
}

/* 상품 수량 증가 */
function increaseQuantity(btn) {
    let qtyElement = btn.previousElementSibling;
    let qty = parseInt(qtyElement.textContent);
    qtyElement.textContent = qty + 1;
}


/*------------  찜하기 버튼  --------*/

document.querySelectorAll('.wishlist_btn').forEach((button) => {
    button.addEventListener('click', function() {
        let buttonId = this.id;
        let index = buttonId.split('_')[2];

        let prodCdElement = document.getElementById(`prod_cd_${index}`);
        let prod_cd = prodCdElement.value;

        $.ajax({
            url: "/wishlist/add",
            type: "POST",
            data: JSON.stringify({ prod_cd: prod_cd}),
            contentType: "application/json",
            success: function(response) {
                alert("찜하기 성공!");
            },
            error: function(xhr, status, error) {
                /*비로그인시에는 눌러도 아무 효과 없음*/
                // alert(xhr.responseText);
            }
        });
    });
});

/*---------------- 찜하기 /장바구니 호버 -----------*/

// 모든 .wishlist_btn 요소 선택
let wishlistBtns = document.querySelectorAll('.wishlist_btn');
let submitBtns = document.querySelectorAll('.submit_btn');


// 각 버튼에 이벤트 리스너 등록
wishlistBtns.forEach((button) => {
    button.addEventListener('mouseenter', function() {
        button.classList.add('hover'); // 호버 상태 클래스 추가
    });

    button.addEventListener('mouseleave', function() {
        button.classList.remove('hover'); // 호버 상태 클래스 제거
    });
});

submitBtns.forEach((button) => {
    button.addEventListener('mouseenter', function() {
        button.classList.add('hover'); // 호버 상태 클래스 추가
    });

    button.addEventListener('mouseleave', function() {
        button.classList.remove('hover'); // 호버 상태 클래스 제거
    });
});



/*----------------  장바구니 버튼  -------------*/

document.querySelectorAll('.submit_btn').forEach((button) => {
    button.addEventListener('click', function() {
        /* 버튼 id가져오기 */
        let buttonId = this.id;
        let index = buttonId.split('_')[2];

        /* 상품코드 가져오기 */
        let prodCdElement = document.getElementById(`prod_cd_${index}`);
        let prod_cd = prodCdElement.value;

        /*보관방법*/
        let typElement = document.querySelector(`.sfkp-stus[data-index="${index}"]`);
        let typ = typElement.getAttribute('value');

        /* 옵션SEQ가져오기 */
        let optDiv = document.getElementById(`opt_div_${index}`);
        let opt_seq = null;
        if (optDiv) {
            let optSelect = document.getElementById(`opt_select_${index}`);
            opt_seq = optSelect.value;
        }

        /* 수량 가져오기 */
        let qtyElement = document.getElementById(`qty_${index}`);
        let qty = parseInt(qtyElement.textContent);


        $.ajax({
            url: "/cart/add",
            type: "POST",
            data: JSON.stringify({
                prod_cd: prod_cd,
                typ: typ,
                opt_seq: opt_seq,
                qty: qty
            }),
            contentType: "application/json",
            success: function(response) {
                alert("장바구니 담기 성공!");
            },
            error: function(xhr, status, error) {
                alert("장바구니 담기 실패!");
            }
        });
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