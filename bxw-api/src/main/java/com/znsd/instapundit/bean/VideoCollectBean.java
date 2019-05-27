package com.znsd.instapundit.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class VideoCollectBean implements Serializable {

    private Integer id;     // id 自增主键
    private Integer uid;    // 用户 id
    private Integer status; // 用户状态
    private Integer vid ;   // 专栏主键
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone ="GTM+8")
    private Date time;      // 收藏时间

    private VideoBean video ;      //  视频
    private AppUserBean appUser;  //  手机端用户

    @Override
    public String toString() {
        return "VideoCollectBean{" +
                "id=" + id +
                ", uid=" + uid +
                ", status=" + status +
                ", vid=" + vid +
                ", time=" + time +
                ", video=" + video +
                ", appUser=" + appUser +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
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

    public AppUserBean getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUserBean appUser) {
        this.appUser = appUser;
    }
}
