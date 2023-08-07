package com.teamProject.ezmeal.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamProject.ezmeal.domain.ProductDto;
import com.teamProject.ezmeal.domain.ProductImgDto;
import com.teamProject.ezmeal.domain.ProductOptionDto;
import com.teamProject.ezmeal.domain.ProductRegistrationRequest;
import com.teamProject.ezmeal.service.AdminProductService;
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
import java.util.*;

@Controller
@RequestMapping("/admin/prod")
public class AdminProductController {


    @Autowired
    ProductService productService;

    @Autowired
    AdminProductService adminProductService;

    @Autowired
    ProductImgService productImgService;

    //파일 업로드할 각자의 img 폴더 주소 src/main/resources/config.properties 에 있음\
    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping("/write")
    public String adminProdWrite(Model model) throws SQLException {

        /*관리자용 상품 페이지(읽기)에 필요한 것 모두 받아오기*/
        HashMap map = productService.getListForProductRegist();

        /*모델에 담기*/
        model.addAttribute("dcList", map.get("dcList"));
        model.addAttribute("cateList", map.get("cateList"));
        model.addAttribute("custList", map.get("custList"));
        model.addAttribute("stusList", map.get("stusList"));
        model.addAttribute("mode","WRITE");
        return "admin_product_regist";
    }


    // [1] 상품 및 옵션 등록
    @PostMapping("/write")
    public ResponseEntity<?> registerProduct(@RequestBody ProductRegistrationRequest request) throws SQLException {
        // 이제 request 안에는 ProductDto 객체와 ProductOptionDto 객체 리스트 있음
        ProductDto productDto = request.getProductDto();
        List<ProductOptionDto> productOptionDtos = request.getProductOptionDto();

        Map<String,Integer> registResult = adminProductService.prodAndOptionRegist(productDto, productOptionDtos);

        // 처리가 성공적으로 끝나면, 응답을 클라이언트에 보냅니다.
        return ResponseEntity.ok(registResult);
    }

    // [2] 상품 이미지 등록
    @PostMapping("/img/write")
    @ResponseBody
    public ResponseEntity<?> uploadAjaxPost(MultipartFile[] uploadProdImg, Long prod_cd ) {
        try {
            int registImgResult = productImgService.uploadMultipleImages(uploadProdImg, prod_cd);
            Map map = new HashMap<>();
            map.put("registImgResult",(Integer) registImgResult );
            return ResponseEntity.ok(map);
        } catch (IOException | SQLException e) {
            return new ResponseEntity<>("이미지를 등록할 수 없습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    //관리자용 상품 정보 읽기
    @GetMapping("/read")
    public String adminProdRead(Model model, Long prod_cd) throws SQLException {
        /*관리자용 상품 페이지(읽기)에 필요한 것 모두 받아오기*/
        HashMap map = productService.getOneProductByProdCdForMng(prod_cd);

        /*모델에 담기*/
        model.addAttribute("product", map.get("product"));
        model.addAttribute("optList", map.get("optList"));
        model.addAttribute("imgList", map.get("imgList"));
        model.addAttribute("dcList", map.get("dcList"));
        model.addAttribute("cateList", map.get("cateList"));
        model.addAttribute("custList", map.get("custList"));
        model.addAttribute("stusList", map.get("stusList"));
        model.addAttribute("mode","READ");
        return "admin_product_regist";
    }

    //관리자 상품 목록 List
    @GetMapping("/list")
    public String adminProdList(Model model, Long prod_cd) throws SQLException {
        /*관리자용 상품 목록 출력에 필요한 것 모두 받아오기*/
        HashMap map = productService.getOneProductByProdCdForMng(prod_cd);

        /*모델에 담기*/
        model.addAttribute("optList", map.get("optList"));
        model.addAttribute("cateList", map.get("cateList"));
        model.addAttribute("custList", map.get("custList"));
        model.addAttribute("stusList", map.get("stusList"));

        /*관리자 전체상품 리스트 받아오기*/
        List<ProductDto> allProdList = productService.getAllProdListForMng();
        model.addAttribute("allProdList", allProdList);
//        System.out.println("allProdList.size()"+allProdList.size());
//        allProdList.forEach(a-> System.out.println(a.getName()));

        return "product_admin_list";
    }



    /*--------관리자 상품 페이지 GET만 먼저 만들어둠--------*/
    @GetMapping("/home")
    public String adminProdHome(Model model) throws SQLException {

        return "admin_prod_home";
    }


    @GetMapping("/display")
    public String adminProdDisplay(Model model) throws SQLException {

        return "admin_prod_display";
    }


    @GetMapping("/option")
    public String adminProdOption(Model model) throws SQLException {

        return "admin_prod_option";
    }

    @GetMapping("/inven")
    public String adminProdInventory(Model model) throws SQLException {

        return "admin_prod_inven";
    }


}







