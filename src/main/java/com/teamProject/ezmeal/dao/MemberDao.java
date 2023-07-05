package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.MemberDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberDao {
    private final SqlSession session;
    private static final String namespace = "tb_member.";

    public String getPassword(String loginId) throws Exception {
        return session.selectOne(namespace+"lgin_pw", loginId);
    }

    public Long getMemberId(String loginId) throws Exception {
        return session.selectOne(namespace+"mbr_id", loginId);
    }

    public int mbrSignup(MemberDto memberDto) throws Exception {
        return session.insert(namespace + "mbr_signup", memberDto);
    }
    public MemberDto getMemberInfo(Long memberId) throws Exception {
        return session.selectOne(namespace + "mbr_Info", memberId);
    }

    // update이지만 del_yn을 'Y'로 변경하므로 실질적으로 삭제라고 본다.
    public int mbrWithdrawal(Long mbr_id) throws Exception {
        return session.update(namespace+"mbr_withdrawal", mbr_id);
    }
}