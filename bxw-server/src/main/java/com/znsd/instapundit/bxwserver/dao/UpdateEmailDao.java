package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.PageBean;
import com.znsd.instapundit.bean.UpdateEmailBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UpdateEmailDao {

    List<UpdateEmailBean> listPage(PageBean pageBean);
    int listPageCount();
    List<UpdateEmailBean> seek(@Param("updateEmailBean")UpdateEmailBean updateEmailBean, @Param("pageBean")PageBean pageBean);
    int seekCount(@Param("updateEmailBean")UpdateEmailBean updateEmailBean);

}
