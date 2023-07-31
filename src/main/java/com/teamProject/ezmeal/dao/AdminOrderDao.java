package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.joinDomain.AdminOrderOrderDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AdminOrderDao {
    private final SqlSession session;
    private static final String namespace = "orderMaster.";

    // 동적으로 발송대기 결제 주문 data
    public List<Map<String, Object>> selectAdminBeforeManageInfo(Map<String, Object> periodData) {
        return session.selectList(namespace + "select_admin_before_manage_info", periodData);
    }

    // admin 날짜 중에서 결제 상태인 경우의 주문 건수의 시작과 끝 날짜
    public Map<String, String> selectStartEndDate() {
        return session.selectOne(namespace + "select_admin_before_manage_date");
    }

    // admin 발주 버튼 누른 것, update 하기 - tb_order_detail 같이 update 수행
    public int updateOrderMasterOrderDetailStatusAfterAdminOrderCheckBtn(AdminOrderOrderDto afterOrderCheckData) {
        return session.update(namespace + "update_order_master_order_detail_status_after_admin_order_check_btn", afterOrderCheckData);
    }
}
