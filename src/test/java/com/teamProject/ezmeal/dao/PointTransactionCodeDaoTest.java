package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.PointTransactionCodeDto;
import com.teamProject.ezmeal.domain.PointTransactionHistoryDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class PointTransactionCodeDaoTest {

    @Autowired
    private PointTransactionCodeDao pointTransactionCodeDao;
    @Test
    public void selectUsePoint() {
        PointTransactionCodeDto usepoint = pointTransactionCodeDao.selectUsePointInfo("USEPOINT");
        System.out.println("usepoint = " + usepoint);
    }
}