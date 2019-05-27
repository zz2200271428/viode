package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.VideoCollectBean;
import com.znsd.instapundit.param.VideoCollectParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VideoCollectDao {

    /**
     *
     *   查询视图
     * */
    public List<VideoCollectBean> queryList(VideoCollectParam videoCollectParam);

    /*
     *   统计页面
     * */

    public  int getCount();


    List<VideoCollectBean> getCollectByUserId(@Param("id") Integer id);
}
