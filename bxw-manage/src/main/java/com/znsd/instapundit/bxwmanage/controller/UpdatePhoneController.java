package com.znsd.instapundit.bxwmanage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.PageBean;
import com.znsd.instapundit.bean.UpdatePhoneBean;
import com.znsd.instapundit.service.UpdatePhoneService;
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
@RequestMapping("/UpdatePhone")
public class UpdatePhoneController {
    @Reference
    UpdatePhoneService updatePhoneService;
    @RequestMapping("/lists")
    public String getListPage(Model model, HttpSession session){
        return "loginUpdatePhone/phone-list";
    }

    //返回分页数据
    @GetMapping("/list")
    @ResponseBody
    public Map<String,Object> getList(PageBean pageBean){
      Map<String,Object> classifyresult = new HashMap<String, Object>();
        List<UpdatePhoneBean> classifylist; classifylist = updatePhoneService.listPage(pageBean);
        int count=updatePhoneService.listPageCount();
        classifyresult.put("code",0);
        classifyresult.put("msg","");
        classifyresult.put("count",count);
        classifyresult.put("data",classifylist);
        return classifyresult;
    }
    //搜索
    @RequestMapping("/seek")
    @ResponseBody
    public Map<String,Object> seek(UpdatePhoneBean updatePhoneBean,PageBean pageBean){
        System.out.println(updatePhoneBean);
        Map<String,Object> result = new HashMap<String, Object>();
        List<UpdatePhoneBean> list = updatePhoneService.seek(updatePhoneBean,pageBean);
        int count=updatePhoneService.seekCount(updatePhoneBean);
        result.put("code",0);
        result.put("msg","");
        result.put("count",count);
        result.put("data",list);
        return result;
    }


}
