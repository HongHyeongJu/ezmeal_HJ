package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.domain.CartJoinProductDto;
import com.teamProject.ezmeal.domain.DeliveryAddressDto;
import com.teamProject.ezmeal.service.CartProductService;
import com.teamProject.ezmeal.service.CartService;
import com.teamProject.ezmeal.service.DeliveryAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.teamProject.ezmeal.service.CartProductService.TYPE_NAME;

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

        // TODO 새로고침 여러번 올 경우 품절상품 업데이트 멈추기 10분에 1번
        // 회원 장바구니 가져오기
        Long cartSeq = cartService.getCartSeq(memberId);
        // 품절 상품 업데이트
        int updatedCnt = cartProductService.checkSoldOut(cartSeq);
        // 기본 배송지
        // todo 선택 배송지 존재시, 선택배송지가 되도록 logic 작성 필요 - selectAddress
        DeliveryAddressDto defaultAddress = deliveryAddressService.getDefaultAddress(memberId);
        // 일반 상품 : 냉장/냉동/상온 map으로 저장
        Map<String, List<CartJoinProductDto>> productsMap = cartProductService.getProducts(cartSeq);
        int productCount = 0;

        String[] productName = {"productIce", "productCold", "productOutside"};
        // jsp에서 풀어서 작업하는 것보다 java에서 냉동/냉장/상온을 푸는 것이 더 빠르다.
        for (int i = 0; i < productsMap.size(); i++) {
            List<CartJoinProductDto> products = productsMap.get(TYPE_NAME[i]);
            productCount += products.size();
            model.addAttribute(productName[i], (products.size() !=0)? products: null);
        }

        model.addAttribute("count", productCount);
        model.addAttribute("defaultAddress", defaultAddress);

        return "cart";
    }

    // 개별 상품 삭제 : JS fetch를 이용한 rest API 수행
    @PatchMapping("/delete")
    @ResponseBody
    public String patchSubscript(@SessionAttribute Long memberId, @RequestBody Long cartProdSeq) {
//        // 예외1. 회원 세션 만료  TODO 장바구니로 이동 필요
        System.out.println("memberId = " + memberId);
        if (memberId == null) return "no_memberId";

        // 예외2. service에서 검증 돌림
        Long cartSeq = cartService.getCartSeq(memberId);
        int validationResult = cartProductService.validateCartProduct(cartSeq, cartProdSeq);

        // 예외3. 잘못된 상품으로 접근
        if (validationResult == 0) return "wrong product";

        cartProductService.removeCartProduct(cartProdSeq);
        // TODO  2. 해당 jsp를 변수에 담이서 return한다. 3. js에서 html을 갈아엎는다.
//        Map<String, List<CartJoinProductDto>> productsMap = cartProductService.getProducts(cartSeq);
        return "cartProductModule";
    }
}
