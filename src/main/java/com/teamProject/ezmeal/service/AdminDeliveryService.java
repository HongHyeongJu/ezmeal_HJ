package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.AdminDeliveryDao;
import com.teamProject.ezmeal.dao.AdminOrderStatusHistoryDao;
import com.teamProject.ezmeal.domain.joinDomain.AdminOrderOrderDto;
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
    private final AdminOrderStatusHistoryDao adminOrderStatusHistoryDao;

    // 배송 준비중 관리에서 기본 배송 관련 정보 보여줌 : 종합적으로 보여주는 값 - 주문상세, 배송 master, 결제 master, member
    public List<Map<String, Object>> getPrepareDeliveryInfo(Map<String, Object> periodData) {
        return adminDeliveryDao.selectPrepareDeliveryInfo(periodData);
    }

    // 묶음 배송 update 쿼리, 단일일 경우와 다중일 경우 모두 사용 가능
    public int setAdminBundleYn(BundleData bundleData) {
        return adminDeliveryDao.updateAdminBundleYN(bundleData);
    }

    //   ordId로 dlvarId list 받아오기 |  송장번호 등록 상태, 배송중 처리 상태 때 사용
    public List<Long> getAdminDlvarIdBndlY(List<Long> ordIdList) {
        return adminDeliveryDao.selectAdminDlvarIdBndlY(ordIdList);
    }

    //   ordId로 묶음처리 안된 dlvarId list 받아오기 |  송장번호 등록 상태, 배송중 처리 상태 때 사용
    public List<Long> getAdminDlvarIdBndlN(List<Long> ordIdList) {
        return adminDeliveryDao.selectAdminDlvarIdBndlN(ordIdList);
    }

    // ordId로 dlvarId 개수 list 받아오기 : order master의 상태 변화로 등록
    public List<Integer> getDlvarIdCountList(List<Long> ordIdList) {
        return adminDeliveryDao.selectDlvarIdCountList(ordIdList);
    }

    // admin 송장번호 등록시, 송장번호, 공급사, 배송비 등록
    public int setAdminInvoiceNum(List<InvoiceDeliveryFeeInfo> invoiceDeliveryFeeInfoList) {
        return adminDeliveryDao.updateAdminInvoiceNum(invoiceDeliveryFeeInfoList);
    }

    // admin 송장번호 등록 order detail, delivery master status update
    public int setInvoiceStatus(AdminOrderOrderDto adminOrderOrderDto) {
        return adminDeliveryDao.updateInvoiceStatus(adminOrderOrderDto);
    }

    // 송장 번호 등록 된 정보를 통해서 delivery history insert
    public int setInvoiceDeliveryHistory(AdminOrderOrderDto adminOrderOrderDto) {
        return adminDeliveryDao.insertInvoiceDeliveryHistory(adminOrderOrderDto);
    }


