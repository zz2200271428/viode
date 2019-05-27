package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.AppUserBean;
import com.znsd.instapundit.bean.TeacherApplyBean;
import com.znsd.instapundit.param.AppUserParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AppLecturerDao {

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


    TeacherApplyBean getTeacherByid(@Param("id") Integer id);

    int updateRo_id(@Param("id")Integer id);
}
