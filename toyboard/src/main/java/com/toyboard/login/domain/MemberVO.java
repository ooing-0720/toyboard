package com.toyboard.login.domain;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Data
@Getter
@Setter
public class MemberVO {
    @Pattern(regexp = "[a-zA-Z0-9_]{3,10}", message = "id를 알파벳 및 숫자로 이루어진 3~10자로 입력해주세요")
    private String id;

    @Pattern(regexp = "[a-zA-Z0-9]{6,12}", message = "pw를 알파벳 및 숫자로 이루어진 6~12자로 입력해주세요")
    private String password;

    private int member_id;

    public MemberVO() {};

    public MemberVO(String id, String password, int member_id) {
        this.id = id;
        this.password = password;
        this.member_id = member_id;
    }

    public String getPassword(String id) {
        return password;
    }

}
