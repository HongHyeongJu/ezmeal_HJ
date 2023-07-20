package com.teamProject.ezmeal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orderDetail")
public class OrderDetailController {
    @GetMapping("/{orderNumber}")
    public String getOrderPayment(){
        return "orderDetail";
    }
}

