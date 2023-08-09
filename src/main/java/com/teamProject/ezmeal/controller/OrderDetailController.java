package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.domain.OrderDetailDto;
import com.teamProject.ezmeal.domain.joinDomain.AdminOrderOrderDto;
import com.teamProject.ezmeal.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 목표
 * 1. pathvariable을 이용한 상품 상세 정보 가지고 오기
 * 1.1. 주문상세 table : 상품코드, 상품명, 가격 (판매가, 소비자가), 수량, 상태 코드, 배송조회 -> 관리자에서 만들어져야 확인 가능
 * 1.2. 결제 master table : 총 상품금액, 총 상품할인금액, 결제 금액, 결제 방법
 * 1.3. 주문 요약 정보 : 주문번호-pathvariable , member명, 결제 일시
 * 1.4. 수령자 정보
 * -> 주문 상세, 결제 master, 배송 master의 각각의 정보를 받는다.
 * -> 객체를 1개 생성을 해서 돌리는 것이 나을까? 상품은 반복되는 경우가 많기 때문에 따로 model에 담는게 나을 듯하다. 총 2개의 객체를 modelAttribute로 넘긴다.
 * 2. 주문 상세 title 만들어주기, margin 맞춰주기, 결제 정보에 쿠폰, 적립금 할인 넣기, 배송비 및 전체 환불완료 금액은 전체 반품, 전체 환불 할 때만 적용 해당 column에서 전체 환불 금액은 결제 master의 실 금액과 동일
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderDetailController {

    private final OrderMasterService orderMasterService;
    private final OrderDetailService orderDetailService;
    private final OrderPaymentService orderPaymentService;
    private final OrderStatusHistoryService orderStatusHistoryService;
    private final DeliveryMasterService deliveryMasterService;

    @GetMapping("/detail/{orderId}")
    public String getOrderDetail(@SessionAttribute Long memberId, @PathVariable Long orderId, Model model) {
        List<OrderDetailDto> orderDetailProductList = orderDetailService.getOrderDetailProductList(orderId); // 상세상품 list
        System.out.println("orderDetailProductList = " + orderDetailProductList);
        Map<String, Object> outsideOrderDetailInfo = orderDetailService.getOutsideOrderDetailInfo(orderId); // 상품 외 상세 정보
        System.out.println("outsideOrderDetailInfo = " + outsideOrderDetailInfo);

        // 1. 배송 상세 정보 가져 오기
        int result = orderMasterService.showDeliveryInfo(orderId);

        if (result == 1) {
            // 2. 배송 cnt와 dlvr_id 가져오기
            // 배송 상세에 관한 정보는 다 여기로 들어감
            Map<String, Integer> deliveryDataCount = new HashMap<>();
            int totalDeliveryCnt = 0;
            int normalDeliveryCnt = 0;
            int waitDeliveryCnt = 0;


            List<Map> dlvarIdDataList = deliveryMasterService.getDlvarIdDataList(orderId);
            System.out.println("dlvarIdDataList = " + dlvarIdDataList);

            // 2. data 수량 세기
            for (Map dlvarData : dlvarIdDataList) {
                int deliveryCount = Integer.parseInt(dlvarData.get("count").toString());
                String stus = dlvarData.get("stus").toString();
                totalDeliveryCnt += deliveryCount;
                if (stus.equals("h3")) waitDeliveryCnt += deliveryCount;
                else normalDeliveryCnt += deliveryCount;
            }

            deliveryDataCount.put("totalDeliveryCnt", totalDeliveryCnt);
            deliveryDataCount.put("normalDeliveryCnt", normalDeliveryCnt);
            deliveryDataCount.put("waitDeliveryCnt", waitDeliveryCnt);

            // 3. 올바른 것들 중에서 dlvarId 1개 가져와서 delivery history 가지고 오기
            String dlvarIdString = null;
            for (Map<String, Object> dlvarData : dlvarIdDataList) {
                Object stusValue = dlvarData.get("stus");
                if (stusValue != null && !stusValue.equals("h3")) {
                    dlvarIdString = dlvarData.get("dlvar_id").toString();
                    break; // Exit the loop once we find the desired object
                }
            }
            if (dlvarIdString != null) {
                System.out.println("Found dlvar_id: " + dlvarIdString);
            } else {
                System.out.println("No matching object found.");
            }

            System.out.println("dlvarIdString = " + dlvarIdString);
            String[] numbers = dlvarIdString.split(",");
            System.out.println("numbers = " + numbers);
            long dlvarId = Long.parseLong(numbers[0]);
            System.out.println("dlvarId = " + dlvarId);
            List<Map> deliveryHistoryList = deliveryMasterService.getDlvarHist(dlvarId);

            // 중간 확인
            System.out.println("중간 확인");
            System.out.println("deliveryHistoryList = " + deliveryHistoryList);
            System.out.println("deliveryDataCount = " + deliveryDataCount);

            // 최종 모델에 담기
            model.addAttribute("deliveryHistoryList", deliveryHistoryList);
            model.addAttribute("deliveryDataCount",deliveryDataCount);
        }

        Map<String, Integer> countOrderDelivery = orderPaymentService.countOrderDeliveryNum(memberId);
        System.out.println("countOrderDelivery = " + countOrderDelivery);
        model.addAttribute("countOrderDelivery", countOrderDelivery); // 배송 수량 - 총 n개 상품 중 정상배송 a건 보류 b 건
        model.addAttribute("orderDetailProductList", orderDetailProductList);
        model.addAttribute("outsideOrderDetailInfo", outsideOrderDetailInfo);

        model.addAttribute("result", result); // 배송 현황 정보 if문 용

        return "orderDetail";
    }

    @PatchMapping("/detail/fixed")
    @ResponseBody
    public String updateFixedOrder(@RequestBody Long orderDetailId) {
        System.out.println("orderDetailPk = " + orderDetailId);
        int updateNum = orderDetailService.setOrderFixed(orderDetailId); // 주문상세 page에서 구매확정으로 변경 시, od - stus update
        int insertNum = orderStatusHistoryService.setFixedComplete(orderDetailId); // 주문상세 page에서 구매확정시, 이력 남기기
        return "success";
    }
}