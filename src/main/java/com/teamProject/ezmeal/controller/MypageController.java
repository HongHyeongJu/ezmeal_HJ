package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.service.LoginService;
import com.teamProject.ezmeal.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/mypage")
public class MypageController {
    @Autowired
    MemberService memberService;

//    @Autowired
//    LoginService loginService;

    @GetMapping("/withdrawal")
    public String withdrawal() {
        return "withdrawal"; // 회원정보수정에서 회원탈퇴 버튼을 누르면, 회원탈퇴 페이지로 이동한다.
    }


    @PostMapping("/withdrawal")     // 회원탈퇴페이지에서 회원탈퇴확인을 누르면 main으로 돌아간다.
    public String mbrWithdrawal(HttpServletRequest req) {
//        System.out.println("AJAX 테스트");
        // service에 회원탈퇴 - 확인을 눌렀을때 로직을 적는다.
        // 회원 로그인id를 구한다.
//        String lgin_id = "scared100"; // 일단 하드코딩
        HttpSession session = req.getSession();
        Long mbr_id = (Long) session.getAttribute("memberId");
        System.out.println("mbr_id = " + mbr_id);
        try {
            // memberService.withdrawal(lgin_id)로 서비스의 탈퇴 메서드를 실행시킨다.
            memberService.withdrawal(mbr_id);
            // 탈퇴처리후에 session도 없애야하지 않나?? 그러면 Logout처리를 할것인가? 그냥 session없애는 코드?
            if (session != null) session.invalidate();
            return "index"; // withdrawal.jsp의 ajax가 경로처리
        } catch (Exception e) {
            e.printStackTrace();
            return "withdrawal";
        }

//        return "index"; // withdrawal.jsp의 ajax가 경로처리함
    }

    @GetMapping("/modify")  // 회원정보수정 페이지
    public String modify(){
        return "modify";
    }
}
