package com.teamProject.ezmeal.domain;

import com.teamProject.ezmeal.dao.ProductDiscountDao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class ProductDiscountDto {

    private String dc_cd, cate_cd, typ, name;
    private LocalDate start_dt, end_dt;
    private String target;
    private Integer rate, prc;
    private String use_yn, del_yn, rmk;
    private LocalDateTime in_dtm;
    private String in_id;
    private LocalDateTime up_dtm;
    private String up_id;

    /*-------------------------------------------------------------------*/

    @Override
    public String toString() {
        return "ProductDiscountDto{" +
                "dc_cd='" + dc_cd + '\'' +
                ", cate_cd='" + cate_cd + '\'' +
                ", typ='" + typ + '\'' +
                ", name='" + name + '\'' +
                ", start_dt=" + start_dt +
                ", end_dt=" + end_dt +
                ", rate=" + rate +
                ", prc=" + prc +
                ", use_yn='" + use_yn + '\'' +
                ", del_yn='" + del_yn + '\'' +
                ", rmk='" + rmk + '\'' +
                '}';
    }
    /*-------------------------------------------------------------------*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDiscountDto that = (ProductDiscountDto) o;
        return Objects.equals(dc_cd, that.dc_cd) && Objects.equals(cate_cd, that.cate_cd) && Objects.equals(typ, that.typ) && Objects.equals(name, that.name) && Objects.equals(start_dt, that.start_dt) && Objects.equals(end_dt, that.end_dt) && Objects.equals(target, that.target) && Objects.equals(rate, that.rate) && Objects.equals(prc, that.prc) && Objects.equals(use_yn, that.use_yn) && Objects.equals(del_yn, that.del_yn) && Objects.equals(rmk, that.rmk) && Objects.equals(in_dtm, that.in_dtm) && Objects.equals(in_id, that.in_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dc_cd, cate_cd, typ, name, start_dt, end_dt, target, rate, prc, use_yn, del_yn, rmk, in_dtm, in_id);
    }

    /*-------------------------------------------------------------------*/

    public ProductDiscountDto(String dc_cd, String cate_cd, String typ, String name,
                              LocalDate start_dt, LocalDate end_dt, String target, Integer rate, Integer prc,
                              String use_yn, String del_yn, String rmk,
                              LocalDateTime in_dtm, String in_id, LocalDateTime up_dtm, String up_id) {
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
        this.rmk = rmk;
        this.in_dtm = in_dtm;
        this.in_id = in_id;
        this.up_dtm = up_dtm;
        this.up_id = up_id;
    }
    public ProductDiscountDto(){}
    /*-------------------------------------------------------------------*/

    public String getDc_cd() {
        return dc_cd;
    }

    public void setDc_cd(String dc_cd) {
        this.dc_cd = dc_cd;
    }

    public String getCate_cd() {
        return cate_cd;
    }

    public void setCate_cd(String cate_cd) {
        this.cate_cd = cate_cd;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStart_dt() {
        return start_dt;
    }

    public void setStart_dt(LocalDate start_dt) {
        this.start_dt = start_dt;
    }

    public LocalDate getEnd_dt() {
        return end_dt;
    }

    public void setEnd_dt(LocalDate end_dt) {
        this.end_dt = end_dt;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getPrc() {
        return prc;
    }

    public void setPrc(Integer prc) {
        this.prc = prc;
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
        return rmk;
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
    /*-------------------------------------------------------------------*/
}
