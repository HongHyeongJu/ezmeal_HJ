package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.PointTransactionCodeDto;
import com.teamProject.ezmeal.domain.PointTransactionHistoryDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PointTransactionCodeDao {
    private final SqlSession session;
    private static final String namespace = "pointTransactionCode.";

    public PointTransactionCodeDto selectUsePointInfo(String pntTrjsCd){
        return session.selectOne(namespace + "selectUsePoint", pntTrjsCd);
    }
}
