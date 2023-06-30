package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.ProductDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDao {
    @Autowired
    private SqlSession session;
    private static String namespace = "tb_product.";

    /* 상품코드로 상품 1개 찾기 */
    public ProductDto selectProdCd(String prod_cd) throws SQLException {
        return session.selectOne(namespace+"select_prod_cd", prod_cd);
    }

    /* 정확한 상품이름으로 상품 찾기 -> 일단 List. 왜냐면 정말 만약으로 동일한 이름의 상품 있을 수 있음..... */
//    public List selectName(String prod_name) throws SQLException {
//        return session.selectList(namespace + "select_name", prod_name);
//    }
    /*이거 굳이 필요 없어보인다....*/


    /* 분류코드로 상품 리스트 받기 */
    public List<ProductDto> selectCateCd(String like_cate_cd) throws SQLException {
        return session.selectList(namespace + "select_cate_cd_List", like_cate_cd);
    }

    /* 분류코드로 상품 리스트 받기 (가격낮은 순 정렬) */
    public List<ProductDto> selectCateCdCheap(String like_cate_cd) throws SQLException {
        return session.selectList(namespace + "select_cate_cd_List_Cheap", like_cate_cd);
    }


    /* 상품이름으로 검색하기 */
    public List<ProductDto> selectName(String like_name) throws SQLException {
        return session.selectList(namespace + "search_name", like_name);
    }


    /* 1개의 상품코드에 대한 리뷰 숫자 가져오기 */
    public Integer countProductReview(String prod_cd) throws SQLException {
        return session.selectOne(namespace+"review_count",prod_cd);
    }

    /* 1개의 상품코드에 대한 별점 평균 받기 */
    public Double avgProductReviewStar(String prod_cd) throws SQLException {
        return session.selectOne(namespace+"review_star_avg",prod_cd);
    }



//    public List<ProductDto> orderBySalePrc() throws SQLException {
//        String tableName = "tb_product";
//        return session.selectList(namespace + "order_by_sale_prc_asc", tableName);
//
//    }

    /* 새 상품 추가하기 (서비스에서 상품 INSERT하고, 수행한 관리자의 아이디 UPDATE해주기)*/
//    public int insertProduct(ProductDto productDto) throws SQLException {
//
//        return session.insert(namespace + "insert_Product", map);
//    }

    /* 상품삭제(TDD용) */
//    public int deleteForTDD(String prodCd) throws SQLException {
//        return session.delete(namespace + "delete_for_TDD", prodCd);
//    }

    /* 상품정보 수정하기 */


    /* 상품 삭제하기 del_yn만 'y'로 */
//    public int deleteProduct(String prod_cd) throws SQLException {
//
//
//    }


    /* 카테고리별 개수 세기 */
    public Map<String, Integer> countCateCd() throws Exception {
        return session.selectMap(namespace+"count_cate_cd","cate_cd");
    }



    /* 상품 등록하기 */
    /* 상품 수정하기 */
    /* 유효일-종료일 차이가 n일 이하인 것 찾기 */
    /* 특정 할인 코드만 검색하기(특가 페이지) */
    /*  */
    /*  */
    /*  */
    /*  */


}