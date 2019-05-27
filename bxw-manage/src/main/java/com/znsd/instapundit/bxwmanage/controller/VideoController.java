package com.znsd.instapundit.bxwmanage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.DictBean;
import com.znsd.instapundit.bean.VideoBean;
import com.znsd.instapundit.bxwmanage.config.RedisServer;
import com.znsd.instapundit.cache.tactics.CacheTactics;
import com.znsd.instapundit.param.VideoParam;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.result.Result;
import com.znsd.instapundit.service.DictService;
import com.znsd.instapundit.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/video")
public class VideoController {

    @Reference
    private VideoService videoService;

    @Reference
    private DictService dictService;

    @Autowired
    private RedisServer redisServer;

    // 类名
    private final String className = "VideoController";
    // 字典表类型
    private final String DICT_TYPE = "videotype";

    @RequestMapping("/video-list")
    public ModelAndView go_list() {
        ModelAndView mv = new ModelAndView();
        List<DictBean> videoTypeList = dictService.getListType(DICT_TYPE);
        mv.addObject("videoTypeList", videoTypeList);
        mv.setViewName("video/video-list");
        return mv;
    }

    @GetMapping("/")
    @ResponseBody
    public PagingResult pagingVideoList(VideoParam param) {
        String key = CacheTactics.getKey(CacheTactics.VIDEO_PREFIX, className, "pagingVideoList", param);
        PagingResult pagingResult = redisServer.getCache(key, PagingResult.class);
        if (pagingResult != null) {
            return pagingResult;
        }

        pagingResult = videoService.pagingVideoList(param);
        redisServer.putCacheWithExpireTime(key, pagingResult, CacheTactics.VIDEO_EXPIRE);
        return pagingResult;
    }

    @PutMapping("/")
    @ResponseBody
    public Result updateStatus(@RequestParam("id") Integer id, @RequestParam("video_status") Integer video_status) {
        redisServer.deleteCacheWithPattern(CacheTactics.VIDEO_PREFIX);
        return videoService.updateStatus(id, video_status);
    }

    @DeleteMapping("/")
    @ResponseBody
    public Result delete(Integer[] ids) {
        redisServer.deleteCacheWithPattern(CacheTactics.VIDEO_PREFIX);
        return videoService.delete(ids);
    }

    @RequestMapping("/play")
    public ModelAndView play(Integer id) {
        ModelAndView mv = new ModelAndView();
        VideoBean video = videoService.getVideoByID(id);
        mv.addObject("video", video);
        mv.setViewName("video/play");
        return mv;
    }

    @RequestMapping("/particulars")
    public ModelAndView particulars(Integer id) {
        ModelAndView mv = new ModelAndView();
        VideoBean video = videoService.getVideoByID(id);
        mv.addObject("video", video);
        mv.setViewName("video/particulars");
        return mv;
    }

    @RequestMapping("/apply-list")
    public ModelAndView go_apply() {
        ModelAndView mv = new ModelAndView();
        List<DictBean> videoTypeList = dictService.getListType(DICT_TYPE);
        mv.addObject("videoTypeList", videoTypeList);
        mv.setViewName("video/apply-list");
        return mv;
    }

    @GetMapping("/apply/")
    @ResponseBody
    public PagingResult pagingApplyList(VideoParam param) {
        String key = CacheTactics.getKey(CacheTactics.VIDEO_PREFIX, className, "pagingApplyList", param);
        PagingResult pagingResult = redisServer.getCache(key, PagingResult.class);
        if (pagingResult != null) {
            return pagingResult;
        }

        pagingResult = videoService.pagingApplyList(param);
        redisServer.putCacheWithExpireTime(key, pagingResult, CacheTactics.VIDEO_EXPIRE);
        return pagingResult;
    }

    @PutMapping("/apply/")
    @ResponseBody
    public Result updateApplyStatus(@RequestParam("id") Integer id, @RequestParam("check_status") Integer check_status) {
        redisServer.deleteCacheWithPattern(CacheTactics.VIDEO_PREFIX);
        return videoService.updateApplyStatus(id, check_status);
    }

}
