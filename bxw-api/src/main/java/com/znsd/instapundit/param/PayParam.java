package com.znsd.instapundit.param;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class PayParam implements Serializable {

    private Integer page;//显示总页数
    private Integer limit;//显示条数
    private Integer status;//状态
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start;//开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end;//结束时间

  

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}

