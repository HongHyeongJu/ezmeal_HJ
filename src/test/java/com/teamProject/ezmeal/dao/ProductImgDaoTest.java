package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.ProductImgDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ProductImgDaoTest {

    @Autowired
    ProductImgDao productImgDao;

    /* 특정코드로 상품 찾고 모든 이미지 객체 toString (현재는 대표이미지만 데이터 있어서 하나 나오지만 최대 5개) */
    @Test
    public void selectProdCdImgAll() throws SQLException {
        List<ProductImgDto> imgList = productImgDao.selectProdCdImgAll("22");
        System.out.println(imgList.size());
        System.out.println(imgList.get(0).toString());

    }

    /* 상품코드로 */
    @Test
    public void selectProdCdImgTyp() throws SQLException {
        ProductImgDto img = productImgDao.selectProdCdTypImg("4","대표");
        System.out.println(img.toString());

    }

    /* 없는 상품코드?? */
    @Test
    public void selectProdCdImgTyp2() throws SQLException {
        ProductImgDto img = productImgDao.selectProdCdTypImg("6","메인1");
        System.out.println(img.toString());

    }

    /*  */
    @Test
    public void selectCateCdImgTyp() throws SQLException {
        List<ProductImgDto> imgList = productImgDao.selectCateCdImgTyp("0603%");
        System.out.println(imgList.size());

    }
}