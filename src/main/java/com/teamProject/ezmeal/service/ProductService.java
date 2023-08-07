package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.*;
import com.teamProject.ezmeal.domain.*;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

@Service
public class ProductService {

    @Autowired
    ProductDao productDao;
    @Autowired
    ProductDiscountDao productDiscountDao;
    @Autowired
    ProductImgService productImgService;
    @Autowired
    ProductImgDao productImgDao;
    @Autowired
    ProductInventoryDao productInventoryDao;
    @Autowired
    ProductOptionDao productOptionDao;
    @Autowired
    ProductReviewDao productReviewDao;
    @Autowired
    ProductCategoryDao productCategoryDao;
    @Autowired
    CustDao custDao;
    @Autowired
    ProductStatusDao productStatusDao;
    @Autowired
    ProductCategoryService productCategoryService;
    @Autowired
    ProductReviewService productReviewService;

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    /*여러 다오의 메서드 호출할 때, 왼쪽창 열고 다오랑, 맵퍼 주석 확인해서 상황에 적절한 메서드 호출하기*/

    /*카테고리 클릭시 -> 해당 카테고리 상품List(mini), 해당 상품관련 대표이미지 List, 옵션있는 상품일경우 옵션객체List(카테고리용), */
    /*(상품 select에 재고 확인 조건 넣기) 할인코드 List(할인율 필요), 해당상품 리뷰 평점, 리뷰숫자 (업는건 0으로?)*/
    /* 분류코드로 상품 리스트받기 */

    /*카테고리별 상품목록을 위한 메서드*/
    public HashMap getProductListByCateCd (String cate_cd, String sortkeyword) throws SQLException {

        try {

            /*카테고리 상품 리스트*/
            List<ProductDto> prodList;

            if ("default".equals(sortkeyword)) {
                prodList = productDao.selectProductListByCateCdMiniLowprc(cate_cd);
                System.out.println("1");
            } else if ("lowprc".equals(sortkeyword)) {
                prodList = productDao.selectProductListByCateCdMiniLowprc(cate_cd);
                System.out.println("2");
            } else if ("high".equals(sortkeyword)) {
                prodList = productDao.selectProductListByCateCdMiniHighprc(cate_cd);
                System.out.println("3");
            } else if ("new".equals(sortkeyword)) {
                prodList = productDao.selectProductListByCateCdMiniNew(cate_cd);
                System.out.println("4");
            } else {
                prodList = productDao.selectProductListByCateCdMini(cate_cd);
                System.out.println("5");
            }

            Map<String, String> cateCdAndNameMap =productCategoryService.getAllProdCateCdAndNameList();
            String cateName = cateCdAndNameMap.get(cate_cd);

            HashMap ProdListMap = new HashMap<>();
            ProdListMap.put("prodList",prodList);
            ProdListMap.put("cateName",cateName);
            return ProdListMap;

        } catch (SQLException e) {
            logger.error("Error occurred in getProductListByCateCd", e);
            throw new RuntimeException("DB 조회 중 에러가 발생했습니다.", e);
        }
    }

    /*각 카테고리별 옵션 있는 상품은 K:상품코드 V:옵션리스트 Map으로 전달*/
    public HashMap<Long,List<ProductOptionDto>> prodCdListChangeToOptionMap(String cate_cd) throws SQLException {
        List<Long> prodCdList = productDao.selectProductProdCdListByCateCd(cate_cd);
        HashMap map = new HashMap();
        for(Long prod_cd : prodCdList){
            List<ProductOptionDto> list = productOptionDao.selectOptionProductsByProdCd(prod_cd);
            map.put(prod_cd,list);
        }
        return map;
    }


