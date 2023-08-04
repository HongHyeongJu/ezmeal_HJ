package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.dao.*;
import com.teamProject.ezmeal.domain.*;
import com.teamProject.ezmeal.domain.joinDomain.CartJoinProductDto;
import com.teamProject.ezmeal.domain.joinDomain.CouponJoinDto;
import com.teamProject.ezmeal.domain.restAPIDomain.InventoryData;
import com.teamProject.ezmeal.domain.restAPIDomain.OrderPaymentAddressData;
import com.teamProject.ezmeal.domain.restAPIDomain.PaymentAPIData;
import com.teamProject.ezmeal.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final CartService cartService;
    private final CartProductService cartProductService;

    private final DeliveryAddressService deliveryAddressService;

    private final MemberDao memberDao;
    private final MemberGradeBenefitDao memberGradeBenefitDao;

    private final PointTransactionHistoryDao pointTransactionHistoryDao;

    private final CouponJoinService couponJoinService;
    private final MemberCouponService memberCouponService;

    private final OrderPaymentAddressService orderPaymentAddressService; // transactional 거는 복합 service

    private final InventoryEventService inventoryEventService;

    private final OrderMasterService orderMasterService;
    private final OrderDetailService orderDetailService;

    //    private static Long orderNumber = 0L;
    private static Long orderNumber = Math.round(Math.random() * 10000);
    //    private static Long paymentNumber = 0L;
    private static Long paymentNumber = Math.round(Math.random() * 10000);

    private static final String status = "a1"; // 결제 완료 상태 코드

    @GetMapping
    public String getOrder(@SessionAttribute Long memberId, Model model) {
        System.out.println("---------- OrderController getMapping getOrder ----------------");
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US); // 가격에 , 표시해주는 것

        Long cartSeq = cartService.getCartSeq(memberId);
        System.out.println("cartSeq = " + cartSeq);
        DeliveryAddressDto selectedAddress = deliveryAddressService.getOrderAddress(memberId); // 선택된 배송지, 없으면 기본배송지
        System.out.println("selectedAddress = " + selectedAddress);
        List<CartJoinProductDto> cartProductList = cartProductService.getOrderProduct(cartSeq); // 주문할 상품 목록
        System.out.println("cartProductList = " + cartProductList);
        MemberDto memberInfo = memberDao.selectMemberInfo(memberId); // 회원정보
        System.out.println("memberInfo = " + memberInfo);

        // 결제 금액 계산
        Map<String, String> priceMap = new HashMap<>();
        int productPrice = 0; // 상품금액
        int orderPrice = 0; // 주문금액
        int productsDiscount = 0; // 상품할인금액

        for (CartJoinProductDto cartProduct : cartProductList) {
            productPrice += cartProduct.getCnsmr_prc() * cartProduct.getCp_qty(); // 실제 상품 금액
            orderPrice += cartProduct.getSale_prc() * cartProduct.getCp_qty(); // 실제 판매 금액
            productsDiscount += (cartProduct.getCnsmr_prc() - cartProduct.getSale_prc()) * cartProduct.getCp_qty(); // 할인 금액
        }

        priceMap.put("productPrice", numberFormat.format(productPrice));
        priceMap.put("orderPrice", numberFormat.format(orderPrice));
        priceMap.put("productsDiscount", numberFormat.format(productsDiscount));

        System.out.println("priceMap = " + priceMap);

        // 적립금
        // 사용가능 적립금, 등급별 적립 예정금액
        Map<String, Integer> pointMap = new HashMap<>();
        int pointCanUse = pointTransactionHistoryDao.pointCanUse(memberId); // 사용가능 적립금
        System.out.println("pointCanUse = " + pointCanUse);
        int pointRate = (orderPrice / 100) * (memberGradeBenefitDao.getPointRate(memberId)); // 적립 예정금액
        System.out.println("pointRate = " + pointRate);
        pointMap.put("userPoint", pointCanUse);
        pointMap.put("pointRate", pointRate);

        // 쿠폰
        int canUseCoupon=0;
        List<CouponJoinDto> couponList = couponJoinService.getCouponList(memberId); // 쿠폰 list
        System.out.println("couponList = " + couponList);
        // 사용 가능 쿠폰 logic 1. 쿠폰 자체 조건 (disabled)
        for (CouponJoinDto coupon: couponList) {
            if (coupon.getUse_base_prc() > orderPrice) {
                coupon.setCan_use(false);
                continue;
            } // 쿠폰 최소금액보다 작으면 사용 불가
            // todo. 마감기한, 사용가능 가한 2가지 정도 조건만 더 들어가면 거의 완벽할 듯
            coupon.setCan_use(true);
            canUseCoupon++;
        }
        System.out.println("couponList = " + couponList);

        model.addAttribute("selectedAddress", selectedAddress); // 배송 정보
        model.addAttribute("cartProductList", cartProductList); // 상품 목록
        model.addAttribute("memberInfo", memberInfo); // 주문자 정보
        model.addAttribute("priceMap", priceMap); // 결제금액
        model.addAttribute("pointMap", pointMap); // 적립금
        model.addAttribute("couponList", couponList);  // 쿠폰
        model.addAttribute("couponCnt", canUseCoupon); // 사용가능 쿠폰 개수

        return "/order";
    }


    @PostMapping
    @ResponseBody
    public String setPayment(@SessionAttribute Long memberId, @RequestBody OrderPaymentAddressData orderPaymentAddressData) {
        /* insert할 때 들어갈 변수들
         * orderPayementData : eventList, 상품요약, 결제pk, 결제 방법
         * 주문 master : 주문pk, 회원번호, 상태코드, 상품명요약, 적립예상금액
         * 결제 master : 결제pk, 주문pk, 회원번호, 사용자쿠폰id, 쿠폰발행cd, 결제유형, 상품유형, 결제일시, 결제수단, 상품주문총금액, 상품 총 할인금액, 쿠폰사용금액, 사용적립금, 할인적용금액, 실결제금액
         * 주문 상품 상세 : 상품
         * */

        /* 변수 */
        System.out.println("orderPaymentData = " + orderPaymentAddressData);
        long orderPk = Long.parseLong(getDate() + orderNumber++); // 주문 master pk todo ++은 제일 마지막에 수행 필요
        long paymentPk = orderPaymentAddressData.getPaymentPk(); // 결제 master pk
        int productPrice = 0; // 할인적용안된 상품금액
        int salePrice = 0; // 할인적용된 상품금액
        int productsDiscount = 0; // 상품 자체 할인 금액
        int point = orderPaymentAddressData.getEventList().get(0).intValue(); // 사용 적립금
        int couponPrice = 0; // 쿠폰 할인 가격
        int finalPrice = 0; // 최종결제 가격
        int expectPoint = 0; // 예정 적립금

        String couponIssueCd = null; // 발행쿠폰 pk

        Long memberCouponId = orderPaymentAddressData.getEventList().get(1); // 개별회원 쿠폰 번호
        Long cartSeq = cartService.getCartSeq(memberId); // 개별회원 장바구니 seq

        List<OrderDetailDto> orderDetailList = new ArrayList<>(); // 주문상세 정보 담는 통

        // 결제하는 가격 제시 및 주문 상세에 들어갈 전반적인 정보를 가지고 있다.
        // 주문상세에 필요한 정보 : prod_cd, opt_cd, cnsmr_prc, seler_prc, qty, tot_prc, dc_prc, setl_expct_prc
        List<CartJoinProductDto> orderProductList = cartProductService.getOrderProduct(cartSeq); // 주문 상품 list

        for (CartJoinProductDto orderProduct : orderProductList) {
            productPrice += orderProduct.getCnsmr_prc() * orderProduct.getCp_qty(); // 실제 상품 금액
            salePrice += orderProduct.getSale_prc() * orderProduct.getCp_qty(); // 실제 판매 금액
            productsDiscount += (orderProduct.getCnsmr_prc() - orderProduct.getSale_prc()) * orderProduct.getCp_qty(); // 할인 금액

            // 주문 상세 dto 객체 생성
            OrderDetailDto orderDetailDto = getOrderDetailDto(orderPk, orderProduct);
            orderDetailList.add(orderDetailDto); // 주문 상세 상품 객체 List 담은 통
        }

        List<Integer> couponPriceList = couponJoinService.getCouponPrice(memberCouponId); // 쿠폰이 없을 시, 빈 list 받는다.
        couponPrice = getCouponPrice(salePrice, couponPrice, couponPriceList); // 조건을 거친 coupon 할인 가격

        // 개인 쿠폰의 coupon_issue_cd 가져오기
        MemberCouponDto memberCoupon = memberCouponService.getMemberCoupon(memberCouponId); // 주의 : 없으면 null 반환
        if (memberCoupon != null) couponIssueCd = memberCoupon.getCoupn_issu_cd();

        finalPrice = salePrice - point - couponPrice; // 최종 결제 가격
        expectPoint = (finalPrice / 100) * (memberGradeBenefitDao.getPointRate(memberId)); // 적립 예정금액

        // 배송 관련 data 가지고 오기
        DeliveryAddressDto orderAddress = deliveryAddressService.getAddress(orderPaymentAddressData.getDeliveryPk());
        System.out.println("orderAddress = " + orderAddress);

        /* INSERT */
        // order master
        System.out.println("orderMaster start ");
        OrderMasterDto orderMasterDto = new OrderMasterDto(orderPk, memberId, status, expectPoint, orderPaymentAddressData.getProdSummaryName()); // 주문 master insert
        orderPaymentAddressService.registerOrderMaster(orderMasterDto); // 주문 master insert
        System.out.println("orderMaster finish ");

        // payment master
        System.out.println("PaymentMaster start ");
        PaymentMasterDto paymentMasterDto = new PaymentMasterDto(
                paymentPk, orderPk, memberId,
                memberCouponId, couponIssueCd,
                "결제완료", LocalDateTime.now(), orderPaymentAddressData.getPaymentMethod(),
                productPrice, productsDiscount, couponPrice, point, couponPrice + point, finalPrice);
        orderPaymentAddressService.registerPaymentMaster(paymentMasterDto);
        System.out.println("PaymentMaster finish ");

        // orderPayment
        System.out.println("orderPayment start ");
        Map<String, Long> orderPaymentMap = new HashMap<>();
        orderPaymentMap.put("orderId", orderPk);
        orderPaymentMap.put("payId", paymentPk);
        orderPaymentAddressService.registerOrderPayment(orderPaymentMap);
        System.out.println("orderPayment finish ");

        // orderDetail
        System.out.println("orderDetail start ");
        orderPaymentAddressService.registerOrderDetail(orderDetailList); // 여기서 주문상세 insert
        System.out.println("orderDetail finish ");

        // order status history
        System.out.println("order status history start ");
        orderPaymentAddressService.registerOrderStatusHistory(orderPk);
        System.out.println("order status history finish ");

        // delivery master
        System.out.println("delivery master start ");
        List<DeliveryMasterDto> deliveryMasterList = new ArrayList<>(); // 주문상세 정보 담는 통
        // 주문상세 pk list로 받아와서 배송 master로 넘기
        List<Long> orderDetailPkList = orderDetailService.getOrderDetailPk(orderPk); // delivery master에 넣는 orderDetailPk list
        for (Long orderDetailPk : orderDetailPkList) {
            DeliveryMasterDto deliveryMasterDto = new DeliveryMasterDto(
                    orderPk,
                    orderDetailPk,
                    orderAddress.getRcpr(),
                    orderAddress.getPhone(),
                    "(주)ezmeal 종로69 YMCA 5층 518호",
                    orderAddress.getDesti(),
                    orderAddress.getDesti_dtl(),
                    orderPaymentAddressData.getDeliveryPlace(),
                    orderPaymentAddressData.getDeliveryDetail() + orderPaymentAddressData.getDeliveryInput(),
                    "h1",
                    "ma",
                    orderPaymentAddressData.getDeliveryMsg(),
                    "중");
            deliveryMasterList.add(deliveryMasterDto);
        }

        orderPaymentAddressService.registerDeliveryMaster(deliveryMasterList); // 객체리스트를 넣어야함
        System.out.println("delivery master finish ");
        return "success";
    }

    @PatchMapping
    @ResponseBody
    public String updatePayment(@SessionAttribute Long memberId, @RequestBody OrderPaymentAddressData orderPaymentAddressData) {
        System.out.println("orderPaymentAddressData = " + orderPaymentAddressData); //[0, 0] == [ 적립금, 쿠폰 pk ]
        List<Long> eventDataList = orderPaymentAddressData.getEventList();
        Long paymentPk = orderPaymentAddressData.getPaymentPk();

        Long cartSeq = cartService.getCartSeq(memberId);
        List<CartJoinProductDto> orderProductList = cartProductService.getOrderProduct(cartSeq); // 주문 상품 list

        // 재고 update에 사용될 데이터 : list[객체(prodCd, qty), 객체(prodCd, qty), ...];
        List<InventoryData> inventoryDataList = new ArrayList<>();

        for (CartJoinProductDto orderProduct : orderProductList) {
            InventoryData data = new InventoryData();
            data.setProdCd(orderProduct.getProd_cd());
            data.setQty(orderProduct.getCp_qty() * orderProduct.getPo_qty());
            inventoryDataList.add(data);
        }
        System.out.println("inventoryDataList = " + inventoryDataList);

        System.out.println("start inventory quantity update");
        Integer integer = inventoryEventService.decreaseInventoryAfterPayment(inventoryDataList); // 상품재고 update
        System.out.println("finish inventory quantity update");

        // point 사용하는 경우 해당 로직 수행
        if (eventDataList.get(0) != 0L) {
            System.out.println("start select point info");
            PointTransactionCodeDto usePointInfo = inventoryEventService.getUsePointInfo("USEPOINT"); // 포인트 코드
            // 포인트 코드 : trjs_cd, name, pnt=0
            // 필요 data(순서대로) :  mbr_id, trjs_cd, name, trjs_pnt(- 붙이기), stus,
            System.out.println("finish select point info");

            System.out.println("start insert point history");
            int usePoint = -eventDataList.get(0).intValue();

            PointTransactionHistoryDto pointTransactionHistoryDto = new PointTransactionHistoryDto(memberId, usePointInfo.getPnt_trjs_cd(), usePointInfo.getName(), usePoint, "사용", paymentPk);
            int insert = inventoryEventService.setPointHistory(pointTransactionHistoryDto);

            System.out.println("finish insert point history");
        }
        if (eventDataList.get(1) != 0L) {
            System.out.println("start update coupon");
            Map<String, Long> updateCouponDataList = new HashMap<>();
            updateCouponDataList.put("payId", paymentPk);
            updateCouponDataList.put("mbrCoupnId", eventDataList.get(1));
            int updateCoupon = inventoryEventService.setUsedCoupon(updateCouponDataList);
            System.out.println("finish update coupon");

        }

        System.out.println("start update cartProd");
        inventoryEventService.setCartProductAfterOrder(cartSeq);
        System.out.println("finish update cartProd");

        return "success";
    }


    @PostMapping("/paymentData")
    @ResponseBody
    public PaymentAPIData setOrder(@SessionAttribute Long memberId, @RequestBody List<Long> event) {
        System.out.println("event = " + event);
        long paymentPk = Long.parseLong(getDate() + paymentNumber++); // 결제 master pk
        int salePrice = 0; // 적립금 제외한 판매가
        int point = event.get(0).intValue(); // 적립금
        int couponPrice = 0; // 쿠폰 할인 가격
        int finalPrice = 0; // 최종결제 가격

        Long cartSeq = cartService.getCartSeq(memberId);
        List<CartJoinProductDto> orderProductList = cartProductService.getOrderProduct(cartSeq); // 주문 상품 list

        for (CartJoinProductDto orderProduct : orderProductList)
            salePrice += orderProduct.getSale_prc() * orderProduct.getCp_qty(); // 판매가 합

        List<Integer> couponPriceList = couponJoinService.getCouponPrice(event.get(1)); // 쿠폰이 없을 시, 빈 list 반환
        couponPrice = getCouponPrice(salePrice, couponPrice, couponPriceList); // coupon 할인 가격
        finalPrice = salePrice - point - couponPrice; // 최종 결제 가격

        MemberDto memberInfo = memberDao.selectMemberInfo(memberId);
        return new PaymentAPIData(paymentPk, finalPrice, memberInfo.getName(), memberInfo.getPhone(), memberInfo.getEmail());
    }


    @GetMapping("/complete")
    public String orderComplete(@SessionAttribute Long memberId, Model model) {
        // 주문번호정도만 있으면 됨
        Long orderId = orderMasterService.getOrderId(memberId);
        model.addAttribute("orderId", orderId);
        return "orderComplete";
    }

    /* 메서드 추출 */

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

    // 날짜 메서드 : 20230711
    private static String getDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    // orderDetail 객체 생성
    private static OrderDetailDto getOrderDetailDto(long orderPk, CartJoinProductDto orderProduct) {
        return new OrderDetailDto(
                orderProduct.getProd_cd(), orderPk, orderProduct.getOpt_seq(),
                orderProduct.getName(),
                orderProduct.getCnsmr_prc(), orderProduct.getSale_prc(), orderProduct.getCp_qty(),
                orderProduct.getCnsmr_prc() * orderProduct.getCp_qty(),
                orderProduct.getCnsmr_prc() * orderProduct.getCp_qty() - orderProduct.getSale_prc() * orderProduct.getCp_qty(),
                orderProduct.getSale_prc() * orderProduct.getCp_qty(),
                status
        );
    }
}