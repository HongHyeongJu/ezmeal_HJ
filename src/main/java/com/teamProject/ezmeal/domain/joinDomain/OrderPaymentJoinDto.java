package com.teamProject.ezmeal.domain.joinDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPaymentJoinDto {
    private Long ord_id;
    private String prod_smry;
    private String stus;
    private String date_time; // 주문한 시간 -> js에서 난리 안나게 String으로 sql에서 처리
    private String pay_mtd; // 결제수단 | 신용카드사, 카페, 네이버페이
    private Integer pay_prc; // 실결제금액
}
