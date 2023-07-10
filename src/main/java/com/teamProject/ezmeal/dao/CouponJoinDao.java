package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.CouponJoinDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CouponJoinDao {
    private final SqlSession session;
    private static final String namespace = "couponJoinDao.";

    public List<CouponJoinDto> couponList(Long mbrId) {
        return session.selectList(namespace + "coupon_list", mbrId);
    }

}
