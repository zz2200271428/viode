package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.*;

import java.util.List;

public interface AppIndexService {

    /*
       图文
   * */
    public List<ImageBean> queryListImageIndex();

    /*
     * 轮播
     * */
    public List<SlideshowBean>  queryListSlideshow();

    //  大咖
    public List<AppUserBean>  queryListMaster();

    /*
     *   视频
     * */
    public List<VideoBean>  queryListVideo(Integer  video_type);


    VideoBean getVideoById(Integer id);

    List<VideoBean> getVideoBySpeId(Integer id);


    /*
     *   专栏
     * */

    public List<SpecialCollectBean> specialList();


    /*
     *   视频
     * */
    public List<VideoBean>  queryListMasterVideo(Integer  video_type);

    /**
     * 讲师的信息
     *
     * @param id
     * @return
     */
    public AppUserBean userTeacher(Integer id);


    /*
    *   讲师列表
    * */
    public List<AppUserBean>  queryListLecturer();

}
