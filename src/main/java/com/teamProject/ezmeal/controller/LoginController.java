package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.dao.MemberDao;
import com.teamProject.ezmeal.domain.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
import java.net.URLEncoder;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    MemberDao memberDao;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 1. 세션을 종료
        session.invalidate();
        // 2. 홈으로 이동
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(String lgin_id, String lgin_pw, String toURL, boolean rememberId,
                        HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("id = " + lgin_id);
        System.out.println("pwd = " + lgin_pw);

        // 1. lgin_id와 lgin_pw를 확인
        if (!loginCheck(lgin_id, lgin_pw)) {
            // 2-1   일치하지 않으면, loginForm으로 이동
            String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.", "utf-8");

            return "redirect:/login?msg=" + msg;
        }
        // 2-2. lgin_id와 lgin_pw가 일치하면,
        //  세션 객체를 얻어오기
        HttpSession session = request.getSession();
        //  세션 객체에 lgin_id를 저장
        session.setAttribute("lgin_id", lgin_id);

        if (rememberId) {
            //     1. 쿠키를 생성
            Cookie cookie = new Cookie("lgin_id", lgin_id); // ctrl+shift+o 자동 import
//		       2. 응답에 저장
            response.addCookie(cookie);
        } else {
            // 1. 쿠키를 삭제
            Cookie cookie = new Cookie("lgin_id", lgin_id); // ctrl+shift+o 자동 import
            cookie.setMaxAge(0); // 쿠키를 삭제
//		       2. 응답에 저장
            response.addCookie(cookie);
        }
//		       3. 홈으로 이동
        toURL = toURL == null || toURL.equals("") ? "/" : toURL;

        return "redirect:" + toURL;
    }

    private boolean loginCheck(String lgin_id, String lgin_pw) {
        MemberDto memberDto = null;

        try {
            memberDto = memberDao.selectMember(lgin_id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return memberDto != null && memberDto.getLgin_pw().equals(lgin_pw);
//        return "asdf".equals(lgin_id) && "1234".equals(pwd);
    }
}