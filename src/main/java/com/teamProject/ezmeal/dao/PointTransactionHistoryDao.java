package com.teamProject.ezmeal.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PointTransactionHistoryDao {
    private final SqlSession session;
    private static final String namespace = "tb_pointTransactionHistory.";

    public int pointCanUse(Long mbrId){
        return session.selectOne(namespace + "point_can_use", mbrId);
    }

}
