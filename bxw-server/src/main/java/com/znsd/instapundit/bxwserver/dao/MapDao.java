package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.StoreBean;
import com.znsd.instapundit.bean.VideoBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MapDao {

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
