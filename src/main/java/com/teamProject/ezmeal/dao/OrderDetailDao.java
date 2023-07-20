package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.OrderDetailDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderDetailDao {
    private final SqlSession session;
    private static final String namespace = "orderDetailDao.";
    public int insertOrderDetail (List<OrderDetailDto> orderDetailList){
        return session.insert(namespace + "insertOrderDetail", orderDetailList);
    }
}
