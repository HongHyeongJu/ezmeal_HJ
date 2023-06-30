package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.domain.MemberDto;
import com.teamProject.ezmeal.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

@Controller
@RequestMapping("/member")
public class SignupController {
    @Autowired
    MemberService memberService;

    @GetMapping("/signup")
    public String signUp() {
        return "signup";
    }
//    servlet-context.xml에서 아래 한줄로 GetMapping 대체 가능? - Get요청만 가능
//    <view-controller path="/member/signup" view-name="signup"/>

    @PostMapping("/signup/success")
    public String signupSuccess(MemberDto memberDto, Model m, HttpSession session, RedirectAttributes rattr) {
        // 1. 유효성 검사
//        if (!isValid(memberDto)) {
//            String msg = URLEncoder.encode("id를 잘못 입력하셨습니다.","utf-8");
//
//            m.addAttribute("msg",msg);
//            return "redirect:/member/signup";
//        }
        // 2. DB에 신규회원 정보를 저장
        try {
            int rowCnt = memberService.signup(memberDto);    // insert

            if (rowCnt!=1)  // insert가 되지않았을 때 예외발생을 해서 signup페이지로 가도록 함
                throw new Exception("Signup failed");

            rattr.addFlashAttribute("msg","Signup_OK");
            return "signupSuccess"; // insert 성공시에 signupSuccess 페이지로 감
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute(memberDto);
            m.addAttribute("msg","Signup_ERR");

            return "signup";    // 예외처리 발생시 signup(회원가입)페이지로 돌아감
        }
//        return "signupSuccess";
    }

//    private boolean isValid(MemberDto memberDto) {
//        return true;
//    }

}
