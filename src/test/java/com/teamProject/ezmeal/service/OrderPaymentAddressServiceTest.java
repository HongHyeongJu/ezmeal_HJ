package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.OrderMasterDao;
import com.teamProject.ezmeal.dao.PaymentMasterDao;
import com.teamProject.ezmeal.domain.OrderMasterDto;
import com.teamProject.ezmeal.domain.PaymentMasterDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class OrderPaymentAddressServiceTest {

    @Autowired
    private OrderMasterDao orderMasterDao;
    @Autowired
    private PaymentMasterDao paymentMasterDao;
    @Test
    public void registerOrderMaster() {
        OrderMasterDto orderMasterDto = new OrderMasterDto(1L,1001L,"oc",123, "코코넛 외 4건");
        int i = orderMasterDao.insertOrderMaster(orderMasterDto);
        assertEquals(i,1);
    }

    @Test
    public void registerPaymentMaster() {
        PaymentMasterDto paymentMasterDto = new PaymentMasterDto(3L, 1L, 1001L, 10000L,
                "couponpk", "결제완료", LocalDateTime.now(),"kakao",
                100, 100, 10, 10, 10, 10);
        System.out.println("LocalDateTime.now() = " + LocalDateTime.now());
        int i = paymentMasterDao.insertPaymentMaster(paymentMasterDto);
        assertEquals(i,1);
    }
}