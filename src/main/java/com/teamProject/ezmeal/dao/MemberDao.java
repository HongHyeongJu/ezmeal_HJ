package com.teamProject.ezmeal.dao;

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
}