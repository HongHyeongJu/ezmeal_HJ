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
        OrderMasterDto orderMasterDto = orderMasterDao.selectOrderMaster();
    }
}