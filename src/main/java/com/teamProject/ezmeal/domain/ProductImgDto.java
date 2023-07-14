package com.teamProject.ezmeal.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class ProductImgDto {

    private Long seq, prod_cd;
    private String typ, detail, url;
    private Integer verti_size, width_size;
    private String extns;
    private Integer file_size_lim;
    private String use_yn, del_yn, rmk;
    private LocalDateTime in_dtm;
    private String in_id;
    private LocalDateTime up_dtm;
    private String up_id;

    /*-------------------------------------------------------------------------------------------------*/
    public ProductImgDto(){

    }

    public ProductImgDto(Long seq, Long prod_cd, String typ, String detail, String url, Integer verti_size,
                         Integer width_size, String extns, Integer file_size_lim, String use_yn, String del_yn,
                         String rmk, LocalDateTime in_dtm, String in_id, LocalDateTime up_dtm, String up_id) {
        this.seq = seq;
        this.prod_cd = prod_cd;
        this.typ = typ;
        this.detail = detail;
        this.url = url;
        this.verti_size = verti_size;
        this.width_size = width_size;
        this.extns = extns;
        this.file_size_lim = file_size_lim;
        this.use_yn = use_yn;
        this.del_yn = del_yn;
        this.rmk = rmk;
        this.in_dtm = in_dtm;
        this.in_id = in_id;
        this.up_dtm = up_dtm;
        this.up_id = up_id;
    }

    /*-----------------------------------------------------------------------  toString()  ------------------*/

    @Override
    public String toString() {
        return "ProductImgDto{" +
                "seq=" + seq +
                ", prod_cd='" + prod_cd + '\'' +
                ", typ='" + typ + '\'' +
                ", detail='" + detail + '\'' +
                ", url='" + url + '\'' +
                ", extns='" + extns + '\'' +
                ", use_yn='" + use_yn + '\'' +
                ", del_yn='" + del_yn + '\'' +
                '}';
    }
    /*------------------------------------------------------------------------------------------------------*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductImgDto that = (ProductImgDto) o;
        return Objects.equals(seq, that.seq) && Objects.equals(prod_cd, that.prod_cd) && Objects.equals(typ, that.typ) && Objects.equals(detail, that.detail) && Objects.equals(url, that.url) && Objects.equals(verti_size, that.verti_size) && Objects.equals(width_size, that.width_size) && Objects.equals(extns, that.extns) && Objects.equals(file_size_lim, that.file_size_lim) && Objects.equals(use_yn, that.use_yn) && Objects.equals(del_yn, that.del_yn) && Objects.equals(rmk, that.rmk) && Objects.equals(in_dtm, that.in_dtm) && Objects.equals(in_id, that.in_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seq, prod_cd, typ, detail, url, verti_size, width_size, extns, file_size_lim, use_yn, del_yn, rmk, in_dtm, in_id);
    }

    /*-------------------------------------------------------------------- getter & setter ---------------------*/
    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public Long getProd_cd() {
        return prod_cd;
    }

    public void setProd_cd(Long prod_cd) {
        this.prod_cd = prod_cd;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getVerti_size() {
        return verti_size;
    }

    public void setVerti_size(Integer verti_size) {
        this.verti_size = verti_size;
    }

    public Integer getWidth_size() {
        return width_size;
    }

    public void setWidth_size(Integer width_size) {
        this.width_size = width_size;
    }

    public String getExtns() {
        return extns;
    }

    public void setExtns(String extns) {
        this.extns = extns;
    }

    public Integer getFile_size_lim() {
        return file_size_lim;
    }

    public void setFile_size_lim(Integer file_size_lim) {
        this.file_size_lim = file_size_lim;
    }

    public String getUse_yn() {
        return use_yn;
    }

    public void setUse_yn(String use_yn) {
        this.use_yn = use_yn;
    }

    public String getDel_yn() {
        return del_yn;
    }

    public void setDel_yn(String del_yn) {
        this.del_yn = del_yn;
    }

    public String getRmk() {
        return rmk;
    }

    public void setRmk(String rmk) {
        this.rmk = rmk;
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