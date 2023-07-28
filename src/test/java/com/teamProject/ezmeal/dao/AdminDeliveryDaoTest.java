package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.restAPIDomain.BundleData;
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
    public void updateAdminInvoiceNum() {
        InvoiceDeliveryFeeInfo info = new InvoiceDeliveryFeeInfo(202307142397L, "ezmeal", "12", 12);
        InvoiceDeliveryFeeInfo info1 = new InvoiceDeliveryFeeInfo(20230717941L, "cj대한통운", "34", 34);
        List<InvoiceDeliveryFeeInfo> invoiceDeliveryFeeInfoList = new ArrayList<>();
        invoiceDeliveryFeeInfoList.add(info);
        invoiceDeliveryFeeInfoList.add(info1);
        int i = adminDeliveryDao.updateAdminInvoiceNum(invoiceDeliveryFeeInfoList);
        System.out.println("i = " + i);
    }

    @Test
    public void updateAdminBundleYN() {
        List<Long> ordId = new ArrayList<>();
        List<Long> dlvarId = new ArrayList<>();
        ordId.add(20230717940L);
        ordId.add(20230717941L);
        dlvarId.add(13L);

        BundleData bundleData = new BundleData(ordId, dlvarId);
        adminDeliveryDao.updateAdminBundleYN(bundleData);
    }

    @Test
    public void update_shipping_status_only_bundleY() {
        List<Long> ordIdList = new ArrayList<>();
        ordIdList.add(20230717940L);
        ordIdList.add(20230717941L);
        adminDeliveryDao.updateShippingStatusOnlyBundleY(ordIdList);
    }
}