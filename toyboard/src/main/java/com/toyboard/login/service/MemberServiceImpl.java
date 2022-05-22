package com.toyboard.login.service;

import com.toyboard.login.dao.MemberDAO;
import com.toyboard.login.domain.MemberVO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Setter // 필요한가?
@Getter
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired private MemberDAO memberDAO;

    @Override
    public MemberVO login(MemberVO memberVO) {
        return memberDAO.select(memberVO);
    }

    @Override
    public void signUp(MemberVO memberVO) {
        memberDAO.insert(memberVO);
    }

    @Override
    public MemberVO checkId(String id) {
        return memberDAO.selectId(id);
    }

    @Override
    public int withdrawal(MemberVO memberVO) {
        return memberDAO.delete(memberVO);
    }

    @Override
    public int updateId(MemberVO memberVO, String newId) {
        return memberDAO.updateId(memberVO, newId);
    }

    @Override
    public int updatePwd(String id, String newPwd) {
        return memberDAO.updatePwd(id, newPwd);
    }
}
