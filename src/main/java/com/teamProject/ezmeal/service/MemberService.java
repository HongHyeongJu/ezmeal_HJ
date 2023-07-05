package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.MemberDao;
import com.teamProject.ezmeal.domain.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    MemberDao memberDao;

    public int signup(MemberDto memberDto) throws Exception {
        return memberDao.mbrSignup(memberDto);
    }

    public int withdrawal(Long mbr_id) throws Exception {   // 회원탈퇴하는 메서드 - UPDATE 문
        // 컨트롤러에서 로그인한 id를 구하고  memberservice.withdrawal(lgin_id)를 하면
        // 해당 로그인id로 memberDao.getMemberId(로그인id)로 mbr_id를 구한다.
//        long mbr_id = memberDao.getMemberId(lgin_id);
        // 그러면 mbr_id를 구한것을 return값에 넣으면? 되지않을까?
        return memberDao.mbrWithdrawal(mbr_id);
    }


}
