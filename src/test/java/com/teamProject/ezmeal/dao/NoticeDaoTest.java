package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.NoticeDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})


public class NoticeDaoTest {
    @Autowired
    NoticeDao noticeDao; // noticeDao는 객체임

    @Test
    public void testNoticeDto() {
        //ex. noticeDto라는 객체의 setUp_id라는 메서드를 호출하고, 매개변수로 "U001"이라는 값이 전달
        NoticeDto noticeDto = new NoticeDto();
        noticeDto.setNotice_no(1L);
        noticeDto.setWriter("E001");
        noticeDto.setTitle("Title1");
        noticeDto.setStmt("Statement1");
        noticeDto.setIn_id("I001");
        noticeDto.setUp_id("U001");

        LocalDateTime now = LocalDateTime.now();
        noticeDto.setIn_dtm(now);
        noticeDto.setUp_dtm(now);
        noticeDto.setWrt_dt(now);

        // when
        Long notice_no = noticeDto.getNotice_no();
        String writer = noticeDto.getWriter();
        String title = noticeDto.getTitle();
        String stmt = noticeDto.getStmt();
        String in_id = noticeDto.getIn_id();
        String up_id = noticeDto.getUp_id();
        LocalDateTime in_dtm = noticeDto.getIn_dtm();
        LocalDateTime up_dtm = noticeDto.getUp_dtm();
        LocalDateTime wrt_dt = noticeDto.getWrt_dt();

        // assertEquals는 두값이 동일한지 여부를 확인하는데 사용, 예상한값이 실제와 일치하지않으면 테스트 실패
        assertEquals(1L, notice_no.longValue());
        assertEquals("E001", writer);
        assertEquals("Title1", title);
        assertEquals("Statement1", stmt);
        assertEquals("I001", in_id);
        assertEquals("U001", up_id);
        assertEquals(now, in_dtm);
        assertEquals(now, up_dtm);
        assertEquals(now, wrt_dt);
    }

    @Test
    public void inserttest() throws Exception {
        // 먼저 1L에 해당하는 데이터를 삭제
        int deletenumber = noticeDao.delete(1L);

        // 새로운 NoticeDto 객체를 생성하고 데이터를 설정합니다.
        NoticeDto noticeDto = new NoticeDto
                (1L,"ezmeal", "긴급공지", "안녕하세용ㄹㅇㄹㅇㄹ" );
        System.out.println("noticeDto: " + noticeDto);
//        NoticeDto noticeDto = new NoticeDto
//                (1L,"ezmeal", "긴급공지", "안녕하세용ㄹㅇㄹㅇㄹ" );
//        System.out.println("noticeDto: " + noticeDto);

        // 설정된 데이터를 데이터베이스에 삽입
        int resultnumber = noticeDao.insert(noticeDto);
        System.out.println("resultnumber" + resultnumber);
    }

    @Test
    public void deletetest() throws Exception {
        // 테스트를 위해 데이터베이스에 새로운 데이터를 삽입
        NoticeDto noticeDto = new NoticeDto(20L,"asdf", "asdf", "asdf");
        int insertResult = noticeDao.insert(noticeDto);
        assertEquals(insertResult, 1); // 데이터 삽입이 성공적으로 이루어졌는지 확인

        // 삽입한 데이터의 notice_no를 가져옴
        Long insertedNoticeNo = noticeDto.getNotice_no();

        // delete() 메서드 호출하여 해당 데이터 삭제
        int deletenumber = noticeDao.delete(insertedNoticeNo);
        assertEquals(deletenumber, 1); // 데이터 삭제가 성공적으로 이루어졌는지 확인
    }

    @Test
    public void selectTest(){
        NoticeDto selectnumber = noticeDao.selectNotice(5l);
        //noticedao클래스의 selectNotice메서드를 사용해서 noticedtp객체를 받는것
        //selectNotice 메서드가 정상적으로 동작하고 데이터베이스에서 해당 공지사항 정보를 가져오면,
        // selectnumber 변수에 조회한 NoticeDto 객체가 담긴다.

        System.out.println("selectnumber: " + selectnumber);
        System.out.println("제목: " + selectnumber.getTitle());
        System.out.println("작성자: " + selectnumber.getWriter());
        System.out.println("내용: " + selectnumber.getStmt());
        // 필요한 경우, 다른 필드들도 출력할 수 있음
        selectnumber.setStmt("컬리가자");
        int updatenum = noticeDao.updateNotice(selectnumber);
        System.out.println("updatenum: " + updatenum);
        System.out.println("제목: " + selectnumber.getTitle());
        System.out.println("작성자: " + selectnumber.getWriter());
        System.out.println("내용: " + selectnumber.getStmt());

    }

}
