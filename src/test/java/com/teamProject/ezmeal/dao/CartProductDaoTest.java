package com.teamProject.ezmeal.dao;

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
public class CartProductDaoTest {
    @Autowired
    private CartProductDao cartProductDao;

    @Test
    public void count() throws Exception {
        int count = cartProductDao.countProduct(1L);

        System.out.println("count = " + count);
    }

    @Test
    public void prodList() throws Exception {
        List<CartProductDto> strings = cartProductDao.selectAllProd(1001L);
        int size = strings.size();
        assertEquals(6, size);
        System.out.println("strings = " + strings);
    }

    @Test
    public void address() throws Exception{
        List<CartProductDto> cartProductDtos = cartProductDao.cartProducts(1001L, "p00002");
        System.out.println("cartProductDtos = " + cartProductDtos);
        System.out.println("cartProductDtos.get(0) = " + cartProductDtos.get(0));
    }
}