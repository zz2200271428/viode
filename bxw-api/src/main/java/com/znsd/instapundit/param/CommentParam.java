package com.znsd.instapundit.param;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

public class CommentParam implements Serializable {

    @NotEmpty
    private Integer page;
    @NotEmpty
    private Integer limit;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start;         // 开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end;           // 截至时间
    private String comment;
    private Integer status;
    private Integer videoID;

    public CommentParam() {
    }

    public CommentParam(@NotEmpty Integer page, @NotEmpty Integer limit, @NotEmpty Integer videoID) {
        this.page = (page - 1) * limit;
        this.limit = limit;
        this.videoID = videoID;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public Integer getVideoID() {
        return videoID;
    }

    public void setVideoID(Integer videoID) {
        this.videoID = videoID;
    }

    @Override
    public String toString() {
        return "CommentParam{" +
                "page=" + page +
                ", limit=" + limit +
                ", start=" + start +
                ", end=" + end +
                ", comment='" + comment + '\'' +
                ", status=" + status +
                ", videoID=" + videoID +
                '}';
    }
}
