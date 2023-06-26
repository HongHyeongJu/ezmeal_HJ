package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.CartProductDto;
import com.teamProject.ezmeal.domain.DeliveryAddressDto;

import java.util.List;

public interface CartDao {
    // cart seq를 구하는 query
    Long cartSeq(Long mbrId) throws Exception;

    // 1차 count 완료 -> 일반, 구독 상품 분류 필요 -> 비동기처리필요
    int count(Long mbrId) throws Exception;

    //
    List<CartProductDto> prodList(Long mbrId) throws Exception;
    List<CartProductDto> subProdList(Long mbrId) throws Exception;
    int subCount(Long mbrId) throws Exception;

    List<CartProductDto> prodColdList(Long mbrId) throws Exception;
    List<CartProductDto> prodIceList(Long mbrId) throws Exception;
    List<CartProductDto> prodOutSideList(Long mbrId) throws Exception;

    void updateSoldOut(Long mbrId) throws Exception;

}
