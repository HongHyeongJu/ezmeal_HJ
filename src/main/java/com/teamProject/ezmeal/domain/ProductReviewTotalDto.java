package com.teamProject.ezmeal.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class ProductReviewTotalDto {

    private Long prod_cd;
    private Double rvAvg;
    private Integer rvCnt;
    /*-----------------------------------------------------------------------------*/
    public ProductReviewTotalDto(){}

    public ProductReviewTotalDto(Long prod_cd, Double rvAvg,  Integer rvCnt) {
        this.prod_cd = prod_cd;
        this.rvAvg = rvAvg;
        this.rvCnt = rvCnt;
    }
    /*-----------------------------------------------------------------------------*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductReviewTotalDto that = (ProductReviewTotalDto) o;
        return Objects.equals(prod_cd, that.prod_cd) && Objects.equals(rvAvg, that.rvAvg) && Objects.equals(rvCnt, that.rvCnt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prod_cd, rvAvg, rvCnt);
    }
    /*-----------------------------------------------------------------------------*/

    @Override
    public String toString() {
        return "ProductReviewTotalDto{" +
                "prod_cd=" + prod_cd +
                ", rvAvg=" + rvAvg +
                ", rvCnt=" + rvCnt +
                '}';
    }

    /*-----------------------------------------------------------------------------*/

    public Long getProd_cd() {
        return prod_cd;
    }

    public void setProd_cd(Long prod_cd) {
        this.prod_cd = prod_cd;
    }

    public Double getRvAvg() {
        return rvAvg;
    }

    public void setRvAvg(Double rvAvg) {
        this.rvAvg = rvAvg;
    }

    public Integer getRvCnt() {
        return rvCnt;
    }

    public void setRvCnt(Integer rvCnt) {
        this.rvCnt = rvCnt;
    }
}
