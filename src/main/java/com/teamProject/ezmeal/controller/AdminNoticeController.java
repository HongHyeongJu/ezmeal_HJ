package com.teamProject.ezmeal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
@RequestMapping("/admin/notice")
public class AdminNoticeController {
    @GetMapping("/write")
    public String adminNoticeWrite(){
      return  "admin_notice_write";
    }


    @GetMapping("/dashboard")
    public String adminboardeHome(Model model)  {return "admin_board_notice";}

}