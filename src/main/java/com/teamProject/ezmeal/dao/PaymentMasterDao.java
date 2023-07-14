package com.teamProject.ezmeal.dao;

import com.teamProject.ezmeal.domain.OrderMasterDto;
import com.teamProject.ezmeal.domain.PaymentMasterDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentMasterDao {
    private final SqlSession session;
    private static final String namespace = "paymentMasterDao.";
    // 결제 완료시 결제 master 1st insert
    public int insertPaymentMaster(PaymentMasterDto paymentMasterDto){
        return session.insert(namespace + "insertPayment", paymentMasterDto);
    }
}
