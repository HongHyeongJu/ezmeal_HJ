package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.domain.NoticeDto;
import com.teamProject.ezmeal.domain.PageHandler;
import com.teamProject.ezmeal.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class NoticeController {

    @Autowired
    NoticeService noticeService;


    // NoticeList
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

/*    //값 받아오는 메서드
    @PostMapping("/noticeresistration")
    public String noticeResistration(String title, String typ, String status, String stmt,String hide_yn  ){
        System.out.println(title);
        System.out.println(typ);
        System.out.println(status);
        System.out.println(stmt);
        System.out.println(hide_yn);
        return "redirect:/admin/notice/write";
//        return "admin_notice_write";
    }*/

    //값 받아오는 메서드
    @PostMapping("/noticeresistration")
    public String noticeResistration(NoticeDto noticeDto){

        System.out.println(noticeDto.toString());
        int notice = noticeService.NoticeResistration(noticeDto);
        //int로 받기로 약속했으니까 약속지키기.
        System.out.println("등록한 글수 " + notice);
        return "redirect:/admin/notice/write";
//        return "admin_notice_write";
    }




    //  @GetMapping("/noticestmt")  링크걸 url을 ( ) 에 적기. 이름 안겹치게.
    //  링크뒤에 ?매개변수=값 ex)http://localhost /ch4/noticestmt?notice_no=3
    // NoticeRead
    @GetMapping("/noticestmt")
    public String showNoticeStmt(Long notice_no, Model model) { // (매개변수 , 모델)
        System.out.println("notice_no: "+notice_no);
        NoticeDto notice = (NoticeDto) noticeService.findNoticeByNo(notice_no);
        System.out.println("notice: "+notice.toString());
        model.addAttribute("notice", notice);

        return "notice_stmt";

        //    public String list(int page, int pageSize, Model m, HttpServletRequest request) {
    }

    // NoticeWrite

    // NoticeModify

    //NoticeDelete
}



