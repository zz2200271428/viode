package com.znsd.instapundit.bxwmanage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.ClassifyBean;

import com.znsd.instapundit.bean.PageBean;
import com.znsd.instapundit.exception.GlobalException;
import com.znsd.instapundit.result.CodeMsg;

import com.znsd.instapundit.result.Result;
import com.znsd.instapundit.service.ClassifyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Classify")
public class ClassifyController {
    @Reference
    ClassifyService classifyService;

    @RequestMapping("/lists")
    public String getListPage(Model model, HttpSession session){
        return "classify/classify-list";
    }

    @RequestMapping("/addPage")
    public String getAddPage(Model model, HttpSession session){
        return "classify/classify-add";
    }

   @GetMapping("/editPage/{id}")
    public String getEditPage(Model model, HttpSession session,@PathVariable("id") Integer id){
        ClassifyBean classifyBean = classifyService.getClassifyById(id);
        model.addAttribute("classify",classifyBean);
        return "classify/classify-add";
    }
    //返回分页数据
    @GetMapping("/list")
    @ResponseBody
    public Map<String,Object> getList(PageBean pageBean){
        Map<String,Object> classifyresult = new HashMap<String, Object>();
        List<ClassifyBean> classifylist = classifyService.listPage(pageBean);
        int count =classifyService.listPageCount();
        classifyresult.put("code",0);
        classifyresult.put("msg","");
        classifyresult.put("count",count);
        classifyresult.put("data",classifylist);
        return classifyresult;
    }

    //增加
    @PostMapping("/save")
    @ResponseBody
    public Result addDict(ClassifyBean classifyBean){
        int insertCount = classifyService.addClassify(classifyBean);
        if (insertCount>0){
            return Result.success(CodeMsg.ADD_SUCCESS);
        }
        throw new GlobalException(CodeMsg.DELETE_ERROR);
    }
    //修改
    @PutMapping("/save")
    @ResponseBody
    public  Result updateDict(ClassifyBean classifyBean){
        Map<String,Object> result = new HashMap<String, Object>();
        int updateCount = classifyService.updateClassify(classifyBean);
        if (updateCount>0){
            return Result.success(CodeMsg.UPDATE_SUCCESS);
        }
        throw new GlobalException(CodeMsg.DELETE_ERROR);
    }

    //删除
    @DeleteMapping("/delete")
    @ResponseBody
   public Result delUser(Integer []ids){
        Map<String,Object> result = new HashMap<String, Object>();
        int delCount = classifyService.delete(ids);
        if (delCount>0){
            return Result.success(CodeMsg.DELETE_SUCCESS);
        }
        throw new GlobalException(CodeMsg.DELETE_ERROR);
    }

    @RequestMapping("/seek")
    @ResponseBody
    public Map<String,Object> seek(ClassifyBean classifyBean,PageBean pageBean){
        Map<String,Object> classifyresult = new HashMap<String, Object>();
        List<ClassifyBean> classifylist = classifyService.seek(classifyBean,pageBean);
        int count =classifyService.seekCount(classifyBean);
        classifyresult.put("code",0);
        classifyresult.put("msg","");
        classifyresult.put("count",count);
        classifyresult.put("data",classifylist);
        return classifyresult;
    }



}
