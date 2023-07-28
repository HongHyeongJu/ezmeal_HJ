package com.teamProject.ezmeal.dao;

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
}
