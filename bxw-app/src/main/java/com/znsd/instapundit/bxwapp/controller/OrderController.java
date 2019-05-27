package com.znsd.instapundit.bxwapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.OrderBean;
import com.znsd.instapundit.result.CodeMsg;
import com.znsd.instapundit.result.Result;
import com.znsd.instapundit.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Reference
    private OrderService orderService;

    @RequestMapping("/orderPage")
    public String OrderPage(Model model,   HttpSession session){
       Integer id = AppUserController.USER_ID;
       List<OrderBean>  list = orderService.getMyOrder(null,id);
       model.addAttribute("order",list);
        return "my-order";
    }

    @RequestMapping("/myOrder")
    public String listOrder(Model model,Integer status){
        Integer id = AppUserController.USER_ID;
        List<OrderBean> list = orderService.getMyOrder(status,id);
        model.addAttribute("order",list);
        return "my-order::order_refresh";
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public Result deleteOrder(Integer id){
        int index = orderService.deleteOrder(id);
        if (index>0){
            return Result.success(CodeMsg.DELETE_SUCCESS);
        }
        return Result.error(CodeMsg.DELETE_ERROR);
    }

    @RequestMapping("/details")
    public String orderDetails(Model model,Long oid){
        OrderBean order = orderService.getOrderByOid(oid);
        model.addAttribute("order",order);
        return "order-details";
    }
}
