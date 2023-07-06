const deleteBtns = document.querySelectorAll(".cart__delete_btn");
const deleteAllBtn = document.querySelector(".cart__items_nav__btn_rm");
// delete REST API
function deleteCartProduct(event) {
    // delete btn의 부모 요소
    const parentElement = event.target.parentNode;
    // 부모요소에서 input 내부 property의 값을 가지고 오기
    const cartProdSeq = parentElement.getAttribute('cart_prod_seq')

    // rest API 수행 , server로 값 보내기
    fetch("/cart/delete", {
        method: 'PATCH',
        headers: {
            // content-type 요청 보내기 | accept 응답 받기
            'Content-Type': 'application/json',
            'accept': 'text/html'
        },
        // 요청 보내는 경우, 형식을 지켜줘야함
        body: JSON.stringify(cartProdSeq)
    })
        .then(response => {
            if (response.ok) return response.text();
            else throw new Error('Error: ' + response.status);
        })
        .then(data => {
            if (data === "no_memberId"){
                alert("no_member. go to login");
                window.location.href = "/login";
                return;
            } else if (data === "wrong product"){
                alert("don't do that")
                return;
            }
            parentElement.remove();
        })
        .catch(error => {
            console.error('Error:', error);
            window.location.href="/";
        });
}

function deleteCartProducts(event){
    console.log(cartProdSeqSet);
}
deleteBtns.forEach(deleteBtn => {
    deleteBtn.addEventListener("click", deleteCartProduct);
});

deleteAllBtn.addEventListener("click", deleteCartProducts);