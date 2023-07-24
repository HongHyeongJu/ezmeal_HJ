package com.teamProject.ezmeal.service;


import com.teamProject.ezmeal.dao.AdminMemberDao;
import com.teamProject.ezmeal.domain.AdminMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminMemberService {

    private final AdminMemberDao adminMemberDao;

    public AdminMemberDto getLoginAdminInfo(int empId) {
        return adminMemberDao.selectLoginAdminInfo(empId);
    }

}
