package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.AdminOrderDao;
import com.teamProject.ezmeal.dao.AdminOrderStatusHistoryDao;
import com.teamProject.ezmeal.domain.joinDomain.AdminOrderOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminOrderService {
    private final AdminOrderDao adminOrderDao;
    private final AdminOrderStatusHistoryDao adminOrderStatusHistoryDao;

    // 동적으로 발송대기 결제 주문 data 받아오기
    public List<Map<String, Object>> getAdminBeforeManageInfo(Map<String, Object> periodData) {
        return adminOrderDao.selectAdminBeforeManageInfo(periodData);
    }

    // admin 날짜 중에서 결제 상태인 경우의 주문 건수의 시작과 끝 날짜
    public Map<String , String> getStartEndDate() {
        return adminOrderDao.selectStartEndDate();
    }

    // admin 발주 버튼 누른 것, update 하기 - tb_order_detail 같이 update 수행
    public int setOrderStatusAfterCheckAdminOrderBtn (AdminOrderOrderDto afterOrderCheckData) {
        return adminOrderDao.updateOrderMasterOrderDetailStatusAfterAdminOrderCheckBtn(afterOrderCheckData);
    }

    // 주문 발주 버튼 클릭시, 이력이 insert. 발주 확인 버튼은 모든 주문 상세를 통합하므로 주문상세에 관한 정보 고려할 필요 없다.
    public int insertOrderHistoryAfterCheckAdminOrder (AdminOrderOrderDto afterOrderCheckData) {
        return adminOrderStatusHistoryDao.insertOrderAfterCheckAdminOrder(afterOrderCheckData);
    }
}
