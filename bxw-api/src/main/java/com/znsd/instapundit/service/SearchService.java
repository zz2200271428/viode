package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.ClassifySeparateBean;
import com.znsd.instapundit.bean.SpecialColumnBean;
import com.znsd.instapundit.bean.VideoBean;

import java.util.List;

public interface SearchService {
    /**
     * 查询热键
     *
     * @return
     */
    List<ClassifySeparateBean> getKeyword();

    /**
     * 增加关键字
     *
     * @param classifySeparateBean
     */
    void addContent(String classifySeparateBean);

    /**
     * 模糊搜索热键
     *
     * @param text
     * @return
     */
    List<VideoBean> listVideo(String text);

    /**
     * 查询讲师更多视频
     *
     * @param id
     * @return
     */
    List<VideoBean> listMore(Integer id,Integer video_type);


    /**
     * 查询讲师专栏
     *
     * @param
     * @return
     */
    List<VideoBean> listSpecial(Integer specialID,Integer video_type);

    /**
     * 查询讲师专栏封面
     *
     * @param
     * @return
     */
    List<SpecialColumnBean> specialAll(Integer id);

    /**
     * 查询讲师专栏里的视频
     *
     * @param id
     * @param specialID
     * @return
     */
    SpecialColumnBean querySpecial(Integer id, Integer specialID);


    boolean collect(Integer specialID, Integer id, boolean flag);

    boolean isCollect(Integer specialID, Integer id);
}
