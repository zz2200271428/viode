package com.znsd.instapundit.bxwmanage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.param.StoreParam;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.result.Result;
import com.znsd.instapundit.service.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/store")
public class StoreController {

    @Reference
    private StoreService storeService;

    @RequestMapping("/apply-list")
    public String go_apply() {
        return "store/apply-list";
    }

    @GetMapping("/apply/")
    @ResponseBody
    public PagingResult pagingApplyList(StoreParam param) {
        return storeService.pagingApplyList(param);
    }

    @PutMapping("/apply/")
    @ResponseBody
    public Result updateCheckStatus(@RequestParam("id") Integer id, @RequestParam("check_status") Integer check_status) {
        return storeService.updateCheckStatus(id, check_status);
    }

    @RequestMapping("/store-list")
    public String go_list() {
        return "store/store-list";
    }

    @GetMapping("/")
    @ResponseBody
    public PagingResult pagingStoreList(StoreParam param) {
        return storeService.pagingStoreList(param);
    }

    @PutMapping("/")
    @ResponseBody
    public Result updateStatus(@RequestParam("id") Integer id, @RequestParam("status") Integer status) {
        return storeService.updateStatus(id, status);
    }

    @DeleteMapping("/")
    @ResponseBody
    public Result delete(Integer[] ids) {
        return storeService.delete(ids);
    }

}
