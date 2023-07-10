<%--
  Created by IntelliJ IDEA.
  User: hhju2
  Date: 2023-07-01
  Time: PM 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 상품 CRUD</title>
    <link rel="stylesheet" href="/css/screens/productRegistration.css"/>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>

<div class="all_div">
    <!------------------------ 왼쪽디브 시작 ------------------------->
    <div class="half_div" id="left_div">
        <br>
        <div class="long-div">
            <h3>상품 판매 설정</h3>
            <p>상품 등록 페이지 입니다.  ${mode} </p>
        </div>
        <div class="read_only">
            <table>
                <tr class="tr_title">
                    <td>상품코드</td>
                    <td><input type="text" id="prod_cd" name="prod_cd" value="${productDto.getProd_cd()}" readonly></td>
                </tr>
                <tr>
                    <td>최초 등록자</td>
                    <td><input type="text" id="mng" name="mng" value="${productDto.getMng()}" readonly></td>
                </tr>
                <tr>
                    <td>최초 등록일시</td>
                    <td><input type="date" id="fst_reg_dt" name="fst_reg_dt" value="${productDto.getFst_reg_dt()}" readonly></td>
                </tr>
            </table>
        </div>
        <br>
        <div class="yn_table">
            <table>
                <tr class="tr_title">
                    <td>상품 타입</td>
                    <td>판매 여부</td>
                    <td>진열 여부</td>
                    <td>삭제 여부</td>
                    <td>재고 관리</td>
                </tr>
                <tr>
                    <td><input type="radio" name="sub_yn" value="y" ${mode=="READ" ? "disabled" : ""}>구독상품</td>
                    <td><input type="radio" name="sale_yn" value="y" ${mode=="READ" ? "disabled" : ""}>판매</td>
                    <td><input type="radio" name="dp_yn" value="y" ${mode=="READ" ? "disabled" : ""}>진열</td>
                    <td><input type="radio" name="del_yn" value="y" ${mode=="READ" ? "disabled" : ""}>삭제</td>
                    <td><input type="radio" name="inv_yn" value="y" ${mode=="READ" ? "disabled" : ""}>재고관리</td>
                </tr>
                <tr>
                    <td><input type="radio" name="sub_yn" value="n" ${mode=="READ" ? "disabled" : ""}>일반상품</td>
                    <td><input type="radio" name="sale_yn" value="n" ${mode=="READ" ? "disabled" : ""}>품절</td>
                    <td><input type="radio" name="dp_yn" value="n" ${mode=="READ" ? "disabled" : ""}>미진열</td>
                    <td><input type="radio" name="del_yn" value="n" ${mode=="READ" ? "disabled" : ""}>미삭제</td>
                    <td><input type="radio" name="inv_yn" value="n" ${mode=="READ" ? "disabled" : ""}>재고미관리</td>
                </tr>
            </table>
        </div>
        <!---------------------------------------------------------------->
        <br>
        <div class="form-row">
            <label for="name">판매용 상품명:</label>
            <input type="text" id="name" name="name" value="${productDto.getName()}" ${mode=="READ" ? "readonly" : ""}>
        </div>
        <div class="form-row">
            <label for="name">관리용 상품명:</label>
            <input type="text" id="mng_prod_nm" name="mng_prod_nm" value="${productDto.getMng_prod_nm()}" ${mode=="READ" ? "readonly" : ""}>
        </div>
        <!-------------------------------------------------------------------->
        <br>
        <div class="form-row">
            <table>
                <tr class="tr_title">
                    <td>카테고리</td>
                    <td  colspan="3">
                        <label for="cate_cd" name="cate_cd">세부카테고리:</label>
                        <select  id="cate_cd"   ${mode=="READ" ? "readonly" : ""}>
                            <option value="0101">훈제닭가슴살</option>
                            <option value="0102">저염닭가슴살</option>
                            <option value="0103">스팀닭가슴살</option>
                            <option value="0104">소스닭가슴살</option>
                            <option value="0105">스테이크</option>
                            <option value="0106">소시지</option>
                            <option value="0201">볶음밥</option>
                            <option value="0202">도시락</option>
                            <option value="0301">샐러드</option>
                            <option value="0302">소스</option>
                            <option value="0401">과일.야채</option>
                            <option value="0402">달걀</option>
                            <option value="0501">분식</option>
                            <option value="0502">반찬.밀키트</option>
                            <option value="0503">면</option>
                            <option value="0504">소스</option>
                        </select>
                    </td>
                </tr>
                <tr class="tr_title">
                    <td>보관상태</td>
                    <td><input type="radio" name="opt_yn" value="n" ${mode=="READ" ? "disabled" : ""}>옵션X
                        <input type="radio" name="opt_yn" value="y" ${mode=="READ" ? "disabled" : ""}>옵션O</td>
                    <td colspan="2">
                        <label for="opt_yn" name="opt_yn">상품옵션:</label>
                        <select  id="opt_yn"   ${mode=="READ" ? "readonly" : ""}>
                            <option value="ts02">맛 2종</option>
                            <option value="ts03">맛 3종</option>
                            <option value="set10">10개 묶음</option>
                            <option value="set20">20개 묶음</option>
                        </select>
                    </td>
                </tr>
                <tr class="tr_title">
                    <td>옵션별 추가 상품명</td>
                    <td id="opt_detail" colspan="3">
                        <input type="text" name="opt_detail" value="" >
                        <input type="text" name="opt_detail" value="" >
                        <input type="text" name="opt_detail" value="" >
                        <input type="text" name="opt_detail" value="" >   <!-- 옵션 O여야 활성화 되도록 -->
                    </td>
                </tr>
            </table>

        </div>
        <!-------------------------------------------------------------------->
        <br>
        <div class="form-row">
            <label for="name" name="dc_cd">할인 옵션:</label>
            <select  id="dc_cd"   ${mode=="READ" ? "readonly" : ""}>
                <option value="pd">1000원 할인</option>
                <option value="rd">10% 할인</option>
            </select>
        </div>
        <!-------------------------------------------------------------------->
        <div class="form-row">
            <label for="dc_cd_detail">할인 내용</label>
            <label id="dc_cd_print">${productDto.getDc_cd()}</label>
            <input type="text" id="dc_cd_detail" name="할인 DTO toString()" value="" readonly>  <!--value : 할인 DTO toString() 오버라이딩한부분 출력-->
        </div>
        <!-------------------------------------------------------------------->
        <div class="form-row">
            <label for="name">거래처:</label>
            <select id="cust_cd" name="cust_cd" ${mode=="READ" ? "readonly" : ""}>
