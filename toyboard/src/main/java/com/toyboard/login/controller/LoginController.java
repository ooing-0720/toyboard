package com.toyboard.login.controller;

import com.toyboard.board.domain.BoardVO;
import com.toyboard.login.domain.MemberVO;
import com.toyboard.login.service.MemberService;
import com.toyboard.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes("memberVO")
public class LoginController {

    private final MemberService memberService;

    @Autowired
    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @ModelAttribute("memberVO")
    public MemberVO setMemberVO() {
        return new MemberVO();
    }

    @GetMapping("/member/login")
    public String login(Model model, @ModelAttribute("memberVO") MemberVO memberVO) {
        model.addAttribute("memberVO", memberVO);
        return "/member/login";
    }

    @PostMapping("/member/login")
    public String login(@Validated @ModelAttribute("memberVO") MemberVO memberVO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/member/login";
        } else {
            if (memberService.login(memberVO) != null) {
                memberVO.setMem_id(memberService.login(memberVO).getMem_id());
                return "redirect:/board/list";
            } else {
                model.addAttribute("msg", "아이디 또는 비밀번호가 잘못되었습니다.");
                model.addAttribute("url", "/member/login");
                return "/message/alert";
            }
        }
    }

    @GetMapping("/member/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/member/login";
    }

    @GetMapping("/member/signIn")
    public String signIn(Model model) {
        model.addAttribute("memberVO", new MemberVO());
        return "/member/signIn";
    }

    @PostMapping("/member/signIn")
    public String signIn(@Validated MemberVO memberVO, BindingResult bindingResult, HttpServletRequest request, Model model) {
        String button = request.getParameter("button");

       if (button.equals("회원가입")) {

           if (memberService.checkId(memberVO.getId()) != null) {
               model.addAttribute("msg", "아이디 중복 여부를 확인해주세요.");
               model.addAttribute("url", "/member/signIn");
           } else {

               if (bindingResult.hasErrors()) {
                   model.addAttribute("msg", "회원가입이 실패하였습니다.");
                   model.addAttribute("url", "/member/signIn");
               } else {
                   memberService.signUp(memberVO);
                   model.addAttribute("msg", "회원가입이 되었습니다.");
                   model.addAttribute("url", "/member/login");
               }
           }
       } else {

            if (memberService.checkId(memberVO.getId()) != null) {
                model.addAttribute("msg", "중복된 아이디입니다.");
            } else {
                model.addAttribute("msg", "사용 가능한 아이디입니다.");;
            }
           model.addAttribute("url", "/member/signIn");

       }
        return "/message/alert";
    }
}
