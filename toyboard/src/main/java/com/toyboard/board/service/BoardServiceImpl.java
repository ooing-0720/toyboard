package com.toyboard.board.service;

import com.toyboard.board.dao.BoardDAO;
import com.toyboard.board.domain.BoardVO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Getter
@Service
public class BoardServiceImpl implements BoardService{

    @Autowired private BoardDAO boardDAO;

    @Override
    public List<BoardVO> list() {
        return boardDAO.list();
    }

    @Override
    public int delete(BoardVO boardVO) {
        return boardDAO.delete(boardVO);
    }

    @Override
    public int edit(BoardVO boardVO) {
        return boardDAO.update(boardVO);
    }

    @Override
    public void write(BoardVO boardVO) {
        boardDAO.insert(boardVO);
    }

    @Override
    public BoardVO read(int seq) {
        boardDAO.updateReadCount(seq);
        return boardDAO.select(seq);
    }

    @Override
    public List<BoardVO> searchTitle(String title) {
        return boardDAO.searchTitle(title);
    }

    @Override
    public List<BoardVO> searchWriter(String writer) {
        return boardDAO.searchWriter(writer);
    }

    @Override
    public List<BoardVO> searchId(int member) {
        return boardDAO.searchID(member);
    }

}
