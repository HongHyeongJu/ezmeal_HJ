package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.ProductInventoryDto;
import com.teamProject.ezmeal.domain.restAPIDomain.InventoryData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ProductInventoryDaoTest {


    @Autowired
    ProductInventoryDao productInventoryDao;

    /*테스트 순서*/
    /*test용 상품인 상품코드 30의 재고 로우 삭제(진짜).
    * 30번 관련 재고 객체 생성
    * 30으로 재고 현황 찾기
    * 재고현황 변동 줘보기 (+) (-) (+)
    * 거래처의 대량 입고       아.. 거래처도 만들어야돼 ㅠㅠㅠㅠ
    * 재고 List 받기
    * 재고현황 List + 10000 & -10000하기
    * 재고 의미적 삭제(console 확인)
    * 재고 위험 List 받기*/

    @Test
    public void productInventoryDaoTest() throws SQLException {
        /*찐 삭제*/
        Integer realDeleteNum = productInventoryDao.deleteProductInventForTdd(30L);
        System.out.println("[1] "+"realDeleteNum: "+realDeleteNum);

        /*30번 관련 재고 객체 생성*/
        String today = LocalDate.now().toString().replace("-","/");
        ProductInventoryDto dto = new ProductInventoryDto(30L,20,100, today ,10,"y","n","테스트재고입니다.","test","test");
        System.out.println("[2] dto: "+dto.toString());
        Integer insertNum = productInventoryDao.insertProductInventory(dto);
        System.out.println("[3] insertNum: "+insertNum);

        /*30으로 재고 현황 찾기*/
        dto = productInventoryDao.selectOneProductInventory(30L);
        System.out.println("[4] dto: "+dto.toString());

        /*재고현황 변동 줘보기 (+) (-) (+)*/
        Integer orderAndReturn = 0;
        orderAndReturn += productInventoryDao.updateCurrentInvByOrderAndReturn(30L,1000);
        orderAndReturn += productInventoryDao.updateCurrentInvByOrderAndReturn(30L,-2000);
        orderAndReturn += productInventoryDao.updateCurrentInvByOrderAndReturn(30L,5000);
        System.out.println("[5] orderAndReturn: "+orderAndReturn);

        /*거래처의 대량 입고*/
        Integer incomingNum = productInventoryDao.updateCurrentInvByIncoming(30L, 1111, "2023/07/08" );
        System.out.println("[6] incomingNum: "+incomingNum);
        dto = productInventoryDao.selectOneProductInventory(30L);
        System.out.println("[7] dto: "+dto.toString());

        /*재고 List 받기*/
        List<ProductInventoryDto> list = productInventoryDao.selectAllProductInventory();
        System.out.println("[8] list.size() : "+list.size());
        System.out.println("[9] list.get(27) : "+list.get(27).toString());

        /*재고현황 List + 10000 & -10000하기*/

        productInventoryDao.updateCurrentInvByOrderAndReturn(list.get(27).getProd_cd(), 90000);
        dto = productInventoryDao.selectOneProductInventory(30L);
        System.out.println("[10] dto.getCurr_inv(): "+dto.getCurr_inv());


        /*재고현황 대량(List) 변경*/
        Integer updateListNum = productInventoryDao.updateProductInventory(list.get(27));
        System.out.println("[11] updateListNum : "+updateListNum);

        /*재고 의미적 삭제(console 확인)*/
        Integer deleteYesNum = productInventoryDao.deleteYesProductInventory(30L);
        System.out.println("[12] deleteYesNum : "+deleteYesNum);


        /*재고 위험 List 받기*/
        List<ProductInventoryDto> dangerousList = productInventoryDao.selectDangerousProductInventory();
        System.out.println("[13] dangerousList : "+dangerousList.size());




    }

    // taewan
    // 내가 만든 mapper 잘 작동하는지 test | [ productInventoryMapper - update_qty_after_payment ]
    @Test
    public void updateInventoryAfterPayment() {
        List<InventoryData> list = new ArrayList<>();
        InventoryData data1 = new InventoryData(1L, 1);
        InventoryData data2 = new InventoryData(2L, 1);
        list.add(data1);
        list.add(data2);
        Integer integer = productInventoryDao.updateInventoryAfterPayment(list);
        assertEquals(2, (int)integer);
    }



}