package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.CommentBean;
import com.znsd.instapundit.param.CommentParam;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.result.Result;

import java.util.List;

public interface CommentService {

    Result delete(Integer[] ids);

    PagingResult pagingList(CommentParam param);

    Result insertComment(Integer userID, Integer videoID, String comment);

    List<CommentBean> getComments(Integer videoID);
}
