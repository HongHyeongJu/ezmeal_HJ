package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.CartDao;
import com.teamProject.ezmeal.dao.CartProductDao;
import com.teamProject.ezmeal.domain.joinDomain.CartJoinProductDto;
import com.teamProject.ezmeal.domain.CartProductDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

// TODO transaction이 핵심 -> 이거의 예외, service 단의 목적
@Service
@RequiredArgsConstructor
public class CartProductService {
    private final CartProductDao cartProductDao;
    private final CartDao cartDao;

    public static final String[] TYPE_NAME = {"냉동", "냉장", "상온"};


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
    public List<CartJoinProductDto> getProducts(Long cartSeq) {
//        String[] typeName = {"냉장", "냉동", "상온"};
//        List<String> typeNameList = new ArrayList<>(Arrays.asList(typeName));
//
//        Map<String, List<CartProductDto>> productsMap = null;
//
//        int maxRetries = 3;
//        int retryCount = 0;
//        while (retryCount < maxRetries) {
//            try {
//                productsMap = new HashMap<>();
//                for (String type : typeNameList) {
//                    List<CartProductDto> cartProductDtos = cartProductDao.selectProduct(cartSeq);
//                    productsMap.put(type, cartProductDtos);
//                }
//                break; // 성공적으로 데이터를 가져왔으므로 반복문 종료
//            } catch (PersistenceException e) {
//                retryCount++;
//                if (retryCount >= maxRetries) {
//                    e.printStackTrace();
//                    throw new RuntimeException(e);
//                }
//                // 추가적인 로깅 또는 대기 시간 설정
//                System.out.println("Retrying after 2 seconds...");
//                try {
//                    Thread.sleep(2000); // 3초 대기
//                } catch (InterruptedException ex) {
//                    Thread.currentThread().interrupt();
//                }
//            }
//        }

        return cartProductDao.selectProduct(cartSeq);

    }


    // 주문하기에 선택된 장바구니 상품 가져오기
    // TODO 필요시 배열로 받아오는 방향으로 변경 <- code 수정 후 작성하기, 당장은 수정 가능성이 너무 높음


    // 상품 삭제 update
    public int removeCartProduct(List<Long> cartProdSeq) {
        return cartProductDao.deleteCartProduct(cartProdSeq);
    }

    // 해당 회원의 장바구니의 상품 존재여부 검증
    public int validateCartProduct(Long cartSeq, List<Long> cartProdSeq) {
        int successMessage = 1;
        int failMessage = 0;

        Map<String, Object> validationMap = new HashMap<>();
        validationMap.put("cartSeq", cartSeq);
        validationMap.put("cartProdSeq", cartProdSeq);

        int validationInt = cartProductDao.selectValidation(validationMap);

        if (validationInt == 0) return failMessage;
        return successMessage;

    }

    // 수량 update
    public String changeQuantity(Map<String, Long> quantityMap) {
        try {
            int updateResult = cartProductDao.updateQuantity(quantityMap);
            if (updateResult != 1) return "fail";
            return "success";
        } catch (PersistenceException e) {
            return "fail";
        }
    }

    // 주문하기에 선택된 장바구니 상품 컬럼 업데이트 (sel_prod n -> y)
    public int updateOrderProduct(Long cartSeq, List<Long> cartProdSeqList) {
        Map<String, Object> selectProductMap = new HashMap<>();
        selectProductMap.put("cartSeq", cartSeq);
        selectProductMap.put("cartProdSeqList", cartProdSeqList);

        return cartProductDao.updateSelectedProduct(selectProductMap);
    }

    // 주문하기 위한 상품 가져오기
    public List<CartJoinProductDto> getOrderProduct(Long cartSeq) {
        return cartProductDao.selectOrderProducts(cartSeq);
    }

    // 주문하려니깐 품절된 상품 보여주기
    public List<Long> checkOrderListSoldOut(List<Long> cartProdSeq) {
        return cartProductDao.selectOrderListSoldOut(cartProdSeq);
    }

    // 재고가 부족한 상품 cartProdPk, 실 재고량 정보 가지고 옴
    public List<List<Number>> checkOrderListOverInventory(List<Long> cartProdSeq) {
        List<CartJoinProductDto> cartJoinProductList = cartProductDao.selectOrderListInventory(cartProdSeq);
        List<List<Number>> productInfoList = new ArrayList<>();

        for (CartJoinProductDto cartJoinProduct : cartJoinProductList) {
            List<Number> productInfo = new ArrayList<>();
//            productInfo.add(cartJoinProduct.getCart_prod_seq());
            productInfo.add(cartJoinProduct.getProd_cd());
            productInfo.add(cartJoinProduct.getCurr_inv());
            productInfoList.add(productInfo);
        }
        return productInfoList; //[cartProdSeq, inventory]
    }

    public Integer countOrderProduct(Long cartSeq){
        return cartProductDao.selectOrderProductNum(cartSeq);
    }

