package com.teamProject.ezmeal.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderMasterDto {
    private Long ord_id;
    private Long mbr_id;
    private String stus;
    private String prod_smry;
    private Integer expct_pnt;
    private LocalDateTime in_dtm;
    private String in_id;
    private LocalDateTime up_dtm;
    private String up_id;

    // insert

    public OrderMasterDto(Long ord_id, Long mbr_id, String stus, Integer expct_pnt, String prod_smry) {
        this.ord_id = ord_id;
        this.mbr_id = mbr_id;
        this.stus = stus;
        this.expct_pnt = expct_pnt;
        this.prod_smry = prod_smry;
    }
}