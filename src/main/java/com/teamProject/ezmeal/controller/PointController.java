package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.service.PointTransactionHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PointController {

    private  final PointTransactionHistoryService pointTransactionHistoryService;

    @GetMapping("/point")
    @ResponseBody
    public Map<String, Integer> getUsablePoint(HttpSession session) {
        Long memberID = (Long) session.getAttribute("memberId");
        int point = pointTransactionHistoryService.getUsablePoint(memberID);
        Map<String , Integer> pointMap = new HashMap<>();
        pointMap.put("point", point);
        return pointMap;
    }



}
