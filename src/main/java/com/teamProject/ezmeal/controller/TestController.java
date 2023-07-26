package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.domain.ProductDto;
import com.teamProject.ezmeal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;
import java.util.HashMap;

@Controller
public class TestController {

    @Autowired
    ProductService productService;

    /*  -------------------  테스트용 컨트롤러  -------------------  JOO  -------------------  */
    @PostMapping("/tttest")
    public ResponseEntity<String> addToCart(@RequestBody ProductDto productDto){
        System.out.println(productDto.toString());
        System.out.println("장바구니에 추가되었습니다.");
        return ResponseEntity.ok("장바구니에 추가되었습니다.");
    }

    @PostMapping("/wwwish")
    public ResponseEntity<String> addToWishList(@RequestBody ProductDto productDto){
        System.out.println(productDto.toString());
        System.out.println("위시리스트에 추가되었습니다.");
        return ResponseEntity.ok("위시리스트에 추가되었습니다.");
    }

//    @GetMapping("/admin/prod/write")
//    public String adminProdWrite(Model model) throws SQLException {
//
//        /*관리자용 상품 페이지(읽기)에 필요한 것 모두 받아오기*/
//        HashMap map = productService.getListForProductRegist();
//
//        /*모델에 담기*/
//        model.addAttribute("dcList", map.get("dcList"));
//        model.addAttribute("cateList", map.get("cateList"));
//        model.addAttribute("custList", map.get("custList"));
//        model.addAttribute("stusList", map.get("stusList"));
//        model.addAttribute("mode","WRITE");
//        return "admin_basic";
//    }
//    @GetMapping("/admin/prod/read")
//    public String adminProdRead(Model model, Long prod_cd) throws SQLException {
//        /*관리자용 상품 페이지(읽기)에 필요한 것 모두 받아오기*/
//        HashMap map = productService.getOneProductByProdCdForMng(prod_cd);
//
//        /*모델에 담기*/
//        model.addAttribute("product", map.get("product"));
//        model.addAttribute("optList", map.get("optList"));
//        model.addAttribute("imgList", map.get("imgList"));
//        model.addAttribute("dcList", map.get("dcList"));
//        model.addAttribute("cateList", map.get("cateList"));
//        model.addAttribute("custList", map.get("custList"));
//        model.addAttribute("stusList", map.get("stusList"));
//        model.addAttribute("mode","READ");
//        return "admin_basic";
//    }

    @GetMapping("/indextest")
    public String goIndexTest(){
        return "indexTest";
    }

    @GetMapping("/slidemain")
    public String goMainTest(){
        return "slideMain";
    }

//    @GetMapping("/admin/regist")
//    public String goRegistAdmin(){
//        return "admin_basic";
//    }










    /* ------------------- 테스트용 컨트롤러 ------------------- WAN  ------------------- */






























    /*  -------------------  테스트용 컨트롤러  -------------------  BEAK  -------------------  */
























}
