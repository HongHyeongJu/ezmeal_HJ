package com.teamProject.ezmeal.controller;


import com.teamProject.ezmeal.domain.AdminMemberDto;
import com.teamProject.ezmeal.service.AdminLoginService;
import com.teamProject.ezmeal.service.AdminMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminLoginController {

    private final AdminLoginService adminLoginService;
    private final AdminMemberService adminMemberService;

    @GetMapping("/login")
    public String getLogin(String redirectURL, Model model) {
        model.addAttribute("redirectURL", redirectURL);
        return "adminLogin";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "adminLogin";
    }

    @PostMapping("/login")
    public String postLogin(HttpServletRequest request, String id) {
        int empId = adminLoginService.getEmpId(id);
        AdminMemberDto loginAdminInfo = adminMemberService.getLoginAdminInfo(empId);

        HttpSession session = request.getSession();
        session.setAttribute("empId", empId);
        session.setAttribute("loginAdminInfo", loginAdminInfo);
        return "admin_home";
    }

}
