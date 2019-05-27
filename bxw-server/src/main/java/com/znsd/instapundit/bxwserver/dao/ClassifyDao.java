package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.ClassifyBean;
import com.znsd.instapundit.bean.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ClassifyDao {
    List<ClassifyBean> listPage(PageBean pageBean);
    int listPageCount();
    int addClassify(ClassifyBean classifyBean);

    ClassifyBean getClassifyById(@Param("id") Integer id);

    int updateClassify(ClassifyBean classifyBean);

    int delete(@Param("ids") Integer[] ids);

    List<ClassifyBean> seek(@Param("classifyBean")ClassifyBean classifyBean,@Param("pageBean")PageBean pageBean);

    int seekCount(@Param("classifyBean")ClassifyBean classifyBean);
}
