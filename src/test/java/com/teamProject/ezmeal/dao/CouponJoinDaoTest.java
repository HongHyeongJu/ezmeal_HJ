package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.CouponJoinDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CouponJoinDaoTest {

    @Autowired
    private CouponJoinDao couponJoinDao;
    @Test
    public void couponList() {
        List<CouponJoinDto> couponJoinDtos = couponJoinDao.couponList(1001L);
        System.out.println("couponJoinDtos = " + couponJoinDtos);
    }
}