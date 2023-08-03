package com.teamProject.ezmeal.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class NoticeDto {
    private Long notice_no;
    private String writer ;
    private String typ;

    private String status;

    private String title ;

    private LocalDateTime wrt_dt;
    private String wrt_dt_format;
    private String stmt;


    // 기본 생성자
    public NoticeDto() {
    }

    //매개변수있는 생성자(테스트코드때문에 만들어놓는거야.  !!PK는 생성자로 만들지마)
    //Long, String 타입의 매개변수를 받는 생성자로, notice_no, writer, title, stmt 값을 설정하는 역할


    public NoticeDto(Long notice_no, String writer, String title, String stmt) {
        this.notice_no = notice_no;
        this.writer = writer;
        this.title = title;
        this.stmt = stmt;
    }

    public NoticeDto(Long notice_no,String writer, String title, String stmt, String  wrt_dt_format ){
        this.notice_no = notice_no;
        this.writer = writer;
        this.title = title;
        this.stmt = stmt;
        this.wrt_dt_format = wrt_dt_format;
    }


//    public NoticeDto(Long notice_no, String writer, String status,String typ, String title, String wrt_dt_format, String stmt) {
//        this.notice_no = notice_no;
//        this.writer = writer;
//        this.status = status;
//        this.typ = typ;
//        this.title = title;
//        this.wrt_dt_format = wrt_dt_format; //
//        this.stmt = stmt;
//
//    }

    public Long getNotice_no() {
        return notice_no;
    }

    public void setNotice_no(Long notice_no) {
        this.notice_no = notice_no;
    }
    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStmt() {
        return stmt;
    }

    public void setStmt(String stmt) {
        this.stmt = stmt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDateTime getWrt_dt() {
        return wrt_dt;
    }

    public void setWrt_dt(LocalDateTime wrt_dt) {
        this.wrt_dt = wrt_dt;
    }

    public String getWrt_dt_format() {

        return wrt_dt_format;
    }
    public void setWrt_dt_format(String wrt_dt_format) {

        this.wrt_dt_format = wrt_dt_format;
    }
    @Override
    public String toString() {
        return "noticeDto{" +
                "notice_no=" + notice_no +
                ", writer='" + writer + '\'' +
                ", status='" + status + '\'' +
                ", title='" + title + '\'' +
                ", wrt_dt" + wrt_dt + '\'' +
                ", wrt_dt_format=" + wrt_dt_format +
                ", stmt='" + stmt + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoticeDto noticeDto = (NoticeDto) o;
        return Objects.equals(notice_no, noticeDto.notice_no) && Objects.equals(writer, noticeDto.writer) && Objects.equals(title, noticeDto.title) && Objects.equals(stmt, noticeDto.stmt) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(notice_no, writer, title, stmt);
    }


}


//Getter 메서드 : 해당 필드의 값을 반환
//Setter 메서드 : 해당 필드의 값을 설정
//equals()와 hashCode() 메서드를 오버라이드하여 객체간의 동등성 비교