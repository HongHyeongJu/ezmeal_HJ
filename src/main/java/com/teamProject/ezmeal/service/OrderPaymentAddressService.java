package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.OrderMasterDao;
import com.teamProject.ezmeal.domain.OrderMasterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderPaymentAddressService {
    private final OrderMasterDao orderMasterDao;

    public int registerOrderMaster(OrderMasterDto orderMasterDto) {
        return orderMasterDao.insertOrderMaster(orderMasterDto);
    }
}
