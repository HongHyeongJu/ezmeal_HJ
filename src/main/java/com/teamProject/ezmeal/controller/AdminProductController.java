package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.domain.ProductDto;
import com.teamProject.ezmeal.domain.ProductOptionDto;
import com.teamProject.ezmeal.domain.ProductRegistrationRequest;
import com.teamProject.ezmeal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminProductController {


    @Autowired
    ProductService productService;

    @GetMapping("/prod/write")
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


    @PostMapping("/prod/write")
    public ResponseEntity<?> registerProduct(@RequestBody ProductRegistrationRequest request) throws SQLException {
        // 이제 request 안에는 ProductDto 객체와 ProductOptionDto 객체 리스트 있음
        ProductDto productDto = request.getProductDto();
        List<ProductOptionDto> productOptionDtos = request.getProductOptionDto();

        for(ProductOptionDto optDto : productOptionDtos){
            System.out.println("optDto: "+optDto.toString());
        }

        Map<String,Integer> registResult = productService.prodAndOptionRegist(productDto, productOptionDtos);

        // 처리가 성공적으로 끝나면, 응답을 클라이언트에 보냅니다.
        return ResponseEntity.ok(registResult);
    }


    @GetMapping("/prod/read")
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







}
