package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.ProductCategoryDao;
import com.teamProject.ezmeal.dao.ProductImgDao;
import com.teamProject.ezmeal.dao.ProductInventoryDao;
import com.teamProject.ezmeal.domain.ProductCategoryDto;
import com.teamProject.ezmeal.domain.ProductOptionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;


@Service
@RequiredArgsConstructor
public class ProductCategoryService {
    @Autowired
    ProductCategoryDao productCategoryDao;

    public Map<String, String> getAllProdCateCdAndNameList() throws SQLException {
        List<ProductCategoryDto> cateList = productCategoryDao.selectCateCdAndNameList();
        Map map = new HashMap();
        for(ProductCategoryDto dto : cateList){
            map.put(dto.getCate_cd(), dto.getName());
        }
        return map;
    }



}
