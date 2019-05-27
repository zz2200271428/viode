package com.znsd.instapundit.bxwserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.znsd.instapundit.bean.*;
import com.znsd.instapundit.bxwserver.dao.PayDao;
import com.znsd.instapundit.exception.GlobalException;
import com.znsd.instapundit.param.PayParam;
import com.znsd.instapundit.result.CodeMsg;
import com.znsd.instapundit.result.Result;
import com.znsd.instapundit.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Component
public class PayServiceImpl implements PayService {

    @Autowired
    private PayDao payDao;

    @Override
    public List<PayBean> pageList(PayParam payParam) {
        return payDao.pageList(payParam);
    }

    @Override
    public Integer counter(PayParam payParam) {
        return payDao.counter(payParam);
    }

    //通过id查询订单
    @Override
    public List<PayBean> getOrderById(Integer uid) {
        return payDao.getOrderById(uid);
    }

    //开通vip
    @Override
    public Result updateVip(AppUserBean userBean, MoveUserBean moveUserBean) {
        OrderBean orderBean = create(userBean);//生成订单号，交易号，商户单号,详情参考方法
        orderBean.setTitle("开通vip");
        orderBean.setOrder_status(new DictBean());
        PayBean payBean = new PayBean();
        if (moveUserBean.getMoney().intValue() >= userBean.getMoney().intValue()) {//判断余额是否足够
            payBean.setOrder_money(String.valueOf(userBean.getMoney()));
            userBean.setMoney(moveUserBean.getMoney().subtract(userBean.getMoney()));//计算后的余额
            int i = compare_date(userBean).intValue();//判断vip是否过期
            userBean.setMember_time(moveUserBean.getMember_time());//传入当前会员日期
            userBean.setMember(1);//判断vip
            orderBean.getOrder_status().setStatus(3);
            userBean.setMember_time(time(userBean));
            addOrder(orderBean);
            payBean.setOrderBean(orderBean);//拿到订单id
            payBean.setPayment_time(new Date());
            payBean.setPayUser(userBean);
            payBean.setStatus("1");
            payDao.addPay(payBean);//插入交易记录
            payDao.updateVip(userBean);//修改vip日期
            return Result.success(CodeMsg.PAYVIP_SUCCESS);
        } else {
            orderBean.getOrder_status().setStatus(1);
            addOrder(orderBean);
            throw new GlobalException(CodeMsg.PAYVIP_ERROR);
        }


    }

    public Integer compare_date(AppUserBean appUserBean) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(df.format(appUserBean.getMember_time()));
            Date dt2 = df.parse(df.format(new Date()));
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public OrderBean create(AppUserBean userBean) {
        OrderBean orderBean = new OrderBean();
        //订单号,交易号,商户单号生成
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");//设置日期格式
        int order_number = (int) ((Math.random() * 9 + 1) * 100000);//随机生后六位
        String date = df.format(new Date()) + order_number;// new Date()为获取当前时间戳
        String commer = "21" + df.format(new Date()) + (int) ((Math.random() * 9 + 1) * 10000);
        String deal = "21" + df.format(new Date()) + (int) ((Math.random() * 9 + 1) * 10000);
        orderBean.setOrder_number(date);
        orderBean.setDeal(deal);
        orderBean.setCommer(commer);
        orderBean.setUser(userBean);
        orderBean.setUser_id(userBean.getId());
        orderBean.setOrder_time(new Date());
        orderBean.setPrice(userBean.getMoney());
        return orderBean;
    }

    //判断收益
    public Integer recently(AppUserBean appUserBean) {
        List<PayBean> app = payDao.listMoney(appUserBean);
        Integer just = 0;
        Integer lose = 0;
        for (PayBean payBean : app) {
            if (payBean.getPutUser().getId().intValue() == appUserBean.getId().intValue()) {
                just += Integer.valueOf(payBean.getOrder_money());
            } else {
                lose += Integer.valueOf(payBean.getOrder_money());
            }
        }
        just = just - lose;
        return just;
    }

    //充值余额
    @Override
    public Integer charge(AppUserBean appUserBean, MoveUserBean moveUserBean) {
        OrderBean orderBean = create(appUserBean);
        orderBean.setTitle("余额充值");
        PayBean payBean = new PayBean();
        orderBean.setOrder_status(new DictBean());
        payBean.setOrder_money(String.valueOf(appUserBean.getMoney()));
        orderBean.getOrder_status().setStatus(3);
        appUserBean.setMember_time(moveUserBean.getMember_time());
        addOrder(orderBean);
        payBean.setOrderBean(orderBean);//拿到订单id
        appUserBean.setMoney(moveUserBean.getMoney().add(appUserBean.getMoney()));//计算后的余额
        payBean.setPayment_time(new Date());
        payBean.setPutUser(appUserBean);
        payBean.setStatus("1");
        payDao.addPay(payBean);//插入交易记录
        return payDao.updateVip(appUserBean);//修改余额

    }

    //存入订单
    public void addOrder(OrderBean orderBean) {
        payDao.addOrder(orderBean);//
    }

    //计算VIP
    public Date time(AppUserBean userBean) {
        //获取开通vip后的日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        if (userBean.getMember().intValue() == 1) {//判断VIP
            today = userBean.getMember_time();
        }
        //获取开通VIP后的时间
        Calendar theCa = Calendar.getInstance();
        theCa.setTime(today);
        theCa.add(theCa.DATE, userBean.getSex());//把sex用作续费的时间
        return theCa.getTime();
    }
}
