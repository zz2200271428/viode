package com.znsd.instapundit.bean;

import java.io.Serializable;
import java.util.Date;

public class PayBean implements Serializable {
    private String id;//主键自增长

    private String status;//交易状态（1.付款成功2.付款失败）

    private String order_money;//交易金额

    private Date payment_time;//付款时间

    private OrderBean orderBean; //订单信息

    private AppUserBean payUser;//买家信息

    private AppUserBean putUser;//卖家信息

    public OrderBean getOrderBean() {
        return orderBean;
    }

    public void setOrderBean(OrderBean orderBean) {
        this.orderBean = orderBean;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AppUserBean getPayUser() {
        return payUser;
    }

    public void setPayUser(AppUserBean payUser) {
        this.payUser = payUser;
    }

    public AppUserBean getPutUser() {
        return putUser;
    }

    public void setPutUser(AppUserBean putUser) {
        this.putUser = putUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrder_money() {
        return order_money;
    }

    public void setOrder_money(String order_money) {
        this.order_money = order_money;
    }

    public Date getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(Date payment_time) {
        this.payment_time = payment_time;
    }
}
