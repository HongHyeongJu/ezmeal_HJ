package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.DeliveryMasterDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DeliveryMasterDao {
    private final SqlSession session;
    private static final String namespace = "deliveryMasterDao.";

    // 주문 완료 후, insert 되는 배송지 기본 정보 TODO 결제 취소시, 사라져야 함
    public int insertDeliveryMaster(List<DeliveryMasterDto> deliveryMasterDtoList) {
        return session.insert(namespace + "insertDelivery", deliveryMasterDtoList);
    }
}
