package com.znsd.instapundit.param;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class StoreParam implements Serializable {

    private Integer page; // 显示总页数
    private Integer limit; // 显示条数
    private String sname; // 店铺名称
    private String uname; // 用户真实姓名
    private Integer check_status; // 状态
    private String store_desc; // 店铺介绍
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start; // 开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end; // 结束时间
    private Integer status;

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

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StoreParam{" +
                "page=" + page +
                ", limit=" + limit +
                ", sname='" + sname + '\'' +
                ", uname='" + uname + '\'' +
                ", check_status=" + check_status +
                ", store_desc='" + store_desc + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", status=" + status +
                '}';
    }
}
