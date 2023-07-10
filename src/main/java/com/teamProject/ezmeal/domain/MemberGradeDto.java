package com.teamProject.ezmeal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberGradeDto {
    private String mbr_grd_cd;   // 회원등급번호-user_grade_number
    private String name;         // 회원등급명-user_grade_name
    private Integer prity;           // 우선순위-grade_priority
    private String use_yn;       // 등급사용여부-grade_usage_status
    private String del_yn;       // 등급삭제여부-grade_deletion_status (기본값: 'n')
    private String rmk;          // 등급메모-grade_memo
    private LocalDateTime in_dtm;   // 최초등록일시-first_registration_datetime (로컬 일시)
    private String in_id;        // 최초등록자식별번호
    private LocalDateTime up_dtm;   // 최종수정일시-last_modified_datetime (로컬 일시)
    private String up_id;        // 최종수정자식별번호

    public MemberGradeDto(String mbr_grd_cd, String name, Integer prity, String use_yn, String del_yn) {
        this.mbr_grd_cd = mbr_grd_cd;
        this.name = name;
        this.prity = prity;
        this.use_yn = use_yn;
        this.del_yn = del_yn;
    }
}
