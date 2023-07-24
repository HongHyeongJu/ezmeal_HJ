package com.teamProject.ezmeal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointTransactionHistoryDto {
    private Long pnt_trjs_hist_seq; // 포인트 거래기록 SEQ-point_transaction_id (primary key)
    private Long mbr_id; // 회원번호 ID-mbr_no
    private String trjs_cd; // 포인트 거래 코드
    private String name; // 포인트 거래명
    private String dscpt; // 포인트 상세설명
    private Integer trjs_pnt; // 거래 금액
    private String stus; // 거래상태(지급/사용/취소)-point_status
    private Integer usfl_acmlt_pnt; // 사용가능 누적 포인트
    private Long pay_id; // 관련결제번호
    private String rel_no; // 추천인-related_number
    private LocalDateTime trjs_dtm; // 포인트 거래 일시
    private String vld_start_dt; // 포인트 유효시작일
    private String vld_end_dt; // 포인트 유효종료일
    private String mng; // 포인트 거래 담당자
    private LocalDateTime in_dtm; // 최초등록일시-first_registration_datetime
    private String in_id; // 최초등록자식별번호
    private LocalDateTime up_dtm; // 최종수정일시
    private String up_id; // 최종수정자식별번호
    private String formattedTrjsDtm; // 일자 변환

    // 포인트 사용때 적용하는 생성자 - usfl과 trjs_dtm은 mapper에서 수행
    public PointTransactionHistoryDto(long mbr_id, String trjs_cd, String name, int trjs_pnt, String stus, Long pay_id) {
        this.mbr_id = mbr_id;
        this.trjs_cd = trjs_cd;
        this.name = name;
        this.trjs_pnt = trjs_pnt;
        this.stus = stus;
        this.pay_id = pay_id;
    }



    // Baek
    // 포인트 내역을 보여주기 위한 생성자
    public PointTransactionHistoryDto(LocalDateTime trjs_dtm, String dscpt, String vld_end_dt, int trjs_pnt) {
        this.trjs_dtm = trjs_dtm;
        this.dscpt = dscpt;
        this.vld_end_dt = vld_end_dt;
        this.trjs_pnt = trjs_pnt;
    }
}
