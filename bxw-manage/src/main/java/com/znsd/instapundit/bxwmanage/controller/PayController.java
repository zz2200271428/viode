package com.znsd.instapundit.bxwmanage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.param.PayParam;
import com.znsd.instapundit.param.StoreParam;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.service.PayService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/pay")
public class PayController {
    @Reference
    private PayService pageList;
    /**
     * 进入查询页面
     * @param getListPage
     * @return PagingResult 结果集
     */
    @RequestMapping("/lists")
    public String getListPage(Model model, HttpSession session) {
        return "pay/pay-list";
    }
    /**
     * 查询功能，包括模糊搜索和默认搜索
     * @param getList
     * @return PagingResult 结果集
     */
    @GetMapping("/list")
    @ResponseBody
    public PagingResult getList(PayParam payParam) {
        return PagingResult.success(pageList.counter(payParam), pageList.pageList(payParam));
    }
}
