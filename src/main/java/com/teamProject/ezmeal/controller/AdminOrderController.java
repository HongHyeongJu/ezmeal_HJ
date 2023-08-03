package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.domain.AdminMemberDto;
import com.teamProject.ezmeal.domain.joinDomain.AdminOrderOrderDto;
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
        System.out.println("----------------------------------");
        System.out.println("adminOrderController-dynamicBeforeManagement 시작");
        System.out.println("periodString = " + periodString);
        Map<String, Object> periodData = AdminDueModule.getPeriodData(periodString); // 기간을 받는 module 함수 {startTime: Object, endTime: Object};
        System.out.println("periodData = " + periodData);
        return adminOrderService.getAdminBeforeManageInfo(periodData);
    }

    // 주문 발주 확인 - 상태 update
    /* todo
    *   1. stus a1 -> a2, h1
    *       1.1. update | om,od : a1 -> h1
    *       1.2. insert | osh : a1 -> a2
    *                           * 취소, 반품, 묶음 배송 아닌 경우 == 모든 경우가 동일 할 때 주문상세 번호 : 1
    *       1.3.                * dm  : a1 -> h1 | 이미 h1으로 생성 되어서 할 필요 X
    *                           * dsh : 아직까지 history 변경 필요 안함.
    * */
    @PatchMapping("/before-management")
    @ResponseBody
    public String test(@SessionAttribute AdminMemberDto loginAdminInfo, @RequestBody List<Long> orderIdList) {
        System.out.println("------------------ adminOrderController @PatchMapping(\"/before-management\") ----------------");
        System.out.println("employee = " + loginAdminInfo);
        System.out.println("orderIdList = " + orderIdList);
        // orderMsterMapper에 넣을 dto 생성
        AdminOrderOrderDto adminOrderOrderDtoUpdate = new AdminOrderOrderDto("h1", loginAdminInfo.getEmp_acct_id(), orderIdList, "발주 확인");
        AdminOrderOrderDto adminOrderOrderDtoInsert = new AdminOrderOrderDto("a2", loginAdminInfo.getEmp_acct_id(), orderIdList, "발주 확인");
        // 객체 {status : "", up_id : "", orderIdList: []}
        adminOrderService.setOrderStatusAfterCheckAdminOrderBtn(adminOrderOrderDtoUpdate); // order master, order detail stus update
        adminOrderService.insertOrderHistoryAfterCheckAdminOrder(adminOrderOrderDtoInsert); // order status history insert
        return "success";
    }

    // 전체 주문 조회
    @GetMapping("/list")
    public String getAdminOrderList(){
        return "admin_order_list";
    }
}