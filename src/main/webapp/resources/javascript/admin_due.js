/* DOCUMENT 변수명 */
const periodBtnAll = document.querySelectorAll(".admin__period_btn"); // 조회 기간 버튼

/* static 변수 */

function handlePeriod(event) {
    const targetBtn = event.target
    // console.log(targetBtn);
    // console.log(targetBtn.classList[1]);
    if (targetBtn.classList[1] === 'personal-day') {
        let periodDateString = "";
        const periodDateAll = document.querySelectorAll('input[type="date"]');
        periodDateAll.forEach((periodDate) => {
            periodDateString += periodDate.value + " "; // 2023-06-29 2023-07-10
        })
        getOrderPaymentData(periodDateString);
    } else {
        const periodString = targetBtn.textContent;
        getOrderPaymentData(periodString);
    }

}

periodBtnAll.forEach((periodBtn) => {
    periodBtn.addEventListener('click', handlePeriod);
})