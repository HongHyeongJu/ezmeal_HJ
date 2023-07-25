/* static 변수 */
// 변수로 함수를 설정했기때문에 함수를 호출하지 않는 이상 수행되지 않는다.
const handlePeriod = function (event) {
    const targetBtn = event.target
    // console.log(targetBtn);
    // console.log(targetBtn.classList[1]);
    if (targetBtn.classList[1] === 'personal-day') {
        let periodDateString = "";
        const periodDateAll = document.querySelectorAll('input[type="date"]');
        periodDateAll.forEach((periodDate) => {
            periodDateString += periodDate.value + " "; // 2023-06-29 2023-07-10
        })
        return periodDateString;
    } else {
        return targetBtn.textContent;
    }

}