package com.znsd.instapundit.bxwapp.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.znsd.instapundit.bean.*;
import com.znsd.instapundit.result.CodeMsg;
import com.znsd.instapundit.result.Result;
import com.znsd.instapundit.service.MoveUserService;
import com.znsd.instapundit.service.PayService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/pay")
public class payController {
    @Reference
    MoveUserService moveUserService;
    @Reference
    PayService payService;

    ///进入钱包页面
    @RequestMapping("/wallet")
    public String getPageWallet(HttpServletRequest request, HttpSession session) {
        AppUserBean appUserBean = (AppUserBean) session.getAttribute("user");//拿到用户session
        MoveUserBean moveUserBean = getBayId(appUserBean);
        request.setAttribute("user", moveUserBean);//查询用户信息
        request.setAttribute("gt", payService.getOrderById(moveUserBean.getId()));//根据用户id查询所有支付记录
        request.setAttribute("money", payService.recently(appUserBean));
        return "wallet";
    }

    //进入充值页面
    @RequestMapping("/charge")
    public String getPageCharge(HttpSession session,HttpServletRequest request) {
        AppUserBean appUserBean = (AppUserBean) session.getAttribute("user");//拿到用户session
        MoveUserBean moveUserBean = getBayId(appUserBean);
        request.setAttribute("vip", moveUserBean);//查询用户信息
        return "charge";
    }

    //进入vip页面
    @RequestMapping("/vipPage")
    public String vipPage(HttpSession session,HttpServletRequest request) {
        AppUserBean appUserBean = (AppUserBean) session.getAttribute("user");//拿到用户session
        MoveUserBean moveUserBean = getBayId(appUserBean);
        appUserBean.setMember_time(moveUserBean.getMember_time());
        request.setAttribute("vip", moveUserBean);//查询用户信息
        request.setAttribute("time", payService.compare_date(appUserBean).intValue());//vip校验
        return "vip";
    }


    //开通vip
    @RequestMapping("/vip")
    @ResponseBody
    public Result dredge(AppUserBean userBean) {
        MoveUserBean moveUserBean = getBayId(userBean);//从数据库拿到数据保持最新
        return payService.updateVip(userBean, moveUserBean);

    }

    //充值
    @RequestMapping("/add-value")
    @ResponseBody
    public Result charge(AppUserBean userBean) {
        MoveUserBean moveUserBean = getBayId(userBean);//从数据库拿到数据保持最新
        if (payService.charge(userBean, moveUserBean).intValue() == 1) {
            return Result.success(CodeMsg.CHARGE_SUCCESS);
        }
        return Result.success(CodeMsg.CHARGE_ERROR);
    }

    public MoveUserBean getBayId(AppUserBean appUserBean) {
        MoveUserBean moveUserBean = new MoveUserBean();
        moveUserBean.setId(appUserBean.getId());
        return moveUserService.queryList(moveUserBean);//查询用户信息
    }


}
