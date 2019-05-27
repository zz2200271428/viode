package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.LoginLogBean;
import com.znsd.instapundit.param.LoginLogParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginLogDao {

    List<LoginLogBean> listPage(LoginLogParam loginLogParam);

    int getCount(LoginLogParam loginLogParam);
}