<%--                <option value="메롱" disabled selected></option>--%>
                <option value="CUST001">잇메이트</option>
                <option value="CUST002">맛있닭</option>
                <option value="CUST003">허닭</option>
                <option value="CUST004">아임닭</option>
                <option value="CUST005">러브잇</option>
                <option value="CUST006">1am</option>
                <option value="CUST007">위잇</option>
            </select>
        </div>
        <!-------------------------------------------------------------------->
        <div class="price_calculation_table">
            <table>
                <tr class="tr_title">
                    <td>공급가</td>
                    <td>소비자가</td>
                    <td>판매가</td>
                    <td>마진율(자동계산)</td>
                </tr>
                <tr>
                    <td><input type="number" id="sp_prc" name="sp_prc" value="${productDto.getSp_prc()}" min="0" ${mode=="READ" ? "readonly" : ""}><p>원</p></td>
                    <td><input type="number" id="cnsmr_prc" name="cnsmr_prc" value="${productDto.getCnsmr_prc()}" min="0" ${mode=="READ" ? "readonly" : ""}><p>원</p></td>
                    <td><input type="number" id="sale_prc" name="sale_prc" value="${productDto.getSale_prc()}" min="0" ${mode=="READ" ? "readonly" : ""}><p>원</p></td>
                    <td><input type="number" id="mgn_rate" name="mgn_rate" value="${productDto.getMgn_rate()}" ${mode=="READ" ? "readonly" : ""}><p>%</p></td>
                </tr>
            </table>
        </div>
        <!-------------------------------------------------------------------->

        <!-------------------------------------------------------------------->
    </div><!-------------------------- 왼쪽디브 끝 ------------------------->
    <!---------------------------------------------------------------------------------------------------------------------------------------------->
    <!------------------------ 오른쪽디브 시작 ----------------------->
    <div class="half_div" id="right_div">
        <br><br><br><br><br>
        <div class="storage_table">
            <table>
                <tr class="tr_title">
                    <td>보관상태</td>
                    <td><input type="radio" name="sfkp_stus" value="상온" ${mode=="READ" ? "disabled" : ""}>상온
                        <input type="radio" name="sfkp_stus" value="냉동" ${mode=="READ" ? "disabled" : ""}>냉동
                        <input type="radio" name="sfkp_stus" value="냉장" ${mode=="READ" ? "disabled" : ""}>냉장</td>
                </tr>
                <tr>
                    <td>보관방법</td>
                    <td><input type="radio" name="sfkp_mtd" value="실온보관" ${mode=="READ" ? "disabled" : ""}>실온보관
                        <input type="radio" name="sfkp_mtd" value="냉장보관(0~10도)" ${mode=="READ" ? "disabled" : ""}>냉장보관(0~10도)
                        <input type="radio" name="sfkp_mtd" value="-18도 이하 냉동보관" ${mode=="READ" ? "disabled" : ""}>-18도 이하 냉동보관</td>
                </tr>
                <tr>
                    <td>상품 요약설명</td>
                    <td><textarea id="dscpt" name="dscpt" rows="4" cols="60" ${mode=="READ" ? "readonly" : ""}>${productDto.getDscpt()}</textarea></td>
                </tr>
                <tr>
                    <td>상품 상세설명</td>
                    <td><textarea id="detail" name="detail" rows="4" cols="60" ${mode=="READ" ? "readonly" : ""}>${productDto.getDetail()}</textarea></td>
                </tr>
                <tr>
                    <td>비고</td>
                    <td><textarea id="rmk" name="rmk" rows="4" cols="60" ${mode=="READ" ? "readonly" : ""}>${productDto.getRmk()}</textarea></td>
                </tr>
            </table>
        </div>
        <!-------------------------------------------------------------------->
        <div class="gram_table">
            <table>
                <tr class="tr_title">
                    <td>상품 최소 수량</td>
                    <td>상품 중량</td>
                    <td>상품 규격</td>
                </tr>
                <tr>
                    <td><input type="number" id="min_qty" name="min_qty" value="${productDto.getMin_qty()}" min="1" value="1" ${mode=="READ" ? "readonly" : ""}></td>
                    <td><input type="number" id="weight" name="weight" value="${productDto.getWeight()}" ${mode=="READ" ? "readonly" : ""}></td>
                    <td><input type="text" id="stnd" name="stnd" value="${productDto.getStnd()}" placeholder="가로*세로*너비" ${mode=="READ" ? "readonly" : ""}></td>
                </tr>
            </table>
        </div>
        <!-------------------------------------------------------------------->
        <div class="detail_set_table">
            <table>
                <tr class="tr_title">
                    <td>조리법</td>
                    <td colspan="3" rows="3" cols="60" ><textarea id="recipe" name="recipe" rows="4" cols="60"  ${mode=="READ" ? "readonly" : ""}>${productDto.getRecipe()}</textarea></td>
                </tr>
                <tr class="tr_title">
                    <td>활용법</td>
                    <td colspan="3" rows="3" cols="60" ><textarea id="mtd" name="mtd" rows="4" cols="60"  ${mode=="READ" ? "readonly" : ""}>${productDto.getMtd()}</textarea></td>
                </tr>
                <tr>
                    <td>소비기한</td>
                    <td><input type="text" id="distb_tlmt" name="distb_tlmt" value="${productDto.getDistb_tlmt()}" ${mode=="READ" ? "readonly" : ""}></td>
                    <td>원산지</td>
                    <td><input type="text" id="orplc" name="orplc" value="${productDto.getOrplc()}" ${mode=="READ" ? "readonly" : ""}></td>
                </tr>
                <tr>
                    <td>유효 시작일</td>
                    <td><input type="date" id="vld_start_dt" name="vld_start_dt" value="${productDto.getVld_start_dt()}" ${mode=="READ" ? "readonly" : ""}></td>
                    <td>유효 종료일</td>
                    <td><input type="date" id="vld_end_dt" name="vld_end_dt" value="${productDto.getVld_end_dt()}" ${mode=="READ" ? "readonly" : ""}></td>
                </tr>
            </table>
        </div>
        <!-- 파일 업로드 나중에 -->
        <br>
        <div class="btn_set">
            <button class="prod_btn" id="regi_btn">상품 등록</button>
            <button class="prod_btn"  id="modi_btn">상품 수정</button>
            <button class="prod_btn"  id="del_btn">상품 삭제</button>
            <button class="prod_btn"  id="list_btn">상품 목록</button>
            <button class="prod_btn"  id="detail_btn">미리보기</button>
        </div>
    </div>
    <!------------------------ 오른쪽디브 끝 ----------------------->

