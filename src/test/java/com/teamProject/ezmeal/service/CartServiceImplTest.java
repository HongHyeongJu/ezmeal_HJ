package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.CartDao;
import com.teamProject.ezmeal.domain.CartProductDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CartServiceImplTest {


    @Autowired
    private CartServiceImpl cartService;

    @Test
    public void getProduct() throws Exception {
        List<CartProductDto> product = cartService.getProduct(1001L);
    }
}