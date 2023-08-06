package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.ProductReviewDto;
import com.teamProject.ezmeal.domain.ProductReviewTotalDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ProductReviewDaoTest {

    @Autowired
    ProductReviewDao productReviewDao;

    /* Dto생성. insert. -> 방금것 꺼내서 toString. revw_id찾아서 select해보기.*/
    @Test
    public void insertReview() throws SQLException {
        /*리뷰 생성*/
        LocalDate today = LocalDate.now();
        ProductReviewDto dto = new ProductReviewDto(9999L, 30L, 1010L, "김*바","  맛있네요", "후기를 30자나쓰라니 힘드네요",
                today, today.plusDays(30), today, 0 , 5, "y", "n", "ateam", "ateam" );
        assertTrue(dto!=null);
        System.out.println(dto.toString());

        /*리뷰 DB에 INSERT*/
        Integer insertNum = productReviewDao.insertReview(dto);
        assertTrue(insertNum==1);

        /*방금 집어넣은 리뷰 꺼내기*/
        dto = productReviewDao.selectLastInsertReview();
        System.out.println(dto.toString());

        /*꺼낸 리뷰의 SEQ 알아내기*/
        Long lastSEQ = dto.getRevw_id();
        System.out.println("lastSEQ: "+ lastSEQ);

        /*리뷰SEQ랑 회원번호로 리뷰 꺼내기*/
        ProductReviewDto dto2 = productReviewDao.selectOneReviewByMember(lastSEQ,1010L);
        System.out.println(dto.toString());

        /*SEQ로 삭제하기(영구삭제)*/
        Integer deleteNum = productReviewDao.deleteReviewForTdd(lastSEQ);
        assertTrue(deleteNum==1);

    }



    @Test
    public void oneProductReviewAverageAndCount() throws SQLException {
        Double reviewAverage = productReviewDao.reviewAverageOneProduct(3L);
        Integer countReview = productReviewDao.countReviewOneProduct(3L);
        System.out.println("reviewAverage: "+reviewAverage);
        System.out.println("countReview: "+countReview);
    }

    /*리뷰 총 개수 잘 찾아오는지 확인*/
    @Test
    public void test2() throws SQLException {
        Integer resultCnt = productReviewDao.countReviewOneProduct(3L);
        System.out.println(resultCnt);
    }

    /*리뷰 별점 평균 잘 찾아오는지 확인*/
    @Test
    public void test3() throws SQLException {
        Double resultAvg = productReviewDao.reviewAverageOneProduct(3L);
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
    }    /*카테고리로 검색한 상품들의 리뷰 숫자만 받아오기*/

    @Test
    public void test6() throws SQLException {
        List<ProductReviewTotalDto> resultMap = productReviewDao.selectReviewAvgAllProduct();

        resultMap.forEach((a) -> {
            System.out.println(a.toString());
        });
    }


    //taewan
    @Test
    public void insert_mini_review_from_orderDetail() {
        ProductReviewDto dto = new ProductReviewDto(111L, "title", "후기를 30자나쓰라니 힘드네요", 5, 4L);
        dto.setOrd_id(1001L);
        dto.setMbr_id(1001L);
        productReviewDao.insertReviewFromOrderDetail(dto);
    }

}