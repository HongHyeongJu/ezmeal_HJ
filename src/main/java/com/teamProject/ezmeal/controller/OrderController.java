package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.dao.CartDao;
import com.teamProject.ezmeal.dao.DeliveryAddressDao;
import com.teamProject.ezmeal.domain.CartProductDto;
import com.teamProject.ezmeal.domain.DeliveryAddressDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final CartDao cartDao;
    private final DeliveryAddressDao deliveryAddressDao;

    @GetMapping("/general")
    public String getGeneralOrder(@SessionAttribute Long memberId, @CookieValue String orderProduct, Model model) {
        try{
            // session.get member id 해야한다.
            int count = cartDao.subCount(memberId);
            int updateSoldOut = cartDao.updateSoldOut(memberId);
            if (updateSoldOut > 0){
                return "cart";
            }

            List<CartProductDto> cartSubProducts = cartDao.subProdList(memberId);

            // 배송지는 일반 구독 나눠서 우선권이 선택된 배송지, 선택된 배송지 column 없을 시 기본 배송지 -> 지금은 너무 할게 많으니깐 그냥 배송지
//            deliveryAddressDao.choiseAddress(memberId)
            DeliveryAddressDto defaultAddress = deliveryAddressDao.defaultAddress(memberId);

            model.addAttribute("count", count);
            model.addAttribute("cartSubProducts", cartSubProducts);
            model.addAttribute("defaultAddress", defaultAddress);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        // cookie 정보 이용해서 데이터 전달하기
        return "orderForm";
    }

    // js를 통해서 일반 url이면 window.location.href = "/order/general" 구독이면  window.location.href = "/order/subscript"
    @GetMapping("/subscript")
    public String getSubscriptOrder(@SessionAttribute Long memberId, @CookieValue String orderProduct, Model model) {
        System.out.println(orderProduct);
        try{
            // session.get member id 해야한다.
            int count = cartDao.subCount(memberId);
            int updateSoldOut = cartDao.updateSoldOut(memberId);
            if (updateSoldOut > 0){
                return "subCart";
            }

            List<CartProductDto> cartSubProducts = cartDao.subProdList(memberId);

            // 배송지는 일반 구독 나눠서 우선권이 선택된 배송지, 선택된 배송지 column 없을 시 기본 배송지 -> 지금은 너무 할게 많으니깐 그냥 배송지
//            deliveryAddressDao.choiseAddress(memberId)
            DeliveryAddressDto defaultAddress = deliveryAddressDao.defaultAddress(memberId);

            model.addAttribute("count", count);
            model.addAttribute("cartSubProducts", cartSubProducts);
            model.addAttribute("defaultAddress", defaultAddress);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        // cookie 정보 이용해서 데이터 전달하기
        return "orderForm";
    }

}