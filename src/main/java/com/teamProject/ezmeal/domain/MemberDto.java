package com.teamProject.ezmeal.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

public class MemberDto {
    private long mbr_id;
    private long rfnd_acc_id;
    @NotEmpty(message = "이름을 입력해주세요.")
    private String name;
    private Integer role;
    @NotEmpty(message = "성별을 입력해주세요.")
    private String gender;
    @NotEmpty(message = "생년월일을 입력해 주세요.")
    @Size(min = 10, message = "유효한 생년월일 형식으로 입력해 주세요.")
    private String birth;
    @NotEmpty(message = "휴대폰 번호를 입력해 주세요.")
    @Size(min = 10, message = "유효한 휴대폰 번호를 입력해 주세요.")
    private String phone;
    @NotEmpty(message = "이메일을 입력해 주세요.")
    @Email(message = "유효한 이메일 주소를 입력해 주세요.")
    private String email;
    private String sc_typ;
    private String sc_lgin_id;
    @NotEmpty(message = "아이디를 입력해 주세요.")
    @Size(min = 4, max = 16, message = "4자에서 16자사이의 아이디를 입력하세요")
    private String lgin_id;
    @NotEmpty(message = "비밀번호를 입력해주세요.")
    @Size(min = 10, max = 16, message = "10자이상 영문/숫자/특수문자, 2개이상 조합")
    private String lgin_pw;
    private String rcmdr_cd;
    private String acct_now_stus;
    private String mbr_grd;
    private String mbr_subs_agre;
    private String usg_terms;
    private String psnlinfo_usg_yn;
    private String age_14_over_yn;
    private String markt_ad_dstn_yn;
    private String event_bnef_yn;
    private String del_yn;
    private LocalDateTime in_dtm;
    private String in_id;
    private LocalDateTime up_dtm;
    private String up_id;
    private String mbr_grd_name;
    private String grd_img;

    // 생성자 생성
    public MemberDto(){}

    // 회원가입 최소값만 넣기위한 생성자
    public MemberDto(String name, String gender, String birth, String phone,
                     String email, String lgin_id, String lgin_pw) {
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.phone = phone;
        this.email = email;
        this.lgin_id = lgin_id;
        this.lgin_pw = lgin_pw;
    }

