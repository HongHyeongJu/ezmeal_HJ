package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.CartProductDao;
import com.teamProject.ezmeal.domain.CartProductDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.stereotype.Service;

import java.awt.print.PrinterException;
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
    // TODO option 확실해지면 다시 작성 필요 - option_cd 존재시, option 값(opt_val)을 상품 명 옆에 두고 | 가격은 옵션 가격으로 지정
    public Map<String, List<CartProductDto>> getProducts(Long cartSeq) {
        Map<String, List<CartProductDto>> productsMap = null;
        int maxRetries = 3;
        int retryCount = 0;
        while (retryCount < maxRetries) {
            try {
                productsMap = new HashMap<>();
                productsMap.put("cold", cartProductDao.selectProdCold(cartSeq));
                productsMap.put("ice", cartProductDao.selectProdIce(cartSeq));
                productsMap.put("outside", cartProductDao.selectProdOutSide(cartSeq));
                break; // 성공적으로 데이터를 가져왔으므로 반복문 종료
            } catch (PersistenceException e) {
                retryCount++;
                if (retryCount >= maxRetries) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                // 추가적인 로깅 또는 대기 시간 설정
                System.out.println("Retrying after 2 seconds...");
                try {
                    Thread.sleep(2000); // 3초 대기
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        return productsMap;
    }


    // 주문하기에 선택된 장바구니 상품 가져오기
    // TODO 필요시 배열로 받아오는 방향으로 변경 <- code 수정 후 작성하기, 당장은 수정 가능성이 너무 높음


    // 상품 삭제 update
    public int removeCartProduct(Long cartProdSeq) {
        return cartProductDao.deleteCartProduct(cartProdSeq);
    }

    // 해당 회원의 장바구니의 상품 존재여부 검증
    public int validateCartProduct(Long cartSeq, Long cartProdSeq) {
        int successMessage = 1;
        int failMessage = 0;

        Map<String, Long> validationMap = new HashMap<>();
        validationMap.put("cartSeq", cartSeq);
        validationMap.put("cartProdSeq", cartProdSeq);

        int validationInt = cartProductDao.selectValidation(validationMap);

        if (validationInt == 0) return failMessage;
        return successMessage;

    }


    // 관리자

    // 장바구니에 존재하는 모든 상품
    public List<CartProductDto> getProductList(Long mbrId) throws Exception {
        List<CartProductDto> cartProducts = cartProductDao.selectAllProd(mbrId);
        String type = cartProducts.get(0).getTyp();
        return cartProducts;
    }

}
