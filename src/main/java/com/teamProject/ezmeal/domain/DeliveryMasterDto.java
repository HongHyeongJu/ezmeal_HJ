package com.teamProject.ezmeal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryMasterDto {
    private Long dlvar_id; //// 배송번호 (Primary Key)
    private Long ord_id; //// 주문번호
    private Long ord_prod_dtl_id; // 주문상세 번호
    private String invc_id; // 송장번호 | 일반적으로 15자리
    private String bndl_yn; // 묶음 y,n
    private String rcpr; //// 수령자, 고객님/회사명 v
    private String rcpr_phone; //// 수령인전화번호 v
    private String typ; //// 배송유형, 반품/ 주문-묶음/예약?개별예약
    private String start_pnt; //// 배송출발지 , 회사/반품상품수거지
    private String desti; //// 배송도착지, 고객님집/회사 v
    private String desti_dtl; //// 배송상세도착지, ymca 518호 v
    private String req_mtr; //// 배송요청사항: 문앞, 경비실 JS
    private String in_mtd; //// 공동현관출입방법 JS (비밀번호 존재시 비밀번호, 없을시 자유출입)
    private String stus; //// 배송상태코드, 배송상태 table 존재
    private String msg; //// 배송메시지코드 -> 동적으로 만들수 있나??
    private String rcv_sns_yn; //// 배송수신sns, 배송출발, 도착 연락받는 것
    private String box_size; //// 배송박스크기, 대/중/소/극소
    private byte[] cmpl_img; // 배송완료이미지, 배송 완료시 추가
    private String mtd; // 반품방식, 회원착불/회원선불/회사에요청
    private String dlvar_vend; // 반품배송업체
    private Integer rt_dexp; // 반품배송비
    private String rmk; // 비고
    private LocalDateTime in_dtm; // 최초등록일시
    private String in_id; // 최초등록(자)식별번호
    private LocalDateTime up_dtm; // 최종수정일시
    private String up_id; // 최종수정(자)식별번호

    public DeliveryMasterDto(Long ord_id,Long ord_prod_dtl_id, String rcpr, String rcpr_phone, String typ, String start_pnt, String desti, String desti_dtl, String req_mtr, String in_mtd, String stus, String msg, String rcv_sns_yn, String box_size) {
        this.ord_id = ord_id; // 주문번호
        this.ord_prod_dtl_id = ord_prod_dtl_id; // 주문상세번호
        this.rcpr = rcpr; // 수령자
        this.rcpr_phone = rcpr_phone; // 수령자 전화번호
        this.typ = typ; // 배송유형 - 반품, 주문, 묶음, 예약 등등
        this.start_pnt = start_pnt; // 배송출발지
        this.desti = desti; // 배송지 기본
        this.desti_dtl = desti_dtl; // 배송지 상세
        this.req_mtr = req_mtr; // 배송요청사항, 문앞, 경비실
        this.in_mtd = in_mtd; // 공동현관출입방법 + 비밀번호
        this.stus = stus;// 배송상태코드 - dr
        this.msg = msg; // 배송메시지코드
        this.rcv_sns_yn = rcv_sns_yn; // 배송메시지 수신여부
        this.box_size = box_size; // 박스사이즈
    }

}
