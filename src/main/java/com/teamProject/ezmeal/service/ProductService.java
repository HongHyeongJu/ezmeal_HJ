package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.*;
import com.teamProject.ezmeal.domain.ProductDiscountDto;
import com.teamProject.ezmeal.domain.ProductDto;
import com.teamProject.ezmeal.domain.ProductImgDto;
import com.teamProject.ezmeal.domain.ProductOptionDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

@Service
public class ProductService {

    @Autowired
    ProductDao productDao;
    @Autowired
    ProductDiscountDao productDiscountDao;
    @Autowired
    ProductImgDao productImgDao;
    @Autowired
    ProductInventoryDao productInventoryDao;
    @Autowired
    ProductOptionDao productOptionDao;
    @Autowired
    ProductReviewDao productReviewDao;

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
/*여러 다오의 메서드 호출할 때, 왼쪽창 열고 다오랑, 맵퍼 주석 확인해서 상황에 적절한 메서드 호출하기*/

    /*카테고리 클릭시 -> 해당 카테고리 상품List(mini), 해당 상품관련 대표이미지 LIst, 옵션있는 상품일경우 옵션객체List(카테고리용), */
    /*(상품 select에 재고 확인 조건 넣기) 할인코드 List(할인율 필요), 해당상품 리뷰 평점, 리뷰숫자 (업는건 0으로?)*/
    /* 분류코드로 상품 리스트받기 */

    public HashMap getProductListByCateCd (String cate_cd) throws SQLException {

        try {

                /*카테고리 상품 리스트*/
                List<ProductDto> prodList = productDao.selectProductListByCateCdMini(cate_cd);

                /*카테고리 상품 '대표'이미지 리스트*/
                List<ProductImgDto> prodImgList = productImgDao.selectCateCdImgTyp(cate_cd);
                /*카테고리 상품의 옵션 리스트*/
                List<ProductOptionDto> prodOptList =  productOptionDao.selectOptionInProductCategory(cate_cd);
                /*할인율 강조를 위한 할인코드 리스트 */
                List<ProductDiscountDto> discountList = productDiscountDao.selectDiscountListByCateCd();
                /*상품 평점, 리뷰 숫자*/
                Map<Long,Double> reviewAngMap = productReviewDao.selectReviewAvgForProdList(cate_cd);
                /**/
                Map<Long,Integer> reviewCntMap = productReviewDao.selectReviewCntForProdList(cate_cd);
                HashMap ProdListMap = new HashMap<>();
                ProdListMap.put("prodList",prodList);
                ProdListMap.put("prodImgList",prodImgList);
                ProdListMap.put("prodOptList",prodOptList);
                ProdListMap.put("reviewAngMap",reviewAngMap);
                ProdListMap.put("reviewCntMap",reviewCntMap);

                return ProdListMap;

        } catch (SQLException e) {
            logger.error("Error occurred in getProductListByCateCd", e);
            throw new RuntimeException("DB 조회 중 에러가 발생했습니다.", e);
        }
    }


    /*다양한 순서의 */




    /*상품 상세 페이지 -> 해당 상품코드 상품 1개 찾기, 이미지 모두 가져오기, 옵션있으면 옵션 상품 List로 전달, 해당 할인코드
    * 리뷰평점, 리뷰숫자, 리뷰 List, 문의 List받아오기  없을때는, 예외는 0으로 반환(컨트롤러에서 해당상품이 없습니다.->index? )*/





    /*-----------관리자용-------------------*/
    /*상품 등록 페이지-> 상품, 할인, 이미지, 재고(0,safe), 옵션(y일 경우) n개 생성*/

    /*상품 등록 트랜잭션-> 상품등록, 이미지등록, 재고(0,safe)생성, 옵션(y일 경우) n개 생성*/

    /*상품 읽기/수정 페이지  전체 상품 목록을 가져온다. 할인, 이미지, 옵션 가져오기*/

    /*상품 수정 완료 -> 전체상품목록이랑 비교해서 equals가 아닌것만 update, 이미지 리스트도 마찬가지. 옵션도 마찬가지*/






    /*만들일 없을 것 같지만. 관리자의 할인코드 생성/수정/삭제 */



}



//@Service
//@EnableRetry
//public class ProductService {

    /*이런게 있다고 함 */
//    @Retryable(value = SQLException.class, maxAttempts = 3, backoff = @Backoff(delay = 1000))
//    @Recover
//    public void recover(SQLException e) {
//        logger.error("Error occurred in getProductListByCateCd", e);
//        throw new RuntimeException("DB 조회 중 에러가 발생했습니다.", e);
//    }
