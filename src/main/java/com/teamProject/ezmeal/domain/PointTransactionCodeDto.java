package com.teamProject.ezmeal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointTransactionCodeDto {
    private String pnt_trjs_cd; // 포인트 거래 코드
    private String name; // 포인트 거래명(구매 적립/추천인 적립 등)
    private String dscpt; // 포인트 거래 상세내용(결제취소로 인한 적립금 환불)
    private Integer pnt; // 적립금액: 리뷰 100, 추천인 3000
    private String del_yn; // 삭제 여부
    private String rmk; // 코드 수정 메모
    private LocalDateTime in_dtm; // 최초 등록 일시
    private String in_id; // 최초 등록자 식별번호
    private LocalDateTime up_dtm; // 최종 수정 일시
    private String up_id; // 최종 수정자 식별번호

    public PointTransactionCodeDto(String pnt_trjs_cd, String name, Integer pnt) {
        this.pnt_trjs_cd = pnt_trjs_cd;
        this.name = name;
        this.pnt = pnt;
    }
}
