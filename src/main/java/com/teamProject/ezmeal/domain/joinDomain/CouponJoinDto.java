package com.teamProject.ezmeal.domain.joinDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponJoinDto {
    // MemberCouponDto
    private Long mbr_coupn_id;               // 사용자 쿠폰 ID
    private String vld_start_dt;              // 쿠폰 사용 가능 기간(시작)
    private String vld_end_dt;                // 쿠폰 사용 가능 기간(마감)
    // memberIssue join
    private String name;
    private Integer val;
    private Integer max_prc;
    private Integer use_base_prc;       // 사용가능 조건 중 사용 가능 최소 가격
    private boolean can_use;            // 초기화 안될 경우 init value = false
}
