package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.joinDomain.CartJoinProductDto;
import com.teamProject.ezmeal.domain.CartProductDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/* TEST 목표
*  0. TODO test용 c,r,u,d 만들기
*  1. dao 에서 정확한 sql 쿼리를 작성했는지 확인
*  2. controller나 service에서 해결할 수 없는 DB적 에러가 발생시 수행하는 logic 검증 - 차후 진행
*       2.1. 서버 연결을 끊을 경우
*       2.2. 다른 wifi로 인해서 서버 연결이 지연되는 경우 - 시간 재서 취소 시켜버리기
*       2.3. wifi 아예 끊은 경우
* */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CartProductDaoTest {
    @Autowired
    private CartProductDao cartProductDao;

    // 품절 상품 업데이트
    @Test
    public void updateSoldOut(){
        // TODO 1. cartDao insert method 생성되면 재고가 적은 상품 임의로 만들기
        //      2. soldOut을 n으로 update
        //      3. 헤당 logic 수행시 count 세기
        int updateSoldOut = cartProductDao.updateSoldOut(1L);
        assertTrue(updateSoldOut >= 0);

    }

    // 일반 상품 수량 - 품절, 삭제 제외 -> TODO 옵션 생각해서 다시
    @Test
    public void count() {
        // TODO 1. cartDao insert method 생성되면 재고가 적은 상품 임의로 만들기
        //      2. soldOut을 n으로 update
        //      3. 헤당 logic 수행시 count 세기
        int count = cartProductDao.countProduct(1L);
        assertTrue(count >= 0);
    }

    // 일반 상품 : 냉장/냉동/상온 map으로 저장
    @Test
    public void typeProd(){
        List<CartJoinProductDto> CartJoinProductDtos = cartProductDao.selectProduct(1L);
        int size = CartJoinProductDtos.size();
        int countProduct = cartProductDao.countProduct(1L);
        System.out.println("countProduct = " + CartJoinProductDtos +'\n');
        assertEquals(size, 10);

    }

    // 개별 상품 삭제 : JS fetch를 이용한 rest API 수행
    @Test
    public void deleteCartProduct(){
        List<Long> longs = new ArrayList<>();
        longs.add(1L);
        int updateDelete = cartProductDao.deleteCartProduct(longs);
        // 해당 상품의 delete가 Y여도 접근 후 변경만 하면 1 반환
        assertEquals(updateDelete, 1);
    }

    // 해당 회원의 장바구니의 상품 존재여부 검증
    @Test
    public void validation(){
        Map<String, Object> validationMap = new HashMap<>();
        List<Long> longs = new ArrayList<>();
        longs.add(1L);
        validationMap.put("cartSeq", 1L);
        validationMap.put("cartProdSeq", longs);
        int collectionValidationInt = cartProductDao.selectValidation(validationMap);
        assertEquals(collectionValidationInt, 1);

//        validationMap.put("cartProdSeq", 0L);
//        int wrongValidationInt = cartProductDao.selectValidation(validationMap);
//        assertEquals(wrongValidationInt, 0);
    }

    // 수량 update
    @Test
    public void quantity(){
        Map<String, Long> quantityMap = new HashMap<>();
        quantityMap.put("cartProdSeq", 1L);
        quantityMap.put("quantity", 21L);
        int i = cartProductDao.updateQuantity(quantityMap);
        assertEquals(i , 1 );
    }

    // 주문 선택 상품 선택 업데이트
    @Test
    public void updateSelectedProduct(){
        Map<String, Object> selectProductMap = new HashMap<>();
        List<Long> lists = new ArrayList<>();
        lists.add(1L);
        lists.add(2L);
        selectProductMap.put("cartSeq", 1);
        selectProductMap.put("cartProdSeqList", lists);

        int i = cartProductDao.updateSelectedProduct(selectProductMap);
        assertEquals(10, i);
    }


    // 주문하기에 선택된 장바구니 상품 가져오기
    @Test
    public void orderProducts(){
        List<CartJoinProductDto> cartJoinProductDtos = cartProductDao.selectOrderProducts(1L);
        assertEquals(cartJoinProductDtos.size(), 2);
    }


    @Test
    public void checkOrderListSoldOut(){
        List<Long> cartProdSeqList = new ArrayList<>();
        cartProdSeqList.add(11L);
        cartProdSeqList.add(22L);

        List<Long> longs = cartProductDao.selectOrderListSoldOut(cartProdSeqList);
        System.out.println("longs = " + longs);
    }
    @Test
    public void selectOrderListInventory(){
        List<Long> cartProdSeqList = new ArrayList<>();
        cartProdSeqList.add(10L);
        cartProdSeqList.add(4L);

        List<CartJoinProductDto> longs = cartProductDao.selectOrderListInventory(cartProdSeqList);
        System.out.println("longs = " + longs);
    }

    //  주문한 상품 수량 받아오기
    @Test
    public void selectOrderProductNum(){
        int i = cartProductDao.selectOrderProductNum(1L);
        assertEquals(i, 7);
    }

    /* 관리자 */

    // 장바구니에 존재하는 모든 상품 - 삭제 항목 제외
    @Test
    public void prodList() {
        List<CartProductDto> strings = cartProductDao.selectAllProd(1L);
        int ProductListSize = strings.size();
        int countNum = cartProductDao.countProduct(1L);
        // 장바구니 상품은 품절 상품을 포함하기 때문에 품절상품을 제외하는 count보다 크거나 같을 수 밖에 없다.
        assertTrue(ProductListSize >= countNum);
    }
}