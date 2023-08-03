package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.domain.NoticeDto;
import com.teamProject.ezmeal.domain.PageHandler;
import com.teamProject.ezmeal.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class NoticeController<pageHandler> {

    @Autowired
    NoticeService noticeService;
//
//    @GetMapping("/notice")
//    public String showNotice(Integer page, Integer pageSize, Model model) {
//
//        if(page == null) page = 1;
//        if(pageSize == null) pageSize = 10;
//
//
//        List<NoticeDto> list = noticeService.getNoticeList(); //공지사항 목록 가져옴
//        System.out.println("list = " + list);
//        model.addAttribute("noticeList", list); //공지사항목록을 model객체에 담아서 뷰로 전달.
//
//        return "notice";
//    }
//
    @GetMapping("/notice")  // 0731 :이거로해야 화면이 나오긴 한다.
    public String showNotice(Integer page, Integer pageSize, Model model) {

        if(page == null) page = 1;
        if(pageSize == null) pageSize = 10;

//        try{
            int totalCnt=noticeService.getTotalCnt();
        System.out.println("totalCnt = " + totalCnt);
            PageHandler pageHandler = new PageHandler(totalCnt,page, pageSize);
//
            Map map=new HashMap();
            map.put("offset",(page-1)*pageSize);
            map.put("pageSize",pageSize);
//
//        }

        List<NoticeDto> list = noticeService.getNoticeList(map);
        System.out.println("list = " + list);
        model.addAttribute("noticeList", list);

        model.addAttribute("ph", pageHandler);

        return "notice_list";
    }



    //  @GetMapping("/noticestmt")  링크걸 url을 ( ) 에 적기. 이름 안겹치게.
    //  링크뒤에 ?매개변수=값 ex)http://localhost /ch4/noticestmt?notice_no=3
    @GetMapping("/noticestmt")
    public String showNoticeStmt(Long notice_no, Model model) { // (매개변수 , 모델)
        System.out.println("notice_no: "+notice_no);
        NoticeDto notice = (NoticeDto) noticeService.findNoticeByNo(notice_no);
        System.out.println("notice: "+notice.toString());
        model.addAttribute("notice", notice);

        return "notice_stmt";

        //    public String list(int page, int pageSize, Model m, HttpServletRequest request) {
    }


}



