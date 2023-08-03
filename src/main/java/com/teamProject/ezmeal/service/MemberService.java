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
            String checkId = memberDao.selectLoginId(id);  // 회원가입폼-id칸에 입력한 id값이 존재하는지
            return checkId != null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // 이메일 중복 체크
    public boolean checkEmailDuplicate(String email) {
        try {
            String checkEmail = memberDao.selectEmail(email);
            return checkEmail != null ; // email이 있으면 true 반환

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int registerMember(MemberDto memberDto)  {
        try {
            // 회원가입 눌렀을때도 아이디가 존재하는지 점검
            String inputId =memberDto.getLgin_id(); // 회원가입란에 적은 id
            String id = memberDao.selectLoginId(inputId);
//validation 처리 - Controller에서
            if (inputId.equals(id)){
                System.out.println("id가 이미 존재합니다.");
                return 0;
            } else {
                System.out.println("아이디 중복 체크 완료"); // 요청한 ID가 DB에 없으면 회원가입한다.
                return memberDao.insertMember(memberDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int removeMember(Long mbr_id) {   // MypageController 회원탈퇴하는 메서드
        // 로그인 중인 회원이 회원탈퇴를 한다.
        // 회원탈퇴할때 어떤 예외가 생길 수가 있을까..?
        // del_yn = Y 인 회원(탈퇴한)은 로그인 할때 막아야함(검증해야함)
        try {
            int mbrWithdrawalCnt = memberDao.deleteMember(mbr_id);
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

    // 회원가입시 장바구니 번호 부여
    public int registerCartSeq(Long memberId) {
        return memberDao.insertCartSeq(memberId);
    }

    // 로그인한 회원정보를 가져오는 로직
    public MemberDto getMemberInfo(Long memberId) {    // 회원정보수정페이지에 띄워줄 회원정보를 조회한다.
        return memberDao.selectMemberInfo(memberId);
    }

    // 회원정보 변경
    public int modifyMember(MemberDto memberDto) {
        try {
            return memberDao.updateMemeber(memberDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // 아이디 찾기
    public String getFindId(String name, String email) {
        try {
            return memberDao.selectFindId(name,email);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // 비밀번호 찾기
    public String getFindPw(String id, String email) {
        return memberDao.selectFindPw(id,email);
    }

}
