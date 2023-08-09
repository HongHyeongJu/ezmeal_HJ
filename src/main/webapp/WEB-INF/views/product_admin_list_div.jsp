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

    <link rel="stylesheet" href="/css/screens/admin_prod_list.css">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
<div class="product_admin_contain_div">
<!--상품 검색 위한 조건 테이브-->
    <div class="search_table_div_top">
        <div class="admin_prod_search_condition_div">
            <table class="admin_prod_search_condition_table">
                <tr>
                    <th class="table_title_1">상품정보</th>
                    <td class="table_title_2">
                        <div class="sc_title_contain_div">
                        <div class="sc_title_div">
                            <label>카테고리</label>
                            <select id="cate_cd" >
                                    <option value="0">전체</option>
                                <c:forEach items="${cateList}" var="cate">
                                    <option value="${cate.cate_cd}">${cate.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="sc_title_div">
                            <label>보관상태</label>
                            <select>
                                <option>전체</option>
                                <option>상온</option>
                                <option>냉장</option>
                                <option>냉동</option>
                            </select>
                        </div>
                        <div class="sc_title_div">
                            <label>판매상태</label>
                            <select id="prod_stus" >
                                <c:forEach items="${stusList}" var="stus">
                                    <option value="${stus.prod_stus_cd}">${stus.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="sc_title_div">
                            <label>재고상태</label>
                            <select id="inven_stus">
                                <option>전체</option>
                                <option>위험</option>
                                <option>주의</option>
                                <option>안전</option>
                            </select>
                        </div>
                        <div class="sc_title_div">
                            <label>거래처</label>
                            <select id="cust_cd">
                                <option value="0">전체</option>
                                <c:forEach items="${custList}" var="cust">
                                    <option value="${cust.cust_cd}">${cust.cust_nm}</option>
                                </c:forEach>
                            </select>
                        </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th class="table_title_1">상품등록일</th>
                    <td class="table_title_2">
                        <button class="fst_reg_dt_btn today">오늘</button>
                        <button class="fst_reg_dt_btn third-day">3일</button>
                        <button class="fst_reg_dt_btn one-week">7일</button>
                        <button class="fst_reg_dt_btn half-month">15일</button>
                        <button class="fst_reg_dt_btn one-month">30일</button>
                        <button class="fst_reg_dt_btn three-month">3개월</button>
                        <button class="fst_reg_dt_btn six-month">6개월</button>
                        <input class="date_input" type="date"/><span>~</span><input type="date"/>
                        <button class="fst_reg_dt_btn personal-day">기간설정 확인</button>
                    </td>
                </tr>
                <tr>
                    <th class="table_title_1">검색어</th>
                    <td class="table_title_search_td">
                        <span>상품명</span>
                        <input class="search_innut"  type="text"/>
                        <button class="search_innut_submit_btn">확인</button>
                    </td>
                </tr>
            </table>
        </div>
    </div>


<!-- 가운데 버튼 들어가는 div-->
    <div class="prod_list_table_btn_div">
        <table class="prod_list_table_btn_table">
            <tr>
                <th class="btn_th"><button>진열</button></th>
                <th class="btn_th"><button>상태변경</button></th>
                <th class="btn_th"><button>엑셀다운로드</button></th>
                <th class="btn_th"><button>인쇄</button></th>
                <th class="btn_th"><button>삭제</button></th>
            </tr>
        </table>
    </div>


<!--상품 리스트 표로 구성-->
    <div class="prod_list_table_div">
        <table class="prod_info_table">
            <tr  class="info_title_tr" data-cate_cd="" data-prod_cd="" data-name="" data-sale_prc="" data-sfkp_stus="" data-fst_reg_dt=""
                data-inven_stus="">
                <th class="table_title_1 check"><input type="checkbox"></th>
                <th class="table_title_1 no">No</th>
                <th class="table_title_1 cate_cd">카테고리</th>
                <th class="table_title_1 prod_cd">상품코드</th>
                <th class="table_title_1 name">상품명</th>
                <th class="table_title_1 sale_prc">판매가</th>
                <th class="table_title_1 sfkp_stus">보관방법</th>
                <th class="table_title_1 opt_yn">옵션상품</th>
                <th class="table_title_1 fst_reg_dt">등록일</th>
                <th class="table_title_1 dp_yn">진열상태</th>
                <th class="table_title_1 prod_stus">상품상태</th>
                <th class="table_title_1 inven_stus">재고상태</th>
            </tr>

            <!-- 각 상품 정보를 <tr>으로 감싸서 표 형태로 출력 -->
            <c:forEach var="prod" items="${allProdList}" varStatus="status">

                <c:choose>
                    <c:when test="${prod.opt_yn == 'Y' and not empty optList[prod.prod_cd]}">
                        <c:set var="optIndexZeroSalePrc" value="${optList[prod.prod_cd].get(0).sale_prc}"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="optIndexZeroSalePrc" value="${prod.sale_prc}"/>
                    </c:otherwise>
                </c:choose>



                <tr class="info_info_tr" id="${prod.prod_cd}"  data-cate_cd="${prod.cate_cd}" data-prod_cd="${prod.prod_cd}"
                    data-name="${prod.name}"
                    data-sale_prc="${optIndexZeroSalePrc}"
                    data-sfkp_stus="${prod.sfkp_stus}" data-fst_reg_dt="${prod.fst_reg_dt}"
                    data-inven_stus="">
                    <td class="table_title_3 check"><input type="checkbox"></td>
                    <td class="table_title_3 no">${status.index + 1}</td>
                    <td class="table_title_3 cate_cd">${prod.cate_cd}</td>
                    <td class="table_title_3 prod_cd">
                        <a class="product_link prod_cd" href="">P0034${prod.prod_cd}</a>
                    </td>
                    <td class="table_title_3 name">
                        <a class="product_link name" href="">${prod.name}</a>
                    </td>
                    <td class="table_title_3 sale_prc">${optIndexZeroSalePrc}</td>
                    <td class="table_title_3 sfkp_stus">${prod.sfkp_stus}</td>
                    <td class="table_title_3 opt_yn">${prod.opt_yn}</td>
                    <td class="table_title_3 fst_reg_dt">${prod.fst_reg_dt}</td>
                    <td class="table_title_3 dp_yn">${prod.dp_yn}</td>
                    <td class="table_title_3 prod_stus">
                        <c:choose>
                            <c:when test="${prod.prod_stus == 1}">판매중</c:when>
                            <c:when test="${prod.prod_stus == 2}">품절임박</c:when>
                            <c:when test="${prod.prod_stus == 3}">일시품절</c:when>
                            <c:when test="${prod.prod_stus == 4}">품절</c:when>
                            <c:when test="${prod.prod_stus == 5}">판매중지</c:when>
                            <c:when test="${prod.prod_stus == 6}">입고예정</c:when>
                            <c:otherwise>상태 정보 없음</c:otherwise>
                        </c:choose>
                    </td>
                    <td class="table_title_3 inven_stus">안전</td>
                </tr>

            </c:forEach>

            <!-- ... -->
        </table>
    </div>

</div>

</body>
</html>

