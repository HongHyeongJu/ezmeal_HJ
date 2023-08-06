package com.teamProject.ezmeal.controller;

import com.teamProject.ezmeal.domain.ProductReviewDto;
import com.teamProject.ezmeal.service.OrderDetailService;
import com.teamProject.ezmeal.service.ProductReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/modal")
@RequiredArgsConstructor
public class ModalController {
    private final ProductReviewService productReviewService;
    private final OrderDetailService orderDetailService;
    @GetMapping("/review/{orderId}")
    public String orderReviewModal(@PathVariable String orderId, Model model){
        System.out.println(orderId); // orderId
        model.addAttribute("orderId", orderId);
        return "modal_order_product_review";
    }
    @PostMapping("/review/{orderId}")
    public String hi(@PathVariable Long orderId, @SessionAttribute Long memberId, ProductReviewDto productReviewDto, Long ord_prod_dtl_id){
        productReviewDto.setOrd_id(orderId);
        productReviewDto.setMbr_id(memberId);
        System.out.println("modalController - postMapping 시작 : " + orderId );
        System.out.println("productReviewDto = " + productReviewDto);
        /* 넘겨야하는 값
        * ord_id, prod_cd, writer : order_detail로부터 가지고 오기
        * mbr_id == writer : memberId session으로 받을 필요없이 join으로 이름 받아오기
        * title, stmt, star : user
        * */
        productReviewService.writeReviewFromOrderDetail(productReviewDto); // 리뷰 작성
        // todo. orderDetail update 해야함.
        orderDetailService.setOrderReview(ord_prod_dtl_id);
        return "redirect:/order/detail/{orderId}";
    }

}
