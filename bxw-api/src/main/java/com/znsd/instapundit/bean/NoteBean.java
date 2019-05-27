package com.znsd.instapundit.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * 短信属性类
 */
public class NoteBean implements Serializable {
    private Integer id;         //主键
    private String phone;       //电话
    private String note;        //短信内容
    private String type;        //类型

    private String email;       //邮箱内容

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date time;          //发送时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "NoteBean{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", note='" + note + '\'' +
                ", type='" + type + '\'' +
                ", email='" + email + '\'' +
                ", time=" + time +
                '}';
    }
}
