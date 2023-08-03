package com.teamProject.ezmeal.domain.joinDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartJoinProductDto {
    private Long cart_prod_seq;
    private Long prod_cd;
    private Long opt_seq;
    private String typ;
    private String soldout_yn;
    private Integer cp_qty; // 장바구니 상품 수량
    private Integer po_qty; // 수량
    private String name;
    private Integer cnsmr_prc;
    private Integer sale_prc;
    private String cnsmr_prc_format; // 1000 -> 1,000원
    private String sale_prc_format; // 1000 -> 1,000원
    private Integer curr_inv; // 주문넣을때 검증 재고 수량, 장바구니 상품 띄울 땐 최대 재고수량

}
