package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.joinDomain.OrderPaymentJoinDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class OrderPaymentDaoTest {
    @Autowired
    OrderPaymentDao orderPaymentDao;

    @Test
    public void insertOrderPayment(){
        Map<String , Long> orderPaymentMap = new HashMap<>();
        orderPaymentMap.put("orderId", 111L);
        orderPaymentMap.put("payId", 212L);
        int i = orderPaymentDao.insertOrderPayment(orderPaymentMap);
        assertEquals(1, i);
    }

    @Test
    public void selectOrderPaymentList(){
        List<OrderPaymentJoinDto> orderPaymentJoinDtos = orderPaymentDao.selectOrderPaymentList(1001L);
        System.out.println("orderPaymentJoinDtos = " + orderPaymentJoinDtos);
    }

    @Test
    public void selectStartEndDate(){
        Map<String, String> stringStringMap = orderPaymentDao.selectStartEndDate(1001L);
        System.out.println("stringStringMap = " + stringStringMap);
    }

    @Test
    public void selectPeriodOrderPaymentList() {
        Map<String, Object> periodData = new HashMap<>();
        periodData.put("mbrId", 1001L);
        periodData.put("startTime", "2023-07-13");
        periodData.put("endTime", "2023-07-13");
        List<OrderPaymentJoinDto> orderPaymentJoinDtos = orderPaymentDao.selectPeriodOrderPaymentList(periodData);
        System.out.println("periodData = " + orderPaymentJoinDtos);
        int size = orderPaymentJoinDtos.size();
        System.out.println("size = " + size);
    }
}