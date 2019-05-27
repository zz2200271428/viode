package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.LoginLogBean;
import com.znsd.instapundit.param.LoginLogParam;

import java.util.List;

public interface LoginLogService {

    List<LoginLogBean> listPage(LoginLogParam loginLogParam);

    int getCount(LoginLogParam loginLogParam);
}
