package com.teamProject.ezmeal.domain.joinDomain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class AdminOrderOrderDto {
    private String status;
    private String id;
    private List<Long> pkIdList;
    private String changeReason; // order status history에 존재

    public AdminOrderOrderDto(List<Long> pkIdList, String changeReason) {
        this.pkIdList = pkIdList;
        this.changeReason = changeReason;
    }

    public AdminOrderOrderDto(String status, String id, List<Long> pkIdList, String changeReason) {
        this.status = status;
        this.id = id;
        this.pkIdList = pkIdList;
        this.changeReason = changeReason;
    }
}
