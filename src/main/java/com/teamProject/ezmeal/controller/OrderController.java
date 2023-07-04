package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.dao.*;
import com.teamProject.ezmeal.domain.CartProductDto;
import com.teamProject.ezmeal.domain.CouponJoinDto;
import com.teamProject.ezmeal.domain.DeliveryAddressDto;
import com.teamProject.ezmeal.domain.MemberDto;
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

    private final CartProductDao cartProductDao;
    private final DeliveryAddressDao deliveryAddressDao;
    private final MemberDao memberDao;
    private final PointTransactionHistoryDao pointTransactionHistoryDao;
    private final MemberGradeBenefitDao memberGradeBenefitDao;
    private final CouponJoinDao couponJoinDao;


    @GetMapping("/general")
    public String getGeneralOrder(@SessionAttribute Long memberId, @CookieValue String orderProduct, Model model) {
        try {

            // TODO 현재 선택 된 상품의 updateSoldOut을 확인 필요
//            int updateSoldOut = cartDao.updateSoldOut(memberId);
//            if (updateSoldOut > 0){
//                return "forward:/cart/general";
//            }

            // TODO 배송지는 일반 구독 나눠서 우선권이 선택된 배송지, 선택된 배송지 column 없을 시 기본 배송지 -> 지금은 너무 할게 많으니깐 그냥 배송지
            //  deliveryAddressDao.choiseAddress(memberId)
            DeliveryAddressDto defaultAddress = deliveryAddressDao.selectDefaultAddress(memberId);
            List<CartProductDto> cartProductDtos = cartProductDao.cartProducts(memberId, orderProduct); // 상품, 할인 정보 존재
            MemberDto memberInfo = memberDao.getMemberInfo(memberId);

            // 결제 금액 계산
            Map<String, Integer> priceMap = new HashMap<>();
            int productPrice = 0; // 상품금액
            int orderPrice = 0; // 주문금액
            int productsDiscount = 0; // 상품할인금액
//            int
            for (int i = 0; i < cartProductDtos.size(); i++) {
                CartProductDto cartProductDto = cartProductDtos.get(i);
                productPrice += cartProductDto.getCnsmr_prc();
                orderPrice += cartProductDto.getSale_prc();
                productsDiscount += (cartProductDto.getCnsmr_prc() - cartProductDto.getSale_prc());
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
            model.addAttribute("cartProductDtos", cartProductDtos);
            model.addAttribute("mbrInfo", memberInfo);
            model.addAttribute("priceMap", priceMap);
            model.addAttribute("pointMap", pointMap);
//            model.addAttribute("couponMap", couponMap);
            model.addAttribute("counpons", coupons);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // cookie 정보 이용해서 데이터 전달하기
        return "order";
    }

    // js를 통해서 일반 url이면 window.location.href = "/order/general" 구독이면  window.location.href = "/order/subscript"
}