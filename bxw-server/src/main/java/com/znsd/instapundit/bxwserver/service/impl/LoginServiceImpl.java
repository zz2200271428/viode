package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.LoginLogBean;
import com.znsd.instapundit.bean.UserBean;
import com.znsd.instapundit.bxwserver.dao.LoginDao;
import com.znsd.instapundit.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Service
@Component
public class LoginServiceImpl implements LoginService {


    @Autowired
    private LoginDao loginDao;

    @Override
    public UserBean login(UserBean user) {
        return loginDao.login(user);
    }

    @Override
    public void insertLoginInfo(UserBean user1, String ip) {
        LoginLogBean loginLogBean = new LoginLogBean();
        loginLogBean.setUser(user1.getNumber());
        loginLogBean.setIp(ip);
        loginLogBean.setLogin_system("后台端");
        loginDao.insertLoginInfo(loginLogBean);
    }

}
