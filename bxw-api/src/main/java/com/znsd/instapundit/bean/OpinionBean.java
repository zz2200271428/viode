package com.znsd.instapundit.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class OpinionBean implements Serializable {

    private Integer id;         //主键
    private String content;     //意见内容
    private String ip;          //ip
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date time;          //提问时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
