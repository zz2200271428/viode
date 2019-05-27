package com.znsd.instapundit.param;

import com.znsd.instapundit.bean.RoleBean;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserParam implements Serializable {
    private Integer page; // 当前页码

    private Integer limit; // 条数

    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date start;         // 开始时间

    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date end;           // 截至时间

    private String roles; //用户角色

    private Integer status;     //用户状态

    private String nickname;  //昵称

    private String id_c;      //身份证

    private String phone;     //手机号码

    private String email;     //邮箱

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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getId_c() {
        return id_c;
    }

    public void setId_c(String id_c) {
        this.id_c = id_c;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserParam{" +
                "page=" + page +
                ", limit=" + limit +
                ", start=" + start +
                ", end=" + end +
                ", roles='" + roles + '\'' +
                ", status='" + status + '\'' +
                ", nickname='" + nickname + '\'' +
                ", id_c='" + id_c + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
