package com.znsd.instapundit.bxwmanage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.StoreBean;
import com.znsd.instapundit.bean.VideoBean;
import com.znsd.instapundit.result.Result;
import com.znsd.instapundit.service.MapService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/pie")
public class PieController {

    @Reference
    private MapService mapService;

    @RequestMapping("/lists")
    public String pielist(Model model) {
        return "pie/pie-list";
    }


    @RequestMapping("/videolist")
    public String pievideolist(Model model) {
        return "pie/pie-videolist";
    }


    @RequestMapping("/videotypelist")
    public String pievideotype(Model model) {
        return "pie/pie-typelist";
    }

    //查询访问数据
    @RequestMapping("/clickCount")
    @ResponseBody
    public Result getMap() {
        List<StoreBean> storeBeanList = mapService.MapApplyList();
        return Result.success(storeBeanList);
    }


    //查询访问数据
    @RequestMapping("/videoCount")
    @ResponseBody
    public Result getMapVideo() {
        List<VideoBean> videoBeans = mapService.MapVoideList();
        return Result.success(videoBeans);
    }

    //查询访问数据
    @RequestMapping("/typeCount")
    @ResponseBody
    public Result getMapType() {
        List<VideoBean> videoBeanList = mapService.MapTypeList();
        return Result.success(videoBeanList);
    }

}
