package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.dao.CartDao;
import com.teamProject.ezmeal.dao.DeliveryAddressDao;
import com.teamProject.ezmeal.domain.CartProductDto;
import com.teamProject.ezmeal.domain.DeliveryAddressDto;
import com.teamProject.ezmeal.service.CartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartDao cartDao;
    private final CartServiceImpl cartService;
    private final DeliveryAddressDao deliveryAddressDao;


    @GetMapping("/general")
    public String subscript(@SessionAttribute(value = "memberId", required = false) Long memberId, Model model) {
            Long mbrId = (memberId != null) ? memberId : 0L; // 일단 비회원일 경우에도 에러없이 장바구니 들어갈 수 있도록 열어둠 -> null 인 경우 바로 view로 return하도록 하는 방안 생각 필요
        try {
            int count = cartDao.count(mbrId);
            // 품절 상태 업데이트
            cartDao.updateSoldOut(mbrId);

            List<CartProductDto> cartColdProducts = cartService.getColdProduct(mbrId);
            List<CartProductDto> cartIceProducts = cartService.getIceProduct(mbrId);
            List<CartProductDto> cartOutSideProducts = cartService.getOutSideProduct(mbrId);

            DeliveryAddressDto defaultAddress = deliveryAddressDao.defaultAddress(mbrId);

            model.addAttribute("loginYN", mbrId);
            model.addAttribute("count", count);
            model.addAttribute("cartColdProducts", cartColdProducts);
            model.addAttribute("cartIceProducts", cartIceProducts);
            model.addAttribute("cartOutSideProducts", cartOutSideProducts);
            model.addAttribute("defaultAddress", defaultAddress);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "cart";
    }
    @GetMapping("/subscript")
    public String general(@SessionAttribute(value = "memberId", required = false) Long memberId, Model model) {
        Long mbrId = (memberId != null) ? memberId : 0L;
        try{
            // session.get member id 해야한다.
            int count = cartDao.subCount(mbrId);
            cartDao.updateSoldOut(mbrId);

            List<CartProductDto> cartSubProducts = cartDao.subProdList(mbrId);
            DeliveryAddressDto defaultAddress = deliveryAddressDao.defaultAddress(mbrId);

            model.addAttribute("count", count);
            model.addAttribute("cartSubProducts", cartSubProducts);
            model.addAttribute("defaultAddress", defaultAddress);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return "subCart";
    }
}
