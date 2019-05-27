package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.ClassifyBean;
import com.znsd.instapundit.bean.PageBean;

import java.util.List;

public interface ClassifyService {
    List<ClassifyBean> listPage(PageBean pageBean);
    int listPageCount();
    int addClassify(ClassifyBean classifyBean);
    ClassifyBean getClassifyById(Integer id);
    int updateClassify(ClassifyBean classifyBean);
    int delete(Integer[] ids);
    List<ClassifyBean> seek(ClassifyBean classifyBean,PageBean pageBean);
    int seekCount(ClassifyBean classifyBean);
}
