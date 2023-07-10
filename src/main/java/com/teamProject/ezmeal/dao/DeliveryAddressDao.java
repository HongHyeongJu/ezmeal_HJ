package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.DeliveryAddressDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DeliveryAddressDao {
    private final SqlSession session;
    private static final String namespace = "tb_delivery_address.";

    // 기본 배송지
    public DeliveryAddressDto selectDefaultAddress(Long mbrId) {
        return session.selectOne(namespace + "default_address", mbrId);
    }

    // 배송지 list
    public List<DeliveryAddressDto> addressList(Long mbrId) throws Exception {
        return session.selectList(namespace + "addressList", mbrId);
    }

    // 선택된 배송지 - 수정 || 삭제
    public DeliveryAddressDto selectedAddress(Long addrId) throws Exception {
        return session.selectOne(namespace + "selectedAddress", addrId);
    }

// insert
    // 배송지 추가
    public int insertDeliveryAddress(DeliveryAddressDto deliveryAddressDto) throws Exception{
        return session.insert(namespace + "insertDeliveryAddress", deliveryAddressDto);
    }

// update
    // 배송지 수정
    public int updateDeliveryAddress(DeliveryAddressDto deliveryAddressDto) throws Exception {
        return session.update(namespace + "updateDeliveryAddress", deliveryAddressDto);
    }
    // 기본배송지 변경 전, 모든 기본배송지 n으로 변경
    public int resetAllBasicYNtoN(DeliveryAddressDto deliveryAddressDto) throws Exception {
        return session.update(namespace + "resetAllBasicYNtoN", deliveryAddressDto);
    }
// delete
    // 배송지 삭제
    public int deleteDeliveryAddress(Long addrId) throws Exception {
        return session.delete(namespace + "deleteDeliveryAddress", addrId);
    }
}