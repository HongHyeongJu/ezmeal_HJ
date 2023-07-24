package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.AdminMemberDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdminMemberDao {

    private final SqlSession session;

    private static final String namespace = "adminMember.";

    public int selectEmpId(String id) {
        return session.selectOne(namespace + "selectEmpId" , id);
    }

    public AdminMemberDto selectLoginAdminInfo(int empId) {
        return session.selectOne(namespace + "selectLoginAdminInfo", empId);
    }

}
