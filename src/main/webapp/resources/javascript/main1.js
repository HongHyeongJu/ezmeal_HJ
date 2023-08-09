//start : img slide 

let index = 0; /*현재 표시되는 이미지*/
displayImages(); /* displayImages 함수선언*/
function displayImages() {
  /*displayImages 함수정의*/
  let i;
  const images =
      document.getElementsByClassName(
          "image "
      ); /*이미지클래스가 있는 모든 요소 선택, 배열에저장*/
  for (i = 0; i < images.length; i++) {
    /*이미지 모든 요소를 반복하고 표시 스타일을 없음으로 설정하여 숨김*/
    images[i].style.display = "none";
  }
  index++; /*인덱스를 증가시키고 이미지갯수의 범위내에있는지 확인 */
  if (index > images.length) {
    index = 1;
  }
  images[index - 1].style.display =
      "block"; /*하나의 이미지만 표시하도록 스타일을 block */
  setTimeout(
      displayImages,
      3000
  ); /*settime으로 displayimage호출하고 2초후에 함수 실행 */
}



const lines = document.querySelectorAll('.line');
let currentIndex = 0;

// function showNextLine() {
//   lines[currentIndex].classList.remove('active');
//
//   currentIndex++;
//   if (currentIndex >= lines.length) {
//     currentIndex = 0;
//   }
//
//   lines[currentIndex].classList.add('active');
// }
//
// setInterval(showNextLine, 1500);