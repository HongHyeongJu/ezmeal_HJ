function getOrderPaymentData() {
    fetch('/orderPayment/dynamicData', {
        method: 'GET',
    })
        .then(response => response.text())
        .then(data => console.log(data))
        .catch(error => {
            console.error('Error:', error);
        });
}

document.addEventListener('DOMContentLoaded', getOrderPaymentData); // html 문서 load 된 후 실행되는 js 함수