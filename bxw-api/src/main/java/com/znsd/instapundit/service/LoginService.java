package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.UserBean;

public interface LoginService {
    UserBean login(UserBean user);

    void insertLoginInfo(UserBean user1, String ip);

}
