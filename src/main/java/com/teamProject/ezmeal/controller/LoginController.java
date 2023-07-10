package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/login")
    public String getLogin(@RequestParam(defaultValue = "/") String redirectURL, Model model) {
        // 로그인 후 직전페이지로 가기위한 queryString을 model에 넣어서 login.jsp의 post Action에 쿼리형태로 넣기
        model.addAttribute("redirectURL", redirectURL);
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(HttpServletRequest req,
                            @RequestParam(defaultValue = "/") String redirectURL,
                            RedirectAttributes redirectAttrs,
                            String tryMbrId,
                            String tryMbrPw,
                            Model model
    ) throws Exception {
//        // checkbox가 click시 on 아닐 시, null 반환해서 nullPointException 막기 위해 검증
//        boolean exisitCookie = remember != null;
//        // cookie 설정하는 logic
//        // clicked on이면 if문 돌고 아니면 그전에 있던 cookie 제거
//        if (exisitCookie) {
//            Cookie cookie = new Cookie("id", tryMbrId);
//            res.addCookie(cookie);
//        } else {
//            Cookie[] cookies = req.getCookies();
//            for (Cookie removeCookie : cookies) {
//                if (removeCookie.getName().equals("id")) {
//                    removeCookie.setMaxAge(0);
//                    res.addCookie(removeCookie);
//                    break;
//                }
//            }
//        }
//        // rememberId cookie logic 끝
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /* 개인 정보를 jsp로 보낼 경우, 특히 redirect를 하는 경우는 조심!!!!!
         * redirect인 경우는 model로 담은 값들이 url에 그대로 나타나게 된다.
         * 따라서 session에 값을 넣어서 보내는 방법을 택해야한다.
         * */

        Long memberId = loginService.getLogin(tryMbrId, tryMbrPw);

            // login 검증
            // session도 없고 session 있는데 login 정보 없을 때 들어옴
            if (memberId == 1L) {
                redirectAttrs.addFlashAttribute("tryMbrId", tryMbrId);
                redirectAttrs.addFlashAttribute("wrongIdMsg", "id가 올바르지 않습니다.");
                return "redirect:" + "/login"; // redirect:는 get만 있다.

            } else if (memberId == 2L) {
                redirectAttrs.addFlashAttribute("tryMbrId", tryMbrId);
                redirectAttrs.addFlashAttribute("wrongPwMsg", "pw가 올바르지 않습니다.");
                return "redirect:" + "/login";

        } else {
            // login성공시 filter에서 구분할 session 넣어주기
            HttpSession session = req.getSession();
            session.setAttribute("memberId", memberId);
            model.addAttribute("checkLoginSuccess", "login success!!");
            return "redirect:" + redirectURL;
            // 이제 session에 있는 pk를 이용해서 값을 유지할 수 있다.
        }

    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // logout 기능 수행
        if (session != null) session.invalidate(); // null 이 아니면 바로 invalidate로 넘기기
        return "redirect:/";
    }

    /* Todo
     * 1. login form 받아와서 변경하기
     * 2. login pageFilter 처리하고 수행하기
     * 3. session -> 정보유지 용도, cookie -> rememberId 마무리만 잘 해주기
     * */
}