package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.MemberCouponDao;
import com.teamProject.ezmeal.domain.MemberCouponDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberCouponService {
    private final MemberCouponDao memberCouponDao;

    public MemberCouponDto getMemberCoupon (Long memberCouponId){
        return memberCouponDao.selectMemberCoupon(memberCouponId);
    }
}
