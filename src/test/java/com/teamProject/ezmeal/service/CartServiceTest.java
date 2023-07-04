package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.domain.CartProductDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CartServiceTest {


    @Autowired
    private CartProductService cartProductService;

    @Test
    public void generalProducts() throws Exception {
        Map<String, List<CartProductDto>> stringListMap = cartProductService.getProducts(1L);
        System.out.println("product = " + stringListMap.size()); //3
        List<CartProductDto> cold = stringListMap.get("ice");
        System.out.println("cold = " + cold);
        String typ = cold.get(0).getTyp();
        assertEquals("냉동", typ);
    }

    @Test
    public void delete() throws Exception{
        Map map = new HashMap();
        map.put("mbrId", 1001L);
        map.put("prodCd", "p00001");
        cartProductService.removeCartProduct(1L);
    }
}