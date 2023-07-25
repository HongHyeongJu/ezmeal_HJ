package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.DeliveryMasterDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeliveryMasterDao {
    private final SqlSession session;
    private static final String namespace = "deliveryMasterDao.";

    // 주문 완료 후, insert 되는 배송지 기본 정보 TODO 결제 취소시, 사라져야 함
    public int insertDeliveryMaster(DeliveryMasterDto deliveryMasterDto) {
        return session.insert(namespace + "insertDelivery", deliveryMasterDto);
    }
}
