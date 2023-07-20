package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.dao.WishListDao;
import com.teamProject.ezmeal.domain.ProductDto;
import com.teamProject.ezmeal.domain.WishListDto;
import com.teamProject.ezmeal.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.sql.SQLException;

@Controller
public class WishListController {

    @Autowired
    WishListService wishListService;

    @PostMapping("/wishlist/add")
    public ResponseEntity<String>  addToCart(@RequestBody WishListDto wishListDto, @SessionAttribute Long memberId) throws SQLException {

        // mbr_id 값 체크
        if(memberId == null) {
            System.out.println("[컨트롤러]mbr_id 없음");
            /* A.나중에는 현재 상품카테고리 정보로 다시 돌아올 수 있게 키워드 챙겨서 로그인창으로 보내기 */
            /* B.그냥 의미없는 클릭으로 아무 반응 안해주기 (이게 좀 더 끌리네요 ) */
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비로그인 찜하기 클릭");
        }

        wishListDto.setMbr_id(memberId);
        boolean insertResult = wishListService.insertWishList(wishListDto);
        /*insert 실패했을 때*/
        if(insertResult){
            System.out.println("[컨트롤러]insert 성공");
            return ResponseEntity.ok("Success message");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("insert 실패");
        }

    }


}























//
///*insert 실패했을 때*/
//        if(!insertResult){
//                System.out.println("[컨트롤러]insert 실패");
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("insert 실패");
//
//                } else {
//                /*insert 성공했을 때*/
//                System.out.println("[컨트롤러]insert 성공");
//                return ResponseEntity.ok("Success message");
//                }