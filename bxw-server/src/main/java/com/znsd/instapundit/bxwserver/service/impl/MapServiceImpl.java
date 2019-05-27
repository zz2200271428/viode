package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.StoreBean;
import com.znsd.instapundit.bean.VideoBean;
import com.znsd.instapundit.bxwserver.dao.MapDao;
import com.znsd.instapundit.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class MapServiceImpl implements MapService {

    @Autowired
    private MapDao mapDao;

    /*
    *   店铺的访问量
    * */
    @Override
    public List<StoreBean> MapApplyList() {
        return this.mapDao.MapApplyList();
    }

    @Override
    public List<VideoBean> MapVoideList() {
        return this.mapDao.MapVoideList();
    }

    @Override
    public List<VideoBean> MapTypeList() {
        return this.mapDao.MapTypeList();
    }
}