    /*상품 상세 페이지 -> 해당 상품코드 상품 1개 찾기, 이미지 모두 가져오기, 옵션있으면 옵션 상품 List로 전달, 해당 할인코드
     * 리뷰평점, 리뷰숫자, 리뷰 List, 문의 List받아오기  없을때는, 예외는 0으로 반환(컨트롤러에서 해당상품이 없습니다.->index? )*/
    public HashMap getOneProductByProdCd (Long prod_cd) throws SQLException {
        /*상품 객체*/
        ProductDto product = productDao.selectProductByProdCd(prod_cd);
        /*옵션 List*/
        List<ProductOptionDto> optList = productOptionDao.selectOptionProductsByProdCd(prod_cd);
        /*이미지 List*/
        List<ProductImgDto> imgList = productImgDao.selectProdCdImgAll(prod_cd);
        /*리뷰평점,리뷰숫자*/
        Double reivewAvg = productReviewDao.reviewAverageOneProduct(prod_cd);
        Integer reviewCount = productReviewDao.countReviewOneProduct(prod_cd);
        /*리뷰 List*/
        List<ProductReviewDto> reviewList= productReviewDao.getAllReviewByProdCd(prod_cd);
        /*문의 List...*/

        HashMap prodDetailMap = new HashMap();
        prodDetailMap.put("product",product);
        prodDetailMap.put("optList",optList);
        prodDetailMap.put("imgList",imgList);
        prodDetailMap.put("reivewAvg",reivewAvg);
        prodDetailMap.put("reviewCount",reviewCount);
        prodDetailMap.put("reviewList",reviewList);
        /* (+) 문의 추가해야함...*/

        return prodDetailMap;
    }


    /*메인페이지에 보여줄 상품 5개 들어있는 List * 4개 보내기   직장인, 헬스, 먹잘알, 자취생*/
    public HashMap getMainDisplayProductList() throws SQLException {
        List<ProductDto> emplList = productDao.selectMainEmplList();
        List<ProductDto> healthList = productDao.selectMainHealthList();
        List<ProductDto> homeList = productDao.selectMainHomeList();
        List<ProductDto> eatList = productDao.selectMainEatList();

        HashMap map = new HashMap();
        map.put("healthList", healthList);
        map.put("emplList", emplList);
        map.put("homeList", homeList);
        map.put("eatList", eatList);

        return map;
    }




    /*-----------------------------------------------  관리자  -----------------------------------------*/


    /*관리자 상품 관리 페이지(읽기, 수정용)*/
    public HashMap getOneProductByProdCdForMng (Long prod_cd) throws SQLException {
        /*상품 객체*/
        ProductDto product = productDao.selectProductByProdCdForMng(prod_cd);
        /*옵션 List*/
        List<ProductOptionDto> optList = productOptionDao.selectOptionProductsByProdCd(prod_cd);
        /*이미지 List*/
        List<ProductImgDto> imgList = productImgDao.selectProdCdImgAll(prod_cd);

        /*할인,카테고리,거래처,상태  리스트 받아오기*/
        HashMap listMap = getListForProductRegist();


        HashMap registProductMap = new HashMap();
        registProductMap.put("product",product);
        registProductMap.put("optList",optList);
        registProductMap.put("imgList",imgList);
        registProductMap.put("dcList",listMap.get("dcList"));
        registProductMap.put("cateList",listMap.get("cateList"));
        registProductMap.put("custList",listMap.get("custList"));
        registProductMap.put("stusList",listMap.get("stusList"));

        return registProductMap;
    }



    /* 관리자 페이지 읽기, 수정 시 -> 할인,카테고리,거래처,상태 리스트 받아오기 */
    public HashMap getListForProductRegist () throws SQLException {

        /*할인코드 List*/
        List<ProductDiscountDto> dcList = productDiscountDao.selectDiscountListByCateCd();
        /*카테고리 List*/
        List<ProductCategoryDto> cateList =  productCategoryDao.selectCategoryList();
        /*거래처 List*/
        List<CustDto> custList = custDao.selectCustList();
        /*상품 상태코드 List*/
        List<ProductStatusDto> stusList = productStatusDao.selectAllProdStus();

        HashMap registProductMap = new HashMap();

        registProductMap.put("dcList",dcList);
        registProductMap.put("cateList",cateList);
        registProductMap.put("custList",custList);
        registProductMap.put("stusList",stusList);

        return registProductMap;

    }

