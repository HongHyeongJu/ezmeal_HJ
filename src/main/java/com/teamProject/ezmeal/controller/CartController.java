package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.domain.CartJoinProductDto;
import com.teamProject.ezmeal.domain.CartProductDto;
import com.teamProject.ezmeal.domain.DeliveryAddressDto;
import com.teamProject.ezmeal.service.CartProductService;
import com.teamProject.ezmeal.service.CartService;
import com.teamProject.ezmeal.service.DeliveryAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final CartProductService cartProductService;
    private final DeliveryAddressService deliveryAddressService;
    private static final String[] TYPE_NAME = {"냉동", "냉장", "상온"};
    private static final String[] productName = {"productIce", "productCold", "productOutside"};

    @GetMapping
    public String getGeneral(@SessionAttribute(required = false) Long memberId, Model model) {
        // TODO 회원 session 유무로 비회원, 회원 장바구니 접근 logic 수행
        // 회원 장바구니 가져오기
        Long cartSeq = cartService.getCartSeq(memberId);
        // 품절 상품 업데이트
        int updatedCnt = cartProductService.checkSoldOut(cartSeq);
        // 기본 배송지
        // todo 선택 배송지 존재시, 선택배송지가 되도록 logic 작성 필요 - selectAddress
        DeliveryAddressDto defaultAddress = deliveryAddressService.getDefaultAddress(memberId);
        // 상품 list
        List<CartJoinProductDto> productsList = cartProductService.getProducts(cartSeq);
        int productCount = productsList.size();

        // 상품 목록 type별로 분리하기
        List<CartJoinProductDto> iceList = new ArrayList<>();
        List<CartJoinProductDto> coldList = new ArrayList<>();
        List<CartJoinProductDto> outSideList = new ArrayList<>();

        for (CartJoinProductDto product : productsList) {
            if (product.getTyp().equals(TYPE_NAME[0])) iceList.add(product);
            else if (product.getTyp().equals(TYPE_NAME[1])) coldList.add(product);
            else if (product.getTyp().equals(TYPE_NAME[2])) outSideList.add(product);
        }
        model.addAttribute(productName[0], (iceList.size() != 0) ? iceList : null);
        model.addAttribute(productName[1], (coldList.size() != 0) ? coldList : null);
        model.addAttribute(productName[2], (outSideList.size() != 0) ? outSideList : null);

        model.addAttribute("count", productCount);
        model.addAttribute("defaultAddress", defaultAddress);

        return "cart";
    }

    // 개별 상품 삭제 : JS fetch를 이용한 rest API 수행
    @PatchMapping("/delete")
    @ResponseBody
    public String removeProduct(@SessionAttribute Long memberId, @RequestBody List<Long> cartProdSeqList) {
//        // 예외1. 회원 세션 만료  TODO 장바구니로 이동 필요
        if (memberId == null) return "no_memberId";

        // 예외2. service에서 검증 돌림
        Long cartSeq = cartService.getCartSeq(memberId);
        int validationResult = cartProductService.validateCartProduct(cartSeq, cartProdSeqList);

        // 예외3. 잘못된 상품으로 접근
        if (validationResult == 0) return "wrong product";

        cartProductService.removeCartProduct(cartProdSeqList);
        // TODO  2. 해당 jsp를 변수에 담이서 return한다. 3. js에서 html을 갈아엎는다.
//        Map<String, List<CartJoinProductDto>> productsMap = cartProductService.getProducts(cartSeq);
        return "cartProductModule";
    }

    @PatchMapping("/select")
    @ResponseBody
    public String updateOrderProduct(@SessionAttribute Long memberId, @RequestBody List<Long> cartProdSeqList) {
        System.out.println("cartProdSeqList = " + cartProdSeqList);
        // TODO cartProdSeqList 만약 값이 비어있을 경우, return
        Long cartSeq = cartService.getCartSeq(memberId);

        int updateNum = cartProductService.updateOrderProduct(cartSeq, cartProdSeqList);
        if (updateNum > 0) return "success";
        else return "false";
    }

    @PatchMapping("/update")
    @ResponseBody
    public String updateProductQuantity(@RequestBody List<Long> quantityList) {
//        System.out.println("cartProdSeqList = " + quantityList); // [0]: cartProdSeq, [1]: quantity
        Map<String, Long> quantityMap = new HashMap<>();
        quantityMap.put("cartProdSeq", quantityList.get(0));
        quantityMap.put("quantity", quantityList.get(1));

        String result = cartProductService.changeQuantity(quantityMap);
        if (result.equals("fail")) return "fail";
        return result;
    }

    @PostMapping("/validation")
    @ResponseBody
    public String validateCartProduct(@SessionAttribute Long memberId, @RequestBody List<Long> cartProdSeqList) {
        // 예외1. 회원 세션 만료  TODO 장바구니로 이동 필요
        if (memberId == null) return "no_memberId";

        // 예외2. service에서 검증 돌림
        Long cartSeq = cartService.getCartSeq(memberId);
        int validationResult = cartProductService.validateCartProduct(cartSeq, cartProdSeqList);
        if (validationResult == 0) return "wrong product";

        return cartSeq + "";
    }

    @PostMapping("/soldOutValidation")
    @ResponseBody
    public String validateSoldOut(@SessionAttribute Long memberId, @RequestBody List<Long> cartProdSeqList) {
        Long cartSeq = cartService.getCartSeq(memberId);
        int updatedCnt = cartProductService.checkSoldOut(cartSeq);
        if (updatedCnt == 0) return "success";

        List<Long> longs = cartProductService.checkOrderListSoldOut(cartProdSeqList);
        if (longs.size() == 0) return "success";

        else return "fail";
    }

    @PostMapping("/inventoryValidation")
    @ResponseBody
    public String checkQuantity(@RequestBody List<Long> cartProdSeqList) {
        List<List<Number>> lists = cartProductService.checkOrderListOverInventory(cartProdSeqList);
        return lists + "";
    }


    /*상품 목록, 상품 상세에서 장바구니에 상품 담기  (seq 자동증가 X 버전) */
    @PostMapping("/add")
    public ResponseEntity<String> addCart(@RequestBody CartProductDto cartProductDto, @SessionAttribute Long memberId, RedirectAttributes reAtt) {

        System.out.println("------------컨트롤러 진입-----------");
        System.out.println("memberId: "+memberId);
        System.out.println("cartProductDto: "+cartProductDto.toString());

        memberId = 1006L;
        System.out.println("memberId: "+memberId);


        // mbr_id 값 체크
        if(memberId == null) {
            System.out.println("[컨트롤러]mbr_id 없음");
            /* 현재 카트에 추가하려는 상품및수량 챙겨서 로그인 페이지로 보내기.*/
            reAtt.addAttribute("msg","noLoginMember_From_CartAdd");
            /* 수정해야할 부분 */
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비로그인 회원 장바구니 추가. 로그인으로 보내버려");
        }

        boolean addCartResult = cartProductService.addProductToCart(memberId, cartProductDto);

        /*insert 실패했을 때*/
        if(addCartResult){
            System.out.println("[컨트롤러] insert 성공");
            return ResponseEntity.ok("Success message");
        } else {
            System.out.println("[컨트롤러] insert 실패");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("insert 실패");
        }

    }


}