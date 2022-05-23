package com.toyboard.login.controller;

import com.toyboard.board.service.BoardService;
import com.toyboard.login.domain.MemberVO;
import com.toyboard.login.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("memberVO")
public class MemberController {

    private final MemberService memberService;
    private final BoardService boardService;

    @Autowired
    public MemberController(MemberService memberService, BoardService boardService) {
        this.memberService = memberService;
        this.boardService = boardService;
    }

    @ModelAttribute("memberVO")
    public MemberVO setMemberVO() {
        return new MemberVO();
    }


    @GetMapping("/member/{id}/myPage")
    public String myPage(@PathVariable("id") String id, @ModelAttribute("memberVO") MemberVO memberVO, Model model) {
        model.addAttribute("memberVO", memberVO);
        model.addAttribute("msg", memberVO.getId() + "님의 마이페이지");

        model.addAttribute("boardList", boardService.searchId(memberVO.getMember_id()));
        return "/member/myPage";
    }

    @GetMapping("/member/{id}/updatePW")
    public String updatePW(@ModelAttribute("memberVO") MemberVO memberVO, Model model) {
        model.addAttribute("memberVO", memberVO);
        return "/member/updatePW";
    }

    @PostMapping("/member/{id}/updatePW")
    public String updatePW(@PathVariable("id") String id, @Validated @ModelAttribute("memberVO") MemberVO memberVO, Model model, BindingResult bindingResult,
                           String pwd, String newPwd, String newPwdCheck) {
        if (bindingResult.hasErrors()) {
            return "/member/updatePW";
        } else {
            if (!memberVO.getPassword().equals(pwd)) {
                model.addAttribute("msg", "현재 비밀번호를 잘못 입력했습니다.");
                model.addAttribute("url", "/member/" + id + "/updatePW");
                return "/message/alert";
            } else {
                if (newPwd.equals(newPwdCheck)) {
                    memberVO.setPassword(newPwd);
                    memberService.updatePwd(id, memberVO.getPassword());
                    model.addAttribute("msg", "비밀번호가 변경되었습니다.");
                    model.addAttribute("url", "/member/" + id + "/myPage");
                    return "/message/alert";
                } else {
                    model.addAttribute("msg", "입력한 비밀번호와 다릅니다.");
                    model.addAttribute("url", "/member/" + id + "/updatePW");
                    return "/member/updatePW";
                }
            }
        }
    }
}
