package com.znsd.instapundit.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 问题实体类映射tb_ask表
 */
public class AskBean implements Serializable {

    private Integer id; // 主键
    private AppUserBean user; // 提问用户
    private StoreBean store; // 被提问店铺
    private String issue; // 问题
    private BigDecimal reward; // 酬劳
    private Date ask_time; // 提问时间
    private Integer ask_status; // 问题状态（1：未完成【默认值】、2：已完成）
    private Integer status; // 记录状态（1：正常【默认值】、2：删除）

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

    public StoreBean getStore() {
        return store;
    }

    public void setStore(StoreBean store) {
        this.store = store;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public BigDecimal getReward() {
        return reward;
    }

    public void setReward(BigDecimal reward) {
        this.reward = reward;
    }

    public Date getAsk_time() {
        return ask_time;
    }

    public void setAsk_time(Date ask_time) {
        this.ask_time = ask_time;
    }

    public Integer getAsk_status() {
        return ask_status;
    }

    public void setAsk_status(Integer ask_status) {
        this.ask_status = ask_status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AskBean{" +
                "id=" + id +
                ", user=" + user +
                ", store=" + store +
                ", issue='" + issue + '\'' +
                ", reward=" + reward +
                ", ask_time=" + ask_time +
                ", ask_status=" + ask_status +
                ", status=" + status +
                '}';
    }
}
