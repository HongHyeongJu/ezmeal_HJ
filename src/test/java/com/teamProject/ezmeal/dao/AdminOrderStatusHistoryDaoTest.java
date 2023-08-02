package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.joinDomain.AdminOrderOrderDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class AdminOrderStatusHistoryDaoTest {

    @Autowired
    AdminOrderStatusHistoryDao adminOrderStatusHistoryDao;
    @Test
    public void insertOrderAfterCheckAdminOrder() {
        List<Long> orderIdList = new ArrayList<>();
        orderIdList.add(20230717940L);
        orderIdList.add(20230717941L);
        AdminOrderOrderDto adminOrderOrderDto = new AdminOrderOrderDto("a2",  "taewan", orderIdList, "발주확인");
        int i = adminOrderStatusHistoryDao.insertOrderAfterCheckAdminOrder(adminOrderOrderDto);
        System.out.println("i = " + i);
    }

    @Test
    public void insert_fixed_complete_auto() {
        int i = adminOrderStatusHistoryDao.insertFixedCompleteAuto();
        System.out.println("i = " + i);
    }

    @Test
    public void insert_fixed_complete_manual() {
        List<Long> dlvarIdList = new ArrayList<>();
        dlvarIdList.add(14L);
        dlvarIdList.add(52L);
        AdminOrderOrderDto adminOrderOrderDto = new AdminOrderOrderDto("a3",  "taewan", dlvarIdList, "관리자 측 수동 구매확정 처리");
        adminOrderStatusHistoryDao.insertFixedCompleteManual(adminOrderOrderDto);
    }


}