package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.ProductDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ProductDaoTest {

    @Autowired
    ProductDao productDao;


    /*상품 생성 검색 삭제 TEST   순서 INSERT -> 마지막 상품코드 얻기 -> 그 상품코드로 객체 꺼내기 -> 삭제하기 */
    @Test
    public void testInsertSelectDeleteProduct() throws Exception  {
        ProductDto productDto = new ProductDto("0201","1","CUST001","DC10%","불닭가슴살콘치즈볶음밥","[잇메이트] 불닭볶음밥 콘치즈맛","냉동","-18도 이하 냉동보관",2900,3900,3900,null,"신상 불닭시리즈 볶음밥",null,1,null,null,"상품설명/상세정보 참조","냉동상태인 제품포장을 살짝 뜯어 전자레인지에 넣어주세요.   냉동상태(700W) : 전자레인지에 약 1분 30초 조리 후 뒤집어서 30초 조리해주세요 / 해동상태(700W) : 해동된 제품은 전자레인지에 약 40~54초 조리해주세요",
                "활용법 : 식단 조절시 활용, 샐러드와 같이 드세요","별도표기일까지","2023/01/01","2024/10/24","ateam02","2023/06/21","y","y","n","y","n",null,"ateam02","ateam02");

        /*상품 생성*/
        int insert_num = productDao.insertProduct(productDto);
        assertTrue(insert_num==1);

        /*방금 등록한 상품코드 확인하기*/
        Long select_num = productDao.selectMaxProdCd();
        System.out.println("select_num:"+select_num);

        /*검색해서 출력하기*/
        assertTrue(null!=productDao.selectProductByProdCd(select_num));
        ProductDto selectProd = productDao.selectProductByProdCd(select_num);
        System.out.println(selectProd.toString());

        /*삭제하기*/
        int  del_num = productDao.deleteForTDD(select_num);
        assertTrue(del_num==1);

    }

    /*상품 왜 안꺼내지지?*/
    @Test
    public void selectTest() throws SQLException {
        ProductDto selectProd = productDao.selectProductByProdCd(2L);
        System.out.println(selectProd.toString());
    }


    /*재고수량에 따른 새벽 상품 상태코드 업데이트*/
    @Test
    public void updateAllProdStatus() throws SQLException {
        Integer update_num = productDao.updateAllProdStatus();
        System.out.println("update_num:"+update_num);
    }


    /*주문으로 인한 재고0으로 해당 상품코드 상태만 3으로 변경*/
    @Test
    public void updateInvTempSoldOutDueToProdStatus() throws SQLException {
        Integer update_num = productDao.updateProdStatusDueToInvTempSoldOut(30L);
        assertTrue(update_num==1);
        System.out.println("update_num: "+update_num);
        ProductDto selectProd = productDao.selectProductByProdCdForTdd(30L);
        if (selectProd != null) {
            System.out.println(selectProd.toString());
        } else {
            System.out.println("Product not found");
        }
    }


    /* 가장 큰 prod_cd 찾기 */
    @Test
    public void lasfProdCd() throws SQLException {
        Long select_num = productDao.selectMaxProdCd();
        System.out.println("select_num:"+select_num);

    }



    /* 상품코드로 상품찾기 */
    @Test
    public void testSelectProdCd() throws SQLException {
        /* 상품코드 하나 정해서 DB에서 SELECT하기 */
        Long prod_cd = 14L;

        /* 객체 나왔는지 확인 */
        assertTrue(null!=productDao.selectProductByProdCd(prod_cd));

        /* 객체 저장 */
        ProductDto selectProd = productDao.selectProductByProdCd(prod_cd);

        /* 출력해보기 */
        System.out.println(selectProd.toString());
    }



    @Test
    public void testSelectProdCd2() throws SQLException {
        /* 없는 상품코드 하나 정해서 DB에서 SELECT하기 */
        Long prod_cd = 1111L;
        /* 객체 나왔는지 확인 */
        assertTrue(null==productDao.selectProductByProdCd(prod_cd));

        /* 객체 저장 */
//        ProductDto selectProd = productDao.selectProdCd(prod_cd);
        /* 출력해보기 */
//        System.out.println(selectProd.toString());
    }



    /* 카테고리로 검색하기 테스트 */
    @Test
    public void testselectCateCd() throws SQLException {
        String cateCd = "01%";
        List list = productDao.selectProductListByCateCd(cateCd);
        for(int i=0; i <list.size() ; i++ ){
            System.out.println(list.get(i).toString());
        }
    }


    /* 카테고리로  일부 컬럼만 받아도 괜찮을까? 검색하기 테스트 */
    @Test
    public void testselectCateCdMini() throws SQLException {
        String cateCd = "01%";
        List list = productDao.selectProductListByCateCdMini(cateCd);
        for(int i=0; i <list.size() ; i++ ){
            System.out.println(list.get(i).toString());
        }
    }





    /* 카테고리로 검색하기 테스트 (가격 저렴한 순) */
    @Test
    public void testselectCateCdCheap() throws SQLException {
        String cateCd = "01";
        List list = productDao.selectProductListByCateCd(cateCd);
        for(int i=0; i <list.size() ; i++ ){
            System.out.println(list.get(i).toString());
        }
    }


    /* 상품명의 단어로 검색하기 */
    @Test
    public void testSearchName() throws SQLException {
        String searchWord = "닭가슴살";
        List list = productDao.selectByName(searchWord);

        for(int i=0; i <list.size() ; i++ ){
            ProductDto dto = (ProductDto)list.get(i);
            System.out.println(dto.getName());
        }

        String searchWord2 = "매콤";
        List list2 = productDao.selectByName(searchWord2);

        for(int i=0; i <list2.size() ; i++ ){
            ProductDto dto = (ProductDto)list2.get(i);
            System.out.println(dto.getName());
        }
    }




}