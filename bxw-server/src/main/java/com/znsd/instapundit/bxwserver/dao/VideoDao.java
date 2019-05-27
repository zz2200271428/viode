package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.OrderBean;
import com.znsd.instapundit.bean.SpecialColumnBean;
import com.znsd.instapundit.bean.VideoBean;
import com.znsd.instapundit.param.VideoParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface VideoDao {

    int updateStatus(@Param("id") Integer id, @Param("video_status") Integer video_status);

    int delete(@Param("ids") Integer[] ids);

    VideoBean getVideoByID(@Param("id") Integer id);

    List<VideoBean> getVideosBySpeID(@Param("id") Integer id);

    List<VideoBean> pagingVideoList(VideoParam param);

    int getPagingVideoCount(VideoParam param);

    List<VideoBean> pagingApplyList(VideoParam param);

    int getPagingApplyCount(VideoParam param);

    int updateApplyStatus(@Param("id") Integer id, @Param("check_status") Integer check_status);

    int isCollect(@Param("videoID") Integer videoID, @Param("userID") Integer userID);

    int insertCollect(@Param("videoID") Integer videoID, @Param("userID") Integer userID);

    int deleteCollect(@Param("videoID") Integer videoID, @Param("userID") Integer userID);

    int minusMoney(@Param("userID") Integer userID, @Param("money") BigDecimal money);

    int addMoney(@Param("userID") Integer userID, @Param("money") BigDecimal money);

    int insertVideoPay(@Param("userID") Integer userID, @Param("videoID") Integer videoID);

    int insertColumnPay(@Param("userID") Integer userID, @Param("columnID") Integer columnID);

    int insertOrder(OrderBean order);

    int isPayVideo(@Param("videoID") Integer videoID, @Param("userID") Integer userID);

    int isPayColumn(@Param("columnID") Integer columnID, @Param("userID") Integer userID);

    int isColumnCollect(@Param("columnID") Integer columnID, @Param("userID") Integer userID);

    int insertColumnCollect(@Param("columnID") Integer columnID, @Param("userID") Integer userID);

    int deleteColumnCollect(@Param("columnID") Integer columnID, @Param("userID") Integer userID);

    int insertColumn(SpecialColumnBean column);

    int insertVideo(VideoBean video);

    int addPlayCount(@Param("videoID") Integer videoID);

    List<VideoBean> getVideoByUserID(@Param("userID") Integer userID);
}
