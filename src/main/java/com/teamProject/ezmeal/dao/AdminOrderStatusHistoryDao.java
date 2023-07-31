package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.joinDomain.AdminOrderOrderDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AdminOrderStatusHistoryDao {
    private final SqlSession session;
    private static final String namespace = "orderStatusHistoryDao.";

    // 주문 발주 버튼 클릭시, 이력이 남음. 발주 확인 버튼은 모든 주문 상세를 통합하므로 주문상세에 관한 정보 고려할 필요 없다.
    public int insertOrderAfterCheckAdminOrder(AdminOrderOrderDto afterOrderCheckData) {
        return session.insert(namespace + "insert_order_after_check_admin_order", afterOrderCheckData);
    }
}