// 배송중으로 상태 변경, 동일 ord_id를 갔지만 묶음 배송 처리가 되지 않은 상품에 한해서는 상태가 변경되지 않고 대신 해당 상품은 송장번호,배송비,공급사 정보 초기화
//    public int setShippingStatusOnlyBundleY(List<Long> orderIdList) {
//        return adminDeliveryDao.updateShippingStatusOnlyBundleY(orderIdList);
//    }

    // 배송중 버튼 누를 시, bndl_y인 order Detail, delivery master status 변경
    public int setShippingStatusBundleY(AdminOrderOrderDto adminOrderOrderDto) {
        return adminDeliveryDao.updateShippingStatusBundleY(adminOrderOrderDto);
    }

    // 배송중 버튼 누를 시, bndl_n인 order Detail, delivery master status 변경 및 배송비, 공급사, 송장번호 초기화
    public int setShippingStatusBundleN(AdminOrderOrderDto adminOrderOrderDto) {
        return adminDeliveryDao.updateShippingStatusBundleN(adminOrderOrderDto);
    }

    // 배송중 버튼 누를 시, 배송 master 정보를 통해서 bndl_y인 delivery history insert
    public int setShippingDeliveryHistoryBundleY(AdminOrderOrderDto adminOrderOrderDto) {
        return adminDeliveryDao.insertShippingDeliveryHistoryBundleY(adminOrderOrderDto);
    }

    // 배송중 버튼 누를 시, 배송 master 정보를 통해서 bndl_n인 delivery history insert
    public int setShippingDeliveryHistoryBundleN(AdminOrderOrderDto adminOrderOrderDto) {
        return adminDeliveryDao.insertShippingDeliveryHistoryBundleN(adminOrderOrderDto);
    }

    // 배송중 버튼 누를 시, order master 상태를 update : bndl_n == 'h3' 존재시, 부분배송중인 'h5' 없을 시 'h4' 상태변경
    public int setOrderMasterShippingStatus(List<Long> orderIdList) {
        return adminDeliveryDao.updateOrderMasterShippingStatus(orderIdList);
    }

    // 배송 중 관리에서 기본 배송 관련 정보 보여줌 : 종합적으로 보여주는 값 - 주문상세, 배송 master, 결제 master, member
    public List<Map<String, Object>> getShippingDeliveryInfo(Map<String, Object> periodData) {
        return adminDeliveryDao.selectShippingDeliveryInfo(periodData);
    }

    // ordId로 dlvarId 받아오기
    public List<Long> getDeliverIdFromOrderId(List<Long> ordIdList) {
        return adminDeliveryDao.selectDeliverIdFromOrderId(ordIdList);
    }

    // 배송중 page에서 배송완료 일 경우, stus, up-dtm update
    public int setShipCompleteStatus(AdminOrderOrderDto adminOrderOrderDto) {
        return adminDeliveryDao.updateShipCompleteStatus(adminOrderOrderDto);
    }

    //  배송중 page에서 배송완료 일 경우 delivery history에 insert 하기
    public int setShipCompleteDeliveryHistory(AdminOrderOrderDto adminOrderOrderDto) {
        return adminDeliveryDao.insertShipCompleteDeliveryHistory(adminOrderOrderDto);
    }

    // 배송중 order master 조건에 따라서 배송완료, 부분 배송완료 설정하기 : 배송준비중의 join하는 방식이 아닌 suquery로 성능 높임
    public int setShipCompleteOrderMasterStatus(AdminOrderOrderDto adminOrderOrderDto) {
        return adminDeliveryDao.updateShipCompleteOrderMasterStatus(adminOrderOrderDto);
    }


    // 배송 완료에서 기본 배송 관련 정보 보여줌 : 종합적으로 보여주는 값 - 주문상세, 배송 master, 결제 master, member
    public List<Map<String, Object>> getCompleteDeliveryInfo(Map<String, Object> periodData) {
        return adminDeliveryDao.selectCompleteDeliveryInfo(periodData);
    }

    // 수동으로 배송완료 admin page에서 구매확정으로 변경 시, od - stus update
    public int setFixedCompleteStatusManual(AdminOrderOrderDto adminOrderOrderDto) {
        return adminDeliveryDao.updateFixedCompleteStatusManual(adminOrderOrderDto);
    }

    // 수동 처리후, order status history insert 수행   배송완료 admin에서 수동으로 구매 확정시, 이력 남기기
    public int insertFixedCompleteManual(AdminOrderOrderDto adminOrderOrderDto) {
        return adminOrderStatusHistoryDao.insertFixedCompleteManual(adminOrderOrderDto);
    }

    // 배송완료 admin 에서 구매확정 update 상황이 존재시 order status history 먼저 insert
    public int insertFixedCompleteAuto() {
        return adminOrderStatusHistoryDao.insertFixedCompleteAuto();
    }

    //osh insert가 1 이상일 경우, 자동으로 배송완료 admin page에서 'h6'이 order_detail 중에서 up_dtm이 now()보다 1주일 이상이면 구매확정 처리
    public int updateFixedCompleteAuto() {
        return adminDeliveryDao.updateFixedCompleteAuto();
    }
}
