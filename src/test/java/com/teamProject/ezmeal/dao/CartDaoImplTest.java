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
public class CartDaoImplTest {
    @Autowired
    private CartDao cartDao;

    @Test
    public void cartSeq() throws Exception {
        int i = cartDao.cartSeq(1001);
        assertEquals(i,1);
    }

    @Test
    public void count() throws Exception {
        int count = cartDao.count(1001);

        System.out.println("count = " + count);
    }

    @Test
    public void prodList() throws Exception {
        List<CartProductDto> strings = cartDao.prodList(1001);
        int size = strings.size();
        assertEquals(4, size);
        System.out.println("strings = " + strings);
    }

    @Test
    public void address() throws Exception{

    }
}