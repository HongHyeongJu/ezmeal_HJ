package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.dao.DeliveryAddressDao;
import com.teamProject.ezmeal.domain.DeliveryAddressDto;
import com.teamProject.ezmeal.service.DeliveryAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/address")
public class DeliveryAddressController {
    private final DeliveryAddressService deliveryAddressService;
    private final DeliveryAddressDao deliveryAddressDao;

    // 목록
    @GetMapping
    public String addressList(@SessionAttribute(value = "memberId", required = false) Long memberId, Model model) throws Exception {
        if (memberId == null) {
            return "login";
        } else {
            List<DeliveryAddressDto> addressList = deliveryAddressDao.addressList(memberId);
            model.addAttribute("addressList", addressList);
            return "deliveryAddressList";
        }
    }

    // 등록
    @GetMapping("/add")
    public String getAddAddress() {
        return "deliveryAddressForm";
    }

    @PostMapping("/add")
    public String postAddAddress(@SessionAttribute(value = "memberId", required = false) Long memberId,
                                 @ModelAttribute DeliveryAddressDto deliveryAddressDto) throws Exception {
        // PS. filter 할거라서 session == null일 경우 login으로 가는 거 일단은 작성 안함
        // 1. mbr_id, in_id, up_id 직접 넣어주기.

        deliveryAddressDto.setMbr_id(memberId);
        deliveryAddressDto.setIn_id("ezmeal");

        // 2. checkbox y,n 로 converting 해주기
        String basicYn = deliveryAddressDto.getBasic_yn();
        convertCheckBoxYN(deliveryAddressDto, basicYn); // checkbox null, on -> y/n로 변경해주는 method + 기존 y는 n으로 변경

        deliveryAddressDao.insertDeliveryAddress(deliveryAddressDto);
        return "redirect:/address";
    }

    // 수정
    @GetMapping("/modify")
    public String getModifyAddress(Long addrId, Model model) throws Exception {
        // PS. filter 할거라서 session == null일 경우 login으로 가는 거 일단은 작성 안함
        // 1. 배송지 pk를 받아오기(input hidden 설정) -> 해당 객체 가지고 오기
        // System.out.println("addrId = " + addrId);
        DeliveryAddressDto deliveryAddressDto = deliveryAddressDao.selectedAddress(addrId);
        model.addAttribute("previousAddress", deliveryAddressDto); // previousAddress가 있는 경우 title 수정 및 input에 값 넣기
        return "deliveryAddressForm";
    }

    @PostMapping("/modify")
    public String postModifyAddress(@SessionAttribute(value = "memberId", required = false) Long memberId,
                                    @ModelAttribute DeliveryAddressDto deliveryAddressDto) throws Exception {
        /* TODO
         *  add_id는 jsp에서 hidden 값으로 넘어간다.
         *  -> 조작하는 고객 있으면 롤백시키는 로직 필요
         */

        // mbr_id, basic_yn - 추가 필요
        // system column은 추가시에만 설정해주고 이후는 sql문에서 작동하도록 수행
        // System.out.println("deliveryAddressDto = " + deliveryAddressDto);
        deliveryAddressDto.setMbr_id(memberId);

        // checkbox null, on -> y/n로 변경해주는 method
        String basicYn = deliveryAddressDto.getBasic_yn();
        convertCheckBoxYN(deliveryAddressDto, basicYn);


        // System.out.println("deliveryAddressDto = " + deliveryAddressDto);

        deliveryAddressDao.updateDeliveryAddress(deliveryAddressDto);
        return "redirect:/address";
    }

    @PostMapping("/delete")
    public String deleteAddress(Long addrId) throws Exception {
        deliveryAddressDao.deleteDeliveryAddress(addrId);
        return "redirect:/address";
    }

    // checkBox converting Method
    private void convertCheckBoxYN(DeliveryAddressDto deliveryAddressDto, String basicYn) throws Exception {
        if (basicYn != null) {
            deliveryAddressDao.resetAllBasicYNtoN(deliveryAddressDto);
            deliveryAddressDto.setBasic_yn("y");
        } else deliveryAddressDto.setBasic_yn("n");
    }

}
