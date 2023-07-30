package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.AdminDeliveryDao;
import com.teamProject.ezmeal.domain.restAPIDomain.BundleData;
import com.teamProject.ezmeal.domain.restAPIDomain.InvoiceDeliveryFeeInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminDeliveryService {
    private final AdminDeliveryDao adminDeliveryDao;

    // 배송 준비중 관리에서 기본 배송 관련 정보 보여줌 : 종합적으로 보여주는 값 - 주문상세, 배송 master, 결제 master, member
    public List<Map<String, Object>> getPrepareDeliveryInfo(Map<String, Object> periodData) {
        return adminDeliveryDao.selectPrepareDeliveryInfo(periodData);
    }

    // admin 송장번호 등록시, 송장번호, 공급사, 배송비 등록
    public int setAdminInvoiceNum(List<InvoiceDeliveryFeeInfo> invoiceDeliveryFeeInfoList) {
        return adminDeliveryDao.updateAdminInvoiceNum(invoiceDeliveryFeeInfoList);
    }

    // 묶음 배송 update 쿼리, 단일일 경우와 다중일 경우 모두 사용 가능
    public int setAdminBundleYn(BundleData bundleData) {
        return adminDeliveryDao.updateAdminBundleYN(bundleData);
    }

    // 배송중으로 상태 변경, 동일 ord_id를 갔지만 묶음 배송 처리가 되지 않은 상품에 한해서는 상태가 변경되지 않고 대신 해당 상품은 송장번호,배송비,공급사 정보 초기화
    public int setShippingStatusOnlyBundleY(List<Long> orderIdList) {
        return adminDeliveryDao.updateShippingStatusOnlyBundleY(orderIdList);
    }

    // 배송 중 관리에서 기본 배송 관련 정보 보여줌 : 종합적으로 보여주는 값 - 주문상세, 배송 master, 결제 master, member
    public List<Map<String, Object>> getShippingDeliveryInfo(Map<String, Object> periodData) {
        return adminDeliveryDao.selectShippingDeliveryInfo(periodData);
    }

    // 배송중 page에서 배송완료 일 경우, stus, up-dtm update
    public int setShipCompleteStatus(List<Long> dlvarIdList){
        return adminDeliveryDao.updateShipCompleteStatus(dlvarIdList);
    }

    // 배송 완료에서 기본 배송 관련 정보 보여줌 : 종합적으로 보여주는 값 - 주문상세, 배송 master, 결제 master, member
    public List<Map<String, Object>> getCompleteDeliveryInfo(Map<String, Object> periodData) {
        return adminDeliveryDao.selectCompleteDeliveryInfo(periodData);
    }

    public int setFixedCompleteStatus(List<Long> dlvarIdList){
       return adminDeliveryDao.updateFixedCompleteStatus(dlvarIdList);
    }
}
