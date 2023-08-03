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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {

    private final MemberService memberService;


    @GetMapping("/withdrawal")
    public String getMemberRemove() {
        return "withdrawal"; // 회원정보수정에서 회원탈퇴 버튼을 누르면, 회원탈퇴 페이지로 이동한다.
    }

    @PostMapping("/withdrawal")     // 회원탈퇴페이지에서 회원탈퇴확인을 누르면 main으로 돌아간다.
    public String postMemberRemove(HttpSession session, Model model) {
        // 회원 로그인id를 구한다.
//        String lgin_id = "scared100"; // 일단 하드코딩

        Long mbr_id = (Long) session.getAttribute("memberId");
        System.out.println("mbr_id = " + mbr_id);
        try {
            // session의 memberId로 현재 로그인중인 회원번호에 해당하는 회원의 탈퇴를 진행
            memberService.removeMember(mbr_id);
            // 탈퇴처리후에 session도 없애야하지 않나??, logout = session.invalidate
            if (session != null) session.invalidate();
            return "index"; // withdrawal.jsp의 ajax가 경로처리

        }        catch (Exception e) {
            e.printStackTrace();
            return "redirect:/mypage/withdrawal";
        }

//        return "index"; // withdrawal.jsp의 ajax가 경로처리함
    }

    @GetMapping("/main")
    public String mypage() {
        return "mypage";
    }

    @GetMapping("/modify")  // 회원정보수정 페이지
    public String getMemberModify(HttpSession session, Model model){
//        HttpSession session = req.getSession();
        Long memberId = (Long) session.getAttribute("memberId");    // 현재로그인 중인 회원번호를 가져온다.
        try {
            MemberDto loginMbrInfo = memberService.getMemberInfo(memberId);   // 현재 로그인중인 회원정보를 조회한다.
            model.addAttribute("loginMbrInfo",loginMbrInfo);    // JSP에서 loginMbrInfo 객체를 불러오기위해 모델에 담아서 넘겨준다.
            return "modify";
        } catch (Exception e) {
            return "mypage";
        }
    }

    @PostMapping("/modify") // 회원정보수정이 완료되면 마이페이지로 돌아감
    public String postMemberModify(@SessionAttribute Long memberId, RedirectAttributes rattr,
                                   Model model, MemberDto memberDto, String originPw, String newPw) {
        try {

            // 회원수정 form에서 입력한 현재 비밀번호(orginPw)가
            // 로그인한 회원의 현 비밀번호(lgin_pw)와 일치하는지 확인
            MemberDto loginMember = memberService.getMemberInfo(memberId);

            if (!(loginMember.getLgin_pw().equals(originPw))) {
                rattr.addFlashAttribute("msg","현재 비밀번호를 확인해주세요.");
                return "redirect:/mypage/modify";
            } else {
                // 회원수정에서 새 비밀번호를 입력한 값으로 변경한다.
                memberDto.setLgin_pw(newPw);
                memberService.modifyMember(memberDto);
                model.addAttribute("modifyMsg","수정되었습니다.");
//                rattr.addFlashAttribute("modifyMsg","수정되었습니다.");
                return "mypage";
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

