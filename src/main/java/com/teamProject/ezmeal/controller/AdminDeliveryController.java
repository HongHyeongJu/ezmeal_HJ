/*TODO
 *   1. upid, uptime
 *   2. 송장번호, 묶음배송 선택 등 patch 하는 경우 상황에 맞게 master history 작성 필요 - 사실 지금해야할 지도 모른다.
 *   3. 검증 - 송장, 묶음, 배송중 순서대로 진행되야하는데 이에 맞는 수정
 *   4. table 분리로 조금더 깔끔하게 수행
 *   5. 묶음배송, 배송대기 상황 관련 로직도 - 묶음배송에 넣기
 * */
package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.domain.AdminMemberDto;
import com.teamProject.ezmeal.domain.joinDomain.AdminOrderDeliveryDto;
import com.teamProject.ezmeal.domain.joinDomain.AdminOrderOrderDto;
import com.teamProject.ezmeal.domain.restAPIDomain.BundleData;
import com.teamProject.ezmeal.domain.restAPIDomain.InvoiceDeliveryFeeInfo;
import com.teamProject.ezmeal.module.AdminDueModule;
import com.teamProject.ezmeal.service.AdminDeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/delivery")
public class AdminDeliveryController {
    private final AdminDeliveryService adminDeliveryService;

    /* ------------- 배송 준비중 page ------------- */
    /* todo - 배송 준비중 logic
     *   이전 : 송장 번호 등록 -> 묶음 처리 -> 배송중
     *   변경 : 묶음 처리 -> 송장번호 등록 -> 배송중  -> ( 배송중 시, bundle check 안되는 경우 자동으로 배송 보류처리로 넘김 )
     *           * 송장번호 넣을 때는 걍 한꺼번에 넣기, history만 등록하지 말고, 너무 복잡해짐 -> 배송 상세 table 만들고 분리할 경우 고민할 것
     *   현재 수행 할 것 : 현재 로직 유지하되, 보류처리 관련 로직은 가만히 둘 것, 3차 때 대거 수정
     *       1. 송장 번호 등록 (all) : 현재는 ordId 나중에는 dlvarId로 처리
     *       2. 묶음 배송 처리
     *       3. 배송 중 상태 변경 - 묶음 처리 되지 않은 로지 reset 처리 (reset 처리에관한 이력 logic 작성 말것 - 3차때 대거 수정)
     * */
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

    // 배송 준비중 묶음 배송
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

    // 배송 준비중 송장번호 등록 - table 분리가 안된 현 상황에서는 all 송장번호 등록, 이후 배송중으로 변경시 제거
    /* todo
     *   1. stus a1 -> a2, h1
     *       1.1. update | od,dm : h1 -> h2
     *       1.2. insert | dh : h1 -> h2
     *                           * 취소, 반품, 묶음 배송 아닌 경우 == 모든 경우가 동일 할 때 주문상세 번호 : 1
     *       1.3.                * osh : 송장 관련 부분은 얘 역할이 아님
     *       1.4.                * om  : 송장 관련 stus 변경은 일단 안함. 배송중으로 역할 넘김
     *  2. 배송보류 : 배송보류 btn or 배송중일 때 묶음버튼 아닐경우
     * */
    @PatchMapping("/invoice")
    @ResponseBody
    public String updateAdminInvoice(@SessionAttribute AdminMemberDto loginAdminInfo, @RequestBody List<InvoiceDeliveryFeeInfo> invoiceDeliveryFeeInfoList) {
        System.out.println("--------------------------------------------------");
        System.out.println("adminDeliveryController-updatePrepare 시작");
        System.out.println("invoiceDeliveryFeeInfoList = " + invoiceDeliveryFeeInfoList); // invoiceDeliveryFeeInfoList = [InvoiceDeliveryFeeInfo(ordId=202307142397, dlvarVend=ezmeal, invoiceNum=12, dlvarExpense=12)]

        List<Long> orderIdList = invoiceDeliveryFeeInfoList.stream()
                .map(InvoiceDeliveryFeeInfo::getOrdId)
                .collect(Collectors.toList()); // ordId로 dlvarId list 받아오기
        System.out.println("ordIdList = " + orderIdList);

        List<Long> orderProdDetailIdListBndlY = adminDeliveryService.getAdminDlvarIdBndlY(orderIdList); // 묶음처리된 dlvarIdList 받기
        System.out.println("orderProdDetailIdListBndlY = " + orderProdDetailIdListBndlY);

        int i = adminDeliveryService.setAdminInvoiceNum(invoiceDeliveryFeeInfoList);// 송장번호, 공급사, 배송비 등록
        System.out.println("adminDeliveryService.setAdminInvoiceNum = " + i);

        AdminOrderOrderDto adminOrderOrderDetailData = new AdminOrderOrderDto("h2", loginAdminInfo.getEmp_acct_id(), orderProdDetailIdListBndlY, "송장번호 등록");
        int i1 = adminDeliveryService.setInvoiceStatus(adminOrderOrderDetailData);// od, dm에 송장번호 등록 status update 수행
        System.out.println("adminDeliveryService.setInvoiceStatus = " + i1);

        AdminOrderOrderDto adminOrderOrderData = new AdminOrderOrderDto(orderIdList, "송장번호 등록");
        int i2 = adminDeliveryService.setInvoiceDeliveryHistory(adminOrderOrderData);// delivery history 작성
        System.out.println("adminDeliveryService.setInvoiceDeliveryHistory = " + i2);

        return "success";
    }


