package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.dao.*;
import com.teamProject.ezmeal.domain.*;
import com.teamProject.ezmeal.service.CartProductService;
import com.teamProject.ezmeal.service.CartService;
import com.teamProject.ezmeal.service.DeliveryAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final CartService cartService;
    private final CartProductService cartProductService;
    private final DeliveryAddressService deliveryAddressService;
    private final MemberDao memberDao;
    private final PointTransactionHistoryDao pointTransactionHistoryDao;
    private final MemberGradeBenefitDao memberGradeBenefitDao;
    private final CouponJoinDao couponJoinDao;


    @GetMapping
    public String getOrder(@SessionAttribute Long memberId, Model model) {
        Long cartSeq = cartService.getCartSeq(memberId);

        try {
            DeliveryAddressDto selectedAddress = deliveryAddressService.getOrderAddress(memberId); // 선택된 배송지, 없으면 기본배송지
            List<CartJoinProductDto> cartProductList = cartProductService.getOrderProduct(cartSeq); // 주문할 상품 목록
            // TODO 현욱님한테 해당 member 관련 logic 수정해도 되는지 물어보기
            MemberDto memberInfo = memberDao.getMemberInfo(memberId); // 회원정보

            // 결제 금액 계산
            Map<String, Integer> priceMap = new HashMap<>();
            int productPrice = 0; // 상품금액
            int orderPrice = 0; // 주문금액
            int productsDiscount = 0; // 상품할인금액

            for (CartJoinProductDto cartProduct : cartProductList) {
                productPrice += cartProduct.getCnsmr_prc(); // 실제 상품 금액
                orderPrice += cartProduct.getSale_prc(); // 실제 판매 금액
                productsDiscount += (cartProduct.getCnsmr_prc() - cartProduct.getSale_prc()); // 할인 금액
            }

            priceMap.put("productPrice", productPrice);
            priceMap.put("orderPrice", orderPrice);
            priceMap.put("productsDiscount", productsDiscount);

            // 적립금
            // 사용가능 적립금, 등급별 적립 예정금액
            Map<String, Integer> pointMap = new HashMap<>();
            int pointCanUse = pointTransactionHistoryDao.pointCanUse(memberId); // 사용가능 적립금
            int pointRate = (orderPrice / 100) * (memberGradeBenefitDao.getPointRate(memberId)); // 적립 예정금액
            pointMap.put("userPoint", pointCanUse);
            pointMap.put("pointRate", pointRate);

            // 쿠폰
            List<CouponJoinDto> couponList = couponJoinDao.couponList(memberId); // 쿠폰 list


            model.addAttribute("selectedAddress", selectedAddress); // 배송 정보
            model.addAttribute("cartProductList", cartProductList); // 상품 목록
            model.addAttribute("memberInfo", memberInfo); // 주문자 정보
            model.addAttribute("priceMap", priceMap); // 결제금액
            model.addAttribute("pointMap", pointMap); // 적립금
            model.addAttribute("couponList", couponList);  // 사용 가능 포인트

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // cookie 정보 이용해서 데이터 전달하기
        return "/order";
    }

    // js를 통해서 일반 url이면 window.location.href = "/order/general" 구독이면  window.location.href = "/order/subscript"
}