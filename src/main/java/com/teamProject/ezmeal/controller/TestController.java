package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.domain.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TestController {

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

    @GetMapping("/admin/test")
    public String addToWishList(){
        return "admin_basic";
    }

    @GetMapping("/indextest")
    public String goIndexTest(){
        return "indexTest";
    }

    @GetMapping("/slidemain")
    public String goMainTest(){
        return "slideMain";
    }

    @GetMapping("/admin/regist")
    public String goRegistAdmin(){
        return "admin_basic";
    }










    /* ------------------- 테스트용 컨트롤러 ------------------- WAN  ------------------- */






























    /*  -------------------  테스트용 컨트롤러  -------------------  BEAK  -------------------  */
























}
