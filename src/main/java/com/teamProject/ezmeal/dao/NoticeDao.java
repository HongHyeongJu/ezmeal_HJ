package com.teamProject.ezmeal.dao;


import com.teamProject.ezmeal.domain.NoticeDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository // repository가 달린 클래스 찾아 빈 등록 하고 다른클래스에서 autowired 를 통해 doa를 주입해서사용
public class NoticeDao {

    @Autowired
    private SqlSession session;
    //@Autowired 어노테이션을 사용하여 자동으로 SqlSession 객체를 주입받
    private String namespace = "tb_notice.";
//MyBatis는 매퍼 파일의 SQL 문을 namespace와 SQL 아이디를 조합하여 찾아 실행


    public int select(long l) {
        return session.selectOne(namespace + "selectNotice");
    }

    //어진 long l 값을 이용하여 데이터베이스에서 특정 값을 조회
    public int insert(NoticeDto noticeDto) {
        return session.insert(namespace + "insertNotice", noticeDto);
    }

    //주어진 NoticeDto noticeDto 객체를 데이터베이스에 삽입
    public int delete(Long notice_no) {
        return session.delete(namespace + "deleteNotice", notice_no);
    }

    //주어진 Long notice_no에 해당하는 데이터를 데이터베이스에서 삭제
    public int deleteAll() {
        return session.delete(namespace + "deleteAll");
    }

    public int count() {
        return session.selectOne(namespace + "count");
    }

    public NoticeDto selectNotice(Long notice_no) {
        return session.selectOne(namespace + "select_notice", notice_no);
    }
    //주어진 Long notice_no에 해당하는 공지사항 정보를 조회


    public int updateNotice(NoticeDto noticeDto) {
        return session.update(namespace + "updateNotice", noticeDto);
    }
//session 객체로 공지사항 정보를 업데이트
    //NoticeDto 객체를 매개변수로 받아오는 public 메서드. 이 메서드는 int 형을 반환
    //session.update() 메서드를 사용하여 데이터베이스의 레코드를 수정
//noticeDto: NoticeDto 객체인 noticeDto를 업데이트할 정보를 담고 있는 객체로 사용

    public NoticeDto selectNoticeDetail(Long notice_no) {
        return session.selectOne(namespace + "selectNoticeDetail", notice_no);
    }
    // //notice_no에 해당하는 공지사항의 세부 정보를 조회


    public List<NoticeDto> selectNoticeList() {
        return session.selectList(namespace + "selectNoticeList");
    }


    public int save(NoticeDto noticeDto) { //값저장하려고 만든 메서드
        return session.insert(namespace + "insertNotice", noticeDto);
    }

    public List<NoticeDto> selectNoticeList(Map map) {
        return session.selectList(namespace + "selectNoticeList", map);
    }

    public List<NoticeDto> selectNoticeListWithoutParams() {
        System.out.println(session.selectList(namespace + "selectNoticeListWithoutParams"));
        return session.selectList(namespace + "selectNoticeListWithoutParams");
    }

    public int selectTotalCnt() {
        return session.selectOne(namespace + "gettotalCnt");
    }
}


