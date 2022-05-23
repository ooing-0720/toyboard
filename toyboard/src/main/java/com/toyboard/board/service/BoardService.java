package com.toyboard.board.service;

import com.toyboard.board.domain.BoardVO;

import java.util.List;


public interface BoardService {
    List<BoardVO> list();
    int delete(BoardVO boardVO);
    int edit(BoardVO boardVO);
    void write(BoardVO boardVO);
    BoardVO read(int seq);
    List<BoardVO> searchTitle(String title);
    List<BoardVO> searchWriter(String writer);
    List<BoardVO> searchId(int member_id);
}
