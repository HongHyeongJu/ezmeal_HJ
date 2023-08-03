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

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
public class WishListController {

    @Autowired
    WishListService wishListService;

    @PostMapping("/wishlist/add")
    public ResponseEntity<String> addToCart(@RequestBody WishListDto wishListDto, @SessionAttribute(required = false) Long memberId) throws SQLException {
        // mbr_id 값 체크
        if (memberId == null) {
            System.out.println("[컨트롤러] mbr_id 없음");
            return ResponseEntity.ok("로그인이 필요합니다");
        }

        wishListDto.setMbr_id(memberId);
        boolean insertResult = wishListService.insertWishList(wishListDto);

        if (insertResult) {
            System.out.println("[컨트롤러] insert 성공");
            return ResponseEntity.ok("찜하기 성공!");
        } else {                     /* insert 실패했을 때 */
            System.out.println("[컨트롤러] insert 실패");
            return ResponseEntity.ok("이미 찜한 상품입니다");
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