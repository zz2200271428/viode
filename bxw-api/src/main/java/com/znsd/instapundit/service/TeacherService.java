package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.TeacherApplyBean;
import com.znsd.instapundit.param.StoreParam;

import java.util.List;

public interface TeacherService {
    //查询申请的讲师信息
    List<TeacherApplyBean> pageList(StoreParam storePage);

    //总条数
    Integer counter(StoreParam storePage);

    //申请通过
    Integer save(Integer[] id);

    //拒绝审核
    Integer saveDel(Integer []id);

}
