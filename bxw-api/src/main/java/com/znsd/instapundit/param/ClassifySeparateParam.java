package com.znsd.instapundit.param;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class ClassifySeparateParam implements Serializable {

    private String content;     //搜索关键字

    private Integer page; // 当前页码

    private Integer limit; // 条数

    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date start;         // 开始时间

    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date end;           // 截至时间

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        return "ClassifySeparateParam{" +
                "content='" + content + '\'' +
                ", page=" + page +
                ", limit=" + limit +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
