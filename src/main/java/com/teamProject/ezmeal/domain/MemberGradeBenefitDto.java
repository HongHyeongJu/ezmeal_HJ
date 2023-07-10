package com.teamProject.ezmeal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberGradeBenefitDto {
    private long mbr_grd_bnef_seq; // 회원등급별혜택 SEQ
    private String mbr_grd_cd; // 회원등급번호
    private String typ; // 혜택구분(적립퍼센트/쿠폰발급) -> 이 등급에는 이름 쿠폰을 발급해줍니다라는 명시, 실제 쿠폰은 쿠폰은행에서 발행
    private String val; // 혜택값(적립율 3% / (발급쿠폰명))
    private String use_yn; // 혜택사용여부(Y/N)
    private String del_yn; // 혜택삭제여부 (기본값: 'n')
    private String rmk; // 비고
    private LocalDateTime in_dtm; // 최초등록일시-first_registration_datetime (로컬 일시)
    private String in_id; // 최초등록자식별번호
    private LocalDateTime up_dtm; // 최종수정일시-last_modified_datetime (로컬 일시)
    private String up_id; // 최종수정자식별번호

    public MemberGradeBenefitDto(long mbr_grd_bnef_seq, String mbr_grd_cd, String typ, String val, String use_yn, String del_yn) {
        this.mbr_grd_bnef_seq = mbr_grd_bnef_seq;
        this.mbr_grd_cd = mbr_grd_cd;
        this.typ = typ;
        this.val = val;
        this.use_yn = use_yn;
        this.del_yn = del_yn;
    }
}
