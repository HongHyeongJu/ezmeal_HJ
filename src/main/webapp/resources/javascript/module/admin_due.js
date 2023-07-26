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