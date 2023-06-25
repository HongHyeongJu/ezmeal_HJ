package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.CartProductDto;
import com.teamProject.ezmeal.domain.DeliveryAddressDto;

import java.util.List;

public interface CartDao {
    // cart seq를 구하는 query
    int cartSeq(int mbrId) throws Exception;

    // 1차 count 완료 -> 일반, 구독 상품 분류 필요 -> 비동기처리필요
    int count(int mbrId) throws Exception;

    //
    List<CartProductDto> prodList(int mbrId) throws Exception;
    List<CartProductDto> subProdList(int mbrId) throws Exception;
    int subCount(int mbrId) throws Exception;

    List<CartProductDto> prodColdList(int mbrId) throws Exception;
    List<CartProductDto> prodIceList(int mbrId) throws Exception;
    List<CartProductDto> prodOutSideList(int mbrId) throws Exception;

    void updateSoldOut(int mbrId) throws Exception;

}
