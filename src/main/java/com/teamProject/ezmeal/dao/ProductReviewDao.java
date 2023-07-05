package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.ProductDto;
import com.teamProject.ezmeal.domain.ProductReviewDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Map;

@Repository
public class ProductReviewDao {

    @Autowired
    private SqlSession session;
    private static String namespace = "tb_product_review.";

    /*1상품 리뷰 개수*/
    public Integer countReview(Long prod_cd) throws SQLException {
        return session.selectOne(namespace+"get_review_count", prod_cd);
    }

    /*1상품 리뷰 평점*/
    public Double ReviewAverage(Long prod_cd) throws SQLException {
        return session.selectOne(namespace+"get_review_average", prod_cd);
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
