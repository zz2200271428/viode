package com.znsd.instapundit.bxwmanage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.VideoBean;
import com.znsd.instapundit.param.SpecialColumnParam;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.result.Result;
import com.znsd.instapundit.service.SpecialColumnService;
import com.znsd.instapundit.service.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/specialColumn")
public class SpecialColumnController {

    @Reference
    private SpecialColumnService specialColumnService;

    @Reference
    private VideoService videoService;

    @GetMapping("/")
    @ResponseBody
    public PagingResult pagingList(SpecialColumnParam param) {
        return specialColumnService.pagingList(param);
    }

    @DeleteMapping("/")
    @ResponseBody
    public Result delete(Integer[] ids) {
        return specialColumnService.delete(ids);
    }

    @GetMapping("/video")
    @ResponseBody
    public PagingResult pagingList(Integer id) {
        List<VideoBean> videoList = videoService.getVideosBySpeID(id);
        return PagingResult.success(videoList.size(), videoList);
    }

}
