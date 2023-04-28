package com.solponge.web.view.login;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginForm {
    @NotEmpty(message = "아이디를 입력하세요")
    private String memberId;
    @NotEmpty(message = "비밀번호를 입력하세요")
    private String memberPwd;

}
