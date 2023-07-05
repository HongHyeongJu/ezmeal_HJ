package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.ProductReviewDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ProductReviewDaoTest {

    @Autowired
    ProductReviewDao productReviewDao;

    /*리뷰 총 개수 잘 찾아오는지 확인*/
    @Test
    public void test2() throws SQLException {
        Integer resultCnt = productReviewDao.countReview(3L);
        System.out.println(resultCnt);
    }

    /*리뷰 별점 평균 잘 찾아오는지 확인*/
    @Test
    public void test3() throws SQLException {
        Double resultAvg = productReviewDao.ReviewAverage(3L);
        System.out.println(resultAvg);
    }

    /*카테고리로 검색한 상품들의 별점 평균만 받아오기*/
    @Test
    public void test4() throws SQLException {
        Map<Long,Double> resultMap = productReviewDao.selectReviewAvgForProdList("02");

        System.out.println(resultMap.get(6L));
        System.out.println(resultMap.values().toString());
    }

    /*카테고리로 검색한 상품들의 리뷰 숫자만 받아오기*/
    @Test
    public void test5() throws SQLException {
        Map<Long,Integer> resultMap = productReviewDao.selectReviewCntForProdList("02");

        System.out.println(resultMap.get(6L));
        System.out.println(resultMap.values().toString());
    }




}