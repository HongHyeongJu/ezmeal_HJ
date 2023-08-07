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
        model.addAttribute("healthList",prodListMap.get("healthList"));
        model.addAttribute("emplList",prodListMap.get("emplList"));
        model.addAttribute("homeList",prodListMap.get("homeList"));
        model.addAttribute("eatList",prodListMap.get("eatList"));

        Map map4 = productService.getAllTypImgOptRivews();  /*모든상품 '대표'이미지 리스트, 옵션 리스트, 평점, 리뷰 숫자*/
        model.addAttribute("prodImgMap",map4.get("prodImgMap"));
        model.addAttribute("prodOptMap",map4.get("prodOptMap"));
        model.addAttribute("reviewAvgMap",map4.get("reviewAvgMap"));
        model.addAttribute("reviewCntMap",map4.get("reviewCntMap"));

        return "index";
    }




}
