package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.ProductInventoryDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductInventoryService {
    private final ProductInventoryDao productInventoryDao;

}
