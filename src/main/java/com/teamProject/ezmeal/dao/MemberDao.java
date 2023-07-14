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

    public String selectLoginId(String lgin_id)  {
        return session.selectOne(namespace+"lgin_id", lgin_id);
    }

    public String selectPassword(String loginId)  {
        return session.selectOne(namespace+"lgin_pw", loginId);
    }

    public String selectEmail(String email)  {
        return session.selectOne(namespace + "email", email);
    }

    public Long selectMemberId(String loginId)  {  // mbr_id(회원번호) 조회
        return session.selectOne(namespace+"mbr_id", loginId);
    }

    public int insertMember(MemberDto memberDto)   {    // 회원가입 resisterMember
        return session.insert(namespace + "mbr_signup", memberDto);
    }
    public MemberDto selectMemberInfo(Long memberId) {    // 회원정보 조회
        return session.selectOne(namespace + "mbr_Info", memberId);
    }

    // update이지만 del_yn을 'Y'로 변경하므로 실질적으로 삭제라고 본다.
    public int deleteMember(Long mbr_id)  {    // 회원탈퇴 removeMember
        return session.update(namespace + "mbr_withdrawal", mbr_id);
    }

    public int updateMemeber(MemberDto memberDto)  {    // 회원정보수정   updateMember
        return session.update(namespace + "mbr_modify", memberDto);
    }
}