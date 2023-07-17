package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.ProductDiscountDto;
import com.teamProject.ezmeal.domain.ProductInventoryDto;
import com.teamProject.ezmeal.domain.restAPIDomain.InventoryData;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Repository
public class ProductInventoryDao {

    @Autowired
    private SqlSession session;
    private static String namespace = "tb_product_inventory.";

    /*재고 로우 생성*/
    public Integer insertProductInventory(ProductInventoryDto productInventoryDto) throws SQLException {
        return session.insert(namespace+"insert_product_inventory", productInventoryDto);
    }

    /*상품코드로 재고 현황 찾기*/
    public ProductInventoryDto selectOneProductInventory(Long prod_cd) throws SQLException {
        return session.selectOne(namespace+"select_one_product_inventory", prod_cd);
    }

    /*주문/반품으로 인한 재고 변동*/
    public Integer updateCurrentInvByOrderAndReturn(Long prod_cd, Integer chg_inv) throws SQLException {
        HashMap map = new HashMap<>();
        map.put("prod_cd",prod_cd);
        map.put("chg_inv",chg_inv);
        return session.update(namespace+"update_curr_inv_by_order_return", map);
    }

    /*거래처 입고시*/
    public Integer updateCurrentInvByIncoming(Long prod_cd, Integer chg_inv, String last_gr_dt) throws SQLException {
        HashMap map = new HashMap<>();
        map.put("prod_cd",prod_cd);
        map.put("chg_inv",chg_inv);
        map.put("last_gr_dt",last_gr_dt);
        return session.update(namespace+"update_curr_inv_by_incoming", map);
    }

    /*현재 관리중이 재고현황 List 받기*/
    public List<ProductInventoryDto> selectAllProductInventory() throws SQLException {
        return session.selectList(namespace+"select_all_product_inventory");
    }

    /*위험재고 List 받기*/
    public List<ProductInventoryDto> selectDangerousProductInventory() throws SQLException {
        return session.selectList(namespace+"select_dangerous_product_inventory");
    }

    /*재고현황 대량(List) 변경*/
    public Integer updateProductInventory(ProductInventoryDto productInventoryDto) throws SQLException {
        return session.update(namespace+"update_product_inventory", productInventoryDto);
    }

    /*재고 진짜삭제*/
    public Integer deleteProductInventForTdd(Long prod_cd) throws SQLException {
        return session.delete(namespace+"delete_product_inventory_for_tdd", prod_cd);
    }

    /*재고 의미적 삭제*/
    public Integer deleteYesProductInventory(Long prod_cd) throws SQLException {
        return session.update(namespace+"update_product_inventory_del_y", prod_cd);
    }

    // taewan : 상품주문 후 재고 update
    public Integer updateInventoryAfterPayment(List<InventoryData> inventoryDataList){
        return session.update(namespace + "update_qty_after_payment", inventoryDataList);
    }



}
