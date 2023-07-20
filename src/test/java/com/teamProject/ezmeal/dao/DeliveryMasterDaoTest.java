package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.DeliveryMasterDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class DeliveryMasterDaoTest {
    @Autowired
    DeliveryMasterDao deliveryMasterDao;
    @Test
    public void insertDeliveryMaster(){
        DeliveryMasterDto deliveryMasterDto = new DeliveryMasterDto(1L,"abc","abc","ac","ab","ab","ab","req","in","st","m","y","상");
        deliveryMasterDao.insertDeliveryMaster(deliveryMasterDto);
    }
}