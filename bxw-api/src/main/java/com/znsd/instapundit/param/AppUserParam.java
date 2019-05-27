package com.znsd.instapundit.param;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class AppUserParam implements Serializable {

    private String phone;      //手机号码

    private String username;    //用户名称

    private String id_card;     //身份证

    private String email;       //邮箱

    private Integer sex;        //性别

    private Integer member;      //会员

    //private Integer ro_id;       //角色值

    private String label;     //所属角色

    private Integer status;     //用户状态

    private Integer page; // 当前页码

    private Integer limit; // 条数

    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date start;         // 开始时间

    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date end;           // 截至时间

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getMember() {
        return member;
    }

    public void setMember(Integer member) {
        this.member = member;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = (page - 1) * limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "AppUserParam{" +
                "phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", id_card='" + id_card + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", member=" + member +
                ", label='" + label + '\'' +
                ", status=" + status +
                ", page=" + page +
                ", limit=" + limit +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}