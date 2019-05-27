package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.StoreBean;
import com.znsd.instapundit.bean.VideoBean;

import java.util.List;

public interface MapService {

    /*
     *   查询店铺的访问量
     * */
    public List<StoreBean> MapApplyList();

    /*
     *  查询排前五播放视频量
     * */
    public  List<VideoBean> MapVoideList();

    /*
     *   查询type 播放量
     * */
    public  List<VideoBean> MapTypeList();

}
