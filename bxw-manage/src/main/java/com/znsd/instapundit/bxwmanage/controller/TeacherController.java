package com.znsd.instapundit.bxwmanage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.exception.GlobalException;
import com.znsd.instapundit.param.StoreParam;
import com.znsd.instapundit.result.CodeMsg;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.result.Result;
import com.znsd.instapundit.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Reference
    private TeacherService teacherService;

    @RequestMapping("/lists")
    public String getListPage(Model model, HttpSession session) {
        return "check/teacher-list";
    }
    /**
     * 查询功能，包括模糊搜索和默认搜索
     * @param getList
     * @return PagingResult 结果集
     */
    @GetMapping("/list")
    @ResponseBody
    public PagingResult getList(StoreParam storePage) {
        return PagingResult.success(teacherService.counter(storePage), teacherService.pageList(storePage));
    }
    /**
     * 拒绝审核
     * @param saveDel
     * @return PagingResult 结果集
     */
    @DeleteMapping("/refuse")
    @ResponseBody
    public Result saveDel(Integer[] ids) {

        Integer delCount = teacherService.saveDel(ids);
        if (delCount > 0) {
            return Result.success(CodeMsg.DELETE_SUCCESS);
        }
        throw new GlobalException(CodeMsg.DELETE_ERROR);
    }
    /**
     * 通过审核
     * @param savePass
     * @return PagingResult 结果集
     */
    @PostMapping("pass")
    @ResponseBody
    public Result savePass(Integer[] ids) {
        Integer delCount = teacherService.save(ids);
        if (delCount > 0) {
            return Result.success(CodeMsg.DELETE_SUCCESS);
        }
        throw new GlobalException(CodeMsg.DELETE_ERROR);
    }
}
