package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.DeliveryAddressDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static java.time.LocalDateTime.now;
import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class DeliveryAddressDaoImplTest {

    @Autowired
    private DeliveryAddressDao deliveryAddressDao;

    @Test
    public void defaultAddress() throws Exception {
        DeliveryAddressDto defaultAddress = deliveryAddressDao.defaultAddress(1001L);
        Long addrId = defaultAddress.getAddr_id();
        assertSame(2L, addrId);
    }

    @Test
    public void addressList() throws Exception {
        List<DeliveryAddressDto> address = deliveryAddressDao.addressList(1001L);
        assertEquals(address.size(),2);
    }

    // address insert test
    @Test
    public void insertAddress() throws Exception {
        DeliveryAddressDto deliveryAddressDto = new DeliveryAddressDto(1001L, "n", "ncnm","rcpr","010-1234-1234","desti","desti_dtl", "ezmeal", "ezmeal");
        int updateNum = deliveryAddressDao.insertDeliveryAddress(deliveryAddressDto);
        assertEquals(updateNum, 1);
    }
    @Test
    public void updateAddress() throws Exception {
        DeliveryAddressDto deliveryAddressDto = new DeliveryAddressDto(22L, 1001L, "n", "edit","edit","010-1234-1234","desti","desti_dtl", now(),"ezmeal", now(),"ezmeal");
        int i = deliveryAddressDao.updateDeliveryAddress(deliveryAddressDto);
        System.out.println("i = " + i);
        assertEquals(i, 1);
    }

    @Test
    public void resetBasicYN() throws Exception {
        DeliveryAddressDto deliveryAddressDto = new DeliveryAddressDto(23L, 1001L);
        int i = deliveryAddressDao.resetAllBasicYNtoN(deliveryAddressDto);
        assertEquals(i, 1);
    }

    @Test
    public void deleteAddress() throws Exception {
        int i = deliveryAddressDao.deleteDeliveryAddress(21L);
        assertEquals(i, 1);
    }
}