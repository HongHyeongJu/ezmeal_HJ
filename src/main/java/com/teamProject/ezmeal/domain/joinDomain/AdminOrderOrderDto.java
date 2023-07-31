package com.teamProject.ezmeal.domain.joinDomain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AdminOrderOrderDto {
    private String status;
    private String id;
    private List<Long> orderIdList;
    private String changeReason; // order status history에 존재

    public AdminOrderOrderDto(String status, String id, List<Long> orderIdList) {
        this.status = status;
        this.id = id;
        this.orderIdList = orderIdList;
    }
}
