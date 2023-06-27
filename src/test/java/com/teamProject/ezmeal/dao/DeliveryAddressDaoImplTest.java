package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.DeliveryAddressDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

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
}