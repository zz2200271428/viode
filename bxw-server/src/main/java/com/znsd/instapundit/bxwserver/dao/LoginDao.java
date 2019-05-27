package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.LoginLogBean;
import com.znsd.instapundit.bean.UserBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginDao {
    UserBean login(UserBean user);

    void insertLoginInfo(LoginLogBean loginLogBean);

}
