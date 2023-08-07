package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.OrderMasterDao;
import com.teamProject.ezmeal.domain.OrderMasterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderMasterService {
    private final OrderMasterDao orderMasterDao;

    public Long getOrderId(Long memberId){
        return orderMasterDao.selectOrderId(memberId);
    }

    // 주문 상세에 배송 상세 정보 보내줄지 말지 알리는 기준
    public int showDeliveryInfo(Long orderId) {
        return orderMasterDao.selectShowDeliveryInfo(orderId);
    }
}
