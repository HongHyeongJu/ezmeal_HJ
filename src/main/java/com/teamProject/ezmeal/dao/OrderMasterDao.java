package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.OrderMasterDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderMasterDao {

    private final SqlSession session;
    private static final String namespace = "orderMaster.";
    // 주문master insert
    public int insertOrderMaster(OrderMasterDto orderMasterDto){
        return session.insert(namespace + "insertOrder", orderMasterDto);
    }

    // 일반 select
    public OrderMasterDto selectOrderMaster(Long mbrId) {
        return session.selectOne(namespace + "selectOrderMaster", mbrId);
    }
    // 제일 최신 주문번호
    public Long selectOrderId(Long mbrId){
        return session.selectOne(namespace + "select_order_id", mbrId);
    }

    // 주문 상세에 배송 상세 정보 보내줄지 말지 알리는 기준
    public int selectShowDeliveryInfo(Long ordId){
        return session.selectOne(namespace + "select_check_show_delivery_info", ordId);
    }
}
