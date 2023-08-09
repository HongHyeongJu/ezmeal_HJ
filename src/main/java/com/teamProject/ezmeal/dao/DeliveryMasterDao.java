package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.DeliveryMasterDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class DeliveryMasterDao {
    private final SqlSession session;
    private static final String namespace = "deliveryMasterDao.";

    // 주문 완료 후, insert 되는 배송지 기본 정보 TODO 결제 취소시, 사라져야 함
    public int insertDeliveryMaster(List<DeliveryMasterDto> deliveryMasterDtoList) {
        return session.insert(namespace + "insertDelivery", deliveryMasterDtoList);
    }

    //  주문상세의 배송 이력을 가지고 오기전 배송 수량, 배송 대기 수량, 올바른 배송 상태 수량 미 list 가지고 오기
    public List<Map> selectDlvarIdDataList(Long ordId) {
        List<Map> objects = session.selectList(namespace + "select_dlvarId_data_list_for_order_detail", ordId);
        System.out.println("부분배송 dao");
        System.out.println("objects = " + objects);
        return objects;
    }

    // 주문상세에 사용할 배송 이력 가지고 오기
    public List<Map> selectDlvarHist(Long dlvarId) {
        return session.selectList(namespace + "select_dlvar_hist", dlvarId);
    }
}
