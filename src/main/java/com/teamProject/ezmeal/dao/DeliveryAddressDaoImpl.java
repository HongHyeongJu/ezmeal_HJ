package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.DeliveryAddressDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DeliveryAddressDaoImpl implements DeliveryAddressDao {
    private final SqlSession session;
    private static final String namespace = "tb_delivery_address.";

    @Override
    public DeliveryAddressDto defaultAddress(int mbrId) throws Exception{
        return session.selectOne("defaultAddress", mbrId);
    }

    @Override
    public List<DeliveryAddressDto> editAddress(int mbrId) throws Exception {
        return session.selectList("editAddress", mbrId);
    }
}
