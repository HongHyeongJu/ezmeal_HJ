package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.domain.AdminMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin")
public class AdminHomeController {

    @GetMapping
    public String adminHome(@SessionAttribute AdminMemberDto loginAdminInfo) {
        System.out.println("-------------- adminHome ---------------" );
        System.out.println("session = " + loginAdminInfo);
//        Object empId = session.getAttribute("empId");
//        System.out.println("empId = " + empId);
//        Object loginAdminInfo = session.getAttribute("loginAdminInfo");
//        System.out.println("loginAdminInfo = " + loginAdminInfo);
        return "admin_home";
    }
}
