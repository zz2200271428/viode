package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.VideoCollectBean;
import com.znsd.instapundit.param.VideoCollectParam;

import java.util.List;

public interface VideoCollectService {
    /**
     *
     *   查询视图
     * */
    public List<VideoCollectBean> queryList(VideoCollectParam videoCollectParam);

    /*
     *   统计页面
     * */

    public  int getCount();


    List<VideoCollectBean> ListCollectByUserID(Integer id);
}
