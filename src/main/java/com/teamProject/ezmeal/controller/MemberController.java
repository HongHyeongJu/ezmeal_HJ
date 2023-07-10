package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.domain.MemberDto;
import com.teamProject.ezmeal.service.LoginService;
import com.teamProject.ezmeal.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    MemberService memberService;

    @Autowired
    LoginService loginService;


    @PostMapping("/checkIdDuplicate")
    @ResponseBody
    public Map<String, Boolean> checkIdDuplicate(@RequestBody Map<String, String> request) {    // id중복체크 post JSON
        String id = request.get("id");  // input에 입력된 id의 value값 받아옴
        try {
            boolean isDuplicate = memberService.checkIdDuplicate(id);   // 기존 id가 존재하면 true
            Map<String, Boolean> response = new HashMap<>();
            response.put("isDuplicate", isDuplicate);   // map에 id를 조회한 결과를 담는다
            return response;    // JSON 응답을 보낸다.
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/signup")
    public String signUp() {    // 회원가입 버튼 클릭시 signup.jsp 화면 보여준다
        return "signup";
    }
    // PostMapping(/signup)
    // View success.jsp
    @PostMapping("/signup/success")
    public String signupSuccess(MemberDto memberDto, String lgin_id, String lgin_pw ,Model model, RedirectAttributes rattr, HttpServletRequest req) {
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
            // 회원가입 눌렀을때도 아이디가 존재하는지 점검
//            if (lgin_id == )
            int rowCnt = memberService.signup(memberDto);    // insert

            if (rowCnt!=1) {  // insert가 되지않았을 때 signup페이지로 가도록 함
                model.addAttribute(memberDto);
                model.addAttribute("msg","회원가입이 되지 않았습니다. 다시 시도해주세요");
                return "forward:/member/signup";
            }
            rattr.addFlashAttribute("msg","Signup_OK");
            // DB에 회원정보가 저장이 되고 동시에 로그인까지 되게 하려면,
            // memberId를 세션에 담는다.
            Long memberId = loginService.getLogin(lgin_id,lgin_pw);
            System.out.println("memberId = " + memberId);
            HttpSession session = req.getSession();
            session.setAttribute("memberId",memberId);
            model.addAttribute("checkSignupSuccess", "signup success!!");

            return "signupSuccess"; // insert 성공시에 signupSuccess 페이지로 감
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute(memberDto);
            model.addAttribute("msg","아이디가 이미 존재합니다. 회원가입을 다시 해주세요");

            return "forward:/member/signup";    // 예외처리 발생시 signup(회원가입)페이지로 돌아감
        }
    }

    @PostMapping("/signup") // 회원가입 예외처리 발생후 다시 회원가입페이지를 보여줌
    public String reSignUp() { // model에 담은 내용을 jsp에서 보여주기 위해 forward함.
        return "signup";
    }

//    private boolean isValid(MemberDto memberDto) {
//        return false;
//    }



}
