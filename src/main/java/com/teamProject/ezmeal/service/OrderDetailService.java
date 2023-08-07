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

    // 주문 번호에 따른 주문 상세 pk 받아오기
    public List<Long> getOrderDetailPk(Long orderId) {
        return orderDetailDao.selectOrderDetailPk(orderId);
    }

    // 주문상세 page에서 구매확정으로 변경 시, od - stus update
    public int setOrderFixed(Long orderDetailId) {
        return orderDetailDao.updateOrderFixed(orderDetailId);
    }

    // 주문상세 page에서 리뷰작성 시, review_yn update
    public int setOrderReview(Long orderDetailId) {
        return orderDetailDao.updateOrderReview(orderDetailId);
    }

}
