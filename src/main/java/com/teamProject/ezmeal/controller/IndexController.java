package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.domain.ProductImgDto;
import com.teamProject.ezmeal.domain.ProductOptionDto;
import com.teamProject.ezmeal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("")
public class IndexController {
    @Autowired
    ProductService productService;

    /*-------- 메인 페이지 상품 추천---------index_display_product-*/
    @GetMapping("")
    public String productDetailView(Model model) throws SQLException {

        HashMap prodListMap = productService.getMainDisplayProductList();

        HashMap prepareListMap = productService.getAllTypImgOptRivews();

        /*모든상품 '대표'이미지 리스트*/
        Map<Long, ProductImgDto> prodImgMap = (Map<Long,ProductImgDto>)prepareListMap.get("prodImgMap");
        /*모든상품의 옵션 리스트*/
        Map<Long, List<ProductOptionDto>> prodOptMap = (Map<Long,List<ProductOptionDto>>)prepareListMap.get("prodOptMap");
        /*모든상품  평점, 리뷰 숫자*/
        Map<Long,Double> reviewAvgMap = (Map<Long,Double>)prepareListMap.get("reviewAvgMap");
        Map<Long,Integer> reviewCntMap = (Map<Long,Integer>)prepareListMap.get("reviewCntMap");


        model.addAttribute("healthList",prodListMap.get("healthList"));
        model.addAttribute("emplList",prodListMap.get("emplList"));
        model.addAttribute("homeList",prodListMap.get("homeList"));
        model.addAttribute("eatList",prodListMap.get("eatList"));
        model.addAttribute("prodImgMap",prodImgMap);
        model.addAttribute("prodOptMap",prodOptMap);
        model.addAttribute("reviewAvgMap",reviewAvgMap);
        model.addAttribute("reviewCntMap",reviewCntMap);
        System.out.println("prodImgMap : "+prodImgMap.size());
        System.out.println("prodOptMap : "+prodOptMap.size());
        System.out.println("reviewAvgMap : "+reviewAvgMap.size());
        System.out.println("reviewCntMap : "+reviewCntMap.size());

        return "index";
    }




}
