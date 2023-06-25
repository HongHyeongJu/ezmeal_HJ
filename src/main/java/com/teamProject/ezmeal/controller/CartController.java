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

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartDao cartDao;
    private final CartServiceImpl cartService;
    private final DeliveryAddressDao deliveryAddressDao;


    @GetMapping("/general")
    public String subscript(Model model) {
        try {
            // session.get member id 해야한다.
            // 임시로 1001을 직접 작성한다.
            int mbrId = 1001;
            int count = cartDao.count(mbrId);
            // 품절 상태 업데이트
            cartDao.updateSoldOut(1001);

            List<CartProductDto> cartColdProducts = cartService.getColdProduct(mbrId);
            List<CartProductDto> cartIceProducts = cartService.getIceProduct(mbrId);
            List<CartProductDto> cartOutSideProducts = cartService.getOutSideProduct(mbrId);

            DeliveryAddressDto defaultAddress = deliveryAddressDao.defaultAddress(mbrId);

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
    public String general(Model model) {
        try{
            // session.get member id 해야한다.
            // 임시로 1001을 직접 작성한다.
            int mbrId = 1001;
            int count = cartDao.subCount(mbrId);
            cartDao.updateSoldOut(1001);

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
