package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.MemberDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class MemberDao {
    private final SqlSession session;
    private static final String namespace = "tb_member.";

    public String selectLoginId(String loginId)  {
        return session.selectOne(namespace+"selectLoginId", loginId);
    }

    public String selectPassword(String loginId)  {
        return session.selectOne(namespace+"selectLoginPw", loginId);
    }

    public String selectEmail(String email)  {
        return session.selectOne(namespace + "selectEmail", email);
    }

    public Long selectMemberId(String loginId)  {  // mbr_id(회원번호) 조회
        return session.selectOne(namespace+"selectMemberId", loginId);
    }

    public int insertMember(MemberDto memberDto)  {    // 회원가입 resisterMember
        return session.insert(namespace + "insertMember", memberDto);
    }

    public MemberDto selectMemberInfo(Long memberId)  {    // 회원정보 조회
        return session.selectOne(namespace + "selectMemberInfo", memberId);
    }

    // update이지만 del_yn을 'Y'로 변경하므로 실질적으로 삭제라고 본다.
    public int deleteMember(Long mbr_id)  {    // 회원탈퇴 removeMember
        return session.update(namespace + "deleteMember", mbr_id);
    }

    public int updateMemeber(MemberDto memberDto)  {    // 회원정보수정   updateMember
        return session.update(namespace + "updateMember", memberDto);
    }

    public String selectFindId(String name, String email)   {
        Map map = new HashMap<>();
        map.put("name", name);
        map.put("email", email);
        return session.selectOne(namespace + "selectFindId", map);
    }

    public String selectFindPw(String lgin_id, String email) {
        Map map = new HashMap<>();
        map.put("lgin_id", lgin_id);
        map.put("email", email);
        return session.selectOne(namespace + "selectFindPw", map);
    }
}