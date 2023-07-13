package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.ProductDiscountDto;
import com.teamProject.ezmeal.domain.ProductDto;
import com.teamProject.ezmeal.domain.ProductOptionDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
@Repository
public class ProductDiscountDao {

    @Autowired
    private SqlSession session;
    private static String namespace = "tb_product_discount.";


    /*카테고리코드에 따른 사용 가능한 할인코드 List*/
    public List<ProductDiscountDto> selectDiscountListByCateCd() throws SQLException {
        return session.selectList(namespace+"get_discount_by_cate_cd");
    }


    public Map<String, ProductDiscountDto> selectDiscountListByCateCdToMap() throws SQLException {
        return session.selectMap(namespace+"get_discount_by_cate_cd_to_map", "dc_cd");
    }


    /*할인코드 관리 페이지에서 보는 모든 할인코드 List*/
    public List<ProductDiscountDto> selectAllDiscountListForMng() throws SQLException {
        return session.selectList(namespace+"get_discount_by_cate_cd_for_mng");
    }

    /*할인 코드 수정하기*/
    public Integer updateDiscount(ProductDiscountDto productDiscountDto) throws SQLException {
        return session.update(namespace+"update_discount", productDiscountDto);
    }

    /*할인 코드 이름 List(중복코드 생성 방지를 위함)*/
    public List<String> dcCodeList() throws SQLException {
        return session.selectList(namespace+"dc_cd_list_by_discount");
    }

    /*할인 코드 수정 후 -> TB_상품 판매가 일괄 수정*/
    public Integer updateProductSalePrcDueToDiscountUpdate() throws SQLException {
        return session.update(namespace+"update_product_sale_price_due_to_discount_update");
    }

    /*할인 코드 수정 후 -> TB_옵션상품 판매가 일괄 수정*/
    public Integer updateOptionProductSalePrcDueToDiscountUpdate() throws SQLException {
        return session.update(namespace+"update_option_product_sale_price_due_to_discount_update");
    }

    /*할인코드 진짜삭제*/
    public Integer deleteDiscountForTdd(String dc_cd) throws SQLException {
        return session.delete(namespace+"delete_discount_for_tdd", dc_cd);
    }

    /*할인코드 삭제(del_yn update)*/
    public Integer deleteDiscount(String dc_cd) throws SQLException {
        return session.update(namespace+"delete_discount_del_y", dc_cd);
    }

    /*할인코드 사용여부만 변경(필요할까 모르겠다. 보통 할인코드 상세페이지 가지 않나ㅏ.. 하는데
    할인코드 List목록자체에서 수정버튼 누르면 readoly만 풀어줘도 되겠다.) 그래도 일단 만들어본다.*/
    public Integer updateUseYnDiscount(String dc_cd, String use_yn) throws SQLException {
        HashMap map = new HashMap<>();
        map.put("dc_cd", dc_cd);
        map.put("use_yn", use_yn);
        return session.update(namespace+"delete_discount_use_yn", map);
    }

    /*할인코드 생성하기*/
    public Integer insertDiscount(ProductDiscountDto productDiscountDto) throws SQLException {
        return session.insert(namespace+"insert_discount", productDiscountDto);
    }

    /*dc_cd로 할인코드 하나 꺼내기*/
    public ProductDiscountDto selectDiscount(String dc_cd) throws SQLException {
        return session.selectOne(namespace+"select_discount_by_dc_cd", dc_cd);
    }

    public Integer updateMngRateTest() throws  SQLException {
        return session.update(namespace+"update_product_sale_price_due_to_discount_update");
    }

}
