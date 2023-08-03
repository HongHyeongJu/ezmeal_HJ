package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.MemberDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class AdminMemberDaoTest {

    @Autowired
    private AdminMemberDao adminMemberDao;

    @Test
    public void selectEmpId() {
    }

    @Test
    public void selectLoginAdminInfo() {
    }

    @Test
    public void selectMemberList() {
//        System.out.println("test");
//        List<MemberDto> memberList = adminMemberDao.selectMemberList();
//        System.out.println("memberList = " + memberList.get(0));
    }

    @Test
    public void selectMembers() {
        Map<String, String> memberMap = new HashMap<>();
//        String name = "백현욱";
        String lgin_id = "zjfl3122";
//        String email = "zjfl3122@naver.com";
//        memberMap.put("name",name);
        memberMap.put("lgin_id",lgin_id);
//        memberMap.put("email",email);
        List<Map<String, Object>> memberList = adminMemberDao.selectMembers(memberMap);
//        System.out.println("memberList = " + memberList);
        System.out.println("---------");
        System.out.println("memberList.get(0) = " + memberList.get(0));
    }

}