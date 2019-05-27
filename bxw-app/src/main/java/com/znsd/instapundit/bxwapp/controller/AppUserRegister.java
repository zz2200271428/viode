package com.znsd.instapundit.bxwapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.AppUserBean;
import com.znsd.instapundit.common.MD5Utils;
import com.znsd.instapundit.common.MessageCodeUtil;
import com.znsd.instapundit.service.AppUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/register")
public class AppUserRegister {

    public static int code;
    public static long codeOverTime;
    public static boolean isExits = false;
    @Reference
    private AppUserService appUserService;


    @RequestMapping("/register")
    public String register(){
        return "register/userregister";
    }


    @RequestMapping("/phoneregister")
    @ResponseBody
    public Map<String,Object> checkPhone(String phone){
        Map<String,Object> map = new HashMap<String, Object>();
        AppUserBean appUserBean = this.appUserService.checkPhone(phone);
        if (appUserBean!=null){
            isExits = true;
            map.put("code",200);
            map.put("msg","用户已存在！");
            return map;
        }
        isExits = false;
        map.put("code",404);
        map.put("msg","用户不存在");
        return map;
    }

    @GetMapping("/coderegister")
    @ResponseBody
    public Map<String,Object> phoneCode(String phone){
        Map<String,Object> map = new HashMap<String, Object>();
        if (isExits){
            map.put("code",400);
            map.put("msg","此号已注册！");
            return map;
        }
        Map<String, Object> noteMap = MessageCodeUtil.getNote(phone);
         if (!"2".equals(noteMap.get("code"))){
            map.put("code",500);
            map.put("msg","服务器异常发送失败！");
            return map;
        }
        code = (int) noteMap.get("mobile_code");
        String content = (String) noteMap.get("content");
        map.put("code","200");
        map.put("msg","发送成功");
        codeOverTime = new Date().getTime();
        System.out.println(code);
        appUserService.addRegisterNote(phone,content);
        return map;
    }

    @PostMapping("/appuserregister")
    @ResponseBody
    public Map<String,Object> register(AppUserBean appUserBean){
        Map<String,Object> map = new HashMap<String,Object>();
        long time = new Date().getTime();
        if ((time-codeOverTime)/1000>=90){
            map.put("code",500);
            map.put("msg","验证码超时");
            return map;
        }
        if (code!=Integer.parseInt(appUserBean.getCode())){
            map.put("code",400);
            map.put("msg","验证码错误");
            return map;
        }
        String password = MD5Utils.getMd5(appUserBean.getPassword());
        appUserBean.setPassword(password);
        Integer count = appUserService.addAppuser(appUserBean);
        if (count>0){
            map.put("code",200);
            map.put("msg","注册成功");
            return map;
        }
        map.put("400",404);
        map.put("msg","服务器异常注册失败");
        return map;
    }
}