</div><!------------------------ 전체 디브 끝 ----------------------->

<!------------------------ 스크립트 자리 ----------------------->

<!-- JavaScript 코드 -->
<script>

    /*-------------- 저장된 상품 읽을 때 필요한 것 -------------*/

    // 등록된 상품 정보 가져오기
    let productInfo = {
        sub_yn: '${productDto.getSub_yn()}',   // 구독상품 선택
        sale_yn:  '${productDto.getSale_yn()}',  // 품절 선택
        dp_yn:  '${productDto.getDp_yn()}',    // 진열 선택
        del_yn:  '${productDto.getDel_yn()}',   // 미삭제 선택
        inv_yn:  '${productDto.getInv_yn()}',    // 재고관리 선택
        /*--------------------------------*/
        opt_yn:  '${productDto.getOpt_yn()}',    // 옵션 여부 선택
        /*--------------------------------*/
        sfkp_stus:  '${productDto.getSfkp_stus()}',  //보관상태
        sfkp_mtd :  '${productDto.getSfkp_mtd()}'    //보관방법

    };

    // 등록된 상품 정보에 따라 라디오 버튼 선택하기
    for (let key in productInfo) {
        let value = productInfo[key];
        let radioBtns = document.getElementsByName(key);

        for (let i = 0; i < radioBtns.length; i++) {
            if (radioBtns[i].value === value) {
                radioBtns[i].checked = true;
                break;
            }
        }
    }

    /* select Option 상품 정보로 selected = true 만들어주기 */
    let productCodeInfo = {
        dc_cd: '${productDto.getDc_cd()}',   // 할인코드 선택
        cust_cd:  '${productDto.getCust_cd()}',  // 거래처코드 선택
    };

    // 등록된 상품 정보에 따라 셀렉트 옵션 버튼 선택하기
    for (let key in productCodeInfo) {
        let value = productCodeInfo[key];
        let selectBtns = document.getElementsByName(key);

        for (let i = 0; i < selectBtns.length; i++) {
            if (selectBtns[i].value === value) {
                console.log(selectBtns[i]);
                selectBtns[i].selected = true;
                break;
            }
        }
    }
