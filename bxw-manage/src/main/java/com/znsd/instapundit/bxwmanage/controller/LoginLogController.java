package com.znsd.instapundit.bxwmanage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.LoginLogBean;
import com.znsd.instapundit.param.LoginLogParam;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.service.LoginLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/log")
@Controller
public class LoginLogController {

    @Reference
    private LoginLogService loginLogService;

    @RequestMapping("/lists")
    public String getListPage(Model model) {
        return "loginLog/log-list";
    }


    @GetMapping("/list")
    @ResponseBody
    public PagingResult listPage(LoginLogParam loginLogParam){
        List<LoginLogBean> list = loginLogService.listPage(loginLogParam);
        int count = loginLogService.getCount(loginLogParam);
        return PagingResult.success(count,list);
    }
}
