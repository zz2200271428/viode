package com.znsd.instapundit.bxwmanage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.SpecialCollectBean;
import com.znsd.instapundit.bean.StoreCollectBean;
import com.znsd.instapundit.param.CollectParam;
import com.znsd.instapundit.param.StoreCollectParam;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.service.StoreCollectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("storeCollect")
public class StoreCollectController {

        @Reference
       private  StoreCollectService storeCollectService;


    @RequestMapping("/lists")
    public String getPageList(Model model, HttpSession session){
        return "storeCollect/storecollect-list";
    }

    //查询分页数据（模糊搜索，默认）
    @RequestMapping("/list")
    @ResponseBody
    public PagingResult SpecialCollectList(StoreCollectParam storeCollectParam){

        List<StoreCollectBean> storeCollectBeanList=storeCollectService.queryStroeList(storeCollectParam);
        int count = storeCollectService.queryCount();
        return PagingResult.success(count,storeCollectBeanList);
    }
}
