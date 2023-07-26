package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.DeliveryMasterDto;
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
public class DeliveryMasterDaoTest {
    @Autowired
    DeliveryMasterDao deliveryMasterDao;
    @Test
    public void insertDelivery(){
        List<DeliveryMasterDto> deliveryMasterDtoList = new ArrayList<>();
        DeliveryMasterDto deliveryMasterDto = new DeliveryMasterDto(1L,2l,"abc","abc","ac","ab","ab","ab","req","in","st","m","y","상");
        DeliveryMasterDto deliveryMasterDto1 = new DeliveryMasterDto(1L,3L,"abc","abc","ac","ab","ab","ab","req","in","st","m","y","상");
        DeliveryMasterDto deliveryMasterDto2 = new DeliveryMasterDto(1L,4L,"abc","abc","ac","ab","ab","ab","req","in","st","m","y","상");
        deliveryMasterDtoList.add(deliveryMasterDto);
        deliveryMasterDtoList.add(deliveryMasterDto1);
        deliveryMasterDtoList.add(deliveryMasterDto2);
        deliveryMasterDao.insertDeliveryMaster(deliveryMasterDtoList);
    }
}