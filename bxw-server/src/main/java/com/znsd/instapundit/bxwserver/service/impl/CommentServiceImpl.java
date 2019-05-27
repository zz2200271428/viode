package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.CommentBean;
import com.znsd.instapundit.bxwserver.dao.CommentDao;
import com.znsd.instapundit.exception.GlobalException;
import com.znsd.instapundit.param.CommentParam;
import com.znsd.instapundit.result.CodeMsg;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.result.Result;
import com.znsd.instapundit.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Component
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public Result delete(Integer[] ids) {
        int result = commentDao.delete(ids);
        return Result.success(CodeMsg.DELETE_SUCCESS);
    }

    @Override
    public PagingResult pagingList(CommentParam param) {
        List<CommentBean> commentList = commentDao.pagingList(param);
        int count = commentDao.getPagingCount(param);
        return PagingResult.success(count, commentList);
    }

    @Override
    public Result insertComment(Integer userID, Integer videoID, String comment) {
        int count = commentDao.insertComment(userID, videoID, comment);
        if (count > 0) {
            return Result.success(CodeMsg.COMMENT_SUCCESS);
        } else {
            throw new GlobalException(CodeMsg.COMMENT_ERROR);
        }
    }

    @Override
    public List<CommentBean> getComments(Integer videoID) {
        return commentDao.getComments(videoID);
    }

}
