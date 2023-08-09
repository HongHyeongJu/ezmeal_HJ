
function getCategoryName(cate_cd) {
        switch (cate_cd) {
            case '0101':
                return '닭가슴살';
            case '0102':
                return '스테이크';
            case '0103':
                return '소시지';
            case '0201':
                return '도시락';
            case '0202':
                return '볶음밥';
            case '0301':
                return '샐러드';
            case '0302':
                return '소스';
            case '0401':
                return '채소/과일';
            case '0402':
                return '달걀';
            case '0501':
                return '분식';
            case '0502':
                return '반찬/밀키트';
            case '0503':
                return '면';
            case '0504':
                return '소스';
            case '0601':
                return '닭가슴살세트';
            case '0602':
                return '도시락세트';
            case '0603':
                return '샐러드세트';
            case '0604':
                return '신선세트';
            case '0605':
                return '간편식세트';
            default:
                return ''; // Return empty string if code doesn't match
        }
    }



 function changeCateCdToName(){
     let tableRows = document.getElementsByClassName("table_title_3 cate_cd");

        for (let i = 0; i < tableRows.length; i++) {
            let cate_cd = tableRows[i].textContent.trim();
            tableRows[i].textContent = getCategoryName(cate_cd);
        }
 }

 function changeInven() {
    let prodStusElements = document.getElementsByClassName("table_title_3 prod_stus");
    let invenStusElements = document.getElementsByClassName("table_title_3 inven_stus");

    for (let i = 0; i < prodStusElements.length; i++) {
        let prodStus = prodStusElements[i].textContent.trim();

        // Resetting the invenStusElements text and color for each iteration
        invenStusElements[i].textContent = "";
        invenStusElements[i].style.color = "black";

        switch (prodStus) {
            case "판매중":
                invenStusElements[i].textContent = "안전";
                invenStusElements[i].style.color = "green";
                break;
            case "품절임박":
                invenStusElements[i].textContent = "주의";
                invenStusElements[i].style.color = "orange";
                break;
            case "일시품절":
            case "품절":
                invenStusElements[i].textContent = "위험";
                invenStusElements[i].style.color = "red";
                break;
            case "판매중지":
                invenStusElements[i].textContent = "중지";
                break;
            case "입고예정":
                invenStusElements[i].textContent = "예정";
                invenStusElements[i].style.color = "pink";
                break;
            default:
                break;
        }
    }
}


document.addEventListener("DOMContentLoaded", function() {
    changeCateCdToName();
    changeInven();
});