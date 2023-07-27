// 현재 let 변수의 경우, 개별 js에 변수를 설정하게 되면 찾지를 못한다.
let SELECT_SEQ_LIST = []; // 선택된 발주 버튼
let dynamicNum = 0; // 체크박스 선택 수 확인

let DLVAR_SEQ_LIST = []; // 선택된 묶음 checkbox의 dlvr_id list
let DLVAR_DYNAMIC_NUM = 0; // 묶음 선택 수 확인

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
    const targetBtn = event.target; // click한 btn 요소
    // console.log(targetBtn.closest(sequenceTag).getAttribute(sequenceName));
    const selectSeq = parseInt(
        targetBtn.closest(sequenceTag).getAttribute(sequenceName)
    ); // 필요 객체 seq
    console.log(selectSeq);
    if (targetBtn.checked) {
        SELECT_SEQ_LIST.push(selectSeq); // 리스트에 담음
        console.log('SELECT_SEQ_LIST push= '+ SELECT_SEQ_LIST);
        console.log('dynamicNum = ' + dynamicNum);
        dynamicNum++; // 동적 숫자 - 전체객체 개수 확인용
    } else {
        // filter : 해당 조건이 true인 경우 값을 남긴다
        SELECT_SEQ_LIST = SELECT_SEQ_LIST.filter((item) => item !== selectSeq); // unchecked 경우 제거
        console.log('SELECT_SEQ_LIST del= '+ SELECT_SEQ_LIST);
        console.log('dynamicNum = ' + dynamicNum);

        dynamicNum--; // 동적 숫자
    }
    selectAllBtn.checked = selectBtns.length === dynamicNum;
    console.log("prod : " + SELECT_SEQ_LIST);
};


// 전체 상품 선택 함수 - 묶음 선택
const selectAllBNDL = function (sequenceName) {
    const check = bndlAllBtn.checked; // true, false(default)

    bndlBtns.forEach((bndlBtn) => {
        if (bndlBtn === null) {
            return;
        }
        bndlBtn.checked = check; // 우변의 checked 여부에 따라서 좌변의 checked 여부 변경
        console.log(bndlBtn.getAttribute(sequenceName))
        const selectSeq = parseInt(
            bndlBtn.getAttribute(sequenceName) // "dlvar_id"
        ); // 필요 객체 seq

        check ? DLVAR_SEQ_LIST.push(selectSeq) : (DLVAR_SEQ_LIST = []);
        // 중복 요소 제거
        DLVAR_SEQ_LIST = DLVAR_SEQ_LIST.filter(
            (value, index) => DLVAR_SEQ_LIST.indexOf(value) === index
        );
    });
    DLVAR_DYNAMIC_NUM = check ? bndlBtns.length : 0;

    console.log("all : " + DLVAR_SEQ_LIST);
};

// 상품 선택 함수 - 묶음 선택
const selectBNDL = function (event,sequenceName) {
    const targetBtn = event.target; // click한 btn 요소

    const selectSeq = parseInt(
        targetBtn.getAttribute(sequenceName) // 'dlvar_id'
    ); // 필요 객체 seq
    console.log(selectSeq);
    if (targetBtn.checked) {
        DLVAR_SEQ_LIST.push(selectSeq); // 리스트에 담음
        console.log('DLVAR_SEQ_LIST push= '+ DLVAR_SEQ_LIST);
        console.log('DLVAR_DYNAMIC_NUM = ' + DLVAR_DYNAMIC_NUM);
        DLVAR_DYNAMIC_NUM++; // 동적 숫자 - 전체객체 개수 확인용
    } else {
        // filter : 해당 조건이 true인 경우 값을 남긴다
        DLVAR_SEQ_LIST = DLVAR_SEQ_LIST.filter((item) => item !== selectSeq); // unchecked 경우 제거
        console.log('DLVAR_SEQ_LIST del= '+ DLVAR_SEQ_LIST);
        console.log('DLVAR_DYNAMIC_NUM = ' + DLVAR_DYNAMIC_NUM);

        DLVAR_DYNAMIC_NUM--; // 동적 숫자
    }
    bndlAllBtn.checked = bndlBtns.length === DLVAR_DYNAMIC_NUM;
    console.log("prod : " + DLVAR_SEQ_LIST);
};