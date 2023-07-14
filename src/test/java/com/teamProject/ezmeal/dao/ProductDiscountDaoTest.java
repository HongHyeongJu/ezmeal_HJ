package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.ProductDiscountDto;
import com.teamProject.ezmeal.domain.ProductOptionDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ProductDiscountDaoTest {


    @Autowired
    ProductDiscountDao productDiscountDao;

    /*할인 생성->등록*/
    /*할인 사용안함 등록삭제 set*/
    /*할인 진짜삭제*/
    /*수정*/
    /*다른테이블 판매가 변경(*2)*/


    /*할인 생성->등록*/
    @Test
    public void creat_And_InsertTest() throws SQLException {
        Integer deleteResult = productDiscountDao.deleteDiscountForTdd("TEST_DC");
        assertTrue(deleteResult==0);

        ProductDiscountDto dto =
                new ProductDiscountDto("TEST_DC", "0", "pt", "50%할인",
                                        LocalDate.now(), LocalDate.now().plusMonths(3),
                                        null, 50, null, "y", "n", null, null, "test", null, "test");
        System.out.println(dto.toString());

        /*등록*/
        Integer insertResult = productDiscountDao.insertDiscount(dto);
        assertTrue(insertResult==1);

        /*할인 진짜삭제*/
//        deleteResult = productDiscountDao.deleteDiscountForTdd("TEST_DC");
//        assertTrue(deleteResult==1);

    }

    /*할인 진짜삭제*/
//    @Test
//    public void delete_real() throws SQLException {
//        Integer deleteResult = productDiscountDao.deleteDiscountForTdd("TEST_DC");
//        assertTrue(deleteResult==1);
//    }

    /*할인 사용안함 등록삭제 set*/
    @Test
    public void use_No_AND_Del_Yes() throws SQLException {
        /*혹시 있을까봐 삭제*/
        Integer deleteResult = productDiscountDao.deleteDiscountForTdd("TEST_DC");

        /*할인코드 생성*/
        ProductDiscountDto dto =
                new ProductDiscountDto("TEST_DC", "0", "pt", "50%할인",
                        LocalDate.now(), LocalDate.now().plusMonths(3),
                        null, 50, null, "y", "n", null, null, "test", null, "test");
        System.out.println(dto.toString());
        /*할인코드 입력*/
        Integer insertResult = productDiscountDao.insertDiscount(dto);
        assertTrue(insertResult==1);

        /*ues_yn 업데이트*/
        Integer use_UpdateResult = productDiscountDao.updateUseYnDiscount("TEST_DC","n");
        System.out.println("use_UpdateResult(1): "+use_UpdateResult);
        assertTrue(use_UpdateResult==1);


        /*하나 꺼내와서 use_yn값 바뀌었는지 확인하기*/
        dto = productDiscountDao.selectDiscount("TEST_DC");
        System.out.println("dto.getUse_yn(): "+dto.getUse_yn());

        use_UpdateResult = productDiscountDao.updateUseYnDiscount("TEST_DC","n");
        System.out.println("use_UpdateResult(2): "+use_UpdateResult);
        assertTrue(use_UpdateResult==1);  /*Use_yn 을 똑같은 값으로 바꾸는데 업데이트 결과가 또 1이 나오는 이유는
                                                     수정할때마다 수정 일시(up_dtm)를 변경하기 때문!*/

        /*del_yn y로 업데이트*/
        Integer del_UpdateResult = productDiscountDao.deleteDiscount("TEST_DC");
        System.out.println("del_UpdateResult(1): "+del_UpdateResult);
        assertTrue(del_UpdateResult==1);
        del_UpdateResult = productDiscountDao.deleteDiscount("TEST_DC");
        System.out.println("del_UpdateResult(2): "+del_UpdateResult);
        assertTrue(del_UpdateResult==1);  /*Del_yn 을 똑같은 값으로 바꾸는데 업데이트 결과가 또 1이 나오는 이유는
                                                     수정할때마다 수정 일시(up_dtm)를 변경하기 때문!*/

        /*할인 진짜삭제*/
        deleteResult = productDiscountDao.deleteDiscountForTdd("TEST_DC");
        assertTrue(deleteResult==1);

    }


    /*수정*/
    @Test
    public void update_discount() throws SQLException {
        productDiscountDao.updateMngRateTest();
    }


    /*다른테이블 판매가 변경(*2)*/
        @Test
    public void dueTo_dueTo() throws SQLException {
        /*가장 흔한 DC10pt 꺼내와서 rate(할인퍼센트)값을 10 -> 50으로 수정하기*/
        ProductDiscountDto dc_dto = productDiscountDao.selectDiscount("DC10pt");
        dc_dto.setRate(10);
        /*다시 테이블에 넣기*/
        productDiscountDao.updateDiscount(dc_dto);


        /*다른 테이블 값 변경 메서드 2개 작동, update결과는 Integer몇일까? */
        Integer update1 = productDiscountDao.updateProductSalePrcDueToDiscountUpdate();
        System.out.println("update1: "+update1);
        Integer update2 = productDiscountDao.updateOptionProductSalePrcDueToDiscountUpdate();
        System.out.println("update2: "+update2);

        /*콘솔창에서 확인하기*/


        /*TDD 확인했으면 원래 값으로 한번 더 TDD 하기! */
        /*dc_dto.setRate(10); 이렇게 수정하고 돌리면됨  */

    }




}