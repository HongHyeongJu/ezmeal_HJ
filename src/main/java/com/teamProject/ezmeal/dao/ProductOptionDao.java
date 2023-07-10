package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.ProductDto;
import com.teamProject.ezmeal.domain.ProductOptionDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class ProductOptionDao {

    @Autowired
    private SqlSession session;
    private static String namespace = "tb_product_option.";

    /*카테고리에서 옵션 모달창에서 선택할때 넘길 옵션 DTO*/
    /*한정된 내용만 담겨있음 옵션SEQ, 짧은 옵션명, 수량, sale_prc */
    public List<ProductOptionDto> selectOptionInProductCategory(String cate_cd) throws SQLException {
        return session.selectList(namespace+"select_option_in_category", cate_cd);
    }


    /*상품 상세 페이지에서 select-option 태그로 보여줄 DTO*/
    /*한정된 내용만 담겨있음
    옵션SEQ, 상품코드, 짧은 옵션명, 상품 풀 네임, 소비자가, 판매가, 타입, 수량 */
    public List<ProductOptionDto> selectOptionInProductDetail(Long prod_cd) throws SQLException {
        return session.selectList(namespace+"select_option_in_detail", prod_cd);
    }

    /*특정 상품코드에 대한 옵션상품 전체 받기 (낱개, 10세트, 20세트, 30세트)*/
    public List<ProductOptionDto> selectOptionProductsByProdCd(Long prod_cd) throws SQLException {
        return session.selectList(namespace+"select_option_product_by_prod_cd", prod_cd);
    }

//    /*특정 카테고리 상품에 대한 옵션상품 전체 받기 (낱개, 10세트, 20세트, 30세트)*/
//    public List<ProductOptionDto> selectOptionProductsByCateCd(String cate_cd) throws SQLException {
//        return session.selectList(namespace+"select_option_product_by_prod_cd", cate_cd);
//    }

    /*옵션SEQ로 하나 꺼내기 수정하기*/
    public ProductOptionDto selectOptionOne(Long opt_seq) throws SQLException {
        return session.selectOne(namespace+"select_option_product_for_mng", opt_seq);
    }

    /*옵션 수정하기*/
    public Integer optionUpdate(ProductOptionDto productOptionDto) throws SQLException {
        return session.update(namespace+"update_option", productOptionDto);
    }







}
