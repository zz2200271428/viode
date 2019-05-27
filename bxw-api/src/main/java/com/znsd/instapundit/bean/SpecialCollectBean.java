package com.znsd.instapundit.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class SpecialCollectBean implements Serializable {

    private Integer id;     // id 自增主键
    private Integer uid;    // 用户 id
    private Integer cid;   // 专栏主键
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    private Date time;      // 收藏时间

    private SpecialColumnBean specialColumn; // 专栏收藏
    private AppUserBean appUser;  //  手机端用户


    @Override
    public String toString() {
        return "SpecialCollectBean{" +
                "id=" + id +
                ", uid=" + uid +
                ", cid=" + cid +
                ", time=" + time +
                ", specialColumn=" + specialColumn +
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

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public SpecialColumnBean getSpecialColumn() {
        return specialColumn;
    }

    public void setSpecialColumn(SpecialColumnBean specialColumn) {
        this.specialColumn = specialColumn;
    }

    public AppUserBean getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUserBean appUser) {
        this.appUser = appUser;
    }
}
