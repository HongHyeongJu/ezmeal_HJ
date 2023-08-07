package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.domain.ProductDto;
import com.teamProject.ezmeal.domain.ProductImgDto;
import com.teamProject.ezmeal.service.ProductImgService;
import com.teamProject.ezmeal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductImgService productImgService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    /*  -------------------  테스트용 컨트롤러  -------------------  JOO  -------------------  */
    @PostMapping("/tttest")
    public ResponseEntity<String> addToCart(@RequestBody ProductDto productDto) {
        System.out.println(productDto.toString());
        System.out.println("장바구니에 추가되었습니다.");
        return ResponseEntity.ok("장바구니에 추가되었습니다.");
    }

    @PostMapping("/wwwish")
    public ResponseEntity<String> addToWishList(@RequestBody ProductDto productDto) {
        System.out.println(productDto.toString());
        System.out.println("위시리스트에 추가되었습니다.");
        return ResponseEntity.ok("위시리스트에 추가되었습니다.");
    }

    @GetMapping("/indextest")
    public String goIndexTest() {
        return "indexTest";
    }

    @GetMapping("/slidemain")
    public String goMainTest() {
        return "slideMain";
    }

    //    @GetMapping("/admin/regist")
//    public String goRegistAdmin(){
//        return "admin_basic";
//    }
    @GetMapping("/testupup")
    public String goUploadTest() {
        return "tttest";
    }

    @GetMapping("/testupuptwo")
    public String goUpload2Test() {
        return "tttest2";
    }

    //    @PostMapping(value = "/test/uploadajaxaction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @PostMapping("/test/uploadajaxaction")
    @ResponseBody
    public ResponseEntity<List<ProductImgDto>> uploadAjaxPost(MultipartFile[] uploadProdImg) throws IOException {

        //각자의 img 폴더 주소
        String uploadFolderPath = uploadDir;
        System.out.println("uploadFolder 주소 = " + uploadFolderPath);

        System.out.println("아니지?");


        return new ResponseEntity<>(HttpStatus.OK);
    }
}

    /* ------------------- 테스트용 컨트롤러 ------------------- WAN  ------------------- */






























    /*  -------------------  테스트용 컨트롤러  -------------------  BEAK  -------------------  */






















