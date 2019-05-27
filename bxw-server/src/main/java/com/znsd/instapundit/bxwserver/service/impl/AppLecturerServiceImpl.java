package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.AppUserBean;
import com.znsd.instapundit.bean.TeacherApplyBean;
import com.znsd.instapundit.bxwserver.dao.AppLecturerDao;
import com.znsd.instapundit.param.AppUserParam;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.service.AppLecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class AppLecturerServiceImpl implements AppLecturerService {

    @Autowired
    private AppLecturerDao appLecturerDao;

    /**
     * @param pageBean
     * @return List<UserBean>
     * 分页查询
     */

    @Override
    public PagingResult listPage(AppUserParam pageBean) {
        int count = appLecturerDao.queryCount(pageBean);
        List<AppUserBean> appUserList = appLecturerDao.listPage(pageBean);
        System.out.println(appUserList);
        return PagingResult.success(count, appUserList);
    }

    @Override
    public TeacherApplyBean getTeacherByid(Integer id) {
        return appLecturerDao.getTeacherByid(id);
    }

    /**
     * 成为大咖
     * @param id
     * @return
     */
    @Override
    public int updateRo_id(Integer id) {
        return appLecturerDao.updateRo_id(id);
    }

}
