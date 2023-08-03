package com.teamProject.ezmeal.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class ProductCategoryDto {
    private String cate_cd, name, detail, level, sort, use_yn, del_yn, rmk;
    private LocalDateTime in_dtm;
    private String in_id;
    private LocalDateTime up_dtm;
    private String up_id;
    /*---------------------------------------------------------------*/

    public ProductCategoryDto(String cate_cd, String name, String level, String use_yn, String del_yn, String rmk) {
        this.cate_cd = cate_cd;
        this.name = name;
        this.level = level;
        this.use_yn = use_yn;
        this.del_yn = del_yn;
        this.rmk = rmk;
    }

    /*---------------------------------------------------------------*/

    @Override
    public String toString() {
        return "ProductCategoryDto{" +
                "cate_cd='" + cate_cd + '\'' +
                ", name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", sort='" + sort + '\'' +
                ", use_yn='" + use_yn + '\'' +
                ", del_yn='" + del_yn + '\'' +
                ", rmk='" + rmk + '\'' +
                '}';
    }
    /*---------------------------------------------------------------*/

    public String getCate_cd() {
        return cate_cd;
    }

    public void setCate_cd(String cate_cd) {
        this.cate_cd = cate_cd;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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
    /*---------------------------------------------------------------*/

    /*---------------------------------------------------------------*/





}
