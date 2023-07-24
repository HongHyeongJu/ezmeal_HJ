let SELECT_SEQ_LIST = []; // 선택된 발주 버튼
let dynamicNum = 0; // 체크박스 선택 수 확인

// checkbox 선택 함수 todo 수정 필요
// 전체 상품 선택 함수
const selectAllProduct = function (sequenceTag, sequenceName) {
    const check = selectAllBtn.checked; // true, false(default)

    selectBtns.forEach((selectBtn) => {
        if (selectBtn === null) {
            return;
        }
        selectBtn.checked = check; // 우변의 checked 여부에 따라서 좌변의 checked 여부 변경
        const selectSeq = parseInt(
            selectBtn.closest(sequenceTag).getAttribute(sequenceName) // "tr", "ordr_id"
        ); // 필요 객체 seq
        console.log()
        check ? SELECT_SEQ_LIST.push(selectSeq) : (SELECT_SEQ_LIST = []);
        // 중복 요소 제거
        SELECT_SEQ_LIST = SELECT_SEQ_LIST.filter(
            (value, index) => SELECT_SEQ_LIST.indexOf(value) === index
        );
    });
    dynamicNum = check ? selectBtns.length : 0;

    console.log("all : " + SELECT_SEQ_LIST);
};

// 상품 선택 함수
const selectProduct = function (event, sequenceTag, sequenceName) {
    console.log(event);
    const targetBtn = event.target; // click한 btn 요소
    console.log(targetBtn.closest(sequenceTag).getAttribute(sequenceName));
    const selectSeq = parseInt(
        targetBtn.closest(sequenceTag).getAttribute(sequenceName)
    ); // 필요 객체 seq
    console.log(selectSeq);
    if (targetBtn.checked) {
        SELECT_SEQ_LIST.push(selectSeq); // 리스트에 담음
        dynamicNum++; // 동적 숫자 - 전체객체 개수 확인용
    } else {
        // filter : 해당 조건이 true인 경우 값을 남긴다
        SELECT_SEQ_LIST = SELECT_SEQ_LIST.filter((item) => item !== selectSeq); // unchecked 경우 제거
        dynamicNum--; // 동적 숫자
    }
    selectAllBtn.checked = selectBtns.length === dynamicNum;
    console.log("prod : " + SELECT_SEQ_LIST);
};
