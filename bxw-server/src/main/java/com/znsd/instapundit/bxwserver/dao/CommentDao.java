package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.CommentBean;
import com.znsd.instapundit.param.CommentParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentDao {

    List<CommentBean> pagingList(CommentParam param);

    int getPagingCount(CommentParam param);

    int delete(@Param("ids") Integer[] ids);

    int insertComment(@Param("userID") Integer userID, @Param("videoID") Integer videoID, @Param("comment") String comment);

    List<CommentBean> getComments(@Param("videoID") Integer videoID);
}
