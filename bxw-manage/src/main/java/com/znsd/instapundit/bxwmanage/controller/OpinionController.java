package com.znsd.instapundit.bxwmanage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.OpinionBean;
import com.znsd.instapundit.param.OpinionParam;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.service.OpinionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/opinion")
public class OpinionController {

    @Reference
    private OpinionService opinionService;

    @RequestMapping("/lists")
    public String getListPage(Model model){
        return "opinion/opinion-list";
    }

    @GetMapping("/list")
    @ResponseBody
    public PagingResult listPage(OpinionParam opinionParam){
        List<OpinionBean> list = opinionService.listPage(opinionParam);
        int count = opinionService.getCount(opinionParam);
        return PagingResult.success(count,list);
    }
}
