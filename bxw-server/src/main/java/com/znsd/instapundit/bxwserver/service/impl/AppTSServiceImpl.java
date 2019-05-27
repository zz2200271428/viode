package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.StoreBean;
import com.znsd.instapundit.bean.TeacherApplyBean;
import com.znsd.instapundit.bxwserver.dao.AppTSDao;
import com.znsd.instapundit.service.AppTSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Component
public class AppTSServiceImpl implements AppTSService {
    @Autowired
    private AppTSDao appTSDao;
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public Integer addTeach(TeacherApplyBean teacherApplyBean) {

        appTSDao.addTeach(teacherApplyBean);

        return  appTSDao.UpdateTeachMoney(teacherApplyBean);
    }
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public Integer addStore(StoreBean storeBean) {
        appTSDao.UpdateStoreMoney(storeBean);
        return appTSDao.addStore(storeBean);
    }
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public Integer UpdateTeach(TeacherApplyBean teacherApplyBean) {
        return appTSDao.UpdateTeach(teacherApplyBean);
    }
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public Integer UpdateStore(StoreBean storeBean) {
        return appTSDao.UpdateStore(storeBean);
    }

    @Override
    public TeacherApplyBean selectTeachOne(TeacherApplyBean teacherApplyBean) {
        return appTSDao.selectTeachOne(teacherApplyBean);
    }

    @Override
    public StoreBean selectStoreBeanOne(StoreBean storeBean) {
        return appTSDao.selectStoreBeanOne(storeBean);
    }

    @Override
    public TeacherApplyBean select(TeacherApplyBean teacherApplyBean) {
        return appTSDao.selectTeach(teacherApplyBean);
    }

    @Override
    public StoreBean selectStoreBean(StoreBean storeBean) {
        return appTSDao.selectStoreBean(storeBean);
    }

}
