package com.teamProject.ezmeal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderPaymentController {
    @GetMapping("/orderPayment")
    public String getOrderPayment(){
        return "orderPayment";
    }
}
