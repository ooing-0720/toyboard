package com.toyboard.login.dao;

import com.toyboard.login.domain.MemberVO;
import org.apache.ibatis.annotations.*;

import static org.apache.ibatis.mapping.StatementType.PREPARED;

@Mapper
public interface MemberDAO {
    @Select("SELECT * FROM MEMBER WHERE id = #{id} and password = #{password}")
    MemberVO select(MemberVO memberVO);

    @Select("SELECT * FROM MEMBER WHERE id = #{id}")
    MemberVO selectId(String id);

    @Insert("INSERT INTO MEMBER (id, password) VALUES (#{id}, #{password})")
    @SelectKey(statementType = PREPARED, statement = "SELECT NVL(MAX(mem_id), 0) FROM MEMBER", keyProperty = "mem_id", before = false, resultType = int.class)
    void insert(MemberVO memberVO);

    @Delete("DELETE FROM BOARD WHERE id = #{id} and password = #{password}")
    int delete(MemberVO memberVO);

    @Update("UPDATE MEMBER SET id = #{id} where id = #{id} and password = #{password}")
    int updateId(MemberVO memberVO, String id);

    @Update("UPDATE MEMBER SET password = #{password} where id = #{id}")
    int updatePwd(String id, String password);
}
