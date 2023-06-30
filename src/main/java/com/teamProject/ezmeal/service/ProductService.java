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
    public ProductDto searchProdCd (String prod_Cd) throws SQLException {
        return productDao.selectProdCd(prod_Cd);
    }

    /* 상품 이름으로 검색(리스트) */
    public List searchName (String name) throws SQLException {
        String like_name = "%"+name+"%";
        return productDao.selectName(like_name);
    }

    /* 분류코드로 상품 리스트받기 */
    public List selectCateCd (String cate_cd) throws SQLException {
        String like_cate_cd = cate_cd+"%";
        return productDao.selectCateCd(like_cate_cd);
    }

    /*할인율 계산해주는 메서드 { (소비자가-판매가)/소비자가 }*100  (k,v) = (prod_cd, dvc_rate) 맵으로 반환 */
    public Map<String, Integer> getDiscountRateAll(String cate_cd) throws SQLException {
        List<ProductDto> productList = selectCateCd(cate_cd);
        Map<String, Integer> discountRateMap = productList.stream()
                .collect(Collectors.toMap(ProductDto::getProd_cd, ProductDto -> (int) (((ProductDto.getCnsmr_prc() - ProductDto.getSale_prc()) * 100) / ProductDto.getCnsmr_prc())));
        return discountRateMap;
    }

    /*할인율 계산해주는 메서드 (1개 상품) { (소비자가-판매가)/소비자가 }*100  (k,v) = (prod_cd, dvc_rate) 맵으로 반환 */
    public Map<String, Integer> getDiscountRateOne(String prod_Cd) throws SQLException {
        ProductDto productDto = searchProdCd(prod_Cd);
        Map<String, Integer> discountRateMap = new HashMap<String, Integer>();
        Integer cnsmr_prc = productDto.getCnsmr_prc();
        Integer sale_prc = productDto.getSale_prc();
        discountRateMap.put(productDto.getProd_cd(),(int) ((cnsmr_prc-sale_prc)*100)/cnsmr_prc);
        return discountRateMap;
    }

    /*카테고리 상품에 대한 리뷰 숫자 반환 메서드 MAP(상품코드, 상품코드별리뷰숫자)*/
    public Map<String, Integer> countProductReviewAll(String cate_cd) throws SQLException {
        List<ProductDto> catecCdList = selectCateCd(cate_cd);
        Map reviewCountMap = new HashMap();
        for(int i=0 ; i<catecCdList.size() ; i++){
            reviewCountMap.put(catecCdList.get(i).getProd_cd(),productDao.countProductReview(catecCdList.get(i).getProd_cd()));
        }
        return reviewCountMap;
    }

    /*1개 상품에 대한 리뷰 숫자 반환 메서드 MAP(상품코드, 상품코드별리뷰숫자)*/
    public Map<String, Integer> countProductReviewOne(String prod_cd) throws SQLException {
        ProductDto productDto = productDao.selectProdCd(prod_cd);
        Map reviewCountMap = new HashMap();
        reviewCountMap.put(productDto.getProd_cd() , productDao.countProductReview(productDto.getProd_cd()));
        return reviewCountMap;
    }

    /*카테고리 상품에 대한 리뷰 별점평균 반환 메서드 MAP(상품코드, 별점평균)*/
    public Map<String, Double> countProductReviewStarAvgAll(String cate_cd) throws SQLException {
        List<ProductDto> catecCdList = selectCateCd(cate_cd);
        Map reviewStarAvgMap = new HashMap();
        for(int i=0 ; i<catecCdList.size() ; i++){
            reviewStarAvgMap.put(catecCdList.get(i).getProd_cd(), Math.round(productDao.avgProductReviewStar(catecCdList.get(i).getProd_cd())*10)/10);
        }
        return reviewStarAvgMap;

    }

    /*1개의 상품에 대한 리뷰 별점평균 반환 메서드 MAP(상품코드, 별점평균)*/
    public Map<String, Double> countProductReviewStarAvgOne(String prod_cd) throws SQLException {
        ProductDto productDto = productDao.selectProdCd(prod_cd);
        Map reviewStarAvgMap = new HashMap();
        reviewStarAvgMap.put(productDto.getProd_cd() , Math.round(productDao.avgProductReviewStar(productDto.getProd_cd())*10.0)/10.0);
        return reviewStarAvgMap;
    }




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