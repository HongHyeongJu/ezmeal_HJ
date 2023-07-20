
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
        document.getElementById('sale_prc').value = sale_prc.toFixed(2);
        console.log("sale_prc.toFixed(2)"+sale_prc.toFixed(2));

        // 할인율을 계산하고 결과를 표시합니다.
        let dc_per = cnsmr_prc !== 0 ? ((cnsmr_prc - sale_prc) / cnsmr_prc) * 100 : 0;
        document.getElementById('dc_per').value = dc_per.toFixed(2);
        console.log("dc_per.toFixed(2)"+dc_per.toFixed(2));

        // 마진율을 계산하고 결과를 표시합니다.
        let mgn_rate = sale_prc !== 0 ? ((sale_prc - sp_prc) / sp_prc) * 100 : 0;
        document.getElementById('mgn_rate').value = mgn_rate.toFixed(2);

        console.log("mgn_rate:"+mgn_rate);


    }
})




