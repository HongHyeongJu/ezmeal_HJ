package com.teamProject.ezmeal.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamProject.ezmeal.domain.ProductDto;
import com.teamProject.ezmeal.domain.ProductImgDto;
import com.teamProject.ezmeal.domain.ProductOptionDto;
import com.teamProject.ezmeal.domain.ProductRegistrationRequest;
import com.teamProject.ezmeal.service.ProductImgService;
import com.teamProject.ezmeal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import org.json.JSONObject;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;


    /*상품 리스트 페이지. 카테고리 코드로 검색해서 상품 리스트를 반환*/
    @GetMapping("/catelist")
    public String productListByCateCd(Model model, String cate_cd, @RequestParam(required = false) String sortkeyword) throws SQLException {
        System.out.println("/catelist 컨트롤러 지나갑니다");
        /*상품목록 표현에 필요한 것 모두 받아오기*/
        model.addAttribute("cate_cd",cate_cd);

        if(sortkeyword == null) sortkeyword = "default";

        Map map = productService.getProductListByCateCd(cate_cd, sortkeyword);
        model.addAttribute("prodList",map.get("prodList"));
        model.addAttribute("cateName",map.get("cateName"));

        Map map4 = productService.getAllTypImgOptRivews(); /*모든상품 '대표'이미지 리스트, 옵션 리스트, 평점, 리뷰 숫자*/
        model.addAttribute("prodImgMap",map4.get("prodImgMap"));
        model.addAttribute("prodOptMap",map4.get("prodOptMap"));
        model.addAttribute("reviewAvgMap",map4.get("reviewAvgMap"));
        model.addAttribute("reviewCntMap",map4.get("reviewCntMap"));

        return "productcatelist";
    }

    /*상품 리스트 페이지. 카테고리 코드로 검색해서 상품 리스트를 반환*/
    @GetMapping("/headerlist")
    public String headerProductList(String headertyp, Model model) throws SQLException {
        System.out.println("/headerlist 컨트롤러 지나갑니다");
        /*상품목록 표현에 필요한 것 모두 받아오기*/
        /*카테고리랑 다른점 -> 상품 List는 서비스에서 headerTyp따라서 알아서 찾아줌 신상품/베스트(리뷰많음)/옵션06만*/
        /*그래서 전체 다 쓰이게 대표이미지 Map으로 다 가져오고 리뷰평균, 리뷰수도 cate_cd 검색없이 사용가능한 조건으로 전부다 가져와*/
        HashMap map = productService.getProductSetForHeader(headertyp);
        model.addAttribute("prodList",map.get("prodList"));
        model.addAttribute("headerTitle",map.get("headerTitle"));
        model.addAttribute("headerTyp",headertyp);

        Map map4 = productService.getAllTypImgOptRivews(); /*모든상품 '대표'이미지 리스트, 옵션 리스트, 평점, 리뷰 숫자*/
        model.addAttribute("prodImgMap",map4.get("prodImgMap"));
        model.addAttribute("prodOptMap",map4.get("prodOptMap"));
        model.addAttribute("reviewAvgMap",map4.get("reviewAvgMap"));
        model.addAttribute("reviewCntMap",map4.get("reviewCntMap"));

        return "productcatelist_header";
    }


    @ResponseBody
    @PostMapping("/restcatelist")
    public ResponseEntity<Map<String, Object>> productListByCateCd(@RequestBody String cate_cd, @RequestParam(required = false) String sortkeyword) throws SQLException {
        /*상품목록에 필요한 것 모두 받아오기*/
        Map map = productService.getProductListByCateCd(cate_cd, sortkeyword);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }




    /* 이부분 다 서비스에서 갖고오도록 1메서드로 가져오기 (서비스-트랜잭션)  맵으로 보내주면 꺼내서 모델에 넣어주기 */
    /*컨트롤러에서 중요한 것. 유효성 검사. 다른 페이지로 잘 넘겨주는 것(페이지 이동). 간단하게. 흐름보이게. 예외처리. */
    /*상품 상세 페이지. 나중에 모델에 상품코드관련 후기, 문의 전달해야함.*/
    @GetMapping("/detail")
    public String productDetailView(Model model, Long prod_cd, String cate_cd,  RedirectAttributes redirectAttributes) throws SQLException {

        /*상품 상세페이지에 필요한 것 모두 받아오기*/
        HashMap map = productService.getOneProductByProdCd(prod_cd);
        model.addAttribute("product",map.get("product"));
        model.addAttribute("optList",map.get("optList"));
        model.addAttribute("imgList",map.get("imgList"));
        model.addAttribute("reivewAvg",map.get("reivewAvg"));
        model.addAttribute("reviewCount",map.get("reviewCount"));
        model.addAttribute("reviewList",map.get("reviewList"));
//        /*상품문의 추가하기*/
//        model.addAttribute("상품문의",map.get("상품문의"));

        return "productdetail";

    }
    /*서비스에서 묶어 오기 중간에 에러났을 때 대처는 서비스에서  ->  묶어도 3개로 됨. 고민해보기 값 없을 떄*/
    /*DB가 꺼진다면,,? select도 안됨.  db연결 직접 끊기..ㅋㅋ  */




}