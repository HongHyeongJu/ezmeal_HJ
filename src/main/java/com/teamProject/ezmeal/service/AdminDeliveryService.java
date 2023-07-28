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
    public List<Map<String ,Object>> getPrepareDeliveryInfo(Map<String, Object> periodData){
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
}
