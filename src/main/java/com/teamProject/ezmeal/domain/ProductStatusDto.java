package com.teamProject.ezmeal.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class ProductStatusDto {
    private String prod_stus_cd, name, detail, sort, use_yn, del_y, rmk;
    private LocalDateTime in_dtm;
    private String in_id;
    private LocalDateTime up_dtm;
    private String up_id;
    /*---------------------------------------------------------------*/

    public ProductStatusDto(String name, String detail, String sort, String use_yn, String del_y) {
        this.name = name;
        this.detail = detail;
        this.sort = sort;
        this.use_yn = use_yn;
        this.del_y = del_y;
    }
    public ProductStatusDto (){}
    /*---------------------------------------------------------------*/

    @Override
    public String toString() {
        return "ProductStatusDto{" +
                "prod_stus_cd='" + prod_stus_cd + '\'' +
                ", name='" + name + '\'' +
                ", detail='" + detail + '\'' +
                ", sort='" + sort + '\'' +
                ", use_yn='" + use_yn + '\'' +
                ", del_y='" + del_y + '\'' +
                ", rmk='" + rmk + '\'' +
                '}';
    }
    /*---------------------------------------------------------------*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductStatusDto that = (ProductStatusDto) o;
        return Objects.equals(prod_stus_cd, that.prod_stus_cd) && Objects.equals(name, that.name) && Objects.equals(detail, that.detail) && Objects.equals(sort, that.sort) && Objects.equals(use_yn, that.use_yn) && Objects.equals(del_y, that.del_y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prod_stus_cd, name, detail, sort, use_yn, del_y);
    }

    /*---------------------------------------------------------------*/

    public String getProd_stus_cd() {
        return prod_stus_cd;
    }

    public void setProd_stus_cd(String prod_stus_cd) {
        this.prod_stus_cd = prod_stus_cd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getUse_yn() {
        return use_yn;
    }

    public void setUse_yn(String use_yn) {
        this.use_yn = use_yn;
    }

    public String getDel_y() {
        return del_y;
    }

    public void setDel_y(String del_y) {
        this.del_y = del_y;
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
    /*---------------------------------------------------------------*/





}
