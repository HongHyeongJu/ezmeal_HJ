package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.module.AdminDueModule;
import com.teamProject.ezmeal.service.AdminOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/order")
public class AdminOrderController {
    private final AdminOrderService adminOrderService;

    // 주문 발송 페이지
    @GetMapping("/before-management")
    public String beforeManagement() {
        return "admin_order_before_management";
    }

    // 동적 data 받아오기
    @PostMapping("/dynamic-before-management")
    @ResponseBody
    public List<Map<String, Object>> dynamicBeforeManagement(@RequestBody String periodString) {
        Map<String, Object> periodData = AdminDueModule.getPeriodData(periodString); // 기간을 받는 module 함수 {startTime: Object, endTime: Object};
        return adminOrderService.getAdminBeforeManageInfo(periodData);
    }

    // 상태 update
    @PatchMapping("/before-management")
    @ResponseBody
    public String test(@RequestBody List<Long> orderIdList) {
        System.out.println("orderIdList = " + orderIdList);
        int i = adminOrderService.setOrderStatusAfterCheckAdminOrderBtn(orderIdList);
        return "success";
    }

    // 전체 주문 조회
    @GetMapping("/list")
    public String getAdminOrderList(){
        return "admin_order_list";
    }
}