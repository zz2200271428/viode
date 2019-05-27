package com.znsd.instapundit.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class LoginLogBean implements Serializable {

    private Integer id;            //主键自增长
    private DictBean way;           //登录方式
    private String ip;              //ip
    private String login_system;    //登录系统
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date login_time;        //登录时间
    private String user;            //用户

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DictBean getWay() {
        return way;
    }

    public void setWay(DictBean way) {
        this.way = way;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getLogin_time() {
        return login_time;
    }

    public void setLogin_time(Date login_time) {
        this.login_time = login_time;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLogin_system() {
        return login_system;
    }

    public void setLogin_system(String login_system) {
        this.login_system = login_system;
    }
}
