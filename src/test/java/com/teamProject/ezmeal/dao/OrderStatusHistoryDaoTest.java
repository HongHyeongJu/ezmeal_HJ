package com.teamProject.ezmeal.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class OrderStatusHistoryDaoTest {

    @Autowired
    OrderStatusHistoryDao orderStatusHistoryDao;
    @Test
    public void insertOrderStatusHistory() {
        int i = orderStatusHistoryDao.insertOrderStatusHistory(2L);
        assertEquals(1, i);
    }

    @Test
    public void insert_fixed_complete() {
        int i = orderStatusHistoryDao.insertFixedComplete(2023071794001L);
    }
}