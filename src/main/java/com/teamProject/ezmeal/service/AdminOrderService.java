package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.AdminOrderDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminOrderService {
    private final AdminOrderDao adminOrderDao;

    // 동적으로 발송대기 결제 주문 data 받아오기
    public List<Map<String, Object>> getAdminBeforeManageInfo(Map<String, Object> periodData) {
        return adminOrderDao.selectAdminBeforeManageInfo(periodData);
    }

    // admin 날짜 중에서 결제 상태인 경우의 주문 건수의 시작과 끝 날짜
    public Map<String , String> getStartEndDate() {
        return adminOrderDao.selectStartEndDate();
    }
}
