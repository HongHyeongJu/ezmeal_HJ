package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.PointTransactionHistoryDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PointTransactionHistoryDao {
    private final SqlSession session;
    private static final String namespace = "pointTransactionHistory.";

    public int pointCanUse(Long mbrId){
        return session.selectOne(namespace + "point_can_use", mbrId);
    } // 사용 가능한 point
    public int insertPointHistory(PointTransactionHistoryDto pointTransactionHistoryDto) {
        return session.insert(namespace + "update_point_history", pointTransactionHistoryDto); // 사용한 point 이력 남기기
    }


    // Baek
    // 현재 남아있는 포인트 조회
    public int selectPoint(Long memberId) {
        return session.selectOne(namespace + "selectPoint", memberId);
    }

    // 포인트 내역 조회
    public List<PointTransactionHistoryDto> selectPointList(Long memberId) {
        return session.selectList(namespace + "selectPointList", memberId);
    }

}
