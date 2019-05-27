package com.znsd.instapundit.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class StoreBean implements Serializable {

    private Integer id; // 主键
    private String sname; // 店铺名称
    private String simg; // 店铺头像
    private String uname; // 真实姓名
    private String datum; //	资质证明（上传图片）
    private String idc_img; // 身份证照片
    private String idc_img2; // 身份证照片
    private BigDecimal cashdeposit; // 保证金
    private String contact; // 联系方式
    private Integer check_status; // 审核状态（1,待审核，已通过 3,未通过）
    private String store_desc; // 店铺介绍
    @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss", timezone = "GMT+8")
    private Date apply_time; // 申请时间
    @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss", timezone = "GMT+8")
    private Date check_time; // 审核时间
    private String reason; // 申请理由
    private AppUserBean user; // 用户id
    private Integer status; // 状态（1：正常、2：冻结、3：删除）
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date open_time; // 开店日期
    private Integer click_amount; // 点击量
    private Integer ask_count; // 被提问次数
    private Integer count; // 店铺关注量
    private Integer apply_id; // 申请表id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSimg() {
        return simg;
    }

    public void setSimg(String simg) {
        this.simg = simg;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getIdc_img() {
        return idc_img;
    }

    public void setIdc_img(String idc_img) {
        this.idc_img = idc_img;
    }

    public String getIdc_img2() {
        return idc_img2;
    }

    public void setIdc_img2(String idc_img2) {
        this.idc_img2 = idc_img2;
    }

    public BigDecimal getCashdeposit() {
        return cashdeposit;
    }

    public void setCashdeposit(BigDecimal cashdeposit) {
        this.cashdeposit = cashdeposit;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getCheck_status() {
        return check_status;
    }

    public void setCheck_status(Integer check_status) {
        this.check_status = check_status;
    }

    public String getStore_desc() {
        return store_desc;
    }

    public void setStore_desc(String store_desc) {
        this.store_desc = store_desc;
    }

    public Date getApply_time() {
        return apply_time;
    }

    public void setApply_time(Date apply_time) {
        this.apply_time = apply_time;
    }

    public Date getCheck_time() {
        return check_time;
    }

    public void setCheck_time(Date check_time) {
        this.check_time = check_time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public AppUserBean getUser() {
        return user;
    }

    public void setUser(AppUserBean user) {
        this.user = user;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getOpen_time() {
        return open_time;
    }

    public void setOpen_time(Date open_time) {
        this.open_time = open_time;
    }

    public Integer getClick_amount() {
        return click_amount;
    }

    public void setClick_amount(Integer click_amount) {
        this.click_amount = click_amount;
    }

    public Integer getAsk_count() {
        return ask_count;
    }

    public void setAsk_count(Integer ask_count) {
        this.ask_count = ask_count;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getApply_id() {
        return apply_id;
    }

    public void setApply_id(Integer apply_id) {
        this.apply_id = apply_id;
    }

    @Override
    public String toString() {
        return "StoreBean{" +
                "id=" + id +
                ", sname='" + sname + '\'' +
                ", simg='" + simg + '\'' +
                ", uname='" + uname + '\'' +
                ", datum='" + datum + '\'' +
                ", idc_img='" + idc_img + '\'' +
                ", cashdeposit=" + cashdeposit +
                ", contact='" + contact + '\'' +
                ", check_status=" + check_status +
                ", store_desc='" + store_desc + '\'' +
                ", apply_time=" + apply_time +
                ", check_time=" + check_time +
                ", reason='" + reason + '\'' +
                ", user=" + user +
                ", status=" + status +
                ", open_time=" + open_time +
                ", click_amount=" + click_amount +
                ", ask_count=" + ask_count +
                ", count=" + count +
                ", apply_id=" + apply_id +
                '}';
    }
}
