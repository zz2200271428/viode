package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.StoreBean;
import com.znsd.instapundit.bean.TeacherApplyBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppTSDao {
    Integer addTeach(TeacherApplyBean teacherApplyBean);
    Integer addStore(StoreBean storeBean);
    Integer UpdateTeach(TeacherApplyBean teacherApplyBean);
    Integer UpdateStore(StoreBean storeBean);
    TeacherApplyBean selectTeachOne(TeacherApplyBean teacherApplyBean);
    TeacherApplyBean selectTeach(TeacherApplyBean teacherApplyBean);
    StoreBean selectStoreBeanOne (StoreBean storeBean);
    StoreBean selectStoreBean (StoreBean storeBean);
    Integer UpdateTeachMoney(TeacherApplyBean teacherApplyBean);
    Integer UpdateStoreMoney(StoreBean storeBean);
}
