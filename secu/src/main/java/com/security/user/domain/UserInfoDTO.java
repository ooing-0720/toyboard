package com.security.user.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDTO {
    private String email;
    private String password;
    private String auth;
}
