package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.*;
import com.teamProject.ezmeal.domain.PointTransactionCodeDto;
import com.teamProject.ezmeal.domain.PointTransactionHistoryDto;
import com.teamProject.ezmeal.domain.restAPIDomain.InventoryData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryEventService {
    private final ProductInventoryDao productInventoryDao; // 재고 update
    private final PointTransactionCodeDao pointTransactionCodeDao; // 포인트 사용
    private final PointTransactionHistoryDao pointTransactionHistoryDao; // 포인트 변경 이력남기기
    private final MemberCouponDao memberCouponDao; // 사용 쿠폰 정보 업데이트
    private final CartProductDao cartProductDao; // 장바구님 주문한 상품 delete 해버리기, select는 유지

    public Integer decreaseInventoryAfterPayment(List<InventoryData> inventoryDataList){
        return productInventoryDao.updateInventoryAfterPayment(inventoryDataList); // 상품 재고 update
    }

    public PointTransactionCodeDto getUsePointInfo(String pointTransactionCode) {
        return pointTransactionCodeDao.selectUsePointInfo(pointTransactionCode); // 사용 포인트 이력에 필요한 포인트 코드
    }

    public int setPointHistory(PointTransactionHistoryDto pointTransactionHistoryDto) {
        return pointTransactionHistoryDao.insertPointHistory(pointTransactionHistoryDto); // 포인트 변경 이력남기기
    }

    public int setUsedCoupon(Map<String, Long> updateCouponDataList) {
        return memberCouponDao.updateUsedCoupon(updateCouponDataList);     // 사용한 coupon update 하기
    }

    public int setCartProductAfterOrder(Long cartSeq) {
        return cartProductDao.updateCartProductAfterOrder(cartSeq); // 장바구님 주문한 상품 delete 해버리기, select는 유지
    }

}
