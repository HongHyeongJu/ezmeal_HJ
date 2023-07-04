package com.teamProject.ezmeal.dao;

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
    // TODO  option 확실해지면 다시 작성 필요 - option_cd 존재시, option 값(opt_val)을 상품 명 옆에 두고 | 가격은 옵션 가격으로 지정
    /* TODO  옵션 쿼리문 - 냉장 냉동, 상온에 조건 추가 하면 된다.
             -> join으로 옵션 값 table 받아오기, column으로 옵션명, 옵션값(수량=quentity), 가격(소비자가, 판매가),
             -> 품절
                    단일상품 & 맛 & 무게 - 재고가 0이상인지 비교
                    수량 옵션의 경우     - 재고가 옵션의 값(quentity)보다 큰지 비교
             -> 담을수 있는 수량
                    단일상품 & 맛 & 무게 - 재고 개수 만큼
                    수량 옵션의 경우     - 재고가 옵션의 값(quentity)보다 큰지 비교
        p.s. 옵션: 맛 & 무게-각각 다른 상품 코드 , 수량-동일 코드  |  재고: 현재재고량 - 안전재고량
    */

    // 냉장 상품
    public List<CartProductDto> selectProdCold(Long cartSeq) {
        return session.selectList(namespace + "product_cold", cartSeq);
    }

    // 냉동 상품
    public List<CartProductDto> selectProdIce(Long cartSeq) {
        return session.selectList(namespace + "product_ice", cartSeq);
    }

    // 일반 상품
    public List<CartProductDto> selectProdOutSide(Long cartSeq) {
        return session.selectList(namespace + "product_outside", cartSeq);
    }

    // 주문하기에 선택된 장바구니 상품 가져오기
    // TODO 필요시 배열로 받아오는 방향으로 변경
    public List<CartProductDto> cartProducts(Long cartSeq, String prodCodeString)  {

        // 하나로 되어있는 string 배열로 쪼개기
        String[] parts = prodCodeString.split("p");

        ArrayList<String> prodCdList = new ArrayList<>();
        for (String part : parts) {
            if (!part.isEmpty()) {
                String prodCd = "p" + part; // TODO 구독상품은 p -> g
                prodCdList.add(prodCd);
            }
        }

        // map으로 다른 것들 담기
        Map map = new HashMap<>();
        map.put("cartSeq", cartSeq);
        map.put("prodCdList", prodCdList);

        return session.selectList(namespace + "selected_prod", map);
    }

    // 삭제한 상품 list 받아오는 작업 수행 - 시스템 컬럼 update 같이 해주기
    // TODO cart_prod_seq로 변경 필요
    public int deleteCartProductYN(Map map) {
        return session.update(namespace + "delete", map);
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
    //
}