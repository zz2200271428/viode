package com.znsd.instapundit.bxwserver.dao;

import com.znsd.instapundit.bean.AppUserBean;
import com.znsd.instapundit.bean.OrderBean;
import com.znsd.instapundit.bean.PayBean;
import com.znsd.instapundit.param.PayParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PayDao {
    //查询订单信息
    List<PayBean> pageList(PayParam payParam);

    //总条数
    Integer counter(PayParam payParam);

    //通过id查询订单
    List<PayBean> getOrderById(@Param("uid") Integer uid);

    //开通VIP
    Integer updateVip(AppUserBean userBean);

    //插入订单表
    Integer addOrder(OrderBean orderBean);

    //插入支付表
    Integer addPay(PayBean payBean);

    //查询我的收益
    List<PayBean> listMoney(AppUserBean appUserBean);
}
