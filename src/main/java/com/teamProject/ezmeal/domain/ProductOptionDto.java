package com.teamProject.ezmeal.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class ProductOptionDto {
    Long opt_seq;
    String prod_cd, dc_cd, name, typ;
    int qty, cnsmr_prc, sale_prc;
    String use_yn, del_yn, rmk;
    LocalDateTime in_dtm;
    String in_id;
    LocalDateTime up_dtm;
    String up_id;

    /*---------------------------------------------------------------*/
    public ProductOptionDto(String prod_cd, String dc_cd, String name, String typ,
                            int qty, int cnsmr_prc, int sale_prc,
                            String use_yn, String del_yn, String rmk,
                            LocalDateTime in_dtm, String in_id,
                            LocalDateTime up_dtm, String up_id) {
        this.prod_cd = prod_cd;
        this.dc_cd = dc_cd;
        this.name = name;
        this.typ = typ;
        this.qty = qty;
        this.cnsmr_prc = cnsmr_prc;
        this.sale_prc = sale_prc;
        this.use_yn = use_yn;
        this.del_yn = del_yn;
        this.rmk = rmk;
        this.in_dtm = in_dtm;
        this.in_id = in_id;
        this.up_dtm = up_dtm;
        this.up_id = up_id;
    }

    ProductOptionDto(){}

    /*---------------------------------------------------------------*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductOptionDto that = (ProductOptionDto) o;
        return qty == that.qty && Objects.equals(opt_seq, that.opt_seq) && Objects.equals(prod_cd, that.prod_cd) && Objects.equals(dc_cd, that.dc_cd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(opt_seq, prod_cd, dc_cd, qty);
    }
    /*---------------------------------------------------------------*/

    @Override
    public String toString() {
        return "ProductDiscoumtDto{" +
                "opt_seq=" + opt_seq +
                ", prod_cd='" + prod_cd + '\'' +
                ", dc_cd='" + dc_cd + '\'' +
                ", name='" + name + '\'' +
                ", typ='" + typ + '\'' +
                ", qty=" + qty +
                ", cnsmr_prc=" + cnsmr_prc +
                ", sale_prc=" + sale_prc +
                ", use_yn='" + use_yn + '\'' +
                ", del_yn='" + del_yn + '\'' +
                '}';
    }

    /*---------------------------------------------------------------*/

    public Long getOpt_seq() {
        return opt_seq;
    }

    public void setOpt_seq(Long opt_seq) {
        this.opt_seq = opt_seq;
    }

    public String getProd_cd() {
        return prod_cd;
    }

    public void setProd_cd(String prod_cd) {
        this.prod_cd = prod_cd;
    }

    public String getDc_cd() {
        return dc_cd;
    }

    public void setDc_cd(String dc_cd) {
        this.dc_cd = dc_cd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getCnsmr_prc() {
        return cnsmr_prc;
    }

    public void setCnsmr_prc(int cnsmr_prc) {
        this.cnsmr_prc = cnsmr_prc;
    }

    public int getSale_prc() {
        return sale_prc;
    }

    public void setSale_prc(int sale_prc) {
        this.sale_prc = sale_prc;
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
}
