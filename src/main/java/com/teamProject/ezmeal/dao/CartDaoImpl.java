package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.CartProductDto;
import com.teamProject.ezmeal.domain.DeliveryAddressDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CartDaoImpl implements CartDao {

    /**
     * mbr_id = 1001
     * service단으로 부터 session에 저장된 회원의 pk값을 받아와야한다.
     * 현재는 회원가입 및 로그인이 구현되지 않았으므로 임의로 member_id 이용
     */

    private final SqlSession session;
    private static final String namespace = "tb_cart.";

    // soldout update
    @Override
    public void updateSoldOut(int mbrId) throws Exception {
        session.update(namespace + "soldOut_yn", mbrId);
    }

    // cart seq를 구하는 query
    @Override
    public int cartSeq(int mbrId) throws Exception {
        return session.selectOne(namespace + "tb_cart_seq", mbrId);
    }

    // 1차 count 완료 -> 일반, 구독 상품 분류
    @Override
    public int count(int mbrId) throws Exception {
        int cartSeq = cartSeq(mbrId); // cart_seq
        return session.selectOne(namespace + "tb_cart_product-count", cartSeq);
    }
    @Override
    public int subCount(int mbrId) throws Exception {
        int cartSeq = cartSeq(mbrId); // cart_seq
        return session.selectOne(namespace + "tb_cart_subscript-count", cartSeq);
    }

    // 2개 일반, 구독
    @Override
    public List<CartProductDto> prodList(int mbrId) throws Exception {
        int cartSeq = cartSeq(mbrId);// cart_seq
        return session.selectList("tb_cart_product", cartSeq);
    }
    // 일반 상품 냉장, 냉동, 상온
    @Override
    public List<CartProductDto> prodColdList(int mbrId) throws Exception {
        int cartSeq = cartSeq(mbrId);// cart_seq
        return session.selectList("tb_cart_product_cold", cartSeq);
    }

    @Override
    public List<CartProductDto> prodIceList(int mbrId) throws Exception {
        int cartSeq = cartSeq(mbrId);// cart_seq
        return session.selectList("tb_cart_product_ice", cartSeq);
    }

    @Override
    public List<CartProductDto> prodOutSideList(int mbrId) throws Exception {
        int cartSeq = cartSeq(mbrId);// cart_seq
        return session.selectList("tb_cart_product_outside", cartSeq);
    }

    @Override
    public List<CartProductDto> subProdList(int mbrId) throws Exception {
        int cartSeq = cartSeq(mbrId);// cart_seq
        return session.selectList("tb_cart_sub-product", cartSeq);
    }

    // 삭제한 상품 list 받아오는 작업 수행

}

