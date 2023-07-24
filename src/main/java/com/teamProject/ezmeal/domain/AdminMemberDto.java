package com.teamProject.ezmeal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminMemberDto {
    private int emp_id;
    private String emp_acct_id;
    private String emp_acct_pw;
    private String name;
    private String email;
    private String hire_dt;
    private int upp_emp_id;
    private String title;
    private int dept_id;
    private int salary;
    private int role;
    private String del_yn;
    private LocalDateTime in_dtm;
    private String in_id;
    private LocalDateTime up_dtm;
    private String up_id;

}
