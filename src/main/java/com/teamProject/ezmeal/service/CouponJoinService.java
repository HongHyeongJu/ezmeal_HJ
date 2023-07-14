package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.CouponJoinDao;
import com.teamProject.ezmeal.domain.joinDomain.CouponJoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponJoinService {
    private final CouponJoinDao couponJoinDao;

    public List<CouponJoinDto> getCouponList(Long mbrId) {
        return couponJoinDao.selectCouponList(mbrId);
    }

    public List<Integer> getCouponPrice(Long mbrCouponId){
        List<Integer> couponPriceList = new ArrayList<>();
        CouponJoinDto couponJoinDto = couponJoinDao.selectCouponPrice(mbrCouponId);
        if (couponJoinDto != null) {
            couponPriceList.add(couponJoinDto.getVal());
            couponPriceList.add(couponJoinDto.getMax_prc());
        }
        return couponPriceList;
    }
}
