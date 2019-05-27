package com.znsd.instapundit.bxwapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.AskBean;
import com.znsd.instapundit.bean.AskRecordBean;
import com.znsd.instapundit.bean.StoreBean;
import com.znsd.instapundit.param.AskRecordParam;
import com.znsd.instapundit.result.CodeMsg;
import com.znsd.instapundit.result.Result;
import com.znsd.instapundit.service.AskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/ask")
public class AskController {



    @Reference
    private AskService askService;

    @RequestMapping("/details")
    public String getAskPage(Model model,Integer id){
        AskBean ask = askService.getAskByAskId(id);
        StoreBean storeBean = askService.getUserByStoreId(ask.getStore().getId());
        model.addAttribute("ask",ask);
        model.addAttribute("user",storeBean);
        return "ask-details";
    }

    @GetMapping("/record")
    public String listPage(Model model,Integer id){
        List<AskRecordBean> record = askService.getRecordByAskId(id);
        model.addAttribute("record",record);
        return "ask-details :: record";
    }

    @PostMapping("/closely")
    @ResponseBody
    public Result closely(AskRecordParam askRecordParam){
        int count  = askService.addRecord(askRecordParam);
        if (count>0){
            return Result.success(CodeMsg.ADD_SUCCESS);
        }
        return Result.success(CodeMsg.ADD_ERROR);
    }
}
