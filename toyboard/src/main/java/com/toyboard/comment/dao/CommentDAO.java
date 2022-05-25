package com.toyboard.comment.dao;

import com.toyboard.board.domain.BoardVO;
import com.toyboard.comment.domain.CommentVO;
import com.toyboard.login.domain.MemberVO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

import static org.apache.ibatis.mapping.StatementType.PREPARED;

@Mapper
public interface CommentDAO {

    @Select("SELECT * FROM COMMENT WHERE board_seq = #{board_seq} ORDER BY seq")
    List<CommentVO> list(int board_seq);

    @Insert("INSERT INTO COMMENT (writer, content, regDate, board_seq, member_id) VALUES (#{writer}, #{content}, CURRENT_TIMESTAMP, #{board_seq}, #{member_id})")
    @SelectKey(statementType = PREPARED, statement = "SELECT NVL(MAX(seq), 0) FROM COMMENT", keyProperty = "seq", before = false, resultType = int.class)
    void insert(CommentVO commentVO);

    @Delete("DELETE FROM COMMENT where seq = #{seq}")
    int delete(CommentVO commentVO);

    // 게시글을 삭제한 경우
    @Delete("DELETE FROM COMMENT WHERE board_seq = #{board_seq}")
    int deleteBoard(int board_seq);

    // 멤버를 삭제했거나 마이페이지에서 댓글을 모두 삭제한 경우
    @Delete("DELETE FROM COMMENT WHERE member_id = #{member_id}")
    int deleteMember(int member_id);

    @Select("SELECT * FROM COMMENT WHERE member_id = #{member_id}")
    List<CommentVO> myComment(int member_id);

    @Select("SELECT * FROM COMMENT WHERE seq = #{seq}")
    CommentVO select(int seq);
}
