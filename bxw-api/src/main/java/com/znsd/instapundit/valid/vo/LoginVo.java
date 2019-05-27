package com.znsd.instapundit.valid.vo;

import com.znsd.instapundit.valid.IsPassword;
import com.znsd.instapundit.valid.IsUsername;

public class LoginVo {

    @IsUsername
    private String username;
    @IsPassword
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
