package com.teamProject.ezmeal.dao;

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
public class AdminDeliveryDaoTest {

    @Autowired
    AdminDeliveryDao adminDeliveryDao;

    @Test
    public void selectPrepareDeliveryInfo() {
        Map<String, Object> periodData = new HashMap<>();
//        periodData.put("startTime", null);
//        periodData.put("endTime", null);
        periodData.put("startTime", "2023-07-22");
        periodData.put("endTime", "2023-07-22");
        List<Map<String, Object>> maps = adminDeliveryDao.selectPrepareDeliveryInfo(periodData);
        System.out.println("maps = " + maps);
    }
}