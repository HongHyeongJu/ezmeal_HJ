package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.DeliveryAddressDao;
import com.teamProject.ezmeal.domain.DeliveryAddressDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryAddressService {
    private final DeliveryAddressDao deliveryAddressDao;

    // 기본 배송지
    public DeliveryAddressDto getDefaultAddress(Long mbrId) {
        return deliveryAddressDao.selectDefaultAddress(mbrId);
    }

}
