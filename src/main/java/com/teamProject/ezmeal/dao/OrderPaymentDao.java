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

    // 조회 시작, 조회 마감일 나타내기
    public Map<String , String> selectStartEndDate(Long mbrId) {
        return session.selectOne(namespace + "select_order_payment_start_end_date", mbrId);
    }

    // 조회 기간에 따른 주문 내역 가지고 오기
    public List<OrderPaymentJoinDto> selectPeriodOrderPaymentList(Map<String, Object> periodData) {
        return session.selectList(namespace + "select_order_payment_period", periodData);
    }

    // ezmeal myPage header에 넣을 주문 배송 개수
    public  Map<String, Integer> selectOrderDeliveryNum(Long mbrId){
        return session.selectOne(namespace + "count_order_delivery", mbrId);
    }
}
