package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.AppUserBean;
import com.znsd.instapundit.bean.SpecialColumnBean;
import com.znsd.instapundit.bean.VideoBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AppTeacherDao {
    /**
     * 推荐视频前三条
     *
     * @param id
     * @return
     */
    public List<VideoBean> listVideo(@Param("id") Integer id,@Param("video_type")Integer video_type);

    /**
     * 讲师的信息
     *
     * @param id
     * @return
     */
    public AppUserBean userTeacher(@Param("id") Integer id);

    /**
     * 推荐专栏前三条
     *
     * @param id
     * @return
     */
    List<SpecialColumnBean> listColumn(@Param("id") Integer id);


}
