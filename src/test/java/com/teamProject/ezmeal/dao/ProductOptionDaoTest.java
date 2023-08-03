package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.ProductOptionDto;
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
public class ProductOptionDaoTest {


    @Autowired
    ProductOptionDao productOptionDao;

    @Autowired
    ProductCategoryDao productCategoryDao;

    /*카테고리에 내보낼 옵션 객체*/
    @Test
    public void selectOptionInCategory() throws SQLException {
        List<ProductOptionDto> list = productOptionDao.selectOptionInProductCategory("0");
        assertTrue(list!=null);
        System.out.println(list.size());
//        map.forEach((k,v)-> System.out.println("K: "+k+", v :"+v.toString()));



    }

    /*상세 페이지에 내보낼 옵션 객체*/
    @Test
    public void selectOptionInDetail() throws SQLException {
        List<ProductOptionDto> list = productOptionDao.selectOptionInProductDetail(16L);
        assertTrue(list!=null);
        list.forEach(a-> System.out.println(a.toString()));

    }

    /* 옵션 y인 상품코드로 옵션상품 전체 출력하기(list의 형태로) */
    @Test
    public void selectOptionProductByProdCd() throws SQLException {
        List<ProductOptionDto> list = productOptionDao.selectOptionProductsByProdCd(17L);
        assertTrue(list!=null);
        System.out.println("list.size():"+ list);
        list.forEach(a-> System.out.println(a.toString()));
    }

    /*옵션 없는 상품코드로 검색하면 어떻게 될까? -> 빈 리스트*/
    @Test
    public void selectOptionProductByProdCd2() throws SQLException {
        /**/
        List<ProductOptionDto> list = productOptionDao.selectOptionProductsByProdCd(3L);
        System.out.println(list);
    }

    /*옵션 하나 꺼내서 업데이트 해보는 테스트*/
    @Test
    public void selectAndUpdateTest() throws SQLException {

        ProductOptionDto dto = productOptionDao.selectOptionOne(4L);
        System.out.println(dto.toString());

        dto.setName("30개 세트에서 수정 이름"); /*30개 세트*/
        dto.setQty(90); /*30*/

        /*원래대로 돌리기 주석을 수정하며 테스트*/
        dto.setName("30개 세트"); /*30개 세트*/
        dto.setQty(30); /*30*/

        Integer updateNum = productOptionDao.optionUpdate(dto);
        System.out.println("업데이트 결과 updateNum: "+updateNum);
        System.out.println(dto.toString());

    }


    @Test
    public void setProductCategoryDao() throws SQLException {
//        Map<String, String> map = productCategoryDao.selectCateCdAndNameList();
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            String cate_cd = entry.getKey();
//            String name = entry.getValue();
//            System.out.println("cate_cd: " + cate_cd + ", name: " + name);
//        }
    }




}