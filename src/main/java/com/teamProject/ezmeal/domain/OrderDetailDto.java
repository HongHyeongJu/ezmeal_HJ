package com.teamProject.ezmeal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {
    private Long ord_prod_dtl_id; // 주문상품상세번호 (Primary Key)
    private Long prod_cd; //// 상품코드
    private Long ord_id; //// 주문번호 id
    private Integer prod_seq; // 상품일련번호
    private Long opt_cd; //// 상품옵션코드, 주문상태에 사용할 내용
    private String name; //// 상품명, 옵션 상품일 경우 옵션명 포함
    private Integer cnsmr_prc; //// 상품소비자가격
    private Integer seler_prc; //// 상품판매자가격
    private Integer qty; //// 상품수량
    private Integer tot_prc; //// 총상품금액, 개별상품의 수량*가격
    private Integer dc_prc; //// 상품할인금액, 상품자체할인 * 수량
    private Integer setl_expct_prc; //// 결제예정금액, 총상품금액-상품할인금액
    private String stus; //// 상태코드, 취소~환불, 반품, 배송상태
    private LocalDateTime in_dtm; // 최초등록일시
    private String in_id; // 최초등록식별번호
    private LocalDateTime up_dtm; // 최종수정일시
    private String up_id; // 최종수정_식별번호

    public OrderDetailDto(Long prod_cd, Long ord_id, Long opt_cd, String name,
                          Integer cnsmr_prc, Integer seler_prc, Integer qty,
                          Integer tot_prc, Integer dc_prc, Integer setl_expct_prc, String stus) {
        this.prod_cd = prod_cd;
        this.ord_id = ord_id;
        this.opt_cd = opt_cd;
        this.name = name;
        this.cnsmr_prc = cnsmr_prc;
        this.seler_prc = seler_prc;
        this.qty = qty;
        this.tot_prc = tot_prc;
        this.dc_prc = dc_prc;
        this.setl_expct_prc = setl_expct_prc;
        this.stus = stus; // 상태코드
    }
}
