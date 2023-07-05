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

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        // TODO 회원 session 유무로 비회원, 회원 장바구니 접근 logic 수행
        if (memberId == null) {
            // TODO 일반 장바구니 임시 table 값 들고 오기 - return 필수
        }

        // 회원 장바구니 가져오기
        Long cartSeq = cartService.getCartSeq(memberId);
        // 품절 상품 업데이트
        int updatedCnt = cartProductService.checkSoldOut(cartSeq);

        // 일반 상품 수량
        int count = cartProductService.countProduct(cartSeq);

        // 일반 상품 : 냉장/냉동/상온 map으로 저장
        Map<String, List<CartProductDto>> productsMap = cartProductService.getProducts(cartSeq);

        // 기본 배송지
        // todo 선택 배송지 존재시, 선택배송지가 되도록 logic 작성 필요 - selectAddress
        DeliveryAddressDto defaultAddress = deliveryAddressService.getDefaultAddress(memberId);
        // product info : cart_prod_seq, prod_cd ,typ ,soldout_yn ,qty ,name ,cnsmr_prc ,sale_prc
        model.addAttribute("productsMap", productsMap);
        model.addAttribute("count", count);
        model.addAttribute("defaultAddress", defaultAddress);

        return "cart";
    }

    // 개별 상품 삭제 : JS fetch를 이용한 rest API 수행
    @PatchMapping("/delete")
    @ResponseBody
    public String patchSubscript(@SessionAttribute Long memberId, @RequestBody Long cartProdSeq, Model model) {
//        // 예외1. 회원 세션 만료  TODO 장바구니로 이동 필요
//        if (memberId == null) return "no_memberId";

        // 예외2. service에서 검증 돌림
        Long cartSeq = cartService.getCartSeq(memberId);
        int validationResult = cartProductService.validateCartProduct(cartSeq, cartProdSeq);

        // 예외3. 잘못된 상품으로 접근
        String failMessage = "wrong product";
        if (validationResult == 0) return failMessage;

        cartProductService.removeCartProduct(cartProdSeq);
        // TODO  2. 해당 jsp를 변수에 담이서 return한다. 3. js에서 html을 갈아엎는다.
        Map<String, List<CartProductDto>> productsMap = cartProductService.getProducts(cartSeq);
        return "cartProductModule";
    }
}
