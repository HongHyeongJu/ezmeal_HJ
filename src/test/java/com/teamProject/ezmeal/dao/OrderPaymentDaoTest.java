package com.teamProject.ezmeal.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
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
}