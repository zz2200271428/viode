package com.znsd.instapundit.param;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class ImageParam  implements Serializable {

    private Integer page; // 当前页码
    private Integer limit; // 条数
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start;         // 开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end;           // 截至时间
    private String desc;       //图片描述
    private String status ;     //图片状态


    @Override
    public String toString() {
        return "ImageParam{" +
                "page=" + page +
                ", limit=" + limit +
                ", start=" + start +
                ", end=" + end +
                ", desc='" + desc + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPage() {
        return (page-1)*limit;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
