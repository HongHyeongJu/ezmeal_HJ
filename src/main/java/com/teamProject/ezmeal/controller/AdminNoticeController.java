package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.domain.NoticeDto;
import com.teamProject.ezmeal.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
@RequestMapping("/admin/notice")
@RequiredArgsConstructor
public class AdminNoticeController {

    private final NoticeService noticeService;

    @GetMapping("/write")
    public String adminNoticeWrite(){
        System.out.println("어드민 notice write 진입 get mapping");
        return  "admin_notice_write";
    }


    @GetMapping("/dashboard")
    public String adminboardeHome(Model model)  {return "admin_board_notice";}


    //값 받아오는 메서드
    @PostMapping("/write")
    public String adminNoticeWrite(NoticeDto noticeDto){

        System.out.println(" admin notice write form으로부터 받아오는 값 ");
        System.out.println(noticeDto.toString());
        int notice = noticeService.NoticeResistration(noticeDto);
        //int로 받기로 약속했으니까 약속지키기.
        System.out.println("등록한 글수 " + notice);
        return "redirect:/admin/notice/write";
//        return "admin_notice_write";
    }

}