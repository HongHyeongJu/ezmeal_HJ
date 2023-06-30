package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.domain.ProductDto;
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

        /*카테고리 코드로 검색해서 상품 리스트를 반환*/
        List productCateCdList = productService.selectCateCd(cate_cd);

        /*각 상품의 대표 이미지 Map 받기. Map<String(prod_cd), ProductImgDto> */
        Map<String, ProductImgDto> productCateCdImgMap = productImgService.selectCateCdImgMap(cate_cd);

        /*각 상품의 할인율 Map 받기 Map<String(prod_cd), Integer(dc_rate)> */
        Map<String, Integer> discountRateMap = productService.getDiscountRateAll(cate_cd);

        /*각 상품의 리뷰 개수 Map<String(prod_cd), Integer(리뷰카운트)>*/
        Map<String, Integer> reviewCountMap = productService.countProductReviewAll(cate_cd);

        /*각 상품의 리뷰 별점평균 Map<String(prod_cd), Double(별점 평균)>*/
        Map<String, Double> reviewStarAvgMap = productService.countProductReviewStarAvgAll(cate_cd);

        model.addAttribute("productCateCdList", productCateCdList);
        model.addAttribute("productCateCdImgMap", productCateCdImgMap);
        model.addAttribute("discountRateMap",discountRateMap);
        model.addAttribute("reviewStarAvgMap",reviewStarAvgMap);
        model.addAttribute("reviewCountMap",reviewCountMap);


        return "productcatelist";
    }


    @GetMapping("/productdetail")
    public String productDetailView(Model model, String prod_cd) throws SQLException {

        /*파라미터로 상품 카테고리코드 받을 예정(일단 하드코딩)*/
//        String test_prod_cd = "G00001";

        /*상품 코드로 검색해서 상품객체 1개를 반환*/
        ProductDto productDto = productService.searchProdCd(prod_cd);

        /*1개 상품에 대한 모든 이미지 맵으로 받기*/
        Map<String, String> typeAndUrlMap = productImgService.selectProdCdImgAlltoMap(prod_cd);

        /*1개 상품에 대한 현재 할인율*/
        Map<String, Integer> discountRateMap = productService.getDiscountRateOne(prod_cd);

        /*1개 상품에 대한 별점 평균*/
        Map<String, Double> reviewStarAvgMap = productService.countProductReviewStarAvgOne(prod_cd);

        /*1개 상품에 대한 리뷰카운트*/
        Map<String, Integer> reviewCountMap = productService.countProductReviewOne(prod_cd);

        model.addAttribute("productDto", productDto);
        model.addAttribute("productCateCdImgMap", typeAndUrlMap);
        model.addAttribute("discountRateMap", discountRateMap);
        model.addAttribute("reviewStarAvgMap", reviewStarAvgMap);
        model.addAttribute("reviewCountMap", reviewCountMap);



        return "productdetail";
    }



}