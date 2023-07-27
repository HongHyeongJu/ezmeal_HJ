package com.teamProject.ezmeal.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class NoticeDto {
    private Long notice_no;
    private String writer, typ, status, title ;
    private String wrt_dt_format;
    private LocalDateTime wrt_dt;
    private String stmt;
    private Integer ranking;
    private String hide_yn;
    private Integer view_cnt;
    private String del_yn;
    private LocalDateTime in_dtm;
    private String in_id;
    private LocalDateTime up_dtm;
    private String up_id;


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

    public NoticeDto(Long notice_no, String writer, String typ, String status, String title, String wrt_dt_format, String stmt, Integer ranking, String hide_yn, Integer view_cnt, String del_yn, LocalDateTime in_dtm, String in_id, LocalDateTime up_dtm, String up_id) {
        this.notice_no = notice_no;
        this.writer = writer;
        this.typ = typ;
        this.status = status;
        this.title = title;
        this.wrt_dt_format = wrt_dt_format; //
        this.stmt = stmt;
        this.ranking = ranking;
        this.hide_yn = hide_yn;
        this.view_cnt = view_cnt;
        this.del_yn = del_yn;
        this.in_dtm = in_dtm;
        this.in_id = in_id;
        this.up_dtm = up_dtm;
        this.up_id = up_id;
    }

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

    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getWrt_dt() {
        return wrt_dt;
    }

    public void setWrt_dt(LocalDateTime wrt_dt) {
        this.wrt_dt = wrt_dt;
    }

    public String getStmt() {
        return stmt;
    }

    public void setStmt(String stmt) {
        this.stmt = stmt;
    }

    public Integer getranking() {
        return ranking;
    }

    public void setranking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getHide_yn() {
        return hide_yn;
    }

    public void setHide_yn(String hide_yn) {
        this.hide_yn = hide_yn;
    }

    public Integer getview_cnt() {
        return view_cnt;
    }

    public void setview_cnt(Integer view_cnt) {
        this.view_cnt = view_cnt;
    }

    public String getDel_yn() {
        return del_yn;
    }

    public void setDel_yn(String del_yn) {
        this.del_yn = del_yn;
    }

    public LocalDateTime getIn_dtm() {
        return in_dtm;
    }

    public void setIn_dtm(LocalDateTime in_dtm) {
        this.in_dtm = in_dtm;
    }

    public String getIn_id() {
        return in_id;
    }

    public void setIn_id(String in_id) {
        this.in_id = in_id;
    }

    public LocalDateTime getUp_dtm() {
        return up_dtm;
    }

    public void setUp_dtm(LocalDateTime up_dtm) {
        this.up_dtm = up_dtm;
    }

    public String getUp_id() {
        return up_id;
    }

    public void setUp_id(String up_id) {
        this.up_id = up_id;
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
                ", wrt_dt=" + wrt_dt +
                ", wrt_dt_format=" + wrt_dt_format +
                ", stmt='" + stmt + '\'' +
                ", ranking=" + ranking +
                ", hide_yn='" + hide_yn + '\'' +
                ", view_cnt=" + view_cnt +
                ", del_yn='" + del_yn + '\'' +
                ", in_dtm=" + in_dtm +
                ", in_id='" + in_id + '\'' +
                ", up_dtm=" + up_dtm +
                ", up_id='" + up_id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoticeDto noticeDto = (NoticeDto) o;
        return Objects.equals(notice_no, noticeDto.notice_no) && Objects.equals(writer, noticeDto.writer) && Objects.equals(title, noticeDto.title) && Objects.equals(wrt_dt, noticeDto.wrt_dt) && Objects.equals(stmt, noticeDto.stmt) && Objects.equals(in_dtm, noticeDto.in_dtm) && Objects.equals(in_id, noticeDto.in_id) && Objects.equals(up_dtm, noticeDto.up_dtm) && Objects.equals(up_id, noticeDto.up_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notice_no, writer, title, stmt,in_id, up_id);
    }
}


//Getter 메서드 : 해당 필드의 값을 반환
//Setter 메서드 : 해당 필드의 값을 설정
//equals()와 hashCode() 메서드를 오버라이드하여 객체간의 동등성 비교