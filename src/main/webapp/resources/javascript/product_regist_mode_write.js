

/*-----  공급가, 소비자가, 할인코드에 이벤트 등록하기 ----*/
window.addEventListener('DOMContentLoaded', (event) => {

    /* 공급가, 소비자가, 할인코드에 이벤트 등록하기 */
    document.getElementById('sp_prc').addEventListener('input', calculate);
    document.getElementById('cnsmr_prc').addEventListener('input', calculate);
    document.getElementById('dc_cd').addEventListener('change', calculate);

    calculate();

    function calculate() {
        // 필요한 값을 가져오기
        let sp_prc = parseFloat(document.getElementById('sp_prc').value) || 0;
        let cnsmr_prc = parseFloat(document.getElementById('cnsmr_prc').value) || 0;
        let dc_cd = document.getElementById('dc_cd');

        let selectedOption = dc_cd.options[dc_cd.selectedIndex];
        let discountType = selectedOption.getAttribute('data-discount-type');
        let discountRate = parseFloat(selectedOption.getAttribute('data-discount-rate')) || 0;
        let discountPrc = parseFloat(selectedOption.getAttribute('data-discount-prc')) || 0;
        console.log("dc_cd"+dc_cd+", selectedOption"+selectedOption
            +", discountType"+discountType+", discountRate"+discountRate+", discountPrc"+discountPrc);


        // 할인된 판매가를 계산하고 결과를 표시합니다.
        let sale_prc = 0;
        if (discountType === 'prc') {
            sale_prc = cnsmr_prc - discountPrc;
        } else if (discountType === 'pt') {
            sale_prc = cnsmr_prc - (cnsmr_prc * (discountRate / 100));
        }
        document.getElementById('sale_prc').value = Math.round(sale_prc);
        console.log("가 sale_prc.toFixed(2)"+sale_prc);

        // 할인율을 계산하고 결과를 표시합니다.
        let dc_rate = cnsmr_prc !== 0 ? ((cnsmr_prc - sale_prc) / cnsmr_prc) * 100 : 0;
        document.getElementById('dc_rate').value = Math.round(dc_rate);
        console.log("나 dc_rate.toFixed(2)"+dc_rate);

        // 마진율을 계산하고 결과를 표시합니다.
        let mgn_rate = sale_prc !== 0 ? ((sale_prc - sp_prc) / sp_prc) * 100 : 0;
        document.getElementById('mgn_rate').value = Math.round(mgn_rate);

        console.log("다 mgn_rate:"+mgn_rate);
    }
})


/*----- 보관상태 직접입력 선택시에만 텍스트칸 활성화-----*/
/* 직접입력 라디오 태그 가져오기 */
let directInputRadio = document.querySelector('input[name="product.sfkp_mtd"][value="직접입력"]');
/* 입력 텍스트 내용 태그 */
let customInput = document.getElementById("custom_sfkp_mtd");
/* 직접입력 라디오 눌렀을때만 직접입력 활성화 */
directInputRadio.addEventListener("click", function() {
    customInput.disabled = false;
});
/* 직접입력 라디오 입력값으로 텍스트 값 넘어가도록 */
customInput.addEventListener("input", function() {
    directInputRadio.value = customInput.value;
});



