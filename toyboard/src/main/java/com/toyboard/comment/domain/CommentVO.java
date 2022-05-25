package com.toyboard.comment.domain;

import com.toyboard.board.domain.BoardVO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Data
@Getter
@Setter
public class CommentVO {
    private int seq;
    private int board_seq;
    private int member_id;
    private String writer;
    @NotBlank(message = "내용을 입력하세요.")
    private String content;
    private Timestamp regDate;
    private boolean anonymous;

    public CommentVO() {}

    public CommentVO(String writer) {
        this.writer = writer;
    }

}

