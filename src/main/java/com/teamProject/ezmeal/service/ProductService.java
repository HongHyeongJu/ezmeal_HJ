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
import java.util.ArrayList;
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

            System.out.println("sortkeyword: "+sortkeyword);
            System.out.println("prodList.size(): "+ prodList);

            /*카테고리 상품 '대표'이미지 리스트*/
            Map<Long,ProductImgDto> prodImgMap = productImgService.cateCdImgListConvertToMap(cate_cd);
            /*카테고리 상품의 옵션 리스트*/
            Map<Long,List<ProductOptionDto>> prodOptMap =  prodCdListChangeToOptionMap(cate_cd);
            /*할인율 강조를 위한 할인코드 리스트 */
//            List<ProductDiscountDto> discountList = productDiscountDao.selectDiscountListByCateCd();
            /*상품 평점, 리뷰 숫자*/
            Map<Long,Double> reviewAvgMap = productReviewDao.selectReviewAvgForProdList(cate_cd);
            Map<Long,Integer> reviewCntMap = productReviewDao.selectReviewCntForProdList(cate_cd);

            HashMap ProdListMap = new HashMap<>();
            ProdListMap.put("prodList",prodList);
            ProdListMap.put("prodImgMap",prodImgMap);
            ProdListMap.put("prodOptMap",prodOptMap);
