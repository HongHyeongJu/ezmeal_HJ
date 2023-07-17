package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.MemberCouponDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class MemberCouponDao {
    private final SqlSession session;
    private static final String namespace = "memberCouponDao.";

    // 사용한 쿠폰의 pk 가져올려고 생성
    public MemberCouponDto selectMemberCoupon(Long mbrCoupnId){
        return session.selectOne(namespace + "selectMemberCoupon", mbrCoupnId);
    }

    // 사용한 coupon update 하기
    public int updateUsedCoupon(Map<String ,Long> updateCouponDataList) {
        return session.update(namespace + "update_used_coupon", updateCouponDataList);
    }
}
