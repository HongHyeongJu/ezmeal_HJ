package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.WishListDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class WishListDaoTest {

    @Autowired
    WishListDao wishListDao;

    @Test
    public void test0() throws SQLException {

    }

    @Test
    public void test() throws SQLException {

        /*insert 테스트를 위한 WishListDto객체 생성하기*/
        WishListDto wish1 = new WishListDto(1111L,3L);
        /*DAO에 만든 메서드 호출해서 사용하기*/
        Integer deleteNum = wishListDao.deleteWishList(wish1);

        /*DAO에 만든 메서드 호출해서 사용하기*/
        Integer insertNum = wishListDao.insertWishList(wish1);

        /*콘솔 찍어보기*/
        System.out.println("insertNum: "+insertNum);

        /*TDD메서드 사용하기. 내 예상값으로*/
        assertTrue(insertNum==1);

        /*DAO에 만든 메서드 호출해서 사용하기*/
        deleteNum = wishListDao.deleteWishList(wish1);

        /*콘솔 찍어보기*/
        System.out.println("deleteNum: "+deleteNum);

        /*TDD메서드 사용하기. 내 예상값으로*/
        assertTrue(deleteNum==1);


    }

    @Test
    public void test2() throws SQLException {

        /*insert 테스트를 위한 WishListDto객체 생성하기*/
        WishListDto wish1 = new WishListDto(1111L,3L);

        /*DAO에 만든 메서드 호출해서 사용하기*/
        Integer deleteNum = wishListDao.deleteWishList(wish1);

        /*콘솔 찍어보기*/
        System.out.println("deleteNum: "+deleteNum);

        /*TDD메서드 사용하기. 내 예상값으로*/
        assertTrue(deleteNum==1);

    }





}




/*deleteWishList 매개변수 ishListDto 객체로 했을 때의 TEST코드 */
//    @Test
//    public void test2() throws SQLException {
//        /*delete 테스트를 위한 WishListDto객체 생성하기*/
//        WishListDto wish1 = new WishListDto(1L,1111L,3L, LocalDateTime.now(), "ateam", LocalDateTime.now() ,"ateam");
//
//        /*DAO에 만든 메서드 호출해서 사용하기*/
//        Integer deleteNum = wishListDao.deleteWishList(wish1);
//
//        /*콘솔 찍어보기*/
//        System.out.println("deleteNum: "+deleteNum);
//
//        /*TDD메서드 사용하기. 내 예상값으로*/
//        assertTrue(deleteNum==1);
//
//    }
