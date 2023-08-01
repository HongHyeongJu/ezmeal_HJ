package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.joinDomain.AdminOrderOrderDto;
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

//    @Test
//    public void update_shipping_status_only_bundleY() {
//        List<Long> ordIdList = new ArrayList<>();
//        ordIdList.add(20230717940L);
//        ordIdList.add(20230717941L);
//        adminDeliveryDao.updateShippingStatusOnlyBundleY(ordIdList);
//    }

    @Test
    public void selectShippingDeliveryInfo() {
        Map<String, Object> periodData = new HashMap<>();
        periodData.put("startTime", null);
        periodData.put("endTime", null);
//        periodData.put("startTime", "2023-07-12");
//        periodData.put("endTime", "2023-07-22");
        List<Map<String, Object>> maps = adminDeliveryDao.selectShippingDeliveryInfo(periodData);
        System.out.println("maps = " + maps);
    }

    @Test
    public void update_ship_complete_stus() {
        List<Long> dlvarIdlist = new ArrayList<>();
        dlvarIdlist.add(14L);
        dlvarIdlist.add(52L);
        AdminOrderOrderDto adminOrderOrderDto = new AdminOrderOrderDto("h6",  "taewan", dlvarIdlist, "배송 완료");
        int i = adminDeliveryDao.updateShipCompleteStatus(adminOrderOrderDto);
    }

    @Test
    public void insert_ship_complete_delivery_hist() {
        List<Long> dlvarIdlist = new ArrayList<>();
        dlvarIdlist.add(14L);
        dlvarIdlist.add(52L);
        AdminOrderOrderDto adminOrderOrderDto = new AdminOrderOrderDto( dlvarIdlist, "배송 완료");
        int i = adminDeliveryDao.insertShipCompleteDeliveryHistory(adminOrderOrderDto);
        System.out.println("i = " + i);
    }

    @Test
    public void update_ship_complete_order_master_status () {
                List<Long> ordIdList = new ArrayList<>();
        ordIdList.add(20230717940L);
        ordIdList.add(20230717941L);
        AdminOrderOrderDto adminOrderOrderDto = new AdminOrderOrderDto("h6",  "taewan", ordIdList, "배송 완료");
        int i = adminDeliveryDao.updateShipCompleteOrderMasterStatus(adminOrderOrderDto);
        System.out.println("i = " + i);
    }

    @Test
    public void selectCompleteDeliveryInfo() {
        Map<String, Object> periodData = new HashMap<>();
        periodData.put("startTime", null);
        periodData.put("endTime", null);
//        periodData.put("startTime", "2023-07-12");
//        periodData.put("endTime", "2023-07-22");
        List<Map<String, Object>> maps = adminDeliveryDao.selectCompleteDeliveryInfo(periodData);
        System.out.println("maps = " + maps);
    }

    @Test
    public void update_fixed_complete_stus_up() {
        List<Long> list = new ArrayList<>();
        list.add(13L);
        list.add(53L);
        list.add(52L);
        int i = adminDeliveryDao.updateFixedCompleteStatus(list);
    }

    @Test
    public void select_dlvarId_bndl_y() {
        List<Long> ordIdList = new ArrayList<>();
        ordIdList.add(20230717940L);
        ordIdList.add(20230717941L);
        List<Long> longs = adminDeliveryDao.selectAdminDlvarIdBndlY(ordIdList);
        System.out.println("longs = " + longs);
    }

    @Test
    public void seleselect_dlvarId_bndl_n() {
        List<Long> ordIdList = new ArrayList<>();
        ordIdList.add(20230717940L);
        ordIdList.add(20230717941L);
        List<Long> longs = adminDeliveryDao.selectAdminDlvarIdBndlN(ordIdList);
        System.out.println("longs = " + longs);
    }

    @Test
    public void update_invoice_status() {
        List<Long> orderDetailIdList = new ArrayList<>();
        orderDetailIdList.add(2023071794002L);
        orderDetailIdList.add(2023071794101L);
        orderDetailIdList.add(2023071794102L);
        AdminOrderOrderDto adminOrderOrderDto = new AdminOrderOrderDto("h2",  "taewan", orderDetailIdList, "송장 등록");
        int i = adminDeliveryDao.updateInvoiceStatus(adminOrderOrderDto);
        System.out.println("i = " + i);
    }

    @Test
    public void select_dlvarId_count_list() {
        List<Long> ordrIdList = new ArrayList<>();
        ordrIdList.add(20230717940L);
        ordrIdList.add(20230717941L);
        List<Integer> list = adminDeliveryDao.selectDlvarIdCountList(ordrIdList);
        System.out.println("list = " + list);
    }

    @Test
    public void insert_invoice_delivery_history() {
        List<Long> orderIdList = new ArrayList<>();
        orderIdList.add(20230717940L);
        orderIdList.add(20230717941L);
        AdminOrderOrderDto adminOrderOrderDto = new AdminOrderOrderDto( orderIdList, "송장번호등록");
        int i = adminDeliveryDao.insertInvoiceDeliveryHistory(adminOrderOrderDto);
    }
    @Test
    public void update_shipping_status_bndl_y() {
        List<Long> orderIdList = new ArrayList<>();
        orderIdList.add(20230717940L);
        orderIdList.add(20230717941L);
        AdminOrderOrderDto adminOrderOrderDto = new AdminOrderOrderDto("h4",  "taewan", orderIdList, "배송중");
        int i = adminDeliveryDao.updateShippingStatusBundleY(adminOrderOrderDto);
    }

    @Test
    public void update_shipping_status_bndl_n() {
        List<Long> orderIdList = new ArrayList<>();
        orderIdList.add(20230717940L);
        orderIdList.add(20230717941L);
        AdminOrderOrderDto adminOrderOrderDto = new AdminOrderOrderDto("h3",  "taewan", orderIdList, "배송대기");
        int i = adminDeliveryDao.updateShippingStatusBundleN(adminOrderOrderDto);
    }

    @Test
    public void insert_shipping_delivery_history_bndl_y() {
        List<Long> orderIdList = new ArrayList<>();
        orderIdList.add(20230717940L);
        orderIdList.add(20230717941L);
        AdminOrderOrderDto adminOrderOrderDto = new AdminOrderOrderDto(orderIdList, "배송중");
        int i = adminDeliveryDao.insertShippingDeliveryHistoryBundleY(adminOrderOrderDto);
    }

    @Test
    public void insert_shipping_delivery_history_bndl_n() {
        List<Long> orderIdList = new ArrayList<>();
        orderIdList.add(20230717940L);
        orderIdList.add(20230717941L);
        AdminOrderOrderDto adminOrderOrderDto = new AdminOrderOrderDto( orderIdList, "배송대기");
        int i = adminDeliveryDao.insertShippingDeliveryHistoryBundleN(adminOrderOrderDto);
    }

    @Test
    public void update_order_master_shipping_status() {
        List<Long> orderIdList = new ArrayList<>();
        orderIdList.add(20230717940L);
        orderIdList.add(20230717941L);
        int i = adminDeliveryDao.updateOrderMasterShippingStatus(orderIdList);
    }

    @Test
    public void select_dlvarId_from_ordId() {
        List<Long> orderIdList = new ArrayList<>();
        orderIdList.add(20230717940L);
        orderIdList.add(20230717941L);
        List<Long> longs = adminDeliveryDao.selectDeliverIdFromOrderId(orderIdList);
        System.out.println("longs = " + longs);
    }
}