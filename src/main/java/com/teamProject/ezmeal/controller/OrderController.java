package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.dao.*;
import com.teamProject.ezmeal.domain.*;
import com.teamProject.ezmeal.domain.restAPIDomain.OrderPaymentData;
import com.teamProject.ezmeal.domain.restAPIDomain.PaymentAPIData;
import com.teamProject.ezmeal.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private final CouponJoinService couponJoinService;
    private final OrderMasterService orderMasterService;
    private final OrderPaymentAddressService orderPaymentAddressService;

    private static String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

//    private static Long orderNumber = 0L;
    private static Long orderNumber = Math.round(Math.random()*10000);
//    private static Long paymentNumber = 0L;
    private static Long paymentNumber = Math.round(Math.random()*10000);

    @GetMapping
    public String getOrder(@SessionAttribute Long memberId, Model model) {
        Long cartSeq = cartService.getCartSeq(memberId);
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

        return "/order";
    }

    /**
     * @return : 회원 정보, 주문번호 pk까지 반환
     */
    @PostMapping("/paymentData")
    @ResponseBody
    public PaymentAPIData setOrder(@SessionAttribute Long memberId, @RequestBody List<Long> event) {
        long paymentPk = Long.parseLong(date + paymentNumber++); // 결제 master pk
        int salePrice = 0; // 적립금 제외한 판매가
        int point = event.get(0).intValue(); // 적립금
        int couponPrice = 0; // 쿠폰 할인 가격
        int finalPrice = 0; // 최종결제 가격

        Long cartSeq = cartService.getCartSeq(memberId);
        List<CartJoinProductDto> orderProductList = cartProductService.getOrderProduct(cartSeq); // 주문 상품 list

        for (CartJoinProductDto orderProduct : orderProductList) salePrice += orderProduct.getSale_prc(); // 판매가 합

        List<Integer> couponPriceList = couponJoinService.getCouponPrice(event.get(1)); // 쿠폰이 없을 시, 빈 list 반환
        couponPrice = getCouponPrice(salePrice, couponPrice, couponPriceList); // coupon 할인 가격
        finalPrice = salePrice - point - couponPrice; // 최종 결제 가격

        MemberDto memberInfo = memberDao.getMemberInfo(memberId);
        return new PaymentAPIData(paymentPk, finalPrice, memberInfo.getName(), memberInfo.getPhone(), memberInfo.getEmail());
    }

    @PostMapping
    @ResponseBody
    public String setPayment(@SessionAttribute Long memberId, @RequestBody OrderPaymentData orderPaymentData) {
        /* insert할 때 들어갈 변수들
         * orderPayementData : eventList, 상품요약, 결제pk, 결제 방법
         * 주문 master : 주문pk, 회원번호, 상태코드, 상품명요약, 적립예상금액
         * 결제 master : 결제pk, 주문pk, 회원번호, 사용자쿠폰id, 쿠폰발행cd, 결제유형, 상품유형, 결제일시, 결제수단, 상품주문총금액, 상품 총 할인금액, 쿠폰사용금액, 사용적립금, 할인적용금액, 실결제금액
         * 주문 상품 상세 : 상품
         * */
        // orderMaster
        System.out.println("orderPaymentData = " + orderPaymentData);
        long orderPk = Long.parseLong(date + orderNumber++); // 주문 master pk
        int salePrice = 0; // 적립금 제외한 판매가
        int point = orderPaymentData.getEventList().get(0).intValue(); // 적립금
        int couponPrice = 0; // 쿠폰 할인 가격
        int finalPrice = 0; // 최종결제 가격
        int expectPoint = 0; // 예정 적립금

        Long cartSeq = cartService.getCartSeq(memberId); // 개별회원 장바구니 seq
        List<CartJoinProductDto> orderProductList = cartProductService.getOrderProduct(cartSeq); // 주문 상품 list
        for (CartJoinProductDto orderProduct : orderProductList) salePrice += orderProduct.getSale_prc(); // 판매가 합

        List<Integer> couponPriceList = couponJoinService.getCouponPrice(orderPaymentData.getEventList().get(1)); // 쿠폰이 없을 시, 빈 list 받는다.
        couponPrice = getCouponPrice(salePrice, couponPrice, couponPriceList); // 조건을 거친 coupon 할인 가격

        finalPrice = salePrice - point - couponPrice; // 최종 결제 가격
        expectPoint = (finalPrice / 100) * (memberGradeBenefitDao.getPointRate(memberId)); // 적립 예정금액

        /* INSERT */
        // order master
        OrderMasterDto orderMasterDto = new OrderMasterDto(orderPk, memberId, "oc", expectPoint, orderPaymentData.getProdSummaryName()); // 주문 master insert
        orderPaymentAddressService.registerOrderMaster(orderMasterDto); // 주문 master insert

        // payment master


        return "success";
    }

    // 정액 정률 쿠폰에서 실제 적용되는 coupon 가격 나타내는 method
    private static int getCouponPrice(int salePrice, int couponPrice, List<Integer> couponPriceList) {
        if (couponPriceList.size() != 0) {
            Integer couponVal = couponPriceList.get(0); // 쿠폰 할인(정액, 정률)
            Integer couponMaxPrice = couponPriceList.get(1); // 쿠폰 최대값(정률용)

            int couponValPrice = couponVal > 100 ? couponVal : (salePrice / 100) * couponVal; // 정률 할인 가격으로 변경
            couponPrice = couponValPrice > couponMaxPrice ? couponMaxPrice : couponValPrice; // 쿠폰 할인 최대값 맞추기
        }
        return couponPrice;
    }
}