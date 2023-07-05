package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.domain.MemberDto;
import com.teamProject.ezmeal.service.LoginService;
import com.teamProject.ezmeal.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    MemberService memberService;

    @Autowired
    LoginService loginService;

    @GetMapping("/signup")
    public String signUp() {
        return "signup";
    }
//    servlet-context.xml에서 아래 한줄로 GetMapping 대체 가능? - Get요청만 가능
//    <view-controller path="/member/signup" view-name="signup"/>

    @PostMapping("/signup/success")
    public String signupSuccess(MemberDto memberDto, Model model, RedirectAttributes rattr, HttpServletRequest req) {
        // 1. 유효성 검사
//        if (!isValid(memberDto)) {
//            String msg = URLEncoder.encode("id를 잘못 입력하셨습니다.","utf-8");
//
//            m.addAttribute("msg",msg);
//            return "redirect:/member/signup";
////            return "redirect:/member/signup?msg="+msg;
//        }
        // 2. DB에 신규회원 정보를 저장
        try {
            int rowCnt = memberService.signup(memberDto);    // insert

            if (rowCnt!=1)  // insert가 되지않았을 때 예외발생을 해서 signup페이지로 가도록 함
                throw new Exception("Signup failed");

            rattr.addFlashAttribute("msg","Signup_OK");
            // DB에 회원정보가 저장이 되고 동시에 로그인까지 되게 하려면,
            // session에 가입한 로그인 정보를 넣어줘야함.
//            System.out.println("lgin_id = " + memberDto.getLgin_id());
//            System.out.println("lgin_pw = " + memberDto.getLgin_pw());
            String lgin_id = memberDto.getLgin_id();
            String lgin_pw = memberDto.getLgin_pw();
            Long memberId = loginService.getLogin(lgin_id,lgin_pw);
            System.out.println("memberId = " + memberId);
            HttpSession session = req.getSession();
            session.setAttribute("memberId",memberId);
            model.addAttribute("checkSignupSuccess", "signup success!!");

            return "signupSuccess"; // insert 성공시에 signupSuccess 페이지로 감
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute(memberDto);
            model.addAttribute("msg","Signup_ERR");

            return "signup";    // 예외처리 발생시 signup(회원가입)페이지로 돌아감
        }
    }

//    private boolean isValid(MemberDto memberDto) {
//        return false;
//    }



}
