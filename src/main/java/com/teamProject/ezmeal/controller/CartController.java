package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.dao.CartDao;
import com.teamProject.ezmeal.dao.CartProductDao;
import com.teamProject.ezmeal.dao.DeliveryAddressDao;
import com.teamProject.ezmeal.domain.CartProductDto;
import com.teamProject.ezmeal.domain.DeliveryAddressDto;
import com.teamProject.ezmeal.service.CartProductService;
import com.teamProject.ezmeal.service.CartService;
import com.teamProject.ezmeal.service.DeliveryAddressService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final CartProductService cartProductService;
    private final DeliveryAddressService deliveryAddressService;

    @GetMapping
    public String getGeneral(@SessionAttribute(required = false) Long memberId, Model model) {
        System.out.println("memberId = " + memberId);
        // TODO 회원 session 유무로 비회원, 회원 장바구니 접근 logic 수행
        if (memberId == null) {
            // TODO 일반 장바구니 임시 table 값 들고 오기 - return 필수
        }

        // 회원 장바구니 가져오기
        Long cartSeq = cartService.getCartSeq(memberId);
        System.out.println("cartSeq = " + cartSeq);
        // 품절 상품 업데이트
        int updatedCnt = cartProductService.checkSoldOut(cartSeq);

        // 일반 상품 수량
        int count = cartProductService.countProduct(cartSeq);

        // 일반 상품 : 냉장/냉동/상온 map으로 저장
        Map<String, List<CartProductDto>> ProductsMap = cartProductService.getProducts(cartSeq);

        // 기본 배송지
        // TODO 선택 배송지 존재시, 선택배송지가 되도록 logic 작성 필요 - selectAddress
        DeliveryAddressDto defaultAddress = deliveryAddressService.getDefaultAddress(memberId);
        model.addAttribute("count", count);
        // product info : cart_prod_seq, prod_cd ,typ ,soldout_yn ,qty ,name ,cnsmr_prc ,sale_prc
        model.addAttribute("ProductsMap", ProductsMap);
        System.out.println("ProductsMap = " + ProductsMap);
        model.addAttribute("defaultAddress", defaultAddress);

        return "cart";
    }

    @PatchMapping("/general")
    @ResponseBody
//    TODO cart_prod_seq로 변경 필요
    public Abc patchSubscript(@RequestBody String deleteProdCd, @SessionAttribute Long memberId) throws Exception {
        Map map = new HashMap();
        map.put("mbrId", memberId);
        map.put("prodCd", deleteProdCd);
        cartProductService.deleteCartProduct(map);
        return new Abc();
    }

    @Data
    static class Abc {
        String name = "taewan";
        int age = 12;
    }
}
