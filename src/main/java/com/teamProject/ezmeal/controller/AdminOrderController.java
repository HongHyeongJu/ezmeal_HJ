package com.teamProject.ezmeal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/order")
public class AdminOrderController {
    @GetMapping("/before/management")
    public String beforeManagement(){
        return "admin_order_before_management";
    }
}
