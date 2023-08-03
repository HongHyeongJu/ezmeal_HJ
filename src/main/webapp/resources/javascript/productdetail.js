

/*------ 상품 이름 아래에 별점 출력하기 ---------*/

/* class stars를 받아온다.
* 정수만큼 클래스에  star_1_img를 추가하고 hidden속성을 false로 바꾸기 document.getElementById('star1').hidden = false; */

/* 0.5가 있는 경우 1개만 star_1_img를 추가해주기.  hidden속성을 false로 바꾸기 */
function makeReviewAvgStarImg () {
    /*별점 태그 가져오기*/
    let divTag = document.getElementsByClassName('stars_set')[0];

    /*별점 평균 가져오기*/
    let reviewAvg = document.getElementById("reviewAvg");
    let avg = parseFloat(reviewAvg.dataset.avg);

    /*별점 평균이 0보다 큰 경우에만 태그 생성*/
    if (avg > 0) {
        /*별점평균만큼 태그 집어넣기*/
        let halfStar = (avg % 1 !== 0) ? true : false;

        /*정수만큼 출력*/
        for(let j = 1; j <= avg; j++){
            let starImg = document.createElement('span');
            starImg.className = 'star_1_img';
            divTag.appendChild(starImg);
        }
        /*소수점이 있을 때*/
        if(halfStar){
            let halfStarImg = document.createElement('span');
            halfStarImg.className = 'star_0_5_img';
            divTag.appendChild(halfStarImg);
        }
    } else { // 별점 평균이 0일 때
        let zeroStarImg = document.createElement('span');
        zeroStarImg.className = 'star_0_img';
        divTag.appendChild(zeroStarImg);
    }
}



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


