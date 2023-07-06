package com.teamProject.ezmeal.dao;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CouponIssueDao {
    private final SqlSession session;
    private static final String namespace = "couponIssueDao.";
}
