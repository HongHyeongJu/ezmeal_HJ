package com.teamProject.ezmeal.controller;


import com.teamProject.ezmeal.domain.MemberDto;
import com.teamProject.ezmeal.service.AdminMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AdminMemberController {

    private final AdminMemberService adminMemberService;


    // 회원대시보드 페이지 조회
    @GetMapping("/admin/member")
    public String  getAdminMemberMain() {
        return "admin_member_main";
    }

    // 회원정보조회 페이지 조회
    @GetMapping("/admin/member/info")
    public String getMemberInfo() {
        return "admin_member_info";
    }

    // 회원 전체 목록 조회
    @GetMapping("/admin/memberList")
    @ResponseBody
    public List<Map<String, Object>> getMemberList() {
        return adminMemberService.getMemberList();
    }


    // 조건에 맞는 회원정보 조회
    @GetMapping("/admin/members")
    @ResponseBody
    public List<Map<String, Object>> getMembers(@RequestParam String option, String value) {
        // jsp에서 ajax요청으로 넘어온 인자들을 map에 담는다.
        // map에 담아서 조회를 한다.
        System.out.println("option = " + option);
        System.out.println("value = " + value);
        Map<String, String> memberMap = new HashMap<>();

        if (option.equals("id")) {  // id를 기준으로 검색할 때
            String id = value;
            memberMap.put("id", id);
        } else if (option.equals("name")) { // 이름을 기준으로 검색할때
            String name = value;
            memberMap.put("name", name);
        } else if (option.equals("email")) { // 이메일을 기준으로 검색
            String email = value;
            memberMap.put("email", email);
        } else if (option.equals("phone")) { // 휴대폰번호를 기준으로 검색
            String phone = value;
            memberMap.put("phone", phone);
        }



        List<Map<String, Object>> memberList = adminMemberService.getMembers(memberMap);
        System.out.println("membersList.get(0) = " + memberList.get(0));
        return memberList;
    }

}
