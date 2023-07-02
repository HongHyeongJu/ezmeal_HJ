package com.teamProject.ezmeal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponIssueDto {
    private String coupn_issu_cd;             // 쿠폰 발행 코드 (Primary Key)
    private LocalDateTime issu_sett_dtm;      // 쿠폰 발급 설정 일시
    private String name;                    // 쿠폰 이름
    private String dscpt;                   // 쿠폰 설명
    private String expct_eff;                // 쿠폰 발급 기대 효과
    private String stus;                    // 발급 상태
    private Integer val;                        // 쿠폰 혜택 값
    private Integer max_prc;                 // 정률 쿠폰 할인 인정 금액
    private String issu_mtd_typ;              // 발급 방법 구분
    private String issu_tgt;                 // 발급 대상
    private String issu_start_dt;             // 발급 시작 일자
    private String issu_end_dt;               // 발급 종료 일자
    private String vld_day;                  // 쿠폰 유효 기간
    private String aply_cate;                // 쿠폰 적용 카테고리
    private Integer use_base_prc;             // 사용 가능 기준 금액
    private Integer issu_qty;                    // 발급 쿠폰 수
    private Integer use_qty;                     // 사용 쿠폰 수
    private String mng;                     // 쿠폰 발급 담당자
    private String dwld_url;                 // 쿠폰 다운로드 URL
    private String del_yn;                   // 삭제 여부 ('n' 또는 'y')
    private String rmk;                     // 비고
    private LocalDateTime in_dtm;            // 최초 등록 일시
    private String in_id;                    // 최초 등록자 식별 번호
    private LocalDateTime up_dtm;            // 최종 수정 일시
    private String up_id;                    // 최종 수정자 식별 번호

}
