package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.domain.restAPIDomain.InvoiceDeliveryFeeInfo;
import com.teamProject.ezmeal.module.AdminDueModule;
import com.teamProject.ezmeal.service.AdminDeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/delivery")
public class AdminDeliveryController {
    private final AdminDeliveryService adminDeliveryService;

    // 배송 준비중 page
    @GetMapping("/prepare")
    public String prepareDelivery(){
        return "admin_delivery_prepare";
    }

    // 동적 data 받아오기
    @PostMapping
    @ResponseBody
    // 배송 준비중 관리에서 기본 배송 관련 정보 보여줌 : 종합적으로 보여주는 값 - 주문상세, 배송 master, 결제 master, member
    // mapper에서 반환하는 value가 현재 string, num 등등 다양하기 때문에 Object로 value를 받아야 한다. 아니면 error 발생, type을 읽지 못하기 때문
    public List<Map<String , Object>> getPrepareDeliveryInfo(@RequestBody String periodString){
        System.out.println("--------------------------------------------------");
        System.out.println("AdminDeliveryController getPrepareDeliveryInfo 시작");
        System.out.println("getPrepareDeliveryInfo @RequestBody = " + periodString);
        Map<String, Object> periodData = AdminDueModule.getPeriodData(periodString); // 기간을 받는 module 함수 {startTime: Object, endTime: Object};
        System.out.println("periodData = " + periodData);
        System.out.println(" db 반환 값 : " + adminDeliveryService.getPrepareDeliveryInfo(periodData));
        return adminDeliveryService.getPrepareDeliveryInfo(periodData);
    }

    @PatchMapping("/invoice")
    @ResponseBody
    public String updatePrepare(@RequestBody List<InvoiceDeliveryFeeInfo> invoiceDeliveryFeeInfoList) {
        System.out.println("--------------------------------------------------");
        System.out.println("adminDeliveryController-updatePrepare 시작");
        System.out.println("invoiceDeliveryFeeInfoList = " + invoiceDeliveryFeeInfoList);
        // todo invoiceDeliveryFeeInfoList이 객체를 이용해서 update
        return "success";
    }

    // 배송 대기 관리
    @GetMapping("/wait")
    public String waitDelivery(){
        return "admin_delivery_wait";
    }

    // 배송 중 관리
    @GetMapping("/ship")
    public String shipDelivery(){
        return "admin_delivery_ship";
    }

    // 배송 완료 조회
    @GetMapping("/complete")
    public String completeDelivery(){
    return "admin_delivery_complete";
    }
}