package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.OrderDetailDao;
import com.teamProject.ezmeal.domain.OrderDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderDetailService {
    private final OrderDetailDao orderDetailDao;

    // 주문 상세 페이지에서 보여줄 각각의 상품 정보들
    public List<OrderDetailDto> getOrderDetailProductList(Long orderId) {
        return orderDetailDao.selectOrderDetailProductList(orderId);
    }

    //  주문 상세에 사용할 복합 정보 가지고 오기 : 결제 master, member master, delivery master
    public Map<String, Object> getOutsideOrderDetailInfo(Long orderId) {
        return orderDetailDao.selectOutsideOrderDetailInfo(orderId);
    }
}
