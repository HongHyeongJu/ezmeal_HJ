package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.domain.MemberDto;
import com.teamProject.ezmeal.service.LoginService;
import com.teamProject.ezmeal.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;
    private final LoginService loginService;

    @GetMapping("/login")
    public String getLogin(@RequestParam(defaultValue = "/") String redirectURL, Model model) {
        // 로그인 후 직전페이지로 가기위한 queryString을 model에 넣어서 login.jsp의 post Action에 쿼리형태로 넣기
        model.addAttribute("redirectURL", redirectURL);
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // logout 기능 수행
//        if (session != null) session.invalidate(); // null 이 아니면 바로 invalidate로 넘기기
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/login")
    public String postLogin(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(defaultValue = "/") String redirectURL,
                            RedirectAttributes redirectAttrs, Model model,
                            String loginId, String loginPw, boolean remember
    ) throws Exception {
//        // checkbox가 click시 on 아닐 시, null 반환해서 nullPointException 막기 위해 검증

//        // clicked on이면 if문 돌고 아니면 그전에 있던 cookie 제거
        /* 개인 정보를 jsp로 보낼 경우, 특히 redirect를 하는 경우는 조심!!!!!
         * redirect인 경우는 model로 담은 값들이 url에 그대로 나타나게 된다.
         * 따라서 session에 값을 넣어서 보내는 방법을 택해야한다.
         * */
        boolean loginCheck = loginService.loginCheck(loginId,loginPw);
        Long memberId = loginService.loginInfo(loginId);

        System.out.println("remember = " + remember);
        if (remember) {
            // ID기억하기 - 쿠키생성
            Cookie cookie = new Cookie("id", loginId);
            response.addCookie(cookie);
        } else {
            // ID기억하기 체크해제 - 쿠키삭제
            Cookie removeCookie = new Cookie("id",loginId);
            removeCookie.setMaxAge(0);
            response.addCookie(removeCookie);
        }
        // login 검증
        // session도 없고 session 있는데 login 정보 없을 때 들어옴

        // id, pwd가 일치하지 않으면
        if (!loginCheck) {
            redirectAttrs.addFlashAttribute("loginId", loginId);
            redirectAttrs.addFlashAttribute("loginFailMsg", "아이디, 비밀번호를 확인해주세요.");
            return "redirect:" + "/login"; // redirect:는 get만 있다.

        }
        // login성공시 filter에서 구분할 session 넣어주기
        // id와 pwd가 일치하면
        HttpSession session = request.getSession();
        session.setAttribute("memberId", memberId);
        MemberDto loginMbrInfo = memberService.mbrInfo(memberId);
        session.setAttribute("loginMbrInfo",loginMbrInfo);
//        model.addAttribute("checkLoginSuccess", "login success!!");
//        redirectAttrs.addFlashAttribute("loginMbrInfo",loginMbrInfo);
        return "redirect:" + redirectURL;
        // 이제 session에 있는 pk를 이용해서 값을 유지할 수 있다.
    }

    /* Todo
     * 1. login form 받아와서 변경하기
     * 2. login pageFilter 처리하고 수행하기
     * 3. session -> 정보유지 용도, cookie -> rememberId 마무리만 잘 해주기
     * */
}