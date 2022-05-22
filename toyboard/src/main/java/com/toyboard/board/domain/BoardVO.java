package com.toyboard.board.domain;

import com.toyboard.login.domain.MemberVO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
@Getter
@Setter
public class BoardVO {
    private int seq;

    private MemberVO memberVO;
    @Length(min = 2, message = "제목은 2자 이상 입력하세요.")
    private String title;
    @NotBlank(message = "내용을 입력하세요.")
    private String content;
    @NotBlank(message = "작성자를 입력하세요.")
    private String writer;
    private boolean anonymous;
    private Timestamp regDate;
    private int cnt;
    private int member;

    public BoardVO() {}

    public BoardVO(String title, String content, String writer) {
        super();
        this.memberVO = null;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.cnt = 0;
        this.anonymous = false;
    }
}
