package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.dao.CartDao;
import com.teamProject.ezmeal.dao.DeliveryAddressDao;
import com.teamProject.ezmeal.dao.MemberDao;
import com.teamProject.ezmeal.domain.CartProductDto;
import com.teamProject.ezmeal.domain.DeliveryAddressDto;
import com.teamProject.ezmeal.domain.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final CartDao cartDao;
    private final DeliveryAddressDao deliveryAddressDao;
    private final MemberDao memberDao;

    @GetMapping("/general")
    public String getGeneralOrder(@SessionAttribute Long memberId, @CookieValue String orderProduct, Model model) {
        try {

            // TODO 현재 선택 된 상품의 updateSoldOut을 확인 필요
//            int updateSoldOut = cartDao.updateSoldOut(memberId);
//            if (updateSoldOut > 0){
//                return "forward:/cart/general";
//            }

            // TODO 배송지는 일반 구독 나눠서 우선권이 선택된 배송지, 선택된 배송지 column 없을 시 기본 배송지 -> 지금은 너무 할게 많으니깐 그냥 배송지
            //  deliveryAddressDao.choiseAddress(memberId)
            DeliveryAddressDto defaultAddress = deliveryAddressDao.defaultAddress(memberId);
            List<CartProductDto> cartProductDtos = cartDao.cartProducts(memberId, orderProduct); // 상품, 할인 정보 존재
            MemberDto memberInfo = memberDao.getMemberInfo(memberId);

            // 결제 금액 계산
            Map<String, Integer> priceMap = new HashMap<>();
            int productPrice = 0; // 상품금액
            int orderPrice = 0; // 주문금액
            int productsDiscount = 0; // 상품할인금액
//            int
            for (int i = 0; i < cartProductDtos.size(); i++) {
                CartProductDto cartProductDto = cartProductDtos.get(i);
                productPrice += cartProductDto.getCnsmr_prc();
                orderPrice += cartProductDto.getSale_prc();
                productsDiscount += (cartProductDto.getCnsmr_prc() - cartProductDto.getSale_prc());
            }

            priceMap.put("productPrice", productPrice);
            priceMap.put("orderPrice", orderPrice);
            priceMap.put("productsDiscount", productsDiscount);

            model.addAttribute("defaultAddress", defaultAddress);
            model.addAttribute("cartProductDtos", cartProductDtos);
            model.addAttribute("priceMap", priceMap);
            model.addAttribute("mbrInfo", memberInfo);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // cookie 정보 이용해서 데이터 전달하기
        return "orderForm";
    }

    // js를 통해서 일반 url이면 window.location.href = "/order/general" 구독이면  window.location.href = "/order/subscript"
    @GetMapping("/subscript")
    public String getSubscriptOrder(@SessionAttribute Long memberId, @CookieValue String orderProduct, Model model) {
        System.out.println(orderProduct);
        try {
            // session.get member id 해야한다.
            int count = cartDao.subCount(memberId);
            int updateSoldOut = cartDao.updateSoldOut(memberId);
            if (updateSoldOut > 0) {
                return "subCart";
            }

            List<CartProductDto> cartSubProducts = cartDao.subProdList(memberId);

            // 배송지는 일반 구독 나눠서 우선권이 선택된 배송지, 선택된 배송지 column 없을 시 기본 배송지 -> 지금은 너무 할게 많으니깐 그냥 배송지
//            deliveryAddressDao.choiseAddress(memberId)
            DeliveryAddressDto defaultAddress = deliveryAddressDao.defaultAddress(memberId);

            model.addAttribute("count", count);
            model.addAttribute("cartSubProducts", cartSubProducts);
            model.addAttribute("defaultAddress", defaultAddress);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // cookie 정보 이용해서 데이터 전달하기
        return "orderForm";
    }

}