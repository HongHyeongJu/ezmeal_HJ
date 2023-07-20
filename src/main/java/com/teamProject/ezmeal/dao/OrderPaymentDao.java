package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.joinDomain.OrderPaymentJoinDto;
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

    // 주문 완료시 insert
    public int insertOrderPayment(Map<String, Long> orderPaymentMap) {
        return session.insert(namespace + "insertOrderPayment", orderPaymentMap);
    }

    // 처음 주문 내역에 들어갈 경우, 모든 주문 내역을 보여준다.
    public List<OrderPaymentJoinDto> selectOrderPaymentList(Long mbrId) {
        return session.selectList(namespace + "select_all_order_payment_list", mbrId);
    }

}