    public MemberDto(long mbr_id, long rfnd_acc_id, String name, Integer role, String gender, String birth, String phone, String email,
                     String sc_typ, String sc_lgin_id, String lgin_id, String lgin_pw, String rcmdr_cd, String acct_now_stus,
                     String mbr_grd, String mbr_subs_agre, String usg_terms, String psnlinfo_usg_yn, String age_14_over_yn,
                     String markt_ad_dstn_yn, String event_bnef_yn, String del_yn, LocalDateTime in_dtm, String in_id,
                     LocalDateTime up_dtm, String up_id) {
        this.mbr_id = mbr_id;
        this.rfnd_acc_id = rfnd_acc_id;
        this.name = name;
        this.role = role;
        this.gender = gender;
        this.birth = birth;
        this.phone = phone;
        this.email = email;
        this.sc_typ = sc_typ;
        this.sc_lgin_id = sc_lgin_id;
        this.lgin_id = lgin_id;
        this.lgin_pw = lgin_pw;
        this.rcmdr_cd = rcmdr_cd;
        this.acct_now_stus = acct_now_stus;
        this.mbr_grd = mbr_grd;
        this.mbr_subs_agre = mbr_subs_agre;
        this.usg_terms = usg_terms;
        this.psnlinfo_usg_yn = psnlinfo_usg_yn;
        this.age_14_over_yn = age_14_over_yn;
        this.markt_ad_dstn_yn = markt_ad_dstn_yn;
        this.event_bnef_yn = event_bnef_yn;
        this.del_yn = del_yn;
        this.in_dtm = in_dtm;
        this.in_id = in_id;
        this.up_dtm = up_dtm;
        this.up_id = up_id;
    }
    // equals and hashcode() 추가
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberDto memberDto = (MemberDto) o;
        return mbr_id == memberDto.mbr_id && rfnd_acc_id == memberDto.rfnd_acc_id && Objects.equals(name, memberDto.name) && Objects.equals(role, memberDto.role) && Objects.equals(gender, memberDto.gender) && Objects.equals(birth, memberDto.birth) && Objects.equals(phone, memberDto.phone) && Objects.equals(email, memberDto.email) && Objects.equals(sc_typ, memberDto.sc_typ) && Objects.equals(sc_lgin_id, memberDto.sc_lgin_id) && Objects.equals(lgin_id, memberDto.lgin_id) && Objects.equals(lgin_pw, memberDto.lgin_pw) && Objects.equals(rcmdr_cd, memberDto.rcmdr_cd) && Objects.equals(acct_now_stus, memberDto.acct_now_stus) && Objects.equals(mbr_grd, memberDto.mbr_grd) && Objects.equals(mbr_subs_agre, memberDto.mbr_subs_agre) && Objects.equals(usg_terms, memberDto.usg_terms) && Objects.equals(psnlinfo_usg_yn, memberDto.psnlinfo_usg_yn) && Objects.equals(age_14_over_yn, memberDto.age_14_over_yn) && Objects.equals(markt_ad_dstn_yn, memberDto.markt_ad_dstn_yn) && Objects.equals(event_bnef_yn, memberDto.event_bnef_yn) && Objects.equals(del_yn, memberDto.del_yn) && Objects.equals(in_dtm, memberDto.in_dtm) && Objects.equals(in_id, memberDto.in_id) && Objects.equals(up_dtm, memberDto.up_dtm) && Objects.equals(up_id, memberDto.up_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mbr_id, rfnd_acc_id, name, role, gender, birth, phone, email, sc_typ, sc_lgin_id, lgin_id, lgin_pw, rcmdr_cd, acct_now_stus, mbr_grd, mbr_subs_agre, usg_terms, psnlinfo_usg_yn, age_14_over_yn, markt_ad_dstn_yn, event_bnef_yn, del_yn, in_dtm, in_id, up_dtm, up_id);
    }

    // toString() 추가
    @Override
    public String toString() {
        return "UserDto{" +
                "mbr_id=" + mbr_id +
                ", rfnd_acc_id=" + rfnd_acc_id +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", gender='" + gender + '\'' +
                ", birth='" + birth + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", sc_typ='" + sc_typ + '\'' +
                ", sc_lgin_id='" + sc_lgin_id + '\'' +
                ", lgin_id='" + lgin_id + '\'' +
                ", lgin_pw='" + lgin_pw + '\'' +
                ", rcmdr_cd='" + rcmdr_cd + '\'' +
                ", acct_now_stus='" + acct_now_stus + '\'' +
                ", mbr_grd='" + mbr_grd + '\'' +
                ", mbr_subs_agre='" + mbr_subs_agre + '\'' +
                ", usg_terms='" + usg_terms + '\'' +
                ", psnlinfo_usg_yn='" + psnlinfo_usg_yn + '\'' +
                ", age_14_over_yn='" + age_14_over_yn + '\'' +
                ", markt_ad_dstn_yn='" + markt_ad_dstn_yn + '\'' +
                ", event_bnef_yn='" + event_bnef_yn + '\'' +
                ", del_yn='" + del_yn + '\'' +
                ", in_dtm=" + in_dtm +
                ", in_id='" + in_id + '\'' +
                ", up_dtm=" + up_dtm +
                ", up_id='" + up_id + '\'' +
                '}';
    }

    // getter, setter 추가
    public long getMbr_id() {
        return mbr_id;
    }

    public void setMbr_id(long mbr_id) {
        this.mbr_id = mbr_id;
    }

    public long getRfnd_acc_id() {
        return rfnd_acc_id;
    }

    public void setRfnd_acc_id(long rfnd_acc_id) {
        this.rfnd_acc_id = rfnd_acc_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getgender() {
        return gender;
    }

    public void setgender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSc_typ() {
        return sc_typ;
    }

    public void setSc_typ(String sc_typ) {
        this.sc_typ = sc_typ;
    }

    public String getSc_lgin_id() {
        return sc_lgin_id;
    }

    public void setSc_lgin_id(String sc_lgin_id) {
        this.sc_lgin_id = sc_lgin_id;
    }

    public String getLgin_id() {
        return lgin_id;
    }

    public void setLgin_id(String lgin_id) {
        this.lgin_id = lgin_id;
    }

    public String getLgin_pw() {
        return lgin_pw;
    }

    public void setLgin_pw(String lgin_pw) {
        this.lgin_pw = lgin_pw;
    }

    public String getRcmdr_cd() {
        return rcmdr_cd;
    }

    public void setRcmdr_cd(String rcmdr_cd) {
        this.rcmdr_cd = rcmdr_cd;
    }

    public String getAcct_now_stus() {
        return acct_now_stus;
    }

    public void setAcct_now_stus(String acct_now_stus) {
        this.acct_now_stus = acct_now_stus;
    }

    public String getMbr_grd() {
        return mbr_grd;
    }

    public void setMbr_grd(String mbr_grd) {
        this.mbr_grd = mbr_grd;
    }

    public String getMbr_subs_agre() {
        return mbr_subs_agre;
    }

    public void setMbr_subs_agre(String mbr_subs_agre) {
        this.mbr_subs_agre = mbr_subs_agre;
    }

    public String getUsg_terms() {
        return usg_terms;
    }

    public void setUsg_terms(String usg_terms) {
        this.usg_terms = usg_terms;
    }

    public String getPsnlinfo_usg_yn() {
        return psnlinfo_usg_yn;
    }

    public void setPsnlinfo_usg_yn(String psnlinfo_usg_yn) {
        this.psnlinfo_usg_yn = psnlinfo_usg_yn;
    }

    public String getAge_14_over_yn() {
        return age_14_over_yn;
    }

    public void setAge_14_over_yn(String age_14_over_yn) {
        this.age_14_over_yn = age_14_over_yn;
    }

    public String getMarkt_ad_dstn_yn() {
        return markt_ad_dstn_yn;
    }

    public void setMarkt_ad_dstn_yn(String markt_ad_dstn_yn) {
        this.markt_ad_dstn_yn = markt_ad_dstn_yn;
    }

    public String getEvent_bnef_yn() {
        return event_bnef_yn;
    }

    public void setEvent_bnef_yn(String event_bnef_yn) {
        this.event_bnef_yn = event_bnef_yn;
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

    public String getMbr_grd_name() {
        return mbr_grd_name;
    }

    public void setMbr_grd_name(String mbr_grd_name) {
        this.mbr_grd_name = mbr_grd_name;
    }

    public String getGrd_img() {
        return grd_img;
    }

    public void setGrd_img(String grd_img) {
        this.grd_img = grd_img;
    }
}
