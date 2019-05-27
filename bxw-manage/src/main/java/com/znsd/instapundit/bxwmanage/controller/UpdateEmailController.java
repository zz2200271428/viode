package com.znsd.instapundit.bxwmanage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.PageBean;
import com.znsd.instapundit.bean.UpdateEmailBean;
import com.znsd.instapundit.service.UpdateEmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/UpdateEmail")
public class UpdateEmailController {
    @Reference
    UpdateEmailService updateEmailService;
    @RequestMapping("/lists")
    public String getListPage(Model model, HttpSession session){
        return "loginUpdateEmail/email-list";
    }

    //返回分页数据
    @GetMapping("/list")
    @ResponseBody
    public Map<String,Object> getList(PageBean pageBean){
        Map<String,Object> classifyresult = new HashMap<String, Object>();
        List<UpdateEmailBean> classifylist = updateEmailService.listPage(pageBean);
        int count=updateEmailService.listPageCount();
        classifyresult.put("code",0);
        classifyresult.put("msg","");
        classifyresult.put("count",count);
        classifyresult.put("data",classifylist);
        return classifyresult;
    }
    //搜索
    @RequestMapping("/seek")
    @ResponseBody
    public Map<String,Object> seek(UpdateEmailBean updateEmailBean,PageBean pageBean){
        System.out.println(updateEmailBean);
        Map<String,Object> result = new HashMap<String, Object>();
        List<UpdateEmailBean> list = updateEmailService.seek(updateEmailBean,pageBean);
        int count=updateEmailService.seekCount(updateEmailBean);
        result.put("code",0);
        result.put("msg","");
        result.put("count",count);
        result.put("data",list);
        return result;
    }


}
