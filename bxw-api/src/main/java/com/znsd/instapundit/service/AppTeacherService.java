package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.AppUserBean;
import com.znsd.instapundit.bean.SpecialColumnBean;
import com.znsd.instapundit.bean.VideoBean;

import java.util.List;

public interface AppTeacherService {
    /**
     * 推荐视频前三条
     *
     * @param id
     * @return
     */
     List<VideoBean> listVideo(Integer id,Integer video_type);

    /**
     * 讲师的信息
     *
     * @param id
     * @return
     */
    AppUserBean userTeacher(Integer id);

    /**
     * 推荐专栏前三条
     *
     * @param id
     * @return
     */
    public List<SpecialColumnBean> listColumn(Integer id);


}
