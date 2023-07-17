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

<form action="" method="POST">
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
                            <td>상품코드</td>
                            <td><input type="text" id="prod_cd" name="product.prod_cd" value="${product.getProd_cd()}" readonly></td>
                            <td>최초 등록자</td>
                            <td><input type="text" id="mng" name="product.mng" value="${product.getMng()}" readonly></td>
                            <td>최초 등록일시</td>
                            <td>
                                <input type="date" id="fst_reg_dt" name="product.fst_reg_dt" value="${product.getFst_reg_dt()}" readonly>
                                <input type="hidden" name="product.del_yn" value="${product.getDel_yn()}" >
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
                                <select id="prod_stus" name="product.prod_stus">
                                    <option value="1">판매중</option>
                                    <option value="2">품절임박</option>
                                    <option value="3">일시품절</option>
                                    <option value="4">품절</option>
                                    <option value="5">판매중지</option>
                                    <option value="6">입고예정</option>
                                </select>
                            </td>
                            <td><input type="radio" name="product.sale_yn" value="y" >판매</td>
                            <td><input type="radio" name="product.dp_yn" value="y" >진열</td>
                            <td><input type="radio" name="product.inv_yn" value="y" >재고관리</td>
                        </tr>
                        <tr>
                            <td ></td>
                            <td><input type="radio" name="product.sale_yn" value="n" >미판매</td>
                            <td><input type="radio" name="product.dp_yn" value="n" >미진열</td>
                            <td><input type="radio" name="product.inv_yn" value="n" >재고미관리</td>
                        </tr>
                    </table>
                </div>
                <!--------------- yn_columns_div ---------------->

                <div class="left product_names_div">
                    <label for="name" class="name_label"><strong>상품 거래처:</strong>
                    </label>
                    <select id="cust_cd" name="product.cust_cd">
                        <option value="CUST001">잇메이트</option>
                        <option value="CUST002">맛있닭</option>
                        <option value="CUST003">허닭</option>
                        <option value="CUST004">아임닭</option>
                        <option value="CUST005">러브잇</option>
                        <option value="CUST006">1am</option>
                        <option value="CUST007">위잇</option>
                    </select>
                    <label for="name" class="name_label2"><strong>상품 카테고리:</strong>
                    </label>
                    <select id="cate_cd" name="product.cate_cd">
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
                    <br>
                    <label for="name" class="name_label"><strong>판매용 상품명:</strong></label>
                    <input type="text" id="name" name="product.name"  value="${product.getName()}" maxlength="10">
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
                            <td><input type="number" id="cnsmr_prc" name="product.cnsmr_prc" value="${product.getCnsmr_prc()}" min="0" ><p>원</p></td>
                            <td>
                                <select id="dc_cd" name="product.dc_cd" >
                                    <option value="no_dc">할인없음</option>
                                    <option value="DC1000">1천원 할인</option>
                                    <option value="DC10pt">10% 할인</option>
                                    <option value="DC15pt">15% 할인</option>
                                    <option value="DC20pt">20% 할인</option>
                                    <option value="DC5pt">5% 할인</option>
                                    <option value="TEST_DC">50%할인</option>
                                </select>
                            </td>
                        </tr>
                        <tr class="dc_label">
                            <td><strong>판매가</strong></td>
                            <td><strong>마진율(자동)</strong></td>
                            <td><strong>할인율(자동)</strong></td>
                        </tr>
                        <tr class="dc_input">
                            <td><input type="number" id="sale_prc" name="product.sale_prc" value="${product.getSale_prc()}" min="0" disabled><p>원</p></td>
                            <td><input type="number" id="mgn_rate" name="product.mgn_rate" value="${product.getMgn_rate()}" disabled><p>%</p></td>
                            <td><input type="number" id="dc_per"  value="" ><p>%</p></td>
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
                    <table class="make_options_div_table">
                        <tr class="opt_title">
                            <th>옵션 이름</th>
                            <th>옵션 수량</th>
                            <th>소비자가</th>
                            <th>할인코드</th>
                            <th>판매가</th>
                            <th>할인율</th>
                        </tr>
                        <tr class="br_tr">
                            <td colspan="6">&nbsp;</td>
                        </tr>
                        <div class="make_option_trtd">
                            <!--- 옵션 A 시작 -->
                            <tr class="opt_tr">
                                <td><input type="text" id="opt_name" name="options[0].name" value="" ></td>
                                <td><input type="number" id="opt_qty" name="options[0].qty" value="" min="0" ></td>
                                <td><input type="number" id="opt_cnsmr_prc" name="options[0].cnsmr_prc" value="" min="0" ></td>
                                <td>
                                    <select id="opt_dc_cd" name="options[0].dc_cd" value="" >
                                        <option value="null">할인없음</option>
                                        <option value="DC1000">1천원 할인</option>
                                        <option value="DC10pt">10% 할인</option>
                                        <option value="DC15pt">15% 할인</option>
                                        <option value="DC20pt">20% 할인</option>
                                        <option value="DC5pt">5% 할인</option>
                                        <option value="TEST_DC">50%할인</option>
                                    </select>
                                </td>
                                <td><input type="number" id="opt_sale_prc" name="options[0].sale_prc" value="" min="0" ></td>
                                <td><input type="number" id="opt_dc_per" name="opt_dc_per" value="" min="0" ></td>
                            </tr >
                            <tr class="opt_tr_rmk">
                                <th >비고</th>
                                <td colspan="4">
                                    <input type="text" id="opt_rmk" name="options[0].rmk" value="옵션 관련 메모 작성하는 곳." >
                                </td>
                                <td class="del_opt_btn_td">
                                    <button class="del_opt_btn">X</button>
                                    <input type="hidden" name="options[0].opt_seq " value="n">
                                    <input type="hidden" name="options[0].prod_cd " value="n">
                                    <input type="hidden" name="options[0].typ" value="qty">
                                    <input type="hidden" name="options[0].use_yn " value="y">
                                    <input type="hidden" name="options[0].del_yn " value="n">
                                </td>
                            </tr>
                            <tr class="br_tr">
                                <td colspan="6">&nbsp;</td>
                            </tr>
                            <!--- 옵션 A 끝 -->
                        </div>
                    </table>
                </div>
                <!--------------- make_options_div ---------------->
        </div>

        <div class="right_bigdiv">
            <div class="right storage_div">
                <table class="storage_div_table">
                    <tr>
                        <th class="storage_header">보관상태</th>
                        <td><input type="radio" name="product.sfkp_stus" value="상온" >상온
                            <input type="radio" name="product.sfkp_stus" value="냉동" >냉동
                            <input type="radio" name="product.sfkp_stus" value="냉장">냉장</td>
                    </tr>
                    <tr>
                        <th class="storage_header">보관방법</th>
                        <td><input type="radio" name="product.sfkp_mtd" value="실온보관" >실온보관
                            <input type="radio" name="product.sfkp_mtd" value="냉장보관(0~10도)" >냉장보관(0~10도)
                            <input type="radio" name="product.sfkp_mtd" value="-18도 이하 냉동보관" >-18도 이하 냉동보관
                            <br>
                            <input type="radio" name="product.sfkp_mtd" value="직접입력" >직접입력
                            <input type="text" id="custom_sfkp_mtd" name="custom_sfkp_mtd" placeholder="직접 입력" disabled>
                        </td>
                    </tr>
                </table>
            </div>
            <!--------------- storage_div ---------------->

            <div class="right dscpt_set_div">
                <table class="storage_div_table">
                    <tr>
                        <th class="storage_header">상품 요약설명</th>
                        <td><textarea id="dscpt" name="product.dscpt" rows="3" cols="80">${product.getDscpt()}</textarea></td>
                    </tr>
                    <tr>
                        <th class="storage_header">상품 상세설명</th>
                        <td><textarea id="detail" name="product.detail" rows="3" cols="80">${product.getDetail()}</textarea></td>
                    </tr>
                    <tr>
                        <th class="storage_header">비고</th>
                        <td><textarea id="rmk" name="product.rmk" rows="3" cols="80">${product.getRmk()}</textarea></td>
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
                    <tr>
                        <td><input type="number" id="min_qty" name="product.min_qty" value="${product.getMin_qty()}" min="1" value="1" ></td>
                        <td><input type="number" id="weight" name="product.weight" value="${product.getWeight()}" ></td>
                        <td><input type="text" id="stnd" name="product.stnd" value="${product.getStnd()}" placeholder="가로*세로*너비" ></td>
                    </tr>
                </table>
                <table class="dscpt_set_div_table_2">
                    <tr>
                        <th class="dscpt_header">조리법</th>
                        <td colspan="3" rows="3" cols="60" ><textarea id="recipe" name="product.recipe" rows="3" cols="80" >${product.getRecipe()}</textarea></td>
                    </tr>
                    <tr>
                        <th class="dscpt_header">활용법</th>
                        <td colspan="3" rows="3" cols="60" ><textarea id="mtd" name="product.mtd" rows="3" cols="80" >${product.getMtd()}</textarea></td>
                    </tr>
                    <tr>
                        <th class="dscpt_header">소비기한</th>
                        <td><input type="text" id="distb_tlmt" name="product.distb_tlmt" value="${product.getDistb_tlmt()}" ></td>
                        <th class="dscpt_header">원산지</th>
                        <td><input type="text" id="orplc" name="product.orplc" value="${product.getOrplc()}" ></td>
                    </tr>
                    <tr>
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
                <button id="uploadBtn">Upload</button>
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
                <button class="prod_btn" id="regi_btn">상품 등록</button>
                <button class="prod_btn" id="modi_btn">상품 수정</button>
                <button class="prod_btn" id="modi_post_btn">상품 수정</button>
                <button class="prod_btn" id="del_btn">상품 삭제</button>
                <button class="prod_btn" id="list_btn">상품 목록</button>
                <button class="prod_btn" id="prev_btn">미리보기</button>
            </div>
<!--------------- button_set_div ---------------->
    </div>
</div>
</form>


<!-- JavaScript 코드 -->
<script>
    <script src="/javascript/product_regist_make_option.js"></script>
</script>



</body>
</html>