/*----------------------------- 저장된 상품 읽을 때 필요한 것 끝 ----------------------------------*/

/*---현재 HTML 값으로 객체 생성하기 위해 필요한 정보.  name으로 묶어서 라디오 checked된것만, 셀렉트 옵션도 선택된것만, 현재 날짜도!*/
    /*현재  날짜+시간*/
    let currentDate = new Date();

    /*오늘 날짜만 2023/04/05 형식으로 -> fst_reg_dt를 위한 것*/
        let year = currentDate.getFullYear();
        let month = String(currentDate.getMonth() + 1).padStart(2, '0');
        let day = String(currentDate.getDate()).padStart(2, '0');
    let fst_reg_dt = year + '/' + month + '/' + day;

    /*라디오 값 가져오자*/
    let selectedSub = document.querySelector('input[name="sub_yn"]:checked');
    let selectedSale = document.querySelector('input[name="sale_yn"]:checked');
    let selectedDp = document.querySelector('input[name="dp_yn"]:checked');
    let selectedDel = document.querySelector('input[name="del_yn"]:checked');
    let selectedInv = document.querySelector('input[name="inv_yn"]:checked');
    /*------*/
    let selectedOpt = document.querySelector('input[name="opt_yn"]:checked');
    /*------*/
    let selectedSfkp_stus = document.querySelector('input[name="sfkp_stus"]:checked');
    let selectedSfkp_mtd = document.querySelector('input[name="sfkp_mtd"]:checked');

    let sub_yn = selectedSub ? selectedSub.value : "";
    let sale_yn = selectedSale ? selectedSale.value : "";
    let dp_yn = selectedDp ? selectedDp.value : "";
    let del_yn = selectedDel ? selectedDel.value : "";
    let inv_yn = selectedInv ? selectedInv.value : "";
    /*------*/
    let opt_yn = selectedOpt ? selectedOpt.value : "";
    /*------*/
    let sfkp_stus = selectedSfkp_stus ? selectedSfkp_stus.value : "";
    let sfkp_mtd = selectedSfkp_mtd ? selectedSfkp_mtd.value : "";


    /*셀렉트 옵션 값 가져오기*/
    let selectElementCate_cd = document.getElementById('cate_cd');
    let selectedValueCate_cd = selectElementCate_cd.options[selectElementCate_cd.selectedIndex].value || '';

    let selectElementCust_cd = document.getElementById('cust_cd');
    let selectedValueCust_cd = selectElementCust_cd.options[selectElementCust_cd.selectedIndex].value || '';

    let selectElementDc_cd = document.getElementById('dc_cd');
    let selectedValueDc_cd = selectElementDc_cd.options[selectElementDc_cd.selectedIndex].value || '';

    let modelMsg = '${mode}';

    /*상품 등록 버튼 누르면 제출하기*/
    $(document).ready(function() {

        $('#regi_btn').click(function() {
            /*--------------------- 새 상품 작성, 등록된 상품 정보 수정 시 필요한 JSON으로 객체 보내기-----------*/
            let makeProduct = {
                prod_cd: (modelMsg === "WRITE") ? "P00000" : document.getElementById('prod_cd').value,
                cate_cd: selectElementCate_cd,  /*셀렉트 옵션*/
                prod_stus: '1',
                cust_cd: selectedValueCust_cd,   /*셀렉트 옵션*/
                dc_cd: selectedValueDc_cd,   /*셀렉트 옵션*/
                name: document.getElementById('name').value,
                mng_prod_nm: document.getElementById('mng_prod_nm').value,
                sfkp_stus: sfkp_stus,  /*라디오*/
                sfkp_mtd: sfkp_mtd,  /*라디오*/
                sp_prc: document.getElementById('sp_prc').value,
                cnsmr_prc: document.getElementById('cnsmr_prc').value,
                sale_prc: document.getElementById('sale_prc').value,
                mgn_rate: document.getElementById('mgn_rate').value,
                dscpt: document.getElementById('dscpt').value,
                detail: document.getElementById('detail').value,
                min_qty: document.getElementById('min_qty').value,
                weight: document.getElementById('weight').value,
                stnd: document.getElementById('stnd').value,
                orplc: document.getElementById('orplc').value,
                recipe: document.getElementById('recipe').value,
                mtd: document.getElementById('mtd').value,
                distb_tlmt: document.getElementById('distb_tlmt').value,
                vld_start_dt: document.getElementById('vld_start_dt').value,
                vld_end_dt: document.getElementById('vld_end_dt').value,
                mng: document.getElementById('mng').value,
                fst_reg_dt: fst_reg_dt,
                sub_yn: sub_yn, /*라디오*/
                sale_yn: sale_yn, /*라디오*/
                dp_yn: dp_yn, /*라디오*/
                del_yn: del_yn, /*라디오*/
                inv_yn: inv_yn, /*라디오*/
                opt_yn: opt_yn, /*라디오*/
                rmk: document.getElementById('rmk').value,
                in_dtm: currentDate,
                in_id: 'ateam04',   /*원래는 세션의 관리자 아이디 가져와야함*/
                up_dtm: currentDate,
                up_id: 'ateam04'
            };

            $.ajax({
                url: 'product/regist/write',
                type: 'POST',
                headers: {"content-type": "application/json"},
                dataType: 'text', // 서버에서 받을 데이터 형식
                data: JSON.stringify(makeProduct),
                success: function () {
                    // 요청이 성공적으로 처리되었을 때의 동작
                    console.log('요청이 성공적으로 처리되었습니다.');

                    // 상품등록 완료 알림을 띄우기
                    alert('상품 등록이 완료되었습니다.');

                    // 뷰로 모드 정보 전달
                    // model.addAttribute("mode", "WRITE_OK");
                },

                error: function (jqXHR, textStatus, errorThrown) {
                    console.log('요청이 실패하였거나 처리되지 않았습니다.');
                    console.log('jqXHR:', jqXHR);
                    console.log('textStatus:', textStatus);
                    console.log('errorThrown:', errorThrown);
                    alert("error");
                }
            });

        })

    }
    );

</script>



</body>
</html>
