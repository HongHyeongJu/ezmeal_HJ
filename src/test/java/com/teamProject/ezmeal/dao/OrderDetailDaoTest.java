package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.OrderDetailDto;
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
public class OrderDetailDaoTest {

    @Autowired
    OrderDetailDao orderDetailDao;
    @Test
    public void insertOrderDetail() {

//        List<OrderDetailDto> orderDetailList = new ArrayList<>();
//
//        OrderDetailDto orderDetailDto = new OrderDetailDto(123L, 20230711L, 456L, 100, 90, 3, 270, 30, 240, "oc");
//        OrderDetailDto orderDetailDto1 = new기 OrderDetailDto(122L, 20230711L, 456L, 100, 90, 3, 270, 30, 240, "oc");
//        OrderDetailDto orderDetailDto2 = new OrderDetailDto(124L, 20230711L, 456L, 100, 90, 3, 270, 30, 240, "oc");
//
//        orderDetailList.add(orderDetailDto);
//        orderDetailList.add(orderDetailDto1);
//        orderDetailList.add(orderDetailDto2);

        List<OrderDetailDto> orderDetailList = new ArrayList<>();

        long[] ids = {123L, 122L, 124L}; // prod_cd
        long[] dates = {20230711L, 20230711L, 20230711L}; // ord_id
        long[] productIds = {456L, 456L, 456L}; // opt_cd
        int[] quantities = {100, 100, 100}; // cnsmr_prc
        int[] prices = {90, 90, 90}; // seler_prc
        int[] discounts = {3, 3, 3}; // qty
        int[] totalPrices = {270, 270, 270}; // tot_prc
        int[] shippingFees = {30, 30, 30}; // dc_prc
        int[] amountPayables = {240, 240, 240}; // setl_expt_prc
        String[] orderCodes = {"oc", "oc", "oc"}; // stus

        for (int i = 0; i < ids.length; i++) {
            OrderDetailDto orderDetailDto = new OrderDetailDto(
                    ids[i], dates[i], productIds[i],"name", quantities[i], prices[i],
                    discounts[i], totalPrices[i], shippingFees[i], amountPayables[i], orderCodes[i]
            );
            orderDetailList.add(orderDetailDto);
        }

        int i = orderDetailDao.insertOrderDetail(orderDetailList);
        assertEquals(i, 3);

    }
}