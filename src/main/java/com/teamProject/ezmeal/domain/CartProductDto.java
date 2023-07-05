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
    private String prod_cd;
    private String typ;
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
    public CartProductDto(Long cart_prod_seq, String prod_cd, String typ, Long opt_seq, String soldout_yn, Integer qty, String name, Integer cnsmr_prc, Integer sale_prc,
                          String opt_name, Integer opt_qty, Integer opt_sale_prc, Integer opt_cnsmr_prc
    ) {
        this.cart_prod_seq = cart_prod_seq;
        this.prod_cd = prod_cd;
        this.typ = typ;
        this.opt_seq = opt_seq;
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
}
