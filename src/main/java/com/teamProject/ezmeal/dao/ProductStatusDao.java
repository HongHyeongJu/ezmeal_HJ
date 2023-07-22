package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.ProductStatusDto;
import com.teamProject.ezmeal.domain.WishListDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;


@Repository
public class ProductStatusDao {

    @Autowired
    private SqlSession session;
    private static String namespace = "tb_product_status.";

    /* 상품 상태코드 리스트 받기 */
    public List<ProductStatusDto> selectAllProdStus() throws SQLException {
        return session.selectList(namespace+"select_all_prod_stus");
    }



}





