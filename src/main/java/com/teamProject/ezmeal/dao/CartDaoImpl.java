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
    public int updateSoldOut(Long mbrId) throws Exception {
        return session.update(namespace + "soldOut_yn", mbrId);
    }

    // cart seq를 구하는 query
    @Override
    public Long cartSeq(Long mbrId) throws Exception {
        return session.selectOne(namespace + "tb_cart_seq", mbrId);
    }

    // 1차 count 완료 -> 일반, 구독 상품 분류
    @Override
    public int count(Long mbrId) throws Exception {
        Long cartSeq = cartSeq(mbrId); // cart_seq
        return session.selectOne(namespace + "tb_cart_product-count", cartSeq);
    }

    @Override
    public int subCount(Long mbrId) throws Exception {
        Long cartSeq = cartSeq(mbrId); // cart_seq
        return session.selectOne(namespace + "tb_cart_subscript-count", cartSeq);
    }

    // 2개 일반, 구독
    @Override
    public List<CartProductDto> prodList(Long mbrId) throws Exception {
        Long cartSeq = cartSeq(mbrId);// cart_seq
        return session.selectList(namespace + "tb_cart_product", cartSeq);
    }

    // 일반 상품 냉장, 냉동, 상온
    @Override
    public List<CartProductDto> prodColdList(Long mbrId) throws Exception {
        Long cartSeq = cartSeq(mbrId);// cart_seq
        return session.selectList(namespace + "tb_cart_product_cold", cartSeq);
    }

    @Override
    public List<CartProductDto> prodIceList(Long mbrId) throws Exception {
        Long cartSeq = cartSeq(mbrId);// cart_seq
        return session.selectList(namespace + "tb_cart_product_ice", cartSeq);
    }

    @Override
    public List<CartProductDto> prodOutSideList(Long mbrId) throws Exception {
        Long cartSeq = cartSeq(mbrId);// cart_seq
        return session.selectList(namespace + "tb_cart_product_outside", cartSeq);
    }

    @Override
    public List<CartProductDto> subProdList(Long mbrId) throws Exception {
        Long cartSeq = cartSeq(mbrId);// cart_seq
        return session.selectList(namespace + "tb_cart_sub-product", cartSeq);
    }

    // 동적쿼리
    @Override
    public List<CartProductDto> cartProducts(Long mbrId, String prodCodeString) throws Exception {
        Long cartSeq = cartSeq(mbrId);// cart_seq

        // 하나로 되어있는 string 배열로 쪼개기
        String[] parts = prodCodeString.split("p"); // TODO 구독상품은 p -> g

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

        return session.selectList(namespace + "select_prod", map);
    }
    // 삭제한 상품 list 받아오는 작업 수행

}

