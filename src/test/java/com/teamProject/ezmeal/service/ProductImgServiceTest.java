package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.domain.ProductImgDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ProductImgServiceTest {


    @Autowired
    ProductImgService productImgService;

    /*상품코드 관련 이미지 전부 가져오기*/
    @Test
    public void test() throws SQLException {
        List<ProductImgDto> imgList = productImgService.selectProdCdImgAll(6L);
        imgList.stream().forEach(ProductImgDto-> System.out.println(ProductImgDto.getProd_cd()+", "+ProductImgDto.getTyp()));
//        assertTrue(imgList!=null);
    }



    @Test
    public void test4() throws SQLException {
        List<ProductImgDto> imgList = productImgService.selectProdCdImgAll(6L);
        imgList.stream().forEach(ProductImgDto-> System.out.println(ProductImgDto.getProd_cd()+", "+ProductImgDto.getTyp()));
        assertTrue(imgList!=null);
        System.out.println(imgList.size());
    }

    /**/
    @Test
    public void test2() throws SQLException {
        List<ProductImgDto> imgList = productImgService.selectProdCdImgAll(6L);
        imgList.stream().forEach(ProductImgDto-> System.out.println(ProductImgDto.getProd_cd()+", "+ProductImgDto.getTyp()));
        System.out.println("--------------------");
        Map<String,String> typeAndUrlMap = productImgService.selectProdCdImgAlltoMap(6L);
        typeAndUrlMap.forEach((k,v)-> System.out.println("k: "+k+", v: "+v));

    }


}