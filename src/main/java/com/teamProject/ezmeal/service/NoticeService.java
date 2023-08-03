package com.teamProject.ezmeal.service;
import com.teamProject.ezmeal.dao.NoticeDao;
import com.teamProject.ezmeal.domain.NoticeDto;
import com.teamProject.ezmeal.domain.PageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//    @Service
//    public class NoticeService {

//        private NoticeDao noticeDao; //불변성, 의존성 주입을 확보하기 위해서  noticeDao가 null이될가능성을 줄인다.
//
//        public NoticeService(NoticeDao noticeDao) {
//            this.noticeDao = noticeDao;
//        }
//
//        public List<NoticeDto> getNoticeList() {
//            return noticeDao.selectNoticeList();
//        }
//
//        public NoticeDto findNoticeByNo(Long notice_no) {
//            return noticeDao.selectNotice(notice_no);
//        }
//
//        public List<NoticeDto> getNoticeList(int page, int pageSize) {
//            int offset = (page - 1) * pageSize;
//            return noticeDao.selectNoticeList(offset, pageSize);
//        }
//
//
//        public PageHandler getNoticePageHandler(int page, int pageSize) {
//            List<NoticeDto> allNotices = noticeDao.selectNoticeList();
//            int totalCnt = allNotices.size();
//            PageHandler pageHandler = new PageHandler(page, pageSize, totalCnt);
//            return pageHandler;
//        }
//
//        }

@Service
public class NoticeService {

    private NoticeDao noticeDao;

    public NoticeService(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
    }

    public List<NoticeDto> getNoticeList() {
        return noticeDao.selectNoticeList();
    }

    public NoticeDto findNoticeByNo(Long notice_no) {
        return noticeDao.selectNotice(notice_no);
    }

    public List<NoticeDto> getNoticeList(Map map) {
        return noticeDao.selectNoticeList(map);
    }

    public PageHandler getNoticePageHandler(int page, int pageSize) {
        List<NoticeDto> allNotices = noticeDao.selectNoticeList();
        int totalCnt = allNotices.size();
        PageHandler pageHandler = new PageHandler(page, pageSize, totalCnt);
        return pageHandler;
    }

    public int getTotalCnt(){
        return noticeDao.selectTotalCnt();
    }


}

