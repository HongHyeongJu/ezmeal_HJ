package com.teamProject.ezmeal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddressDto {

    private Long addr_id;
    private Long mbr_id;
    private String basic_yn;
    private String select_yn;
    private String ncnm; // 별명
    private String rcpr; // 수령인 이름
    private String phone; // 수령인 연락처
    private String desti; // 수령인 기본주소
    private String desti_dtl; // 수령인 상세주소
    private LocalDateTime in_dtm;
    private String in_id;
    private LocalDateTime up_dtm;
    private String up_id;

    // insert constructor
    public DeliveryAddressDto(Long mbr_id, String basic_yn, String ncnm, String rcpr, String phone, String desti, String desti_dtl, String in_id, String up_id) {
        this.mbr_id = mbr_id;
        this.basic_yn = basic_yn;
        this.ncnm = ncnm;
        this.rcpr = rcpr;
        this.phone = phone;
        this.desti = desti;
        this.desti_dtl = desti_dtl;
        this.in_id = in_id;
        this.up_id = up_id;
    }

    // resetAllBasicYNtoN
    public DeliveryAddressDto(Long addr_id, Long mbr_id) {
        this.addr_id = addr_id;
        this.mbr_id = mbr_id;
    }
}
