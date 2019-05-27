package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.AppUserBean;
import com.znsd.instapundit.param.AppUserParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AppMasterDao {

    /**
     * @param pageBean
     * @return List<UserBean>
     * 查询所有用户
     */
    List<AppUserBean> listPage(AppUserParam pageBean);


    /*
     * 查询总记录数
     * */
    Integer queryCount(AppUserParam pageBean);


}
