package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.joinDomain.CouponJoinDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CouponJoinDaoTest {

    @Autowired
    private CouponJoinDao couponJoinDao;
    @Test
    public void couponList() {
        List<CouponJoinDto> couponJoinDtos = couponJoinDao.selectCouponList(1001L);
        System.out.println("couponJoinDtos = " + couponJoinDtos);
    }

    @Test
    public void couponPrice(){
        CouponJoinDto couponJoinDtos = couponJoinDao.selectCouponPrice(1000121211L);
        System.out.println("couponJoinDtos = " + couponJoinDtos);
//        System.out.println("couponJoinDtos.getVal() = " + couponJoinDtos.getVal());
//        System.out.println("couponJoinDtos.getMax_prc() = " + couponJoinDtos.getMax_prc());
    }
}