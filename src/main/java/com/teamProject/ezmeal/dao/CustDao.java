package com.teamProject.ezmeal.dao;


import com.teamProject.ezmeal.domain.CustDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;


@Repository
public class CustDao {

    @Autowired
    private SqlSession session;
    private static String namespace = "tb_cust.";

    /* 거래처 List 불러오기 */
    public List<CustDto> selectCustList() throws SQLException {
        return session.selectList(namespace+"select_all_cust");
    }


}





