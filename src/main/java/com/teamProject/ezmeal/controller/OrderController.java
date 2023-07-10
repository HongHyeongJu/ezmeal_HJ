package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.dao.*;
import com.teamProject.ezmeal.domain.*;
import com.teamProject.ezmeal.service.CartProductService;
import com.teamProject.ezmeal.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final CartService cartService;
    private final CartProductService cartProductService;
    private final DeliveryAddressDao deliveryAddressDao;
    private final MemberDao memberDao;
    private final PointTransactionHistoryDao pointTransactionHistoryDao;
    private final MemberGradeBenefitDao memberGradeBenefitDao;
    private final CouponJoinDao couponJoinDao;


    @GetMapping
    public String getOrder(@SessionAttribute Long memberId, Model model) {
        Long cartSeq = cartService.getCartSeq(memberId);

        try {

            // TODO 배송지는 일반 구독 나눠서 우선권이 선택된 배송지, 선택된 배송지 column 없을 시 기본 배송지 -> 지금은 너무 할게 많으니깐 그냥 배송지
            //  deliveryAddressDao.choiseAddress(memberId)
            DeliveryAddressDto defaultAddress = deliveryAddressDao.selectDefaultAddress(memberId);

            List<CartJoinProductDto> cartProductList = cartProductService.getOrderProduct(cartSeq); // 주문할 상품 목록
            MemberDto memberInfo = memberDao.getMemberInfo(memberId);

            // 결제 금액 계산
            Map<String, Integer> priceMap = new HashMap<>();
            int productPrice = 0; // 상품금액
            int orderPrice = 0; // 주문금액
            int productsDiscount = 0; // 상품할인금액

            for (CartJoinProductDto cartProduct : cartProductList) {
                productPrice += cartProduct.getCnsmr_prc(); // 할인된 판매 가격
                orderPrice += cartProduct.getSale_prc(); // 실제 상품 가격
                productsDiscount += (cartProduct.getCnsmr_prc() - cartProduct.getSale_prc()); // 할인 금액
            }

            priceMap.put("productPrice", productPrice);
            priceMap.put("orderPrice", orderPrice);
            priceMap.put("productsDiscount", productsDiscount);

            // 적립금
            // 사용가능 적립금, 적립 예정금액
            Map<String, Integer> pointMap = new HashMap<>();
            int pointCanUse = pointTransactionHistoryDao.pointCanUse(memberId);
            int pointRate = (orderPrice / 100) * (memberGradeBenefitDao.getPointRate(memberId));

            pointMap.put("usePoint", pointCanUse);
            pointMap.put("pointRate", pointRate);

            // 쿠폰
//            Map<String, Object> couponMap = new HashMap<>();
            List<CouponJoinDto> coupons = couponJoinDao.couponList(memberId);
            // TODO 사용가능 쿠폰 logic 생성 후 Map 사용할지 결정
//            int couponCount = couponJoinDtos.size();
//            couponMap.put("coupon", couponJoinDtos);
//            couponMap.put("couponCount", couponCount);


            model.addAttribute("defaultAddress", defaultAddress);
            model.addAttribute("cartProductDtos", cartProductList);
            model.addAttribute("mbrInfo", memberInfo);
            model.addAttribute("priceMap", priceMap);
            model.addAttribute("pointMap", pointMap);
            model.addAttribute("counpons", coupons);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // cookie 정보 이용해서 데이터 전달하기
        return "/order";
    }

    // js를 통해서 일반 url이면 window.location.href = "/order/general" 구독이면  window.location.href = "/order/subscript"
}