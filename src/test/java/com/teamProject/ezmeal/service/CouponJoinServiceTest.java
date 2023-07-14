package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.CouponJoinDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CouponJoinServiceTest {

    @Autowired
    private CouponJoinService couponJoinService;
    @Test
    public void getCouponPrice() {
        List<Integer> couponPrice = couponJoinService.getCouponPrice(10011221011L);
        System.out.println("couponPrice = " + couponPrice);
        if (couponPrice.size() != 0){
            System.out.println("couponPrice.get(0) = " + couponPrice.get(0));
        }

    }
}