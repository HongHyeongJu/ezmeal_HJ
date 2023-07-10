package com.teamProject.ezmeal.service;

import com.teamProject.ezmeal.dao.MemberDao;
import com.teamProject.ezmeal.domain.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    MemberDao memberDao;
    // service는 예외 되던지기 해야함, 처리할 수 없는건 Controller로
    // tx처리가 Service의 핵심
    public boolean checkIdDuplicate(String id)  {
        // 중복 체크 로직 구현
        try {
            String checkId = memberDao.getLoginId(id);  // 회원가입폼-id칸에 입력한 id값이 존재하는지
            return checkId != null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int signup(MemberDto memberDto)  {
        try {
            // 회원가입 눌렀을때도 아이디가 존재하는지 점검
            String inputId =memberDto.getLgin_id(); // 회원가입란에 적은 id
            String id = memberDao.getLoginId(inputId);
//validation 처리 - Controller에서
            if (inputId.equals(id)){  // 요청한 ID가 DB에 없으면 회원가입한다.
                System.out.println("id가 이미 존재합니다.");
                throw new Exception();
            } else {
                System.out.println("아이디 중복 체크");
                return memberDao.registerMember(memberDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int withdrawal(Long mbr_id) {   // MypageController 회원탈퇴하는 메서드
        // 로그인 중인 회원이 회원탈퇴를 한다.
        // 회원탈퇴할때 어떤 예외가 생길 수가 있을까..?
        // del_yn = Y 인 회원(탈퇴한)은 로그인 할때 막아야함(검증해야함)
        try {
            int mbrWithdrawalCnt = memberDao.mbrWithdrawal(mbr_id);
            if (mbrWithdrawalCnt != 1) {
                throw new RuntimeException();
            }
// 회원이 할 건 없음.
            return mbrWithdrawalCnt;
        } catch (Exception e) {
            e.printStackTrace();
//  탈퇴 처리가 실패 했을때, 몇초후 retry, 연결안되는지 체크,
//  retry 10번 후에 예외처리 (몇번, 몇초간격)
            throw new RuntimeException(e);
        }
    }

    public MemberDto mbrInfo(Long memberId) throws Exception {    // 회원정보수정페이지에 띄워줄 회원정보를 조회한다.
        return memberDao.getMemberInfo(memberId);
    }

    public int modify(MemberDto memberDto) {
        try {
            return memberDao.mbrModify(memberDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
