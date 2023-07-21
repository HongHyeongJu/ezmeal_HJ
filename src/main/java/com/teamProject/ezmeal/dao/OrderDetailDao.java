package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.OrderDetailDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class OrderDetailDao {
    private final SqlSession session;
    private static final String namespace = "orderDetailDao.";

    // 주문 결제 후, 값 넣기
    public int insertOrderDetail(List<OrderDetailDto> orderDetailList) {
        return session.insert(namespace + "insertOrderDetail", orderDetailList);
    }

    // 주문 상세 페이지에서 보여줄 각각의 상품 정보들
    public List<OrderDetailDto> selectOrderDetailProductList(Long ordId) {
        return session.selectList(namespace + "select_order_detail", ordId);
    }

    //  주문 상세에 사용할 복합 정보 가지고 오기 : 결제 master, member master, delivery master
    public Map<String, Object> selectOutsideOrderDetailInfo(Long ordId) {
        return session.selectOne(namespace + "select_outside_order_detail_info", ordId);
    }
}
