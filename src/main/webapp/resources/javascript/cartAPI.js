const deleteBtns = document.querySelectorAll(".cart__delete_btn");

// delete REST API
function deleteCartProduct(e) {
    // delete btn의 부모 요소 들고 오기
    const parentDiv = e.target.parentNode;

    // 부모요소에서 input 내부 property의 값을 가지고 오기
    const cartProdSeq = parentDiv.querySelector("input[cart_prod_seq]").getAttribute('cart_prod_seq');

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
            // 응답을 받는 경우, 형식을 지켜줘야한다.
            if (response.ok) {
                return response.text()
            }
            throw new Error('Error: ' + response.status);
        })
        .then(data => {
            console.log(data);
        })
        .catch(error => {
            // 서버에서 응답이 실패한 경우에 대한 처리를 여기에 작성하세요.
            console.error('Error:', error);
        });
    // TODO. 화면에서 동적으로 처리된 html을 받아서 뿌릴 것
    parentDiv.style.display = 'none';
}

// 모든 삭제 btn에 관련된 요소 listen하기
for (let deleteBtn of deleteBtns)
    deleteBtn.addEventListener("click", deleteCartProduct);