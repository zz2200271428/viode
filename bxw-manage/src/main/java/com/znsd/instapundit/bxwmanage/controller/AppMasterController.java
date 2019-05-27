package com.znsd.instapundit.bxwmanage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.param.AppUserParam;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.service.AppMasterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/master")
public class AppMasterController {

    private static final String PREFIX = "appMaster";


    @Reference
    private AppMasterService appMasterService;

    //跳转查询页面
    @RequestMapping("/lists")
    public String queryList() {
        return PREFIX + "/app-master-list";
    }

    //用户查询
    @GetMapping("/list")
    @ResponseBody
    public PagingResult getList(AppUserParam apppageBean) {
        return appMasterService.listPage(apppageBean);
    }


}
