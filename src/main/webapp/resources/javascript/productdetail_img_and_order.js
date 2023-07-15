
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
    /*클릭한 이미지의 소스 저장*/
    var newSrc = img.src;
    /*현재 메인 이미지 소스 가져오기*/
    var mainImage = document.querySelector('.main_img');
    /*둘이 바꾸기*/
    mainImage.src = newSrc;
}


/*------------[찜하기] [장바구니] [주문하기...는 나중에]--------*/

/*------------  찜하기 버튼  --------*/

document.querySelectorAll('.wishlist_btn').forEach((button) => {
    button.addEventListener('click', function() {

        /* 상품코드 가져오기 */
        let prodCdElement = document.getElementById("prod_cd_is");
        let prod_cd = prodCdElement.dataset.value;

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



/*----------- 장바구니 값 넘기기 (옵션 없는 일반 상품)--------------*/

document.querySelectorAll('.addcart_btn').forEach((button) => {
    button.addEventListener('click', function() {

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

            for (let ul of ulTags) {
                let optSeq = parseInt(ul.dataset.value.split('_')[4]);
                let optQty = parseInt(ul.dataset.value.split('_')[2]);
                console.log("optSeq:"+optSeq);
                console.log("optQty:"+optQty);

                $.ajax({
                    url: "/cart/add",
                    type: "POST",
                    data: JSON.stringify({
                        prod_cd: prod_cd,
                        opt_seq: optSeq,
                        typ: typ,
                        qty: optQty
                    }),
                    contentType: "application/json",
                    success: function(response) {
                        alert("옵션상품 장바구니 담기 성공!");
                    },
                    error: function(xhr, status, error) {
                        alert("옵션상품 장바구니 담기 실패!");
                    }
                });
            }

        }

    });
});



/*------------   호버    [찜하기] [장바구니] [주문하기]   호버   --------*/

let buttons = document.querySelectorAll('.wishlist_btn, .addcart_btn, .order_btn');

function addHover(button) {
    button.addEventListener('mouseenter', function() {
        button.classList.add('hover');
    });

    button.addEventListener('mouseleave', function() {
        button.classList.remove('hover');
    });
}

buttons.forEach(addHover);