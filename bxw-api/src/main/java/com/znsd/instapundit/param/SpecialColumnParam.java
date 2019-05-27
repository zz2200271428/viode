package com.znsd.instapundit.param;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

public class SpecialColumnParam implements Serializable {

    @NotEmpty
    private Integer page;
    @NotEmpty
    private Integer limit;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start;         // 开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end;           // 截至时间
    private String spe_title; // 专栏标题

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

    public String getSpe_title() {
        return spe_title;
    }

    public void setSpe_title(String spe_title) {
        this.spe_title = spe_title;
    }

    @Override
    public String toString() {
        return "SpecialColumnParam{" +
                "page=" + page +
                ", limit=" + limit +
                ", start=" + start +
                ", end=" + end +
                ", spe_title='" + spe_title + '\'' +
                '}';
    }
}
