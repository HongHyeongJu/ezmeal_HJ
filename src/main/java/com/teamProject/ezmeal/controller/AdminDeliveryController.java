package com.teamProject.ezmeal.controller;


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
    public List<Map<String , String>> getPrepareDeliveryInfo(@RequestBody String periodString){
        Map<String, Object> periodData = AdminDueModule.getPeriodData(periodString); // 기간을 받는 module 함수 {startTime: Object, endTime: Object};
        return adminDeliveryService.getPrepareDeliveryInfo(periodData);
    }

}
