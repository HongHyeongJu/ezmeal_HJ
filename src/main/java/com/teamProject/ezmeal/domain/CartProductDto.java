package com.teamProject.ezmeal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductDto {
    private Long cart_seq;
    private Long mbr_id;
    private String prod_cd;
    private String typ;
    private Integer seq;
    private String opt_cd;
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
    private String opt_nm;

    // tb_product_discount
    private Integer rate;
    private Integer prc;

    public CartProductDto(String prod_cd, String typ, Integer qty, String name, Integer cnsmr_prc, Integer sale_prc) {
        this.prod_cd = prod_cd;
        this.typ = typ;
        this.qty = qty;
        this.name = name;
        this.cnsmr_prc = cnsmr_prc;
        this.sale_prc = sale_prc;
    }

    public CartProductDto(String prod_cd, String typ, Integer qty, String name, Integer cnsmr_prc, Integer sale_prc, Integer rate
            ,Integer prc) {
        this.prod_cd = prod_cd;
        this.typ = typ;
        this.qty = qty;
        this.name = name;
        this.cnsmr_prc = cnsmr_prc;
        this.sale_prc = sale_prc;
        this.rate = rate;
        this.prc = prc;
    }

    @Override
    public String toString() {
        return "CartProductDto{" +
                "prod_cd='" + prod_cd + '\'' +
                ", typ='" + typ + '\'' +
                ", qty=" + qty +
                ", name='" + name + '\'' +
                ", cnsmr_prc=" + cnsmr_prc +
                ", sale_prc=" + sale_prc +
                ", rate=" + rate +
                ", prc=" + prc +
                '}';
    }
}
