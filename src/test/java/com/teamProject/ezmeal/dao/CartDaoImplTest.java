package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.CartProductDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CartDaoImplTest {
    @Autowired
    private CartDao cartDao;

    @Test
    public void cartSeq() throws Exception {
        Long i = cartDao.cartSeq(1001L);
        assertEquals((long)i,1L);
    }

    @Test
    public void count() throws Exception {
        int count = cartDao.count(1001L);

        System.out.println("count = " + count);
    }

    @Test
    public void prodList() throws Exception {
        List<CartProductDto> strings = cartDao.prodList(1001L);
        int size = strings.size();
        assertEquals(6, size);
        System.out.println("strings = " + strings);
    }

    @Test
    public void address() throws Exception{
    }
}