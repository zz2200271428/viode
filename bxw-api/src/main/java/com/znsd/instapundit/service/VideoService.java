package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.SpecialColumnBean;
import com.znsd.instapundit.bean.VideoBean;
import com.znsd.instapundit.param.VideoParam;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.result.Result;

import java.util.List;

public interface VideoService {

    Result updateStatus(Integer id, Integer video_status);

    Result delete(Integer[] ids);

    VideoBean getVideoByID(Integer id);

    List<VideoBean> getVideosBySpeID(Integer id);

    PagingResult pagingVideoList(VideoParam param);

    PagingResult pagingApplyList(VideoParam param);

    Result updateApplyStatus(Integer id, Integer check_status);

    boolean isCollect(Integer videoID, Integer userID);

    boolean collect(Integer videoID, Integer userID, boolean flag);

    Result pay(Integer videoID, Integer userID);

    boolean isPay(Integer videoID, Integer userID);

    int insertColumn(SpecialColumnBean column);

    int insertVideo(VideoBean video);

    int addPlayCount(Integer videoID);

    List<VideoBean> getVideoByUserID(Integer userID);
}
