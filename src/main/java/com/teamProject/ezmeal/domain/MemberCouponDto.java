package com.teamProject.ezmeal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberCouponDto {
    private Long mbr_coupn_id;               // 사용자 쿠폰 ID
    private String coupon_issu_cd;            // 쿠폰 발행 코드
    private Integer mbr_id;                  // 회원 번호 ID
    private LocalDateTime issu_dtm;         // 쿠폰 발행 일시
    private String use_yn;                   // 쿠폰 사용 가능 여부
    private Long ord_id;                     // 쿠폰 사용 주문 번호
    private LocalDateTime use_dtm;          // 쿠폰 사용 일시
    private String vld_start_dt;              // 쿠폰 사용 가능 기간(시작)
    private String vld_end_dt;                // 쿠폰 사용 가능 기간(마감)
    private LocalDateTime revr_dtm;         // 쿠폰 부활 일시
    private String del_yn;                   // 쿠폰 삭제 여부 ('n' 또는 'y')
    private String rmk;                     // 쿠폰 발급 관련 업무 메모
    private LocalDateTime in_dtm;           // 최초 등록 일시
    private String in_id;                    // 최초 등록자 식별 번호
    private LocalDateTime up_dtm;           // 최종 수정 일시
    private String up_id;                    // 최종 수정자 식별 번호

}