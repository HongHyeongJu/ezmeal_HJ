/*TODO
 *   1. upid, uptime
 *   2. 송장번호, 묶음배송 선택 등 patch 하는 경우 상황에 맞게 master history 작성 필요 - 사실 지금해야할 지도 모른다.
 *   3. 검증 - 송장, 묶음, 배송중 순서대로 진행되야하는데 이에 맞는 수정
 *   4. table 분리로 조금더 깔끔하게 수행
 *   5. 묶음배송, 배송대기 상황 관련 로직도 - 묶음배송에 넣기
 * */
package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.domain.restAPIDomain.BundleData;
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
@RequestMapping("/admin/delivery")
public class AdminDeliveryController {
    private final AdminDeliveryService adminDeliveryService;

    /* ------------- 배송 준비중 page ------------- */
    @GetMapping("/prepare")
    public String prepareDelivery() {
        return "admin_delivery_prepare";
    }

    // 배송 준비중 동적 data 받아오기
    @PostMapping
    @ResponseBody
    // 배송 준비중 관리에서 기본 배송 관련 정보 보여줌 : 종합적으로 보여주는 값 - 주문상세, 배송 master, 결제 master, member
    // mapper에서 반환하는 value가 현재 string, num 등등 다양하기 때문에 Object로 value를 받아야 한다. 아니면 error 발생, type을 읽지 못하기 때문
    public List<Map<String, Object>> getPrepareDeliveryInfo(@RequestBody String periodString) {
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
    public String updateAdminInvoice(@RequestBody List<InvoiceDeliveryFeeInfo> invoiceDeliveryFeeInfoList) {
        System.out.println("--------------------------------------------------");
        System.out.println("adminDeliveryController-updatePrepare 시작");
        System.out.println("invoiceDeliveryFeeInfoList = " + invoiceDeliveryFeeInfoList);
        // todo invoiceDeliveryFeeInfoList이 객체를 이용해서 update
        // invoiceDeliveryFeeInfoList = [InvoiceDeliveryFeeInfo(ordId=202307142397, dlvarVend=ezmeal, invoiceNum=12, dlvarExpense=12)]
        adminDeliveryService.setAdminInvoiceNum(invoiceDeliveryFeeInfoList);
        return "success";
    }

    @PatchMapping("/bundle")
    @ResponseBody
    public String updateAdminBundle(@RequestBody BundleData bundleData) {
        System.out.println("--------------------------------------------------");
        System.out.println("adminDeliveryController-updateAdminBundle 시작");
        System.out.println("bundleData = " + bundleData);
        // todo bundleData 객체를 이용해서 update | BundleData(ord_id=[202307142397, 20230717941], dlvar_id=[66, 69])
        adminDeliveryService.setAdminBundleYn(bundleData);
        return "success";
    }

    // 배송중으로 상태 변경, 동일 ord_id를 갔지만 묶음 배송 처리가 되지 않은 상품에 한해서는 상태가 변경되지 않고 대신 해당 상품은 송장번호,배송비,공급사 정보 초기화
    @PatchMapping("/shipping")
    @ResponseBody
    public String updateAdminShipping(@RequestBody List<Long> orderIdList) {
        System.out.println("--------------------------------------------------");
        System.out.println("adminDeliveryController-updateAdminShipping 시작");
        System.out.println("orderIdList = " + orderIdList); // orderIdList = [202307142397]
        adminDeliveryService.setShippingStatusOnlyBundleY(orderIdList);
        return "success";
    }

    /* ------------- 배송 대기 관리 - TODO 3차 개발 때 수행 ------------- */
    @GetMapping("/wait")
    public String waitDelivery() {
        return "admin_delivery_wait";
    }

    /* ------------- 배송 중 관리 ------------- */
    @GetMapping("/ship")
    public String shipDelivery() {
        return "admin_delivery_ship";
    }

    // 배송중 동적 data 받아오기
    @PostMapping("/ship")
    @ResponseBody
    // 배송중 관리에서 기본 배송 관련 정보 보여줌 : 종합적으로 보여주는 값 - 주문상세, 배송 master, 결제 master, member
    // mapper에서 반환하는 value가 현재 string, num 등등 다양하기 때문에 Object로 value를 받아야 한다. 아니면 error 발생, type을 읽지 못하기 때문
    public List<Map<String, Object>> getShippingDeliveryInfo(@RequestBody String periodString) {
        System.out.println("---------------------------------------------------");
        System.out.println("AdminDeliveryController getShippingDeliveryInfo 시작");
        System.out.println("getPrepareDeliveryInfo @RequestBody = " + periodString);
        Map<String, Object> periodData = AdminDueModule.getPeriodData(periodString); // 기간을 받는 module 함수 {startTime: Object, endTime: Object};
        System.out.println("periodData = " + periodData);
        System.out.println(" db 반환 값 : " + adminDeliveryService.getShippingDeliveryInfo(periodData));
        return adminDeliveryService.getShippingDeliveryInfo(periodData);
    }

    // 배송완료 버튼 누를 시, 배송완료 상태로 변경
    @PatchMapping("/ship/complete")
    @ResponseBody
    public String updateAdminShipComplete(@RequestBody List<Long> dlvarIdList){
        System.out.println("------------------------------");
        System.out.println("AdminDeliveryController updateAdminShipComplete 시작");
        System.out.println("dlvarId = " + dlvarIdList); // dlvarId = [13]
        adminDeliveryService.setShipCompleteStatus(dlvarIdList);
        return "success";
    }

    // 배송대기 버튼 누를 시, 배송대기 상태로 변경
    @PatchMapping("/ship/wait")
    @ResponseBody
    public String updateAdminShipWait(@RequestBody List<Long> dlvarId){
        // todo. 3차때 개발
        return "success";
    }

    // 배송준비중 버튼 누를 시, 배송준비중 상태로 변경
    @PatchMapping("/ship/prepare")
    @ResponseBody
    public String updateAdminShipPrepare(@RequestBody List<Long> dlvarId){
        // todo. 3차때 개발
        return "success";
    }

    /* ------------- 배송 완료 조회 ------------- */
    @GetMapping("/complete")
    public String completeDelivery() {
        return "admin_delivery_complete";
    }

    // 배송완료 동적 data 받아오기
    @PostMapping("/complete")
    @ResponseBody
    public List<Map<String, Object>> getCompleteDeliveryInfo(@RequestBody String periodString) {
        System.out.println("---------------------------------------------------");
        System.out.println("AdminDeliveryController getCompleteDeliveryInfo 시작");
        System.out.println("getPrepareDeliveryInfo @RequestBody = " + periodString);
        Map<String, Object> periodData = AdminDueModule.getPeriodData(periodString); // 기간을 받는 module 함수 {startTime: Object, endTime: Object};
        System.out.println("periodData = " + periodData);
        System.out.println("배송완료 db 반환 값 : " + adminDeliveryService.getCompleteDeliveryInfo(periodData));
        return adminDeliveryService.getCompleteDeliveryInfo(periodData);
    }

    @PatchMapping("/complete/fixed")
    @ResponseBody
    public String updateAdminFixedCompleteDeliver(@RequestBody List<Long> dlvarIdList) {
        System.out.println("------------------------------");
        System.out.println("AdminDeliveryController updateAdminFixedCompleteDeliver 시작");
        System.out.println("dlvarId = " + dlvarIdList); // dlvarId = [13]
        // todo. 1. 상태 변경 2. 후기 작성란 3. 적립금 받음 4. 관련 이력에 값 등록 5.
        adminDeliveryService.setFixedCompleteStatus(dlvarIdList);
        return "success";
    }
}