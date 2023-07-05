package com.teamProject.ezmeal.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamProject.ezmeal.domain.ProductDto;
import com.teamProject.ezmeal.domain.ProductImgDto;
import com.teamProject.ezmeal.service.ProductImgService;
import com.teamProject.ezmeal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import org.json.JSONObject;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductImgService productImgService;


    /*상품 리스트 페이지. 카테고리 코드로 검색해서 상품 리스트를 반환*/
    @GetMapping("/catelist")
    public String productListByCateCd(Model model, String cate_cd) throws SQLException {

        /*카테고리 코드로 검색해서 상품 리스트를 반환*/
        List productCateCdList = productService.getProductListByCateCd(cate_cd);

        /*각 상품의 대표 이미지 Map 받기. Map<String(prod_cd), ProductImgDto> */
        Map<String, ProductImgDto> productCateCdImgMap = productImgService.selectCateCdImgMap(cate_cd);

//        /*각 상품의 할인율 Map 받기 Map<String(prod_cd), Integer(dc_rate)> */
//        Map<String, Integer> discountRateMap = productService.getDiscountRateAll(cate_cd);
//
//        /*각 상품의 리뷰 개수 Map<String(prod_cd), Integer(리뷰카운트)>*/
//        Map<String, Integer> reviewCountMap = productService.countProductReviewAll(cate_cd);
//
//        /*각 상품의 리뷰 별점평균 Map<String(prod_cd), Double(별점 평균)>*/
//        Map<String, Double> reviewStarAvgMap = productService.countProductReviewStarAvgAll(cate_cd);

        model.addAttribute("productCateCdList", productCateCdList);
        model.addAttribute("productCateCdImgMap", productCateCdImgMap);
//        model.addAttribute("discountRateMap",discountRateMap);
//        model.addAttribute("reviewStarAvgMap",reviewStarAvgMap);
//        model.addAttribute("reviewCountMap",reviewCountMap);
        return "productcatelist";
    }

    /* 이부분 다 서비스에서 갖고오도록 1메서드로 가져오기 (서비스-트랜잭션)  맵으로 보내주면 꺼내서 모델에 넣어주기 */
    /*컨트롤러에서 중요한 것. 유효성 검사. 다른 페이지로 잘 넘겨주는 것(페이지 이동). 간단하게. 흐름보이게. 예외처리. */
    /*상품 상세 페이지. 나중에 모델에 상품코드관련 후기, 문의 전달해야함.*/
    @GetMapping("/detail")
    public String productDetailView(Model model, Long prod_cd, RedirectAttributes redirectAttributes) throws SQLException {

        ProductDto productDto = productService.getProductByProdCd(prod_cd);
        /*상품 코드로 검색해서 상품객체 1개를 반환*/
        if (productDto==null){
            redirectAttributes.addFlashAttribute("msg","판매중지된 상품입니다");
            return "redirect:/product/catelist?cate_cd=01";
        }

        /*1개 상품에 대한 모든 이미지 맵으로 받기*/
//        Map<String, String> typeAndUrlMap = productImgService.selectProdCdImgAlltoMap(prod_cd);
        /*이미지 없는 경우 JSP에서 쇼핑몰 LOGO로 대체함*/

//        /*1개 상품에 대한 현재 할인율*/
//        Map<String, Integer> discountRateMap = productService.getDiscountRateOne(prod_cd);
//
//        /*1개 상품에 대한 별점 평균*/
//        Map<String, Double> reviewStarAvgMap = productService.countProductReviewStarAvgOne(prod_cd);
//
//        /*1개 상품에 대한 리뷰카운트*/
//        Map<String, Integer> reviewCountMap = productService.countProductReviewOne(prod_cd);

        model.addAttribute("productDto", productDto);
//        model.addAttribute("productCateCdImgMap", typeAndUrlMap);
//        model.addAttribute("discountRateMap", discountRateMap);
//        model.addAttribute("reviewStarAvgMap", reviewStarAvgMap);
//        model.addAttribute("reviewCountMap", reviewCountMap);

        return "productdetail";
    }
    /*서비스에서 묶어 오기 중간에 에러났을 때 대처는 서비스에서  ->  묶어도 3개로 됨. 고민해보기 값 없을 떄*/
    /*DB가 꺼진다면,,? select도 안됨.  db연결 직접 끊기..ㅋㅋ  */








    /*-------------------------------  관리자용 상품 등록.수정.읽기.삭제 시작  ------------------------------------------*/

    /*관리자 상품 CRUD page - READ */
    @GetMapping("/regist/read")
    public String productRegistPage(Model model, Long prod_cd) throws SQLException, JsonProcessingException {
        /*해당 상품코드의 상품객체 1개 전달*/
        ProductDto productDto = productService.getProductByProdCd(prod_cd);
        /*나중에는 이미지도 전달해야겠지...*/

        /*라디오 태그 값 뷰의 <script>로 전달 (이부분만 추출하는 메서드 만들어야함)*/
        String jsonString = "{\"sub_yn\": \"y\", \"sale_yn\": \"n\", \"dp_yn\": \"y\", \"del_yn\": \"n\", \"inv_yn\": \"y\"}";
        model.addAttribute("jsonString", jsonString);

        /*모델에 담기*/
        model.addAttribute("productDto", productDto);
        model.addAttribute("mode","READ");

        return "productRegistration";
    }



    /*관리자 상품 CRUD page - WRITE 새상품 등록 페이지 */
    @GetMapping("/regist/write")
    public String productMngRegistWritePage(Model model) {

        model.addAttribute("mode","WRITE");

        return "productRegistration";
    }
    /*------------------------------*/

    /*관리자 상품 CRUD page - WRITE */
    @PostMapping("/regist/write")
    public String productMngRegistWritePostPage(@RequestBody ProductDto productDto, Model model) throws SQLException {
        int resultNum = productService.registerProduct(productDto);
//        if(resultNum==1) {
//            model.addAttribute("mode", "WRITE_OK");
//        }
        return "redirect:/product/mng/productlist?cate_cd=02";
    }
    /*------------------------------*/




    /*관리자 상품 CRUD page - UPDATE */ /*수정 시작하려는 화면!  업데이트문 아직*/
    @GetMapping("/regist/modify")
    public String productMngRegistModifyPage(Long prod_cd, Model model) throws SQLException {
        ProductDto productDto = productService.getProductByProdCd(prod_cd);
        /*모델에 담기*/
        model.addAttribute("productDto", productDto);
        model.addAttribute("mode", "UPDATE_MODE");

        return "productRegistration";
    }
    /*------------------------------*/
    /*관리자 상품 CRUD page - UPDATE */ /*수정 다하고 객체가 넘어왔음. 업데이트문 아직*/
    @PostMapping("/regist/modify")
    public String productMngRegistUpdatePage(ProductDto productDto, Model model) throws SQLException {

        /*서비스에서 업데이트 하는 메서드 사용하겠지?
        * 업데이트 성공하면 성공 로우수 1이니까
        * 반환값 1일 때 로직 작성하자
        * 반환값 1이 아닐때는 예외처리 해야해*/
        /*모델에 담기*/


        return "productRegistration";
    }
    /*------------------------------*/


    /*관리자 상품 CRUD page - DELETE */


    @PostMapping("/testProd")
    public String testProd(@RequestBody ProductDto productDto, Model model) throws SQLException {
        /*테스트용이라서 prod_cd 안줌*/
        model.addAttribute("productDto",productDto);

        return "testProd";
    }


    /*-------------------------------  관리자용 상품 관리 페이지 시작  ------------------------------------------*/

    @GetMapping("/mng/productlist")
    public String productMngListPage(Model model, String cate_cd) throws SQLException {

        /*해당 상품코드의 상품객체 1개 전달*/  /*원래는 상품 List인데 지금 테스트중이라 하나만*/  /*매개변수 String prod_cd*/
//        ProductDto productDto = productService.searchProdCd(prod_cd);

        /*카테고리 코드로 검색해서 상품 리스트를 반환*/
        List productCateCdList = productService.getProductListByCateCd(cate_cd);

        /*나중에는 이미지도 전달해야겠지...*/

        /*모델에 담기*/
//        model.addAttribute("productDto", productDto);
        model.addAttribute("productCateCdList", productCateCdList);


        return "productMngList";

    }


}