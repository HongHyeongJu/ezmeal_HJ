package com.teamProject.ezmeal.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderStatusHistoryDao {
    private final SqlSession session;
    private static final String namespace = "orderStatusHistoryDao.";

    public int insertOrderStatusHistory(Long ordId){
        return session.insert(namespace + "insertOrderStatusHistory", ordId);
    }
}
