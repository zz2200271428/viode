package com.znsd.instapundit.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TeacherApplyBean implements Serializable {
    private Integer id;//自增长
    private String uname;// 真实姓名
    private String datum;//资质证明( 上传图片)
    private String idc_img;// 身份证照片国徽
    private String idc_img2;// 身份证照片人头
    private BigDecimal cashdeposit;//保证金额
    private String contact;// 联系方式
    private String check_status;//	审核状态（1,待审核，已通过 3,未通过）
    private String store_desc;//讲师个人介绍
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date apply_time;//申请时间
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date check_time;//审核时间
    private String reason;//理由
    private AppUserBean user;// 用户id
    @Override
    public String toString() {
        return "TeacherApplyBean{" +
                "id=" + id +
                ", uname='" + uname + '\'' +
                ", datum='" + datum + '\'' +
                ", idc_img='" + idc_img + '\'' +
                ", cashdeposit=" + cashdeposit +
                ", contact='" + contact + '\'' +
                ", check_status='" + check_status + '\'' +
                ", store_desc='" + store_desc + '\'' +
                ", apply_time=" + apply_time +
                ", check_time=" + check_time +
                ", reason='" + reason + '\'' +
                ", user=" + user +
                '}';
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCheck_status() {
        return check_status;
    }

    public void setCheck_status(String check_status) {
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
}
