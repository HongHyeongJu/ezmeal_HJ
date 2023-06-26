package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.CartDao;
import com.teamProject.ezmeal.domain.CartProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl {
    private final CartDao cartDao;


    // 일반, 냉동, 냉장, 상온 list로 담기
    public List<CartProductDto> getProduct(Long mbrId) throws Exception {
        List<CartProductDto> cartProducts = cartDao.prodList(mbrId);
        String typ = cartProducts.get(0).getTyp();
        return cartProducts;
    }

    // 냉장
    public List<CartProductDto> getColdProduct(Long mbrId) throws Exception {
        return cartDao.prodColdList(mbrId);
    }
    // 냉동
    public List<CartProductDto> getIceProduct(Long mbrId) throws Exception {
        return cartDao.prodIceList(mbrId);
    }
    // 상온
    public List<CartProductDto> getOutSideProduct(Long mbrId) throws Exception {
        return cartDao.prodOutSideList(mbrId);
    }
}
