package com.teamProject.ezmeal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductDto {
    private Long cart_prod_seq;
    private Long cart_seq;
    private Long mbr_id;
    private Long prod_cd;
    private String typ;
    private String sel_prod;
    private Integer seq;
    private Long opt_seq;
    private String soldout_yn;
    private Integer qty;
    private String add_dt;
    private String del_yn;
    private LocalDateTime in_dtm;
    private String in_id;
    private LocalDateTime up_dtm;
    private String up_id;

    // tb_product data
    private String name;
    private Integer cnsmr_prc;
    private Integer sale_prc;

    // tb_product_option data

    private String opt_name;
    private Integer opt_qty;
    private Integer opt_sale_prc;
    private Integer opt_cnsmr_prc;


    // 장바구니에 뿌려줄 data
    // TODO  option 확실해지면 다시 작성 필요 - option_cd 존재시, option 값(opt_val)을 상품 명 옆에 두고 | 가격은 옵션 가격으로 지정
    public CartProductDto(Long cart_prod_seq, Long prod_cd, String typ, Long opt_seq, String sel_prod, String soldout_yn, Integer qty, String name, Integer cnsmr_prc, Integer sale_prc,
                          String opt_name, Integer opt_qty, Integer opt_sale_prc, Integer opt_cnsmr_prc
    ) {
        this.cart_prod_seq = cart_prod_seq;
        this.prod_cd = prod_cd;
        this.typ = typ;
        this.opt_seq = opt_seq;
        this.sel_prod = sel_prod;
        this.soldout_yn = soldout_yn;
        this.qty = qty;
        this.name = name;
        this.cnsmr_prc = cnsmr_prc;
        this.sale_prc = sale_prc;
        this.opt_name = opt_name;
        this.opt_qty = opt_qty;
        this.opt_sale_prc = opt_sale_prc;
        this.opt_cnsmr_prc = opt_cnsmr_prc;
    }

    /*------------- getter & setter -----------------*/

    public Long getCart_prod_seq() {
        return cart_prod_seq;
    }

    public void setCart_prod_seq(Long cart_prod_seq) {
        this.cart_prod_seq = cart_prod_seq;
    }

    public Long getCart_seq() {
        return cart_seq;
    }

    public void setCart_seq(Long cart_seq) {
        this.cart_seq = cart_seq;
    }

    public Long getMbr_id() {
        return mbr_id;
    }

    public void setMbr_id(Long mbr_id) {
        this.mbr_id = mbr_id;
    }

    public Long getProd_cd() {
        return prod_cd;
    }

    public void setProd_cd(Long prod_cd) {
        this.prod_cd = prod_cd;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getSel_prod() {
        return sel_prod;
    }

    public void setSel_prod(String sel_prod) {
        this.sel_prod = sel_prod;
    }

    public Long getOpt_seq() {
        return opt_seq;
    }

    public void setOpt_seq(Long opt_seq) {
        this.opt_seq = opt_seq;
    }

    public String getSoldout_yn() {
        return soldout_yn;
    }

    public void setSoldout_yn(String soldout_yn) {
        this.soldout_yn = soldout_yn;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getAdd_dt() {
        return add_dt;
    }

    public void setAdd_dt(String add_dt) {
        this.add_dt = add_dt;
    }

    public String getDel_yn() {
        return del_yn;
    }

    public void setDel_yn(String del_yn) {
        this.del_yn = del_yn;
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
