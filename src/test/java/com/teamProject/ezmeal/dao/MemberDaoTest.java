package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.MemberDto;
import com.teamProject.ezmeal.service.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MemberDaoTest {

    @Autowired
    private MemberDao memberDao;
    @Autowired
    private LoginService loginService;
    @Test
    public void getPassword() throws Exception {
        String kimjava100 = memberDao.getPassword("kimjava100");
        System.out.println("kimjava100 = " + kimjava100);
    }
    @Test
    public void checkNull() throws Exception {
        Long login = loginService.getLogin("kimjava100", "asdf1234");
        System.out.println("login = " + login);
    }

    @Test
    public void  memberInfo() throws  Exception {
        MemberDto memberInfo = memberDao.getMemberInfo(1001L);
        System.out.println("memberInfo = " + memberInfo);
    }

    @Test
    public void signupTest() throws Exception {
        MemberDto memberDto = new MemberDto("백자바","m","1995-08-15","010-1234-5678","java@naver.com","java","1234");
        assertTrue(memberDao.registerMember(memberDto) == 1);
        System.out.println("memberDto.getLgin_id() = " + memberDto.getLgin_id());
        System.out.println("memberDto.getName() = " + memberDto.getName());
    }

    @Test
    public void withdrawalTest() throws Exception {
        // 로그인 정보를 가져온다.
        Long getMemberId = memberDao.getMemberId("zjfl3122");
        System.out.println("getMemberId = " + getMemberId);
        // memberDao의 mbrWithdrawal 메서드 호출해서 쿼리문 실행 테스트한다.
        int withdrawal = memberDao.mbrWithdrawal(getMemberId);
        assertTrue(withdrawal == 1);

        MemberDto memberDto = new MemberDto("test","m","1995-08-15","010-1234-5678","java@naver.com","test006","1234");
        assertTrue(memberDao.registerMember(memberDto) == 1); // 회원정보를 입력한 회원을 가입한다.
        System.out.println("lgin_id = " + memberDto.getLgin_id());
//        System.out.println("memberDto.getMbr_id() = " + memberDto.getMbr_id()); // dto 객체 만들때는 mbr_id를 넣지 않기 때문에 조회안됨
        Long mbr_id = memberDao.getMemberId(memberDto.getLgin_id()); // 해당하는 id로 쿼리문으로 조회할땐 mbr_id가 정상 조회됨
        System.out.println("mbr_id = " + mbr_id);
        withdrawal = memberDao.mbrWithdrawal(mbr_id);
        assertTrue(withdrawal==1);

    }

    @Test
    public  void mbrModifyTest() throws Exception {
        MemberDto newmbr = new MemberDto("test", "m","1995-08-15","010-1234-5678","java@naver.com","test14","1234");
        assertTrue(memberDao.registerMember(newmbr) == 1); // 회원정보를 입력한 회원을 가입한다.
        System.out.println("newmbr_pw = " + newmbr.getLgin_pw());
        newmbr.setLgin_pw("5964");
        System.out.println("newmbr.change_pw = " + newmbr.getLgin_pw());
        newmbr.setEmail("test@gmail.com");

        int modity = memberDao.mbrModify(newmbr);
        assertTrue(modity==1);  // 해당하는 회원번호의 회원의 정보를 변경한다
    }
}