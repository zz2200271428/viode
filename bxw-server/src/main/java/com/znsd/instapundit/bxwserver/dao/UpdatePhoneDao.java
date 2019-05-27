package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.PageBean;
import com.znsd.instapundit.bean.UpdatePhoneBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface UpdatePhoneDao {
    List<UpdatePhoneBean> listPage(PageBean pageBean);
    int listPageCount();
    List<UpdatePhoneBean> seek(@Param("updatePhoneBean")UpdatePhoneBean updatePhoneBean, @Param("pageBean")PageBean pageBean);
    int seekCount(@Param("updatePhoneBean")UpdatePhoneBean updatePhoneBean);
}
