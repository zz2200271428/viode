package com.znsd.instapundit.bxwmanage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.DictBean;
import com.znsd.instapundit.param.OrderParam;
import com.znsd.instapundit.result.CodeMsg;
import com.znsd.instapundit.result.PagingResult;
import com.znsd.instapundit.result.Result;
import com.znsd.instapundit.service.DictService;
import com.znsd.instapundit.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final String DICT_TYPE = "ordertype";

    @Reference
    private OrderService orderService;

    @Reference
    private DictService dictService;

    @RequestMapping("/list")
    public ModelAndView go_list() {
        ModelAndView mv = new ModelAndView();
        List<DictBean> orderStatusList = dictService.getListType(DICT_TYPE);
        mv.addObject("orderStatusList", orderStatusList);
        mv.setViewName("order/order-list");
        return mv;
    }

    @GetMapping("/")
    @ResponseBody
    public PagingResult pagingList(OrderParam param) {
        return orderService.pagingList(param);
    }



}
