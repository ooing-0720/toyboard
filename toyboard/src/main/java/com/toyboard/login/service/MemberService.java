package com.toyboard.login.service;

import com.toyboard.login.domain.MemberVO;

public interface MemberService {
    MemberVO login(MemberVO memberVO);
    void signUp(MemberVO memberVO);
    MemberVO checkId(String id);
    int withdrawal(MemberVO memberVO);
    int updateId(MemberVO memberVO, String newId);
    int updatePwd(String id, String newPwd);
}
