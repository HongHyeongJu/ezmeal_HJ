package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.PointTransactionHistoryDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class PointTransactionHistoryDaoTest {

    @Autowired
    private PointTransactionHistoryDao pointTransactionHistoryDao;

    @Test
    public void checkPointCanUse(){
        int i = pointTransactionHistoryDao.pointCanUse(1001L);
        System.out.println("i = " + i);
    }

    @Test
    public void updatePointHistory(){
        PointTransactionHistoryDto pointTransactionHistoryDto = new PointTransactionHistoryDto(1001L, "USEPOINT", "상품 결제 사용 포인트", -200, "사용", 123213L);
        int i = pointTransactionHistoryDao.insertPointHistory(pointTransactionHistoryDto);
        assertEquals(1, i);
    }
}