package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.joinDomain.AdminOrderOrderDto;
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

    // 묶음 배송 update 쿼리, 단일일 경우와 다중일 경우 모두 사용 가능
    public int updateAdminBundleYN(BundleData bundleData) {
        return session.update(namespace + "update_bundle_yn", bundleData);
    }

    //   ordId로 dlvarId list 받아오기 송장번호 등록 상태, 배송중 처리 상태 때 사용
    public List<Long> selectAdminDlvarIdBndlY(List<Long> ordIdList) {
        return session.selectList(namespace + "select_dlvarId_bndl_y", ordIdList);
    }

    //   ordId로 dlvarId list 받아오기 송장번호 등록 상태, 배송중 처리 상태 때 사용
    public List<Long> selectAdminDlvarIdBndlN(List<Long> ordIdList) {
        return session.selectList(namespace + "select_dlvarId_bndl_n", ordIdList);
    }

    // ordId로 dlvarId 개수 list 받아오기 : order master의 상태 변화로 등록
    public List<Integer> selectDlvarIdCountList(List<Long> ordIdList) {
        return session.selectList(namespace + "select_dlvarId_count_list", ordIdList);
    }

    // 송장 번호 등록 된 정보를 통해서 delivery history insert
    public int insertInvoiceDeliveryHistory(AdminOrderOrderDto adminOrderOrderDto) {
        return session.insert(namespace + "insert_invoice_delivery_history", adminOrderOrderDto);
    }

    // admin 송장번호 등록시, 송장번호, 공급사, 배송비 등록
    public int updateAdminInvoiceNum(List<InvoiceDeliveryFeeInfo> invoiceDeliveryFeeInfoList) {
        return session.update(namespace + "update_admin_invoice_num", invoiceDeliveryFeeInfoList);
    }

    // admin 송장 번호 등록 stus 저장 : dm, od에서 사용
    public int updateInvoiceStatus(AdminOrderOrderDto adminOrderOrderDto) {
        return session.update(namespace + "update_invoice_status", adminOrderOrderDto);
    }

    // 배송중으로 상태 변경, 동일 ord_id를 갔지만 묶음 배송 처리가 되지 않은 상품에 한해서는 상태가 변경되지 않고 대신 해당 상품은 송장번호,배송비,공급사 정보 초기화
//    public int updateShippingStatusOnlyBundleY(List<Long> ordIdList) {
//        return session.update(namespace + "update_shipping_status_only_bundleY", ordIdList);
//    }

    // 배송중 버튼 누를 시, bndl_y인 order Detail, delivery master status 변경
    public int updateShippingStatusBundleY(AdminOrderOrderDto adminOrderOrderDto) {
        return session.update(namespace + "update_shipping_status_bndl_y", adminOrderOrderDto);
    }

    // 배송중 버튼 누를 시, bndl_n인 order Detail, delivery master status 변경 및 배송비, 공급사, 송장번호 초기화
    public int updateShippingStatusBundleN(AdminOrderOrderDto adminOrderOrderDto) {
        return session.update(namespace + "update_shipping_status_bndl_n", adminOrderOrderDto);
    }

    // 배송중 버튼 누를 시, 배송 master 정보를 통해서 bndl_y인 delivery history insert
    public int insertShippingDeliveryHistoryBundleY(AdminOrderOrderDto adminOrderOrderDto) {
        return session.insert(namespace + "insert_shipping_delivery_history_bndl_y", adminOrderOrderDto);
    }

    // 배송중 버튼 누를 시, 배송 master 정보를 통해서 bndl_n인 delivery history insert
    public int insertShippingDeliveryHistoryBundleN(AdminOrderOrderDto adminOrderOrderDto) {
        return session.insert(namespace + "insert_shipping_delivery_history_bndl_n", adminOrderOrderDto);
    }

    // 배송중 버튼 누를 시, order master 상태를 update : bndl_n == 'h3' 존재시, 부분배송중인 'h5' 없을 시 'h4' 상태변경
    public int updateOrderMasterShippingStatus(List<Long> ordIdList) {
        return session.update(namespace + "update_order_master_shipping_status", ordIdList);
    }

    // 배송 중 관리에서 기본 배송 관련 정보 보여줌 : 종합적으로 보여주는 값 - 주문상세, 배송 master, 결제 master, member
    public List<Map<String, Object>> selectShippingDeliveryInfo(Map<String, Object> periodData) {
        return session.selectList(namespace + "select_shipping_delivery_info_with_od_pm_m", periodData);
    }

    // ordId로 dlvarId 받아오기
    public List<Long> selectDeliverIdFromOrderId(List<Long> ordIdList) {
        return session.selectList(namespace + "select_dlvarId_from_ordId", ordIdList);
    }

    // 배송중 page에서 배송완료 일 경우, stus, up-dtm, up-id update : dm, od
    public int updateShipCompleteStatus(AdminOrderOrderDto adminOrderOrderDto) {
        return session.update(namespace + "update_ship_complete_stus", adminOrderOrderDto);
    }

    //  배송중 page에서 배송완료 일 경우 delivery history에 insert 하기
    public int insertShipCompleteDeliveryHistory(AdminOrderOrderDto adminOrderOrderDto) {
        return session.insert(namespace + "insert_ship_complete_delivery_hist", adminOrderOrderDto);
    }

    // 배송중 order master 조건에 따라서 배송완료, 부분 배송완료 설정하기 : 배송준비중의 join하는 방식이 아닌 suquery로 성능 높임
    public int updateShipCompleteOrderMasterStatus(AdminOrderOrderDto adminOrderOrderDto) {
        return session.update(namespace + "update_ship_complete_order_master_status", adminOrderOrderDto);
    }

    // 배송 완료에서 기본 배송 관련 정보 보여줌 : 종합적으로 보여주는 값 - 주문상세, 배송 master, 결제 master, member
    public List<Map<String, Object>> selectCompleteDeliveryInfo(Map<String, Object> periodData) {
        return session.selectList(namespace + "select_complete_delivery_info_with_od_pm_m", periodData);
    }

    // 수동으로 배송완료 admin page에서 구매확정으로 변경 시, od - stus update
    public int updateFixedCompleteStatusManual(AdminOrderOrderDto adminOrderOrderDto) {
        return session.update(namespace + "update_fixed_complete_manual", adminOrderOrderDto);
    }

    // osh insert가 1 이상일 경우, 자동으로 배송완료 admin page에서 'h6'이 order_detail 중에서 up_dtm이 now()보다 1주일 이상이면 구매확정 처리
    public int updateFixedCompleteAuto () {
        return session.insert(namespace + "update_fixed_complete_auto");
    }
}
