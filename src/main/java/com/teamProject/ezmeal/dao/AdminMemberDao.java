package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.AdminMemberDto;
import com.teamProject.ezmeal.domain.MemberDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AdminMemberDao {

    private final SqlSession session;

    private static final String namespace = "adminMember.";

    // 관리자id로 해당 id의 empId 조회하기
    public int selectEmpId(String id) {
        return session.selectOne(namespace + "selectEmpId" , id);
    }


    // 현재 로그인중인 empId로 관리자 정보 조회하기
    public AdminMemberDto selectLoginAdminInfo(int empId) {
        return session.selectOne(namespace + "selectLoginAdminInfo", empId);
    }

    // 회원 정보 목록 전체 조회
    public List<Map<String,Object>> selectMemberList() {
        return session.selectList(namespace + "selectMemberList");
    }

    // 동적으로 조건에 따라 회원 정보 조회
    public List<Map<String, Object>> selectMembers(Map<String, String> memberMap) {
        try {
            return session.selectList(namespace + "selectMembers", memberMap);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
