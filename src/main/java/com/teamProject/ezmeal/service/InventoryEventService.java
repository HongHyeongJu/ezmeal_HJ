package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.ProductInventoryDao;
import com.teamProject.ezmeal.domain.restAPIDomain.InventoryData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryEventService {
    private final ProductInventoryDao productInventoryDao;
    public Integer decreaseInventoryAfterPayment(List<InventoryData> inventoryDataList){
        return productInventoryDao.updateInventoryAfterPayment(inventoryDataList); // 상품 재고 update
    }
}
