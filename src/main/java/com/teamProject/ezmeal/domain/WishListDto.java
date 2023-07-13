package com.teamProject.ezmeal.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class WishListDto {
    private Long mbr_id, prod_cd;
    private LocalDateTime in_dtm;
    private String in_id;
    private LocalDateTime up_dtm;
    private String up_id;
    /*---------------------------------------------------------------*/

    public WishListDto(Long mbr_id, Long prod_cd) {
        this.mbr_id = mbr_id;
        this.prod_cd = prod_cd;
    }
    public WishListDto(){}

    /*---------------------------------------------------------------*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishListDto that = (WishListDto) o;
        return Objects.equals(mbr_id, that.mbr_id) && Objects.equals(prod_cd, that.prod_cd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mbr_id, prod_cd);
    }
    /*---------------------------------------------------------------*/

    @Override
    public String toString() {
        return "mbr_id=" + mbr_id +
                ", prod_cd=" + prod_cd +
                ", in_dtm=" + in_dtm +
                ", in_id='" + in_id + '\'' +
                '}';
    }

    /*---------------------------------------------------------------*/

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
