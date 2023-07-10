package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.ProductImgDao;
import com.teamProject.ezmeal.domain.ProductImgDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductImgService {
    @Autowired
    ProductImgDao productImgDao;

    /*상품 코드 입력해서 모든 이미지 List로 반환하기 */
    public List<ProductImgDto> selectProdCdImg (String prodCd) throws SQLException {
        return productImgDao.selectProdCdImgAll(prodCd);
    }

    /* 상품코드로 이미지 찾는데 특정 타입 이미지가 필요할 때 */
    public ProductImgDto selectProdCdTypImg (String prodCd, String typ) throws SQLException {
        return productImgDao.selectProdCdTypImg(prodCd, typ);
    }


    /* 카테고리로 검색한 상품리스트의 대표 이미지 리스트 (와일드카드 사용) */
    public List<ProductImgDto> selectCateCdImg (String cate_cd) throws SQLException {
        String like_cate_cd = cate_cd+"%";
        return productImgDao.selectCateCdImgTyp(like_cate_cd);
    }


    /* 위의  List<ProductImgDto> -> 변환 -> Map<String(Prod_cd), ProductImgDto> */
    public Map<String, ProductImgDto> selectCateCdImgMap (String cate_cd) throws SQLException {
        List<ProductImgDto> selectCateCdImgList = selectCateCdImg(cate_cd);
        Map<String, ProductImgDto> selectCateCdImgMap
                = selectCateCdImgList.stream().collect(Collectors.toMap(ProductImgDto->ProductImgDto.getProd_cd(), ProductImgDto->ProductImgDto));
        return  selectCateCdImgMap;
    }


    /*한 상품에 대한 전체 이미지 받기 List*/
    public List<ProductImgDto> selectProdCdImgAll(String prod_cd) throws SQLException {
        List<ProductImgDto> selectProdCdImgList = productImgDao.selectProdCdImgAll(prod_cd);
        return selectProdCdImgList;
    }

    /*위에서 받은  list 타입별 map으로 변환하기 (대표,대표이미지)(메인,메인이미지),(상세, 상세이미지)*/
    public Map<String,String> selectProdCdImgAlltoMap(String prod_cd) throws SQLException {
        List<ProductImgDto> selectProdCdImgList = productImgDao.selectProdCdImgAll(prod_cd);
        Map<String,String> typeAndUrlMap =  selectProdCdImgList.stream().collect(Collectors.toMap(ProductImgDto->ProductImgDto.getTyp(),ProductImgDto->ProductImgDto.getUrl()));
        return typeAndUrlMap;
    }



}