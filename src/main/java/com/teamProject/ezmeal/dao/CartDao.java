package com.teamProject.ezmeal.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CartDao {
    private final SqlSession session;
    private static final String namespace = "cart.";

    // select - 회원 장바구니 번호
    public Long selectCartSeq(Long mbrId) {
        try {
            return session.selectOne(namespace + "cart_seq", mbrId);
        } catch (PersistenceException pse) {
            // MyBatis SQLException 예외 처리
            pse.printStackTrace();
            // TODO log로 변경
            System.out.println("pse.getMessage() = " + pse.getMessage());
            throw new RuntimeException(pse);
        }
    }
    // TODO insert - 회원 가입시 장바구니 자동 생성
//    public int insertCart(Long mbrId){
//        return session.insert(namespace + "insert", mbrId);
//    }
}
