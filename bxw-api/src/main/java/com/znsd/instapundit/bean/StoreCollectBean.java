package com.znsd.instapundit.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class StoreCollectBean implements Serializable {

    private Integer id ;    // 表主键
    private Integer uid;    // 用户主键
    private Integer sid;    // 店铺主键
    private Integer count;  // 默认值0单数代表正在关注双数代表没有关注

    private StoreBean storeBean; // 店铺收藏
    private AppUserBean appUser;  //  手机端用户
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone ="GTM+8")
    private Date time;      // 收藏时间

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "StoreCollectBean{" +
                "id=" + id +
                ", uid=" + uid +
                ", sid=" + sid +
                ", count=" + count +
                ", storeBean=" + storeBean +
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

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public StoreBean getStoreBean() {
        return storeBean;
    }

    public void setStoreBean(StoreBean storeBean) {
        this.storeBean = storeBean;
    }

    public AppUserBean getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUserBean appUser) {
        this.appUser = appUser;
    }
}
