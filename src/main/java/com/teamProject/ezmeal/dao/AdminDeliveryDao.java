package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.restAPIDomain.BundleData;
import com.teamProject.ezmeal.domain.restAPIDomain.InvoiceDeliveryFeeInfo;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AdminDeliveryDao {
    private final SqlSession session;
    private static final String namespace = "deliveryMasterDao.";

    // 배송 준비중 관리에서 기본 배송 관련 정보 보여줌 : 종합적으로 보여주는 값 - 주문상세, 배송 master, 결제 master, member
    public List<Map<String, Object>> selectPrepareDeliveryInfo(Map<String, Object> periodData) {
        return session.selectList(namespace + "select_prepare_delivery_info_with_od_pm_m", periodData);
    }

    // admin 송장번호 등록시, 송장번호, 공급사, 배송비 등록
    public int updateAdminInvoiceNum(List<InvoiceDeliveryFeeInfo> invoiceDeliveryFeeInfoList) {
        return session.update(namespace + "update_admin_invoice_num", invoiceDeliveryFeeInfoList);
    }

    // 묶음 배송 update 쿼리, 단일일 경우와 다중일 경우 모두 사용 가능
    public int updateAdminBundleYN(BundleData bundleData) {
        return session.update(namespace + "update_bundle_yn", bundleData);
    }

    // 배송중으로 상태 변경, 동일 ord_id를 갔지만 묶음 배송 처리가 되지 않은 상품에 한해서는 상태가 변경되지 않고 대신 해당 상품은 송장번호,배송비,공급사 정보 초기화
    public int updateShippingStatusOnlyBundleY(List<Long> ordIdList){
        return session.update(namespace + "update_shipping_status_only_bundleY", ordIdList);
    }

    // 배송 중 관리에서 기본 배송 관련 정보 보여줌 : 종합적으로 보여주는 값 - 주문상세, 배송 master, 결제 master, member
    public List<Map<String, Object>> selectShippingDeliveryInfo(Map<String, Object> periodData) {
        return session.selectList(namespace + "select_shipping_delivery_info_with_od_pm_m", periodData);
    }
}
