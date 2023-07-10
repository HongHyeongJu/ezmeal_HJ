package com.teamProject.ezmeal.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class ProductReviewDto {

    private Long revw_id, ord_id, prod_cd, mbr_id;
    private String title, stmt;
    private LocalDate dlvry_cmpl_dt, revw_posbl_dt, wrt_dt;
    private Integer thumb, star;
    private String revw_opub_yn, del_yn;
    private LocalDateTime in_dtm;
    private String in_id;
    private LocalDateTime up_dtm;
    private String up_id;
    /*-----------------------------------------------------------------------------*/
    public ProductReviewDto(){}

    public ProductReviewDto(Long ord_id, Long prod_cd, Long mbr_id, String title,
                            String stmt, LocalDate dlvry_cmpl_dt,
                            LocalDate revw_posbl_dt, LocalDate wrt_dt, Integer thumb, Integer star,
                            String revw_opub_yn, String del_yn, LocalDateTime in_dtm,
                            String in_id, LocalDateTime up_dtm, String up_id) {
        this.ord_id = ord_id;
        this.prod_cd = prod_cd;
        this.mbr_id = mbr_id;
        this.title = title;
        this.stmt = stmt;
        this.dlvry_cmpl_dt = dlvry_cmpl_dt;
        this.revw_posbl_dt = revw_posbl_dt;
        this.wrt_dt = wrt_dt;
        this.thumb = thumb;
        this.star = star;
        this.revw_opub_yn = revw_opub_yn;
        this.del_yn = del_yn;
        this.in_dtm = in_dtm;
        this.in_id = in_id;
        this.up_dtm = up_dtm;
        this.up_id = up_id;
    }
    /*-----------------------------------------------------------------------------*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductReviewDto that = (ProductReviewDto) o;
        return Objects.equals(revw_id, that.revw_id) && Objects.equals(ord_id, that.ord_id) && Objects.equals(prod_cd, that.prod_cd) && Objects.equals(mbr_id, that.mbr_id) && Objects.equals(title, that.title) && Objects.equals(stmt, that.stmt) && Objects.equals(dlvry_cmpl_dt, that.dlvry_cmpl_dt) && Objects.equals(revw_opub_yn, that.revw_opub_yn) && Objects.equals(del_yn, that.del_yn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(revw_id, ord_id, prod_cd, mbr_id, title, stmt, dlvry_cmpl_dt, revw_opub_yn, del_yn);
    }
    /*-----------------------------------------------------------------------------*/

    @Override
    public String toString() {
        return "ProductReviewDto{" +
                "revw_id=" + revw_id +
                ", ord_id=" + ord_id +
                ", mbr_id=" + mbr_id +
                ", prod_cd='" + prod_cd + '\'' +
                ", title='" + title + '\'' +
                ", stmt='" + stmt + '\'' +
                ", wrt_dt=" + wrt_dt +
                ", thumb=" + thumb +
                ", star=" + star +
                '}';
    }

    /*-----------------------------------------------------------------------------*/
    public Long getRevw_id() {
        return revw_id;
    }

    public void setRevw_id(Long revw_id) {
        this.revw_id = revw_id;
    }

    public Long getOrd_id() {
        return ord_id;
    }

    public void setOrd_id(Long ord_id) {
        this.ord_id = ord_id;
    }

    public Long getMbr_id() {
        return mbr_id;
    }

    public void setMbr_id(Long mbr_id) {
        this.mbr_id = mbr_id;
    }

    public Long getProd_cd() {
        return prod_cd;
    }

    public void setProd_cd(Long prod_cd) {
        this.prod_cd = prod_cd;
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

    public LocalDate getDlvry_cmpl_dt() {
        return dlvry_cmpl_dt;
    }

    public void setDlvry_cmpl_dt(LocalDate dlvry_cmpl_dt) {
        this.dlvry_cmpl_dt = dlvry_cmpl_dt;
    }

    public LocalDate getRevw_posbl_dt() {
        return revw_posbl_dt;
    }

    public void setRevw_posbl_dt(LocalDate revw_posbl_dt) {
        this.revw_posbl_dt = revw_posbl_dt;
    }

    public LocalDate getWrt_dt() {
        return wrt_dt;
    }

    public void setWrt_dt(LocalDate wrt_dt) {
        this.wrt_dt = wrt_dt;
    }

    public Integer getThumb() {
        return thumb;
    }

    public void setThumb(Integer thumb) {
        this.thumb = thumb;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getRevw_opub_yn() {
        return revw_opub_yn;
    }

    public void setRevw_opub_yn(String revw_opub_yn) {
        this.revw_opub_yn = revw_opub_yn;
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
}
