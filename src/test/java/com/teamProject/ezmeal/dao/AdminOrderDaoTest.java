package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.joinDomain.AdminOrderOrderDto;
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
        List<Long> orderIdList = new ArrayList<>();
        orderIdList.add(20230717940L);
        orderIdList.add(20230717941L);
        AdminOrderOrderDto adminOrderOrderDto = new AdminOrderOrderDto("h1",  "taewan", orderIdList, "발주 확인");
        int i = adminOrderDao.updateOrderMasterOrderDetailStatusAfterAdminOrderCheckBtn(adminOrderOrderDto);
        System.out.println("i = " + i);
    }
}