    // 배송중으로 상태 변경, 동일 ord_id를 갔지만 묶음 배송 처리가 되지 않은 상품에 한해서는 상태가 변경되지 않고 대신 해당 상품은 송장번호,배송비,공급사 정보 초기화
    /* todo
     *   1. stus a1 -> a2, h1
     *       1.1. update | od,dm - bndl_y : h2 -> h4  & od,dm - bndl_n : h2 -> h3
     *       1.2. insert | dh : h3, h4 변경사항 모두 저장
     *       1.3.                * osh : 송장 관련 부분은 얘 역할이 아님
     *       1.4. om  | 배송중 or 부분 배송중으로 선택 필요
     *  2. 배송보류 : 배송보류 btn or 배송중일 때 묶음버튼 아닐경우
     * */
    @PatchMapping("/shipping")
    @ResponseBody
    public String updateAdminShipping(@SessionAttribute AdminMemberDto loginAdminInfo, @RequestBody List<Long> orderIdList) {
        System.out.println("--------------------------------------------------");
        System.out.println("adminDeliveryController-updateAdminShipping 시작");
        System.out.println("orderIdList = " + orderIdList); // orderIdList = [202307142397]
        // 1. od, dm - bndl_y : h2 -> h4 | od, dm - bndl_n : h2 -> h3
        // 2. od, dm - bndl_n 인것 , 배송비, 송장번호, 공급사 null로 초기화
        // 2. dh - od or dm에서 수행한 것 넣기
        // 3. om - 부분 배송중으로 변경
        // List<Long> orderProdDetailIdListBndlN = adminDeliveryService.getAdminDlvarIdBndlN(orderIdList); // ordId로 묶음처리 안된 dlvarId list 받아오기 |  송장번호 등록 상태, 배송중 처리 상태 때 사용
        //todo.  선택 안된 <> 이용 해서 orderDetailPk 가져와서 동일 작업 수행 | 선택 안된거랑 선택 된거 개수 비교해서 om status 작업 수행 - 배송중일 때 사용 - List<Integer> dlvarIdCountList = adminDeliveryService.getDlvarIdCountList(orderIdList); // 송장번호 등록하는 ordId의 모든 ordDetailId 개수
        AdminOrderOrderDto adminOrderOrderBundleY = new AdminOrderOrderDto("h4", loginAdminInfo.getEmp_acct_id(), orderIdList, "배송중");
        AdminOrderOrderDto adminOrderOrderBundleN = new AdminOrderOrderDto("h3", loginAdminInfo.getEmp_acct_id(), orderIdList, "배송보류");

        adminDeliveryService.setShippingStatusBundleY(adminOrderOrderBundleY); // 배송중 버튼 누를 시, bndl_y인 order Detail, delivery master status 변경
        adminDeliveryService.setShippingStatusBundleN(adminOrderOrderBundleN); // 배송중 버튼 누를 시, bndl_n인 order Detail, delivery master status 변경 및 배송비, 공급사, 송장번호 초기화

        adminDeliveryService.setShippingDeliveryHistoryBundleY(adminOrderOrderBundleY); // 배송중 버튼 누를 시, 배송 master 정보를 통해서 bndl_y인 delivery history insert
        adminDeliveryService.setShippingDeliveryHistoryBundleN(adminOrderOrderBundleN); // 배송중 버튼 누를 시, 배송 master 정보를 통해서 bndl_n인 delivery history insert

        adminDeliveryService.setOrderMasterShippingStatus(orderIdList); // 배송중 버튼 누를 시, order master 상태를 update : bndl_n == 'h3' 존재시, 부분배송중인 'h5' 없을 시 'h4' 상태변경

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
    public String updateAdminShipComplete(@RequestBody List<Long> dlvarIdList) {
        System.out.println("------------------------------");
        System.out.println("AdminDeliveryController updateAdminShipComplete 시작");
        System.out.println("dlvarId = " + dlvarIdList); // dlvarId = [13]
        adminDeliveryService.setShipCompleteStatus(dlvarIdList);
        return "success";
    }

    // 배송대기 버튼 누를 시, 배송대기 상태로 변경
    @PatchMapping("/ship/wait")
    @ResponseBody
    public String updateAdminShipWait(@RequestBody List<Long> dlvarId) {
        // todo. 3차때 개발
        return "success";
    }

    // 배송준비중 버튼 누를 시, 배송준비중 상태로 변경
    @PatchMapping("/ship/prepare")
    @ResponseBody
    public String updateAdminShipPrepare(@RequestBody List<Long> dlvarId) {
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