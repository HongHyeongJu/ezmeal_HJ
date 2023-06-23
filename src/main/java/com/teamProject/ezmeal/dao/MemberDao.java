package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.MemberDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDao {
    @Autowired
    private SqlSession session;
    private static final String namespace = "com.teamProject.ezmeal.dao.MemberMapper.";

    // selectAll이 필요한가??
    public List<MemberDto> selectMemberAll() throws Exception {
        return session.selectList(namespace + "selectAll");
    }

    // Member조회, lgin_id기준으로 조회함.
    public MemberDto selectMember(String lgin_id) throws Exception {
        return session.selectOne(namespace+"select", lgin_id);
    }
}
