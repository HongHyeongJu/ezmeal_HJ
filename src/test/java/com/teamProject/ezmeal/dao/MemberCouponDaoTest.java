package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.MemberCouponDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MemberCouponDaoTest {

    @Autowired
    MemberCouponDao memberCouponDao;

    @Test
    public void selectMemberCoupon() {
        MemberCouponDto memberCouponDto = memberCouponDao.selectMemberCoupon(0l);
        System.out.println("memberCouponDto = " + memberCouponDto);
    }
    @Test
    public void updateUsedCoupon() {
        Map<String, Long> map = new HashMap<>();
        map.put("payId", 1212L);
        map.put("mbrCoupnId", 100000L);
        int i = memberCouponDao.updateUsedCoupon(map);
    }
}