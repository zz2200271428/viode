package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.AppUserBean;
import com.znsd.instapundit.bean.SpecialColumnBean;
import com.znsd.instapundit.bean.VideoBean;
import com.znsd.instapundit.bxwserver.dao.AppTeacherDao;
import com.znsd.instapundit.service.AppTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class AppTeacherServiceImpl implements AppTeacherService {
    /**
     * 推荐免费的三条视频
     */
    @Autowired
    private AppTeacherDao appTeacherDao;


    @Override
    public List<VideoBean> listVideo(Integer id, Integer video_type) {
        return appTeacherDao.listVideo(id,video_type);
    }

    /**
     * 讲师的信息
     *
     * @param id
     * @return
     */
    @Override
    public AppUserBean userTeacher(Integer id) {
        return appTeacherDao.userTeacher(id);
    }

    /**
     * 推荐专栏前三条
     *
     * @param id
     * @return
     */
    @Override
    public List<SpecialColumnBean> listColumn(Integer id) {
        return appTeacherDao.listColumn(id);
    }

}
