package com.teamProject.ezmeal.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class AdminOrderDaoTest {
    @Autowired
    AdminOrderDao adminOrderDao;

    @Test
    public void selectAdminBeforeManageInfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("startTime", null);
        map.put("endTime", null);
//        map.put("startTime", "2023-07-13");
//        map.put("endTime", "2023-07-13");
        List<Map<String, Object>> maps = adminOrderDao.selectAdminBeforeManageInfo(map);
        System.out.println("maps = " + maps);
    }

    @Test
    public void select_admin_before_manage_date() {
        Map<String, String> stringStringMap = adminOrderDao.selectStartEndDate();
        System.out.println("stringStringMap = " + stringStringMap);
    }

    @Test
    public void updateOrderMasterOrderDetailStatusAfterAdminOrderCheckBtn() {
        List<Long> list = new ArrayList<>();
        list.add(202307235005L);
        list.add( 202307235004L);
        int i = adminOrderDao.updateOrderMasterOrderDetailStatusAfterAdminOrderCheckBtn(list);
        System.out.println("i = " + i);
    }
}