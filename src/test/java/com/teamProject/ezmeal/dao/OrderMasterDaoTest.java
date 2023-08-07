package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.OrderMasterDto;
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
public class OrderMasterDaoTest {
    @Autowired
    private OrderMasterDao orderMasterDao;

    @Test
    public void insertOrderMaster(){
        OrderMasterDto orderMasterDto = new OrderMasterDto(5L,1001L, "oc",3 ,"sss 외 3건");
        int i = orderMasterDao.insertOrderMaster(orderMasterDto);
        assertEquals(1, i);
    }

    @Test
    public void selectOrderMaster() {
        OrderMasterDto orderMasterDto = orderMasterDao.selectOrderMaster(1001L);
    }

    @Test
    public void selectOrderId(){
        Long orderId = orderMasterDao.selectOrderId(1001L);
        System.out.println("orderId = " + orderId);
    }

    @Test
    public void select_check_show_delivery_info() {
        int i = orderMasterDao.selectShowDeliveryInfo(20230717940L);
        System.out.println("i = " + i);
    }
}