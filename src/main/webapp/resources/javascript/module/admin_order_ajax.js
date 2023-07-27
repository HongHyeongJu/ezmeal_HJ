// dynamic data : table body에 받는 동적 data
const getAdminDynamicData = function (url, periodString) {
    return fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(periodString)
    })
        .then(response => response.json())
        .then(data => {
            return data;
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

// 발주, 배송중, 등등  확인 버튼 누를 경우 수행되는 update 함수
const updateAdminSubmitBtn = function (url, dataList) {
    return fetch(url, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(dataList) // update list
    })
        .then(response => response.text()) // text의 경우: text(), list,map,object 경우 .json()
        .then(data => {
            console.log("---------------");
            console.log("updateAdminSubmitBtn data");
            console.log(data);
            return data;
            // 가지고 온 data로 reload 수행
        })
        .catch(error => {
            console.error('Error:', error); // 따로 return으로 반환할 필요 없음
        })
}
