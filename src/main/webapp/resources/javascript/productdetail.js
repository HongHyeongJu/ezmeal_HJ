

window.addEventListener('DOMContentLoaded', (event) => {

    /*-----------------------------------------------  상품 문의 내용보기 스크립트  --------------------------*/
    const accordions = document.querySelectorAll('.accordion');

    accordions.forEach((accordion) => {
        accordion.addEventListener('click', () => {
            const answer = accordion.parentElement.nextElementSibling.querySelector('.answer');
            if (answer.style.maxHeight) {
                answer.style.maxHeight = null;
                answer.style.marginTop = '0';
                answer.style.paddingBottom = '0';
            } else {
                answer.style.maxHeight = answer.scrollHeight + 'px';
                answer.style.marginTop = '20px';
                answer.style.paddingBottom = '20px';
            }
        });
    });

    /*-------------------------------------  상품 후기 이미지 확대 스크립트  ---------------------------------*/

    const imgWrappers = document.querySelectorAll('.rv_img');
    const rvStmts = document.querySelectorAll('.rv_stmt');
    let originalMarginTop = '';

    imgWrappers.forEach((imgWrapper, index) => {
        const rvStmt = imgWrapper.previousElementSibling; // 이전 형제 요소인 .rv_stmt 선택

        imgWrapper.addEventListener('click', () => {
            imgWrapper.classList.toggle('active');

            if (imgWrapper.classList.contains('active')) {
                originalMarginTop = rvStmt.style.marginTop; // 원래의 margin-top 값 저장
                rvStmt.style.width = '100%'; // 이미지 확대시 rv_stmt의 width 값을 조정
            } else {
                rvStmt.style.width = '80%'; // 원래의 width 값으로 복원
            }
        });
    });

});