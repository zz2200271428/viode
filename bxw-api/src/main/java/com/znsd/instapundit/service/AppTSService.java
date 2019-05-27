package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.StoreBean;
import com.znsd.instapundit.bean.TeacherApplyBean;

public interface AppTSService {
    Integer addTeach(TeacherApplyBean teacherApplyBean);
    Integer addStore(StoreBean storeBean);
    Integer UpdateTeach(TeacherApplyBean teacherApplyBean);
    Integer UpdateStore(StoreBean storeBean);
    TeacherApplyBean selectTeachOne(TeacherApplyBean teacherApplyBean);
    StoreBean selectStoreBeanOne (StoreBean storeBean);
    TeacherApplyBean select(TeacherApplyBean teacherApplyBean);
    StoreBean selectStoreBean (StoreBean storeBean);
}
