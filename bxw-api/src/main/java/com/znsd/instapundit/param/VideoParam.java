package com.znsd.instapundit.param;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

public class VideoParam implements Serializable {

    @NotEmpty
    private Integer page;
    @NotEmpty
    private Integer limit;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start;         // 开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end;           // 截至时间
    private String video_title; // 视频标题
    private Integer video_type; // 视频状态
    private Integer video_status; // 视频状态
    private Integer check_status; // 审核状态


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

    public Integer getVideo_status() {
        return video_status;
    }

    public void setVideo_status(Integer video_status) {
        this.video_status = video_status;
    }

    public Integer getCheck_status() {
        return check_status;
    }

    public void setCheck_status(Integer check_status) {
        this.check_status = check_status;
    }

    @Override
    public String toString() {
        return "VideoParam{" +
                "page=" + page +
                ", limit=" + limit +
                ", start=" + start +
                ", end=" + end +
                ", video_title='" + video_title + '\'' +
                ", video_type=" + video_type +
                ", video_status=" + video_status +
                ", check_status=" + check_status +
                '}';
    }
}
