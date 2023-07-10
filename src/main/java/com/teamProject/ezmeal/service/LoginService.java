package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.MemberDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberDao memberDao;

    public boolean loginCheck(String loginId, String loginPw) throws Exception {
        String id = memberDao.getLoginId(loginId);  // 로그인시 입력한 loginId로 가입된 loginId를 얻어온다
        String password = memberDao.getPassword(loginId);   // 로그인시 입력한 loginPw로 가입된 password를 얻는다
//        System.out.println("loginId = " + loginId);
//        System.out.println("loginPw = " + loginPw);
//        System.out.println("id = " + id);
//        System.out.println("password = " + password);
        return loginId.equals(id) && loginPw.equals(password);  // 로그인 Id,pw 일치시 true 반환
    }

    public Long loginInfo(String loginId) throws Exception {
        return memberDao.getMemberId(loginId);
        // controller에서는 다시 Long type으로 변경 필요
    }
}
