package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.CartProductDao;
import com.teamProject.ezmeal.domain.CartProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO transaction이 핵심 -> 이거의 예외, service 단의 목적
@Service
@RequiredArgsConstructor
public class CartProductService {
    private final CartProductDao cartProductDao;

    // 품절 상품 업데이트
    public int checkSoldOut(Long cartSeq) {
        return cartProductDao.updateSoldOut(cartSeq);
    }

    // 일반 상품 수량
    public int countProduct(Long cartSeq) {
        return cartProductDao.countProduct(cartSeq);
    }

    // 일반 상품 : 냉장/냉동/상온 map으로 저장
    // TODO  option 확실해지면 다시 작성 필요 - option_cd 존재시, option 값(opt_val)을 상품 명 옆에 두고 | 가격은 옵션 가격으로 지정
    public Map<String, List<CartProductDto>> getProducts(Long cartSeq) {
        Map<String, List<CartProductDto>> productsMap = new HashMap<>();
        productsMap.put("cold", cartProductDao.selectProdCold(cartSeq));
        productsMap.put("ice", cartProductDao.selectProdIce(cartSeq));
        productsMap.put("outside", cartProductDao.selectProdOutSide(cartSeq));
        System.out.println("productsMap = " + productsMap);
        return productsMap;
    }

    // 주문하기에 선택된 장바구니 상품 가져오기
    // TODO 필요시 배열로 받아오는 방향으로 변경 <- code 수정 후 작성하기, 당장은 수정 가능성이 너무 높음



    // 상품 삭제 update
    // TODO cart_prod_seq로 변경 필요
    public int deleteCartProduct(Map map) throws Exception {
        return cartProductDao.deleteCartProductYN(map);
    }


    // 관리자

    // 장바구니에 존재하는 모든 상품
    public List<CartProductDto> getProductList(Long mbrId) throws Exception {
        List<CartProductDto> cartProducts = cartProductDao.selectAllProd(mbrId);
        String type = cartProducts.get(0).getTyp();
        return cartProducts;
    }

}
