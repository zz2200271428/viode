package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.TeacherApplyBean;
import com.znsd.instapundit.param.AppUserParam;
import com.znsd.instapundit.result.PagingResult;

public interface AppLecturerService {

    /**
     * @param pageBean
     * @return List<AppUserBean>
     * 分页查询
     */
    PagingResult listPage(AppUserParam pageBean);


    TeacherApplyBean getTeacherByid(Integer id);

    /**
     * 成为大咖
     * @param id
     * @return
     */
    int updateRo_id(Integer id);
}
