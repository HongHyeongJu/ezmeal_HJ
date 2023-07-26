package com.teamProject.ezmeal.module;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AdminDueModule {

    public static Map<String, Object> getPeriodData(String periodString) {
        System.out.println("초기 json 값 periodString = " + periodString);
        System.out.println("개행문자 확인 periodString = [" + periodString + "]");
        String periodStringFormat = periodString.replaceAll("\"", "");
        System.out.println("개행문자 제거 periodStringFormat = " + periodStringFormat);

        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);

        String startTime = null;
        String endTime = today.toString();

        Map<String, Object> periodData = new HashMap<>();
        periodData.put("endTime", endTime);

        switch (periodStringFormat) {
            case "{isTrusted:true}":
                System.out.println("periodStringFormat = " + periodStringFormat);
                break;
            case "오늘":
                startTime = today.toString();
                break;
            case "어제":
                startTime = yesterday.toString();
                endTime = yesterday.toString();
                break;
            case "3일":
                System.out.println("3일");
                startTime = today.minusDays(3).toString();
                break;
            case "7일":
                startTime = today.minusDays(7).toString();
                break;
            case "15일":
                startTime = today.minusDays(15).toString();
                break;
            case "30일":
                startTime = today.minusMonths(1).toString();
                break;
            case "3개월":
                startTime = today.minusMonths(3).toString();
                break;
            case "6개월":
                startTime = today.minusMonths(6).toString();
                break;
            default:
                String[] dateArray = periodStringFormat.split(" ");
                System.out.println("dateArray = " + Arrays.toString(dateArray));
                startTime = dateArray[0];
                endTime = dateArray[1];
        }
        periodData.put("startTime", startTime);
        periodData.put("endTime", endTime);

        System.out.println("periodData = " + periodData);

        return periodData;
    }
}
