package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.domain.ProductImgDto;
import com.teamProject.ezmeal.service.ProductImgService;
import com.teamProject.ezmeal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductImgService productImgService;

    /*카테고리 코드로 검색해서 상품 리스트를 반환*/
    @GetMapping("/productcatelist")
    public String productListView(Model model, String cate_cd) throws SQLException {

        /*파라미터로 상품 카테고리코드 받을 예정(일단 하드코딩)*/
//        String testCateCd = "01";

        /*카테고리 코드로 검색해서 상품 리스트를 반환*/
        List productCateCdList = productService.selectCateCd(cate_cd);

        /*각 상품의 대표 이미지 Map 받기. Map<String(Prod_cd), ProductImgDto>*/
        Map<String, ProductImgDto> productCateCdImgMap = productImgService.selectCateCdImgMap(cate_cd);

        model.addAttribute("productCateCdList", productCateCdList);
        model.addAttribute("productCateCdImgMap", productCateCdImgMap);


        return "productcatelist";
    }




}