package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.OrderPaymentDao;
import com.teamProject.ezmeal.domain.joinDomain.OrderPaymentJoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderPaymentService {
    private final OrderPaymentDao orderPaymentDao;

    // 처음 주문 내역에 들어갈 경우, 모든 주문 내역을 보여준다.
    public List<OrderPaymentJoinDto> getAllOrderPaymentList(Long memberId) {
        return orderPaymentDao.selectOrderPaymentList(memberId);
    }

    // 조회 시작, 조회 마감일 나타내기
    public Map<String, String> getStartEndDate(Long memberId) {
        return orderPaymentDao.selectStartEndDate(memberId);
    }

    // 조회 기간에 따른 주문 내역 가지고 오기
    public List<OrderPaymentJoinDto> getPeriodOrderPaymentList(Map<String, Object> periodData) {
        return orderPaymentDao.selectPeriodOrderPaymentList(periodData);
    }

    // ezmeal myPage header에 넣을 주문 배송 개수
    public  Map<String, Integer> countOrderDeliveryNum(Long memberId){
        return orderPaymentDao.selectOrderDeliveryNum(memberId);
    }
}
