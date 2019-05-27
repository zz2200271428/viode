package com.znsd.instapundit.bxwmanage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.param.ClassifySeparateParam;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.service.ClassifySeparateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/classifySeparate")
public class ClassifySeparateController {

    private static final String PREFIX = "classifySeparate";

    @Reference
    private ClassifySeparateService classifySeparateService;

    @RequestMapping("/lists")
    public String queryList() {
        return PREFIX + "/classifySeparate-list";
    }

    @GetMapping("/list")
    @ResponseBody
    public PagingResult getList(ClassifySeparateParam classifySeparateParam) {
        return classifySeparateService.listPage(classifySeparateParam);
    }
}
