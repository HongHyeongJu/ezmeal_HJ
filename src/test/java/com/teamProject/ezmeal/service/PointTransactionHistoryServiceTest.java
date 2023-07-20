package com.teamProject.ezmeal.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class PointTransactionHistoryServiceTest {
    @Autowired
    PointTransactionHistoryService pointTransactionHistoryService;
    
    @Test
    public void getUsablePoint() {
        int getPoint = pointTransactionHistoryService.getUsablePoint(1001L);
        System.out.println("getPoint = " + getPoint);
        
    }
}