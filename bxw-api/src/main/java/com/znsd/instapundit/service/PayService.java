package com.znsd.instapundit.service;

import com.znsd.instapundit.bean.*;
import com.znsd.instapundit.param.PayParam;
import com.znsd.instapundit.result.Result;

import java.util.List;

public interface PayService {
    //查询订单信息
    List<PayBean> pageList(PayParam payParam);

    //总条数
    Integer counter(PayParam payParam);

    //通过id查询订单
    List<PayBean> getOrderById(Integer uid);
    //
    Integer compare_date(AppUserBean appUserBean);

    //开通vip
    Result updateVip(AppUserBean userBean, MoveUserBean moveUserBean);
    //计算余额
    Integer recently(AppUserBean appUserBean);
    //充值余额
    Integer charge(AppUserBean appUserBean,MoveUserBean moveUserBean);
}
