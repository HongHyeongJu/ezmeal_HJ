package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.dao.DeliveryAddressDao;
import com.teamProject.ezmeal.domain.DeliveryAddressDto;
import com.teamProject.ezmeal.service.DeliveryAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/address")
public class DeliveryAddressController {
    private final DeliveryAddressService deliveryAddressService;
    private final DeliveryAddressDao deliveryAddressDao;

    @GetMapping
    public String addressList(@SessionAttribute() Model model) throws Exception {
        List<DeliveryAddressDto> addressList = deliveryAddressDao.addressList(10001L);
        model.addAttribute("addressList",addressList);
        return "addressList";
    }
}
