package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.OrderPaymentDao;
import com.teamProject.ezmeal.domain.joinDomain.OrderPaymentJoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderPaymentService {
    private final OrderPaymentDao orderPaymentDao;

    public List<OrderPaymentJoinDto> getAllOrderPaymentList(Long memberId) {
        return orderPaymentDao.selectOrderPaymentList(memberId);
    }
}
