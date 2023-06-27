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

    public Long getLogin(String tryMbrId, String tryMbrPw) throws Exception {
        // controller로 부터 받은 id, pw 정보
        // 1. id를 먼저 check -> 존재시, pw 비교 / 없을 시, 다시 login 창으로
        String checkLogin = memberDao.getPassword(tryMbrId);
        if (checkLogin == null){
            // 다시 controller로 돌려보낸다.
            return 1L; // "id가 올바르지 않습니다."
        }else if(!checkLogin.equals(tryMbrPw)) {
            // password 비교 dao 수행
            return 2L; // "pw가 올바르지 않습니다."
        }else {
            // 다 맞으면 해당 pk 넘겨준다.
            return memberDao.getMemberId(tryMbrId);
             // controller에서는 다시 Long type으로 변경 필요
        }
    }
}
