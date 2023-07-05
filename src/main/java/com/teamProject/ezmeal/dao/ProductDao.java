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
    public ProductDto selectProductByProdCd(Long prod_cd) throws SQLException {
        return session.selectOne(namespace+"select_product_by_prod_cd", prod_cd);
    }

    /* 분류코드로 상품 리스트 받기 */
    public List<ProductDto> selectProductListByCateCd(String cate_cd) throws SQLException {
        return session.selectList(namespace + "select_product_list_by_cate_cd", cate_cd);
    }

    public List<ProductDto> selectProductListByCateCdMini(String cate_cd) throws SQLException {
        return session.selectList(namespace + "select_product_list_cate_cd_mini", cate_cd);
    }


//    /* 분류코드로 상품 리스트 받기 (가격낮은 순 정렬) */
//    public List<ProductDto> selectCateCdCheap(String cate_cd) throws SQLException {
//        return session.selectList(namespace + "select_cate_cd_List_Cheap", cate_cd);
//    }
//     /*개쩌는 쿼리문으로 주는 값에 따라서 동적으로 사용할 수 있게 DB단에서 찾아서 반환할꺼야*/

    /* 상품이름으로 검색하기 */
    public List<ProductDto> selectByName(String name) throws SQLException {
        return session.selectList(namespace + "search_by_name", name);
    }

    /* 새 상품 추가하기 (서비스에서 상품 INSERT하고, 수행한 관리자의 아이디 UPDATE해주기)*/
    public int insertProduct(ProductDto productDto) throws SQLException {
        return session.insert(namespace + "insert_product", productDto);
    }



    /*리뷰 DAO에서 처리할꺼야*/
//    /* 1개의 상품코드에 대한 리뷰 숫자 가져오기 */
//    public Integer getReviewCount(String prod_cd) throws SQLException {
//        return session.selectOne(namespace+"review_count",prod_cd);
//    }

//    /* 1개의 상품코드에 대한 별점 평균 받기 */
//    public Double reviewAverage(String prod_cd) throws SQLException {
//        return session.selectOne(namespace+"review_star_avg",prod_cd);
//    }


    /* 상품삭제(TDD용) */
    public int deleteForTDD(Long prod_Cd) throws SQLException {
        return session.delete(namespace + "delete_for_TDD", prod_Cd);
    }

    /* 상품 삭제하기 del_yn만 'y'로   데이터 적인 삭제*/
    public int deleteProduct(Long prod_cd) throws SQLException {
        return session.delete(namespace + "delete_Product_del_y", prod_cd);
    }

    /* 카테고리별 개수 세기 */
    public Map<String, Integer> countCateCd() throws Exception {
        return session.selectMap(namespace+"count_cate_cd","cate_cd");
    }

    /*상품 코드가 제일 큰 것 받기*/
    public Long selectMaxProdCd() throws SQLException {
        return session.selectOne(namespace + "select_max_prod_cd");
    }




    /* 상품정보 수정하기 */







    /* 상품 등록하기 */
    /* 상품 수정하기 */
    /* 유효일-종료일 차이가 n일 이하인 것 찾기 */
    /* 특정 할인 코드만 검색하기(특가 페이지) */
    /*  */
    /*  */
    /*  */
    /*  */
    /* 정확한 상품이름으로 상품 찾기 -> 일단 List. 왜냐면 정말 만약으로 동일한 이름의 상품 있을 수 있음..... */
//    public List selectName(String prod_name) throws SQLException {
//        return session.selectList(namespace + "select_name", prod_name);
//    }
    /*이거 굳이 필요 없어보인다....*/


}