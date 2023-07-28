package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.restAPIDomain.InvoiceDeliveryFeeInfo;
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

    @Test
    public void updateAdminInvoiceNum(){
        InvoiceDeliveryFeeInfo info = new InvoiceDeliveryFeeInfo(202307142397L,"ezmeal", "12", 12);
        InvoiceDeliveryFeeInfo info1 = new InvoiceDeliveryFeeInfo(20230717941L,"cj대한통운", "34", 34);
        List<InvoiceDeliveryFeeInfo> invoiceDeliveryFeeInfoList = new ArrayList<>();
        invoiceDeliveryFeeInfoList.add(info);
        invoiceDeliveryFeeInfoList.add(info1);
        int i = adminDeliveryDao.updateAdminInvoiceNum(invoiceDeliveryFeeInfoList);
        System.out.println("i = " + i);
    }
}