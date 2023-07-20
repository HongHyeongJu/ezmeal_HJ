package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.OrderMasterDao;
import com.teamProject.ezmeal.domain.OrderMasterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderMasterService {
    private final OrderMasterDao orderMasterDao;
}
