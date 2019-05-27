package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.*;
import com.znsd.instapundit.bxwserver.dao.AppIndexDao;
import com.znsd.instapundit.service.AppIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Service
@Component
public class AppIndexServiceImpl implements AppIndexService {

    @Autowired
    private AppIndexDao appIndexDao;

    @Override
    public List<ImageBean> queryListImageIndex() {
        return this.appIndexDao.queryListImageIndex();
    }

    @Override
    public List<SlideshowBean> queryListSlideshow() {
        return this.appIndexDao.queryListSlideshow();
    }

    @Override
    public List<AppUserBean> queryListMaster() {
        return this.appIndexDao.queryListMaster();
    }

    @Override
    public List<VideoBean> queryListVideo(Integer video_type) {
        return this.appIndexDao.queryListVideo(video_type);
    }

    @Override
    public VideoBean getVideoById(Integer id) {
        return this.appIndexDao.getVideoById(id);
    }

    @Override
    public List<VideoBean> getVideoBySpeId(Integer id) {
        return this.appIndexDao.getVideoBySpeId(id);
    }

    @Override
    public List<SpecialCollectBean> specialList() {
        return this.appIndexDao.specialList();
    }

    @Override
    public List<VideoBean> queryListMasterVideo(Integer video_type) {
        return this.appIndexDao.queryListMasterVideo(video_type);
    }

    @Override
    public AppUserBean userTeacher(Integer id) {
        return this.appIndexDao.userTeacher(id);
    }

    @Override
    public List<AppUserBean> queryListLecturer() {
        return this.appIndexDao.queryListLecturer();
    }
}
