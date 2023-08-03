package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.domain.joinDomain.OrderPaymentJoinDto;
import com.teamProject.ezmeal.service.OrderPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orderPayment")
public class OrderPaymentController {

    private final OrderPaymentService orderPaymentService;

    @GetMapping
    public String getOrderPaymentTemplate(@SessionAttribute Long memberId, Model model) {
        Map<String, String> startEndDate = orderPaymentService.getStartEndDate(memberId);
        System.out.println("OrderPaymentController - getOrderPaymentTemplate 진입" + startEndDate); // Mapper의 반환 type을 map으로 하면 column명이 map의 key가 된다.

        Map<String, Integer> countOrderDelivery = orderPaymentService.countOrderDeliveryNum(memberId);
        System.out.println("countOrderDelivery = " + countOrderDelivery);

        model.addAttribute("startEndDate", startEndDate);
        model.addAttribute("countOrderDelivery", countOrderDelivery);
        return "orderPayment";

    }

    @GetMapping("/initData") // bean 중복으로 인해서 경로 지정 필요
    @ResponseBody
    public List<OrderPaymentJoinDto> getOrderPaymentInitData(@SessionAttribute Long memberId) {
        // 1. 해당 멤버의 주문 내역의 모든 정보가 나타난다 - 주문 시간, 이미지, 상품코드, 상품명, 주문번호, 결제 방법, 결제금액, 상태코드, 전체취소 - 배송 후부터는 전체 반품 , 구매 확정 이후는 아예 없애기
        System.out.println("OrderPaymentController - getOrderPaymentInitData 진입");
        return orderPaymentService.getAllOrderPaymentList(memberId);

    }

    @PostMapping("/dynamicData")
    @ResponseBody
    public List<OrderPaymentJoinDto> getOrderPaymentDynamicData(@SessionAttribute Long memberId, @RequestBody List<String> periodList) {
        System.out.println("OrderPaymentController - getOrderPaymentDynamicData 진입" + periodList);
        // 3. 조회 설정은 1개월, 3개월 6개월 1년 을 우선적으로 수행 -> 기간설정같은 것이 정해져 있긴 하다. -> 그래서 ajax를 이용해서 다시 reload를 수행
        Map<String, Object> periodData = new HashMap<>();
        periodData.put("mbrId", memberId);
        periodData.put("startTime", periodList.get(0));
        periodData.put("endTime", periodList.get(1));
        return orderPaymentService.getPeriodOrderPaymentList(periodData);
    }

}

