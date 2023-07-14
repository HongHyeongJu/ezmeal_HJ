package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.ProductDao;
import com.teamProject.ezmeal.dao.ProductOptionDao;
import com.teamProject.ezmeal.domain.ProductDto;
import com.teamProject.ezmeal.domain.ProductOptionDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Test
    public void test55() throws SQLException {
        Map<Long,List<ProductOptionDto>>  map = productService.prodCdListChangeToOptionMap("05");
        map.forEach((k,v)-> System.out.println(k+","+v.size()));

    }


    /* 상품코드로 상품 찾기 */
//    @Test
//    public void searchProdCd() throws SQLException {
//        ProductDto productDto = productService.getProductByProdCd(1L);
//        System.out.println(productDto.toString());
//        assertTrue(productDto!=null);
//    }
//
//    /* 이름으로 상품 관련 상품 검색하기 */
//    @Test
//    public void searchName() throws SQLException {
//        List list = productService.searchProductByName("밥");
//        System.out.println(list.get(0).toString());
//        System.out.println("list.size() : "+ list.size());
//        list.stream().forEach(a-> System.out.println(a.toString()));
//        assertTrue(list!=null);
//    }
//
//    /* 카테코리 코드로 상품 검색하기(카테고리별 페이지용) */
//    @Test
//    public void selectCateCd() throws SQLException {
//        List list = productService.getProductListByCateCd("02");
//        System.out.println(list.get(0).toString());
//        System.out.println("list.size() : "+ list.size());
//        list.stream().forEach(a-> System.out.println(a.toString()));
//        assertTrue(list!=null);
//    }


    /* 다음 상품코드 반환해주는지 확인하는 테스트 */
//    public void incrementProdCd() throws SQLException {
//        String makeNextProdCd = productService.incrementProdCd(1);
//        System.out.println("makeNextProdCd"+makeNextProdCd);
//    }




    /*  */
//    @Test
//    public void getDiscountRateOne() throws SQLException {
//        ProductDto productDto = productService.productDao.selectProdCd("P00002");
//        System.out.println(productDto.getName()+",  "+productDto.getCnsmr_prc()+",  "+productDto.getSale_prc());
//
//        Map map = productService.getDiscountRateOne("P00002");
//        map.forEach((k,v)-> System.out.println("key:"+k+" , value:"+v));
//    }




}