package com.teamProject.ezmeal.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Objects;

public class ProductDto {
    private Long prod_cd;
    private String cate_cd, prod_stus, cust_cd, dc_cd, name, mng_prod_nm, sfkp_stus, sfkp_mtd;
    private Integer sp_prc, cnsmr_prc, sale_prc, mgn_rate;
    private String dscpt;

    private String detail;
    private Integer min_qty, weight;
    private String stnd, orplc, recipe, mtd, distb_tlmt, vld_start_dt, vld_end_dt, mng, fst_reg_dt;
    private String sale_yn, dp_yn, del_yn, inv_yn, opt_yn, rmk;

    private LocalDateTime in_dtm;

    private String in_id;

    private LocalDateTime up_dtm;

    private String up_id;

    /*-------------------------------------------------------------------   [생성자 시작]   -------------------*/


    public ProductDto(String cate_cd,String prod_stus,String cust_cd,String dc_cd,String name,String mng_prod_nm,
               String sfkp_stus,String sfkp_mtd,Integer sp_prc,Integer cnsmr_prc,Integer sale_prc,
               Integer mgn_rate, String dscpt,String detail,Integer min_qty,Integer weight,
               String stnd,String orplc,String recipe,String mtd,String distb_tlmt,
               String vld_start_dt,String vld_end_dt,String mng,String fst_reg_dt,
               String sale_yn,String dp_yn,String del_yn,String inv_yn,String opt_yn,String rmk,
               LocalDateTime in_dtm,String in_id, LocalDateTime up_dtm, String up_id){
        this.cate_cd=cate_cd;
        this.prod_stus=prod_stus;
        this.cust_cd=cust_cd;
        this.dc_cd=dc_cd;
        this.name=name;
        this.mng_prod_nm=mng_prod_nm;
        this.sfkp_stus=sfkp_stus;
        this.sfkp_mtd=sfkp_mtd;
        this.sp_prc=sp_prc;
        this.cnsmr_prc=cnsmr_prc;
        this.sale_prc=sale_prc;
        this.mgn_rate=mgn_rate; //마진율 = ((판매가 - 원가) / 판매가) * 100
        this.dscpt=dscpt;
        this.detail=detail;
        this.min_qty=min_qty;
        this.weight=weight;
        this.stnd=stnd;
        this.orplc=orplc;
        this.recipe=recipe;
        this.mtd=mtd;
        this.distb_tlmt=distb_tlmt;
        this.vld_start_dt=vld_start_dt;
        this.vld_end_dt=vld_end_dt;
        this.mng=mng;
        this.fst_reg_dt=fst_reg_dt;
        this.sale_yn=sale_yn;
        this.dp_yn=dp_yn;
        this.del_yn=del_yn;
        this.inv_yn=inv_yn;
        this.opt_yn=opt_yn;
        this.rmk=rmk;
        this.in_dtm=in_dtm;
        this.in_id=in_id;
        this.up_dtm=up_dtm;
        this.up_id=up_id;
    }

    ProductDto(){}
    /*-------------------------------------------------------------------   [생성자 끝]   -------------------*/

    /*-----------------------------------------------------------------   [toString & equals 시작]   -------*/
    @Override
    public String toString() {
        return "ProductDto{" +
                "prod_cd='" + prod_cd + '\'' +
                ", cate_cd='" + cate_cd + '\'' +
                ", prod_stus='" + prod_stus + '\'' +
                ", cust_cd='" + cust_cd + '\'' +
                ", dc_cd='" + dc_cd + '\'' +
                ", name='" + name + '\'' +
                ", sale_prc=" + sale_prc +
                ", sale_yn='" + sale_yn + '\'' +
                ", del_yn='" + del_yn + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return Objects.equals(prod_cd, that.prod_cd) && Objects.equals(cate_cd, that.cate_cd) && Objects.equals(in_dtm, that.in_dtm) && Objects.equals(in_id, that.in_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prod_cd, cate_cd, in_dtm, in_id);
    }


    /*-------------------------------------------------------------------   [getter & setter 시작]   ---------*/
    public Long getProd_cd() {
        return prod_cd;
    }

    public void setProd_cd(Long prod_cd) {
        this.prod_cd = prod_cd;
    }

    public String getCate_cd() {
        return cate_cd;
    }

    public void setCate_cd(String cate_cd) {
        this.cate_cd = cate_cd;
    }

