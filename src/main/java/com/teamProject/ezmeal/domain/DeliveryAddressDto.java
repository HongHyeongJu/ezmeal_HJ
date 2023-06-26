package com.teamProject.ezmeal.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeliveryAddressDto {

    private Long addr_id;
    private Long mbr_id;
    private String basic_yn;
    private String ncnm;
    private String rcpr;
    private String phone;
    private String desti;
    private String desti_dtl;
    private String zpcd;

    private LocalDateTime in_dtm;
    private String in_id;
    private LocalDateTime up_dtm;
    private String up_id;
}
