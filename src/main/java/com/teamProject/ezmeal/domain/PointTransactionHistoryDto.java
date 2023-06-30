package com.teamProject.ezmeal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointTransactionHistoryDto {
    private long pnt_trjs_hist_seq; // 포인트 거래기록 SEQ-point_transaction_id (primary key)
    private long mbr_id; // 회원번호 ID-mbr_no
    private String trjs_cd; // 포인트 거래 코드
    private String dscpt; // 포인트 거래상세-transaction_detail
    private int trjs_pnt; // 거래 금액
    private String stus; // 거래상태(지급/사용/취소)-point_status
    private int usfl_acmlt_pnt; // 사용가능 누적 포인트
    private String rel_no; // 관련주문번호 / 추천인-related_number
    private LocalDateTime trjs_dtm; // 포인트 거래 일시
    private String vld_start_dt; // 포인트 유효시작일
    private String vld_end_dt; // 포인트 유효종료일
    private String mng; // 포인트 거래 담당자
    private LocalDateTime in_dtm; // 최초등록일시-first_registration_datetime
    private String in_id; // 최초등록자식별번호
    private LocalDateTime up_dtm; // 최종수정일시
    private String up_id; // 최종수정자식별번호
}
