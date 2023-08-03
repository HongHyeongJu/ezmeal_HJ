package com.teamProject.ezmeal.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class CustDto {
    private String cust_cd, cust_nm, biz_no, Biz_cnd, biz_cate, ceo, addr, zpcd, phone, fax, email, mng_nm, mng_phone, bank, acno, deal_yn, reg_dt, use_yn, rmk;
    private LocalDateTime in_dtm;
    private String in_id;
    private LocalDateTime up_dtm;
    private String up_id;
    /*---------------------------------------------------------------*/

    public CustDto(String cust_cd, String cust_nm, String biz_no, String ceo, String phone,
                   String fax, String email, String mng_nm, String mng_phone) {
        this.cust_cd = cust_cd;
        this.cust_nm = cust_nm;
        this.biz_no = biz_no;
        this.ceo = ceo;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.mng_nm = mng_nm;
        this.mng_phone = mng_phone;
    }
    /*---------------------------------------------------------------*/

    @Override
    public String toString() {
        return "CustDto{" +
                "cust_cd='" + cust_cd + '\'' +
                ", cust_nm='" + cust_nm + '\'' +
                ", biz_no='" + biz_no + '\'' +
                ", ceo='" + ceo + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", email='" + email + '\'' +
                ", mng_nm='" + mng_nm + '\'' +
                ", mng_phone='" + mng_phone + '\'' +
                '}';
    }
    /*---------------------------------------------------------------*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustDto custDto = (CustDto) o;
        return Objects.equals(cust_cd, custDto.cust_cd) && Objects.equals(cust_nm, custDto.cust_nm) && Objects.equals(biz_no, custDto.biz_no) && Objects.equals(ceo, custDto.ceo) && Objects.equals(phone, custDto.phone) && Objects.equals(fax, custDto.fax) && Objects.equals(email, custDto.email) && Objects.equals(mng_nm, custDto.mng_nm) && Objects.equals(mng_phone, custDto.mng_phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cust_cd, cust_nm, biz_no, ceo, phone, fax, email, mng_nm, mng_phone);
    }

    /*---------------------------------------------------------------*/

    public String getCust_cd() {
        return cust_cd;
    }

    public void setCust_cd(String cust_cd) {
        this.cust_cd = cust_cd;
    }

    public String getCust_nm() {
        return cust_nm;
    }

    public void setCust_nm(String cust_nm) {
        this.cust_nm = cust_nm;
    }

    public String getBiz_no() {
        return biz_no;
    }

    public void setBiz_no(String biz_no) {
        this.biz_no = biz_no;
    }

    public String getBiz_cnd() {
        return Biz_cnd;
    }

    public void setBiz_cnd(String biz_cnd) {
        Biz_cnd = biz_cnd;
    }

    public String getBiz_cate() {
        return biz_cate;
    }

    public void setBiz_cate(String biz_cate) {
        this.biz_cate = biz_cate;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getZpcd() {
        return zpcd;
    }

    public void setZpcd(String zpcd) {
        this.zpcd = zpcd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMng_nm() {
        return mng_nm;
    }

    public void setMng_nm(String mng_nm) {
        this.mng_nm = mng_nm;
    }

    public String getMng_phone() {
        return mng_phone;
    }

    public void setMng_phone(String mng_phone) {
        this.mng_phone = mng_phone;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAcno() {
        return acno;
    }

    public void setAcno(String acno) {
        this.acno = acno;
    }

    public String getDeal_yn() {
        return deal_yn;
    }

    public void setDeal_yn(String deal_yn) {
        this.deal_yn = deal_yn;
    }

    public String getReg_dt() {
        return reg_dt;
    }

    public void setReg_dt(String reg_dt) {
        this.reg_dt = reg_dt;
    }

    public String getUse_yn() {
        return use_yn;
    }

    public void setUse_yn(String use_yn) {
        this.use_yn = use_yn;
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
    /*---------------------------------------------------------------*/





}