    public String getProd_stus() {
        return prod_stus;
    }

    public void setProd_stus(String prod_stus) {
        this.prod_stus = prod_stus;
    }

    public String getCust_cd() {
        return cust_cd;
    }

    public void setCust_cd(String cust_cd) {
        this.cust_cd = cust_cd;
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

    public String getMng_prod_nm() {
        return mng_prod_nm;
    }

    public void setMng_prod_nm(String mng_prod_nm) {
        this.mng_prod_nm = mng_prod_nm;
    }

    public String getSfkp_stus() {
        return sfkp_stus;
    }

    public void setSfkp_stus(String sfkp_stus) {
        this.sfkp_stus = sfkp_stus;
    }

    public String getSfkp_mtd() {
        return sfkp_mtd;
    }

    public void setSfkp_mtd(String sfkp_mtd) {
        this.sfkp_mtd = sfkp_mtd;
    }

    public Integer getSp_prc() {
        return sp_prc;
    }

    public void setSp_prc(int sp_prc) {
        this.sp_prc = sp_prc;
    }

    public Integer getCnsmr_prc() {
        return cnsmr_prc;
    }

    public void setCnsmr_prc(int cnsmr_prc) {
        this.cnsmr_prc = cnsmr_prc;
    }

    public Integer getSale_prc() {
        return sale_prc;
    }

    public void setSale_prc(int sale_prc) {
        this.sale_prc = sale_prc;
    }

    public Integer getMgn_rate() {
        return mgn_rate;
    }

    public void setMgn_rate(int mgn_rate) {
        this.mgn_rate = mgn_rate;
    }

    public String getDscpt() {
        return dscpt;
    }

    public void setDscpt(String dscpt) {
        this.dscpt = dscpt;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getMin_qty() {
        return min_qty;
    }

    public void setMin_qty(int min_qty) {
        this.min_qty = min_qty;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getStnd() {
        return stnd;
    }

    public void setStnd(String stnd) {
        this.stnd = stnd;
    }

    public String getOrplc() {
        return orplc;
    }

    public void setOrplc(String orplc) {
        this.orplc = orplc;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getMtd() {
        return mtd;
    }

    public void setMtd(String mtd) {
        this.mtd = mtd;
    }

    public String getDistb_tlmt() {
        return distb_tlmt;
    }

    public void setDistb_tlmt(String distb_tlmt) {
        this.distb_tlmt = distb_tlmt;
    }

    public String getVld_start_dt() {
        return vld_start_dt;
    }

    public void setVld_start_dt(String vld_start_dt) {
        this.vld_start_dt = vld_start_dt;
    }

    public String getVld_end_dt() {
        return vld_end_dt;
    }

    public void setVld_end_dt(String vld_end_dt) {
        this.vld_end_dt = vld_end_dt;
    }

    public String getMng() {
        return mng;
    }

    public void setMng(String mng) {
        this.mng = mng;
    }

    public String getFst_reg_dt() {
        return fst_reg_dt;
    }

    public void setFst_reg_dt(String fst_reg_dt) {
        this.fst_reg_dt = fst_reg_dt;
    }

    public String getSale_yn() {
        return sale_yn;
    }

    public void setSale_yn(String sale_yn) {
        this.sale_yn = sale_yn;
    }

    public String getDp_yn() {
        return dp_yn;
    }

    public void setDp_yn(String dp_yn) {
        this.dp_yn = dp_yn;
    }

    public String getDel_yn() {
        return del_yn;
    }

    public void setDel_yn(String del_yn) {
        this.del_yn = del_yn;
    }

    public String getInv_yn() {
        return inv_yn;
    }

    public void setInv_yn(String inv_yn) {
        this.inv_yn = inv_yn;
    }

    public String getOpt_yn() {
        return opt_yn;
    }

    public void setOpt_yn(String opt_yn) {
        this.opt_yn = opt_yn;
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

    public LocalDateTime getUp_dtm() {
        return up_dtm;
    }

    public void setUp_dtm(LocalDateTime up_dtm) {
        this.up_dtm = up_dtm;
    }

    public String getIn_id() {
        return in_id;
    }

    public void setIn_id(String in_id) {
        this.in_id = in_id;
    }

    public String getUp_id() {
        return up_id;
    }

    public void setUp_id(String up_id) {
        this.up_id = up_id;
    }



}