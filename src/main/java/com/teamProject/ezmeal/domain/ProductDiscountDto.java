package com.teamProject.ezmeal.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProductDiscountDto {

    String dc_cd, cate_cd, typ, name;
    LocalDate start_dt, end_dt;
    String target;
    int rate, prc;
    String use_yn, del_yn;
    LocalDateTime in_dtm;
    String in_id;
    LocalDateTime up_dtm;
    String up_id;
    /*-------------------------------------------------------*/

    public ProductDiscountDto(String dc_cd, String cate_cd, String typ, String name, LocalDate start_dt, LocalDate end_dt, String target, int rate, int prc, String use_yn, String del_yn, LocalDateTime in_dtm, String in_id, LocalDateTime up_dtm, String up_id) {
        this.dc_cd = dc_cd;
        this.cate_cd = cate_cd;
        this.typ = typ;
        this.name = name;
        this.start_dt = start_dt;
        this.end_dt = end_dt;
        this.target = target;
        this.rate = rate;
        this.prc = prc;
        this.use_yn = use_yn;
        this.del_yn = del_yn;
        this.in_dtm = in_dtm;
        this.in_id = in_id;
        this.up_dtm = up_dtm;
        this.up_id = up_id;
    }
}
