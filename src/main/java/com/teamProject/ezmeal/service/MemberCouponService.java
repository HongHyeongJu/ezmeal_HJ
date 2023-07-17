package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.MemberCouponDao;
import com.teamProject.ezmeal.domain.MemberCouponDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberCouponService {
    private final MemberCouponDao memberCouponDao;

    // 사용한 쿠폰의 pk 가져올려고 생성
    public MemberCouponDto getMemberCoupon (Long memberCouponId){
        return memberCouponDao.selectMemberCoupon(memberCouponId);
    }
}
