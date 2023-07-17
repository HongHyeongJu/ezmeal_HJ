package com.teamProject.ezmeal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusHistoryDto {
    private Integer ord_stus_hist_id; // 주문상태이력 id (Primary Key)
    private Long ord_id; // 주문번호 id
    private Long ord_prod_dtl_id; // 주문상품상세번호 | 1: all (Default 1)
    private String stus; // 상태코드, 주문내역/주문상세
    private LocalDateTime chg_dtm; // 상태변경일시
    private String chg_rsn; // 상태변경이유
    private String rmk; // 비고
    private LocalDateTime in_dtm; // 최초등록일시
    private String in_id; // 최초등록(자)식별번호
    private LocalDateTime up_dtm; // 최종수정일시
    private String up_id; // 최종수정(자)식별번호

    // 첫 이력
    public OrderStatusHistoryDto(Integer ord_stus_hist_id, Long ord_id, Long ord_prod_dtl_id, String stus) {
        this.ord_stus_hist_id = ord_stus_hist_id;
        this.ord_id = ord_id;
        this.ord_prod_dtl_id = ord_prod_dtl_id;
        this.stus = stus;
    }
}
