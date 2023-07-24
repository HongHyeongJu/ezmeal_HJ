package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.service.AdminOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
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
        System.out.println("초기 json 값 periodString = " + periodString); // 오늘, 어제, 3일, 7일, 15일, 30일, 3개월, 6개월
        System.out.println("개행문자 확인 periodString = [" + periodString + "]"); // json 객체여서 ""도 string으로 같이 들어옴
        String periodStringFormat = periodString.replaceAll("\"", "");// 모든 따옴표를 빈 문자열로 대체
        System.out.println("개행문자 제거 periodStringFormat = " + periodStringFormat);

        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);

        String startTime = null;
        String endTime = today.toString();

        Map<String, Object> periodData = new HashMap<>();
        periodData.put("endTime", endTime);

        switch (periodStringFormat) {
            case "{isTrusted:true}": // 초기 값은 json event값이 들어와서 해당 객체를 처리
                System.out.println("periodStringFormat = " + periodStringFormat);
                break;
            case "오늘":
                startTime = today.toString();
                break;
            case "어제":
                startTime = yesterday.toString();
                endTime = yesterday.toString();
                break;
            case "3일":
                System.out.println("3일");
                startTime = today.minusDays(3).toString();
                break;
            case "7일":
                startTime = today.minusDays(7).toString();
                break;
            case "15일":
                startTime = today.minusDays(15).toString();
                break;
            case "30일":
                startTime = today.minusMonths(1).toString();
                break;
            case "3개월":
                startTime = today.minusMonths(3).toString();
                break;
            case "6개월":
                startTime = today.minusMonths(6).toString();
                break;
            default: // 기간 설정 방식으로 수행하는 경우
                String[] dateArray = periodStringFormat.split(" ");
                System.out.println("dateArray = " + Arrays.toString(dateArray));
                startTime = dateArray[0];
                endTime = dateArray[1];
        }
        periodData.put("startTime", startTime);
        periodData.put("endTime", endTime);

        System.out.println("periodData = " + periodData);

        return adminOrderService.getAdminBeforeManageInfo(periodData);
    }

    @PatchMapping("/before-management")
    @ResponseBody
    public String test(@RequestBody List<Long> orderIdList) {
        System.out.println("orderIdList = " + orderIdList);
        int i = adminOrderService.setOrderStatusAfterCheckAdminOrderBtn(orderIdList);
        return "success";
    }

}