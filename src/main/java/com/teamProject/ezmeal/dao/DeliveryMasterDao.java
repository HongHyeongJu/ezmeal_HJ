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

    public int insertDeliveryMaster(DeliveryMasterDto deliveryMasterDto) {
        return session.insert(namespace + "insertDelivery", deliveryMasterDto);
    }
}
