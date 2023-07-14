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

    public OrderMasterDto selectOrderMaster() {
        return session.selectOne(namespace + "selectOrderMaster");
    }
}