    // 관리자

    // 장바구니에 존재하는 모든 상품
    public List<CartProductDto> getProductList(Long mbrId) {
        List<CartProductDto> cartProducts = cartProductDao.selectAllProd(mbrId);
        String type = cartProducts.get(0).getTyp();
        return cartProducts;
    }




    /*----- HHJ -----*/
    //장바구니에 상품 추가하기       /*<!-- (변경부분) boolean existProduct에서 opt_seq도 비교하도록 추가함 -->*/
    public boolean addProductToCart(Long mbrId, CartProductDto cartProductDto) {
        System.out.println("------------서비스 진입-----------");
        /*카트에 상품이 있는지 확인한다.
         * 있으면 -> 수량 update
         * 없으면 -> Insert  */
        try {
            /*아직 자동증가 아니라 seq 지정해줘야함 지금은 수동으로! */
            //            cartProductDto.setCart_prod_seq(13L);
            /*카트 시퀀스도 넣어줘야함 ㅎㅎ*/
            Long cart_seq = cartDao.selectCartSeq(mbrId);
            System.out.println("cart_seq: " + cart_seq);

            cartProductDto.setCart_seq(cart_seq);
            /*mbr-id*/
            cartProductDto.setMbr_id(mbrId);
            /*기본값 지정*/
            cartProductDto.setSel_prod("n");
            cartProductDto.setSoldout_yn("n");
            cartProductDto.setDel_yn("n");
            String today = LocalDate.now() + "";
            String add_dt = today.replace("-", "/");
            cartProductDto.setAdd_dt(add_dt);
            cartProductDto.setDel_yn("n");

            System.out.println("[서비스] cartProductDto 기본값 넣기 완료");
            System.out.println("cartProductDto: " + cartProductDto.toString());

            boolean existProduct =
                    (cartProductDao.selectProductInCart(cartProductDto) != null) ? true : false;

            /* 이미 그 상품이 있으면 수량 변경, del_yn 'n'으로 */
            if (existProduct) {
                System.out.println("[서비스] 이미 있는 상품 => update");
                cartProductDao.updateCartOfProductQty(cartProductDto);
                return true;
            } else {
                System.out.println("[서비스] 장바구니에 없는 상품 => insert");
                /* 장바구니에 담은 적 없으면 insert */
                cartProductDao.insertAddCart(cartProductDto);
                return true;
            }
        } catch (Exception e) {
            System.out.println("[서비스] 예외발생");
            e.getMessage();
            e.printStackTrace();
            return false;
        }
    }

    /*----- HHJ -----*/
    //장바구니에 상품List (옵션 많은 경우) 추가하기       /*<!-- (변경부분) boolean existProduct에서 opt_seq도 비교하도록 추가함 -->*/
    public boolean addProductListToCart(Long mbrId, CartProductDto[] cartProductDtos) {
        System.out.println("------------서비스 진입-----------");
        /*카트에 상품이 있는지 확인한다.
         * 있으면 -> 수량 update
         * 없으면 -> Insert  */
        try {
            /*아직 자동증가 아니라 seq 지정해줘야함 지금은 수동으로! */
//            cartProductDto.setCart_prod_seq(13L);
            /*카트 시퀀스도 넣어줘야함 ㅎㅎ*/
            Long cart_seq = cartDao.selectCartSeq(mbrId);
            System.out.println("cart_seq: "+cart_seq);

            for(CartProductDto cartProductDto : cartProductDtos){
                System.out.println("반복문 들어왔어요");
                cartProductDto.setCart_seq(cart_seq);
                /*mbr-id*/
                cartProductDto.setMbr_id(mbrId);
                /*기본값 지정*/
                cartProductDto.setSel_prod("n");
                cartProductDto.setSoldout_yn("n");
                cartProductDto.setDel_yn("n");
                String today = LocalDate.now()+"";
                String add_dt = today.replace("-","/");
                cartProductDto.setAdd_dt(add_dt);
                cartProductDto.setDel_yn("n");

                System.out.println("[서비스] cartProductDto 기본값 넣기 완료");
                System.out.println("cartProductDto: "+cartProductDto.toString());

                boolean existProduct =
                        (cartProductDao.selectProductInCart(cartProductDto) != null) ? true : false ;

                /* 이미 그 상품이 있으면 수량 변경, del_yn 'n'으로 */
                if(existProduct){
                    System.out.println("[서비스] 이미 있는 상품 => update");
                    cartProductDao.updateCartOfProductQty(cartProductDto);
                } else {
                    System.out.println("[서비스] 장바구니에 없는 상품 => insert");
                    /* 장바구니에 담은 적 없으면 insert */
                    cartProductDao.insertAddCart(cartProductDto);
                } // if-else 끝

            } // for 끝


        } catch (Exception e) {
            System.out.println("[서비스] 예외발생");
            e.getMessage();
            e.printStackTrace();
            return false;
        }
        return true;
    }



}
