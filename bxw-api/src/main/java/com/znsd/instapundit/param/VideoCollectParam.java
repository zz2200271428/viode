package com.znsd.instapundit.param;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class VideoCollectParam implements Serializable {

    private Integer page; // 当前页码
    private Integer limit; // 条数
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start;         // 开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end;           // 截至时间
    private String desc;       //收藏标题描述
    private Integer status ;     // 收藏状态
    private  String video_title; // 专栏的标题
    private Integer video_type; // 视频状态

    @Override
    public String toString() {
        return "VideoCollectParam{" +
                "page=" + page +
                ", limit=" + limit +
                ", start=" + start +
                ", end=" + end +
                ", desc='" + desc + '\'' +
                ", status=" + status +
                ", video_title='" + video_title + '\'' +
                ", video_type=" + video_type +
                '}';
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

    public String getVideo_title() {
        return video_title;
    }

    public void setVideo_title(String video_title) {
        this.video_title = video_title;
    }

    public Integer getVideo_type() {
        return video_type;
    }

    public void setVideo_type(Integer video_type) {
        this.video_type = video_type;
    }
}
