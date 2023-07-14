package com.teamProject.ezmeal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPaymentDto {
    private Long ord_delsd_id; // 주문내역번호 (Primary Key)
    private Long ord_id; // 주문번호
    private Long pay_id; // 결제번호
    private LocalDateTime in_dtm; // 최초등록일시
    private String in_id; // 최초등록(자)식별번호
    private LocalDateTime up_dtm; // 최종수정일시
    private String up_id; // 최종수정(자)식별번호
}
