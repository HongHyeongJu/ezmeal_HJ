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
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Nanum+Gothic&display=swap" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
<form id="productForm" action="/product/regist/write" method="post">
    <div class="container">
        <div class="left_bigdiv">
            <div class="left page_info_div">
                <h3>상품 판매 설정</h3>
                <p>상품 등록 페이지 입니다.  ${mode} </p>
            </div>
            <!--------------- page_info_div ---------------->
            <div class="left product_info_div">
                <table class="product_info_table">
                    <tr>
                        <th>상품코드</th>
                        <td><input type="text" id="prod_cd" name="product.prod_cd" value="${product.getProd_cd()}" class="readonly_input" readonly></td>
                        <th>최초 등록자</th>
                        <td><input type="text" id="mng" name="product.mng" value="${product.getMng()}" class="readonly_input"  readonly></td>
                        <th>최초 등록일시</th>
                        <td>
                            <input type="date" id="fst_reg_dt" name="product.fst_reg_dt" value="${product.getFst_reg_dt()}" class="readonly_input"  readonly>
                            <input type="hidden" name="product.del_yn" value="${product.getDel_yn()}" >
                            <input type="hidden" id="optList_size_count" value="${optList.size()}" >
                        </td>
                    </tr>
                </table>
            </div>
            <!--------------- product_info_div ---------------->

            <div class="left yn_columns_div">
                <table class="yn_columns_div_table">
                    <tr class="yn_columns_first_tr">
                        <th>상품 상태</th>
                        <th>판매 여부</th>
                        <th>진열 여부</th>
                        <th>재고관리 여부</th>
                    </tr>
                    <tr>
                        <td>
                            <select id="prod_stus" name="product.prod_stus" ${mode == 'READ' ? 'readonly' : ''} >
                                <c:forEach items="${stusList}" var="stus">
                                    <option value="${stus.prod_stus_cd}"
                                            <c:if test="${not empty product.getProd_stus() and stus.prod_stus_cd == product.getProd_stus()}">selected</c:if>
                                    >${stus.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td><label><input type="radio" name="product.sale_yn" value="y" <c:if test="${product.getSale_yn() == 'y'}">checked</c:if> >판매</label></td>
                        <td><label><input type="radio" name="product.dp_yn" value="y" <c:if test="${product.getDp_yn() == 'y'}">checked</c:if> >진열</label></td>
                        <td><label><input type="radio" name="product.inv_yn" value="y" <c:if test="${product.getInv_yn() == 'y'}">checked</c:if> >재고관리</label></td>
                    </tr>
                    <tr>
                        <td ></td>
                        <td><label><input type="radio" name="product.sale_yn" value="n" <c:if test="${product.getSale_yn() == 'n'}">checked</c:if> >미판매</label></td>
                        <td><label><input type="radio" name="product.dp_yn" value="n" <c:if test="${product.getDp_yn() == 'n'}">checked</c:if> >미진열</label></td>
                        <td><label><input type="radio" name="product.inv_yn" value="n" <c:if test="${product.getInv_yn() == 'n'}">checked</c:if> >재고미관리</label></td>
                    </tr>

                </table>
            </div>
            <!--------------- yn_columns_div ---------------->

            <div class="left product_names_div">
                <label for="name" class="name_label"><strong>상품 거래처:</strong>
                </label>
                <select id="cust_cd" name="product.cust_cd">
                    <c:forEach items="${custList}" var="cust">
                        <option value="${cust.cust_cd}"
                                <c:if test="${not empty product.getCust_cd() and cust.cust_cd == product.getCust_cd()}">selected</c:if>
                        >${cust.cust_nm}</option>
                    </c:forEach>
                </select>
                <label for="name" class="name_label2"><strong>상품 카테고리:</strong>
                </label>
                <select id="cate_cd" name="product.cate_cd">
                    <c:forEach items="${cateList}" var="cate">
                        <option value="${cate.cate_cd}"
                                <c:if test="${not empty product.getCate_cd() and cate.cate_cd == product.getCate_cd()}">selected</c:if>
                        >${cate.name}</option>
                    </c:forEach>
                </select>
                <br>
                <label for="name" class="name_label"><strong>판매용 상품명:</strong></label>
                <input type="text" id="name" name="product.name"  value="${product.getName()}" maxlength="12">
                <br>
                <label for="name" class="name_label"><strong>관리자용 상품명:</strong></label>
                <input type="text" id="mng_prod_nm" name="product.mng_prod_nm"  value="${product.getMng_prod_nm()}" maxlength="30">
            </div>
            <!--------------- product_names_div ---------------->

            <div class="left price_set_div">
                <table class="price_set_div_table">
                    <tr class="dc_label">
                        <th>공급가</th>
                        <th>소비자가</th>
                        <th>할인코드</th>
                    </tr >
                    <tr class="dc_input">
                        <td><input type="number" id="sp_prc" name="product.sp_prc" value="${product.getSp_prc()}" min="0" ><p>원</p></td>
                        <td>
                            <input type="number" id="cnsmr_prc" name="product.cnsmr_prc" value="${product.getCnsmr_prc()}" min="0" ${mode=='READ' || (product.getOpt_yn()=='y') ? 'disabled' :'' }>
                            <p>원</p>
                        </td>
                        <td>
                            <select id="dc_cd" name="product.dc_cd">
                                <c:forEach items="${dcList}" var="dc">
                                    <option value="${dc.dc_cd}"
                                            data-discount-type="${dc.typ}"
                                            data-discount-rate="${dc.rate}"
                                            data-discount-prc="${dc.prc}"
                                            <c:if test="${not empty product.getDc_cd() and dc.dc_cd == product.getDc_cd()}">selected</c:if>
                                    >${dc.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr class="dc_label">
                        <td><strong>판매가</strong></td>
                        <td><strong>마진율</strong></td>
                        <td><strong>할인율</strong></td>
                    </tr>
                    <tr class="dc_input">
                        <td><input type="number" id="sale_prc" name="product.sale_prc" value="${product.getSale_prc()}" min="0" disabled><p>원</p></td>
                        <td><input type="number" id="mgn_rate" name="product.mgn_rate" value="${product.getMgn_rate()}" disabled><p>%</p></td>
                        <td><input type="number" id="dc_rate" name="product.dc_rate"  value="${product.getDc_rate()}"  disabled ><p>%</p></td>
                    </tr>
                </table>
            </div>
            <!--------------- price_set_div ---------------->

            <div class="left make_options_div">

                <div class="make_opt_btn_div">
                    <button class="make_opt_btn">+</button>
                    <span>옵션 생성하기</span>
                    <input type="hidden" name="product.opt_yn" value="${product.getOpt_yn()}">
                </div>

                <div class="make_option_ulli">
                    <c:forEach items="${optList}" var="option" varStatus="status">
                        <ul class="opt_list_ul">

                            <!-- [1] 옵션명 -->
                            <li>
                                <span>옵션명</span>
                                <input type="text" name="options[${status.index}].name" value="${option.name}">
                            </li>

                            <!-- [2] 옵션수량 -->
                            <li>
                                <span>옵션수량</span>
                                <input type="number" name="options[${status.index}].qty" value="${option.qty}" min="0">
                            </li>

                            <!-- [3] 할인코드 -->
                            <li>
                                <span>할인코드</span>
                                <select name="options[${status.index}].dc_cd">
                                    <c:forEach items="${dcList}" var="dc">
                                        <option value="${dc.dc_cd}"
                                                <c:if test="${not empty option.dc_cd and dc.dc_cd == option.dc_cd}">selected</c:if>
                                        >${dc.name}</option>
                                    </c:forEach>
                                </select>
                            </li>
                            <br>

                            <!-- [4] 소비자가 -->
                            <li>
                                <span>소비자가</span>
                                <input type="number" name="options[${status.index}].cnsmr_prc" value="${option.cnsmr_prc}" min="0">
                            </li>

                            <!-- [5] 판매가 -->
                            <li>
                                <span>판매가</span>
                                <input type="number" name="options[${status.index}].sale_prc" value="${option.sale_prc}" min="0">
                            </li>

                            <!-- [6] 할인율 -->
                            <li>
                                <span>할인율</span>
                                <input type="number" name="options[${status.index}].dc_rate" value="${option.dc_rate}" min="0">
                            </li>
                            <br>

                            <!-- [7] 비고, x버튼 -->
                            <li class="last_li">
                                <span class="span_rmk">비고</span>
                                <textarea class="opt_rmk" name="options[${status.index}].rmk">${option.rmk}</textarea>
                                <button type="button"  class="del_opt_btn">X</button>
                            </li>
                        </ul>
                    </c:forEach>
                </div>

            </div>


            <!--------------- make_options_div ---------------->

        </div>

        <div class="right_bigdiv">
            <div class="right storage_div">
                <table class="storage_div_table">
                    <tr>
                        <th class="storage_header">보관상태</th>
                        <td>
                            <label><input type="radio" name="product.sfkp_stus" value="상온" ${product.getSfkp_stus().equals("상온") ? 'checked' : ''}>상온</label>
                            <label><input type="radio" name="product.sfkp_stus" value="냉장" ${product.getSfkp_stus().equals("냉장") ? 'checked' : ''}>냉장</label>
                            <label><input type="radio" name="product.sfkp_stus" value="냉동" ${product.getSfkp_stus().equals("냉동") ? 'checked' : ''}>냉동</label>
                        </td>
                    </tr>
                    <tr>
                        <th class="storage_header sh_2">보관방법</th>
                        <td>
                            <input type="radio" name="product.sfkp_mtd" value="실온보관" ${product.getSfkp_mtd().equals("실온보관") ? 'checked' : ''}>실온보관
                            <input type="radio" name="product.sfkp_mtd" value="냉장보관(0~10도)" ${product.getSfkp_mtd().equals("냉장보관(0~10도)") ? 'checked' : ''}>냉장보관(0~10도)
                            <input type="radio" name="product.sfkp_mtd" value="-18도 이하 냉동보관" ${product.getSfkp_mtd().equals("-18도 이하 냉동보관") ? 'checked' : ''}>-18도 이하 냉동보관
                            <br>
                            <br>
                            <input type="radio" name="product.sfkp_mtd" value="직접입력">직접입력
                            <input type="text" id="custom_sfkp_mtd" name="custom_sfkp_mtd" placeholder="직접 입력" disabled>
                        </td>
                    </tr>

                </table>
            </div>
            <!--------------- storage_div ---------------->

            <div class="right dscpt_set_div">
                <table class="storage_div_table">
                    <tr class="storage_div_tr">
                        <th class="storage_header">상품 요약설명</th>
                        <td><textarea id="dscpt" name="product.dscpt" rows="3" cols="50">${product.getDscpt()}</textarea></td>
                    </tr>
                    <tr class="storage_div_tr ">
                        <th class="storage_header">상품 상세설명</th>
                        <td><textarea id="detail" name="product.detail" rows="3" cols="50">${product.getDetail()}</textarea></td>
                    </tr>
                    <tr class="storage_div_tr">
                        <th class="storage_header">비고</th>
                        <td><textarea id="rmk" name="product.rmk" rows="3" cols="50">${product.getRmk()}</textarea></td>
                    </tr>
                </table>
            </div>
            <!--------------- dscpt_set_div ---------------->

            <div class="right product_detail_info_div">
                <table class="dscpt_set_div_table_1">
                    <tr>
                        <th>상품 최소 수량</th>
                        <th>상품 중량</th>
                        <th>상품 규격</th>
                    </tr>
                    <tr class="dscpt_set_tr_1">
                        <td><input type="number" id="min_qty" name="product.min_qty" value="${product.getMin_qty()}" min="1" value="1" ><p>개</p></td>
                        <td><input type="number" id="weight" name="product.weight" value="${product.getWeight()}"><p>g</p></td>
                        <td><input type="text" id="stnd" name="product.stnd" value="${product.getStnd()}" placeholder="가로*세로*너비" ></td>
                    </tr>
                </table>
                <table class="dscpt_set_div_table_2">
                    <tr>
                        <th class="dscpt_header">조리법</th>
                        <td colspan="3" rows="3" cols="50" ><textarea id="recipe" name="product.recipe" rows="3" cols="50" >${product.getRecipe()}</textarea></td>
                    </tr>
                    <tr>
                        <th class="dscpt_header">활용법</th>
                        <td colspan="3" rows="3" cols="50" ><textarea id="mtd" name="product.mtd" rows="3" cols="50" >${product.getMtd()}</textarea></td>
                    </tr>
                    <tr class="dscpt_set_tr_2">
                        <th class="dscpt_header">소비기한</th>
                        <td><input type="text" id="distb_tlmt" name="product.distb_tlmt" value="${product.getDistb_tlmt()}" ></td>
                        <th class="dscpt_header">원산지</th>
                        <td><input type="text" id="orplc" name="product.orplc" value="${product.getOrplc()}" ></td>
                    </tr>
                    <tr class="dscpt_set_tr_3">
                        <th class="dscpt_header">유효 시작일</th>
                        <td><input type="date" id="vld_start_dt" name="product.vld_start_dt" value="${product.getVld_start_dt()}" ></td>
                        <th class="dscpt_header">유효 종료일</th>
                        <td><input type="date" id="vld_end_dt" name="product.vld_end_dt" value="${product.getVld_end_dt()}" ></td>
                    </tr>
                </table>
            </div>
            <!--------------- product_detail_info_div ---------------->

            <div class="right img_upload_div">
                <input type='file'  name='uploadFile' multiple>
                <button type="button"  id="uploadBtn">Upload</button>
            </div>
            <!--------------- img_upload_div ---------------->

            <div class="right img_set_div">
                <ul class="upload_img_thumnail img_ul_1">
                    <li>대표</li><br>
                    <li><img src='/resource/img/attach.png'></li>
                </ul>
                <ul class="upload_img_thumnail img_ul_2">
                    <li>메인</li><br>
                    <li><img src='/resource/img/attach.png'></li>
                    <li><img src='/resource/img/attach.png'></li>
                    <li><img src='/resource/img/attach.png'></li>
                    <li><img src='/resource/img/attach.png'></li>
                </ul>
                <ul class="upload_img_thumnail img_ul_3">
                    <li>상세</li><br>
                    <li><img src='/resource/img/attach.png'></li>
                </ul>
            </div>
            <!--------------- img_set_div ---------------->

            <div class="right button_set_div">
                <button type="submit"  class="prod_btn" id="regi_btn">상품 등록</button>
                <button type="button"  class="prod_btn" id="modi_btn">상품 수정</button>
                <button type="button"  class="prod_btn" id="modi_post_btn">상품 수정</button>
                <button type="button"  class="prod_btn" id="del_btn">상품 삭제</button>
                <button type="button"  class="prod_btn" id="list_btn">상품 목록</button>
                <button type="button"  class="prod_btn" id="prev_btn">미리보기</button>
            </div>
            <!--------------- button_set_div ---------------->
        </div>
    </div>
</form>

<script>
    window.onload = function() {
        /* 공급가, 소비자가, 할인코드에 이벤트 등록하기 */
        document.getElementById('sp_prc').addEventListener('input', calculate);
        document.getElementById('cnsmr_prc').addEventListener('input', calculate);
        document.getElementById('dc_cd').addEventListener('change', calculate);
    };
</script>
<!-- JavaScript 코드 -->
<!-- 공급가, 소비자가, 할인코드에 이벤트 등록하기 -->
<!---- 보관상태 직접입력 선택시에만 텍스트칸 활성화----->
<script src="/javascript/product_regist_mode_write.js"></script>


<script src="/javascript/product_regist_make_option.js"></script>




<script src="/javascript/product_regist_mode_read.js"></script>
<script src="/javascript/product_regist_mode_modify.js"></script>

</body>
</html>


<%--<script>--%>
<%--    window.onload = function() {--%>
<%--        /*페이지 로드 시 */--%>

<%--    }--%>

<%--</script>--%>