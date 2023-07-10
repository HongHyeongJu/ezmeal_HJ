package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.domain.CartJoinProductDto;
import com.teamProject.ezmeal.domain.CartProductDto;
import org.apache.ibatis.exceptions.PersistenceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/* TEST 목표
 *  0. TODO test용 c,u,d 만들기
 *  1. dao 에서 정확한 sql 쿼리를 작성했는지 확인
 *  2. test에서 예외를 발생시켜서 해당 검증 로직이 잘 동작하는지 확인
 *  3. 기본 비즈니스 로직 수행 확인 -> 예외 처리가 없어서 dao와 동일한 method는 test X -> 나중에 검증이 들어갈 시 체크
 * */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CartProductServiceTest {

    @Autowired
    private CartProductService cartProductService;

    @Test
    public void checkSoldOut() {
    }

    @Test
    public void countProduct() {
    }

    @Test
    public void getProducts() {
        Map<String, List<CartJoinProductDto>> products = cartProductService.getProducts(1L);

    }

    // 임의 sql 예외 발생 수행
    @Test(expected = RuntimeException.class)
    public void testRuntimeException() {
        int maxRetries = 3;
        int retryCount = 0;
        while (retryCount < maxRetries) {
            try {
                // PersistenceException 발생
                throw new PersistenceException(); // 임의 예외 발생
            } catch (PersistenceException e) {
                retryCount++;
                if (retryCount >= maxRetries) {
                    throw new RuntimeException(e);
                }
                System.out.println("대기시간 3초");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    @Test
    public void removeCartProduct() {
    }

    @Test
    public void validateCartProduct() {
        int successResult = cartProductService.validateCartProduct(1L, 1L);
        assertEquals(1, successResult);

        int wrongValidationInt = cartProductService.validateCartProduct(1L, 0L);
        assertEquals(0, wrongValidationInt);
    }

    @Test
    public void getProductList() {
    }
}