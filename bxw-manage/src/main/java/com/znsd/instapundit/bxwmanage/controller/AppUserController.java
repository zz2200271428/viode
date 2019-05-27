package com.znsd.instapundit.bxwmanage.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.AppUserBean;
import com.znsd.instapundit.common.MD5Utils;
import com.znsd.instapundit.exception.GlobalException;
import com.znsd.instapundit.param.AppUserParam;
import com.znsd.instapundit.result.CodeMsg;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.result.Result;
import com.znsd.instapundit.service.AppUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/appUser")
public class AppUserController {

    private static final String PREFIX = "appUser";

    @Reference
    private AppUserService appUserService;

    //跳转查询页面
    @RequestMapping("/lists")
    public String queryList() {
        return PREFIX + "/app-user-list";
    }

    //用户查询
    @GetMapping("/list")
    @ResponseBody
    public PagingResult getList(AppUserParam apppageBean) {
        return appUserService.listPage(apppageBean);
    }

    //冻结用户
    @PutMapping("/freeze")
    @ResponseBody
    public Result updateStatus(AppUserBean appUserBean) {
        return this.appUserService.updateStatus(appUserBean);
    }

    @RequestMapping("/particulars")
    public ModelAndView particulars(Integer id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(PREFIX + "/app-user-particulars");
        mv.addObject("user", this.appUserService.getUserByid(id));
        return mv;
    }
    /**
     * 重置密码
     */
    @DeleteMapping("/updatePass")
    @ResponseBody
    public Result updatePass(AppUserBean pageBean){
        String password = MD5Utils.getMd5Simple(pageBean.getPassword());
        pageBean.setPassword(password);
        Map<String,Object> result = new HashMap<String, Object>();

        int delCount = appUserService.updatePass(pageBean);
        if (delCount>0){
            return Result.success(CodeMsg.DELETE_SUCCESS);
        }
        throw new GlobalException(CodeMsg.DELETE_ERROR);
    }
}
