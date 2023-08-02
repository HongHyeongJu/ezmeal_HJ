package com.teamProject.ezmeal.domain.joinDomain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AdminOrderDeliveryDto {
    private String status;
    private String id;
    private List<Long> orderProdDetailId;
    private String changeReason; // order status history에 존재

    public AdminOrderDeliveryDto(String status, String id, List<Long> orderProdDetailId) {
        this.status = status;
        this.id = id;
        this.orderProdDetailId = orderProdDetailId;
    }
}
