package com.znsd.instapundit.param;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class CollectParam implements Serializable {

    private Integer page; // 当前页码
    private Integer limit; // 条数
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start;         // 开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end;           // 截至时间
    private String desc;       //收藏标题描述
    private Integer status ;     // 收藏状态
    private  String spe_title; // 专栏的标题
    private Integer spe_status;  // 收藏专栏 状态

    @Override
    public String toString() {
        return "CollectParam{" +
                "page=" + page +
                ", limit=" + limit +
                ", start=" + start +
                ", end=" + end +
                ", desc='" + desc + '\'' +
                ", status=" + status +
                ", spe_title='" + spe_title + '\'' +
                ", spe_status=" + spe_status +
                '}';
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPage() {
        return (page-1)*limit;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSpe_title() {
        return spe_title;
    }

    public void setSpe_title(String spe_title) {
        this.spe_title = spe_title;
    }

    public Integer getSpe_status() {
        return spe_status;
    }

    public void setSpe_status(Integer spe_status) {
        this.spe_status = spe_status;
    }
}
