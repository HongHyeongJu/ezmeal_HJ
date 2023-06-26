package com.teamProject.ezmeal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {
    @GetMapping
    public String addressList(){
        return "addressList";
    }
}
