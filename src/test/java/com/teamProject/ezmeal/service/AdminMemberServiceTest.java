package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.domain.AdminMemberDto;
import com.teamProject.ezmeal.domain.MemberDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class AdminMemberServiceTest {
    
    @Autowired
    AdminMemberService adminMemberService;
    
    @Test
    public void getLoginAdminInfo() {
        AdminMemberDto loginAdminInfo = adminMemberService.getLoginAdminInfo(18);
        System.out.println("loginAdminInfo = " + loginAdminInfo);
    }
    
    @Test
    public void selectMemberList(){
//        List<Map> memberList = adminMemberService.getMemberList();
//        System.out.println("memberList.get(0) = " + memberList.get(0));
    }
}