//            ProdListMap.put("discountList",discountList);
            ProdListMap.put("reviewAvgMap",reviewAvgMap);
            ProdListMap.put("reviewCntMap",reviewCntMap);

            return ProdListMap;

        } catch (SQLException e) {
            logger.error("Error occurred in getProductListByCateCd", e);
            throw new RuntimeException("DB 조회 중 에러가 발생했습니다.", e);
        }
    }

    /*각 카테고리별 옵션 있는 상품은 K:상품코드 V:옵션리스트 Map으로 전달*/
    public Map<Long,List<ProductOptionDto>> prodCdListChangeToOptionMap(String cate_cd) throws SQLException {
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
        List<ProductDto> healthList = new ArrayList<>();
        List<ProductDto> emplList = new ArrayList<>();
        List<ProductDto> homeList = new ArrayList<>();
        List<ProductDto> eatList = new ArrayList<>();

        healthList.add(productDao.selectProductByProdCd(5L));
        healthList.add(productDao.selectProductByProdCd(6L));
        healthList.add(productDao.selectProductByProdCd(9L));
        healthList.add(productDao.selectProductByProdCd(20L));
        healthList.add(productDao.selectProductByProdCd(27L));

        emplList.add(productDao.selectProductByProdCd(25L));
        emplList.add(productDao.selectProductByProdCd(5L));
        emplList.add(productDao.selectProductByProdCd(8L));
        emplList.add(productDao.selectProductByProdCd(9L));
        emplList.add(productDao.selectProductByProdCd(20L));

        eatList.add(productDao.selectProductByProdCd(30L));
        eatList.add(productDao.selectProductByProdCd(20L));
        eatList.add(productDao.selectProductByProdCd(1L));
        eatList.add(productDao.selectProductByProdCd(8L));
        eatList.add(productDao.selectProductByProdCd(9L));

        homeList.add(productDao.selectProductByProdCd(16L));
        homeList.add(productDao.selectProductByProdCd(17L));
        homeList.add(productDao.selectProductByProdCd(18L));
        homeList.add(productDao.selectProductByProdCd(40L));
        homeList.add(productDao.selectProductByProdCd(41L));


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

    /*관리자 상품 등록 메서드*/
    /*상품 1개와 옵션 리스트를 매개변수로 받는다. 이거 PK중요해서 트랜잭션 해야함
    *optList size() == 0이면 바로 아래 로직.
    *optList size() > 0이면 새 옵션 객체 만들기
    *상품 변수로 생성. optList의 index 0번 으로 넣기
    * -------------------------------
    *상품에 prod_cd가 ==null이면 Insert하고 1 받기, pk받기(가장 큰수의 pK)
    *prod_cd != null 이면 update하고 1받기. pk 꺼내기
    *꺼낸 pk -> option에 setProd_cd() 해주기
    *prod_cd로 찾은 optList랑 equals면 update / 아니면 insert
    *옵션 update, insert 카운트 해주기. optUpdateCnt / optInsertCnt
    *알려줄것들 Map(prodInsertCnt, prodUpdateCnt,optUpdateCnt, optInsertCnt)으로 반환
    * */
    public Map<String, Integer> prodAndOptionRegist(ProductDto productDto, List<ProductOptionDto> productOptionDtos) throws SQLException {
        Integer prodInsertCnt = 0;
        Integer prodUpdateCnt = 0;
        Integer optInsertCnt = 0;
        Integer optUpdateCnt = 0;
        HashMap<String, Integer> map = new HashMap<>();

        /*혹시 낱개 옵션 만들어야 할까봐 미리 만드는 옵션 객체*/
        ProductOptionDto prodOptOne = null;
        int optListSize = productOptionDtos.size();
        System.out.println("optListSize (1) = " + optListSize);


        try {
            if(optListSize>0) {
                /*상품정보로 낱개 옵션 만들기*/
                prodOptOne = new ProductOptionDto(null, productDto.getDc_cd(), "낱개", "qty", 1,
                                productDto.getCnsmr_prc(), productDto.getSale_prc(), productDto.getIn_id(), productDto.getUp_id());
                /*옵션 List 0번째로 낱개 옵션 넣어주기*/
                productOptionDtos.add(0, prodOptOne);
                /*옵션 있을 때 일반 상품에서 소비자가, 판매가 없애기로 했음*/
                productDto.setCnsmr_prc(null);
                productDto.setSale_prc(null);
            }

            optListSize = productOptionDtos.size();
            System.out.println("optListSize (2) = " + optListSize);

            System.out.println("상품 Insert하기 전. PK_Prod_Cd: "+productDao.selectMaxProdCd());
            /*상품 PK prod_cd*/
            Long pkProdCd = productDto.getProd_cd();
            System.out.println("productDto.getProd_cd() = " + pkProdCd);

            /*상품 Insert나 Update하기*/
            if(pkProdCd==null){
                prodInsertCnt += productDao.insertProduct(productDto);
                pkProdCd = productDao.selectMaxProdCd() ;
                System.out.println("Insert 후 pkProdCd "+pkProdCd);
            } else {
                /*이미 있는 상품*/
                System.out.println("이미 있어서 UPDATE");
                prodUpdateCnt += productDao.updateProductInfo(productDto);
            }

            if(optListSize>0) {
                /*prod_cd로 찾아오는 optList*/
                boolean optEq = false;
                List<ProductOptionDto> compareOptList = productOptionDao.selectOptionProductsByProdCd(pkProdCd);
                /*반복문으로 옵션 없으면 Insert 있으면 Update*/
                for (ProductOptionDto newOption : productOptionDtos) { /*폼에서 받아온 옵션 리스트*/
                    // 옵션에 상품코드 넣어주기
                    newOption.setProd_cd(pkProdCd);
                    newOption.setTyp("qty");
                    newOption.setIn_id("ezmeal");
                    newOption.setUp_id("ezmeal");

                    // 기존에 동일한 옵션이 있는지 확인
                    boolean found = false;
                    for (ProductOptionDto existingOption : compareOptList) {
                        if (newOption.equals(existingOption)) {
                            found = true;
                            break;
                        }
                    }

                    // 기존에 동일한 옵션이 있다면 update, 그렇지 않으면 insert
                    if (found) {
                        optUpdateCnt += productOptionDao.optionUpdate(newOption);
                    } else {
                        optInsertCnt += productOptionDao.optionInsert(newOption);
                    }
                }
            }
            System.out.println("prodInsertCnt: "+prodInsertCnt);
            System.out.println("prodUpdateCnt: "+prodUpdateCnt);
            System.out.println("optInsertCnt: "+optInsertCnt);
            System.out.println("optUpdateCnt: "+optUpdateCnt);

            map.put("prodInsertCnt",prodInsertCnt);
            map.put("prodUpdateCnt",prodUpdateCnt);
            map.put("optInsertCnt",optInsertCnt);
            map.put("optUpdateCnt",optUpdateCnt);

            return map;

        } catch (SQLException e) {
            System.out.println("SQLException");
            return map;
        } catch (NullPointerException e) {
            System.out.println("NullPointerException");
            return map;
        } catch (Exception e) {
            e.printStackTrace();  // 예외 정보를 출력합니다.
            System.out.println("Exception: " + e.getMessage());  // 예외 메시지를 출력합니다.
            return map;
        }
    }

    /*헤더 판매 상품화면 (신상품,베스트,특가)을 위한 메서드  (이미지, 옵션, 리뷰 등 조건없이 다 가져옴)*/
    public HashMap getProductSetForHeader (String headerTyp) throws SQLException {

        try {

            /*headerTyp에 따라 상품 리스트 받아오기*/
            List<ProductDto> prodList;

            if ("new".equals(headerTyp)) {
                prodList = productDao.selectByNewProduct();
                System.out.println("new");
            } else if ("best".equals(headerTyp)) {
                prodList = productDao.selectByBestProduct();
                System.out.println("best");
            } else if ("bigdc".equals(headerTyp)) {
                prodList = productDao.selectByBigDcProduct();
                System.out.println("bigdc");
            } else {
                prodList = productDao.selectByNewProduct();
                System.out.println("else");
            }

            System.out.println("서비스 headerTyp: "+headerTyp);
            System.out.println("서비스 prodList.size(): "+ prodList);


            HashMap prepareListMap = getAllTypImgOptRivews();

            /*모든상품 '대표'이미지 리스트*/
            Map<Long,ProductImgDto> prodImgMap = (Map<Long,ProductImgDto>)prepareListMap.get("prodImgMap");
            /*모든상품의 옵션 리스트*/
            Map<Long,List<ProductOptionDto>> prodOptMap = (Map<Long,List<ProductOptionDto>>)prepareListMap.get("prodOptMap");
            /*모든상품  평점, 리뷰 숫자*/
            Map<Long,Double> reviewAvgMap = (Map<Long,Double>)prepareListMap.get("reviewAvgMap");
            Map<Long,Integer> reviewCntMap = (Map<Long,Integer>)prepareListMap.get("reviewCntMap");


            HashMap ProdListMap = new HashMap<>();
            ProdListMap.put("prodList",prodList);
            ProdListMap.put("prodImgMap",prodImgMap);
            ProdListMap.put("prodOptMap",prodOptMap);
            ProdListMap.put("reviewAvgMap",reviewAvgMap);
            ProdListMap.put("reviewCntMap",reviewCntMap);

            return ProdListMap;

        } catch (SQLException e) {
            logger.error("Error occurred in getProductSetForHeader", e);
            throw new RuntimeException("DB 조회 중 에러가 발생했습니다.", e);
        }

    }

    public HashMap getAllTypImgOptRivews() throws SQLException {
        HashMap prepareListMap = new HashMap<>();
        /*모든상품 '대표'이미지 리스트*/
        Map<Long,ProductImgDto> prodImgMap = productImgService.getAllRecentTypImgListConvertToMap();
        /*모든상품의 옵션 리스트*/
        Map<Long,List<ProductOptionDto>> prodOptMap =  prodCdListChangeToOptionMap("0");
        /*모든상품  평점, 리뷰 숫자*/
        Map<Long,Double> reviewAvgMap = productReviewDao.selectReviewAvgAllProduct();
        Map<Long,Integer> reviewCntMap = productReviewDao.selectReviewCntAllProduct();

        prepareListMap.put("prodImgMap",prodImgMap);
        prepareListMap.put("prodOptMap",prodOptMap);
        prepareListMap.put("reviewAvgMap",reviewAvgMap);
        prepareListMap.put("reviewCntMap",reviewCntMap);

        return prepareListMap;
    }


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
