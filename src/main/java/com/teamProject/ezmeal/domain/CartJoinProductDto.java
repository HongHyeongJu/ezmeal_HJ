package com.teamProject.ezmeal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartJoinProductDto {
    private Long cart_prod_seq;
    private String prod_cd;
    private Long opt_seq;
    private String typ;
    private Integer qty;
    private String name;
    private Integer cnsmr_prc;
    private Integer sale_prc;

}
