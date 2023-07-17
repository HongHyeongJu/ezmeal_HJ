package com.teamProject.ezmeal.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class ProductInventoryDto {

    private Long prod_cd;
    private Integer curr_inv, safe_inv;
    private String last_gr_dt;
    private Integer gr_dura_dt;
    private String use_yn, del_yn, rmk;
    private LocalDateTime in_dtm;
    private String in_id;
    private LocalDateTime up_dtm;
    private String up_id;
    /*----------------------------------------------------------*/

    public ProductInventoryDto(Long prod_cd, Integer curr_inv,
                               Integer safe_inv, String last_gr_dt,
                               Integer gr_dura_dt, String use_yn, String del_yn, String rmk,String in_id,  String up_id) {
        this.prod_cd = prod_cd;
        this.curr_inv = curr_inv;
        this.safe_inv = safe_inv;
        this.last_gr_dt = last_gr_dt;
        this.gr_dura_dt = gr_dura_dt;
        this.use_yn = use_yn;
        this.del_yn = del_yn;
        this.rmk = rmk;
        this.in_id = in_id;
        this.up_id = up_id;
    }
    public ProductInventoryDto(){}
    /*----------------------------------------------------------*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInventoryDto that = (ProductInventoryDto) o;
        return Objects.equals(prod_cd, that.prod_cd) && Objects.equals(curr_inv, that.curr_inv) && Objects.equals(safe_inv, that.safe_inv) && Objects.equals(last_gr_dt, that.last_gr_dt) && Objects.equals(gr_dura_dt, that.gr_dura_dt) && Objects.equals(use_yn, that.use_yn) && Objects.equals(del_yn, that.del_yn) && Objects.equals(rmk, that.rmk) && Objects.equals(in_dtm, that.in_dtm) && Objects.equals(in_id, that.in_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prod_cd, curr_inv, safe_inv, last_gr_dt, gr_dura_dt, use_yn, del_yn, rmk, in_dtm, in_id);
    }
    /*----------------------------------------------------------*/

    @Override
    public String toString() {
        return "ProductInventoryDto{" +
                "prod_cd=" + prod_cd +
                ", curr_inv=" + curr_inv +
                ", safe_inv=" + safe_inv +
                ", last_gr_dt='" + last_gr_dt + '\'' +
                ", gr_dura_dt='" + gr_dura_dt + '\'' +
                ", rmk='" + rmk + '\'' +
                '}';
    }
    /*----------------------------------------------------------*/

    public Long getProd_cd() {
        return prod_cd;
    }

    public void setProd_cd(Long prod_cd) {
        this.prod_cd = prod_cd;
    }

    public Integer getCurr_inv() {
        return curr_inv;
    }

    public void setCurr_inv(Integer curr_inv) {
        this.curr_inv = curr_inv;
    }

    public Integer getSafe_inv() {
        return safe_inv;
    }

    public void setSafe_inv(Integer safe_inv) {
        this.safe_inv = safe_inv;
    }

    public String getLast_gr_dt() {
        return last_gr_dt;
    }

    public void setLast_gr_dt(String last_gr_dt) {
        this.last_gr_dt = last_gr_dt;
    }

    public Integer getGr_dura_dt() {
        return gr_dura_dt;
    }

    public void setGr_dura_dt(Integer gr_dura_dt) {
        this.gr_dura_dt = gr_dura_dt;
    }
    public String getUse_yn() {
        return use_yn;
    }

    public void setUse_yn(String use_yn) {
        this.use_yn = use_yn;
    }

    public String getDel_yn() {
        return del_yn;
    }

    public void setDel_yn(String del_yn) {
        this.del_yn = del_yn;
    }
    public String getRmk() {
        return rmk != null ? rmk : "";
    }

    public void setRmk(String rmk) {
        this.rmk = rmk;
    }

    public LocalDateTime getIn_dtm() {
        return in_dtm;
    }

    public void setIn_dtm(LocalDateTime in_dtm) {
        this.in_dtm = in_dtm;
    }

    public String getIn_id() {
        return in_id;
    }

    public void setIn_id(String in_id) {
        this.in_id = in_id;
    }

    public LocalDateTime getUp_dtm() {
        return up_dtm;
    }

    public void setUp_dtm(LocalDateTime up_dtm) {
        this.up_dtm = up_dtm;
    }

    public String getUp_id() {
        return up_id;
    }

    public void setUp_id(String up_id) {
        this.up_id = up_id;
    }
    /*----------------------------------------------------------*/
    /*----------------------------------------------------------*/









}
