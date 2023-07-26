package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.*;
import com.teamProject.ezmeal.domain.DeliveryMasterDto;
import com.teamProject.ezmeal.domain.OrderDetailDto;
import com.teamProject.ezmeal.domain.OrderMasterDto;
import com.teamProject.ezmeal.domain.PaymentMasterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderPaymentAddressService {
    private final OrderMasterDao orderMasterDao; // 주문
    private final PaymentMasterDao paymentMasterDao; // 결제
    private final OrderPaymentDao orderPaymentDao; // 주문결제내역
    private final OrderDetailDao orderDetailDao; // 주문상세
    private final OrderStatusHistoryDao orderStatusHistoryDao; // 주문상태이력
    private final DeliveryMasterDao deliveryMasterDao; // 배송

    public int registerOrderMaster(OrderMasterDto orderMasterDto) {
        return orderMasterDao.insertOrderMaster(orderMasterDto);
    }

    public int registerPaymentMaster(PaymentMasterDto paymentMasterDto) {
        return paymentMasterDao.insertPaymentMaster(paymentMasterDto);
    }

    public int registerOrderPayment(Map<String,Long> orderPaymentMap){
        return orderPaymentDao.insertOrderPayment(orderPaymentMap);
    }

    public int registerOrderDetail(List<OrderDetailDto> orderDetailList){
        return orderDetailDao.insertOrderDetail(orderDetailList);
    }

    public int registerOrderStatusHistory(Long orderId){
        return orderStatusHistoryDao.insertOrderStatusHistory(orderId);
    }

    public int registerDeliveryMaster(List<DeliveryMasterDto> deliveryMasterDtoList) {
        return deliveryMasterDao.insertDeliveryMaster(deliveryMasterDtoList);
    }

}
