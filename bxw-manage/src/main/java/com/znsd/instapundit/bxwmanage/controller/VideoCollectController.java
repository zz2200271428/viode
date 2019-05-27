package com.znsd.instapundit.bxwmanage.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.DictBean;
import com.znsd.instapundit.bean.SpecialCollectBean;
import com.znsd.instapundit.bean.VideoCollectBean;
import com.znsd.instapundit.param.CollectParam;
import com.znsd.instapundit.param.VideoCollectParam;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.service.DictService;
import com.znsd.instapundit.service.VideoCollectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/videoCollect")
public class VideoCollectController {

    // 字典表类型
    private final String DICT_TYPE = "videotype";

    @Reference
    private VideoCollectService videoCollectService;

    @Reference
    private DictService dictService;



    @RequestMapping("/lists")
    public ModelAndView go_list() {
        ModelAndView mv = new ModelAndView();
        List<DictBean> videoTypeList = dictService.getListType(DICT_TYPE);
        mv.addObject("videoTypeList", videoTypeList);
        mv.setViewName("videoCollect/video-list");
        return mv;
    }


    //查询分页数据（模糊搜索，默认）
    @RequestMapping("/list")
    @ResponseBody
    public PagingResult SpecialCollectList(VideoCollectParam videoCollectParam){
       List<VideoCollectBean> VideoCollects=videoCollectService.queryList(videoCollectParam);
        int count = videoCollectService.getCount();
        return PagingResult.success(count,VideoCollects);
    }
}
