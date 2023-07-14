package com.teamProject.ezmeal.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class OrderPaymentDao {
    private final SqlSession session;
    private static final String namespace = "orderPaymentDao.";

    public int insertOrderPayment(Map<String,Long> orderPaymentMap){
        return session.insert(namespace + "insertOrderPayment", orderPaymentMap);
    }


}
