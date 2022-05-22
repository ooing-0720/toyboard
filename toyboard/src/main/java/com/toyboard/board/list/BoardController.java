package com.toyboard.board.list;

import com.toyboard.board.domain.BoardVO;
import com.toyboard.board.service.BoardService;
import com.toyboard.login.domain.MemberVO;
import com.toyboard.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("memberVO")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/board/list")
    public String list(@ModelAttribute("memberVO") MemberVO memberVO, Model model) {
        if (memberVO != null) {
            model.addAttribute("msg", "Welcome " + memberVO.getId() + "!");
        } else {
            model.addAttribute("msg", "Welcome!");
        }

        model.addAttribute("boardList", boardService.list());
        return "/board/list";
    }

    @RequestMapping("/board/read/{seq}")
    public String read(Model model, @PathVariable("seq") int seq, @ModelAttribute("memberVO") MemberVO memberVO) {
        model.addAttribute("boardVO", boardService.read(seq));
        model.addAttribute("memberVO", memberVO);
        return "/board/read";
    }

    @GetMapping("/board/write")
    public String write(Model model, @ModelAttribute("memberVO") MemberVO memberVO) {
        BoardVO boardVO = new BoardVO();

        model.addAttribute("boardVO", boardVO);
        model.addAttribute("memberVO", memberVO);
        return "/board/write";
    }

    @PostMapping("/board/write")
    public String write(@Validated BoardVO boardVO, BindingResult bindingResult, @ModelAttribute("memberVO") MemberVO memberVO)  {
        if (bindingResult.hasErrors()) {
            return "/board/write";
        } else {
            if (boardVO.isAnonymous()) {
                boardVO.setWriter("익명");
            }
            boardVO.setMember(memberVO.getMem_id());
            boardService.write(boardVO);
            return "redirect:/board/list";
        }
    }

    @GetMapping("/board/edit/{seq}")
    public String edit(@PathVariable("seq") int seq, Model model) {
        BoardVO boardVO = boardService.read(seq);
        model.addAttribute("boardVO", boardVO);
        return "/board/edit";
    }

    @PostMapping("/board/edit/{seq}")
    public String edit(@Validated BoardVO boardVO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/board/edit";
        } else {
            if (boardVO.isAnonymous()) {
                boardVO.setWriter("익명");
            }
            boardService.edit(boardVO);
            return "redirect:/board/list";
        }
    }

    @GetMapping("/board/delete/{seq}")
    public String delete(@PathVariable("seq") int seq, Model model) {
        BoardVO boardVO = new BoardVO();
        boardVO.setSeq(seq);
        boardService.delete(boardVO);

        model.addAttribute("msg", "삭제되었습니다.");
        model.addAttribute("url", "/board/list");

        return "/message/alert";
    }

    @PostMapping("/board/list")
    public String search(@ModelAttribute("memberVO") MemberVO memberVO, Model model, String select, String search) {

        if (search.isEmpty()) {
            model.addAttribute("boardList", boardService.list());
        } else {
            if (select.equals("title")) {
                model.addAttribute("boardList", boardService.searchTitle(search));
            } else {
                model.addAttribute("boardList", boardService.searchWriter(search));
            }
        }

        if (memberVO != null) {
            model.addAttribute("msg", "Welcome " + memberVO.getId() + "!");
        } else {
            model.addAttribute("msg", "Welcome!");
        }

        return "/board/list";
    }
}
