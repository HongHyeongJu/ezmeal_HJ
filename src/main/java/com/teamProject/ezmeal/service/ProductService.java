package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.ProductDao;
import com.teamProject.ezmeal.domain.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
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

    /* 상품 코드로 찾기(1개) */
    public ProductDto getProductByProdCd (Long prod_Cd) throws SQLException {
        try {
            return productDao.selectProductByProdCd(prod_Cd);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /* 상품 이름으로 검색(리스트) */
    public List searchProductByName (String name) throws SQLException {
        try {
            return productDao.selectByName(name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /* 분류코드로 상품 리스트받기 */
    public List getProductListByCateCd (String cate_cd) throws SQLException {
        try {
            return productDao.selectProductListByCateCd(cate_cd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*상품목록 페이지를 위한 값 넘겨주기.  상품 리스트, 대표이미지(이미지 쿼리가 알아서 해줄꺼임),
    할인율(할인코드&상품코드로 찾아서 ) */



    /* 상품 생성 (WRITE) */
    public int registerProduct (ProductDto productDto) throws SQLException {
        return productDao.insertProduct(productDto);
    }
    /*컨트롤러에서 값 확인*/


    /* 상품 수정 (UPDATE) */
//  public int modifyProduct (ProductDto productDto) throws
    /*상픔 등록 시 이미지도 (업데이트도)*/


    /* 상품 찐삭제 (DELETE) */
    public int removeProduct(Long prodCd) throws SQLException {
        return productDao.deleteForTDD(prodCd);
    }



/* DB에서 처리하도록! 시퀀스로 하고 문자로 구분 있어보이는 쿼리 ㅎㅎ  이러면 쿼리 2번.  모든 대화는 서류로
* 같은DB 안에서 처리하는 것이 매우매우 빠르다 */

    /*할인율 계산해주는 메서드 { (소비자가-판매가)/소비자가 }*100  (k,v) = (prod_cd, dvc_rate) 맵으로 반환 */
//    public Map<String, Integer> get?(String cate_cd) throws SQLException {
//        List<ProductDto> productList = selectCateCd(cate_cd);
//        Map<String, Integer> discountRateMap = productList.stream()
//                .collect(Collectors.toMap(ProductDto::getProd_cd, ProductDto -> (int) (((ProductDto.getCnsmr_prc() - ProductDto.getSale_prc()) * 100) / ProductDto.getCnsmr_prc())));
//        return discountRateMap;
//    }

    /*할인율 계산해주는 메서드 (1개 상품) { (소비자가-판매가)/소비자가 }*100  (k,v) = (prod_cd, dvc_rate) 맵으로 반환 */
//    public Map<String, Integer> getDiscountRateOne(String prod_Cd) throws SQLException {
//        ProductDto productDto = searchProdCd(prod_Cd);
//        Map<String, Integer> discountRateMap = new HashMap<String, Integer>();
//        Integer cnsmr_prc = productDto.getCnsmr_prc();
//        Integer sale_prc = productDto.getSale_prc();
//        discountRateMap.put(productDto.getProd_cd(),(int) ((cnsmr_prc-sale_prc)*100)/cnsmr_prc);
//        return discountRateMap;
//    }

    /*카테고리 상품에 대한 리뷰 숫자 반환 메서드 MAP(상품코드, 상품코드별리뷰숫자)*/
//    public Map<String, Integer> getReviewCount(String cate_cd) throws SQLException {
//        List<ProductDto> catecCdList = selectCateCd(cate_cd);
//        Map reviewCountMap = new HashMap();
//        for(int i=0 ; i<catecCdList.size() ; i++){
//            reviewCountMap.put(catecCdList.get(i).getProd_cd(),productDao.countProductReview(catecCdList.get(i).getProd_cd()));
//        }
//        return reviewCountMap;
//    }

    /*1개 상품에 대한 리뷰 숫자 반환 메서드 MAP(상품코드, 상품코드별리뷰숫자)*/
//    public Map<String, Integer> countProductReviewOne(String prod_cd) throws SQLException {
//        ProductDto productDto = productDao.selectProductByProdCd(prod_cd);
//        Map reviewCountMap = new HashMap();
//        reviewCountMap.put(productDto.getProd_cd() , productDao.countProductReview(productDto.getProd_cd()));
//        return reviewCountMap;
//    }

    /*카테고리 상품에 대한 리뷰 별점평균 반환 메서드 MAP(상품코드, 별점평균)*/
//    public Map<String, Double> reviewAverage(String cate_cd) throws SQLException {
//        List<ProductDto> catecCdList = selectCateCd(cate_cd);
//        Map reviewStarAvgMap = new HashMap();
//        for(int i=0 ; i<catecCdList.size() ; i++){
//            reviewStarAvgMap.put(catecCdList.get(i).getProd_cd(), Math.round(productDao.avgProductReviewStar(catecCdList.get(i).getProd_cd())*10)/10);
//        }
//        return reviewStarAvgMap;
//    }

    /*리뷰 mapper먼저 만들기 */

    /*1개의 상품에 대한 리뷰 별점평균 반환 메서드 MAP(상품코드, 별점평균)*/
//    public Map<String, Double> countProductReviewStarAvgOne(String prod_cd) throws SQLException {
//        ProductDto productDto = productDao.selectProdCd(prod_cd);
//        Map reviewStarAvgMap = new HashMap();
//        reviewStarAvgMap.put(productDto.getProd_cd() , Math.round(productDao.avgProductReviewStar(productDto.getProd_cd())*10.0)/10.0);
//        return reviewStarAvgMap;
//    }




//    /* 카테고리별 개수 세기 */
//    public int countCateCd(String CateCd) throws Exception {
//        Map<String, Integer> resultMap = productDao.countCateCd(CateCd);
//        int count = resultMap.get(CateCd);
//        return count;
//    }

    /* 상품 등록하기 */
    /* 상품 수정하기 */
    /* 유효일 조금 남은 것 찾기 */
    /* 특정 할인코드 검색하기 */
    /*  */
    /*  */
    /*  */
    /*  */
    /*  */
    /*  */
    /*  */
    /*  */
    /*  */
    /*  */



}