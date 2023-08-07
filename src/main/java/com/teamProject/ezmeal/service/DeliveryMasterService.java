package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.DeliveryMasterDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DeliveryMasterService {
    private final DeliveryMasterDao deliveryMasterDao;

    //  주문상세의 배송 이력을 가지고 오기전 배송 수량, 배송 대기 수량, 올바른 배송 상태 수량 미 list 가지고 오기
    public List<Map> getDlvarIdDataList(Long orderId) {
        return deliveryMasterDao.selectDlvarIdDataList(orderId);
    }

    // 주문상세에 사용할 배송 이력 가지고 오기
    public List<Map> getDlvarHist(Long dlvarId){
        return deliveryMasterDao.selectDlvarHist(dlvarId);
    }
}
