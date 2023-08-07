package com.teamProject.ezmeal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/notice")
public class AdminNoticeController {
    @GetMapping("/write")
    public String adminNoticeWrite(){
      return  "admin_notice_write";
    }
}

