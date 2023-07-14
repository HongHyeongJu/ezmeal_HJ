package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.MemberDao;
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
        boolean checkEmail = memberService.checkEmailDuplicate("zjfl@naver.com");
        System.out.println("checkEmail = " + checkEmail);
        assertTrue(checkEmail);
    }

    @Test
    public void signup() {
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
}