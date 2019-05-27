package com.znsd.instapundit.bxwmanage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.param.CommentParam;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.result.Result;
import com.znsd.instapundit.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Reference
    private CommentService commentService;

    @GetMapping("/")
    @ResponseBody
    public PagingResult pagingList(CommentParam param) {
        return commentService.pagingList(param);
    }

    @DeleteMapping("/")
    @ResponseBody
    public Result delete(Integer[] ids) {
        return commentService.delete(ids);
    }

}
