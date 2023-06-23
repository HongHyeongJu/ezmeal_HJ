package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.CartDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl {
    private final CartDao cartDao;


}
