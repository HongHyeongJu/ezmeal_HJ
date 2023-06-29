package com.teamProject.ezmeal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    @GetMapping("/orderView")
    public String getOrder(@CookieValue(name = "orderProduct") String orderProduct, HttpServletResponse res) {
        System.out.println("orderView controller!!");
        System.out.println(orderProduct);
        return "orderForm";
    }
    @ResponseBody
    @PostMapping("/insert")
    public String postOrder(HttpServletResponse res, @RequestBody String stringData){
        // 1. 장바구니 코드를 받아옴
        String selectedCartProductCode = stringData.substring(0, stringData.length() - 1);
        // 2. cookie에 담기-> js에서 해야함
        return selectedCartProductCode;
    }

}
