package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AppIndexDao  {

    /*
         图文
    * */
    public List<ImageBean> queryListImageIndex();

    /*
    *   轮播
    * */
    public List<SlideshowBean>  queryListSlideshow();

    //  大咖
    public List<AppUserBean>  queryListMaster();

    /*
    *   视频
    * */
    public List<VideoBean>  queryListVideo(Integer video_type);


    VideoBean getVideoById(Integer id);

    List<VideoBean> getVideoBySpeId(Integer id);


    /*
    *   专栏
    * */

    public List<SpecialCollectBean> specialList();


    /*
    *   所有大咖的视频
    * */

    public List<VideoBean>  queryListMasterVideo(Integer video_type);


    /**
     * 讲师的信息
     *
     * @param id
     * @return
     */
    public AppUserBean userTeacher(@Param("id") Integer id);



    public List<AppUserBean>  queryListLecturer();


}
