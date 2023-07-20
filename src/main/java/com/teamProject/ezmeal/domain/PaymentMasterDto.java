package com.teamProject.ezmeal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMasterDto {
    private Long pay_id; // 결제번호(paymentId)1_1 / 1_2 / 2_1
    private Long ord_id; // 주문번호
    private Long mbr_id; // 회원번호 id
    private Long mbr_coupn_id; // 사용자쿠폰 ID
    private String coupn_issu_cd; // 쿠폰 발행 cd
    private String pay_typ; // 결제유형 | 결제/취소/반품
    private LocalDateTime pay_dtm; // 결제일시 / 환불일시
    private String pay_mtd; // 결제수단 | 신용카드사, 카페, 네이버페이
    private String card_num; // 카드번호 | 15~16자리
    private String cvc; // cvc | 341
    private String card_vld_term; // 카드유효기간 | 23/06
    private String card_aprv_id; // 카드승인번호 | 8자리지만 혹시 몰라
    private String rfnd_mtd; // 환불수단 | 카드사부분취소/적립금/환불계좌/재주문 후 취소
    private String acno; // 환불계좌
    private Integer ord_tot_prc; // 상품주문총금액
    private Integer tot_dc_prc; // 상품 총 할인 금액
    private Integer coupn_use_prc; // 쿠폰사용금액
    private Integer use_pnt; // 사용적립금
    private Integer dc_prc; // 할인적용금액 | 쿠폰+ 적립금 = 할인적용금액
    private Integer dexp; // 배송비 | 반품시에만 적용
    private Integer pay_prc; // 실결제금액
    private String reason; // 사유 | 취소사유/반품사유/반품철회사유
    private String rmk; // 비고
    private LocalDateTime in_dtm; // 최초등록일시
    private String in_id; // 최초등록(자)식별번호
    private LocalDateTime up_dtm; // 최종수정일시
    private String up_id; // 최종수정(자)식별번호

    // 카카오페이 결제
    public PaymentMasterDto(Long pay_id, Long ord_id, Long mbr_id, Long mbr_coupn_id, String coupn_issu_cd,
                            String pay_typ, LocalDateTime pay_dtm, String pay_mtd,
                            Integer ord_tot_prc, Integer tot_dc_prc, Integer coupn_use_prc, Integer use_pnt, Integer dc_prc, Integer pay_prc) {
        this.pay_id = pay_id;
        this.ord_id = ord_id;
        this.mbr_id = mbr_id;
        this.mbr_coupn_id = mbr_coupn_id;
        this.coupn_issu_cd = coupn_issu_cd;
        this.pay_typ = pay_typ; // 결제완료, 환불완료, 반품완료, 환불대기, 반품대기
        this.pay_dtm = pay_dtm;
        this.pay_mtd = pay_mtd;
        this.ord_tot_prc = ord_tot_prc;  // 상품주문총금액
        this.tot_dc_prc = tot_dc_prc; // 상품 총 할인 금액
        this.coupn_use_prc = coupn_use_prc; // 쿠폰사용금액
        this.use_pnt = use_pnt; // 사용적립금
        this.dc_prc = dc_prc; // 할인적용금액 | 쿠폰+ 적립금 = 할인적용금액
        this.pay_prc = pay_prc; // 실결제금액
    }
}
