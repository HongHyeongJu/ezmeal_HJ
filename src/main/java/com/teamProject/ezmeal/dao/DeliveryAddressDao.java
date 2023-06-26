package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.DeliveryAddressDto;

import java.util.List;

public interface DeliveryAddressDao {
    DeliveryAddressDto defaultAddress(Long mbrId) throws Exception;

    List<DeliveryAddressDto> editAddress(Long mbrId) throws Exception;
}
