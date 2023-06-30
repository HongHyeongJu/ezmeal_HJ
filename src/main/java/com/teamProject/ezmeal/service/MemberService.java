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
        return memberDao.mbrsignup(memberDto);
    }
}
