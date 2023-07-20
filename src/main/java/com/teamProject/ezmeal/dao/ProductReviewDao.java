package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.ProductDto;
import com.teamProject.ezmeal.domain.ProductReviewDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductReviewDao {

    @Autowired
    private SqlSession session;
    private static String namespace = "tb_product_review.";

    /*개인 회원의 자신의 리뷰 모아보기*/
    public List<ProductReviewDto> selectAllReviewByMember(Long mbr_id) throws SQLException {
        return session.selectList("get_all_member_review", mbr_id);
    }

    /* (리뷰 시퀀스, 회원 PK )로 리뷰 객체 얻기*/
    public ProductReviewDto selectOneReviewByMember(Long revw_id , Long mbr_id) throws SQLException {
        HashMap map = new HashMap<>();
        map.put("revw_id", revw_id);
        map.put("mbr_id", mbr_id);
        return session.selectOne("get_one_review", map);
    }

    /*가장 최근에 생성한 리뷰 SEQ 얻기 (TDD용)*/
    public ProductReviewDto selectLastInsertReview() throws SQLException {
        return session.selectOne("get_last_insert_review");
    }


    /*리뷰 삭제하기(영구삭제)*/
    public Integer deleteReviewForTdd(Long revw_id) throws SQLException {
        return session.delete("delete_my_review_for_tdd", revw_id);
    }


    /*리뷰 삭제하기(del_yn만 'y'로)*/
    public Integer deleteReview(Long revw_id, Long mbr_id) throws SQLException {
        HashMap map = new HashMap<>();
        map.put("revw_id", revw_id);
        map.put("mbr_id", mbr_id);
        return session.update("delete_my_review_del_y", map);
    }


    /*리뷰 생성하기*/
    public Integer insertReview(ProductReviewDto productReviewDto) throws SQLException {
        return session.insert("insertProductReview", productReviewDto);
    }


    /*리뷰 수정하기*/
    public Integer UpdateReview(ProductReviewDto productReviewDto) throws SQLException {
        return session.update("update_review", productReviewDto);
    }

//    /*리뷰 공개여부만 변경*/
//    public Integer UpdateReviewOpenYN(String revw_opub_yn) throws SQLException {
//        return session.update("update_review_open_yn", revw_opub_yn);
//    } 리뷰 상세가서 비공개 체크 누르면 그때 반영되게 하면 될듯 ㅇㅇ


    /*1상품 리뷰 개수*/
    public Integer countReviewOneProduct(Long prod_cd) throws SQLException {
        return session.selectOne(namespace+"get_review_count", prod_cd);
    }


    /*1상품 리뷰 평점*/
    public Double reviewAverageOneProduct(Long prod_cd) throws SQLException {
        return session.selectOne(namespace+"get_review_average", prod_cd);
    }


    /*   개별 상품에 대한 리뷰 묶음(공개-y,삭제-n) */
    public List<ProductReviewDto> getAllReviewByProdCd(Long prod_cd) throws SQLException {
        return session.selectList(namespace+"get_all_review_by_prod_cd", prod_cd);
    }



    /*해당 카테고리 상품들의 개별 리뷰 평점 */
    public Map<Long,Double> selectReviewAvgForProdList(String cate_cd) throws SQLException {
        return session.selectMap(namespace+"get_review_average_for_cate_list",cate_cd,"prod_cd");
    }


    /*해당 카테고리 상품의 개별 전체리뷰수 */
    public Map<Long,Integer> selectReviewCntForProdList(String cate_cd) throws SQLException {
        return session.selectMap(namespace+"get_review_count_for_cate_list",cate_cd,"prod_cd");
    }

    /*또 만들게 있나? 리뷰...상품 리뷰... 아 리뷰객체 반환해야 후기글에 반복문 돌릴 수 있음.
    *개별 상품에 대한 리뷰 묶음(공개-y,삭제-n)
    *한 회원에 대한 리뷰묶음(배송완료 역순, 역순이란걸 어떻게 정하지...-> 책을 봐라.)*/






}
