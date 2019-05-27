package com.znsd.instapundit.bxwmanage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.exception.GlobalException;
import com.znsd.instapundit.param.AppUserParam;
import com.znsd.instapundit.result.CodeMsg;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.result.Result;
import com.znsd.instapundit.service.AppLecturerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/lecturer")
public class AppLecturerController {

    private static final String PREFIX = "appLecturer";


    @Reference
    private AppLecturerService appLecturerService;

    //跳转查询页面
    @RequestMapping("/lists")
    public String queryList() {
        return PREFIX + "/app-lecturer-list";
    }

    //讲师分页查询
    @GetMapping("/list")
    @ResponseBody
    public PagingResult getList(AppUserParam apppageBean) {
        return appLecturerService.listPage(apppageBean);
    }

    //查看申请详情
   @RequestMapping("/particulars")
   public ModelAndView particulars(Integer id) {
       ModelAndView mv = new ModelAndView();
       mv.setViewName(PREFIX + "/app-lecturer-particulars");
       mv.addObject("user", this.appLecturerService.getTeacherByid(id));
       return mv;
   }
    //成为大咖
    @PutMapping("/updateRo_id")
    @ResponseBody
    public Result updateRo_id(Integer id){
        Map<String,Object> result = new HashMap<String, Object>();

        int UpdateCount = appLecturerService.updateRo_id(id);
        if (UpdateCount>0){
            return Result.success(CodeMsg.DELETE_SUCCESS);
        }
        throw new GlobalException(CodeMsg.DELETE_ERROR);
    }
}
