package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.domain.joinDomain.OrderPaymentJoinDto;
import com.teamProject.ezmeal.service.OrderPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orderPayment")
public class OrderPaymentController {

    private final OrderPaymentService orderPaymentService;

    @GetMapping
    public String getOrderPaymentTemplate(@SessionAttribute Long memberId) {
        return "orderPayment";

    }

    @GetMapping("/dynamicData") // bean 중복으로 인해서 경로 지정 필요
    @ResponseBody
    public List<OrderPaymentJoinDto> getOrderPaymentData(@SessionAttribute Long memberId) {
        // 1. 해당 멤버의 주문 내역의 모든 정보가 나타난다 - 주문 시간, 이미지, 상품코드, 상품명, 주문번호, 결제 방법, 결제금액, 상태코드, 전체취소 - 배송 후부터는 전체 반품 , 구매 확정 이후는 아예 없애기
        List<OrderPaymentJoinDto> allOrderPaymentList = orderPaymentService.getAllOrderPaymentList(memberId);
        System.out.println("orderPayment/dynamicData start");
        System.out.println("allOrderPaymentList = " + allOrderPaymentList);
        return allOrderPaymentList;
        // 2. 주문 내역 상세보기의 링크는 {pathvariable}을 이용해야한다. /orderPayment/detail/{주문번호}

        // 3. 조회 설정은 1개월, 3개월 6개월 1년 을 우선적으로 수행 -> 기간설정같은 것이 정해져 있긴 하다. -> 그래서 ajax를 이용해서 다시 reload를 수행


    }
}

