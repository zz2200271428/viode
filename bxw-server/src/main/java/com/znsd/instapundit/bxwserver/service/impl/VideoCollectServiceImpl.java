package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.VideoCollectBean;
import com.znsd.instapundit.bxwserver.dao.VideoCollectDao;
import com.znsd.instapundit.param.VideoCollectParam;
import com.znsd.instapundit.service.VideoCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Service
@Component
public class VideoCollectServiceImpl implements VideoCollectService {

    @Autowired
    private VideoCollectDao videoCollectDao;

    @Override
    public List<VideoCollectBean> queryList(VideoCollectParam videoCollectParam) {
        return this.videoCollectDao.queryList(videoCollectParam);
    }

    @Override
    public int getCount() {
        return this.videoCollectDao.getCount();
    }

    @Override
    public List<VideoCollectBean> ListCollectByUserID(Integer id) {
        return videoCollectDao.getCollectByUserId(id);
    }
}
