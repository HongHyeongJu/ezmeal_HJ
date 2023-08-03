package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.domain.ProductOptionDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ProductReviewTest {

    @Autowired
    ProductReviewService productReviewService;


    @Test
    public void test() throws SQLException {
        Map<Long,Object> map = productReviewService.selectReviewAvgAllProduct();
        map.forEach((k,v)-> System.out.println("k:"+k+",v:"+v));
        Map<Long,Integer> map2 = productReviewService.selectReviewCntAllProduct();
        map2.forEach((k,v)-> System.out.println("k:"+k+",v:"+v));

    }



}