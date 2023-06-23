package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.dao.CartDao;
import com.teamProject.ezmeal.dao.DeliveryAddressDao;
import com.teamProject.ezmeal.domain.CartProductDto;
import com.teamProject.ezmeal.domain.DeliveryAddressDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartDao cartDao;
    private final DeliveryAddressDao deliveryAddressDao;


    @GetMapping("/cart")
    public String cart(Model model) {
        try {
            // session.get member id 해야한다.
            // 임시로 1001을 직접 작성한다.
            int mbrId = 1001;
            int count = cartDao.count(mbrId);
            List<CartProductDto> cartProducts = cartDao.prodList(mbrId);
            DeliveryAddressDto defaultAddress = deliveryAddressDao.defaultAddress(mbrId);

            model.addAttribute("count", count);
            model.addAttribute("cartProducts", cartProducts);
            model.addAttribute("defaultAddress", defaultAddress);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "cart";
    }
}
