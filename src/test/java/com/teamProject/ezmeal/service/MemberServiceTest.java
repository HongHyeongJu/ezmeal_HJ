package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.MemberDao;
import com.teamProject.ezmeal.domain.MemberDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MemberServiceTest {

    @Autowired
    MemberDao memberDao;

    @Autowired
    MemberService memberService;

    @Test
    public void checkIdDuplicate() {
    }

    @Test
    public void checkEmailDuplicate() {
        boolean checkEmail = memberService.checkEmailDuplicate("zjfl3122@naver.com");
        System.out.println("checkEmail = " + checkEmail);
        assertTrue(checkEmail);
    }

    @Test
    public void signup() {
        MemberDto memberDto = new MemberDto();
        memberDto.setLgin_id("testuser");


    }

    @Test
    public void withdrawal() {
    }

    @Test
    public void mbrInfo() {
    }

    @Test
    public void modify() {
    }

    @Test
    public void getFindId(){
        String name = "백현욱";
        String email = "zjfl3122@naver.com";
        String findId = memberService.getFindId(name,email);
        System.out.println("findId = " + findId);
        assertEquals(findId,"zjfl3122");
    }

    @Test
    public void getFindPw(){
        String id = "zjfl3122";
        String email = "zjfl3122@naver.com";
        String findPw = memberService.getFindPw(id, email);
        System.out.println("findPw = " + findPw);
    }
}