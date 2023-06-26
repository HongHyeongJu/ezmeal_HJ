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

    // 배달 할 때 mbrId cart에서는 Long으로 변경했으니깐 나중에 커밋 후 long 변경 확인 필요 : commit -> cart로부터 merge 받기
    public DeliveryAddressDto defaultAddress(Long mbrId) throws Exception {
        return session.selectOne("defaultAddress", mbrId);
    }

    public List<DeliveryAddressDto> addressList(Long mbrId) throws Exception {
        return session.selectList("addressList", mbrId);
    }
}