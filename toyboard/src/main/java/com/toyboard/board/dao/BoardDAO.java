package com.toyboard.board.dao;

import com.toyboard.board.domain.BoardVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

import static org.apache.ibatis.mapping.StatementType.PREPARED;


@Mapper
public interface BoardDAO {

    @Select("SELECT * FROM BOARD ORDER BY seq")
    List<BoardVO> list();

    @Delete("DELETE FROM BOARD WHERE seq = #{seq}")
    int delete(BoardVO boardVO);

    @Delete("DELETE FROM BOARD")
    int deleteAll();

    @Update("UPDATE BOARD SET title = #{title}, content = #{content}, writer = #{writer} WHERE seq = #{seq}")
    int update(BoardVO boardVO);

    @Insert("INSERT INTO BOARD (title, content, writer, regDate, cnt, member_id) VALUES (#{title}, #{content}, #{writer}, CURRENT_TIMESTAMP, 0, #{member_id})")
    @SelectKey(statementType = PREPARED, statement = "SELECT NVL(MAX(seq), 0) FROM BOARD", keyProperty = "seq", before = false, resultType = int.class)
    void insert(BoardVO boardVO);

    @Select("SELECT * FROM BOARD WHERE seq = #{seq}")
    BoardVO select(int seq);

    @Update("UPDATE BOARD SET cnt = cnt + 1 WHERE seq = #{seq}")
    int updateReadCount(int seq);

    @Select("SELECT * FROM BOARD WHERE title = #{title}")
    List<BoardVO> searchTitle(String title);

    @Select("SELECT * FROM BOARD WHERE writer = #{writer}")
    List<BoardVO> searchWriter(String writer);

    @Select("SELECT * FROM BOARD WHERE member_id = #{member_id}")
    List<BoardVO> searchID(int member_id);
}
