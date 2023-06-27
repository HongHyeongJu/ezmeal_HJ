package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.ProductDao;
import com.teamProject.ezmeal.domain.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

@Service
public class ProductService {

    @Autowired
    ProductDao productDao;

    /* 상품 코드로 찾기(1개) */
    public ProductDto searchProdCd (String prodCd) throws SQLException {
        return productDao.selectProdCd(prodCd);
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

    /* 카테고리별 개수 세기 */
    public int countCateCd(String CateCd) throws Exception {
        Map<String, Integer> resultMap = productDao.countCateCd();
        int count = resultMap.get(CateCd);
        return count;
    }

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