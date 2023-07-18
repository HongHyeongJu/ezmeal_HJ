package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.PointTransactionHistoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointTransactionHistoryService {

    @Autowired
    PointTransactionHistoryDao pointTransactionHistoryDao;

    // dao에서 포인트 조회 메서드 호출해서 사용할것, 회원번호로 조회, 포인트도 Long
    public int getUsablePoint(Long memberId) {
        return pointTransactionHistoryDao.selectPoint(memberId);
    }

}
