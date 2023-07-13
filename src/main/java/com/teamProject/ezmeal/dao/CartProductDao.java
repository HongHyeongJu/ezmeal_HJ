package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.CartJoinProductDto;
import com.teamProject.ezmeal.domain.CartProductDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CartProductDao {

    private final SqlSession session;
    private static final String namespace = "cartProduct.";

    // 품절 상품 업데이트
    public int updateSoldOut(Long cartSeq) {
        return session.update(namespace + "soldOut_yn", cartSeq);
    }

    // 일반 상품 수량 - 품절, 삭제 제외
    public int countProduct(Long cartSeq) {
        return session.selectOne(namespace + "count", cartSeq);
    }

    // 일반 상품 : 냉장/냉동/상온 map으로 저장
    /* TODO  옵션 쿼리문 - 냉장 냉동, 상온에 조건 추가 하면 된다.
             -> 품절
                    단일상품 & 맛 & 무게 - 재고가 0이상인지 비교
                    수량 옵션의 경우     - 재고가 옵션의 값(quentity)보다 큰지 비교
             -> 담을수 있는 수량
                    단일상품 & 맛 & 무게 - 재고 개수 만큼
                    수량 옵션의 경우     - 재고가 옵션의 값(quentity)보다 큰지 비교
        p.s. 옵션: 맛 & 무게-각각 다른 상품 코드 , 수량-동일 코드  |  재고: 현재재고량 - 안전재고량
    */

    // 일반 상품 : 냉동, 냉장, 상온 분리
    public List<CartJoinProductDto> selectProduct(Long cartSeq) {
        return session.selectList(namespace + "product", cartSeq);
    }

    // 개별 상품 삭제 : JS fetch를 이용한 rest API 수행
    public int deleteCartProduct(List<Long> cartProdSeq) {
        return session.update(namespace + "delete", cartProdSeq);
    }

    // 해당 회원의 장바구니의 상품 존재여부 검증
    public int selectValidation(Map<String, Object> validationMap){
        return session.selectOne(namespace + "validation", validationMap);
    }
    // 수량 update
    public int updateQuantity(Map<String, Long> quantityMap){
        return session.update(namespace + "quantity", quantityMap);
    }

    // 주문하기에 선택된 장바구니 상품 컬럼 업데이트
    public int updateSelectedProduct (Map<String, Object> selectProductMap){
        return session.update(namespace + "select_Product", selectProductMap);
    }

    // 주문하기에 선택된 장바구니 상품 가져오기
    public List<CartJoinProductDto> selectOrderProducts(Long cartSeq)  {
        return session.selectList(namespace + "order_products", cartSeq);
    }

    // 주문하려니깐 품절된 상품 보여주기
    public List<Long> selectOrderListSoldOut(List<Long> cartProdSeq) {
        return session.selectList(namespace + "orderList_soldOut", cartProdSeq);
    }

    // 재고가 부족한 상품 cartProdPk, 실 재고량 정보 가지고 옴
    public List<CartJoinProductDto> selectOrderListInventory(List<Long> cartProdSeq) {
        return session.selectList(namespace + "orderList_inventory", cartProdSeq);
    }

    // 주문한 상품 수량 받아오기
    public int selectOrderProductNum(Long cartSeq){
        return session.selectOne(namespace + "count_orderProduct", cartSeq);
    }

    // TODO 담을수 있는 최대 수량
    // TODO 삭제된 상품들 중에서 up_dtm이 가장 낮은거 5개 보여주기
    // TODO 상품 목록에서 클릭시, 장바구니 insert하기
    // TODO 비회원 장바구니 login시 회원 장바구니에 insert하기
    // TODO 결제 완료시, 해당 장바구니 상품 delete
    // TODO 전체 취소 재결제시, 다시 해당 장바구니 보여주기 - 결제 날짜와 동일한 dateTime의 up_dtm의 상품을 다시 가져 오기

    // 관리자

    // 장바구니에 존재하는 모든 상품 - 삭제 항목 제외
    public List<CartProductDto> selectAllProd(Long cartSeq) {
        return session.selectList(namespace + "all_product", cartSeq);
    }
    // TODO 많이 담은 상품 도표로 나타내기 - top 5개

    // TEST
    // TODO - test용 insert, update, delete 생성
}