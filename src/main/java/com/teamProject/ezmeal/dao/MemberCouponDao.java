package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.MemberCouponDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberCouponDao {
    private final SqlSession session;
    private static final String namespace = "memberCouponDao.";

    public MemberCouponDto selectMemberCoupon(Long mbrCoupnId){
        return session.selectOne(namespace + "selectMemberCoupon", mbrCoupnId);
    }

}
