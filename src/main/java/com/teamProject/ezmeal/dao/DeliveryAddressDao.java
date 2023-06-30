package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.DeliveryAddressDto;

import java.util.List;

public interface DeliveryAddressDao {
    DeliveryAddressDto defaultAddress(int mbrId) throws Exception;

    List<DeliveryAddressDto> editAddress(int mbrId) throws Exception;
}
