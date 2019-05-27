package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.ClassifyBean;
import com.znsd.instapundit.bean.PageBean;
import com.znsd.instapundit.bxwserver.dao.ClassifyDao;
import com.znsd.instapundit.service.ClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Service
@Component
public class ClassifyServiceImpl implements ClassifyService {
    @Autowired
    ClassifyDao classifyDao;
    @Override
    public List<ClassifyBean> listPage(PageBean pageBean) {
        return classifyDao.listPage(pageBean);
    }

    @Override
    public int listPageCount() {
        return classifyDao.listPageCount();
    }

    @Override
    public int addClassify(ClassifyBean classifyBean) {
        return classifyDao.addClassify(classifyBean);
    }

    @Override
    public ClassifyBean getClassifyById(Integer id) {
        return classifyDao.getClassifyById(id);
    }

    @Override
    public int updateClassify(ClassifyBean classifyBean) {
        return classifyDao.updateClassify(classifyBean);
    }

    @Override
    public int delete(Integer[] ids) {
        return classifyDao.delete(ids);
    }

    @Override
    public List<ClassifyBean> seek(ClassifyBean classifyBean,PageBean pageBean) {
        return classifyDao.seek(classifyBean,pageBean);
    }

    @Override
    public int seekCount(ClassifyBean classifyBean) {
        return  classifyDao.seekCount(classifyBean);
    }

}
