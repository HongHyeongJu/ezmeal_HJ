package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.ProductCategoryDto;
import com.teamProject.ezmeal.domain.WishListDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;


@Repository
public class ProductCategoryDao {

    @Autowired
    private SqlSession session;
    private static String namespace = "tb_product_category.";

    /**/
    public List<ProductCategoryDto> selectCategoryList() throws SQLException {
        return session.selectList(namespace+"select_all_category");
    }

}