    /*헤더 판매 상품화면 (신상품,베스트,특가)을 위한 메서드  (이미지, 옵션, 리뷰 등 조건없이 다 가져옴)*/
    public HashMap getProductSetForHeader (String headerTyp) throws SQLException {

        try {

            /*headerTyp에 따라 상품 리스트 받아오기*/
            List<ProductDto> prodList;
            String headerTitle = "";
            if ("new".equals(headerTyp)) {
                prodList = productDao.selectByNewProduct();
                headerTitle = "신상품";
                System.out.println("new");
            } else if ("best".equals(headerTyp)) {
                prodList = productDao.selectByBestProduct();
                headerTitle = "베스트";
                System.out.println("best");
            } else if ("bigdc".equals(headerTyp)) {
                prodList = productDao.selectByBigDcProduct();
                headerTitle = "특가 | 혜택";
                System.out.println("bigdc");
            } else {
                prodList = productDao.selectByNewProduct();
                headerTitle = "신상품";
                System.out.println("else");
            }

//            System.out.println("서비스 headerTyp: "+headerTyp);
//            System.out.println("서비스 prodList.size(): "+ prodList);


            HashMap fourTypesMap = getAllTypImgOptRivews();

            /*모든상품 '대표'이미지 리스트*/
            Map<Long,ProductImgDto> prodImgMap = (Map<Long,ProductImgDto>)fourTypesMap.get("prodImgMap");
            /*모든상품의 옵션 리스트*/
            Map<Long,List<ProductOptionDto>> prodOptMap = (Map<Long,List<ProductOptionDto>>)fourTypesMap.get("prodOptMap");
            /*모든상품  평점, 리뷰 숫자*/
            Map<Long,Double> reviewAvgMap = (Map<Long,Double>)fourTypesMap.get("reviewAvgMap");
            Map<Long,Integer> reviewCntMap = (Map<Long,Integer>)fourTypesMap.get("reviewCntMap");


            HashMap ProdListMap = new HashMap<>();
            ProdListMap.put("prodList",prodList);
            ProdListMap.put("prodImgMap",prodImgMap);
            ProdListMap.put("prodOptMap",prodOptMap);
            ProdListMap.put("reviewAvgMap",reviewAvgMap);
            ProdListMap.put("reviewCntMap",reviewCntMap);
            ProdListMap.put("headerTitle",headerTitle);

            return ProdListMap;

        } catch (SQLException e) {
            logger.error("Error occurred in getProductSetForHeader", e);
            throw new RuntimeException("DB 조회 중 에러가 발생했습니다.", e);
        }

    }



    /*모두 가져오기 종합세트 대표이미지, 옵션 리스트, 리뷰 평점, 리뷰 총개수*/
    public HashMap getAllTypImgOptRivews() throws SQLException {
        HashMap prepareListMap = new HashMap<>();
        /*모든상품 '대표'이미지 리스트*/
        Map<Long,ProductImgDto> prodImgMap = productImgService.getAllRecentTypImgListConvertToMap();
        if (prodImgMap == null) {
            System.out.println("prodImgMap is null");
        }
        /*모든상품의 옵션 리스트*/
        Map<Long,List<ProductOptionDto>> prodOptMap = prodCdListChangeToOptionMap("0");
//        System.out.println("prodOptMap = " + prodOptMap);
        if (prodOptMap == null) {
            System.out.println("prodOptMap is null");
        }
        /*모든상품  평점, 리뷰 숫자*/
        Map<Long,Object> reviewAvgMap = productReviewService.selectReviewAvgAllProduct();
//        System.out.println("reviewAvgMap = " + reviewAvgMap);

        if (reviewAvgMap == null) {
            System.out.println("reviewAvgMap is null");
        }
        Map<Long,Integer> reviewCntMap = productReviewService.selectReviewCntAllProduct();
        if (reviewCntMap == null) {
            System.out.println("reviewCntMap is null");
        }

        prepareListMap.put("prodImgMap",prodImgMap);
        prepareListMap.put("prodOptMap",prodOptMap);
        prepareListMap.put("reviewAvgMap",reviewAvgMap);
        prepareListMap.put("reviewCntMap",reviewCntMap);

        return prepareListMap;
    }


    public List<ProductDto> getAllProdListForMng() throws SQLException {
        List<ProductDto> list = productDao.selectAllProdListForMng();
        return list;
    }




}


