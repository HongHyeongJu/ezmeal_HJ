package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.ProductReviewDto;
import com.teamProject.ezmeal.domain.WishListDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Repository
public class WishListDao {

    @Autowired
    private SqlSession session;
    private static String namespace = "tb_wishlist.";

    /*위시리스트에 상품 추가하기*/
    public Integer insertWishList(WishListDto wishListDto) throws SQLException {
        System.out.println("DAO의 insertWishList");
        return session.insert(namespace+"insertWishList", wishListDto);
    }

    /*위시리스트 삭제. 어떤 값을 줄지 어떤 방법으로 줄지 몰라서 일단 매개변수로 지움*/
    public Integer deleteWishList(WishListDto wishListDto) throws SQLException {
        System.out.println("DAO의 deleteWishList");
        return session.delete(namespace+"deleteWishList", wishListDto);
    }

    public WishListDto selectWishList(WishListDto wishListDto) throws SQLException {
        HashMap map = new HashMap();
        System.out.println("DAO의 selectWishList");
        return session.selectOne(namespace+"selectOneWishList", wishListDto);
    }

    public List<WishListDto> selectMembersList(Long mbr_id) throws SQLException {
        return session.selectList(namespace+"select_Menbers_all_WishList", mbr_id);
    }





}





/*deleteWishList 매개변수 ishListDto 객체*/
//    public Integer deleteWishList(WishListDto wishListDto) throws SQLException {
//        return session.delete(namespace+"deleteWishList", wishListDto);
//    }






