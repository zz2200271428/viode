package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.LoginLogBean;
import com.znsd.instapundit.bxwserver.dao.LoginLogDao;
import com.znsd.instapundit.param.LoginLogParam;
import com.znsd.instapundit.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogDao loginLogDao;

    @Override
    public List<LoginLogBean> listPage(LoginLogParam loginLogParam) {
        return loginLogDao.listPage(loginLogParam);
    }

    @Override
    public int getCount(LoginLogParam loginLogParam) {
        return loginLogDao.getCount(loginLogParam);
    }
}
