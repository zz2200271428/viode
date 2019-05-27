package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.TeacherApplyBean;
import com.znsd.instapundit.bxwserver.dao.TeacherDao;
import com.znsd.instapundit.param.StoreParam;
import com.znsd.instapundit.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDao teacherDao;

    //查询申请的讲师信息

   public List<TeacherApplyBean> pageList(StoreParam storePage) {
        return teacherDao.pageList(storePage);
    }

    //总条数
    public Integer counter(StoreParam storePage) {
        return teacherDao.counter(storePage);
    }

    //申请通过
    public  Integer save(Integer[] id) {
        return teacherDao.save(id);
    }

    //拒绝审核
    public  Integer saveDel(Integer[] id) {
        return teacherDao.saveDel(id);
    }
}
