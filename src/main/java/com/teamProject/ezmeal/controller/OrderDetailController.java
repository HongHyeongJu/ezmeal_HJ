package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.domain.OrderDetailDto;
import com.teamProject.ezmeal.domain.joinDomain.AdminOrderOrderDto;
import com.teamProject.ezmeal.service.AdminDeliveryService;
import com.teamProject.ezmeal.service.OrderDetailService;
import com.teamProject.ezmeal.service.OrderPaymentService;
import com.teamProject.ezmeal.service.OrderStatusHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/** 목표
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

    private final OrderDetailService orderDetailService;
    private final OrderPaymentService orderPaymentService;
    private final OrderStatusHistoryService orderStatusHistoryService;

    @GetMapping("/detail/{orderId}")
    public String getOrderDetail(@SessionAttribute Long memberId, @PathVariable Long orderId, Model model){
        List<OrderDetailDto> orderDetailProductList = orderDetailService.getOrderDetailProductList(orderId); // 상세상품 list
        System.out.println("orderDetailProductList = " + orderDetailProductList);
        Map<String, Object> outsideOrderDetailInfo = orderDetailService.getOutsideOrderDetailInfo(orderId); // 상품 외 상세 정보
        System.out.println("outsideOrderDetailInfo = " + outsideOrderDetailInfo);

        Map<String, Integer> countOrderDelivery = orderPaymentService.countOrderDeliveryNum(memberId);
        System.out.println("countOrderDelivery = " + countOrderDelivery);
        model.addAttribute("countOrderDelivery", countOrderDelivery);
        model.addAttribute("orderDetailProductList", orderDetailProductList);
        model.addAttribute("outsideOrderDetailInfo", outsideOrderDetailInfo);
        return "orderDetail";
    }

    @PatchMapping("/detail/fixed")
    @ResponseBody
    public String updateFixedOrder(@RequestBody Long orderDetailId ){
        System.out.println("orderDetailPk = " + orderDetailId);
        int updateNum = orderDetailService.setOrderFixed(orderDetailId); // 주문상세 page에서 구매확정으로 변경 시, od - stus update
        int insertNum = orderStatusHistoryService.setFixedComplete(orderDetailId); // 주문상세 page에서 구매확정시, 이력 남기기
        return "success";
    }
}