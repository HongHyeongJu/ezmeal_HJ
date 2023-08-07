package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.ProductImgDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductImgDao {

    @Autowired
    private SqlSession session;
    private static String namespace = "tb_product_img.";

    /*ProductImgDao에서 DB에 연결해서 하는 작업들 뭐가 있을까 */
    /*상품 코드로 이미지 찾기(매개변수 상품 코드,이미지 타입), 카테고리별 검색 상품 List에 맞는 대표사진만*/


    /* 상품코드에 해당하는 모든 이미지 찾기 */
    public List<ProductImgDto> selectProdCdImgAll(Long prod_cd) throws SQLException {
        return session.selectList(namespace+"select_prod_img_all", prod_cd);
    }

    /* 상품코드로  특정 이미지 찾기 */
    public ProductImgDto selectProdCdTypImg(Long prod_cd, String typ) throws SQLException {
        Map map = new HashMap<>();
        map.put("prod_cd", prod_cd);
        map.put("typ", typ);
        return session.selectOne(namespace+"select_prod_type_img", map);
    }

    /* 카테고리로 검색한 상품리스트의 대표이미지 리스트 */
    public List<ProductImgDto> selectCateCdImgTyp(String cate_cd) throws SQLException {
        return session.selectList(namespace+"select_cate_cd_img_list", cate_cd);
    }

    public List<ProductImgDto> selectAllImgTypRepresent() throws SQLException {
        return session.selectList(namespace+"select_all_img_list_typ_is_represent");
    }

    /*이미지 INSERT*/
    public int insertImgOne(ProductImgDto productImgDto) throws SQLException {
        return session.insert(namespace+"insert_prod_img", productImgDto);
    }



}

