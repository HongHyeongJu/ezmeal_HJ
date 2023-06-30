package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.DeliveryAddressDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryAddressService {
    private final DeliveryAddressDao deliveryAddressDao;


}