/* 상품 및 옵션 배열 등록 */
document.querySelector('#productForm').addEventListener('submit', function(event) {
    event.preventDefault();

    /*옵션 배열 먼저 가져오기*/
    let productOptionDto = Array.from(document.querySelectorAll('.opt_list_ul')).map((ul, i) => {
        return {
            name: ul.querySelector(`input[name="options[${i}].name"]`).value,
            qty: ul.querySelector(`input[name="options[${i}].qty"]`).value,
            dc_cd: ul.querySelector(`select[name="options[${i}].dc_cd"]`).value,
            cnsmr_prc: ul.querySelector(`input[name="options[${i}].cnsmr_prc"]`).value,
            sale_prc: ul.querySelector(`input[name="options[${i}].sale_prc"]`).value,
            dc_rate: ul.querySelector(`input[name="options[${i}].dc_rate"]`).value,
            rmk: ul.querySelector(`textarea[name="options[${i}].rmk"]`).value,
        };
    });

    let opt_yn = productOptionDto.length > 0 ? 'y' : 'n';

    //등록날짜
    const currentDate = new Date();
    // const year = currentDate.getFullYear();
    // const month = String(currentDate.getMonth() + 1).padStart(2, '0');
    // const day = String(currentDate.getDate()).padStart(2, '0');
    // const formattedDate = `${year}-${month}-${day}`;

    // HTML 폼 요소에 접근하여 메인 상품 값을 가져오기
    const productDto = {
        prod_cd: document.querySelector('#prod_cd').value,
        cate_cd: document.querySelector('#cate_cd').value,
        prod_stus: document.querySelector('#prod_stus').options[document.querySelector('#prod_stus').selectedIndex].value,
        cust_cd: document.querySelector('#cust_cd').value,
        dc_cd: document.querySelector('#dc_cd').value,
        name: document.querySelector('#name').value,
        mng_prod_nm: document.querySelector('#mng_prod_nm').value,
        sfkp_stus: document.querySelector('input[name="product.sfkp_stus"]:checked').value,
        sfkp_mtd: document.querySelector('input[name="product.sfkp_mtd"]:checked').value,
        sp_prc: parseInt(document.querySelector('#sp_prc').value),
        cnsmr_prc: parseInt(document.querySelector('#cnsmr_prc').value),
        sale_prc: parseInt(document.querySelector('#sale_prc').value),
        dc_rate: parseInt(document.querySelector('#dc_rate').value),
        mgn_rate: parseInt(document.querySelector('#mgn_rate').value),
        dscpt: document.querySelector('#dscpt').value,
        detail: document.querySelector('#detail').value,
        min_qty: parseInt(document.querySelector('#min_qty').value),
        weight: parseInt(document.querySelector('#weight').value),
        stnd: document.querySelector('#stnd').value,
        orplc: document.querySelector('#orplc').value,
        recipe: document.querySelector('#recipe').value,
        mtd: document.querySelector('#mtd').value,
        distb_tlmt: document.querySelector('#distb_tlmt').value,
        vld_start_dt: document.querySelector('#vld_start_dt').value,
        vld_end_dt: document.querySelector('#vld_end_dt').value,
        mng: document.querySelector('#mng').value,
        fst_reg_dt: currentDate,
        sale_yn: document.querySelector('input[name="product.sale_yn"]:checked').value,
        dp_yn: document.querySelector('input[name="product.dp_yn"]:checked').value,
        del_yn: 'n',
        inv_yn: document.querySelector('input[name="product.inv_yn"]:checked').value,
        opt_yn: opt_yn,
        rmk: document.querySelector('#rmk').value,
        in_id: 'ezmeal4',
        up_id: 'ezmeal4'
    };

    // 서버에 POST 요청을 보냅니다.
    $.ajax({
        url: '/admin/prod/write',  // 실제 서버의 URL로 변경해야 합니다.
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({ productDto: productDto, productOptionDto: productOptionDto }),
        success: function(response) {
            // 요청이 성공하면 이 부분이 실행됩니다.
            alert("등록 성공");
            console.log(response); // 서버에서 보낸 응답 데이터 확인 (Object 형태로 출력)
            const prodInsertCnt = response.prodInsertCnt;
            const prodUpdateCnt = response.prodUpdateCnt;
            const optInsertCnt = response.optInsertCnt;
            const optUpdateCnt = response.optUpdateCnt;
            if(prodInsertCnt>0){
                alert("상품 등록 1건 및 옵션"+optInsertCnt+"개 등록, "+optInsertCnt+"개 수정");
            } else {
                alert("상품 수정 1건 및 옵션"+optInsertCnt+"개 등록, "+optInsertCnt+"개 수정");
            }

        },
        error: function(error) {
            // 요청이 실패하면 이 부분이 실행됩니다.
            alert("등록 실패");
            console.log(error);
        },
    });

});
