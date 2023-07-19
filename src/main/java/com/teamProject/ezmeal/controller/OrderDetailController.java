package com.teamProject.ezmeal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderDetailController {
    @GetMapping("/orderDetail")
    public String getOrderPayment(){
        return "orderDetail";
    }
}

