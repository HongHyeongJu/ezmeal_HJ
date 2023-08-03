package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.ProductCategoryDto;
import com.teamProject.ezmeal.domain.WishListDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class ProductCategoryDao {

    @Autowired
    private SqlSession session;
    private static String namespace = "tb_product_category.";

    /*상품 카테고리 level2 중분류만 가져오기*/
    public List<ProductCategoryDto> selectCategoryList() throws SQLException {
        return session.selectList(namespace+"select_all_category");
    }

    /*(카테고리코드, 카테고리이름)의 Map*/
    public Map<String,String> selectCateCdAndNameList() throws SQLException {
        return session.selectMap(namespace+"select_all_catecd_and_name","cate_cd");
    }

}







