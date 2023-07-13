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

    public String getLoginId(String lgin_id) throws Exception {
        return session.selectOne(namespace+"lgin_id", lgin_id);
    }

    public String getPassword(String loginId) throws Exception {
        return session.selectOne(namespace+"lgin_pw", loginId);
    }

    public Long getMemberId(String loginId) throws Exception {  // mbr_id(회원번호) 조회
        return session.selectOne(namespace+"mbr_id", loginId);
    }

    public int registerMember(MemberDto memberDto) throws Exception {    // 회원가입 resisterMember
        return session.insert(namespace + "mbr_signup", memberDto);
    }
    public MemberDto getMemberInfo(Long memberId) {    // 회원정보 조회
        return session.selectOne(namespace + "mbr_Info", memberId);
    }

    // update이지만 del_yn을 'Y'로 변경하므로 실질적으로 삭제라고 본다.
    public int mbrWithdrawal(Long mbr_id) throws Exception {    // 회원탈퇴 removeMember
        return session.update(namespace + "mbr_withdrawal", mbr_id);
    }

    public int mbrModify(MemberDto memberDto) throws Exception {    // 회원정보수정   updateMember
        return session.update(namespace + "mbr_modify", memberDto);
    }
}