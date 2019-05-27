package com.znsd.instapundit.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论表 tb_comment
 */
public class CommentBean implements Serializable {

    private Integer id;
    private AppUserBean user; // 评论用户
    private String comment; // 评论内容
    @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss", timezone = "GMT+8")
    private Date time; // 评论时间
    private VideoBean video; // 被评论的视频
    private Integer status; // 状态（1：正常、2：删除）

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AppUserBean getUser() {
        return user;
    }

    public void setUser(AppUserBean user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public VideoBean getVideo() {
        return video;
    }

    public void setVideo(VideoBean video) {
        this.video = video;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CommentBean{" +
                "id=" + id +
                ", user=" + user +
                ", comment='" + comment + '\'' +
                ", time=" + time +
                ", video=" + video +
                ", status=" + status +
                '}';
    }
}
