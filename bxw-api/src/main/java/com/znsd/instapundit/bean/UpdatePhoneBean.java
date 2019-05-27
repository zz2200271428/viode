package com.znsd.instapundit.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class UpdatePhoneBean implements Serializable {
    private Integer id;
    private Integer userId;
    private String username;
    private String old_phone;
    private String new_phone;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date create_time;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updated_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOld_phone() {
        return old_phone;
    }

    public void setOld_phone(String old_phone) {
        this.old_phone = old_phone;
    }

    public String getNew_phone() {
        return new_phone;
    }

    public void setNew_phone(String new_phone) {
        this.new_phone = new_phone;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(Date updated_time) {
        this.updated_time = updated_time;
    }

    @Override
    public String toString() {
        return "UpdatePhoneBean{" +
                "id=" + id +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", old_phone='" + old_phone + '\'' +
                ", new_phone='" + new_phone + '\'' +
                ", create_time=" + create_time +
                ", updated_time=" + updated_time +
                '}';
    }
}
