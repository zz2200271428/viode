package com.znsd.instapundit.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * tb_order 订单实体类
 */
public class OrderBean implements Serializable {

    private Integer id;
    private AppUserBean user; // 下单用户
    private Integer user_id; //用户id
    private String order_number; // 订单编号
    private String picture; // 商品封面
    private String title; // 商品标题
    private BigDecimal price; // 商品单价
    private DictBean order_status; // 订单状态
    private String commer; // 商户号
    private String deal; // 交易号
    @JsonFormat(pattern = "yyyy-MM-dd ", timezone = "GMT+8")
    private Date payment_time; // 付款时间
    @JsonFormat(pattern = "yyyy-MM-dd ", timezone = "GMT+8")
    private Date order_time; // 下单时间
    private Integer status; // 状态（1：正常、2：删除）
    private Integer orderStatus; // 订单状态

    public OrderBean() {
    }

    public OrderBean(Integer user_id, String order_number, String picture, String title, BigDecimal price, Integer orderStatus, String commer, String deal) {
        this.user_id = user_id;
        this.order_number = order_number;
        this.picture = picture;
        this.title = title;
        this.price = price;
        this.orderStatus = orderStatus;
        this.commer = commer;
        this.deal = deal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public AppUserBean getUser() {
        return user;
    }

    public void setUser(AppUserBean user) {
        this.user = user;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public DictBean getOrder_status() {
        return order_status;
    }

    public void setOrder_status(DictBean order_status) {
        this.order_status = order_status;
    }

    public String getCommer() {
        return commer;
    }

    public void setCommer(String commer) {
        this.commer = commer;
    }

    public String getDeal() {
        return deal;
    }

    public void setDeal(String deal) {
        this.deal = deal;
    }

    public Date getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(Date payment_time) {
        this.payment_time = payment_time;
    }

    public Date getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Date order_time) {
        this.order_time = order_time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "id=" + id +
                ", user=" + user +
                ", user_id=" + user_id +
                ", order_number='" + order_number + '\'' +
                ", picture='" + picture + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", order_status=" + order_status +
                ", commer='" + commer + '\'' +
                ", deal='" + deal + '\'' +
                ", payment_time=" + payment_time +
                ", order_time=" + order_time +
                ", status=" + status +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
